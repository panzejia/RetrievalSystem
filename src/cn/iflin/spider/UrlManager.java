package cn.iflin.spider;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
<<<<<<< HEAD

import cn.iflin.mysql.SpiderUrlDAO;
=======
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
/*
 * url��ȡ����mysql���ӵ����б�
 */
public class UrlManager extends HttpClient{
	private ContentTest contentTest = new ContentTest();
	private int ListNum = 0;
<<<<<<< HEAD
	private SpiderUrlDAO spiderUrlDao = new SpiderUrlDAO();
	/*
	 * ��ȡ�㶫�������б�����
	 */
	public void getListUrlFromGDCZT(String htmlUrl) throws Exception{
=======
	/*
	 * ��ȡ�㶫�������б�����
	 */
	public void getListUrlFromGDCZT(String htmlUrl){
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
		//����HTML�ַ�������һ��Documentʵ��  
		Elements content = super.getHttpClient(htmlUrl).select("div.content").select("a");	//��ȡ�����б�
		if(content!=null){
			for(Element element : content){
				String oldUrl = element.attr("href");
<<<<<<< HEAD
				String newUrl = oldUrl.replaceFirst(".", "http://www.gdczt.gov.cn/zwgk/ggtz");
				
				spiderUrlDao.addNewUrl(newUrl, ListNum);
				contentTest.getTextToGDCZT(newUrl);
=======
				String newUrl = oldUrl.replaceFirst(".", htmlUrl);
				contentTest.getTextToGDCZT(newUrl);
//				addToMysql(newUrl,i);
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
				ListNum++;
			}
		}
	}
	
	
	/*
	 * ��ȡ�㶫��ѧ������
	 */
	public void getListUrlFromGDSTC(String htmlUrl){
		//����HTML�ַ�������һ��Documentʵ��  
		Elements content = super.getHttpClient(htmlUrl).select("table.p12_l22").select("a");	//��ȡ�����б�
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
