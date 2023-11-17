package com.jobfinder.service;

import java.util.List;

import com.jobfinder.dto.ApplicantDTO;
import com.jobfinder.dto.JobDTO;

public interface IApplicantService {
	ApplicantDTO findById(Long id);
	ApplicantDTO findByUserId(Long id);
	List<ApplicantDTO> findAll();
	ApplicantDTO save(ApplicantDTO dto);
	void delete(long[] ids);
	ApplicantDTO findByUsername(String username);
	boolean hasAlreadyApplied(Long applicantId, Long jobId);
	boolean applyForJob(ApplicantDTO applicantDTO, JobDTO jobDTO);
    List<JobDTO> findAppliedJobs(Long applicantId);
}
