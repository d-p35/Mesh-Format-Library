package meshOperations;

import java.io.FileNotFoundException;
import java.util.HashSet;

public interface MeshReader {
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException, FileNotFoundException;
	
}
