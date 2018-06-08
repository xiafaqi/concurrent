/**
 * 
 */
package com.farry.concurrent.demo_020;

import java.util.concurrent.TimeUnit;

/**
 * reentrantlock替代synchronized
 * 本例中由于test1锁定this,只有test1执行完毕的时候，test2才能执行
 * 这里复习synchronized最原始的语义
 * @author xiafaqi
 *
 */
public class ReentrantLock1 {

	public synchronized void test1() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void test2() {
		System.out.println("execute test2......");
	}
	
	public static void main(String[] args) {
		ReentrantLock1 r1 = new ReentrantLock1();
		
		new Thread(r1::test1).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new Thread(r1::test2).start();
	}
}
