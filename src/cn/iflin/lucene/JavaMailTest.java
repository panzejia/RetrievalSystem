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
        // ����debug����  
        props.setProperty("mail.debug", "true");  
        // ���ͷ�������Ҫ�����֤  
        props.setProperty("mail.smtp.auth", "true");  
        // �����ʼ�������������  
        props.setProperty("mail.host", "smtp.163.com");  
        // �����ʼ�Э������  
        props.setProperty("mail.transport.protocol", "smtp");  
          
        // ���û�����Ϣ  
        Session session = Session.getInstance(props);  
          
        // �����ʼ�����  
        Message msg = new MimeMessage(session);  
        try {
			msg.setSubject("Panzejia ����");
			 // �����ʼ�����  
	        msg.setText("title:����2017��ũҵ��չ��ũ�幤��ר���ʽ𣨡����ߡ�ũҵ����չ��ʳ��������������Ĺ�ʾ"
	        		+ "time:2017-03-31 00:00:00.0content: ��������ʡ������ר���ʽ����취���й�Ҫ��"
	        		+ "�ֽ�2017��ũҵ��չ��ũ�幤��ר���ʽ�(�����ߡ�ũҵ����չ��ʳ����)����������Թ�ʾ(�������)��"
	        		+ "��ʾ����2017��3��31����4��6�գ���7�졣��Թ�ʾ���������飬���ڹ�ʾ����ʡ��������ʡũҵ����ӳ��"
	        		+ "�Ը������巴ӳ����ģ����ṩ��ʵ��������ϵ��ʽ�ͷ�ӳ����֤�����ϵ�;�Ե�λ���巴ӳ����ģ�"
	        		+ "���ṩ��λ��ʵ����(�Ӹǹ���)����ϵ�ˡ���ϵ��ʽ�ͷ�ӳ����֤�����ϵȡ� ����"
	        		+ "(ʡ��������ϵ�ˣ��� �񣬵绰��020-83170593) ����(ʡũҵ����ϵ�ˣ��� �꣬�绰��020-37288209) ����"
	        		+ "������2017��ũҵ��չ��ũ�幤��ר���ʽ�(�����ߡ�ũҵ����չ��ʳ����)���ű� ����"
	        		+ "�㶫ʡ������ ����"
	        		+ "2017��3��28��"
	        		+ " ������2017��ũҵ��չ��ũ�幤��ר���ʽ�(�����ߡ�ũҵ����չ��ʳ����)���ű�.xls");  
	        // ���÷�����  
	        msg.setFrom(new InternetAddress("jaypans@163.com"));  
	          
	        Transport transport = session.getTransport();  
	        // �����ʼ�������  
	        transport.connect("jaypans", "hongpp");  
	        // �����ʼ�  
	        transport.sendMessage(msg, new Address[] {new InternetAddress(mailAdd)});  
	        // �ر�����  
	        transport.close();  
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
    }  
}
