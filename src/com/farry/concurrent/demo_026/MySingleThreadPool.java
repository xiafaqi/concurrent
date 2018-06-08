/**
 * 
 */
package com.farry.concurrent.demo_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单个线程的线程池
 * 按照一定顺序来执行任务
 * @author xiafaqi
 *
 */
public class MySingleThreadPool {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		for (int i = 0; i < 5; i++) {
			final int j = i;
			service.execute(()->{
				System.out.println(j + " " + Thread.currentThread().getName());
			});
		}
		
		service.shutdown();
	}
}
