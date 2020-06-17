# java 多线程 集合

## ConcurrentHashMap

1. JDK1.8 之前是采用分段锁，之后采用CAS和synchronized加数组、链表、红黑树来保证线程安全的

2. HashMap线程不安全，HashTable在激烈的线程竞争下效率低下，ConcurrentHashMap在多线程环境下，能保证线程安全且效率较高

3. 方法和HahsMap常用的方法一致