package designPattern;
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
    
 
    //¿‡æ≤Ã¨øÈ
    static {
        System.out.println("Test Static");
    }
 
    public static Singleton testOut = new Singleton(1);
    //¿‡æ≤Ã¨ Ù–‘
 
    public static void main(String args[]){
        Singleton t = new Singleton(2);
        Singleton.getInstance();
    }*/
    
    private static class SingletonHolder{
    	private static Singleton instance = new Singleton();
    }
 
    public static Singleton getInstance(){
    	return SingletonHolder.instance;
    }
}