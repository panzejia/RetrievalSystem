package cn.iflin.spider.model;

public class SpiderModel {
	private static String taskId;
	private static String taskName;
	private static String sourceName;
	private static String liUrl;
	private static String liststartTag;
	private static String listopTag;
	private static String firstUrl;
	private static String titlestartTag;
	private static String titlestopTag;
	private static String starttimestartTag;
	private static String starttimestopTag;
	private static String contentstartTag;
	private static String contentstopTag;
	public void setAll(String taskName,String sourceName,String liUrl,String liststartTag
			,String listopTag,String firstUrl,String titlestartTag,String titlestopTag
			,String starttimestartTag,String starttimestopTag
			,String contentstartTag,String contentstopTag){
		SpiderModel.taskName=taskName;
		SpiderModel.sourceName=sourceName;
		SpiderModel.liUrl=liUrl;
		SpiderModel.liststartTag=liststartTag;
		SpiderModel.listopTag=listopTag;
		SpiderModel.firstUrl=firstUrl;
		SpiderModel.titlestartTag=titlestartTag;
		SpiderModel.titlestopTag=titlestopTag;
		SpiderModel.starttimestartTag=starttimestartTag;
		SpiderModel.starttimestopTag=starttimestopTag;
		SpiderModel.contentstartTag=contentstartTag;
		SpiderModel.contentstopTag=contentstopTag;
	}
	/**
	 * return the taskId
	 */
	public  String getTaskId(){
		return taskId;
	}
	/**
	 * set taskId
	 */
	public void setTaskId(String taskId){
		SpiderModel.taskId=taskId;
	}
	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @return the liUrl
	 */
	public  String getLiUrl() {
		return liUrl;
	}
	/**
	 * @return the liststartTag
	 */
	public  String getListstartTag() {
		return liststartTag;
	}
	/**
	 * @return the listopTag
	 */
	public  String getListopTag() {
		return listopTag;
	}
	/**
	 * @return the firstUrl
	 */
	public  String getFirstUrl() {
		return firstUrl;
	}
	/**
	 * @return the titlestartTag
	 */
	public  String getTitlestartTag() {
		return titlestartTag;
	}
	/**
	 * @return the titlestopTag
	 */
	public  String getTitlestopTag() {
		return titlestopTag;
	}
	/**
	 * @return the contentstartTag
	 */
	public  String getContentstartTag() {
		return contentstartTag;
	}
	/**
	 * @return the contentstopTag
	 */
	public  String getContentstopTag() {
		return contentstopTag;
	}
	/**
	 * @return the sourceName
	 */
	public  String getSourceName() {
		return sourceName;
	}
	/**
	 * @return the startTime
	 */
	public  String getStartTimeStartTag() {
		return starttimestartTag;
	}
	/**
	 * @return the endTime
	 */
	public  String getStartTimeStopTag() {
		return starttimestopTag;
	}
	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		SpiderModel.taskName = taskName;
	}
	/**
	 * @param liUrl the liUrl to set
	 */
	public  void setLiUrl(String liUrl) {
		SpiderModel.liUrl = liUrl;
	}
	/**
	 * @param liststartTag the liststartTag to set
	 */
	public  void setListstartTag(String liststartTag) {
		SpiderModel.liststartTag = liststartTag;
	}
	/**
	 * @param listopTag the listopTag to set
	 */
	public  void setListopTag(String listopTag) {
		SpiderModel.listopTag = listopTag;
	}
	/**
	 * @param firstUrl the firstUrl to set
	 */
	public  void setFirstUrl(String firstUrl) {
		SpiderModel.firstUrl = firstUrl;
	}
	/**
	 * @param titlestartTag the titlestartTag to set
	 */
	public  void setTitlestartTag(String titlestartTag) {
		SpiderModel.titlestartTag = titlestartTag;
	}
	/**
	 * @param titlestopTag the titlestopTag to set
	 */
	public  void setTitlestopTag(String titlestopTag) {
		SpiderModel.titlestopTag = titlestopTag;
	}
	/**
	 * @param contentstartTag the contentstartTag to set
	 */
	public  void setContentstartTag(String contentstartTag) {
		SpiderModel.contentstartTag = contentstartTag;
	}
	/**
	 * @param contentstopTag the contentstopTag to set
	 */
	public  void setContentstopTag(String contentstopTag) {
		SpiderModel.contentstopTag = contentstopTag;
	}
	/**
	 * @param url the url to set
	 */
	public  void setSourceName(String sourceName) {
		SpiderModel.sourceName = sourceName;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public  void setStartTimeStartTag(String starttimestartTag) {
		SpiderModel.starttimestartTag = starttimestartTag;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public  void setStartTimeStopTag(String starttimestopTag) {
		SpiderModel.starttimestopTag = starttimestopTag;
	}
}
