package cn.iflin.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.iflin.lucene.Article;
import cn.iflin.lucene.SystemSearch;


public class SearchIndex extends HttpServlet {
	private static final long serialVersionUID = 5026294400345092112L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		SystemSearch ssearch = new SystemSearch();
		String searchResult = request.getParameter("searchResult");
		System.out.println(searchResult);
		ArrayList<Article> result  = ssearch.getSearch(searchResult);
		out.println("<!Doctype html>");
		out.print("<html xmlns=http://www.w3.org/1999/xhtml>");
		out.print("<head>");
		out.print("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">");
		out.print("<title>科研项目申报信息检索系统</title>");
		out.print("<style>");
		out.print("<!--");
		out.print("#idxlogo {");
		out.print("    position: relative;");
		out.print("    margin-top: 40px;");
		out.print("    text-align: center;");
		out.print("    width: 100%;");
		out.print("}");
		out.print("#indexpage {");
		out.print("    width: 100%;");
		out.print("    padding: 0;");
		out.print("    margin: 20px 0;");
		out.print("    text-align: center;");
		out.print("}");
		out.print("-->");
		out.print("</style>");
		out.print("</head>");
		out.print("<body>");
		out.print("<div id=\"idxlogo\" >");
		out.print("	<a href=\"http://www.iflin.cn:8080/RetrievalSystem\">");
		out.print("	<img src=\"http://www.iflin.cn:8080/RetrievalSystem/image/logo/logo.png\"></a>");
		out.print("</div>");
		out.print("<div id=\"indexpage\">");
		for(Article a : result){
			out.print("	<div class=\"title\" >");
			out.print("		<h1>"+a.getTitle()+"</h1>");
			out.print("	</div>");
			out.print("	<div class=\"time\" >");
			out.print("		<p>"+a.getTime()+"</p>");
			out.print("	</div>");
			out.print("	<div class=\"content\" >");
			out.print("		<p>"+a.getContent()+"</p>");
			out.print("	</div>");
		}
		out.print("</div>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
	
	
}
