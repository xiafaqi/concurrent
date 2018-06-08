/**
 * 
 */
package com.farry.concurrent.demo_025;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author xiafaqi
 *
 */
public class MyTransferQueue {

	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strings = new LinkedTransferQueue<>();
		
		/*
		new Thread(()->{
			try {
				System.out.println(strings.take());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		*/
		
//		strings.transfer("aaa");
		
		strings.put("aaa");
		
		new Thread(()->{
			try {
				System.out.println(strings.take());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}
