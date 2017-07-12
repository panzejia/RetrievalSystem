# Information Retrieval System 
-- Beijing Normal University, Zhuhai

#20170710 版本说明
1.新增用户登陆后访问个人主页时自动推荐文章
2.新增用户注册时自动爬取知网信息


--addinfo.py cnki爬虫主程序，用于用户注册后将自动运行对其在cnki中进行爬取。需放置C盘根目录下。
--Spider     文章爬虫，用于对已写好的网站模板进行爬虫，同时爬取过程中将对用户信息进行匹配，将分数大于0.025的文章推荐给用户
--RetrievalSystem 	web主程序。
--retrievalsystemdb.sql 数据库数据及结构