package cn.iflin.spider;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

<<<<<<< HEAD
import cn.iflin.mysql.SpiderUrlDAO;

=======
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
/*
 * ��ȡ��������
 */
public class ContentTest extends HttpClient{
	/*
	 * ��ȡ�㶫ʡ��������������
	 */
<<<<<<< HEAD
	public  void getTextToGDCZT(String htmlUrl) throws Exception{
=======
	public  void getTextToGDCZT(String htmlUrl){
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
		Elements content = super.getHttpClient(htmlUrl).select("div.content");	//��ȡ�����б�
		if(content!=null){
			String title = content.select("h1").text();
			String time = content.select("span.pub_time").text();
			String source = content.select("span#source_baidu").text();
<<<<<<< HEAD
			String text = content.select("p").text();
=======
			String text = content.select("p").html();
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
			System.out.println(
					"title: "+title+"\n"+
					"time:  "+time+"\n"+
					"source:"+source+"\n"+
					"text: "+text+"\n");
<<<<<<< HEAD
			SpiderUrlDAO spiderUrlDao = new SpiderUrlDAO();
			spiderUrlDao.addAticle(title, time, text,htmlUrl,htmlUrl);
=======
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
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

