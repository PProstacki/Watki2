package wątki2;

public class RecursionThread implements Runnable {

    long factorialNumber;
    long outcome;
    long nanoseconds;

    public long getOutcome() {
        return outcome;
    }

    public long getNanoseconds() {
        return nanoseconds;
    }

    RecursionThread(long factorialNumber) {
        this.factorialNumber = factorialNumber;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        outcome = recursionFactorial(factorialNumber);
        nanoseconds = System.nanoTime() - startTime;
    }
    
    private  long recursionFactorial(long temp) {
        if (temp == 0) {
            return 1;
        } else {
            return (temp * recursionFactorial(temp - 1));
        }
    }
}
