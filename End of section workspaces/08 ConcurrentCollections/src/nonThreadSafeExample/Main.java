package nonThreadSafeExample;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ConcurrentModificationExceptionExample example = new ConcurrentModificationExceptionExample();
        //ArrayIndexOutOfBoundsExceptionExample example = new ArrayIndexOutOfBoundsExceptionExample();
        example.start();
    }
}
