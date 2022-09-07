package meshOperations;

public abstract class GraphicalObject {
	public abstract void transform(double [][]matrix);
	
	public void rotateXAxis(double theta) {
		double [][]matrixX = new double[3][3];
		matrixX[0][0] = 1;
		matrixX[1][1] = Math.cos(theta);
		matrixX[1][2] = - Math.sin(theta);
		matrixX[2][1] = Math.sin(theta);
		matrixX[2][2] = Math.cos(theta);
		transform(matrixX);
	}
	
	public void rotateYAxis(double theta) {
		double [][]matrixY = new double[3][3];
		matrixY[0][0] = Math.cos(theta);
		matrixY[0][2] = Math.sin(theta);
		matrixY[1][1] = 1;
		matrixY[2][0] = - Math.sin(theta);
		matrixY[2][2] = Math.cos(theta);
		transform(matrixY);
		
	}
	
	public void rotateZAxis(double theta) {
		double [][]matrixZ = new double[3][3];
		matrixZ[0][0] = Math.cos(theta);
		matrixZ[0][1] = - Math.sin(theta);
		matrixZ[1][0] = Math.sin(theta);
		matrixZ[1][1] = Math.cos(theta);
		matrixZ[2][2] = 1;
		transform(matrixZ);
	}
}
