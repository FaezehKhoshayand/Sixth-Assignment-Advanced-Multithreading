
## Bailey–Borwein–Plouffe formula

Among all the algorithms possible for calculating pi such as Chudnovsky algorithm, Leibniz’s formula and etc. The BBP was mostly recommended because it calculates the digits of pi with a high accuracy and also it takes a short amount of time for calculation and the reason is that in this algorithm we are working with basic data types that consume little memory.
<img style="float: left;" src="whatever.jpg">
The Bailey–Borwein–Plouffe formula (BBP formula) is a formula for π. It was discovered in 1995 by Simon Plouffe.The BBP formula gives rise to a spigot algorithm for computing the nth base-16 (hexadecimal) digit of π (and therefore also the 4nth binary digit of π) without computing the preceding digits. 

## In Java, what would be the outcome if the run() method of a Runnable object is invoked directly, without initiating it inside a Thread object?

In this case we won't have a new thread cause we're not creating it .we have just made a class with a method. we create an instance then call the run method and we are still in the thread main.
So th eoutcome is:
Running in: main
```text
public class DirectRunnable implements Runnable {
    public void run() {
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        DirectRunnable runnable = new DirectRunnable();
        runnable.run();
    }
}
```
## Elaborate on the sequence of events that occur when the join() method of a thread (let's call it Thread_0) is invoked within the Main() method of a Java program.

A new thread is started here.The main thread waits for the new thread to get finished and then prints "Back to: main".If we had something that throwed InterruptedException the e.printStackTrace() would be printed.
The outcome is:
Running in: Thread_0
Back to: main

```text
public class JoinThread extends Thread {
    public void run() {
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        JoinThread thread = new JoinThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Back to: " + Thread.currentThread().getName());
    }
}
```

