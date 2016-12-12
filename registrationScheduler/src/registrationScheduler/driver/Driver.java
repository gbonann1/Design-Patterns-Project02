package registrationScheduler.driver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.lang.Class;


import registrationScheduler.store.Classes;
import registrationScheduler.store.Results;
import registrationScheduler.threadMgmt.CreateWorkers;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;
import registrationScheduler.store.StdoutDisplayInterface;
import registrationScheduler.store.FileDisplayInterface;



public class Driver{
	
	public static void main(String args[]) throws IOException, InterruptedException {

		String in = null;
		String out = null;
		int numThreads = -1;
		int debugValue = -1;
		if (args.length == 4){
			in = args[0];
			out = args[1];
			try {
				numThreads = Integer.valueOf(args[2]); //between 1 and 3
				debugValue = Integer.valueOf(args[3]); //between 0 and 4
			}
			catch (NumberFormatException e){
				System.out.println("Invalid type for number of threads/debug level");
				e.printStackTrace();
				System.exit(0);
			}
		}
		else  {
			System.out.println("Please provide 4 args");
			System.exit(0);
		}
		if (numThreads < 1 | numThreads > 3){
			System.out.println("numThreads must be between 1 and 3");
			System.exit(0);
		}
		if (debugValue < 0 | debugValue > 4){
			System.out.println("debugValue must be between 0 and 4");
			System.exit(0);
		}
		//creating neccessary objects
		Logger myLogger = new Logger(); 
		myLogger.setDebugValue(debugValue);
		FileProcessor myInput = new FileProcessor(in);
		Classes poolOfClasses = new Classes();
		
		//creating results as an STDDInterface in order to implement the interface
		//however this forces us to include all method declarations in StdoutDisplayInterface
		//so that we can pass this object and use the methods in the results class
		StdoutDisplayInterface result = new Results();
		//used for printing to a file
		FileDisplayInterface result2 = (Results)result;
		
		//creating the worker threads with above objects
		CreateWorkers c = new CreateWorkers(myInput, result, poolOfClasses);
		c.startWorkers(numThreads);
		
		// *Note: functions for printing to screen and file just need to be uncommented
		//result.writeSchedulesToScreen();
		result2.writeSchedulesToFile(out);
		
		//logger level 1 - printing the contents of the data structure in the store
		if(debugValue == 1){
			result.printStoreContents();
		}
		
		//logger level 0 - printing the average preference
		if(debugValue == 0){
			result.printAveragePreference();
		}
		
	} // end main(...)

} // end public class Driver

