/**
 * Copyright © zuoguoqing
 *
 * @description Each thread calculate one line of the matrix
 * @package name.zuoguoqing.concurrency.task
 * @file CalculateMatrix.java
 * @author zuoguoqing
 * @date 2017年3月23日
 * @version 
 */
package name.zuoguoqing.concurrency.task;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zuoguoqing
 *
 */
/**
 * @author zuoguoqing
 *
 */
public class CalculateMatrix {
	private int[][] matrix;
	private int[] rowResult;
	private int threadNum;
	private Integer result;
	private CyclicBarrier barrier;
	private volatile boolean isOK = false;

	/**
	 * constructor
	 */
	public CalculateMatrix(int[][] matrix) {
		this.matrix = matrix;
		this.threadNum = matrix.length;
		this.result = Integer.valueOf(0);
		this.rowResult = new int[threadNum];
		this.barrier = new CyclicBarrier(threadNum, new Runnable() {

			@Override
			public void run() {
				// the last thread process this procedure and notify finally
				mergeRow();
				isOK = true;
			}
		});
	}

	private void mergeRow() {
		int total = 0;
		for (int x : rowResult) {
			total += x;
		}
		result = total;
	}

	private void sum(int row) {
		int sum = 0;
		for (int x : matrix[row]) {
			sum += x;
		}

		rowResult[row] = sum;
	}

	public Integer calculate() {
		for (int i = 0; i < threadNum; i++) {
			new Thread(new Calculator(i)).start();;
		}

		// wait until all calculating done
		while (!isOK) {}

		return result;
	}

	/**
	 * inner class to run calculate threads
	 * 
	 * @author zuoguoqing
	 *
	 */
	class Calculator implements Runnable {
		int rowNum;

		public Calculator(int row) {
			rowNum = row;
		}

		@Override
		public void run() {
			sum(rowNum);
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				System.out.println("Something wrong happens " + "about thread[" + rowNum + "]");
			}
		}
	}

	public static void main(String[] args) {
		Random random = new Random(47);
		int row = 100;
		int colum = 100;
		int[][] matrix = new int[row][colum];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < colum; j++) {
				matrix[i][j] = random.nextInt(10);
			}
		}

		Integer result = new CalculateMatrix(matrix).calculate();
		System.out.println("Final result of matrix is: " + result);
	}

}
