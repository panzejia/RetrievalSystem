package cn.iflin.controller;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理用户个人信息修改操作
 */
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.server.GetArticles;
import cn.iflin.server.UserOperating;

@Controller
public class PersonalController {
	@RequestMapping("/personal")
	public String personal(HttpServletRequest request,ModelMap map,Model model) throws SQLException{	
		UserOperating c=new UserOperating();
		ResultSet result =c.selectpersonal((String)request.getSession().getAttribute("username"));
		String username = null,password,email,phone,info = "",workspace,realname;
		while(result.next()){
			username=result.getString("username");
			password=result.getString("password");
			email=result.getString("email");
			phone=result.getString("phone");
			workspace = result.getString("workspace");
			realname=result.getString("realname");
			info=result.getString("info");
			map.put("username", username);
			map.put("password", password);
			map.put("email", email);
			map.put("phone", phone);
			map.put("workspace", workspace);
			map.put("realname", realname);
			break;
		}
		if(username.equals("admin")){
			return "forward:/adminPage";
		}
		if(info==""){
			GetArticles artile = new GetArticles();
	    	model.addAttribute("articles", artile.getArticle());
		}else{
			model.addAttribute("articles", GetArticles.getBestArticles(info));
		}
		return "personal";
	}
	
	@RequestMapping("/change")
	public String change(){
		return "changeinfo";
	}
	
	@RequestMapping("/personalchange")
	public String personalchange(HttpServletRequest request,@RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("password") String password){
		UserOperating c=new UserOperating();
		String username=(String) request.getSession().getAttribute("username");
		c.executeUpdate(username, password, email, phone);
		return "redirect:/personal";
	}
}
