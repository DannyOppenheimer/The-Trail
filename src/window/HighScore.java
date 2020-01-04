package window;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;

public class HighScore {
	
	// static methods, as the only values come from a text file,
	// and will never need multiple instances.
	
	public final static String[] getHighScore() throws IOException {
		
		String[] lines = Files.readAllLines(new File("Texts/HighScore.dat").toPath()).toArray(new String[0]);
		return lines;
	}
	
	public final static void writeHighScore(int w) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				
	        new FileOutputStream("Texts/HighScore.dat"), "utf-8"))) {
			writer.write(Integer.toString(w));
		}
	}
}
