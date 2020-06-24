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

## ThreadLocal

1. 线程作用域下存储变量的类，内部用map实现，key为对应线程，value为对应值

2. 方法

``` java

    public void set(T value) 
        设置值
    public T get()
        获取值
    public void remove()
        清空以存储数据

```

## fork/join 分治多任务框架

1. 将大任务变为小任务然后，逐个小任务并行执行，最后汇总任务结果

2. fork/join 线程池 ForkJoinPool

* 构造方法

    ``` java
        public ForkJoinPool() 默认构造
        
        public ForkJoinPool(int parallelism)  参数为并行级别，不是线程池中的线程数
    
        public ForkJoinPool(int parallelism,
                            ForkJoinWorkerThreadFactory factory,
                            UncaughtExceptionHandler handler,
                            boolean asyncMode) 
            int parallelism 参数为并行级别，不是线程池中的线程数
            factory： 线程工厂，生产线程的工厂，工厂类ForkJoinWorkerThreadFactory
            handler：异常捕获处理器。当执行的任务中出现异常，并从任务中被抛出时，就会被handler捕获
            asyncMode：这个参数也非常重要，从字面意思来看是指的异步模式，它并不是说Fork/Join框架是采用同步模式还是采用异步模式工作。
                Fork/Join框架中为每一个独立工作的线程准备了对应的待执行任务队列，这个任务队列是使用数组进行组合的双向队列。
                即是说存在于队列中的待执行任务，即可以使用先进先出的工作模式，也可以使用后进先出的工作模式。
                默认为false，为后进先出，true为先进先出
    ```

* 方法
fork/join 中采用递归执行的方式，创建子任务线程，然后将其加入到线程队列中，然后等待执行   

    ``` java
        public final ForkJoinTask<V> fork()  
            将子任务线程插入执行队列中
        public final V join()
            将子任务值返回
    ``` 