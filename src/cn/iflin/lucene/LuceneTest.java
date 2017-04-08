package cn.iflin.lucene;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
public class LuceneTest {
	public static void main(String[] args) throws IOException, ParseException {
	    StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
	 
	    Directory index = new RAMDirectory();
	 
	    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
	 
	    IndexWriter w = new IndexWriter(index, config);
	    File f=new File("D:\\test\\test.txt");
	    FileInputStream fip=new FileInputStream(f);
	    InputStreamReader r=new InputStreamReader(fip, "utf-8");
	    StringBuffer sb = new StringBuffer();		    
	    while(r.ready()){
	    	sb.append((char)r.read());
		}
	    addDoc(w, sb.toString());
	    r.close();
	    w.close();
	 
	    String querystr = args.length > 0 ? args[0] : "关于进一步完善省级财政科研项目资金管理等政策的实施意见(试行)";
	 
	    Query q = new QueryParser(Version.LUCENE_40, "title", analyzer).parse(querystr);
	 
	    int hitsPerPage = 10;
	    IndexReader reader = DirectoryReader.open(index);
	    IndexSearcher searcher = new IndexSearcher(reader);
	    TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
	    searcher.search(q, collector);
	    ScoreDoc[] hits = collector.topDocs().scoreDocs;
	     
	    System.out.println("Found " + hits.length + " hits.");
	    for(int i=0;i<hits.length;++i) {
	      int docId = hits[i].doc;
	      Document d = searcher.doc(docId);
	      System.out.println((i + 1) + ". " +d.get("title"));
	    }
	 
	    reader.close();
	  }
	 
	private static void addDoc(IndexWriter w, String string) throws IOException {
	    Document doc = new Document();
	    doc.add(new TextField("title", string,Field.Store.YES));
	    w.addDocument(doc);
	  }
}
