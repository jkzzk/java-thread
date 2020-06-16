# Java 线程

## CyclicBarrier 栅栏

1. CyclicBarrier可以使一定数量的线程反复地在栅栏位置处汇集。当线程到达栅栏位置时将调用await方法，这个方法将阻塞直到所有线程都到达栅栏位置。如果所有线程都到达栅栏位置，那么栅栏将打开，此时所有的线程都将被释放，而栅栏将被重置以便下次使用。

2. 内部使用ReentrantLock与Condition 实现

3. 构造方法

    ``` java
        public CyclicBarrier(int parties)  
            参数为汇集的线程数量,每个线程使用await()方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞
        public CyclicBarrier(int parties, Runnable barrierAction)
            用于线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景
    ```
   
4. 方法
    
    await方法 会使得调用同一个栅栏类的线程进入等待状态，只有达到屏障点时，进入栅栏的所有线程才会被唤醒或者先执行barrierAction
    
## CountDownLatch 

1. 使得一个或多个线程等待另一个或多个线程执行完毕后，才会执行

2. 构造方法

``` java
    CountDownLatch(int cnt)
        参数为等待线程数
``` 

3. 方法

``` java
    await() 使得线程等待
    countDown() 执行线程完成后，减去要等待线程的数量
``` 

## Semaphore 信号量

1. 使资源在同一时间里，只能有一个线程访问，不是采用线程同步的方法，与synchronized编译方式不一样

2. 构造方法

``` java
    public Semaphore(int permits) 参数为资源数量，也可以说是使用资源权限的数量
    public Semaphore(int permits, boolean fair) 参数一同上，参数二使用资源时为公平模式，还是非公平模式 
```

3. 方法

``` java
    void acquire()
        从信号量获取一个许可，如果无可用许可前将一直阻塞等待，
    void acquire(int permits) 
        获取指定数目的许可，如果无可用许可前也将会一直阻塞等待
    boolean tryAcquire()
        从信号量尝试获取一个许可，如果无可用许可，直接返回false，不会阻塞
    boolean tryAcquire(int permits)
        尝试获取指定数目的许可，如果无可用许可直接返回false
    boolean tryAcquire(int permits, long timeout, TimeUnit unit)
        在指定的时间内尝试从信号量中获取许可，如果在指定的时间内获取成功，返回true，否则返回false
    void release()
        释放一个许可，别忘了在finally中使用，注意：多次调用该方法，会使信号量的许可数增加，达到动态扩展的效果，
            如：初始permits为1， 调用了两次release，最大许可会改变为2
    int availablePermits()
        获取当前信号量可用的许可
``` 

## Exchanger 

1. 可以交换两个线程的数据

2. 方法
  
``` java
    exchange（String x）
        方法具有阻塞的特性，也就是说，当没有其他线程与其进行数据交换，它将一直阻塞等待。
    exchange（String x，long timeout，TimeUnit unit）
        timeout表示超时时间，unit表示时间的单位，使用该方法，在规定的时间内没有从其他线程获取数据，将抛出超时异常。
```    