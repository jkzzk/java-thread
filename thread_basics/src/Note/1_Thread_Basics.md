# Java 线程

## 概念
    
1. 进程  
    进程是程序的一次动态执行过程，它需要经历从代码加载，代码执行到执行完毕的一个完整的过程，这个过程也是进程本身从产生，发展到最终消亡的过程。  
2. 线程  
    一个进程可以包含多个线程，线程是操作系统执行程序的最小单元，线程可以同时存在，同时进行
    
## 实现线程的方式

1. 通过实现Runnable接口，来创建一个线程
    
    ``` java
        public class FirstThread implements Runnable {}
        
        实现run方法，该方法就是线程执行的任务
    
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(this.name + ":" + i);
            }
        } 
    ```
    
2. 通过继承Thread类，来创建一个线程

    ``` java
        public class FirstThread implements Runnable {}
   
        重写run方法，该方法就是线程执行的任务
   
        public void run() {
            for (int i = 0; i < 10; i++) {
               System.out.println(this.name + ":" + i);
            }
        }
         
        创建线程对象，由Thread的构造方法创建一个线程对象
        
        Thread thread_A = new Thread(firstThread_A); 
    ```
   
## 线程启用方法

1. start()方法
     
     ``` java
        
        Thread thread_A = new Thread(firstThread_A);
        Thread thread_B = new Thread(firstThread_B);
        
        thread_A.start(); 由Thread对象调用
        thread_B.start(); 由Thread对象调用
   
     ```   

## 线程状态
    
    任何线程一般具有5种状态，即创建，就绪，运行，阻塞，终止
    
1 创建状态
    
    在程序中用构造方法创建了一个线程对象后，新的线程对象便处于新建状态，此时它已经有了相应的内存空间和其他资源，但还处于不可运行状态。新建一个线程对象可采用Thread 类的构造方法来实现，例如 “Thread thread=new Thread()”。
   
2 就绪状态
    
    新建线程对象后，调用该线程的 start() 方法就可以启动线程。当线程启动时，线程进入就绪状态。此时，线程将进入线程队列排队，等待 CPU 服务，这表明它已经具备了运行条件。
    
3 运行状态
    
    当就绪状态被调用并获得处理器资源时，线程就进入了运行状态。此时，自动调用该线程对象的 run() 方法。run() 方法定义该线程的操作和功能。

4 阻塞状态 

    一个正在执行的线程在某些特殊情况下，如被人为挂起或需要执行耗时的输入/输出操作，会让 CPU 暂时中止自己的执行，进入阻塞状态。在可执行状态下，如果调用sleep(),suspend(),wait() 等方法，线程都将进入阻塞状态，发生阻塞时线程不能进入排队队列，只有当引起阻塞的原因被消除后，线程才可以转入就绪状态。

5 死亡状态

    线程调用 stop() 方法时或 run() 方法执行结束后，即处于死亡状态。处于死亡状态的线程不具有继续运行的能力。
    
## 获取当前线程与线程名称

1. 方法

     ``` java
   
         public static native Thread currentThread();
         获取当前线程
         public final String getName() {}
         获取线程名称
   
     ``` 
   
## 强制运行线程

1. 方法
    
    ``` java
    
        public final void join() throws InterruptedException { }
        等待调用线程结束，强制执行当前线程，使得其他线程进入阻塞状态
        public final synchronized void join(long millis) throws InterruptedException { } 
        等待调用线程多长毫秒，强制执行当前线程，使得其他线程进入阻塞状态
        public final synchronized void join(long millis, int nanos) throws InterruptedExceptio { } 
        等待调用线程多长毫秒加纳秒，强制执行当前线程，使得其他线程进入阻塞状态 
     
    ``` 
   
## 线程休眠

1. 方法

    ``` java
   
        public static void sleep(long millis) throws InterruptedException {}
        当前线程等待多少毫秒，参数指定
        public static void sleep(long millis，int nanos) throws InterruptedException {}
        当前线程等待多少毫秒加纳秒，参数指定
    ``` 
   
## 线程中断

1. 方法

    ``` java
        public void interrupt() { }
        中断线程,该方法不能中断正在运行的进程，只是可以改变线程的状态为中断状态，所以其可以中断sleep、join、wait等状态
    ```

## 后台线程

1. 方法

    ``` java
        public final void setDaemon(boolean on) { }
        设置后台进程，true：设置为后台进程，false： 默认，不是后台进程 
    ```
   
## 线程优先级

1. 方法

    ``` java
        public final void setPriority(int newPriority) { }
        设置线程优先级
        优先级预设值：
            static int MAX_PRIORITY 
            线程可以拥有的最大优先级。  
            static int MIN_PRIORITY 
            线程可以拥有的最小优先级。  
            static int NORM_PRIORITY 
            分配给线程的默认优先级。  
    ```
   
## 线程让步

1. 方法

    ``` java
        public static native void yield();
        让其他线程先执行
    ```   
   
## 线程同步

1. 同步代码块

     ``` java
        synchronized (同步锁) {
            可能会出现线程安全的代码块（访问共享数据的代码）
            注意：多个需要同步的代码块，需要同一个同步锁，锁可以是任意对象
        }
     ```  
``
2. 同步方法

     ``` java
        修饰符 synchronized 返回值 方法名（参数列表） {
            可能会出现线程安全的代码块（访问共享数据的代码），这种方式将会用方法本身作为一个同步锁
        }
     ```
   
3. 锁机制

    会在后面详解
    
## 线程通信

1. 方法

    ``` java
        public final native void wait(long timeout) throws InterruptedException;
        使当前线程进入等待状态，由锁调用
        public final native void wait(long timeout) throws InterruptedException;
        使当前线程进入等待多少毫秒，由锁调用
        public final native void wait(long timeout, int nanos) throws InterruptedException
        使当前线程进入等待多少毫秒加纳秒，由锁调用
   
        public final native void notify();
        唤醒一个在等待状态的线程，具体并不知道是哪一个线程
        public final native void notifyAll();
        唤醒所有在等待的线程
    ```            