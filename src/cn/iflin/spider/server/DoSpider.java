package cn.iflin.spider.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import cn.iflin.model.MysqlConnection;
import cn.iflin.spider.model.SpiderModel;

public class DoSpider extends SpiderGetContent {
	private static String reg="([\\s\\S]*)";
	private static Connection conn = MysqlConnection.getConnection();
	
	
	public static void main(String[] args) {
		getTask();
	}
	
	/**
	 * 开始运行
	 * @return
	 */
	public static ArrayList<SpiderModel> getTask(){
		ArrayList<SpiderModel> taskList = SpiderMysql.getTask();
		for(SpiderModel task : taskList){
			try {
				doSpider(task);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return taskList;
	}
	/**
	 * 单个任务进行
	 * @param spider
	 * @throws Exception
	 */
	private static void doSpider(SpiderModel spider) throws Exception{
		String taskName = spider.getTaskName();
		String liUrl = spider.getLiUrl();
		String liststartTag = spider.getListstartTag();
		String listopTag = spider.getListopTag();
		String firstUrl = spider.getFirstUrl();
		String listReg = liststartTag+reg+listopTag;
		String listCode = getListCode(liUrl, listReg);
		ArrayList<String> urlList = getArticleUrl(listCode,firstUrl);
		checkUrl(urlList,spider);
//		for (String articleurl:urlList){
//			System.out.println(getTitle(articleurl,titleReg));
//			System.out.println(getOneContent(articleurl,contentreg));
//			System.out.println(getOneContentNoCode(articleurl,contentreg));
//		}
	}
	/**
	 * 校对列表链接是否在数据库中已存在
	 * @param list
	 * @param spider
	 * @return
	 * @throws Exception
	 */
	private static boolean checkUrl(ArrayList<String> list,SpiderModel spider ) throws Exception{
		String sourceName = spider.getSourceName();
		if(list == null){
			return false;
		}
		String url="";
		url = SpiderMysql.getLastUrl(sourceName);
		ArrayList<String> urlList = new ArrayList<String>();
		for(int i =0;i<list.size();i++){
			if(url.equals(list.get(i))){
				break;
			}else{
				urlList.add(list.get(i));
			}
		}
		for(int i =urlList.size();i>=1;i--){
			if(url.equals(urlList.get(i-1))){
				break;
			}else{
				if(getArticle(urlList.get(i-1),sourceName,spider)){
					continue;
				}else{
					return false;
				}
				
			}
		}
		return true;
	}
	
	/**
	 * 获取网址内的内容
	 * @param htmlUrl
	 * @throws Exception
	 */
	private static boolean getArticle(String articleurl,String source,SpiderModel spider) throws Exception{
        SpiderMysql spiderMysql = new SpiderMysql();
		String titlestartTag = spider.getTitlestartTag();
		String titlestopTag = spider.getTitlestopTag();
		String contentstartTag = spider.getContentstartTag();
		String contentstopTag = spider.getContentstopTag();
		String starttimestartTag = spider.getStartTimeStartTag();
		String starttimestopTag = spider.getStartTimeStopTag();
		String starttimeReg = "";
		if(starttimestartTag==null){
			starttimeReg = "";
		}
		starttimeReg = starttimestartTag+reg+starttimestopTag;
		String titleReg = titlestartTag+reg+titlestopTag;
		String contentReg = contentstartTag+reg+contentstopTag;
		String title =getTitle(articleurl,titleReg);
		String starttime = getStartTime(articleurl,starttimeReg);
		String content = getOneContent(articleurl,contentReg);
		String contentNoCode=getOneContentNoCode(articleurl,contentReg);
        if(TitleAnalyer.projectJudgment(title)){
        	Statement stmt;
        	stmt = conn.createStatement();
        	String aritcleId = "";
        	spiderMysql.addAticle(title, starttime, content,contentNoCode,articleurl,source);
			ResultSet result =stmt.executeQuery("SELECT * FROM context  ORDER BY AticleId DESC limit 1");
			while(result.next()){
				aritcleId = result.getString("AticleId");
			}
        	AddIndex.SystemIndex.addAllIndex(aritcleId, title, starttime, content, articleurl, source);
        	AddIndex.ScoreIndex.addIndex(contentNoCode);
//        	Scorer.startScore(aritcleId, news.getTitle());
        }
        return true;
	}
}
