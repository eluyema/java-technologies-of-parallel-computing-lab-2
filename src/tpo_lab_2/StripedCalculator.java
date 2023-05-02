package tpo_lab_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StripedCalculator {
    public static ResultMatrix multiplyMatrixByMatrix(double[][] matrix1, double[][] matrix2)
            throws InterruptedException {
        int n = matrix1.length;
        
        ResultMatrix result = new ResultMatrix(n);

        ExecutorService executor = Executors.newFixedThreadPool(8);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                executor.submit(new MatrixMultiplyTask(matrix1, matrix2, result, i, j));
            }
        }

        executor.shutdown();

        try {
            if (!executor.awaitTermination(30, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            throw new RuntimeException("Matrix multiplication interrupted", e);
        }

        return result;
    }
    
    private static class MatrixMultiplyTask implements Runnable {
        private double[][] matrix1;
        private double[][] matrix2;
        private ResultMatrix result;
        private int rowStart;
        private int colStart;
        
        public MatrixMultiplyTask(double[][] matrix1, double[][] matrix2, ResultMatrix result, int rowStart, int colStart) {
            this.matrix1 = matrix1;
            this.matrix2 = matrix2;
            this.result = result;
            this.rowStart = rowStart;
            this.colStart = colStart;
        }
        
        @Override
        public void run() {
        	double sum = 0;
        	int n = matrix1.length;
        	int currentColumn = getNeededColumn(rowStart, colStart, n);
        	for(int i =0; i < n;i++) {
        		sum += matrix1[colStart][i] * matrix2[i][currentColumn];
        	}
        	result.setValue(colStart, currentColumn, sum);
        }
        
        static int getNeededColumn(int iterationIndex, int processIndex, int matrixSize) {
        	if(processIndex - iterationIndex < 0) {
        		return matrixSize - iterationIndex + processIndex;
        	}
        	return processIndex - iterationIndex;
        }
    }
}