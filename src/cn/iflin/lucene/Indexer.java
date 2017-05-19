package cn.iflin.lucene;

import java.io.File;
import java.io.FileFilter;
<<<<<<< HEAD
=======
import java.io.FileReader;
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
<<<<<<< HEAD
=======
import org.apache.lucene.document.Field;
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/*
 * 建立索引
 */
public class Indexer {
	private IndexWriter writer;

	@SuppressWarnings("deprecation")
	public Indexer(String indexDir) throws IOException {
		Directory dir = FSDirectory.open(new File(indexDir));
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
	    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
	    writer = new IndexWriter(dir, config); //创建Lucene Index
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
<<<<<<< HEAD

=======
		doc.add(new Field("文档",new FileReader(f)));
		doc.add(new Field("文件名",f.getName(),Field.Store.YES,Field.Index.NOT_ANALYZED));
		doc.add(new Field("路径:",f.getCanonicalPath(),Field.Store.YES,Field.Index.NOT_ANALYZED));
>>>>>>> b0b06a132e58e9db6e7da7f4e24a56b7a47b3128
		return doc;
	}
	
	private static class TextFilesFilter implements FileFilter{
		public boolean accept(File path){
			return path.getName().toLowerCase().endsWith(".txt");
		}
	}
	
	public static void main(String[] args) throws Exception {
		String indexDir = "D://test"; //在指定目录下建立一个实例索引
		String dataDir = "D://test" ; //对制定目录下的.txt文档进行索引
		long start = System.currentTimeMillis();
		Indexer indexer = new Indexer(indexDir);
		int numIndexed;
		try{
			numIndexed = indexer.index(dataDir,new TextFilesFilter());
		}finally{
			indexer.close();
		}
		long end = System.currentTimeMillis();
		System.out.println("搜索到"+numIndexed+"文件夹共花费"+(end-start)+"毫秒");
	}

}
