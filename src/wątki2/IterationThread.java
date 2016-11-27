package wątki2;

public class IterationThread implements Runnable {

    long factorialNumber;
    long outcome;
    long nanoseconds;

    public long getOutcome() {
        return outcome;
    }

    public long getNanoseconds() {
        return nanoseconds;
    }

    IterationThread(long factorialNumber) {
        this.factorialNumber = factorialNumber;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        long tempOutcome = 1;
        for (int i = 2; i <= factorialNumber; i++) {
            tempOutcome = tempOutcome * i;
        }
        outcome = tempOutcome;
        nanoseconds = System.nanoTime() - startTime;
    }

}
