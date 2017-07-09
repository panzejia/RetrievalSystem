package cn.iflin.spider.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import cn.iflin.spider.contentextractor.ContentExtractor;
import cn.iflin.spider.contentextractor.CrawlFile;
import cn.iflin.spider.contentextractor.TitleAnalyer;
import cn.iflin.spider.model.GovernmentModel;
import cn.iflin.spider.util.SpiderUrlDAO;

public class SpiderMain {
	/**
	 * 用来增加新的文章内容
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		inputUrl(GovernmentModel.getGdcylorg());
		inputUrl(GovernmentModel.getGdeigovcn());
		inputUrl(GovernmentModel.getGdpplgopssgovcn());
		inputUrl(GovernmentModel.getGdstacn());
//		inputUrl(GovernmentModel.getGdwhtgovcn());
		inputUrl(GovernmentModel.getHieeducn());
		inputUrl(GovernmentModel.getMcprcgovcn());
		inputUrl(GovernmentModel.getMoeeducn());
		inputUrl(GovernmentModel.getNpopsscngovcn());
		inputUrl(GovernmentModel.getNsfcgovcn());
		inputUrl(GovernmentModel.getOnsgepmoeeducn());
		inputUrl(GovernmentModel.getSeacgovcn());
		inputUrl(GovernmentModel.getSinossnet());
		inputUrl(GovernmentModel.getSinossnetZhaobiao());

	}
	
	private static boolean inputUrl(ArrayList<String> list ) throws Exception{
		String source = "",url="";
		source = list.get(0);
		url = SpiderUrlDAO.getLastUrl(source);
		ArrayList<String> urlList = new ArrayList<String>();
		for(int i =1;i<list.size();i++){
			System.out.println(list.get(i));
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
				if(outputMysql(urlList.get(i-1),source)){
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
	private static boolean outputMysql(String htmlUrl,String source) throws Exception{
        News news = ContentExtractor.getNewsByUrl(htmlUrl);
        if(news==null){
        	return false;
        }
//        System.out.println(news.getUrl());
        System.out.println(news.getTitle());
//        System.out.println(news.getTime());
//        CrawlFile.downLoadFromUrl(news.getContentElement(), htmlUrl);
        SpiderUrlDAO spiderUrlDao = new SpiderUrlDAO();
        if(TitleAnalyer.projectJudgment(news.getTitle())){
        	spiderUrlDao.addAticle(news.getTitle(), news.getTime(), news.getContentElement().toString(),news.getContent(),htmlUrl,source);
        }
        return true;
	}
}
