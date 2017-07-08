package cn.iflin.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.iflin.model.MysqlConnection;

public class UserOperating {
	private static Statement st;
	private static Connection conn=null;
//	连接到数据库
    public UserOperating() {
        try
        {
        	conn = MysqlConnection.getConnection();
            st = conn.createStatement();
            System.out.println("ok");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
  //查询数据表的所有记录
    public ResultSet select(){ 
        ResultSet result = null;
        try
        {
            result = st.executeQuery("SELECT * FROM userinformation");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    //查询指定用户名的记录
    public ResultSet selectpersonal(String name){ 
        ResultSet result = null;
        try
        {
            result = st.executeQuery("SELECT * FROM userinformation Where username='"+name+"'");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    //输出结果
    public void printTABLE(ResultSet result) {
        try
        {
            while(result.next()) {
                System.out.println(result.getString(1)+"   "+result.getString(2)+"   "+result.getString(3)+"   "+result.getString(4)+"   "+result.getString(5));
            }
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //更新指定用户名的记录
    public void executeUpdate(String username,String password,String email,String phone){
    	try{
            String sql = "UPDATE userinformation SET password = '"+password+"', email ='"+email+"', phone ='"+phone+"' WHERE username ='"+username+"'";
            st.executeUpdate(sql);
            }catch(SQLException e1) {    
                // TODO Auto-generated catch block    
                e1.printStackTrace();   
            } 
    }
    //增加新的用户记录到数据表
    public void executeADD(String username,String password,String email,String phone){
    	try{
            String sql = "INSERT userinformation VALUE(NULL,'"+username+"','"+password+"','"+email+"','"+phone+"')";
            st.executeUpdate(sql);
            }catch(SQLException e1) {    
                // TODO Auto-generated catch block    
                e1.printStackTrace();   
            } 
    }
//    删除指定id的记录
    public void executeDELETE(int id){
    	try{
            String sql = "DELETE FROM userinformation WHERE id = "+id;  
            st.executeUpdate(sql);
            }catch(SQLException e1) {    
                // TODO Auto-generated catch block    
                e1.printStackTrace();   
            } 
    }
}
