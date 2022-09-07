package meshOperations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

public class Mesh extends GraphicalObject{
	HashSet<Polygon> polygons;
	MeshReader reader;
	MeshWriter writer;
	
	public void setReader(MeshReader reader) {
		this.reader = reader;
	}
	
	public void setWriter(MeshWriter writer) {
		this.writer = writer;
	}
	
	public void readFromFile(String filename) throws FileNotFoundException, WrongFileFormatException {
		polygons = reader.read(filename);
	}
	
	public void writeToFile(String filename) throws IOException {
		writer.write(filename, polygons);
	}

	@Override
	public void transform(double[][] matrix) {
		for (Polygon poly : polygons) {
			poly.transform(matrix);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(polygons, reader, writer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesh other = (Mesh) obj;
		for(Polygon poly1:this.polygons) {
			int counter = 0;
			for(Polygon poly2:other.polygons) {
				if (poly1.equals(poly2)){
					counter = 1;
				}
			}
			if(counter==0) return false;
		}
		return true;
	}
	
	
}
