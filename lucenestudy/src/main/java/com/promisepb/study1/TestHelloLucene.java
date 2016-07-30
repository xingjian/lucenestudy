package com.promisepb.study1;

import org.junit.Test;

public class TestHelloLucene {

	@Test
	public void testIndex(){
		HelloLucene helloLucene = new HelloLucene();
		try {
			helloLucene.index();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearcher(){
		HelloLucene helloLucene = new HelloLucene();
		try {
			helloLucene.searcher("ActiveMQ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
