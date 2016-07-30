/** @文件名: HelloLucene.java @创建人：邢健  @创建日期： 2015年5月24日 下午8:21:46 */
package com.promisepb.study1;

import java.io.File;
import java.io.FileReader;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


     /**  
 * @类名: HelloLucene.java
 * @包名: com.promisepb.study1
 * @描述: TODO
 * @作者: xingjian xingjian@yeah.net  
 * @日期:2015年5月24日 下午8:21:46
 * @版本: V1.0  
 */
public class HelloLucene {

	/**
	 * 创建索引
	 * @throws Exception 
	 */
	public void index() throws Exception{
		//1.创建Directory
		//Directory directory = new RAMDirectory();//索引创建到内存中
		Directory directory = FSDirectory.open(new File("F:\\gitworkspace\\lucenestudy\\lucenestudy\\src\\main\\resources\\indexs").toPath());
		//2.创建IndexWriter
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		//3.创建Document
		Document document  = null;
		File file = new File("F:\\gitworkspace\\lucenestudy\\lucenestudy\\src\\main\\resources\\files");
		for(File f : file.listFiles()){
			document  = new Document();
			//4.为Document创建Field
			document.add(new TextField("content",new FileReader(f)));
			document.add(new StringField("fileName",f.getName(),Field.Store.YES));
			document.add(new StringField("path",f.getAbsolutePath(),Field.Store.YES));
			//5.通过IndexWriter添加文档到索引中
			indexWriter.addDocument(document);
		}
		//6. 关闭indexWriter
		indexWriter.close();
	}
	
	/**
	 * 搜索
	 */
	public void searcher(String searchStr) throws Exception{
		//1.创建Directory
		Directory directory = FSDirectory.open(new File("F:\\gitworkspace\\lucenestudy\\lucenestudy\\src\\main\\resources\\indexs").toPath());
		//2.创建IndexReader
		IndexReader indexReader = DirectoryReader.open(directory);
		//3.根据IndexReader创建IndexSearcher
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		//4.创建搜索的query
		QueryParser queryParser = new QueryParser("content", new StandardAnalyzer());
		//创建query搜索域为"content"中包含searchStr的文档
		Query query = queryParser.parse(searchStr);
		//5.根据搜索searcher并且返回TopDocs
		TopDocs topDocs = indexSearcher.search(query, 10);
		//6.根据TopDoc获取StoreDoc
		ScoreDoc[] sds = topDocs.scoreDocs;
		//7.根据search和StoreDoc获取Document
		for(ScoreDoc sd : sds){
			Document doc = indexSearcher.doc(sd.doc);
			//8.根据Document获取相应的值
			System.out.println(doc.get("fileName")+"["+doc.get("path")+"]");
		}
		indexReader.close();
	}
}
