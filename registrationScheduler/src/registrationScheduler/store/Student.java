package registrationScheduler.store;

import registrationScheduler.util.Logger;


public class Student {
	private int[] myPreferences = new int[7];//index 0 - A, 1 - B, etc
	private int[] classes = new int[7];//0 if class not assigned 1 if assigned
	private int numClasses = 0;
	private int totalScore;
	
	/**
	 * Creates a new student object with no classes assigned
	 * @param newPreferences, array of int representing preferences
	 */
	public Student(int[] newPreferences){
			Logger.writeMessage("Student created", Logger.DebugLevel.CONSTRUCTOR);
			this.myPreferences = newPreferences;
			for (int i = 0; i < 7; i++){
				classes[i] = 0;
			}
	}
	/**
	 * Getter method
	 * @return myPreferences, array of int representing preferences
	 */
	public int[] getPreferences(){
		return myPreferences;
	}
	
	/**
	 * Getter method
	 * @return totalScore, the sum of the preferences of assigned
	 * classes
	 */
	public int getTotalScore(){
		return totalScore;
	}
	
	/**
	 * Getter Method
	 * @return numClasses, should not exceed 5
	 */
	public int getNumClasses(){
		return numClasses;
	}
	
	/**
	 * Getter Method
	 * @return classes, array of int representing which classes
	 * have been assigned to the student. Index 0 corresponds to A,
	 * 1 to B, etc. 
	 * Value of 0 means not assigned and 1 means assigned.
	 */
	public int[] getClasses(){
		return classes;
	}
	
	/**
	 * Updates student's total preference score 
	 * @param value, preference level of course to add to sum
	 */
	public synchronized void updateScore(int value){
		totalScore += value;
	}
	
	/**
	 * Updates the student's classes array
	 * Represents which classes have been assigned to the student
	 * @param index, 0-3 corresponds to classes A-D respectively
	 */
	public synchronized void assignClass(int index){
		classes[index] = 1;
		numClasses++;
	}
	
	/**
	 * toString to print the students preferences
	 */
	public String toString() {
		String out = new String();
		if (classes[0] == 1)
			out += " A";
		if (classes[1] == 1)
			out += " B";
		if (classes[2] == 1)
			out += " C";
		if (classes[3] == 1)
			out += " D";
		if (classes[4] == 1)
			out += " E";
		if (classes[5] == 1)
			out += " F";
		if (classes[6] == 1)
			out += " G";
		out+= " " + totalScore;
		return out;		
	}
}