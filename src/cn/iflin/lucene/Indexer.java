package cn.iflin.lucene;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/*
 * ��������
 */
public class Indexer {
	private IndexWriter writer;
	public Indexer(String indexDir) throws IOException {
		Directory dir = FSDirectory.open(new File(indexDir));
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
	    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
	    writer = new IndexWriter(dir, config); //����Lucene Index
	}
	
	public void close() throws IOException{
		writer.close();
	}
	
	public int index(String dataDir,FileFilter filter) throws Exception {
		File [] files = new File(dataDir).listFiles();
		for(File f: files){
			if(!f.isDirectory() &&
			!f.isHidden() &&
			f.exists() &&
			f.canRead()&&
			(filter == null || filter.accept(f))){
				indexFile(f);
			}
		}
		return writer.numDocs();
	}
	private void indexFile(File f) throws Exception {
		System.out.println("Indexing"+f.getCanonicalPath());
		Document doc = getDocument(f);
		writer.addDocument(doc);
		
	}

	private Document getDocument(File f) throws Exception {
		Document doc = new Document();

		return doc;
	}
	
	private static class TextFilesFilter implements FileFilter{
		public boolean accept(File path){
			return path.getName().toLowerCase().endsWith(".txt");
		}
	}
	
	public static void main(String[] args) throws Exception {
		String indexDir = "D://test"; //��ָ��Ŀ¼�½���һ��ʵ������
		String dataDir = "D://test" ; //���ƶ�Ŀ¼�µ�.txt�ĵ���������
		long start = System.currentTimeMillis();
		Indexer indexer = new Indexer(indexDir);
		int numIndexed;
		try{
			numIndexed = indexer.index(dataDir,new TextFilesFilter());
		}finally{
			indexer.close();
		}
		long end = System.currentTimeMillis();
		System.out.println("������"+numIndexed+"�ļ��й�����"+(end-start)+"����");
	}

}
