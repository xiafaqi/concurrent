/**
 * 
 */
package com.farry.concurrent.demo_026;

import java.util.concurrent.Executor;

/**
 * 认识 executor
 * @author xiafaqi
 *
 */
public class MyExecutor implements Executor{

	public static void main(String[] args) {
		new MyExecutor().execute(() -> System.out.println("hello executor"));
	}

	@Override
	public void execute(Runnable command) {
		command.run();
	}
}
