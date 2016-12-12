
package registrationScheduler.store;

import registrationScheduler.util.Logger;

import java.util.Vector;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Results implements StdoutDisplayInterface, FileDisplayInterface {
    private Vector<Student> studentBody;
	private int studentBCounter = -1;
	double averagePref = 0;
    // appropriate data structure as private data member

	/**
	 * Constructor - creates a vector of students
	 */
	public Results(){
		Logger.writeMessage("Results created", Logger.DebugLevel.CONSTRUCTOR);
		studentBody = new Vector<Student>();
	}
	
	/**
	 * incrementCounter - keeps track of the student currently being assigned 
	 * so the threads know which student to assign next
	 */
	public synchronized void incrementCounter(){
		if(studentBCounter < 79){
			studentBCounter++;
		}
		else{
			studentBCounter = 0;
		}
	}
	
	/**
	 * getBodySize - 
	 * @returns an int for the number of students in the student body
	 */
	public int getBodySize(){
		return studentBody.size();
	}
	
	/**
	 * getCounter
	 * @returns int for the student about to be assigned
	 */
	public int getCounter(){
		return studentBCounter;
	} 
	
	/**
	 * getAveragePref
	 * @returns a double for the average pref score of all students
	 */
	public double getAveragePref(){
		return averagePref;
	}
	
	/**
	 * calcPref - calculates the average pref of all students
	 */
	public void calcPref(){
		double sum = 0;
		for(int i=0; i<studentBody.size(); i++){
			sum += studentBody.get(i).getTotalScore();
		}
		averagePref = sum/studentBody.size();
	}
	
	/**
	 * toString
	 * @returns a string with each students number and classes
	 */
	public String toString(){
		String out = new String();
		for(int i = 0; i<studentBody.size(); i++){
			out += i + " " + studentBody.get(i) + "\n";
		}
		return out;
	}
    
    /**
	 * addStudent - adds a student to the student body from the param
	 */
    public synchronized void addStudent(Student s){
    	studentBody.add(s);
    	Logger.writeMessage("Student added", Logger.DebugLevel.RESULTS);
    }
    
    /**
	 * getStudent
	 * @returns a Student from the studentBody at the index specified
	 */
    public synchronized Student getStudent(int index){
    	return studentBody.get(index);
    }

	public void writeSchedulesToFile(String out){
		PrintStream outStream = null;
		PrintStream original = System.out;
		try{
			
			outStream = new PrintStream(new FileOutputStream(out));
			System.setOut(outStream);
			String temp = "";
    		for(int i = 0; i < 80; i++){
				temp += "Student_" + i + "" + studentBody.get(i) + "\n";
    		}
    		this.calcPref();
    		System.out.print(temp);
    		this.printAveragePreference();
		}
		catch (FileNotFoundException e) {
			if (outStream != null) {
				outStream.close();
			}
			e.printStackTrace();
			System.exit(0);
		}
		System.setOut(original);
	}

	/**
	 * writeScheduleToScreen - prints the students one at a time from the student body as well as the average pref score
	 */
    public void writeSchedulesToScreen() {
    	String temp = "";
    	for(int i = 0; i < 80; i++){
			temp += "Student_" + i + "" + studentBody.get(i) + "\n";
    	}
    	this.calcPref();
    	System.out.print(temp);
    	this.printAveragePreference();
    }
    
    public void printStoreContents() {
    	String temp = "";
    	for(int i = 0; i < 80; i++){
			temp += "Student_" + i + "" + studentBody.get(i) + "\n";
    	}
    	Logger.writeMessage(temp, Logger.DebugLevel.DATA);
    }
    
    public void printAveragePreference(){
    	this.calcPref();
    	Logger.writeMessage("The average preference value is " + averagePref, Logger.DebugLevel.AVERAGE);
    }
}
