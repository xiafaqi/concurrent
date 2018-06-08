/**
 * 
 */
package com.farry.concurrent.demo_026.executorservice1;

/**
 * @author xiafaqi
 *
 */
public class TestRunnable implements Runnable{

	private String name;
	
	public TestRunnable(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		while(true) {
			if (Main.Surplus < 0) {
				return;
			}
			Main.Surplus--;
			System.out.println(name + " " + Main.Surplus);
		}
	}
	
	public static void main(String[] args) {
		TestRunnable runnable = new TestRunnable("runnable");
		TestRunnable runnable2 = new TestRunnable("runnable2");
		
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable2);
		
		t1.start();
		t2.start();
	}

}
