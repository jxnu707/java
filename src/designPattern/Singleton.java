package designPattern;


/**
 * ����˳�򣺾�̬��ʼ��/��̬��Ա������ͬ�����Ⱥ�˳��   ��̬�ڲ���ֻ��������ʱ�ż���   
 * �ο����ӣ�http://www.tuicool.com/articles/aU3Qzi
 * @author Lin-Xi
 *
 */
public class Singleton {
 
/*    public static class Inner{
        static {
            System.out.println("TestInner Static!");
        }
        public final static Singleton testInstance = new Singleton(3);
    }
 
    public static Singleton getInstance(){
        return Inner.testInstance;
    }
 
    public Singleton(int i ) {
        System.out.println("Test " + i +" Construct! ");
    }
    
 
    public static Singleton testOut = new Singleton(1);
    //�ྲ̬��
    static {
        System.out.println("Test Static");
    }
 
    //�ྲ̬����
 
    public static void main(String args[]){
        Singleton t = new Singleton(2);
        Singleton.getInstance();
    }*/
	
	/**
     * ��ʽ����double-check�� ���Ⲣ��ʱ�����˶��ʵ��, �÷�ʽ������ȫ���Ⲣ���������ƻ�.
     * @author Lin-Xi
     * @return
     * 
     */
	
    /*public static Singleton getInstance() {
        if (mInstance == null) {
            synchronized (Singleton.class) {
                if (mInstance == null) {
                    mInstance = new Singleton();
                }
            }
        }
        return mInstance;
    }*/
    
	
    /**
     * 1. ��̬����ȷ��ʵ��ֻ��һ��
     * 2. ��̬������Ϊ���ʵ�������
     * 3. ˽�л����췽�����������ĵ���
     * 4. �̰߳�ȫ��.��Ϊ��̬��ֻ�����һ�Σ��ɹ�������̲߳�������ɵ��ظ�������ʵ��������.
     * @author Lin-Xi
     *
     */
    private static class SingletonHolder{
    	private static Singleton instance = new Singleton();
    }
 
    public static Singleton getInstance(){
    	return SingletonHolder.instance;
    }
}