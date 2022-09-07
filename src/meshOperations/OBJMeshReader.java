package meshOperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OBJMeshReader implements MeshReader{

	@Override
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException, FileNotFoundException {
		Scanner file = new Scanner(new File(filename));
		String line;
		
		LinkedHashSet<String> vertlines = new LinkedHashSet<String>();
		LinkedHashSet<String> facelines = new LinkedHashSet<String>();
		Pattern pattern;
		Matcher matcher;
		String []values;
		int count_ver = 0;
		while(file.hasNextLine()) {
			line = file.nextLine();
			line = line.replaceAll("\\s+", " ").strip();
			values = line.split(" ");
			if(values[0].equals("v")) {
				pattern = Pattern.compile("^v((\\s-?\\d+(\\.\\d+)?)(E-?\\d+)?){3}\\s*");
				matcher = pattern.matcher(line);
				if(!matcher.matches()) {
					throw new WrongFileFormatException("Incorrect format for vertices");
				}
				count_ver++;
				vertlines.add(line);
			}
			else if(values[0].equals("f")) {
				try {
					for(int i =0; i<values.length-1;i++) {
						double value = Double.parseDouble(values[i+1]);					
						if(!(value>0 && value<=count_ver)) {
							throw new WrongFileFormatException("Face vertices are not in range");
						}
					}
				} catch (NumberFormatException e) {
					throw new WrongFileFormatException("There is a character on the face lines that is not a digit, apart from 'f'");
				}
				pattern = Pattern.compile("\\s*f\\s+(\\d+\\s+){"+ (values.length-2) + "}\\d+\\s*");
				matcher = pattern.matcher(line);
				if(!matcher.matches()) {
					throw new WrongFileFormatException("Format of faces is wrong");
				}
				facelines.add(line);
			}
			else {
				file.close();
				throw new WrongFileFormatException("Wrong file format");
			}
		}
		
		
		String [] vertlines_arr = vertlines.toArray(new String[vertlines.size()]);
		String [] facelines_arr = facelines.toArray(new String[facelines.size()]);
		
		HashSet<Polygon> answer = new HashSet<Polygon>();
		
		for(int i =0; i<facelines_arr.length;i++) {
			LinkedHashSet<Vertex> vertices = new LinkedHashSet<Vertex>();
			String [] vertof_face = facelines_arr[i].split(" ");
			for(int j = 1; j<vertof_face.length;j++) {
				String [] vertcomponents = vertlines_arr[Integer.parseInt(vertof_face[j])-1].split(" ");
				Vertex vertex = new Vertex(Double.parseDouble(vertcomponents[1]), Double.parseDouble(vertcomponents[2]), Double.parseDouble(vertcomponents[3]));
				vertices.add(vertex);
				
			}
			Polygon poly = new Polygon(vertices);
			answer.add(poly);
		}
		file.close();
		return answer;
	}

}
