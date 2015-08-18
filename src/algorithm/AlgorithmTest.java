package algorithm;

import org.junit.Test;

public class AlgorithmTest {

	@Test
	public void lcsTest(){
		LCS lcs = new LCS();
		String str1 = "123456abcd567";
		String str2 = "2345dddabc45678";
		int maxLen = lcs.getLCSLength2(str1, str2);
		System.out.println(maxLen);
	}
	
}
