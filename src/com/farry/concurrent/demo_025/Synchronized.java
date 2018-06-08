/**
 * 
 */
package com.farry.concurrent.demo_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author xiafaqi
 *
 */
public class Synchronized {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> strings = new SynchronousQueue<>();
		
		new Thread(()->{
			try {
				System.out.println(strings.take());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		
		strings.put("aaaa"); // 阻塞等待消费者消费
//		strings.add("aaaa");
		System.out.println(strings.size());
	}
}
