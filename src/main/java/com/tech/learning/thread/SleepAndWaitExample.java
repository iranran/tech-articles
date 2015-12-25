package com.tech.learning.thread;

import java.util.Date;

/**
 * wait notify必须与synchronized结合使用 否则报告java.lang.IllegalMonitorStateException异常
 * @author lenovo
 *
 */
public class SleepAndWaitExample {

	private final Object DUMMY = new Object();
	
	private final class SleepThread extends Thread{
		public void run(){
			synchronized(DUMMY){
				try {
					System.out.println(Thread.currentThread().getName()+ " SleepThread begin " + new Date());
					Thread.sleep(10000);
					System.out.println(Thread.currentThread().getName()+ " SleepThread end " + new Date());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private final class WaitThread extends Thread{
		private final int timeout;
		
		public WaitThread(int timeout){
			this.timeout = timeout;
		}
		
		public void run(){
			synchronized(DUMMY){
				try {
					System.out.println(Thread.currentThread().getName()+ " WaitThread begin " + new Date());
					DUMMY.wait(timeout);
					System.out.println(Thread.currentThread().getName()+ " WaitThread end " + new Date());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private final class NotifyThread extends Thread{
		public void run(){
			System.out.println(Thread.currentThread().getName()+ " NotifyThread out synchronized " + new Date());
			synchronized(DUMMY){
				System.out.println(Thread.currentThread().getName()+ " NotifyThread begin " + new Date());
				long t0 = System.currentTimeMillis();
				while(System.currentTimeMillis() - t0 < 5000){//停顿5s
					
				}
				DUMMY.notify();
				DUMMY.notifyAll();
				System.out.println(Thread.currentThread().getName()+ " NotifyThread end " + new Date());
			}
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		SleepAndWaitExample saw = new SleepAndWaitExample();
		
		saw.new WaitThread(0).start();
		Thread.sleep(100);
		saw.new SleepThread().start();//此时sleep占有锁，notify无法进入同步块，直到sleep结束，释放锁
		Thread.sleep(100);
		saw.new NotifyThread().start();
		
		//测试wait(timeout),如果timeout时间到了，则进行后续操作
//		saw.new WaitThread(1000).start();
		
		
		//测试wait(timeout),如果timeout时间到了，则进行后续操作
		//但是前提是WaitThread线程可以获得锁，而此时锁被NotifyThread占用，还是要等NotifyThread结束后才能进行后续操作
//		saw.new WaitThread(1000).start();
//		Thread.sleep(100);
//		saw.new NotifyThread().start();
		
		

	}

}
