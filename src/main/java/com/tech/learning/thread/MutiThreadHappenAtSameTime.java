package com.tech.learning.thread;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 利用CountDownLatch模拟多线程同时发生
 * 
 * A synchronization aid that allows one or more threads to wait until a set of operations being performed in other threads completes. 

   A CountDownLatch is initialized with a given count. The await methods block until the current count reaches zero due to invocations of the countDown method,
 after which all waiting threads are released and any subsequent invocations of await return immediately. 
 
 This is a one-shot phenomenon -- the count cannot be reset. If you need a version that resets the count, consider using a CyclicBarrier. 

A CountDownLatch is a versatile synchronization tool and can be used for a number of purposes. A CountDownLatch initialized with a count of one serves as a simple on/off latch, or gate: all threads invoking await wait at the gate until it is opened by a thread invoking countDown. A CountDownLatch initialized to N can be used to make one thread wait until N threads have completed some action, or some action has been completed N times. 

A useful property of a CountDownLatch is that it doesn't require that threads calling countDown wait for the count to reach zero before proceeding, it simply prevents any thread from proceeding past an await until all threads could pass. 


 */
public class MutiThreadHappenAtSameTime {

	private static final int THREAD_COUNT = 20;
	
	public MutiThreadHappenAtSameTime(){
		
	}
	
	private final class TheThread extends Thread{
		CountDownLatch cdl;
		public TheThread(CountDownLatch cdl){
			this.cdl = cdl;
		}
		
		public void run(){
			try {
				//这可以进行一些准备操作，然后进行等待，直到所有线程的启动完成，然后所有线程一起发送请求
				this.cdl.await();
				System.err.println(new Date());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		MutiThreadHappenAtSameTime m = new MutiThreadHappenAtSameTime();
		CountDownLatch cdl = new CountDownLatch(THREAD_COUNT);
		System.out.println(new Date());
		for(int i=0; i<THREAD_COUNT; i++){
			m.new TheThread(cdl).start();
			Thread.sleep(1000);
			cdl.countDown();
		}
		
	}

}
