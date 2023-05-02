package tpo_lab_2;

public class ResultMatrix {
	private double[][] res;
	private int size;
	
	public ResultMatrix(int size) {
		this.res = new double[size][size];
		this.size = size;
	}
	
	public void setValue(int row, int column, double value) {
		res[row][column] = value;
	}
	
	public double[][] getMatrix() {
		return res;
	}
}
