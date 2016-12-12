package registrationScheduler.util;

import registrationScheduler.util.Logger;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.io.FileNotFoundException;



public class FileProcessor{
	private FileInputStream input = null;
	private BufferedReader buffer = null;
	
	/**
	 * Constructor - trys to create a new FileInputStream and a new BufferedReader
	 */
	public FileProcessor(String in)throws IOException{
		try {
				input = new FileInputStream(in);
				buffer = new BufferedReader(new InputStreamReader(input));	
				Logger.writeMessage("FileProcessor created", Logger.DebugLevel.CONSTRUCTOR);		
		}
		catch (FileNotFoundException e) {
			if (input != null) {
				input.close();
			}
			if (buffer != null){
				buffer.close();
			}
			System.out.println("File does not exist");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * readLine - reads in the next line from the file
	 */
	public synchronized String readLine()throws IOException{
		String thisLine;
		
		thisLine = buffer.readLine();
		return thisLine;
	}
}
