package com.tech.learning.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ConcurrentModificationExceptionTest {

	@Test
	public void test1(){
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 100000; i++) {
			list.add(i,i);
		}
		
		
		for(Integer i : list){//创建迭代器
			if(i == 3){//修改list中的数据，会产生ConcurrentModificationException异常
				list.remove(i);
			}
		}
	}
	
	@Test
	public void test2(){
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 100000; i++) {
			list.add(i,i);
		}
		
		Iterator<Integer> iter = list.iterator();
		
		while(iter.hasNext()){
			Integer i = iter.next();
			if(i == 3){
				iter.remove();
			}
		}
		Assert.assertFalse(list.contains(3));
	}
}
