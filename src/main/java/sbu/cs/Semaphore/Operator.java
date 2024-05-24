package sbu.cs.Semaphore;
import java.util.concurrent.Semaphore;
public class Operator extends Thread {

    public Operator(String name, Semaphore sm) {
        super(name);
        this.sm = sm;
    }
    Semaphore sm;
    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
        {
            try {
                sm.acquire();
                System.out.println("Name: " + getName() + "Time: " + System.currentTimeMillis());
                Resource.accessResource();          // critical section - a Maximum of 2 operators can access the resource concurrently
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            sm.release();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
