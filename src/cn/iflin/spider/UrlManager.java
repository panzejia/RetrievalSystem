package cn.iflin.spider;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/*
 * url��ȡ����mysql���ӵ����б�
 */
public class UrlManager extends HttpClient{
	private ContentTest contentTest = new ContentTest();
	private int ListNum = 0;
	/*
	 * ��ȡ�㶫�������б�����
	 */
	public void getListUrlFromGDCZT(String htmlUrl){
		//����HTML�ַ�������һ��Documentʵ��  
		Elements content = super.getHttpClient(htmlUrl).select("div.content").select("a");	//��ȡ�����б�
		if(content!=null){
			for(Element element : content){
				String oldUrl = element.attr("href");
				String newUrl = oldUrl.replaceFirst(".", htmlUrl);
				contentTest.getTextToGDCZT(newUrl);
//				addToMysql(newUrl,i);
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
