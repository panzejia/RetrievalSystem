package cn.iflin.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * ���ݿ�����
 */
public class MysqlConnection {
	private static final String URL="jdbc:mysql://127.0.0.1:3306/panzejia_test";
	private static final String USER="root";
	private static final String PASSWORD="";
	
	private static Connection conn=null;
	//ʹ�þ�̬�������������о�̬����
	static {
		try {
			//������������
			Class.forName("com.mysql.jdbc.Driver");
			//���ӱ������ݿ�
			conn =DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ���Ӳ�����
	public static Connection getConnection (){
		return conn;
	}
}


