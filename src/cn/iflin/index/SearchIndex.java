package cn.iflin.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.iflin.lucene.Article;
import cn.iflin.lucene.SystemGetArticle;
import cn.iflin.lucene.SystemSearch;
import cn.iflin.mysql.DBModel;
/*
 * 搜索结果页
 */

public class SearchIndex extends HttpServlet {
	private static final long serialVersionUID = 5026294400345092112L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		SystemSearch ssearch = new SystemSearch();	//Lucene搜索类
		String searchResult = request.getParameter("searchResult");
		System.out.println(searchResult);
		ArrayList<Article> result  = ssearch.getSearch(searchResult);
		out.println("<!Doctype html>");
		out.println("<html xmlns=http://www.w3.org/1999/xhtml>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">");
		out.println("<title>科研项目申报信息检索系统</title>");
		out.println("<style>");
		out.println("<!--");
		out.println("#idxlogo {");
		out.println("    position: relative;");
		out.println("    margin-top: 40px;");
		out.println("    text-align: center;");
		out.println("    width: 100%;");
		out.println("}");
		out.println("#indexpage {");
		out.println("    width: 100%;");
		out.println("    padding: 0;");
		out.println("    margin: 20px 0;");
		out.println("    text-align: center;");
		out.println("}");
		out.println("-->");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id=\"idxlogo\" >");
		out.println("	<a href=\"http://120.25.254.121:8080\">");
		out.println("	<img src=\"image/logo/logo.png\"></a>");
		out.println("</div>");
		out.println("<div id=\"indexpage\">");
		
		String context = " ";
		for(int i=0;i<1;i++){
			context += "<br>标题：   "+result.get(i).getTitle()+"<br>时间：   "+result.get(i).getTime()+"<br>内容：   "+result.get(i).getContent();
		}
		System.out.println(context);
		out.println("<form action=\"SendEmail\" method=\"post\" >");
		out.println("	<p>输入邮箱获取当前申报通知</p>");
		out.println("	<input hidden id=\"searchtext\" type=\"text\" name=\"context\" value=\""+context+"\" >");
		out.println("	<input id=\"searchinput\" type=\"text\" name=\"mail\" value=\"\">");
		out.println("	<input id=\"inputbutton\" type=\"submit\" value=\"订阅文章\" />");
		out.println("</form>");
		for(Article a : result){
			out.println("	<div class=\"title\" >");
			out.println("		<h1>"+a.getTitle()+"</h1>");
			out.println("	</div>");
			out.println("	<div class=\"time\" >");
			out.println("		<p>"+a.getTime()+"</p>");
			out.println("	</div>");
			out.println("	<div class=\"content\" >");
			out.println("		<p>"+a.getContent()+"</p>");
			out.println("	</div>");
		}
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
	
	
}
