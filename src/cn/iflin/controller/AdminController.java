package cn.iflin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.iflin.server.AdminOperating;

@Controller
public class AdminController {
	@RequestMapping("/adminPage")
	public String admin(Model model){
		model.addAttribute("users",AdminOperating.getUsers());
		return "admin";
	}
}
