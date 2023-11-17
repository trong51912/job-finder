package com.jobfinder.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jobfinder.converter.ApplicantConverter;
import com.jobfinder.converter.JobConverter;
import com.jobfinder.converter.UserConverter;
import com.jobfinder.dto.ApplicantDTO;
import com.jobfinder.dto.JobDTO;
import com.jobfinder.dto.UserDTO;
import com.jobfinder.entity.ApplicantEntity;
import com.jobfinder.entity.JobEntity;
import com.jobfinder.entity.SkillEntity;
import com.jobfinder.entity.UserEntity;
import com.jobfinder.repository.CategoryRepository;
import com.jobfinder.repository.EmployerRepository;
import com.jobfinder.repository.JobRepository;
import com.jobfinder.repository.SkillRepository;
import com.jobfinder.service.IJobService;
import com.jobfinder.service.IUserService;
import com.jobfinder.util.SearchUtils;

@Service
public class JobService implements IJobService {


	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private JobConverter jobConverter;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private EmployerRepository employerRepository;

	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private ApplicantConverter applicantConverter;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private IUserService userService;
	
	public JobService(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public JobDTO findById(Long id) {
		return jobConverter.toDto(jobRepository.findOne(id));
	}

	@Override
	public List<JobDTO> findAll() {
		List<JobDTO> models = new ArrayList<>();
		List<JobEntity> entities = jobRepository.findAll();
		for (JobEntity item : entities) {
			JobDTO userModel = jobConverter.toDto(item);
			models.add(userModel);
		}
		return models;
	}
	
	@Override
	public List<JobDTO> findAll(Pageable pageable) {
		List<JobDTO> models = new ArrayList<>();
		List<JobEntity> entities = jobRepository.findAll(pageable).getContent();
		for (JobEntity item : entities) {
			JobDTO userModel = jobConverter.toDto(item);
			models.add(userModel);
		}
		return models;
	}
	

	@Override
	@Transactional
	public JobDTO save(JobDTO dto) {
		JobEntity jobEntity = new JobEntity();
		if (dto.getId() != null) {
			JobEntity oldJob = jobRepository.findOne(dto.getId());
			jobEntity = jobConverter.toEntity(oldJob, dto);
		} else {
			jobEntity = jobConverter.toEntity(dto);
			List<SkillEntity> skills = new ArrayList<>();
			Date mysqlDate = null;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf.parse(dto.getDeadline());
				mysqlDate = new Date(date.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			jobEntity.setApplicationDeadline(mysqlDate);
			for (Long skillId : dto.getSkills()) {
				skills.add(skillRepository.findOne(skillId));
			}
			jobEntity.setSkills(skills);
			if (dto.getCategory_id() != null) {
				jobEntity.setCategory(categoryRepository.findOne(dto.getCategory_id()));
			}
			if (dto.getEmployer_id() != null) {
				jobEntity.setEmployer(employerRepository.findOne(dto.getEmployer_id()));
			}
		}
		return jobConverter.toDto(jobRepository.save(jobEntity));
	}

	@Override
	public List<JobDTO> filter(Pageable pageable, Long categoryId, String type, int salary, String location) {
		List<JobDTO> result = new ArrayList<>();
		if (categoryId != 0) {
			result = removeDuplicateJob(result, jobRepository.findByCategoryId(pageable, categoryId).getContent());
		}
		if (!type.equals("")) {
			result = removeDuplicateJob(result, jobRepository.findByType(pageable,type).getContent());
		}
		if (salary != 1) {
			result = removeDuplicateJob(result, jobRepository.findBySalary(pageable,salary).getContent());
		}
		if (!location.equals("")) {
			result = removeDuplicateJob(result, jobRepository.findByLocation(pageable,location).getContent());
		}
		return result;
	}
	
	@Override
	public List<JobDTO> findByTitle(Pageable pageable, String keyword) {
	    String keywordWithoutAccents = SearchUtils.removeAccents(keyword);
	    List<JobDTO> models = new ArrayList<>();
		List<JobEntity> jobs = jobRepository.findByTitleContaining(pageable, keywordWithoutAccents);
		for (JobEntity item : jobs) {
			JobDTO userModel = jobConverter.toDto(item);
			models.add(userModel);
		}
		return models;
		
	}
	
	//Ham filter theo tung truong hop
	public List<JobDTO> removeDuplicateJob(List<JobDTO> jobDTO, List<JobEntity> jobEntity) {
		List<JobDTO> jobfiltered = new ArrayList<>();
		if(jobDTO.size()!=0) {
			for(int i=0 ; i<jobDTO.size() ; i++) {
				for(JobEntity job: jobEntity) {
					if(jobDTO.get(i).getId()==job.getId()) {
						jobfiltered.add(jobConverter.toDto(job));
					}
				}
			}
		}else {
			for(JobEntity job: jobEntity) {
				jobfiltered.add(jobConverter.toDto(job));
			}
		}
		return jobfiltered;
	}

	@Override
	public void deleteJobs(List<Long> jobIds) {
		for(Long jobId   : jobIds ) {
			jobRepository.delete(jobId);
		}
	}
		
	public List<JobDTO> findByEmployerId(Long employer_id) {
		List<JobDTO> result = new ArrayList<>();
		for(JobEntity job: jobRepository.findByEmployerId(employer_id)) {
			result.add(jobConverter.toDto(job));
		}
		return result;
	}

	@Override
	public int getTotalItem() {
		return (int) jobRepository.count();
	}


	@Override
	public List<ApplicantDTO> findApplicantsForJob(Long jobId) {
	    List<ApplicantDTO> applicantDTOList = new ArrayList<>();
	    JobEntity job = jobRepository.findOne(jobId);

	    if (job != null) {
	        List<ApplicantEntity> applicants = job.getApplicants();
	        for (ApplicantEntity applicant : applicants) {
	            UserEntity users = applicant.getUser();
	            if (users != null) {
	                ApplicantDTO applicantDTO = applicantConverter.toDto(applicant);
	                UserDTO userDTO = userConverter.toDto(users);
	                
	                applicantDTO.setUser(userDTO);
	                
	                applicantDTOList.add(applicantDTO);
	            }
	        }
	    }
	    return applicantDTOList;
	}





//	@Override
//	public List<ApplicantDTO> findApplicantsForJob(Long jobId) {
//		List<ApplicantDTO> applicantDTOList = new ArrayList<>();
//		JobEntity job = jobRepository.findOne(jobId);
//		
//		if (job != null) {
//            List<ApplicantEntity> applicants = job.getApplicants();
//            for (ApplicantEntity applicant : applicants) {
//            	
//                applicantDTOList.add(applicantConverter.toDto(applicant));
//            }
//        }
//		return applicantDTOList;
//	}
	
}