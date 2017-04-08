package cn.iflin.spider;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * ��ȡ��������
 */
public class ContentTest extends HttpClient{
	/*
	 * ��ȡ�㶫ʡ��������������
	 */
	public  void getTextToGDCZT(String htmlUrl){
		Elements content = super.getHttpClient(htmlUrl).select("div.content");	//��ȡ�����б�
		if(content!=null){
			String title = content.select("h1").text();
			String time = content.select("span.pub_time").text();
			String source = content.select("span#source_baidu").text();
			String text = content.select("p").html();
			System.out.println(
					"title: "+title+"\n"+
					"time:  "+time+"\n"+
					"source:"+source+"\n"+
					"text: "+text+"\n");
		}
	}
	
	/*
	 * ��ȡ�㶫�Ƽ�����������
	 */
	public  void getTextToGDSTC(String htmlUrl){
		Element content = super.getHttpClient(htmlUrl).body();	//��ȡ�����б�
		if(content!=null){
			String title = content.select("div.title").text();
			String time = content.select("div.time").text();
			String source = content.select("div.time").text();
			String text = content.select("div.content").text();
			System.out.println(
					"title: "+title+"\n"+
					"time:  "+time+"\n"+
					"source:"+source+"\n"+
					"text: "+text+"\n");
		}
	}
}

