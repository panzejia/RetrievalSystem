package cn.iflin.spider;

public class SpiserMain {

	public static void main(String[] args) throws Exception {
//		String url = "http://www.gdstc.gov.cn/zwgk/tzgg/index@1.htm";
		String url = "http://www.gdczt.gov.cn/zwgk/ggtz";
		selectGetModel(url);
	}
	
<<<<<<< HEAD
	public static void selectGetModel(String htmlUrl) throws Exception{
=======
	public static void selectGetModel(String htmlUrl){
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
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
