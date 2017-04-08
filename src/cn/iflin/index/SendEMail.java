package cn.iflin.index;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.iflin.lucene.JavaMailTest;

/**
 * Servlet implementation class SendEMail
 */
public class SendEMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String mail = request.getParameter("mail");
		System.out.println(mail);
		JavaMailTest sendMail = new JavaMailTest();
		sendMail.sendEmail(mail);
		out.println("<html>");
		out.println("<head>");
		out.println("<title>发送邮箱</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p> 发送成功！ </p>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
