package com.jobfinder.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jobfinder.service.IJobService;
import com.jobfinder.service.IRoleService;
import com.jobfinder.service.IUserService;
import com.jobfinder.service.impl.RoleService;

@Controller(value = "permisionControllerOfAdmin")
@RequestMapping("/quan-tri/nguoi-tim-viec")
public class PermisionUser {
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IJobService jobService ;
	
	@RequestMapping(value="/blockUser/{userId}", method = RequestMethod.GET)
    public String blockUser(@PathVariable Long userId, RedirectAttributes redirectAttributes) {
        userService.blockUser(userId);
        redirectAttributes.addFlashAttribute("message", "Thực hiện thành công!");
        return "redirect:/quan-tri/nguoi-tim-viec";
    }

    @RequestMapping(value="/unblockUser/{userId}",method = RequestMethod.GET)
    public String unblockUser(@PathVariable Long userId, RedirectAttributes redirectAttributes) {
        userService.unblockUser(userId);
        redirectAttributes.addFlashAttribute("message", "Thực hiện thành công!");
        return "redirect:/quan-tri/nguoi-tim-viec";
    }

    @RequestMapping(value="/role/{userId}/{roleId}",method = RequestMethod.GET)
    public String updateRole(@PathVariable("userId") Long userId, @RequestParam (value = "roleId", required = false) Long roleId, RedirectAttributes redirectAttributes) {
        roleService.updateRole(userId, roleId);
        redirectAttributes.addFlashAttribute("message", "Thực hiện thành công!");
        return "redirect:/quan-tri/nguoi-tim-viec";
    }

}
