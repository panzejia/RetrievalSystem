package cn.iflin.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;
/*
 * �˺����������м���
 */

public class SystemSearch {
	private ArrayList<Article> aticle;
//	public static void main(String[] args) {
//		SystemSearch ss = new SystemSearch();
//		System.out.println(ss.getSearch("������Ŀ"));
//		for (Article a : ss.getSearch("������Ŀ")){
//			System.out.println(a.getTitle()+"\n"+a.getTime()+"\n"+a.getContent());
//		}
//	}
	
	public  ArrayList<Article> getSearch(String querystr)  {
		// 2.��������
		// �������ж�����������Ȼ��������н�������󴴽�һ��Lucene�е�Query����
		ArrayList<Article> result = null;
		try {
			result = getResult(querystr);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	private  ArrayList<Article>  getResult(String querystr)throws IOException, ParseException{
		Query q = null;

		
		Analyzer analyzerIKA = new IKAnalyzer();
		Directory fileindex = FSDirectory.open(new File("C:\\LuceneIndex"));
		System.out.println(fileindex);
		q = new QueryParser(Version.LUCENE_40, "Title", analyzerIKA).parse(querystr);

		// 3.����
		// ����һ��Searcher����ʹ��֮ǰ������Query����������������ƥ�䵽��ǰ10�������װ��TopScoreDocCollector�����ﷵ�ء�
		int hitsPerPage = 100;
		IndexReader reader = DirectoryReader.open(fileindex);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
		searcher.search(q, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		System.out.println(hits.length);
		String result = null;
//		// 4.��ӡ���
		 aticle = new ArrayList<Article>();
		 
		String title = null,time = null,context = null,url=null;
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			url = d.get("Url");
			System.out.println(url);
			Article article = new Article(d.get("Title"),d.get("Time"),d.get("Context"));
			aticle.add(article);
		}
		// ��ʱ����Ҫ�ٷ����ĵ����ر�reader
		reader.close();
		return aticle;
	}
	
	
}
