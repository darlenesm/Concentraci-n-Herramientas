public class Example4 {
    public static void main(String[] args) throws InterruptedException {
        class Account {
            int balance = 100;
            public Account(int balance) { this.balance = balance; }
            public synchronized void deposit(int amount) { balance += amount; }
            public synchronized boolean withdraw(int amount) {
                if (balance >= amount) {
                    balance -= amount;
                    return true;
                }
                return false;
            }
            public synchronized boolean transfer(Account destination, int amount) {
                if (balance >= amount) {
                    balance -= amount;
                    synchronized(destination) {
                        destination.balance += amount;
                    };
                    return true;
                }
                return false;
            }
            public int getBalance() { return balance; }
        }

        final Account Darlene = new Account(200000);
        final Account Victor = new Account(300000);

        class FirstTransfer extends Thread {
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    Darlene.transfer(Victor, 2);
                }
            }
        }
        class SecondTransfer extends Thread {
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    Victor.transfer(Darlene, 1);
                }
            }
        }

        FirstTransfer thread1 = new FirstTransfer();
        SecondTransfer thread2 = new SecondTransfer();
        thread1.start(); thread2.start();
        thread1.join(); thread2.join();
        System.out.println("Darlene's balance: " + Darlene.getBalance());
        System.out.println("Victor's balance: " + Victor.getBalance());
    }
}