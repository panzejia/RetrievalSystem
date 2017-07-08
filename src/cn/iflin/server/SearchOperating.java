package cn.iflin.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.iflin.model.ArticleModel;

/**
 * 功能：1.根据文章边弄好获取文章内容
 * 2.根据关键词获取文章内容
 * @author Jaypan
 *
 */
public class SearchOperating {
	/**
	 * 根据文章编号获取文章内容
	 * @author Jaypan
	 *
	 */
	public static class SystemGetArticle {
//		public static void main(String[] args) {
//			System.out.println(getSearch("159").getTitle().substring(0,24));
//		}
		/**
		 * 根据编号获取内容
		 * @param querystr
		 * @return
		 */
		public static  ArticleModel getSearch(String querystr)  {
			// 2.请求搜索
			// 从输入中读入搜索请求，然后对它进行解析，最后创建一个Lucene中的Query对象。
			ArticleModel result = null;
			try {
				result = getResult(querystr);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		
		private static  ArticleModel  getResult(String querystr)throws IOException, ParseException{
			Query q = null;
			Analyzer analyzerIKA = new IKAnalyzer();
			Directory fileindex = FSDirectory.open(new File("C:\\LuceneIndex"));
			q = new QueryParser(Version.LUCENE_40, "ArticleId", analyzerIKA).parse(querystr);
			// 3.搜索
			// 创建一个Searcher对象并使用之前创建的Query对象来进行搜索，匹配到的前10个结果封装在TopScoreDocCollector对象里返回。
			IndexReader reader = DirectoryReader.open(fileindex);
			IndexSearcher searcher = new IndexSearcher(reader);
			TopDocs topdocs = searcher.search(q, 1);	 
			ScoreDoc[] hits = topdocs.scoreDocs;
			// 4.打印结果
			ArticleModel article = null;
			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				article = new ArticleModel(d.get("Title"),d.get("Time"),d.get("Context"),d.get("ArticleId"));
			}
			// 此时不需要再访问文档，关闭reader
			reader.close();
			return article;
		}
	}
	/**
	 * 提供主页搜索
	 * @author Jaypan
	 *
	 */
	public static class SystemSearch {
		private ArrayList<ArticleModel> aticles;
		public  ArrayList<ArticleModel> getSearch(String querystr)  {
			// 2.请求搜索
			// 从输入中读入搜索请求，然后对它进行解析，最后创建一个Lucene中的Query对象。
			ArrayList<ArticleModel> result = null;
			try {
				result = getResult(querystr);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		
		private  ArrayList<ArticleModel>  getResult(String querystr)throws IOException, ParseException{
			Query q = null;

			
			Analyzer analyzerIKA = new IKAnalyzer();
			Directory fileindex = FSDirectory.open(new File("C:\\LuceneIndex"));
			System.out.println(fileindex);
			q = new QueryParser(Version.LUCENE_40, "Title", analyzerIKA).parse(querystr);

			// 3.搜索
			// 创建一个Searcher对象并使用之前创建的Query对象来进行搜索，匹配到的前10个结果封装在TopScoreDocCollector对象里返回。
			int hitsPerPage = 100;
			IndexReader reader = DirectoryReader.open(fileindex);
			IndexSearcher searcher = new IndexSearcher(reader);
			
			//把这里的number改为数据库中存储时间的列
			Sort sort = new Sort(new SortField("Time", SortField.Type.STRING, true));
			TopDocs topdocs = searcher.search(q, 30);	 
			searcher.search(q, 30, sort);
			ScoreDoc[] hits = topdocs.scoreDocs;
//			// 4.打印结果
			 aticles = new ArrayList<ArticleModel>();
			String title = null,time = null,context = null,url=null;
			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				url = d.get("Url");
				ArticleModel article = new ArticleModel(d.get("Title"),d.get("Time"),d.get("Context"),d.get("ArticleId"));
				aticles.add(article);
			}
			// 此时不需要再访问文档，关闭reader
			reader.close();
			return aticles;
		}
	}
}
