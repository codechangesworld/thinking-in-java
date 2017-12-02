/**
 * Copyright © zuoguoqing
 *
 * @description 
 * @package name.zuoguoqing.concurrency
 * @file SleepingTask.java
 * @author zuoguoqing
 * @date 2017年3月20日
 * @version 1.0
 */
package name.zuoguoqing.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import name.zuoguoqing.concurrency.task.PrintSleepTime;

/**
 * @author zuoguoqing
 *
 */
public class SleepingTask {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			executorService.execute(new PrintSleepTime());
		}
		executorService.shutdown();
	}

}
