package cn.iflin.mysql;

import java.util.List;

public class Action {

	public static void main(String[] args) throws Exception {
		SystemDao g = new SystemDao();
		SpiderUrlDAO url = new SpiderUrlDAO();
		List<DBModel> gs = g.queryId();
		for (DBModel dbModel : gs) {
			System.out.println(dbModel.getArticleTtile());
		}
			
		
//		url.addNewUrl("salkdjga", 1);
//		System.out.println(url.getNewUrl());
	}
}
