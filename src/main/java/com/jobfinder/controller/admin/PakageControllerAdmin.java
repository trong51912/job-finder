package com.jobfinder.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jobfinder.dto.ServiceDTO;
import com.jobfinder.service.IPackageService;

@Controller
public class PakageControllerAdmin {
	@Autowired
	private IPackageService packageService;
	
	// get data on view 
	@GetMapping("/nha-tuyen-dung/goi-dang-ky")
	public String getServicePakage(Model model){
		List<ServiceDTO> services = packageService.finAll();
		model.addAttribute("services", services);
		return "admin/service_package" ;
	}

}
