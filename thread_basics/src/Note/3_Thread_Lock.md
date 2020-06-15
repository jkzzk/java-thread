# Java 线程

## ReentrantLock 可重入式锁

1. 与synchronized锁进行比较
   
* 尝试获得锁,获得就执行，没有获得，可以执行，也可以跳过
* 获取到锁的线程能够响应中断

2. 方法

* lock() 获取可重入锁
* unlock() 释放可重入锁
* Condition newCondition() 获取Condition对象  
      Condition对象的唤醒方法，只会唤醒该对象await方法进入阻塞的线程，换句话说，可以指定唤醒那个线程
*     await方法 等同于Object的wait()方法,使线程进入阻塞状态
*     signal方法 等同于Object的notify()方法，使线程被唤醒
*     signalAll方法 等同于Object的notifyAll()方法，使所有线程被唤醒
* getHoldCount() 查询当前线程保持此锁的次数，也就是执行此线程执行lock方法的次数
* getQueueLength（）返回正等待获取此锁的线程估计数，比如启动10个线程，1个线程获得锁，此时返回的是9
* getWaitQueueLength（Condition condition）返回等待与此锁相关的给定条件的线程估计数。比如10个线程，用同一个condition对象，并且此时这10个线程都执行了condition对象的await方法，那么此时执行此方法返回10
* hasWaiters(Condition condition)查询是否有线程等待与此锁有关的给定条件(condition)，对于指定contidion对象，有多少线程执行了condition.await方法
* hasQueuedThread(Thread thread)查询给定线程是否等待获取此锁
* hasQueuedThreads()是否有线程等待此锁
* isFair()该锁是否公平锁
* isHeldByCurrentThread() 当前线程是否保持锁锁定，线程的执行lock方法的前后分别是false和true
* isLock()此锁是否有任意线程占用
* lockInterruptibly（）如果当前线程未被中断，获取锁
* tryLock（）尝试获得锁，仅在调用时锁未被线程占用，获得锁
* tryLock(long timeout TimeUnit unit)如果锁在给定等待时间内没有被另一个线程保持，则获取该锁

## ReentrantReadWriteLock    可重入式读写锁

1. ReentrantReadWriteLock.readLock 读锁，称为共享锁

* 没有其他线程的写锁，
* 没有写请求或者有写请求，但调用线程和持有锁的线程是同一个。

2. ReentrantReadWriteLock.writeLock 写锁，称为排他锁

* 没有其他线程的读锁
* 没有其他线程的写锁

3. 而读写锁有以下三个重要的特性：
   
   （1）公平选择性：支持非公平（默认）和公平的锁获取方式，吞吐量还是非公平优于公平。
   
   （2）重进入：读锁和写锁都支持线程重进入。
   
   （3）锁降级：遵循获取写锁、获取读锁再释放写锁的次序，写锁能够降级成为读锁。
        所以注意写锁与读锁之间的调用顺序
    