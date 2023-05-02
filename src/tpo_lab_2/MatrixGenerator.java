package tpo_lab_2;

import java.util.Random;

public class MatrixGenerator {

    private Random rand;
    private int numberSize;
    
    public MatrixGenerator(int numberSize) {
        this.rand = new Random();
        this.numberSize = numberSize;
    }

    public double[][] generateMatrix(int numRows, int numCols) {
        double[][] matrix = new double[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = rand.nextDouble() * this.numberSize;
            }
        }
        return matrix;
    }

    public double[] generateVector(int length) {
        double[] vector = new double[length];
        for (int i = 0; i < length; i++) {
            vector[i] = rand.nextDouble() * this.numberSize;
        }
        return vector;
    }

    public double generateScalar() {
        return rand.nextDouble() * this.numberSize;
    }
}
