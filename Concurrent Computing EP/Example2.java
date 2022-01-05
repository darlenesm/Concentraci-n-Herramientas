public class Example2 
{
    public static void main(String[] args) throws InterruptedException {
        class Counter {
            int counter;
            public void increment() { counter++; }
            public synchronized void increase() { counter++; }
            public synchronized int get() { return counter; }
        }
        final Counter counter = new Counter();
        
        class CountingThread extends Thread {
            public void run() {
                for (int i = 0; i < 500000; i++) {
                    counter.increment();
                }
            }
        }

        CountingThread d1 = new CountingThread();
        CountingThread v2 = new CountingThread();
        d1.start(); v2.start();
        d1.join(); v2.join();
        System.out.println(counter.get());
    }
    
}
