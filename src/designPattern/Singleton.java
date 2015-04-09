package designPattern;


/**
 * 加载顺序：静态初始块/静态成员变量（同级看先后顺序）   静态内部类只有在引用时才加载   
 * 参考链接：http://www.tuicool.com/articles/aU3Qzi
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
    //类静态块
    static {
        System.out.println("Test Static");
    }
 
    //类静态属性
 
    public static void main(String args[]){
        Singleton t = new Singleton(2);
        Singleton.getInstance();
    }*/
	
	/**
     * 方式二、double-check， 避免并发时创建了多个实例, 该方式不能完全避免并发带来的破坏.
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
     * 1. 静态变量确定实例只有一个
     * 2. 静态方法作为获得实例的入口
     * 3. 私有化构造方法避免程序外的调用
     * 4. 线程安全的.因为静态类只会加载一次，成功避免多线程操作而造成的重复操作（实例创建）.
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