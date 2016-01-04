package com.tech.learning.basic;

import org.junit.Test;

/**
 * 简单的测试SystemArrayCopy 和 普通的数组复制间的差距，在本机跑在7倍以上的差距
 *
 */
public class SystemArrayCopyVsCommonArrayCopy {

	@Test
	public void test1(){
		int[] ints = new int[100000];
		for(int i=0; i<100000; i++){
			ints[i] = i;
		}
		
		long start1 = System.nanoTime();
		int[] ints2 = new int[150000];
		for(int i=0; i<100000; i++){
			ints2[i] = i;
		}
		long end1 = System.nanoTime();
		long diff1 = end1 - start1;

		System.out.println("Time taken by common array cooy = "+diff1);
	}
	
	@Test
	public void test2(){
		int[] ints = new int[100000];
		for(int i=0; i<100000; i++){
			ints[i] = i;
		}
		
		long start1 = System.nanoTime();
		int[] ints2 = new int[150000];
		System.arraycopy(ints, 0, ints2, 0, 100000);
		long end1 = System.nanoTime();
		long diff1 = end1 - start1;

		System.out.println("Time taken by System.arraycopy = "+diff1);
	}
}
