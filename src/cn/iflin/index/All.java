package cn.iflin.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.iflin.lucene.Article;
import cn.iflin.lucene.SystemGetArticle;
import cn.iflin.mysql.DBModel;

/**
 * 获取到最新的十条文章
 */
@WebServlet("/All")
public class All extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SystemGetArticle articles = new SystemGetArticle();
		ArrayList<DBModel> articleTop = articles.getArticle();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
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
		out.println("	<a href=\"http://www.iflin.cn:8080/RetrievalSystem\">");
		out.println("	<img src=\"http://www.iflin.cn:8080/RetrievalSystem/image/logo/logo.png\"></a>");
		out.println("</div>");
		out.println("<div id=\"indexpage\">");
		for(DBModel a : articleTop){
			out.println("	<div class=\"title\" >");
			out.println("		<h1>"+a.getArticleTtile()+"</h1>");
			out.println("	</div>");
			out.println("	<div class=\"time\" >");
			out.println("		<p>"+a.getArticleTime()+"</p>");
			out.println("	</div>");
			out.println("	<div class=\"content\" >");
			out.println("		<p>"+a.getArticleText()+"</p>");
			out.println("	</div>");
		}
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
