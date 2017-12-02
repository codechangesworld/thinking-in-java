/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency
 * @file CalculateMatrixTester.java
 * @author zuoguoqing
 * @date 2017年3月23日
 * @version 
 */
package name.zuoguoqing.concurrency;

import java.util.Random;

import name.zuoguoqing.concurrency.task.CalculateMatrix;

/**
 * @author zuoguoqing
 *
 */
public class CalculateMatrixTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random random = new Random(13);
		int row = 10;
		int colum = 10;
		int[][] matrix = new int[row][colum];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < colum; j++) {
				matrix[i][j] = random.nextInt(10);
			}
		}
		
		CalculateMatrix calculator = new CalculateMatrix(matrix);
		int sum = calculator.calculate();
		System.out.println("#1: " + sum);
	}

}
