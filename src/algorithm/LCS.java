package algorithm;

/**
 * 最长公共字串(连续)问题
 * @author Lync
 * @since 08-11-2015
 */
public class LCS {
	
	/**
	 * 将两个字符串构建成一个二维数组，数组初始化为0.</br>
	 * 将两个字符串中的每个相同位置上的字符经行比较，相等就将在二维数组该位置上的元素赋值(如果是在第一行或第一列上为1，否则赋值为左上角元素加1).</br>
	 * 在一行的比较当中，每比较赋值完后与当前的子序列长度最大值比较，更大则对max赋值.最终返回Max就是最长字串长度.</br>
	 * </br>
	 * 算法分析:</br>
	 * 两重for循环依次比较两个String中所有字符，时间复杂度为O(n^2).</br>
	 * 二维数组存储两个字符串，空间复杂度为O(n^2).
	 * @param str1 
	 * @param str2
	 * @return 最长公共字串长度
	 */
	public int getLCSLength1(String str1,String str2){
		
		if ((str1 == null || str1.isEmpty())||(str2 == null || str2.isEmpty()))
			return -1;
		
		int max = 0;//最长公共字串长度
		int n = str1.length();
		int m = str2.length();
		int[][] array = new int[n][m];
		for (int i=0;i<n;i++){
			for (int j=0;j<m;j++){
				if (str1.charAt(i) == str2.charAt(j)){
					if (i>0&&j>0)//不为第0行或第0列
						array[i][j] = array[i-1][j-1]+1;
					else
						array[i][j] = 1;
				}
				if (array[i][j]>max){
					max = array[i][j];
				}
			}
		}
		return max;
	}
	
	/**
	 * 在getLCSLength1上，优化空间复杂度.</br>
	 * 每次比较完一行后，当前行上的元素值可由上一行得到(如果是在第一行或第一列上为1，否则赋值为左上角元素加1)，但当前行赋值完毕后，上一行就没作用了.</br>
	 * 考虑只用一维来存储，最后在这一维数组中找到的最大值即是解.</br>
	 * @param str1
	 * @param str2
	 * @return
	 */
	public int getLCSLength2(String str1,String str2){
		if ((str1 == null || str1.isEmpty())||(str2 == null || str2.isEmpty()))
			return -1;
		int max = 0;//最长公共字串长度
		int n = str1.length();
		int m = str2.length();
		int size = m>=n?m:n;
//		int[][] array = new int[n][m];
		int[] array = new int[size];//当前行的结果
		int[] array_last = new int[size];//上一行的结果
		for (int i=0;i<n;i++){
			array_last = array.clone();
			for (int j=0;j<m;j++){
				if (str1.charAt(i) == str2.charAt(j)){
					if (j==0||i==0){//为第0行或第0列
						array[j] = 1;
					}
					else{
						array[j] = array_last[j-1]+1;
					}
				}else{
					array[j] = 0;
				}
				if (array[j]>max){
					max = array[j];
				}
			}
		}
		return max;
	}
}
