package cn.iflin.controller;

import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.model.UserBean;
import cn.iflin.server.UserOperating;

@Controller
public class LandingController {
	@RequestMapping("login")
	public String login(){
		return "login";
	}
	@RequestMapping("register")
	public String register(){
		return "register";
	}
	/**
	 * 获取提交的表单数据并传入到数据库中，并返回注册成功界面
	 * @param username
	 * @param password
	 * @param email
	 * @param phone
	 * @return
	 */
	@RequestMapping("/registerAction")
	public String add(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("email") String email, @RequestParam("phone") String phone,
			@RequestParam("realname") String realname,@RequestParam("workspace") String workspace){
		class MyThread extends Thread {  
			public void run() {  
				UserOperating.addInfo(realname, workspace);
			} 
		}
//		获取提交的表单数据并传入到数据库中，并返回注册成功界面
		UserBean u=new UserBean(username,password,email,phone,realname,workspace);
		UserOperating c=new UserOperating();		
		c.executeADD(u.getUsername(),u.getPassword(), u.getEmail(), u.getPhone(),u.getRealname(),u.getWorkspace());
		MyThread myThread1 = new MyThread();  
		myThread1.start();  
		return "registersuccess";
	}
	
	/**
	 * 判断用户名是否存在，若是则返回错误信息	bug
	 * @param name
	 * @return
	 */
	@RequestMapping("/CheckName")
	public String check(@RequestParam("username") String name){
		//判断用户名是否存在，若是则返回错误信息
		UserOperating c = new UserOperating();
		ResultSet result = c.select();
		try {
			while(result.next()){
				if(name.equals(result.getString(2))){
					 return "false";
				}else{
					continue;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "true";
	}
	
	/**
	 * 获取并检查用户密码是否正确
	 * @param request
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/sign")
    public String Check(HttpServletRequest request,@RequestParam("username") String username, @RequestParam("password") String password){	
		//获取并检查用户密码是否正确
		UserOperating c = new UserOperating();
		System.out.println(username+password);
		ResultSet result = c.select();			
		try {
			while (result.next()) {
				if (username.equals(result.getString("username")) && password.equals(result.getString("password"))) {
					request.getSession().setAttribute("username",result.getString(2));
					return "forward:index.jsp";
				} else {
					continue;
				}
			}			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		//检查用户名密码错误时，应弹出错误提示窗口并跳转回原页面
		return "login";
    }

	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response){		
		request.getSession().setAttribute("username",null);
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
}
