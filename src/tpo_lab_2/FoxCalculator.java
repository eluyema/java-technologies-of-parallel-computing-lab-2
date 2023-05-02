package tpo_lab_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FoxCalculator {
	public static ResultMatrix multiplyMatrixByMatrix(double[][] A, double[][] B)
            throws InterruptedException {
        int n = A.length;
        int numThreads = 8;
        
        int sqrtP = (int) Math.sqrt(numThreads);
        if (n % sqrtP != 0) {
            throw new IllegalArgumentException("Matrix size must be a multiple of the square root of the number of threads.");
        }
        
        ResultMatrix result = new ResultMatrix(n);
        
        Thread[][] threads = new Thread[sqrtP][sqrtP];

        for (int i = 0; i < sqrtP; i++) {
            for (int j = 0; j < sqrtP; j++) {
                FoxAlgorithmTask task = new FoxAlgorithmTask(A, B, result, i, j, sqrtP);
                threads[i][j] = new Thread(task);
                threads[i][j].start();
            }
        }

        for (int i = 0; i < sqrtP; i++) {
            for (int j = 0; j < sqrtP; j++) {
                threads[i][j].join();
            }
        }

        return result;
    }

    private static class FoxAlgorithmTask implements Runnable {
        private double[][] A;
        private double[][] B;
        private ResultMatrix result;
        private int rowGroup;
        private int colGroup;
        private int sqrtP;

        public FoxAlgorithmTask(double[][] A, double[][] B, ResultMatrix result, int rowGroup, int colGroup, int sqrtP) {
            this.A = A;
            this.B = B;
            this.result = result;
            this.rowGroup = rowGroup;
            this.colGroup = colGroup;
            this.sqrtP = sqrtP;
        }

        @Override
        public void run() {
            int n = A.length;
            int blockSize = n / sqrtP;

            for (int stage = 0; stage < sqrtP; stage++) {
                int aOffset = (stage + rowGroup) % sqrtP;

                for (int i = rowGroup * blockSize; i < (rowGroup + 1) * blockSize; i++) {
                    for (int j = colGroup * blockSize; j < (colGroup + 1) * blockSize; j++) {
                        double sum = 0;
                        for (int k = aOffset * blockSize; k < (aOffset + 1) * blockSize; k++) {
                            sum += A[i][k] * B[k][j];
                        }
                        result.setValue(i, j, sum);
                    }
                }
            }
        }
    }
}
