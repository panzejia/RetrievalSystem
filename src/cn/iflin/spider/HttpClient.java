package cn.iflin.spider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/*
 * 获取页面连接
 */
public class HttpClient {
	
	
	/*
	 * 连接主函数
	 * return string (页面内容)
	 */
	public Document getHttpClient(String htmlUrl){
		Document doc = null;
	    try {
	    	//因为出现编码问题，如此处理可解决
	    	System.out.println("响应： " + htmlUrl);
			doc = Jsoup.parse(new URL(htmlUrl).openStream(),"utf-8",htmlUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return doc;		
	}
}
