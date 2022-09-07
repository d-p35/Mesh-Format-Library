package meshOperations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class OFFMeshWriter implements MeshWriter{

	@Override
	public void write(String filename, HashSet<Polygon> polygons) throws IOException {
		FileWriter newfile = new FileWriter(filename);
		newfile.write("OFF\n");
		LinkedHashSet<Vertex> vertices= new LinkedHashSet<Vertex>();
		for (Polygon poly: polygons) {
			for (Vertex vertex : poly.vertices) {
				vertices.add(vertex);
			}
		}
		
		Vertex []arrvertices = vertices.toArray(new Vertex[vertices.size()]);
		
		newfile.write(vertices.size() + " " + polygons.size() + " 0\n");
			for (Vertex vertex : vertices) {
				newfile.write(vertex.x + " " + vertex.y + " " + vertex.z + "\n");
		}
		
		for(Polygon poly : polygons) {
			
			int index;
			String str = String.valueOf(poly.vertices.size());
			
			
			for(index = 0; index<arrvertices.length; index++) {
				for(Vertex vertex : poly.vertices) {
					if(vertex.equals(arrvertices[index])) {
						str = str + " "+ index;
						break;
					}
				}
			}
			newfile.write(str + "\n");
		}
		newfile.close();
	}

}
