/** @文件名: TestIndexUtil.java @创建人：邢健  @创建日期： 2015年5月25日 下午9:56:05 */
package com.promisepb.study2;

import org.junit.Test;

     /**  
 * @类名: TestIndexUtil.java
 * @包名: com.promisepb.study2
 * @描述: TODO
 * @作者: xingjian xingjian@yeah.net  
 * @日期:2015年5月25日 下午9:56:05
 * @版本: V1.0  
 */
public class TestIndexUtil {
	
	@Test
	public void testIndex(){
		IndexUtil indexUtil = new IndexUtil();
		indexUtil.index();
	}
	
	@Test
	public void testQuery(){
		IndexUtil indexUtil = new IndexUtil();
		indexUtil.query();
	}
	
	@Test
	public void testDeleteIndex(){
		IndexUtil indexUtil = new IndexUtil();
		indexUtil.deleteIndex();
	}
	
	@Test
	public void testUnDeleteIndex(){
		IndexUtil indexUtil = new IndexUtil();
		indexUtil.unDeleteIndex();
	}
}
