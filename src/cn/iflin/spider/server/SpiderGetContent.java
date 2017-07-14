package cn.iflin.spider.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

public class SpiderGetContent {
	/**
	 * 根据标签爬取
	 * @param startTag
	 * @param stopTag
	 * @param doc
	 * @return
	 */
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符
    private static final String regEx_w = "<w[^>]*?>[\\s\\S]*?<\\/w[^>]*?>";//定义所有w标签
    private static String doc="",listCode="";
	
    
	/**
	 * 获取整个的源代码
	 */
	public static String getAllCode(String url){
		try {
			doc = Jsoup.connect(url).timeout(5000).get().toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	/**
	 * 获取列表链接代码
	 * @return
	 */
	public static String getListCode(String start,String stop,String url){
		try {
			doc = Jsoup.connect(url).timeout(5000).get().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String regex_1=start+"([\\s\\S]*)"+stop;
		listCode = setHtmlUrl(url,regex_1);
		return listCode;
	}
	
	/**
	 * 获取列表链接代码，再此之前先获取文章源码，后获取列表代码，最后获取url
	 * @param start
	 * @param stop
	 * @param url
	 * @return
	 */
	public static String getListUrl(String start,String stop,String url,String firstUrl){
		String listUrlCode="";
		try {
			doc = Jsoup.connect(url).timeout(5000).get().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String regex_1=start+"([\\s\\S]*)"+stop;
		listCode = setHtmlUrl(url,regex_1);
		ArrayList<String> msg2 = setHref(listCode);
		//href属性：剪切成完整的
		ArrayList<String> links = alterHref(msg2,firstUrl);//links是可以直接加载的网址
		for(String code:links){
			listUrlCode += code;
		}
		return listUrlCode;
	}
	/**
	 * 在正文页中获取发布时间
	 * @return
	 */
	public static String getStartTime(String start,String stop,String url){
		String regex_1=start+"([\\s\\S]*)"+stop;
		String msg = setHtmlUrl(url,regex_1);
		String  startTime = setTime(msg);
		System.out.println(startTime);
		return startTime;
	}
	
	private static String getContent(String regex,String doc){
		String content = null;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(doc);
		while (matcher.find()) {
			content = matcher.group();
		}
		return content;
	}
	
	private static ArrayList<String> getHref(String regex,String doc){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(doc);
		ArrayList<String> hrefList=new ArrayList<String>();
		while (matcher.find()) { 
			hrefList.add(matcher.group());
		}
		return hrefList;
	}
	
	private static String getTime(String doc){
		String startTime = "";
		String regex="[0-9]*-[0-9]*-[0-9]*";
		String regex_2="[0-9]*年[0-9]*月[0-9]*日";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(doc);
		Pattern pattern_2 = Pattern.compile(regex_2);
		Matcher matcher_2 = pattern_2.matcher(doc);
		if(matcher.find()){
			while (matcher.find()) { 
				startTime = matcher.group();
			}
			return startTime;
		}
		while (matcher_2.find()) { 
			startTime = matcher.group();
		}
		return startTime;
	}
	/**
	 * 根据正则获取到列表的源代码
	 * return code
	 */
	public static String setHtmlUrl(String url,String regex) {
		String doc=null;
		try {
			doc = Jsoup.connect(url).timeout(5000).get().toString();
			if(regex.equals("0")){
				return doc;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(doc==null){
			return "无法获取html源代码";
		}
		String content = getContent(regex,doc);
		if(content==null){
			return "标签输入错误";
		}
		return content;
	}
	
	@SuppressWarnings("null")
	private static ArrayList<String> setHref(String msg){
		String regex="href=\"([\\s\\S]*?)\"";
		ArrayList<String> content = getHref(regex,msg);
		if(msg==null){
			content.add("无法获取html源代码");
			return content;
		}
		if(content==null){
			content.add("标签输入错误");
			return content;
		}
		return content;
	}
	
	@SuppressWarnings("null")
	private static String setTime(String msg){
		String content = getTime(msg);
		if(msg==null){
			content="无法获取html源代码";
			return content;
		}
		if(content==null){
			content="标签输入错误";
			return content;
		}
		return content;
	}
	
	private static ArrayList<String> alterHref(ArrayList<String> msg2,String url)
	{
		System.out.println(msg2.get(0).substring(7,7));
		if(msg2.get(0).substring(6,7).equals(".")||msg2.get(0).substring(6,7).equals("/")){
			String regex_1 = "/[^\\.][^\\s]*htm*[^\"]";
			Pattern pattern = Pattern.compile(regex_1);
			ArrayList<String> links=new ArrayList<String>();
			for(String msg : msg2){
				Matcher matcher = pattern.matcher(msg);
				while (matcher.find()) { 
					links.add(url+"/"+matcher.group());
				}
			}
			return links;
		}
		String regex_1= "[^\"]*/[^\\s]*htm*[^\"]";
		Pattern pattern = Pattern.compile(regex_1);
		ArrayList<String> links=new ArrayList<String>();
		for(String msg : msg2){
			Matcher matcher = pattern.matcher(msg);
			while (matcher.find()) { 
				links.add(url+"/"+matcher.group());
			}
		}
		return links;
	}
	
	
	private static ArrayList<String> setCode(ArrayList<String> links,String regex){
		ArrayList<String> doc=new ArrayList<String>();
		ArrayList<String> content=new ArrayList<String>();
		for(String link : links)
		{
			int i=0;
			try {
				doc.add(Jsoup.connect(link).timeout(5000).get().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			content = getCode(regex,doc);
			if(doc.get(i)==null){
				content.add("无法获取html源代码");
				return content;
			}
			if(content==null){
				content.add("标签输入错误");
				return content;
			}
			i++;
		}
		
		return content;
	}
	
	private static ArrayList<String> getCode(String regex,ArrayList<String> doc){
		Pattern pattern = Pattern.compile(regex);
		ArrayList<String> links=new ArrayList<String>();
		for(String d : doc){
			Matcher matcher = pattern.matcher(d);
			while (matcher.find()) { 
				String kk =delTag(matcher.group());
				links.add(kk);
			}
		}
		return links;
	}
	
	private static String delTag(String htmlStr) {
        Pattern p_w = Pattern.compile(regEx_w, Pattern.CASE_INSENSITIVE);
        Matcher m_w = p_w.matcher(htmlStr);
        htmlStr = m_w.replaceAll(""); // 过滤script标签
 
 
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签
 
 
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签
 
 
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签
 
 
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        
 
        htmlStr = htmlStr.replaceAll(" ", ""); //过滤 
        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        htmlStr = htmlStr.replaceAll("&nbsp", "");
        return htmlStr.trim(); // 返回文本字符串
    }
	
	/**
	 * 获取列表区域源码
	 * @param url
	 * @param reg
	 * @return
	 */
	public static String getListCode(String url,String reg){
		String msg = setHtmlUrl(url,reg);
		return msg;
	}
	/**
	 * 获取正文链接集合
	 * @param listCode
	 * @param reg
	 * @param firstUrl
	 * @return
	 */
	public static ArrayList<String> getArticleUrl(String listCode,String firstUrl){
		ArrayList<String> articleUrlList = setHref(listCode);
		ArrayList<String> links = alterHref(articleUrlList,firstUrl);//links是可以直接加载的网址
		return links;
	}
	/**
	 * 获取正文
	 */
	public static String getOneContent(String url,String reg){
		String msg = setHtmlUrl(url,reg);
		return msg;
	}
	/**
	 * 获取正文没有源码
	 */
	public static String getOneContentNoCode(String url,String reg){
		String msg = delTag(setHtmlUrl(url,reg));
		return msg;
	}
	/**
	 * 获取标题
	 * @param url
	 * @param reg
	 * @return
	 */
	public static String getTitle(String url,String reg){
		String title = delTag(setHtmlUrl(url,reg));
		return title;
	}
	/**
	 * 获取发布时间
	 * @param listCode
	 * @return
	 */
	public static String getStartTime(String url,String reg){
		if(reg.equals("")){
			return "0";
		}
		String msg = setHtmlUrl(url,reg);
		String  startTime = setTime(msg);
		return startTime;
	}
	public static void main(String[] args)
	{
		//获取列表
//		String url="http://www.gdpplgopss.gov.cn/tzgg/";
//		String regex_1="<ul class=\"GsTL5\">([\\s\\S]*)</ul>";
//		String msg = setHtmlUrl(url,regex_1);
//		System.out.println(msg);
		
		//获取发布时间
//		String regex_6="[0-9]*-[0-9]*-[0-9]*";
//		ArrayList<String> msg3 = setTime(msg,regex_6);
//		System.out.println(msg3.get(0));
		
		//获取href
//		String regex_2="href=\"([\\s\\S]*?)\"";	///[^\.][^\s]*html
//		ArrayList<String> msg2 = setHref(msg,regex_2);
//		System.out.println(msg2.get(0));
//		
//		//href属性：剪切成完整的
//		ArrayList<String> links = alterHref(msg2,url);//links是可以直接加载的网址
//		System.out.println(links.get(0));
		
//		//获取标题//正文，发布时间，截止时间(待解决!!!)
//		String regex_4="<div class=\"title\">([\\s\\S]*?)</div>";//标题
//		String regex_5="<strong>([\\s\\S]*)<p align=\"center\">";//正文
//		ArrayList<String> msg4 = setCode(links,regex_4);//标题
//		ArrayList<String> msg5 = setCode(links,regex_5);//正文
//		
//		System.out.println(msg4);
//		System.out.println(msg5);
//		//String regex_7="";//截止时间
//		
//		
	}
	
}
