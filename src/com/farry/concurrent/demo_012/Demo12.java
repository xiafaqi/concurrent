/**
 * 
 */
package com.farry.concurrent.demo_012;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字，是一个变量在多个线程中可见
 * @author xiafaqi
 *
 */
public class Demo12 {

	volatile boolean running = true;
	
	public void test() {
		System.out.println("test start......");
		while (running) {
			/*
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
		}
		
		System.out.println("test end......");
	}
	
	public static void main(String[] args) {
		Demo12 demo12 = new Demo12();
		
		new Thread(demo12 :: test, "t1").start(); // JDK8新特性写法
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		demo12.running = false;
	}
}
