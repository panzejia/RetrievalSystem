package cn.iflin.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.iflin.server.GetArticles;
import cn.iflin.server.SystemSearch;
/**
 * 用户管理
 * 
 * @author zjn
 */
@Controller
public class IndexController {

    @RequestMapping("")
    public String Create(Model model) {
        return "create";
    }

    @RequestMapping("/getarticle")
    public String Save(@ModelAttribute("a") GetArticles artile,Model model) { // user:视图层传给控制层的表单对象；model：控制层返回给视图层的对象
        model.addAttribute("articles", artile.getArticle());
        System.out.println(artile.getArticle().get(0).getArticleTtile());
        return "findall";
    }
    
    @RequestMapping("/getresult")
    public String getResult( HttpServletRequest request,Model model) { // user:视图层传给控制层的表单对象；model：控制层返回给视图层的对象
    	String searchResult = request.getParameter("searchResult");  
    	SystemSearch getResult = new SystemSearch();
    	model.addAttribute("results", getResult.getSearch(searchResult));
    	System.out.println(getResult.getSearch(searchResult).get(0).getTitle());
        return "result";
    }
}