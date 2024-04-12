import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
    private int id;
    private int leftFork;
    private int rightFork;
    private Semaphore[] forks;

    public Philosopher(int id, Semaphore[] forks) {
        this.id = id;
        this.forks = forks;
        this.leftFork = id;
        this.rightFork = (id + 1) % forks.length;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Philosopher " + id + " thinking " + i + " time");

            try {
                forks[leftFork].acquire();
                forks[rightFork].acquire();
                
                System.out.println("Philosopher " + id + " took both forks");
                
                System.out.println("Philosopher " + id + " eating " + i + " time");

                forks[rightFork].release();
                forks[leftFork].release();
                
                System.out.println("Philosopher " + id + " put both forks");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}