package meshOperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OFFMeshReader implements MeshReader{

	@Override
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException, FileNotFoundException {
		Scanner file = new Scanner(new File(filename));
		String line = file.nextLine();
		line = line.replaceAll("\\s+", " ").strip();
//		String expectedfirstline = new String("OFF");
		Pattern pattern = Pattern.compile("\\s*OFF\\s*");
		Matcher matcher = pattern.matcher(line);
		if(!(matcher.matches())) {
			file.close();
			throw new WrongFileFormatException("First line is not 'OFF'");
		}
		line = file.nextLine();
		line = line.replaceAll("\\s+", " ").strip();
 		pattern = Pattern.compile("\\s*\\d+\\s+\\d+\\s+\\d+\\s*");
 		matcher = pattern.matcher(line); 
 		if(!(matcher.matches())) {
 			file.close();
 			throw new WrongFileFormatException("Incorrect format of second line");
 		}
 		
 		String []attributes = line.split(" ");
 		double numvert = Double.parseDouble(attributes[0]);
 		double numface = Double.parseDouble(attributes[1]);
 		
 		pattern = Pattern.compile("\\s*(-?\\d+(\\.\\d+)?\\s){2}-?\\d+(\\.\\d+)?\\s*");
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
 	 	 		for(int j=0;j<num_values;j++) {
 	 	 			double value = Double.parseDouble(values[j+1]);
 	 	 			if(!(value>=0 && value<numvert)) {
 	 	 				throw new WrongFileFormatException("Faces vertices range is incorrect");
 	 	 			}
 	 	 		}
			} catch (NumberFormatException e) {
				throw new WrongFileFormatException("There is a character on the face lines that is not a digit");
			}
 	 		pattern = Pattern.compile("\\s*\\d+(\\s\\d+){"+ values[0] + "}\\s*((([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\s){2}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]))?\\s*");
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
