package cn.iflin.server;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import cn.iflin.model.DBModel;
import cn.iflin.model.MysqlConnection;
/**
 * 1.放置首页数据
 * 2.查看最新文章
 * 3.用户进入后台后进行推荐文章
 * @author Jaypan
 *
 */
public class GetArticles {
	public   ArrayList<DBModel> getArticle(){
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		Statement stmt;
		ArrayList<DBModel> article = new ArrayList<DBModel>();
		try {
			stmt = conn.createStatement();
			ResultSet result =stmt.executeQuery("SELECT * FROM context  ORDER BY Time DESC limit 3");
			DBModel db = null;
			while(result.next()){
				db = new DBModel();
				db.setArticleTtile(result.getString("Title"));
				db.setArticleTime(result.getString("Time"));
				db.setArticleText(result.getString("ContextNoCode").substring(0,100));
				db.setArticleId(result.getString("AticleId"));
				article.add(db);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		};
		return article;
	}
	/**
	 * 2.查看最新文章
	 * @return
	 */
	public static  ArrayList<DBModel> getArticles(){
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		Statement stmt;
		ArrayList<DBModel> article = new ArrayList<DBModel>();
		try {
			stmt = conn.createStatement();
			ResultSet result =stmt.executeQuery("SELECT * FROM context  ORDER BY Time DESC limit 30");
			DBModel db = null;
			while(result.next()){
				db = new DBModel();
				db.setArticleTtile(result.getString("Title"));
				db.setArticleTime(result.getString("Time"));
				db.setArticleId(result.getString("AticleId"));
				db.setArticleSource(result.getString("Source"));
				article.add(db);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		};
		return article;
	}
	
	/**
	 * 3.用户后台进入后推荐文章
	 * @throws IOException 
	 */
	public static  ArrayList<ArticleModel> getBestArticles(String querystr)  {
		ArrayList<ArticleModel> result = null;
		try {
			result = LuceneOperating.getResult("Context", querystr);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
}