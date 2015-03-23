package thoughts;

import java.util.concurrent.Callable; 
import java.util.concurrent.ExecutorService;   
import java.util.concurrent.Executors;   
import java.util.concurrent.Future; 
import java.util.logging.Logger;
  
/*
 * Callable 和 Future接口  
 * Callable是类似于Runnable的接口,实现Callable接口的类和实现Runnable的类都是可被其它线程执行的任务。  
 * Callable和Runnable有几点不同:  
 * (1)Callable规定的方法是call(),而Runnable规定的方法是run().  
 * (2)Callable的任务执行后可返回值(任意类型),而Runnable的任务是不能返回值的。  
 * (3)call()方法可抛出异常,而run()方法是不能抛出异常的。  
 * (4)运行Callable任务可拿到一个Future对象,  
 * Future 表示异步计算的结果。它提供了检查计算是否完成的方法,以等待计算的完成,并检索计算的结果。  
 * 通过Future对象可了解任务执行情况,可取消任务的执行,还可获取任务执行的结果。  
 */  
public class CallableAndFuture {   
	
	private static final String TAG = "xlx";
  
    /** *//**  
     * 自定义一个任务类,实现Callable接口  
     */  
    public static class MyCallableClass implements Callable{   
        // 标志位   
        private int flag = 0;   
        public MyCallableClass(int flag){   
            this.flag = flag;   
        }
        
		@Override
        public String call() throws Exception{   
            if (this.flag == 0){
                // 如果flag的值为0,则立即返回   
                return "flag = 0";   
            }    
            if (this.flag == 1){
                // 如果flag的值为1,做一个无限循环   
                try {   
                    while (true) {   
                        System.out.println("looping.");   
                        Thread.sleep(1000);   
                    }   
                } catch (InterruptedException e) {   
                    System.out.println("Interrupted");   
                }   
                return "false";   
            } else {   
                // falg不为0或者1,则抛出异常   
                throw new Exception("Bad flag value!");   
            }   
        }   
    }   
       
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {   
        // 定义3个Callable类型的任务   
        /*MyCallableClass task1 = new MyCallableClass(0);   
        MyCallableClass task2 = new MyCallableClass(1);   
        MyCallableClass task3 = new MyCallableClass(2);   
        // 创建一个执行任务的服务   
        ExecutorService es = Executors.newFixedThreadPool(3);   
        try {   
            // 提交并执行任务,任务启动时返回了一个 Future对象,   
            // 如果想得到任务执行的结果或者是异常可对这个Future对象进行操作   
            Future future1 = es.submit(task1);   
            // 获得第一个任务的结果,如果调用get方法,当前线程会等待任务执行完毕后才往下执行   
            System.out.println("task1: " + future1.get());   
               
            Future future2 = es.submit(task2);   
            // 等待5秒后,再停止第二个任务。因为第二个任务进行的是无限循环   
            Thread.sleep(5000);   
            //future2.cancel(true)会触发InterruptedException，
            System.out.println("task2 cancel: " + future2.cancel(true));   
               
            // 获取第三个任务的输出,因为执行第三个任务会引起异常   
            // 所以下面的语句将引起异常的抛出   
            Future future3 = es.submit(task3);   
            System.out.println("task3: " + future3.get());   
        } catch (Exception e){   
            System.out.println(e.toString());   
        }   
        // 停止任务执行服务   
        es.shutdownNow();   */
    	CvbsStateRefresh mThread = new CvbsStateRefresh();
    	mThread.start();
    	mThread.setCurrentState(CvbsStateRefresh.RUNNING);
    	try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	mThread.setCurrentState(CvbsStateRefresh.EXIT);
    }   
    
    private static class CvbsStateRefresh extends Thread {
      public static final int NO_START = 0;
      public static final int RUNNING = 1;
      public static final int PAUSE = 2;
      public static final int EXIT = 3;
      public int currentState = NO_START;
      public Object obj = new Object();
  
      public void setCurrentState(int state) {
         synchronized (obj) {
            this.currentState = state;
         }
      }
  
      public int getCurrentState() {
         synchronized (obj) {
            return this.currentState;
         }
      }
  
      @SuppressWarnings("static-access")
      private synchronized void doReadCvbsState() {
         
      }
  
      private synchronized void loopRun() {
         while (true) {
            if (getCurrentState() == NO_START) {
               try {
            	   System.out.println("NO_START...");
//                  wait(3000);
                  Thread.sleep(1000);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            } else if (getCurrentState() == RUNNING) {
               doReadCvbsState();
               try {
            	   System.out.println("RUNNING...");
//                  wait(3000);
                  Thread.sleep(1000);
               } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
            } else if (getCurrentState() == PAUSE) {
               try {
            	   System.out.println("PAUSE...");
//                  wait(3000);
                  Thread.sleep(1000);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            } else if (getCurrentState() == EXIT) {
//               Logger.getAnonymousLogger().info("stop this thread!");
            	System.out.println("stop this thread!");
               break;
            }
         }
      }
  
      public void run() {
         /*if (logOn) {
            Log.e(TAG, "start method mCVBSModule.cvbsStateRefresh!");
         }*/
         loopRun();
      }
   }

}
