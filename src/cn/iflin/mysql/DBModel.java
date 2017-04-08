package cn.iflin.mysql;

public class DBModel {
	private Integer id;
	private String newUrl;
	public String getNewUrl() {
		return newUrl;
	}

	public void setNewUrl(String newUrl) {
		this.newUrl = newUrl;
	}

	public Integer getId() throws Exception {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
