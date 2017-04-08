package cn.iflin.spider;

public class SpiserMain {

	public static void main(String[] args) throws Exception {
//		String url = "http://www.gdstc.gov.cn/zwgk/tzgg/index@1.htm";
		String url = "http://www.gdczt.gov.cn/zwgk/ggtz";
		selectGetModel(url);
	}
	
	public static void selectGetModel(String htmlUrl){
		UrlManager urlManager = new UrlManager();
		switch (htmlUrl){
		case "http://www.gdstc.gov.cn/zwgk/tzgg/index@1.htm" :
			urlManager.getListUrlFromGDSTC(htmlUrl);;
			break;
		case "http://www.gdczt.gov.cn/zwgk/ggtz":
			urlManager.getListUrlFromGDCZT(htmlUrl);
			break;
		default:
			break;
		}
	}
}
