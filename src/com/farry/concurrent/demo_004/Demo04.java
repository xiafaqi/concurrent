/**
 * 
 */
package com.farry.concurrent.demo_004;

/**
 * synchronized 关键字
 * 对某个对象加锁
 * @author xiafaqi
 *
 */
public class Demo04 {

	private static int count = 10;
	
	public synchronized static void test1() { // 这里等同于synchronized(Demo04.class)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void test2() { // 这里写成synchronized(this) 是否可以？
		synchronized (Demo04.class) {
			count--;
		}
	}
}
