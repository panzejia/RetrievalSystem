package cn.iflin.spider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/*
 * ��ȡҳ������
 */
public class HttpClient {
	
	
	/*
	 * ����������
	 * return string (ҳ������)
	 */
	public Document getHttpClient(String htmlUrl){
		Document doc = null;
	    try {
	    	//��Ϊ���ֱ������⣬��˴���ɽ��
	    	System.out.println("��Ӧ�� " + htmlUrl);
			doc = Jsoup.parse(new URL(htmlUrl).openStream(),"utf-8",htmlUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return doc;		
	}
}
