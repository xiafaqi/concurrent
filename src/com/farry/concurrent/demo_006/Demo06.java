/**
 * 
 */
package com.farry.concurrent.demo_006;

/**
 * @author xiafaqi
 *
 */
public class Demo06 implements Runnable{
	
	private int count = 10;

	@Override
	public synchronized void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}

	public static void main(String[] args) {
		
		for (int i = 0; i < 5; i++) {
			Demo06 demo06 = new Demo06(); // 注意这里
			new Thread(demo06, "线程" + i).start();;
		}
	}
	
}
