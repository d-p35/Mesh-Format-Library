package meshOperations;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class Tester {
	static String dir = "C:\\Users\\dhruv\\OneDrive - University of Toronto\\CSCB07\\A1\\";

	@Test
	void VertexToStringtest() {
		Vertex vertex = new Vertex(2, 3, 4);
		String s = vertex.toString();
		assertTrue(s.equals("2.0 3.0 4.0"));
	}

	@Test
	void rotateOBJtest() throws FileNotFoundException, WrongFileFormatException {
		Mesh mesh1 = new Mesh();
		mesh1.setReader(new OBJMeshReader());
		mesh1.readFromFile("TestFiles/car.obj");
		mesh1.rotateYAxis(Math.PI / 3);
		Mesh mesh2 = new Mesh();
		mesh2.setReader(new OBJMeshReader());
		mesh2.readFromFile("TestFiles/" + "car_rotated.obj");

		assertTrue(mesh1.equals(mesh2));
	}

	@Test
	void rotatePLY() throws WrongFileFormatException, IOException {
		Mesh mesh1 = new Mesh();
		mesh1.setReader(new PLYMeshReader());
		mesh1.readFromFile("TestFiles/" + "car.ply");
		mesh1.rotateXAxis(Math.PI);

		Mesh mesh2 = new Mesh();
		mesh2.setReader(new OBJMeshReader());
		mesh2.readFromFile("TestFiles/" + "car.obj");
		mesh2.rotateXAxis(Math.PI);

		assertTrue(mesh1.equals(mesh2));

	}

	@Test
	void rotateOFF() throws WrongFileFormatException, IOException {
		Mesh mesh1 = new Mesh();
		mesh1.setReader(new OFFMeshReader());
		mesh1.readFromFile("TestFiles/" + "car.off");
		mesh1.rotateZAxis(Math.PI / 2);

		Mesh mesh2 = new Mesh();
		mesh2.setReader(new OBJMeshReader());
		mesh2.readFromFile("TestFiles/" + "car.obj");
		mesh2.rotateZAxis(Math.PI / 2);

		assertTrue(mesh1.equals(mesh2));
	}

	@Test
	void OBJwritertest() throws WrongFileFormatException, IOException {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("TestFiles/" + "car.obj");
		mesh.setWriter(new OBJMeshWriter());
		mesh.writeToFile("TestFiles/" + "car-writertest.obj");
		Mesh newmesh = new Mesh();
		newmesh.setReader(new OBJMeshReader());
		newmesh.readFromFile("TestFiles/" + "car-writertest.obj");
		assertTrue(newmesh.equals(mesh));
	}

	@Test
	void PLYwritertest() throws WrongFileFormatException, IOException {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("TestFiles/" + "custom-PLY-Testfile.ply");
		mesh.setWriter(new PLYMeshWriter());
		mesh.writeToFile("TestFiles/" + "custom-PLY-writer.ply");
		Mesh newmesh = new Mesh();
		newmesh.setReader(new PLYMeshReader());
		newmesh.readFromFile("TestFiles/" + "custom-PLY-writer.ply");
		assertTrue(newmesh.equals(mesh));
	}

	@Test
	void OFFwritertest() throws WrongFileFormatException, IOException {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("TestFiles/" + "custom-OFF-Testfile.off");
		mesh.setWriter(new OFFMeshWriter());
		mesh.writeToFile("TestFiles/" + "custom-OFF-writer.off");
		Mesh newmesh = new Mesh();
		newmesh.setReader(new OFFMeshReader());
		newmesh.readFromFile("TestFiles/" + "custom-OFF-writer.off");
		assertTrue(newmesh.equals(mesh));
	}

	@Test
	void OBJExceptionTest1() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new OBJMeshReader());
			mesh.readFromFile("TestFiles/" + "ObjException1.obj");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new OBJMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.obj");
		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "Wrong file format");
		}
	}

	@Test
	void OBJExceptionTest2() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new OBJMeshReader());
			mesh.readFromFile("TestFiles/" + "ObjException2.obj");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new OBJMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.obj");
		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "Incorrect format for vertices");
		}

	}

	@Test
	void OBJExceptionTest3() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new OBJMeshReader());
			mesh.readFromFile("TestFiles/" + "ObjException3.obj");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new OBJMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.obj");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "Face vertices are not in range");
		}
	}

	@Test
	void OBJExceptionTest4() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new OBJMeshReader());
			mesh.readFromFile("TestFiles/" + "ObjException4.obj");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new OBJMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.obj");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "Format of faces is wrong");
		}
	}

	@Test
	void OBJExceptionTest5() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new OBJMeshReader());
			mesh.readFromFile("TestFiles/" + "ObjException5.obj");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new OBJMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.obj");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "There is a character on the face lines that is not a digit, apart from 'f'");
		}
	}

	@Test
	void OFFExceptionTest1() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new OFFMeshReader());
			mesh.readFromFile("TestFiles/" + "OffException1.off");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new OFFMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.off");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "First line is not 'OFF'");
		}
	}

	@Test
	void OFFExceptionTest2() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new OFFMeshReader());
			mesh.readFromFile("TestFiles/" + "OffException2.off");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new OFFMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.off");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "Incorrect format of second line");
		}
	}

	@Test
	void OFFExceptionTest3() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new OFFMeshReader());
			mesh.readFromFile("TestFiles/" + "OffException3.off");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new OFFMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.off");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "Incorrect vertices format");
		}
	}

	@Test
	void OFFExceptionTest4() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new OFFMeshReader());
			mesh.readFromFile("TestFiles/" + "OffException4.off");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new OFFMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.off");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "Faces vertices range is incorrect");
		}
	}

	@Test
	void OFFExceptionTest5() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new OFFMeshReader());
			mesh.readFromFile("TestFiles/" + "OffException5.off");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new OFFMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.off");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "There is a character on the face lines that is not a digit");
		}
	}

	@Test
	void OFFExceptionTest6() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new OFFMeshReader());
			mesh.readFromFile("TestFiles/" + "OffException6.off");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new OFFMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.off");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "Incorrect faces format");
		}
	}

	@Test
	void PLYExceptionHeaderTest() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new PLYMeshReader());
			mesh.readFromFile("TestFiles/" + "PlyException-header.ply");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new PLYMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.ply");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "Third line of file is not valid");
		}
	}

	@Test
	void PLYExceptionHeader1Test() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new PLYMeshReader());
			mesh.readFromFile("TestFiles/" + "PlyException-header(1).ply");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new PLYMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.ply");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "Fifth line of file is not valid");
		}
	}

	@Test
	void PLYExceptionTest2() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new PLYMeshReader());
			mesh.readFromFile("TestFiles/" + "PlyException2.ply");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new PLYMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.ply");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "Incorrect vertices format");
		}
	}

	@Test
	void PLYExceptionTest3() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new PLYMeshReader());
			mesh.readFromFile("TestFiles/" + "PlyException3.ply");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new PLYMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.ply");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "Faces indexes are not in range");
		}
	}

	@Test
	void PLYExceptionTest4() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new PLYMeshReader());
			mesh.readFromFile("TestFiles/" + "PlyException4.ply");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new PLYMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.ply");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "A character in the faces line is not a number");
		}
	}

	@Test
	void PLYExceptionTest5() throws WrongFileFormatException, IOException {
		try {
			Mesh mesh = new Mesh();
			mesh.setReader(new PLYMeshReader());
			mesh.readFromFile("TestFiles/" + "PlyException5.ply");
			mesh.rotateXAxis(Math.PI);
			mesh.setWriter(new PLYMeshWriter());
			mesh.writeToFile("TestFiles/" + "car-new.ply");

		} catch (WrongFileFormatException e) {
			assertEquals(e.message, "Incorrect faces format");
		}
	}
}
