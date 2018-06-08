/**
 * 
 */
package com.farry.concurrent.demo_026;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池的概念
 * @author xiafaqi
 *
 */
public class ParalleComputing {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 普通的方式获取
		System.out.println("cpu的核数为：" + Runtime.getRuntime().availableProcessors());
		long startTime = System.currentTimeMillis();
		List<Integer> results = getPrime(1, 2000000);
		System.out.println("质数的个数为：" + results.size());
		long endTime = System.currentTimeMillis();
		System.out.println("普通方式耗费的时间：" + (endTime - startTime));
		
		// 线程池的方式
		final int cpuCoreNums = 8;
		ExecutorService service = Executors.newFixedThreadPool(cpuCoreNums);
		
		MyTask myTask1 = new MyTask(1, 300000);
		MyTask myTask2 = new MyTask(300001, 600000);
		MyTask myTask3 = new MyTask(600001, 900000);
		MyTask myTask4 = new MyTask(900001, 1000000);
		MyTask myTask5 = new MyTask(1000001, 1300000);
		MyTask myTask6 = new MyTask(1300001, 1500000);
		MyTask myTask7 = new MyTask(1500001, 1800000);
		MyTask myTask8 = new MyTask(1800001, 2000000);
		
		Future<List<Integer>> f1 = service.submit(myTask1);
		Future<List<Integer>> f2 = service.submit(myTask2);
		Future<List<Integer>> f3 = service.submit(myTask3);
		Future<List<Integer>> f4 = service.submit(myTask4);
		Future<List<Integer>> f5 = service.submit(myTask5);
		Future<List<Integer>> f6 = service.submit(myTask6);
		Future<List<Integer>> f7 = service.submit(myTask7);
		Future<List<Integer>> f8 = service.submit(myTask8);
		
		startTime = System.currentTimeMillis();
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		f5.get();
		f6.get();
		f7.get();
		f8.get();
		endTime = System.currentTimeMillis();
		System.out.println("线程池耗费的时间：" + (endTime - startTime));
		
		service.shutdown();
	}
	
	/**
	 * 获取某范围内所欲质数的任务
	 * @author xiafaqi
	 *
	 */
	static class MyTask implements Callable<List<Integer>> {

		int startPos, endPos;
		
		public MyTask(int s, int e) {
			this.startPos = s;
			this.endPos = e;
		}
		
		@Override
		public List<Integer> call() throws Exception {
			List<Integer> r = getPrime(startPos, endPos);
			return r;
		}
		
	}

	/**
	 * 判断是否是质数的方法
	 * @param num
	 * @return
	 */
	static boolean isPrime(int num) {
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取某范围内的所有质数
	 * @param start
	 * @param end
	 * @return
	 */
	static List<Integer> getPrime(int start, int end) {
		List<Integer> results = new ArrayList<>();
		for (int i = start; i <= end; i++) {
			if (isPrime(i)) {
				results.add(i);
			}
		}
		return results;
	}
}
