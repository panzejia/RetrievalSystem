package cn.iflin.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * ���ݿ�����
 */
public class MysqlConnection {
<<<<<<< HEAD
	private static final String URL="jdbc:mysql://127.0.0.1:3306/retrievalsystemdb";
	private static final String USER="root";
	private static final String PASSWORD="0503";
=======
	private static final String URL="jdbc:mysql://127.0.0.1:3306/panzejia_test";
	private static final String USER="root";
	private static final String PASSWORD="";
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
	
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


