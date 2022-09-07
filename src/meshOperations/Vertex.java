package meshOperations;

import java.util.Objects;

public class Vertex extends GraphicalObject{
	
	double x, y, z;
	
	public Vertex(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void transform(double[][] matrix) {
		double ix,iy,iz;
		ix = this.x;
		iy = this.y;
		iz = this.z;
		this.x = (matrix[0][0]*ix) + (matrix[0][1]*iy) + (matrix[0][2]*iz);
		this.y = (matrix[1][0]*ix) + (matrix[1][1]*iy) + (matrix[1][2]*iz);
		this.z = (matrix[2][0]*ix) + (matrix[2][1]*iy) + (matrix[2][2]*iz);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}



	@Override
	public String toString() {
		return x + " " + y + " " + z;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		return this.x==other.x && this.y==other.y && this.z==other.z;
	}
	
	
}
