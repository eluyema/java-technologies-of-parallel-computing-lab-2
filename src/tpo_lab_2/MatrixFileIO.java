package tpo_lab_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MatrixFileIO {

    // Method to write a matrix to a text file
    public static void writeMatrixToFile(double[][] matrix, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter( filename));
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                writer.write(Double.toString(matrix[i][j]));
                writer.write(" ");
            }
            writer.newLine();
        }
        writer.close();
    }
    
    // Method to read a matrix from a text file
    public static double[][] readMatrixFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int rows = 0;
        int cols = 0;
        while ((line = reader.readLine()) != null) {
            rows++;
            String[] parts = line.split(" ");
            cols = parts.length;
        }
        reader.close();

        double[][] matrix = new double[rows][cols];
        reader = new BufferedReader(new FileReader(filename));
        int i = 0;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            for (int j = 0; j < parts.length; j++) {
                matrix[i][j] = Double.parseDouble(parts[j]);
            }
            i++;
        }
        reader.close();

        return matrix;
    }
    
    // Method to write a vector to a text file
    public static void writeVectorToFile(double[] vector, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < vector.length; i++) {
            writer.write(Double.toString(vector[i]));
            writer.newLine();
        }
        writer.close();
    }
    
    // Method to read a vector from a text file
    public static double[] readVectorFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int size = 0;
        while ((line = reader.readLine()) != null) {
            size++;
        }
        reader.close();

        double[] vector = new double[size];
        reader = new BufferedReader(new FileReader(filename));
        int i = 0;
        while ((line = reader.readLine()) != null) {
            vector[i] = Double.parseDouble(line);
            i++;
        }
        reader.close();

        return vector;
    }
    
    // Method to write a scalar to a text file
    public static void writeScalarToFile(double scalar, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(Double.toString(scalar));
        writer.newLine();
        writer.close();
    }
    
    // Method to read a scalar from a text file
    public static double readScalarFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        reader.close();
        return Double.parseDouble(line);
    }
}
