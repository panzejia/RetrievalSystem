package cn.iflin.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
<<<<<<< HEAD
=======
import java.sql.SQLException;
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
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
