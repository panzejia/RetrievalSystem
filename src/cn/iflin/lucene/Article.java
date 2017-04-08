package cn.iflin.lucene;

public class Article {
	private String title;
	private String time;
	private String content;
	public Article(String title,String time,String content){
		this.title=title;
		this.time=time;
		this.content=content;
	}
	public String getTitle() {
		return title;
	}

	public String getTime() {
		return time;
	}

	public String getContent() {
		return content;
	}

}
