/**
 * 
 */
package com.farry.concurrent.demo_024;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时又10个窗口对外售票
 * 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题
 * 重复销售？超量销售？
 * 
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 * @author xiafaqi
 *
 *
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 * 
 * 使用concurrentQueue提高并高并发性
 */
public class TicketSeller4 {

	private static ConcurrentLinkedQueue<String> tickets = new ConcurrentLinkedQueue<>();
	
	static {
		for (int i = 0; i < 10000; i++) {
			tickets.add("票编号：" + i);
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
					String s = tickets.poll();
					if (s == null) {
						break;
					}
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("销售了>>>>>" + s);
				}
			}).start();
		}
	}
}
