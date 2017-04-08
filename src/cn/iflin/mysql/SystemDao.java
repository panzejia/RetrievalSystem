package cn.iflin.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SystemDao {

	public void addId(){
		
	}
	
	public void updataId(){
		
	}
	
	public void delId(){
		
	}
	
	public List<DBModel> queryId() throws Exception{
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		Statement stmt = conn.createStatement();
		ResultSet result =stmt.executeQuery("select id from test");
		List<DBModel> id = new ArrayList<DBModel>();
		DBModel db = null;
		
		while(result.next()){
			db = new DBModel();
			db.setId(result.getInt("id"));
			id.add(db);
		}
		return id;
	}
	
	public DBModel GetId(){
		return null;
	}
	

}
