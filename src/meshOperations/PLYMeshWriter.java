package meshOperations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class PLYMeshWriter implements MeshWriter{

	@Override
	public void write(String filename, HashSet<Polygon> polygons) throws IOException {
		FileWriter newfile = new FileWriter(filename);
		
		LinkedHashSet<Vertex> vertices = new LinkedHashSet<Vertex>();
		
		for (Polygon poly : polygons) {
			for(Vertex vertex : poly.vertices) {
				vertices.add(vertex);
			}
		}
		int numvert = vertices.size();
		int numface = polygons.size();
		
		newfile.write(" ply \n"
				+ "format ascii 1.0\n"
				+ "element vertex "+numvert+"\n"
				+ "property float32 x\n"
				+ "property float32 y\n"
				+ "property float32 z\n"
				+ "element face "+numface+"\n"
				+ "property list uint8 int32 vertex_indices\n"
				+ "end_header\n");
		
		for(Vertex vertex : vertices) {
			newfile.write(vertex.x + " " + vertex.y + " " + vertex.z + "\n");
		}
		
		Vertex []vertices_arr = vertices.toArray(new Vertex[vertices.size()]);
		for(Polygon poly: polygons) {
			int index;
			String str = String.valueOf(poly.vertices.size());
			for(index=0;index<vertices_arr.length;index++) {
				for(Vertex vertex : poly.vertices) {
					if(vertex.equals(vertices_arr[index])) {
						str = str + " " + index;
						break;
					}
				}
			}
			newfile.write(str + "\n");
		}
		
		newfile.close();
	}
	
}
