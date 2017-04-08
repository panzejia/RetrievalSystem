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
    public void sendEmail(String mailAdd)  {  
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
	        msg.setText("title:关于2017年农业发展和农村工作专项资金（“三高”农业及发展粮食生产）安排情况的公示"
	        		+ "time:2017-03-31 00:00:00.0content: 　　根据省级财政专项资金管理办法的有关要求，"
	        		+ "现将2017年农业发展和农村工作专项资金(“三高”农业及发展粮食生产)安排情况予以公示(详见附件)。"
	        		+ "公示期自2017年3月31日至4月6日，共7天。如对公示内容有异议，请在公示期向省财政厅、省农业厅反映。"
	        		+ "以个人名义反映情况的，请提供真实姓名、联系方式和反映事项证明材料等;以单位名义反映情况的，"
	        		+ "请提供单位真实名称(加盖公章)、联系人、联系方式和反映事项证明材料等。 　　"
	        		+ "(省财政厅联系人：尹 旭，电话：020-83170593) 　　(省农业厅联系人：刘 娟，电话：020-37288209) 　　"
	        		+ "附件：2017年农业发展和农村工作专项资金(“三高”农业及发展粮食生产)安排表 　　"
	        		+ "广东省财政厅 　　"
	        		+ "2017年3月28日"
	        		+ " 附件：2017年农业发展和农村工作专项资金(“三高”农业及发展粮食生产)安排表.xls");  
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
