<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<div class="resultrightcenter">
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>任务名称：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" id="taskName" name="taskName" type="text"placeholder="任务名称" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>来源名称：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" id="sourceName" name="sourceName" type="text"placeholder="来源名称" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>列表页：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" id="liurl" name="listurl" type="text"placeholder="文章列表链接" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>列表起始标记：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" id="liststarttag" name="liststarttag"  type="text"placeholder="标签" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>列表结束标志：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" id="listoptag" name="listoptag"  type="text"placeholder="标签" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>正文链接前缀：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" id="firstUrl" name="firstUrl"  type="text"placeholder="前缀URL" ></textarea></div>
				</div>
				<div class="textarea">
				<input class="inputbutton" type="submit" onclick="doPreview(liurl,'0','allcode');" value="预览整个源代码"/>
					<input class="inputbutton" type="submit" onclick="doPreview(liurl,liststarttag,listoptag);"value="预览列表">
					<input class="inputbutton" type="submit" onclick="doListPreview(liurl,liststarttag,listoptag,firstUrl);"value="预览正文链接">
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>正文测试链接：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" name="texturl" id="texturl" type="text"placeholder="标签" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>标题开始标志：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" name="titlestarttag" id="titlestarttag" type="text"placeholder="标签" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>标题结束标志：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" name="titlestoptag" id="titlestoptag" type="text"placeholder="标签" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>发布时间开始标志：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" name="starttimetag" id="starttimetag" type="text"placeholder="标签" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>发布时间结束标志：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" name="stoptimetag" id="stoptimetag" type="text"placeholder="标签" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>正文开始标志：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" name="contentstarttag" id="contentstarttag" type="text"placeholder="标签" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>正文结束标志：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" name="contentstoptag" id="contentstoptag" type="text"placeholder="标签" ></textarea></div>
				</div>
				<div class="textarea">
					<div style="width:30%;float:left;text-align:center;height:48px; line-height:48px"><a>截止时间：</a></div>
					<div style="width:70%;float:left">
					<textarea autocomplete="off" class="searchinput" name="stoptimereg" id="stoptimereg" type="text"placeholder="正则表达式" ></textarea></div>
				</div>
				<div class="textarea">
					<input class="inputbutton" type="submit" onclick="doPreview(texturl,'0','0');" value="预览正文源代码"/>
					<input class="inputbutton" type="submit" onclick="doPreview(texturl,titlestarttag,titlestoptag);" value="预览标题"/>
					<input class="inputbutton" type="submit" onclick="doPreview(texturl,contentstarttag,contentstoptag);" value="预览正文"/>
					<input class="inputbutton" type="submit" onclick="doTimePreview(liurl,starttimetag,stoptimetag);"value="预览发布时间">
					<input class="inputbutton" type="submit" onclick="doPreview(texturl,titlestarttag,titlestarttag);" value="预览截止时间"/>
				</div>
				<div class="textarea">
					<input class="inputbutton" type="submit" onclick="doSave();" value="提交"/>
				</div>
			</div>
		