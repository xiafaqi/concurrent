/**
 * 
 */
package com.farry.concurrent.demo_014;

import java.util.ArrayList;
import java.util.List;

/**
 * 对比 Demo13的程序，可以用synchronized解决，synchronized可以保证可见性和原子性，volatile只能保证可见性
 * @author xiafaqi
 *
 */
public class Demo14 {

	int count = 0;
	
	public synchronized void test() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}
	
	public static void main(String[] args) {
		Demo14 demo14 = new Demo14();
		
		List<Thread> threads = new ArrayList<Thread>();
		
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(demo14 :: test, "thread-" + i));
		}
		
		threads.forEach((o) -> o.start());
		
		threads.forEach((o) -> {
			try {
				o.join(); // 等线程执行完毕之后才执行主线程main
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		System.out.println(demo14.count);
	}
}
