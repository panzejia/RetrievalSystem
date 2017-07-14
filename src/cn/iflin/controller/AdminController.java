package cn.iflin.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.model.UserBean;
import cn.iflin.server.AdminOperating;
import cn.iflin.server.UserOperating;
import cn.iflin.spider.server.SpiderGetContent;

@Controller
public class AdminController {
	/**
	 * 后台页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminPage")
	public String admin(Model model){
		model.addAttribute("users",AdminOperating.getUsers());
		return "admin/admin";
	}
	/**
	 * 修改某个用户信息
	 * @return
	 */
	@RequestMapping("/changeone")
	public String changeone(){
		return "admin/changeone";
	}
	/**
	 * 删除某个用户
	 */
	@RequestMapping("/deleteone")
	public String deleteone(@RequestParam("id") String id){
		UserOperating u=new UserOperating();
		u.executeDELETE(id);
		return "forward:/adminPage";
	}
	/**
	 * 更改用户信息
	 * @param request
	 * @param id
	 * @param email
	 * @param realname
	 * @param workspace
	 * @return
	 */
	@RequestMapping("/adminchange")
	public String personalchange(HttpServletRequest request, @RequestParam("id") String id,@RequestParam("email") String email, @RequestParam("realname") String realname,@RequestParam("Workspace") String workspace){
		UserOperating c=new UserOperating();
		c.UpdateByadmin(id,realname,email,workspace);
		return "forward:/adminPage";
	}
	/**
	 * 获取到用户信息
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getuser",method=RequestMethod.GET)
	public String getuser(@RequestParam("email")String email,Model model){
		System.out.println(AdminOperating.getUsers(email).getRealname());
		System.out.println(email);;
		model.addAttribute("user",AdminOperating.getUsers(email));
		return "admin/userinfo";
	}
}
