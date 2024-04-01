import java.util.concurrent.Semaphore;

public class threads {
    public static Semaphore db = new Semaphore(1);
    public static Semaphore Mutex = new Semaphore(0);
    static class ThreadA extends Thread {

        public void run() {
            while (true) {
                double soma = 0;
                for (int i = 0; i < 1000; i++)
                    for (int j = 0; j < 2000; j++) {
                        soma = soma + Math.sin(i) + Math.sin(j);
                    }
                try {
                    Mutex.acquire();
                    System.out.println("A");
                    db.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static class ThreadB extends Thread {
        public void run() {
            while (true) {
                double soma = 0;
                for (int i = 0; i < 10000; i++)
                    for (int j = 0; j < 2000; j++) {
                        soma = soma + Math.sin(i) + Math.sin(j);
                    }
                try {
                    db.acquire();
                    System.out.println("B");
                    Mutex.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadA tA1 = new ThreadA();
        ThreadB tB1 = new ThreadB();
        tA1.start();
        tB1.start();
    }
}
