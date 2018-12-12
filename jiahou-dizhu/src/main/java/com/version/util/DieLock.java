
package com.version.util;

/**
 * @author 作者:cb
 * @version 创建时间：2018年11月21日 上午9:22:10
 * 
 */
public class DieLock implements Runnable {
	public static int count = 0;
	public static Object obj = new Object();
	public static boolean flag = true;

	@Override
	public void run() {
		if (flag) {
			while (true) {
				flag = false;
				synchronized (obj) {
					try {
						Thread.sleep(1000);
						sale();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

			}
		} else {
			while (true) {
				sale();
			}
		}
	}

	private synchronized void sale() {
		synchronized (obj) {
			System.out.println("AAAAA");

		}

	}

	public static void main(String[] args) {
		DieLock dieLock = new DieLock();
		new Thread(dieLock).start();
		new Thread(dieLock).start();
	}
}
