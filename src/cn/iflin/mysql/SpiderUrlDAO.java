package cn.iflin.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SpiderUrlDAO {
	public String getNewUrl() throws Exception{
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		Statement stmt = conn.createStatement();
		ResultSet result =stmt.executeQuery("select * from urlmanager where id=(select max(id) from urlmanager) ");
		String url = "";
		if(result.next()){
			url = result.getString("newUrl");
		}
		return url;
	}
	public void addNewUrl(String url,int id) throws Exception{
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		String sql = "INSERT INTO urlmanager(newUrl,UrlId) VALUES (?,?)";
		PreparedStatement preStmt=conn.prepareStatement(sql); 
		preStmt.setString(1, url);
		preStmt.setInt(2, id);
		int result=preStmt.executeUpdate();
		if(result!=1){
			System.out.println("添加失败");
		}else{
			System.out.println("添加成功");
		}
	}
	
	public void addAticle(String title,String time ,String context,String url,String articleId) throws Exception{
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		String sql = "INSERT INTO context(Title,Time,Context,Url,AticleId) VALUES (?,?,?,?,?)";
		PreparedStatement preStmt=conn.prepareStatement(sql); 
		preStmt.setString(1, title);
		preStmt.setString(2, time);
		preStmt.setString(3, context);
		preStmt.setString(4, url);
		preStmt.setString(5, articleId);
		int result=preStmt.executeUpdate();
		if(result!=1){
			System.out.println("添加失败");
		}else{
			System.out.println("添加成功");
		}
	}
	
}
