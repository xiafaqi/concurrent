/**
 * 
 */
package com.farry.concurrent.demo_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 缓存线程池
 * 有任务就new一个线程，线程可以复用
 * @author xiafaqi
 * 
 */
public class MyCachedPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println(service);
		
		for (int i = 0; i < 2; i++) {
			service.execute(()->{
				try {
					TimeUnit.MICROSECONDS.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		
		System.out.println(service);
		
		TimeUnit.SECONDS.sleep(10);
		
		System.out.println(service);
		
		service.shutdown();
	}
}
