import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Test {

	public static void main(String[] args) {		
		String fileName = "article.txt";
		
		try {
			File f = new File(fileName);
			
			// Check if the file exists
			if (!f.exists() || f.isDirectory()) {
				System.out.printf("The file '%s' does not exist.\n", fileName);
				return;
			}			
			
			// Set up file writer
			File output = new File("output.txt");
			if (output.exists() && output.isFile()) {
				output.delete();
			}
			output.createNewFile();
			FileWriter writer = new FileWriter(output);
			BufferedWriter out = new BufferedWriter(writer);
			
			// Set up file reader
			FileReader rdr = new FileReader(f);
			BufferedReader buf = new BufferedReader(rdr);
			String line = "";
			
			// Go through line by line until end of file
			while ((line = buf.readLine()) != null) {
				String[] words = line.split(" ");
				// Use this regular expression to exclude special characters
				String regex = "^.*[a-zA-Z0-9]+.*$";
				int counter = 0;
				// Now compare each word to make sure it's actually a word and not just a special character
				for (int i = 0; i < words.length; i++) {
					if (words[i].matches(regex)) {
						counter++;
					}
				}
				
				// Don't add word count if the line is empty
				if (counter > 0) {
					line = line + " (" + counter + ")";
				}
				out.write(line);
				out.newLine();
			}
						
			buf.close();
			rdr.close();
			
			out.close();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
