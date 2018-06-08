/**
 * 
 */
package com.farry.concurrent.demo_013;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 保证可见性，不保证原子性
 * volatile 不能替代 synchronized
 * @author xiafaqi
 *
 */
public class Demo13 {

	volatile int count = 0;
	
	public void test () {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}
	
	public static void main(String[] args) {
		Demo13 demo13 = new Demo13();
		
		List<Thread> threads = new ArrayList<Thread>();
		
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(demo13 :: test, "thread-" + i));
		}
		
		threads.forEach((o) -> o.start()); // jdk1.8新特性写法
		
		threads.forEach((o) -> {
			try {
				o.join(); // 等线程执行完毕之后才执行主线程main
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		System.out.println(demo13.count);
	}
}
