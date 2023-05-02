package tpo_lab_2;

import java.io.IOException;

public class DataManager {
	
    private MatrixGenerator generator;
    
    public DataManager(MatrixGenerator matrixGenerator) {
        this.generator = matrixGenerator;
    }
	
	public void generateData(int matrixSize) {
	    // Write a matrix to a file
		this.generateMatrixes(this.generator, matrixSize);
	}
	
	public double[][] getMatrix(String name) {
	    double[][] matrixFromFile = null;
	    try {
	        matrixFromFile = MatrixFileIO.readMatrixFromFile( name + ".txt");
	    } catch (IOException e) {
	        System.out.println("Error reading matrix from file: " + e.getMessage());
	    }
	    
	    return matrixFromFile;
	}
	
	public void printMatrix(double[][] matrix, String name) {
	    if (matrix != null) {
	        System.out.println("Matrix " + name + ":");
	        for (int i = 0; i < matrix.length; i++) {
	            for (int j = 0; j < matrix[0].length; j++) {
	                System.out.print(matrix[i][j] + " ");
	            }
	            System.out.println();
	        }
	    }
	}
	
	private void generateMatrixes(MatrixGenerator generator, int matrixSize) {
	    double[][] AA = generator.generateMatrix(matrixSize, matrixSize);
	    writeMatrixToFile(AA, "AA");
	    
	    double[][] BB = generator.generateMatrix(matrixSize, matrixSize);
	    writeMatrixToFile(BB, "BB");
	}
		
	
	public void writeMatrixToFile(double[][] matrix, String name) {
	    try {
	        MatrixFileIO.writeMatrixToFile(matrix, name + ".txt");
	    } catch (IOException e) {
	        System.out.println("Error writing matrix "+name+" to file: " + e.getMessage());
	    }
	}
}
