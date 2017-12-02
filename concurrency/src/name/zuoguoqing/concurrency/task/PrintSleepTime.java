/**
 * Copyright © zuoguoqing
 *
 * Description: 
 *
 * @package name.zuoguoqing.concurrency.task
 * @file PrintSleepTime.java
 * @author zuoguoqing
 * @date 2017年3月20日
 * @version 1.0
 */
package name.zuoguoqing.concurrency.task;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zuoguoqing
 *
 */
public class PrintSleepTime implements Runnable {
	private static int countTask = 0;
	private final int id = countTask++;
	private long timeout;

	public PrintSleepTime() {
		Random random = new Random();
		timeout = random.nextInt(10) + 1;
	}
	public PrintSleepTime(long timeout) {
		this.timeout = timeout;
	}
	
	public void showSleepTime() {
		System.out.println("Task#" + id + " sleep for " + timeout + " seconds.");
	}
	
	@Override
	public void run() {
		try {
			showSleepTime();
			TimeUnit.SECONDS.sleep(timeout);
			System.out.println("Task#" + id + "finished!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
