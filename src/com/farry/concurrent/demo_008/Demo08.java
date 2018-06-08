package com.farry.concurrent.demo_008;

import java.util.concurrent.TimeUnit;

/**
 * 对业务的写方法加锁
 * 对业务的读方法不加锁
 * 容易产生脏读问题
 * @author xiafaqi
 *
 */
public class Demo08 {

	String name;
	double balance;
	
	/**
	 * 写方法
	 * @param name
	 * @param balance
	 */
	public synchronized void set(String name, double balance) {
		this.name = name;
		try {
			Thread.sleep(2 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.balance = balance;
	}
	
	/**
	 * 读方法
	 * @param name
	 * @return
	 */
	public synchronized double getBalance(String name) {
		return this.balance;
	}
	
	public static void main(String[] args) {
		Demo08 demo08 = new Demo08();
		
		new Thread(() -> demo08.set("zhangsan", 100.0)).start(); // JDK1.8特性写法
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(demo08.getBalance("zhangsan"));
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(demo08.getBalance("zhangsan"));
	}
}
