package cn.iflin.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.iflin.model.DBModel;
import cn.iflin.model.MysqlConnection;
/**
 * 放置首页数据
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
			ResultSet result =stmt.executeQuery("select * from context limit 3");
			DBModel db = null;
			while(result.next()){
				db = new DBModel();
				db.setArticleTtile(result.getString("Title").substring(0,24));
				db.setArticleTime(result.getString("Time"));
				db.setArticleText(result.getString("Context"));
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
}