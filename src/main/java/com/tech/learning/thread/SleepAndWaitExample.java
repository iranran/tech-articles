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
					System.out.println("SleepThread begin " + new Date());
					Thread.sleep(10000);
					System.out.println("SleepThread end " + new Date());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private final class WaitThread extends Thread{
		public void run(){
			synchronized(DUMMY){
				try {
					System.out.println("WaitThread begin " + new Date());
					DUMMY.wait();
					System.out.println("WaitThread end " + new Date());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private final class NotifyThread extends Thread{
		public void run(){
			synchronized(DUMMY){
				System.out.println("NotifyThread begin " + new Date());
				DUMMY.notify();
				//DUMMY.notifyAll();
				System.out.println("NotifyThread end " + new Date());
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		SleepAndWaitExample saw = new SleepAndWaitExample();
		
		saw.new WaitThread().start();
		Thread.sleep(100);
		saw.new SleepThread().start();
		Thread.sleep(100);
		saw.new NotifyThread().start();

	}

}
