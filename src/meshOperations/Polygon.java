package meshOperations;

import java.util.LinkedHashSet;
import java.util.Objects;

public class Polygon extends GraphicalObject{
	LinkedHashSet<Vertex> vertices;
	
	public Polygon(LinkedHashSet<Vertex> vertices) {
		this.vertices = vertices;
	}

	@Override
	public void transform(double[][] matrix) {
		for (Vertex vertex: vertices) {
			vertex.transform(matrix);
		}
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(vertices);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polygon other = (Polygon) obj;
		for (Vertex vertex1 : this.vertices) {
			int counter = 0;
			for(Vertex vertex2 : other.vertices) {
				if(vertex1.equals(vertex2)) {
					counter = 1;
					break;
				}
			}
			if(counter==0) {
				return false;
			}
		}
		return true;
	}
	

	
	
}
