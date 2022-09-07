package meshOperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.xpath.XPathEvaluationResult;

public class PLYMeshReader implements MeshReader{

	@Override
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException, FileNotFoundException {
		Scanner file = new Scanner(new File(filename));
		
		String line = file.nextLine();
		line = line.replaceAll("\\s+", " ").strip();
		Pattern pattern = Pattern.compile("ply\\s*");
		Matcher matcher = pattern.matcher(line);
		if(!matcher.matches()) {
			throw new WrongFileFormatException("First line of file is not valid");
		}
		
		line = file.nextLine();
		line = line.replaceAll("\\s+", " ").strip();
		pattern = Pattern.compile("format ascii 1.0\\s*");
		matcher = pattern.matcher(line);
		if(!matcher.matches()) {
			throw new WrongFileFormatException("Second line of file is not valid");
		}
		
		line = file.nextLine();
		line = line.replaceAll("\\s+", " ").strip();
		pattern = Pattern.compile("element vertex\\s+\\d+\\s*");
		matcher = pattern.matcher(line);
		if(!matcher.matches()) {
			throw new WrongFileFormatException("Third line of file is not valid");
		}
		String []lineof_numvert = line.split(" ");
		double numvert = Double.parseDouble(lineof_numvert[2]);
		
		line = file.nextLine();
		line = line.replaceAll("\\s+", " ").strip();
		pattern = Pattern.compile("property float32 x\\s*");
		matcher = pattern.matcher(line);
		if(!matcher.matches()) {
			throw new WrongFileFormatException("Fourth line of file is not valid");
		}
		
		line = file.nextLine();
		line = line.replaceAll("\\s+", " ").strip();
		pattern = Pattern.compile("property float32 y\\s*");
		matcher = pattern.matcher(line);
		if(!matcher.matches()) {
			throw new WrongFileFormatException("Fifth line of file is not valid");
		}
		
		line = file.nextLine();
		line = line.replaceAll("\\s+", " ").strip();
		pattern = Pattern.compile("property float32 z\\s*");
		matcher = pattern.matcher(line);
		if(!matcher.matches()) {
			throw new WrongFileFormatException("Sixth line of file is not valid");
		}
		
		line = file.nextLine();
		line = line.replaceAll("\\s+", " ").strip();
		pattern = Pattern.compile("element face\\s+\\d+\\s*");
		matcher = pattern.matcher(line);
		if(!matcher.matches()) {

			throw new WrongFileFormatException("Seventh line of file is not valid");
		}
		
		String []lineof_numface = line.split(" ");
		double numface = Double.parseDouble(lineof_numface[2]);
		
		line = file.nextLine();
		line = line.replaceAll("\\s+", " ").strip();
		pattern = Pattern.compile("property list uint8 int32 vertex_indices\\s*");
		matcher = pattern.matcher(line);
		if(!matcher.matches()) {
			throw new WrongFileFormatException("Eighth line of file is not valid");
		}
		
		
		line = file.nextLine();
		line = line.replaceAll("\\s+", " ").strip();
		pattern = Pattern.compile("end_header\\s*");
		matcher = pattern.matcher(line);
		if(!matcher.matches()) {
			throw new WrongFileFormatException("Ninth line of file is not valid");
		}
		
		pattern = Pattern.compile("\\s*-?\\d+(\\.\\d+)?\\s+-?\\d+(\\.\\d+)?\\s+-?\\d+(\\.\\d+)?\\s*");
 		String []vertices = new String[(int)numvert];
 		
 		for(int i = 0; i<numvert;i++) {
 			line = file.nextLine();
 			line = line.replaceAll("\\s+", " ").strip();
 			vertices[i] = line;
 			matcher = pattern.matcher(line);
 			if(!(matcher.matches())) {
 				file.close();
 				throw new WrongFileFormatException("Incorrect vertices format");
 			}
 		}
 		
 		String []faces = new String[(int)numface];
 		for(int i=0;i<numface;i++) {
 	 		line = file.nextLine();
 	 		line = line.replaceAll("\\s+", " ").strip();
 	 		faces[i] = line;
 	 		String []values = line.split(" ");
 	 		try {
 	 			double num_values = Double.parseDouble(values[0]);
 	 			for(int j = 0; j<num_values;j++) {
 	 	 			double value = Double.parseDouble(values[j+1]);
 	 	 			if(!(value>=0 && value<numvert)) {
 
 	 	 				throw new WrongFileFormatException("Faces indexes are not in range");
 	 	 			}
 	 	 		}
			} 
 	 		catch (NumberFormatException e) {
				throw new WrongFileFormatException("A character in the faces line is not a number");
			}
	 		
 	 		pattern = Pattern.compile("\\s*\\d+(\\s+\\d+){" + values[0] + "}\\s*");
 	 		matcher = pattern.matcher(line);
 	 		if(!matcher.matches()) {
 	 			throw new WrongFileFormatException("Incorrect faces format");
 	 		}
 	 		
 		}
 		
 		HashSet<Polygon> answer = new HashSet<Polygon>();
 		for(int j=0; j<faces.length;j++) {
 			LinkedHashSet<Vertex> listofvertex = new LinkedHashSet<Vertex>(); 
 			String []numf = faces[j].split(" ");
 			int vlines = Integer.parseInt(numf[0]);
 	 		for (int i=0;i<vlines;i++) {
 	 			String []vertex = vertices[Integer.parseInt(numf[i+1])].split(" ");
 	 			Vertex newvert = new Vertex(Double.parseDouble(vertex[0]), Double.parseDouble(vertex[1]), Double.parseDouble(vertex[2]));
 	 			listofvertex.add(newvert);
 	 		}
 	 		
 	 		Polygon poly = new Polygon(listofvertex);
 	 		answer.add(poly);
 		}

 		file.close();
		return answer;
	}

}
