package cn.iflin.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
<<<<<<< HEAD
=======
import java.sql.SQLException;
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
import java.sql.Statement;

public class SpiderUrlDAO {
	public String getNewUrl() throws Exception{
		Connection conn = MysqlConnection.getConnection();
		//ͨ�����ݵ����Ӳ������ݿ�
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
		//ͨ�����ݵ����Ӳ������ݿ�
<<<<<<< HEAD
		String sql = "INSERT INTO urlmanager(newUrl,UrlId) VALUES (?,?)";
=======
		Statement stmt = conn.createStatement();
		String sql = "INSERT INTO urlmanager(newUrl,id) VALUES (?,?)";
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
		PreparedStatement preStmt=conn.prepareStatement(sql); 
		preStmt.setString(1, url);
		preStmt.setInt(2, id);
		int result=preStmt.executeUpdate();
		if(result!=1){
			System.out.println("����ʧ��");
		}else{
			System.out.println("���ӳɹ�");
		}
	}
<<<<<<< HEAD
	
	public void addAticle(String title,String time ,String context,String url,String articleId) throws Exception{
		Connection conn = MysqlConnection.getConnection();
		//ͨ�����ݵ����Ӳ������ݿ�
		String sql = "INSERT INTO context(Title,Time,Context,Url,AticleId) VALUES (?,?,?,?,?)";
		PreparedStatement preStmt=conn.prepareStatement(sql); 
		preStmt.setString(1, title);
		preStmt.setString(2, time);
		preStmt.setString(3, context);
		preStmt.setString(4, url);
		preStmt.setString(5, articleId);
		int result=preStmt.executeUpdate();
		if(result!=1){
			System.out.println("����ʧ��");
		}else{
			System.out.println("���ӳɹ�");
		}
	}
	
=======
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
}