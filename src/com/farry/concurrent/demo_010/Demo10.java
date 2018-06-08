/**
 * 
 */
package com.farry.concurrent.demo_010;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另一个同步方法
 * 一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的
 * @author xiafaqi
 *
 */
public class Demo10 {

	synchronized void test () {
		System.out.println("test start......");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("test end......");
	}
	
	public static void main(String[] args) {
		new Demo10().test();
	}
}

class Demo100 extends Demo10 {
	@Override
	synchronized void test() {
		System.out.println("child test start......");
		super.test();
		System.out.println("child test end......");
	}
}
