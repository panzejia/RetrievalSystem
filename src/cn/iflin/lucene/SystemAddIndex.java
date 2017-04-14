package cn.iflin.lucene;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.iflin.mysql.MysqlConnection;
/*
 * �˺������������ݿ��е����±���Ƚ����������洢���ļ�����
 */
public class SystemAddIndex {
		private  void addIndex() throws IOException {
			Connection conn = MysqlConnection.getConnection();
			// 1.��������
			// ʵ�����������������ݿ⣬ͬʱ��sql���������ݿ���ָ�������ݱ�
			String sql = "SELECT * FROM context";
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			
			/*�ڴ淽����
			StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
			Directory index = new RAMDirectory();
			IndexWriter w = new IndexWriter(index, config);
			*/
			
			Analyzer analyzerIKA = new IKAnalyzer();
			IndexWriterConfig configfile = new IndexWriterConfig(Version.LUCENE_40, analyzerIKA);
			Directory fileindex;
			fileindex = FSDirectory.open(new File("C:\\LuceneIndex"));
			IndexWriter filew=new IndexWriter(fileindex, configfile);
			System.out.println(fileindex);
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					addDoc(filew, rs.getString(1) + "", rs.getString(2) + "", rs.getString(3) + "", rs.getString(4) + "");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// ͳһ�ͷ��ڴ�
				try {				
					filew.close();
					rs.close();
					pstmt.close();
					if (!conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		private static void addDoc(IndexWriter w, String title, String time, String context,String url) throws IOException {
			Document doc = new Document();
			doc.add(new TextField("Title", title, Field.Store.YES));
			doc.add(new TextField("Time", time, Field.Store.YES));
			doc.add(new TextField("Context", context, Field.Store.YES));
			doc.add(new TextField("Url", url, Field.Store.YES));
			w.addDocument(doc);
		}
		
		public static void main(String[] args) throws IOException {
			SystemAddIndex index = new SystemAddIndex();
			index.addIndex();
			System.out.println("ok");
		}
}
