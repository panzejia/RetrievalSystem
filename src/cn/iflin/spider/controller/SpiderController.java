package cn.iflin.spider.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.spider.model.SpiderModel;
import cn.iflin.spider.server.SpiderGetContent;
import cn.iflin.spider.server.SpiderMysql;
@Controller
public class SpiderController {
		//预览所有源码，预览列表区域代码
		@RequestMapping(value="/spiderView",method=RequestMethod.GET)
		public String spiderView(@RequestParam("url")String url,@RequestParam("start")String start,
				@RequestParam("stop")String stop,Model model){
			if(stop.equals("allcode")){
				model.addAttribute("view", SpiderGetContent.getAllCode(url));
			}
			model.addAttribute("view", SpiderGetContent.getListCode(start, stop, url));
			return "spider/view";
		}
		//预览发布时间页
		@RequestMapping(value="/spiderTimeView",method=RequestMethod.GET)
		public String spiderListView(@RequestParam("url")String url,@RequestParam("start")String start,
				@RequestParam("stop")String stop,Model model){
			model.addAttribute("view", SpiderGetContent.getStartTime(start, stop, url));
			return "spider/view";
		}
		//预览列表中文章链接页
		@RequestMapping(value="/spiderListUrlView",method=RequestMethod.GET)
		public String spiderListUrlView(@RequestParam("url")String url,@RequestParam("start")String start,
				@RequestParam("stop")String stop,@RequestParam("firstUrl")String firstUrl,Model model){
			model.addAttribute("view", SpiderGetContent.getListUrl(start, stop, url, firstUrl));
			return "spider/view";
		}
		//获取控制板右边部分
		@RequestMapping("/spiderpanelright")
		public String spiderpanelright(){
			return "spider/SpiderPanelRight";
		}
		//保存操作
		@RequestMapping(value="/spiderSave",method=RequestMethod.GET)
		public String spidersave(@RequestParam("sourceName")String sourceName,@RequestParam("taskName")String taskName,
				@RequestParam("liUrl")String liUrl,@RequestParam("liststartTag")String liststartTag,@RequestParam("listopTag")String listopTag,
				@RequestParam("firstUrl")String firstUrl,@RequestParam("titlestartTag")String titlestartTag,@RequestParam("titlestopTag")String titlestopTag,
				@RequestParam("contentstartTag")String contentstartTag,@RequestParam("contentstopTag")String contentstopTag){
			SpiderMysql.saveSpiderInfo(sourceName,taskName,liUrl,liststartTag,listopTag,
					firstUrl,titlestartTag,titlestopTag,contentstartTag,contentstopTag,"","");
			return "spider/savesuccess";
		}
		
		//查询爬虫任务
		@RequestMapping("/spider")
		public String spiderpanelright(Model model){
			ArrayList<SpiderModel> taskList = SpiderMysql.getTaskName();
			model.addAttribute("spiders", taskList);
			return "spider/SpiderControlPanel";
		}
		
		//查询单个爬虫任务
		@RequestMapping(value="/getSpiderInfo",method=RequestMethod.GET)
		public String getSpiderInfo(@RequestParam("taskId")String taskId,Model model){
			model.addAttribute("spider", SpiderMysql.getTaskById(taskId));
			return "spider/SpiderTaskDetail";
		}
}
