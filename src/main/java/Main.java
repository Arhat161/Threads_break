import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // create first thread with anonymous class or lambda
        Thread firstThread = new Thread(() -> {
            Random random = new Random(); // new random generator
            for (int i = 0; i < 1000000000; i++) {
                // we check signal of interrupt for first thread
                if (Thread.currentThread().isInterrupted()) { // if we see interrupt signal
                    System.out.println("Thread '" + Thread.currentThread().getName() + "' was interrupted!");
                    break; // we break cycle
                }
                double targetDigit = random.nextDouble(); // get random digit
                double result = Math.sin(targetDigit); // get sinus of random digit
                System.out.println("Sin of " + targetDigit + " is " + result); // show result in console
            }
        }, "My first thread"); // set thread name

        // create second thread with anonymous class or lambda
        Thread secondThread = new Thread(() -> {
            // cycle without end
            while (true) {
                // we check signal of interrupt for second thread
                if (Thread.currentThread().isInterrupted()) { // if we see interrupt signal
                    System.out.println("Thread '" + Thread.currentThread().getName() + "' was interrupted!");
                    break; // we break cycle
                }
                System.out.println("Message from second thread!"); // second thread just send simple message
            }
        }, "My second thread"); // set thread name

        System.out.println("Starting threads...");
        // start first and second threads
        firstThread.start();
        secondThread.start();

        Thread.sleep(1000); // give some time to threads job

        firstThread.interrupt(); // send stop signal to first thread
        secondThread.interrupt(); // send stop signal to second thread

        // join all threads to main thread
        firstThread.join();
        secondThread.join();

        System.out.println("All threads finished!");
    }
}
