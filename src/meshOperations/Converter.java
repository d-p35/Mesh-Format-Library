package meshOperations;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Converter {

	MeshReader reader;
	MeshWriter writer;
	
	public Converter() {
	
	}
	
	//Set to OBJMeshReader, OFFMeshReader, or PLYMeshReader according to current file format
	public void setReader(MeshReader reader) {
		this.reader = reader;
	}
	
	//Set to OBJMeshWriter, OFFMeshWriter, or PLYMeshWriter according to desired/new file format
	public void setWriter(MeshWriter writer) {
		this.writer = writer;
	}
	
	public void convert(String currentfile, String newfile) throws WrongFileFormatException, IOException, IllegalStateException {
		if(reader==null||writer==null) {
			throw new IllegalStateException("writer or reader has not been set");
		}
		
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(reader);
			mesh.setWriter(writer);
			
			mesh.readFromFile(currentfile);
			mesh.writeToFile(newfile);
			
			} 
		catch (FileNotFoundException f) {
			f.getMessage();
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
	
	
}
