package cn.iflin.model;

public class ArticleModel {
	private String title;
	private String time;
	private String content;
	private String articleId;
	private String source;
	public ArticleModel(String title,String time,String content,String articleId,String source){
		this.title=title;
		this.time=time;
		this.content=content;
		this.articleId=articleId;
		this.source=source;
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
	public String getArticleId() {
		return articleId;
	}
	public String getSource() {
		return source;
	}
}
