package thoughts;

import java.util.logging.Logger;

public class Test {

public static void main(String[] args) {
		
	/*
	 * �ж�GC�Գ����صĻ���		
	 */
		Logger log = Logger.getAnonymousLogger();
		String s1 = "abc";
		log.info(((Object)s1).toString());
		s1 = null;
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s2 = "abc";
		log.info(((Object)s2).toString());
		
	}
	
}
