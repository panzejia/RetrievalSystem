package cn.iflin.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.iflin.model.DBModel;
import cn.iflin.model.MysqlConnection;

public class GetArticles {
	public   ArrayList<DBModel> getArticle(){
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		Statement stmt;
		ArrayList<DBModel> article = new ArrayList<DBModel>();
		try {
			stmt = conn.createStatement();
			ResultSet result =stmt.executeQuery("select * from context limit 30");
			DBModel db = null;
			while(result.next()){
				db = new DBModel();
				db.setArticleTtile(result.getString("Title"));
				db.setArticleTime(result.getString("Time"));
				db.setArticleText(result.getString("Context"));
				article.add(db);
			}
//			if (!conn.isClosed()) {
//				conn.close();
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		};
		return article;
	}
}