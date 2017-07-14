package cn.iflin.spider.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.iflin.model.MysqlConnection;
import cn.iflin.spider.model.SpiderModel;

public class SpiderMysql {
	/**
	 * 将信息添加至数据库
	 */
	public static void saveSpiderInfo(String taskName,String sourceName,String liUrl,String liststartTag
			,String listopTag,String firstUrl,String titlestartTag,String titlestopTag
			,String contentstartTag,String contentstopTag,String startTime,String endTime){
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		Statement stmt;
		try{
			stmt = conn.createStatement();
            String sql = "INSERT spiderTaskInfo VALUE(NULL,'"+taskName+"','"+sourceName+"','"+liUrl+"'"
            		+ ",'"+liststartTag+"','"+listopTag+"','"+firstUrl+"','"+titlestartTag+"','"+titlestopTag+"','"
            			+contentstartTag+ "','"+contentstopTag+"')";
            stmt.executeUpdate(sql);
            }catch(SQLException e1) {    
                e1.printStackTrace();   
            } 
	}
	
	/**
	 * 获取数据库中的任务
	 */
	public static ArrayList<SpiderModel> getTaskName(){
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		Statement stmt;
		ArrayList<SpiderModel> taskList = new ArrayList<SpiderModel>();
        ResultSet result = null;
        String taskName,taskId;
        try
        {
        	stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT taskId,taskName FROM spiderTaskInfo");
            if(result==null){
            	SpiderModel sm = new SpiderModel();
            	sm.setTaskName("请新建一个任务");
            	sm.setTaskId("#");
            	taskList.add(sm);
            	return taskList;
            }
            while(result.next()){
            	taskName=result.getString("taskName");
            	taskId=result.getString("taskId");
            	SpiderModel sm = new SpiderModel();
            	sm.setTaskName(taskName);
            	sm.setTaskId(taskId);
            	taskList.add(sm);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
		return taskList;
	}
	/**
	 * 根据taskId查询某个任务
	 */
	public static SpiderModel getTaskById(String taskId){
		SpiderModel sm = new SpiderModel();
		ResultSet result=null;
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		Statement stmt;
		try
        {
			stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM spiderTaskInfo where taskId='"+taskId+"'");
            while(result.next()){
            	sm.setAll(result.getString("taskName"),result.getString("sourceName")
            			,result.getString("liUrl"),result.getString("liststartTag"),result.getString("listopTag")
            			,result.getString("firstUrl"),result.getString("titlestartTag"),result.getString("titlestopTag")
            			,result.getString("contentstartTag"),result.getString("contentstopTag"),"","");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
		return sm;
	}
	
	/**
	 * 查询到数据库所有任务
	 */
	public static ArrayList<SpiderModel> getTask(){
		ArrayList<SpiderModel> taskList = new ArrayList<SpiderModel>();
		SpiderModel sm ;
		ResultSet result=null;
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		Statement stmt;
		try
        {
			stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM spiderTaskInfo");
            while(result.next()){
            	sm = new SpiderModel();
            	sm.setAll(result.getString("taskName"),result.getString("sourceName")
            			,result.getString("liUrl"),result.getString("liststartTag"),result.getString("listopTag")
            			,result.getString("firstUrl"),result.getString("titlestartTag"),result.getString("titlestopTag")
            			,result.getString("starttimestartTag"),result.getString("starttimestopTag")
            			,result.getString("contentstartTag"),result.getString("contentstopTag"));
            	sm.setTaskId(result.getString("taskId"));
            	taskList.add(sm);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
		return taskList;
	}
	
	
	/**
	 * 从数据库中获取到分类下的最新的一条通知 
	 * @return
	 * @throws Exception
	 */
	public static String getLastUrl(String source) throws Exception{
		Connection conn = MysqlConnection.getConnection();
		String sql = "SELECT context.AticleId,context.Url FROM context WHERE "
				+ "context.Source = '"+source+"' ORDER BY context.AticleId DESC";
		//通过数据的连接操作数据库
		Statement stmt = conn.createStatement();
		ResultSet result =stmt.executeQuery(sql);
		String url = "";
		if(result.next()){
			url=result.getString("Url");
			return url;
		}
		return "";
	}
	
	/**
	 * 在数据库中添加一篇文章
	 */
	public void addAticle(String title,String time ,String context,String contextNoCode,String url,String source) throws Exception{
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		String sql = "INSERT INTO context(AticleId,Title,Time,Source,Context,ContextNoCode,Url) VALUES (NULL,?,?,?,?,?,?)";
		PreparedStatement preStmt=conn.prepareStatement(sql); 
		preStmt.setString(1, title);
		preStmt.setString(2, time);
		preStmt.setString(3, source);
		preStmt.setString(4, context);
		preStmt.setString(5, contextNoCode);
		preStmt.setString(6, url);
		int result=preStmt.executeUpdate();
		if(result!=1){
//			System.out.println("添加失败");
		}else{
//			System.out.println("添加成功");
		}
	}
}
