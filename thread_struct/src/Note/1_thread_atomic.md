# java 多线程之数据结构

## Atomic 原子数据

>1.基本类型
* AtomicInteger：整形原子类
* AtomicLong：长整型原子类
* AtomicBoolean：布尔型原子类

>2.数组类型
* AtomicIntegerArray：整形数组原子类
* AtomicLongArray：长整形数组原子类
* AtomicReferenceArray：引用类型数组原子类

>3.引用类型
* AtomicReference：引用类型原子类
* AtomicStampedReference：原子更新引用类型里的字段原子类,内部维护了一个时间戳，进行比较
* AtomicMarkableReference ：原子更新带有标记位的引用类型，内部维护了一个标记位，进行比较

>4.对象属性更新类型
* AtomicIntegerFieldUpdater：原子更新整形字段的更新器
* AtomicLongFieldUpdater：原子更新长整形字段的更新器

>5.基本类型常用方法

``` java
    public final int get() 
        获取当前的值
    public final int getAndSet(int newValue)
        获取当前的值，并设置新的值
    public final int getAndIncrement()
        获取当前的值，并自增
    public final int getAndDecrement()
        获取当前的值，并自减
    public final int getAndAdd(int delta)
        获取当前的值，并加上预期的值
    boolean compareAndSet(int expect, int update)
        如果输入的数值等于预期值，则以原子方式将该值设置为输入值（update）
    public final void lazySet(int newValue)
        最终设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
``` 

>6.数组类型常用方法

``` java
    public final int get(int i)
        获取 index=i 位置元素的值
    public final int getAndSet(int i, int newValue)
        返回 index=i 位置的当前的值，并将其设置为新值：newValue
    public final int getAndIncrement(int i)
        获取 index=i 位置元素的值，并让该位置的元素自增
    public final int getAndDecrement(int i) 
        获取 index=i 位置元素的值，并让该位置的元素自减
    public final int getAndAdd(int delta)
        获取 index=i 位置元素的值，并加上预期的值
    boolean compareAndSet(int expect, int update)
        如果输入的数值等于预期值，则以原子方式将 index=i 位置的元素值设置为输入值（update）
    public final void lazySet(int i, int newValue)
        最终 将index=i 位置的元素设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
```