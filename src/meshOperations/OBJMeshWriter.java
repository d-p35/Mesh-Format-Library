package meshOperations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class OBJMeshWriter implements MeshWriter{

	@Override
	public void write(String filename, HashSet<Polygon> polygons) throws IOException {
		
		FileWriter newfile = new FileWriter(filename);
		
		LinkedHashSet<Vertex> vertices = new LinkedHashSet<Vertex>();
		for(Polygon poly : polygons) {
			for (Vertex vertex : poly.vertices) {
				vertices.add(vertex);
			}
		}
		
		for(Vertex vertex : vertices) {
			newfile.write("v " + vertex.x + " " + vertex.y + " " + vertex.z + "\n");
		}
		
		Vertex [] vert_arr = vertices.toArray(new Vertex[vertices.size()]);
		
		for(Polygon poly:polygons) {
			String str = "f";
			for(int i=0; i<vert_arr.length; i++) {
				for(Vertex vertex:poly.vertices) {
					if(vertex.equals(vert_arr[i])) {
						str = str + " " + (i+1);
						break;
					}
				}
			}
			newfile.write(str + "\n");
		}
		newfile.close();
	}

}
