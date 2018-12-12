
package com.version.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者:cb
 * @version 创建时间：2018年11月20日 上午10:00:10
 * 
 */
public class TestLambda {
	public static void runThreadUseLambda() {
		// Runnable是一个函数接口，只包含了有个无参数的，返回void的run方法；
		// 所以lambda表达式左边没有参数，右边也没有return，只是单纯的打印一句话
		new Thread(() -> System.out.println("lambda实现的线程")).start();
	}

	public static void runThreadUseInnerClass() {
		// 这种方式就不多讲了，以前旧版本比较常见的做法
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("内部类实现的线程");
			}
		}).start();
	} 

	public static void main(String[] args) {
		TestLambda.runThreadUseLambda();
		TestLambda.runThreadUseInnerClass();

		try {
			List list = new ArrayList<>();
			list.get(100);
		} catch (Exception e) {
			
			try {
				int a = 1 / 0;
			} catch (Exception e2) {
				System.out.println(e2);
			}
			System.out.println(e);
		}
		System.out.println("aa");
	}
}
