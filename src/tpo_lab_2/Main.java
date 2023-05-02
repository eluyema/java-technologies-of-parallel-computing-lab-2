package tpo_lab_2;

public class Main {
	public static void main(String[] args) {
		
		int matrixSize = 1000;
		boolean generateData = true;
		boolean printData = false;
		
		boolean isFox = true;
		
		MatrixGenerator generator = new MatrixGenerator(10);
		DataManager dataManager = new DataManager(generator);
		
		if(generateData) {
			dataManager.generateData(matrixSize);
		}
		
		double[][] AA = dataManager.getMatrix("AA");
		double[][] BB = dataManager.getMatrix("BB");
		
		if(printData) {
			dataManager.printMatrix(AA, "AA");
			dataManager.printMatrix(BB, "BB");
		}
		ResultMatrix RES = new ResultMatrix(matrixSize);
		long startTime = System.currentTimeMillis();
		try {
			if(isFox) {
				RES = FoxCalculator.multiplyMatrixByMatrix(AA, BB);
			} else {
				RES = StripedCalculator.multiplyMatrixByMatrix(AA, BB);
			}
		} catch (InterruptedException e) {
			System.out.println("Error was appeared");
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;

		dataManager.writeMatrixToFile(RES.getMatrix(), "RES");
		if(printData) {
			dataManager.printMatrix(RES.getMatrix(), "RES");
		}
		System.out.println("Duration of caclulation: " + duration + " milliseconds");
	}

}
