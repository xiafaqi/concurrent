/**
 * 
 */
package com.farry.concurrent.demo_026;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 工作窃取线程池
 * @author xiafaqi
 *
 */
public class MyWorkStealingPool {

	public static void main(String[] args) throws IOException {
		ExecutorService service = Executors.newWorkStealingPool();
		
		service.execute(new R(1000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		
		// 由于产生的是精灵线程(守护线程，后台线程)，主线程不阻塞，看不到输出
		System.in.read();
		
	}
	
	static class R implements Runnable{

		int time;
		
		public R(int t) {
			this.time = t;
		}
		
		@Override
		public void run() {
			try {
				TimeUnit.MICROSECONDS.sleep(time);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(time + " " + Thread.currentThread().getName());
		}
		
	}
}
