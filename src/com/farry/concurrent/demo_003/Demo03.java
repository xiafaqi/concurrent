/**
 * 
 */
package com.farry.concurrent.demo_003;

/**
 * synchronized 关键字
 * 对某个对象加锁
 * @author xiafaqi
 *
 */
public class Demo03 {

	private int count = 10;
	
	// 等同于synchronized(this),锁定的是Demo03对象的实例
	public synchronized void test() {
		count--;
		System.out.println(Thread.currentThread().getName() + "count = " + count);
	}
}
