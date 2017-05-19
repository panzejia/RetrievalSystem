package cn.iflin.spider;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.iflin.mysql.SpiderUrlDAO;
/*
 * url提取并与mysql连接导入列表
 */
public class UrlManager extends HttpClient{
	private ContentTest contentTest = new ContentTest();
	private int ListNum = 0;
	private SpiderUrlDAO spiderUrlDao = new SpiderUrlDAO();
	/*
	 * 获取广东财政厅列表链接
	 */
	public void getListUrlFromGDCZT(String htmlUrl) throws Exception{
		//解析HTML字符串返回一个Document实现
		Elements content = super.getHttpClient(htmlUrl).select("div.content").select("a");	//获取链接列表
		if(content!=null){
			for(Element element : content){
				String oldUrl = element.attr("href");
				String newUrl = oldUrl.replaceFirst(".", "http://www.gdczt.gov.cn/zwgk/ggtz");
				
				spiderUrlDao.addNewUrl(newUrl, ListNum);
				contentTest.getTextToGDCZT(newUrl);
				ListNum++;
			}
		}
	}
	
	
	/*
	 * 获取广东科学技术厅
	 */
	public void getListUrlFromGDSTC(String htmlUrl){
		//解析HTML字符串返回一个Document实现  
		Elements content = super.getHttpClient(htmlUrl).select("table.p12_l22").select("a");	//获取链接列表
		if(content!=null){
			for(Element element : content){
				String oldUrl = element.attr("href");
				String newUrl = oldUrl.replaceFirst(".","http://www.gdstc.gov.cn/");
				contentTest.getTextToGDSTC(newUrl);
				ListNum++;
			}
		}
	}
	
	
}
