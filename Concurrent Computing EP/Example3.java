import java.util.concurrent.atomic.AtomicInteger;

public class Example3 {
    
    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger counter = new AtomicInteger(0);

        class CountingThread extends Thread {
            public void run() {
                for (int i = 0; i < 500000; i++) {
                    counter.incrementAndGet();
                }
            }
        }
        CountingThread thread1 = new CountingThread();
        CountingThread thread2 = new CountingThread();
        thread1.start(); thread2.start();
        thread1.join(); thread2.join();
        System.out.println(counter.get());
    }
}
    

