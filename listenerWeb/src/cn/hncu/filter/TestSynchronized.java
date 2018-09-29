package cn.hncu.filter;


public class TestSynchronized {
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				TestSynchronized.A.slp();
			}
		}.start();;
		new Thread() {
			@Override
			public void run() {
				synchronized (TestSynchronized.A.class) {
					System.out.println("111111111");
				}
			}
		}.start();
	}
	static  class A{
		public static void slp() {
			synchronized (TestSynchronized.A.class) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
