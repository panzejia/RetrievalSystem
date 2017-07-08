package cn.iflin.spider;

import java.io.BufferedReader;
import java.io.FileReader;

public class SpiserMain {

	public static void main(String[] args) throws Exception {
//		String url = "http://www.gdstc.gov.cn/zwgk/tzgg/index@1.htm";
		String url = "http://www.gdczt.gov.cn/zwgk/ggtz/default_6.htm";
		selectGetModel(url);
//		ContentTest c = new ContentTest();
//		BufferedReader bf= new BufferedReader(new FileReader("C:\\Users\\Jaypan\\Desktop\\全国.txt"));
//		String s = null;
//		while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
//			c.getTextToHAIYAN(s);
//		}
		
	}
	
	public static void selectGetModel(String htmlUrl) throws Exception{
		UrlManager urlManager = new UrlManager();
		switch (htmlUrl){
		case "http://www.gdstc.gov.cn/zwgk/tzgg/index@1.htm" :
			urlManager.getListUrlFromGDSTC(htmlUrl);;
			break;
		case "http://www.gdczt.gov.cn/zwgk/ggtz/default_6.htm":
			urlManager.getListUrlFromGDCZT(htmlUrl);
			break;
		default:
			break;
		}
	}
}