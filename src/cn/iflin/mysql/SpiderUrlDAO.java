package cn.iflin.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		Statement stmt = conn.createStatement();
		String sql = "INSERT INTO urlmanager(newUrl,id) VALUES (?,?)";
		PreparedStatement preStmt=conn.prepareStatement(sql); 
		preStmt.setString(1, url);
		preStmt.setInt(2, id);
		int result=preStmt.executeUpdate();
		if(result!=1){
			System.out.println("���ʧ��");
		}else{
			System.out.println("��ӳɹ�");
		}
	}
}
