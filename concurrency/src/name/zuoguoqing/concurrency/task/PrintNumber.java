/**
 * Copyright © zuoguoqing
 *
 * Description: A task class file which to print some numbers.
 *
 * @package name.zuoguoqing.concurrency.task
 * @file PrintNumber.java
 * @author zuoguoqing
 * @time 2017年3月20日
 * @version 1.0
 */
package name.zuoguoqing.concurrency.task;

/**
 * @author zuoguoqing
 *
 */
public class PrintNumber implements Runnable {
	private static int countTask = 0;
	private final int id = countTask++;
	protected int countDown = 5;
	
	public PrintNumber() {
		
	}
	public PrintNumber(int countDown) {
		this.countDown = countDown;
	}
	
	public void print() {
		System.out.println("#" + id + "(" + countDown + ")");
	}
	
	@Override
	public void run() {
		while (countDown-- > 0) {
			print();
		}
	}
}
