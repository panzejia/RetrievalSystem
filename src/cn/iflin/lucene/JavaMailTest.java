package cn.iflin.lucene;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailTest {
    public void sendEmail(String mailAdd,String sendText)  {  
        Properties props = new Properties();  
        // 开启debug调试  
        props.setProperty("mail.debug", "true");  
        // 发送服务器需要身份验证  
        props.setProperty("mail.smtp.auth", "true");  
        // 设置邮件服务器主机名  
        props.setProperty("mail.host", "smtp.163.com");  
        // 发送邮件协议名称  
        props.setProperty("mail.transport.protocol", "smtp");  
        // 设置环境信息  
        Session session = Session.getInstance(props);  
        // 创建邮件对象  
        Message msg = new MimeMessage(session);  
        try {
			msg.setSubject("Panzejia 发射");
			 // 设置邮件内容  
	        msg.setText(sendText);  
	        // 设置发件人  
	        msg.setFrom(new InternetAddress("jaypans@163.com"));  
	          
	        Transport transport = session.getTransport();  
	        // 连接邮件服务器  
	        transport.connect("jaypans", "hongpp");  
	        // 发送邮件  
	        transport.sendMessage(msg, new Address[] {new InternetAddress(mailAdd)});  
	        // 关闭连接  
	        transport.close();  
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
    }  
}
