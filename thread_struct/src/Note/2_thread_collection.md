# java 多线程 集合

## ConcurrentHashMap

1. JDK1.8 之前是采用分段锁，之后采用CAS和synchronized加数组、链表、红黑树来保证线程安全的

2. HashMap线程不安全，HashTable在激烈的线程竞争下效率低下，ConcurrentHashMap在多线程环境下，能保证线程安全且效率较高

3. 方法和HahsMap常用的方法一致

## ConcurrentLinkedQueue

1. 内部采用链表实现队列，可多线程访问

2. 方法

``` java

    public boolean add(E e)  入队
    public E poll() 出队
    public E peek() 获取首元素，只获取不移除     
    public int size() 长度
    public boolean remove(Object o) 删除某个元素
    public boolean contains(Object o) 是否包含某个元素

``` 

## 非阻塞算法

> 一.类加载器
>> 1.JVM类加载器的分类
>>
>>* Bootstrap ClassLoader ： 
>>``` desc
>>    1) 最顶层的加载类，主要加载核心类库，%JRE_HOME%\lib下的rt.jar、resources.jar、charsets.jar和class等。
>>    2) 另外需要注意的是可以通过启动jvm时指定-Xbootclasspath和路径来改变Bootstrap ClassLoader的加载目录。
>>```
>>* Extension ClassLoader
>>``` desc
>>    主要负责加载Java的扩展类库，加载目录%JRE_HOME%\lib\ext目录下的jar包和class文件。还可以加载-D java.ext.dirs选项指定的目录。
>>```
>>* Appclass Loader
>>``` desc
>>    也称为SystemAppClass 加载当前应用的classpath的所有类。
>>```
>
>> 2.类加载器启动顺序(双亲委托算法)
>> 
>> * 首先为启动类加载器(Bootstrap ClassLoader)加载进入内存，其完全有C实现，在JDK中没有其对应的java类
>> * 其次JVM launch 初始化，加载扩展类加载器(Extension ClassLoader)加载进入内存
>> * 最后应用类加载器(Appclass Loader)加载进入内存
>
>> 3.类加载顺序
>> 
>> * 自底向上（从应用到启动范围） 检查类是否加载
>> * 自上向下（从启动到应用范围） 加载该类
>> 
>> 该算法可以避免类重复加载，且提高类加载的效率，因为JVM不会讲素有class类全部加载进入内存，会动态的加载、连接类（class文件）算
>> 
>
>> 4.双亲委托算法的弊端
>> 
>> 顶层类加载器无法获取到底层的类加载器加载的类，如果顶层定义的接口与规范，在底层实现，那么用顶层接口调用时，是无法加载到底层的实现类的
>>
>> 解决办法 ： 绕开双亲委托算法，使用ContextClassLoader线程类加载器，其可以访问所有类加载器加载的类，此时就绕开了双亲委托的弊端
>>
> 二.Unsafe类
>
>> 1.概述
>>
>> JDK核心类，由C实现，JDK中只不提供调用的接口，由启动类加载器加载，其实不安全的，因为其采用操作系统底层语法实现对内存的分配、修改、添加、删除，完全绕开了JVM的空值，其分配的内存，不受GC控制，所以说及其危险
>> 
>> 1.方法
>>
>>  putOrderedObject(Object var1, long var2, Object var4) 直接在内存级别改变对象属性的值
>>  参数1：要改变属性值的对象
>>  参数2：属性值相对于对象地址的偏移量，一般通过objectFieldOffset(Field var1)方法获取，该方法也是魔法类中的方法
>>  参数3：改变后的属性值
>>  说明：该方法，会插入store-store内存屏障，并对其进行写操作，比volatile插入的store-loade内存屏障效率要高的多，但是缺少可见性，所以一般用来改变volatile修饰的值
>>
> 三.ConcurrentLinkedQueue
>
>> 1.概述
>>
>> 由非阻塞算法实现,内部为链表结构
>>
>> 2.方法
>>
>> public ConcurrentLinkedQueue()
>> public ConcurrentLinkedQueue(Collection<? extends E> c) 有参数构造
>> public boolean add(E e)  入队方法
>> public E poll() 出队方法