package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tools {
	public static BufferedReader getNewBufferedReader(String filename) throws FileNotFoundException {
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(fileReader);
			return reader;
		}
		catch (FileNotFoundException e) {
			throw new FileNotFoundException("Error: No se encontro el archivo '" + filename + "' [" + e.getMessage() + "]");
		}
	}
	
	public static String readLine(BufferedReader reader) {
		try {
			return reader.readLine();
		}
		catch (IOException e) {
			return null;
		}
	}
	
	public static void closeBufferedReader(BufferedReader reader) throws IOException {
		try {
			reader.close();
		}
		catch (IOException e) {
			throw new IOException("Error: No se pudo cerrar el buffer de lectura [" + e.getMessage() + "]");
		}
	}
}
