import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class MyJavaTest {

	private static BigInteger sum;
	private static String a;
	private static String n;
	private static StringBuilder sb;
	private static final BigInteger MOD_NUM;
	private static long startMills;
	
	private int num = -1;//findXnum()
	private int[] datas = {1,1,2,2,3,4,4,4,5,5,5,5,5,5,6,7,9,9,9,9};
	private int xSrc = -1;
	private int xDes = -1;
	private int flag = 0;
	private int lowfinish = 0;
	
	private int[][] datas2= {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
	static {
		startMills = System.currentTimeMillis();
		MOD_NUM = new BigInteger("1000000007");
	}
	public MyJavaTest(){
//		bigAdd();
		//面试题1
/*		findXNum(5, datas, 0, datas.length);
		getXNum(5,datas,0,datas.length);
		System.out.println("find "+num+" "+5+" in datas")*/;
		
		//面试题2
/*		if (findXIn2Dimensional(datas2, 13, 0, datas2[0].length - 1)){
			System.out.println("find "+13+" in datas2.");
		}else
			System.out.println("can't find "+20+" in datas2.");*/
		
		//面试题3
		/*String replaceStr = " dont    worry,be happy!";
		getReplaceString(replaceStr);
		System.out.println("out:"+replaceStr);*/
		
		//面试题4
		/*Node head = new Node(0);
		Node deleteNode = null ;
		MyLinkedList linkedList = new MyLinkedList(head);
		for(int i = 1;i<10;i++){
			Node node = new Node(i);
			linkedList.add(node);
			if (i == 9){
				deleteNode = node;
			}
		}
		System.out.println(linkedList);
		linkedList.delete(deleteNode);
		System.out.println(linkedList);*/
		
		//面试题5
		Node head = new Node(0);
		MyLinkedList linkedList = new MyLinkedList(head);
		for(int i = 1;i<10;i++){
			Node node = new Node(i);
			linkedList.add(node);
		}
		//利用堆栈方式
		rePrintLinkedList(linkedList);
		//利用递归方式
//		recursionLinkedList(head);
//		System.out.println(linkedList);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MyJavaTest a = new MyJavaTest();
			
	}
	/******************************************************************
	 * 面试题1 <br>
	 * 在一个有序一位数组中查找k出现的次数，eg:{1,1,2,2,3,5,5,5,6,8,9} k=5,返回 3。
	 * 
	 * tips:有序查找 想到二分查找
	 * 		找到第一个k和最后一个k的两个下标后，即得个数。
	 * 		分前后两段折半，递归寻找第一个k和最后一个k。
	 * 
	 * ******************************************************************/
	private void getXNum(int x, int[] data, int start,int end){
		int n = -1;
		int firstX = getFirstX(x, data, start, end - 1);
		int lastX = getLastX(x, data, start, end - 1);
		if (firstX == -1 && lastX == -1){
			n = 0;
		}else
			n = lastX - firstX + 1;
		System.out.println("find "+num+" "+5+" in datas");
	}
	
	
	private int getFirstX(int x, int[] data, int start, int end){
		if (start > end){
			return -1;
		}
		int midIndex = (start+end)/2;
		int midElm = data[midIndex];
		
		if (midElm > x){
			end = midIndex - 1;
		}
		else if (midElm < x){
			start = midIndex + 1;
		}
		else if (midElm == x){
			if (midIndex>0 && data[midIndex - 1]!= x || midIndex == 0){
				return midIndex;
			}else
				return getFirstX(x,data,start,midIndex - 1);
		}
		return getFirstX(x, data, start, end);
	}
	
	private int getLastX(int x, int[] data, int start, int end){
		if (start > end){
			return -1;
		}
		int midIndex = (start+end)/2;
		int midElm = data[midIndex];
		
		if (midElm == x){
			if (midIndex + 1 < data.length && data[midIndex + 1]!= x || midIndex == data.length - 1){
				return midIndex;
			}else
				start = midIndex + 1;
		}
		else if (midElm > x){
			end = midIndex - 1;
		}
		else if (midElm < x){
			start = midIndex + 1;
		}
		return getLastX(x, data, start, end);
	}
	
	/**
	 * @param x
	 * @param data
	 * @param src
	 * @param des
	 * 尝试一个方法解决前后两段的递归二分查找失败
	 * 原因：找完前一半后，递归到最上层的src 和 des的值无法正确恢复到后半段查找的下标
	 */
	private void findXNum(int x, int[] data, int src, int des){
		
		int size = des - src;
		int temp = data[size/2 + src];
		
		if (src == des){
			if (xSrc == -1) {
				xSrc = src;
				lowfinish = 1;
			}
			else xDes = des;
			return ;
		}
		
		if (xSrc != -1 && xDes != -1){
			num = xDes - xSrc;
			return;
		}
		
		if (temp>x){
			des = size/2 - 1 + src;
			findXNum(x, data, src, des);
		}
		else if (temp < x){
			src = size/2 + 1 + src;
			findXNum(x, data, src, des);
		}
		else if (temp == x){
			if (lowfinish == 0){
				if (data[size/2 - 1+ src] == x){
					des = size/2 - 1+ src;
					findXNum(x, data, src, des);
				}
				else {
					xSrc = size/2+ src;
					return;
				}
				lowfinish = 1;
			}
			
			if (data[size/2 + 1+ src] == x) {
				if (flag == 0){
					des = data.length - 1;
					flag = 1;
				}
				src = size/2 + 1+ src;
				findXNum(x, data, src, des);	
			}
			else{
				xDes = size/2+ src;
				return;
			}
		}
	}
	
	
/********************************************************************************************/
	
	
	private void bigAdd(){
		while(true){
			try {
				sb = new StringBuilder();
				sum = new BigInteger("0");
				readIn();
				for (int i = 1; i <= Integer.valueOf(n); i++) {
					StringBuilder ax = sb.append(a);
					add(ax.toString());
				}
				System.out.println((sum.mod(MOD_NUM).toString())); 
				System.out.println("cost time: "+(System.currentTimeMillis()-startMills)+"ms"); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void add(String a) {
		BigInteger bi = new BigInteger(a);
		sum = sum.add(bi);
	}

	private static void readIn() {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String in = br.readLine();
			String[] an = in.split(" ");
			if (Long.valueOf(an[0])>=1&&Long.valueOf(an[1])<=1000000){
				a = an[0];
				n = an[1];
			}
			System.out.println("a: " + a + "  n:  " + n);
//			isr.close();
//			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
/********************************************************************************************
 * 面试题2 <br>
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。<br>
 * 
 * 1   2   8   9   <br>
 * 2   4   9   12  <br>
 * 4   7   10  13  <br>
 * 6   8   11  15  <br>
 * 
 * tips：
 * 		“有序”———— 二分查找<br>
 *       一维数组的二分好说，但在二维中如何找到一个“中点”？<br>
 *       如果找到了这个中点，每次剔除的“一半”是什么？<br>
 *       束手无策时，我们从特殊位置入手 如这个二维数组的四个顶点<br>
 * 
 * ***********************************************************************/
	/**
	 * @param datas 查找数据集
	 * @param x 目标整数
	 * @param p 查找起点行下标
	 * @param q 查找起点列下标
	 * @return 找到 true 否则 false
	 */
	private boolean findXIn2Dimensional(int[][] datas, int x, int p, int q){
		int i = p;
		int j = q;
		int temp = -1;
		if (i < datas2.length && j < datas2[0].length ){//数组下标越界即没有找到
			temp = datas[i][j];
		}else{
			return false;
		}
		
		if (temp == x)
			return true;
		
		if (temp > x){//即可删除temp所在的一列
			j = j - 1;
		}
		
		if (temp < x){//即可删除temp所在的一行
			i = i + 1;
		}
		return findXIn2Dimensional(datas, x, i, j);
	}
	
	/********************************************************************************************
	 * 面试题3 <br>
	 * 在一个字符串中，把空格符替换成"%20",要求在原字符串上修改，算法高效。</br>
	 * 
	 * Tips： </br>
	 * 1. 常规想法是，从头到尾开始逐个字符扫描，碰到一个替换一个。但是,空格是一个字符，替换后要变成三个字符，这样每替换一次字符串总长度就要修改一次，并且要把当前空格后的所有字符后移。O(n^2)
	 * 2. 倒着来，从尾到头。逐个字符扫描，并从新字符串的尾部开始一个个复制过去。
	 *    
	 * 举一反三：在合并数组（字符串）时，为了避免重复移动同一个字符，就可以采取这种从尾到头的方式。
	 * 
	 * ***********************************************************************/
	
	/**
	 * @param srcStr： 源字符串
	 * @return ： 返回替换好后的新字符串
	 */
	private void getReplaceString(String srcStr){
		int mDLength = 2;//“%20” 与 “ ”的长度差
		int count = 0;//空格总数
		int index = -1;
		int newIndex = -1;
		
		if (srcStr.isEmpty()||srcStr==null){
			System.out.println("srcStr is null");
			return;
		}
		StringBuilder sb = new StringBuilder(srcStr);
		index = sb.length()-1;
		for(int i = 0;i<srcStr.length();i++){
			if (sb.charAt(i) == ' '){
				count++;
			}
		}
		sb.setLength(sb.length()+count*mDLength);
		newIndex = sb.length()-1;
		
		for(int i = index;i>=0;i--){
			if(i==newIndex)
				break;
			if (sb.charAt(i) == ' '){//碰到空格
				sb.setCharAt(newIndex--, '0');
				sb.setCharAt(newIndex--, '2');
				sb.setCharAt(newIndex--, '%');
			}else{//不是空格
				sb.setCharAt(newIndex, sb.charAt(i));
				newIndex--;
			}
		}
		srcStr = sb.toString();
		System.out.println("in："+srcStr);
	}
	
	
	/********************************************************************************************
	 * 面试题4 <br>
	 * 给定待删链表结点，实现链表的O（1）删除。</br>
	 * 
	 * Tips： </br>
	 * 1. 常规想法是，从头到尾一次遍历，找到待删结点的前一个结点preNode，吧preNode的指针指向待删结点的下一个结点，最后把待删结点位置为NUll.O(n).
	 * 2. 复制待删结点的下一结点内容到待删结点，然后preNode指向待删结点的下一个结点，O(1).
	 *    
	 * ***********************************************************************/
	
	
	/**
	 * 定义结点类型
	 * @author 21427754
	 *
	 */
	private class Node{
		protected int count;
		protected int value;
		protected Node nextNode;
		
		
		public Node(int value){
			this.value = value;
			this.count++;
			this.nextNode = null;
		}

		public Node(int value, Node nextNode){
			this.value = value;
			this.nextNode = nextNode;
			this.count ++;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			String str = "value: "+value+", ";
			return str;
		}
		
	}
	
	/**
	 * 定义单向链表类型
	 * @author 21427754
	 *
	 */
	private class MyLinkedList{
		
		/**
		 * 头结点
		 */
		private Node head;
		
		/**
		 * 尾结点
		 */
		private Node tail;
		
		/**
		 * 链表大小
		 */
		private int size;

		public MyLinkedList(Node node){
			this.setHead(node);
			this.setTail(node);
			size = 0;
		}
		
		/**
		 * 在指定尾部添加新结点
		 * @param node
		 */
		public void add(Node node){
			Node tailNode = getTail();
			tailNode.nextNode = node;
			node.nextNode = null;
			this.setTail(node);
		}
		
		/**
		 * 删除指定结点，O(1)算法
		 * @param node
		 */
		public void delete(Node node){
			Node preNode = getHead();
			Node nextNode;
			
			if(preNode == null){
				System.out.println("List is null.");   
				return;
			}
			//待删结点不在最后一个,O(1)
			if(node.nextNode != null){
				nextNode = node.nextNode;
				node.value = nextNode.value;
				node.nextNode = nextNode.nextNode;
				node = null;
			}else{
				//是头一个 也是最后一个 同时还恰好是待删结点
				if (preNode.nextNode == null && preNode == node){
					node = null;
					setHead(null);
				}
				else{
					//是最后一个，但不是头一个 O(n)
					while(preNode != null && preNode.nextNode != node){
						preNode = preNode.nextNode;
					}
					if (preNode == null){
						System.out.println("there is no such node");
					}else{
						preNode.nextNode = null;
						node = null;
					}
				}
			}
			
				
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			String str = "[";
			Node temp = getHead();
			while(temp != null){
				str = str + temp.toString();
				temp = temp.nextNode;
			}
			str = str + "]";
			return str;
		}

		public Node getHead() {
			return head;
		}

		private synchronized void setHead(Node head) {
			this.head = head;
		}

		public int getSize() {
			return size;
		}

		private synchronized void setSize(int size) {
			this.size = size;
		}

		public Node getTail() {
			return tail;
		}

		public synchronized void setTail(Node tail) {
			this.tail = tail;
		}
		
		
		
	}
/********************************************************************************************
	 * 面试题5 <br>
	 * 倒序输出一个链表。</br>
	 * 
	 * Tips： </br>
	 * 1. 要输出链表，只能遍历。而链表的特性是只能从头遍历，这点无法改变。
	 * 2. 既然从头开始，要倒序输出，便是“先遍历的后输出”跟栈的“先进后出”相符，于是想到利用堆栈来实现。
	 *    
	 * **************************************************************************************/
	
	/**
	 * 
	 * 利用上面的Node类，实现一个链表形式的堆栈
	 * @author 21427754
	 *
	 */
	private class MyStack{
		
		/**
		 * 栈顶结点
		 */
		private Node top;
		private int size;
		
		public synchronized Node pop(){
			if (top != null && size > 0){
				Node node = top;
				top = top.nextNode;
				size--;
				return node;
			}else
				return null;
		}
		
		public synchronized void push(Node node){
			if (node != null){
				node.nextNode = top;
				top = node;
				size++;
			}
		}
		
		public int getSize(){
			return size;
		}
	}
	
	/**
	 * 倒序输出链表的所有结点
	 * @param list 待处理的链表
	 */
	private void rePrintLinkedList(MyLinkedList list){
		MyStack mStack = new MyStack();
		Node node = list.getHead();
		while(node != null){
			//这段代码无效 会改变链表结构 因为temp = node是将temp指向node所在的同一块内存  是一块内存的两个别名引用而已
			/*Node temp = new Node(-1);
			temp = node;*/
			
			Node temp = new Node(node.value, node.nextNode);
			mStack.push(temp);
			node = node.nextNode;
		}
		
		while(mStack.getSize() > 0){
			Node _node = mStack.pop();
			System.out.println(_node);
		}
		
	}
	
	/**
	 * 递归倒序输出链表所有结点
	 * @param list 待处理的链表
	 */
	private void recursionLinkedList(Node node){
		if (node == null)
			return;
		recursionLinkedList(node.nextNode);
		System.out.println(node);
		//以下也行
		/*if (node != null){
			if (node.nextNode != null){
				recursionLinkedList(node.nextNode);
			}
			System.out.println(node);
		}*/
	}
	
}
