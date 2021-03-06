/**
 * 
 */
package com.farry.concurrent.demo_026;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * 分-合线程池
 * @author xiafaqi
 *
 */
public class MyForkJoinPool {
	static int[] nums = new int[1000000];
	static final int MAX_NUM = 50000;
	static Random r = new Random();
	
	static {
		for (int i = 0; i < nums.length; i++) {
			nums[i] = r.nextInt(100);
		}
		
		System.out.println(Arrays.stream(nums).sum()); // stream api
	}

	/**
	 * 第一种方法 extends RecursiveAction 
	 * 没有返回值
	 * @author xiafaqi
	 *
	 */
	/**
	static class AddTask extends RecursiveAction {

		int start, end;
		
		public AddTask(int s, int e) {
			start = s;
			end = e;
		}
		
		@Override
		protected void compute() {
			if (end -start <= MAX_NUM) {
				long sum = 0l;
				for (int i = start; i < end; i++) {
					sum += nums[i];
				}
				System.out.println("from:" + start + " to:" + end + " = " + sum);
			} else {
				int middle = start + (end - start) / 2;
				
				AddTask subTask1 = new AddTask(start, middle);
				AddTask subTask2 = new AddTask(middle, end);
				subTask1.fork();
				subTask2.fork();
			}
		}
		
	}
	*/
	
	/**
	 * 第二种方式：extends RecursiveTask 
	 * 有返回值
	 * @author xiafaqi
	 *
	 */
	static class AddTask extends RecursiveTask<Long> {

		int start, end;
		
		public AddTask(int s, int e) {
			start = s;
			end = e;
		}
		
		@Override
		protected Long compute() {
			if (end - start <= MAX_NUM) {
				long sum = 0l;
				for (int i = start; i < end; i++) sum += nums[i];
				return sum;
			}
			
			int middle = start + (end - start) / 2;
			AddTask addTask1 = new AddTask(start, middle);
			AddTask addTask2 = new AddTask(middle, end);
			addTask1.fork();
			addTask2.fork();
			
			return addTask1.join() + addTask2.join();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		ForkJoinPool fjp = new ForkJoinPool();
		AddTask task = new AddTask(0, nums.length);
		fjp.execute(task);
		long result = task.join();
		System.out.println("result:" + result);
		
//		System.in.read();
	}
}
