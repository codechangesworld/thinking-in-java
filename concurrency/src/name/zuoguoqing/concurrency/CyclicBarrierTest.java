/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency
 * @file CyclicBarrierTest.java
 * @author zuoguoqing
 * @date 2017年3月24日
 * @version 
 */
package name.zuoguoqing.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zuoguoqing
 *
 */
public class CyclicBarrierTest {
	private int N;
	private CyclicBarrier barrier;

	public CyclicBarrierTest() {
		this.N = 3;
		this.barrier = new CyclicBarrier(N, new Runnable() {

			@Override
			public void run() {
				System.out.println("all threads of barrier.await() are here"
						+ " and the last one will do some work before release" 
						+ " all the threads.");
			}
		});
	}

	public CyclicBarrierTest(int n) {
		this.N = n;
		this.barrier = new CyclicBarrier(N, new Runnable() {

			@Override
			public void run() {
				System.out.println("all threads of barrier.await() are here"
						+ " and the last one will do some work before release" + " all the threads.");
			}
		});
	}

	public void testCyclicBarrier() {
		for (int i = 0; i < N; i++) {
//			new Thread(new ShowMsg(barrier)).start();
			new Thread(new ShowMsg()).start();
		}
	}

	class ShowMsg implements Runnable {
//		CyclicBarrier barrier;
//
//		public ShowMsg(CyclicBarrier barrier) {
//			this.barrier = barrier;
//		}

		@Override
		public void run() {
			System.out.println("do some work...");
			System.out.println("barrier.await() here...");
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				System.out.println("some exception happens here...");
			}
			System.out.println("thread awake here...");
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CyclicBarrierTest(10).testCyclicBarrier();
	}

}
