/**
 * 
 */
package com.farry.concurrent.demo_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xiafaqi
 *
 */
public class SynchronizedList {

	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList<>();
		List<String> synchronizedList = Collections.synchronizedList(arrayList);// 此时的集合是同步的
	}
}
