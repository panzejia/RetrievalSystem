package cn.iflin.controller;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理用户个人信息修改操作
 */
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.server.UserOperating;

@Controller
public class PersonalController {
	@RequestMapping("/personal")
	public String personal(HttpServletRequest request,ModelMap map) throws SQLException{	
		UserOperating c=new UserOperating();
		ResultSet result =c.selectpersonal((String)request.getSession().getAttribute("username"));
		String username,password,email,phone;
		while(result.next()){
			username=result.getString(2);
			password=result.getString(3);
			email=result.getString(4);
			phone=result.getString(5);
			map.put("username", username);
			map.put("password", password);
			map.put("email", email);
			map.put("phone", phone);
			break;
		}
		return "personal";
	}
	@RequestMapping("/change")
	public String change(){
		return "PersonalChange";
	}
	@RequestMapping("/personalchange")
	public String personalchange(HttpServletRequest request,@RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("password") String password){
		UserOperating c=new UserOperating();
		String username=(String) request.getSession().getAttribute("username");
		c.executeUpdate(username, password, email, phone);
		return "redirect:/personal ";
	}
}
