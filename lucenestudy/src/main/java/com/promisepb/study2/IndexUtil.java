package com.promisepb.study2;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexUtil {

	private String[] ids = {"1","2","3","4","5","6","7"};
	private String[] emails={"aa@tongtusoft.net","gg@sina.net","bb@163.net","cc@yeah.net","dd@yeah.net","ee@yeah.net","ff@yeah.net"};
	private String[] contents = {"aaa111","bbb222","ccc333","ddd444","eee555","fff666","ggg777"};
	private int[] attachs = {2,3,1,4,5,5,6};
	private String[] names = {"zhangshan","mpeiyang","qiaodong","wangwu","dhcdd","dapao","feiji"};
	
	private Directory directory;
	
	public IndexUtil(){
		try {
			directory = FSDirectory.open(new File("F:\\gitworkspace\\lucenestudy\\lucenestudy\\src\\main\\resources\\indexs2").toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void index(){
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter indexWriter = null;
		try {
			indexWriter = new IndexWriter(directory, indexWriterConfig);
			Document doc = null;
			for(int i=0;i<ids.length;i++){
				doc = new Document();
				doc.add(new StringField("id",ids[i],Field.Store.YES));
				doc.add(new StringField("email",emails[i],Field.Store.YES));
				doc.add(new StringField("name",names[i],Field.Store.YES));
				doc.add(new TextField("content",contents[i],Field.Store.NO));
				indexWriter.addDocument(doc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null!=indexWriter){
				try {
					indexWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void query(){
		IndexReader indexReader = null;
		try {
			indexReader = DirectoryReader.open(directory);
			System.out.println("maxDoc : "+indexReader.maxDoc());
			System.out.println("numDocs : "+indexReader.numDocs());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null!=indexReader){
				try {
					indexReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void deleteIndex(){
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter indexWriter = null;
		try {
			indexWriter = new IndexWriter(directory, indexWriterConfig);
			//query 多查找   term精确查找
			indexWriter.deleteDocuments(new Term("id", "1"));
			indexWriter.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null!=indexWriter){
				try {
					indexWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void unDeleteIndex(){
		try {
			IndexReader indexReader = DirectoryReader.open(directory);
			System.out.println("deleteDoc : "+indexReader.numDeletedDocs());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
