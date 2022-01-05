public class Example1
{
      //first example w counter and 2 threads
    public static void main(String[] args) throws InterruptedException {
	class Counter {
	    int counter;
	    public void increment() { counter++; }
	    public int get() { return counter; }
	}
	
        final Counter counter = new Counter();
        
        class CountingThread extends Thread {
            public void  run() {
                for (int x = 0; x < 500000; x++) {
                    counter.increment();
                }
            }
        }

        CountingThread d = new CountingThread();
        CountingThread v = new CountingThread();
        d.start(); v.start();
        d.join(); v.join();
        System.out.println(counter.get());
    }

    

}


