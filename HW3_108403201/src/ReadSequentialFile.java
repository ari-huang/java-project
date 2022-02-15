import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadSequentialFile {
	private static ObjectInputStream input;
	public static void main(String[] args) {
		openFile();
		readRecords();
		closeFile();
	}
	
	public static void openFile() {
		try {
			 input = new ObjectInputStream(Files.newInputStream(Paths.get("save-as.txt")));
			
		}
		catch(IOException ioException) {
			System.err.println("Error opening file.");
			
		}
	}
	
	public static void readRecords() {
		try {
			System.out.printf("%-10s%-12s%-12s%10s%n","Content","Weather","EditTime","IsLike");
			while(true) {
			PostSerializable record = (PostSerializable) input.readObject();
				System.out.printf(record.getContent(),record.getWeather(),record.getEditTime(),record.getIsLike());
			}
		}
		catch(EOFException endOfFileException) {
			System.out.printf("%nNo more records%n");
		}
		catch (ClassNotFoundException classNotFouundException) {
			System.err.println("Invalid object type. Terminating.");
		}
		catch (IOException ioException) {
			System.err.println("Error reading from file. Terminating.");
		}
	}
	public static void closeFile() {
		try {
			if(input != null)
				input.close();
		}
		catch (IOException inException) {
			System.err.println("Error closing File.class Terminating.");
			System.exit(1);
		}
	}
}
