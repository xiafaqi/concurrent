/**
 * 
 */
package com.farry.concurrent.demo_002;

/**
 * synchronized关键字
 * 对某个对象加锁
 * @author xiafaqi
 *
 */
public class Demo02 {
	
	private int count = 10;
	
	public void test() {
		synchronized (this) { // 任何要执行下面代码，必须拿到Demo02对象实例的锁
			count--;
			System.out.println(Thread.currentThread().getName() + "count = " + count);
		}
	}

}
