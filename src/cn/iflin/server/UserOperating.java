package cn.iflin.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import cn.iflin.model.MysqlConnection;
/**
 * 实现对用户的操作
 * @author Jaypan
 *
 */
public class UserOperating {
	private static Statement st;
	private static Connection conn=null;
//	连接到数据库
    public UserOperating() {
        try
        {
        	conn = MysqlConnection.getConnection();
            st = conn.createStatement();
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
    public void executeADD(String username,String password,String email,String phone,String realname,String workspace){
    	try{
            String sql = "INSERT userinformation VALUE(NULL,'"+username+"','"+password+"','"+email+"','"+phone+"','"+realname+"','"+workspace+"',NULL,NULL)";
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
    
    /**
     * 将真实姓名和工作单位传入python文件中进行爬取其相关内容
     * @param realname
     * @param workspace
     */
    public static void addInfo(String realname,String workspace){
    	System.out.println("开始");
		try {
            //需传入的参数
            //设置命令行传入参数
            String[] args1 = new String[] { "python", "F:\\addinfo.py", realname, workspace};
            Process pr = Runtime.getRuntime().exec(args1);
            pr.waitFor();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
