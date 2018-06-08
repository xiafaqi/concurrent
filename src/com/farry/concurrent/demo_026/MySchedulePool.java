/**
 * 
 */
package com.farry.concurrent.demo_026;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务的线程池
 * @author xiafaqi
 *
 */
public class MySchedulePool {

	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(8);
		
		service.scheduleAtFixedRate(()->{
			try {
				TimeUnit.MICROSECONDS.sleep(new Random().nextInt(1000));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}, 0, 500, TimeUnit.MICROSECONDS);//间隔执行
	}
}
