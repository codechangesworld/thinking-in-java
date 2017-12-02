/**
 * Copyright © zuoguoqing
 *
 * Description: 
 *
 * @package name.zuoguoqing.concurrency.task
 * @file PrintNumberWithSleep.java
 * @author zuoguoqing
 * @time 2017年3月20日
 * @version 
 */
package name.zuoguoqing.concurrency.task;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zuoguoqing
 *
 */
public class PrintNumberWithSleep extends PrintNumber {
	private long timeout;

	public PrintNumberWithSleep() {
		Random random = new Random();
		timeout = random.nextInt(10) + 1;
	}
	PrintNumberWithSleep(long timeout) {
		this.timeout = timeout;
	}
	
	@Override
	public void run() {
		try {
			while (countDown-- > 0) {
				print();
				TimeUnit.SECONDS.sleep(timeout);
				System.out.println("task sleep for " + timeout + " seconds.");
			} 
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

}
