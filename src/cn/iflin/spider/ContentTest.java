package cn.iflin.spider;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/*
 * 获取文章内容
 */
public class ContentTest extends HttpClient{
	/*
	 * 获取广东省财政厅文章内容
	 */
	public  void getTextToGDCZT(String htmlUrl) throws Exception{
		Elements content = super.getHttpClient(htmlUrl).select("div.content");	//获取链接列表
		if(content!=null){
			String title = content.select("h1").text();
			String time = content.select("span.pub_time").text();
			String source = content.select("span#source_baidu").text();
			String text = content.select("p").text();
			System.out.println(
					"title: "+title+"\n"+
					"time:  "+time+"\n"+
					"source:"+source+"\n"+
					"text: "+text+"\n");
			SpiderUrlDAO spiderUrlDao = new SpiderUrlDAO();
			spiderUrlDao.addAticle(title, time, text,htmlUrl);
		}
	}
	
	/*
	 * 获取广东科技厅文章内容
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
	
	/*
	 * 获取海研文章内容
	 */
	public  void getTextToHAIYAN(String htmlUrl) throws Exception{
		Element content = super.getHttpClient(htmlUrl).body();	
		if(content!=null){
			String title = content.select("h3.text-center").text();
			String time = content.select("div.tab-content").select("div.col-md-6").select("span").first().text();
			String source = content.select("div.tab-content").select("div.col-md-6.text-right").text();
			String text = content.select("div.row").text();
			System.out.println(
					"title: "+title+"\n"+
					"time:  "+time+"\n"+
					"source:"+source+"\n"+
					"text: "+text+"\n");
			SpiderUrlDAO spiderUrlDao = new SpiderUrlDAO();
			spiderUrlDao.addAticle(title, time, text,htmlUrl);
		}
	}
}