package registrationScheduler.store;

import java.util.Vector;
import registrationScheduler.util.Logger;

public class Classes{
	private static int[] capacity = new int[7];
	private static Vector<Vector<Student>> classRosters = new Vector<Vector<Student>>();
	
	/**
	 * Constructor
	 */
	public Classes(){
		
		Logger.writeMessage("Object Pool of classes created", Logger.DebugLevel.CONSTRUCTOR);
		
		for (int i = 0; i < capacity.length; i++){
			capacity[i] = 60;
		} 
		for (int i = 0; i < 7; i++){
			classRosters.add(new Vector<Student>(60));
		}
	}
	
	/**
	 * borrowObject -takes in an index for class number and a student object and 
	 * assigns the student to that class
	 */
	public static void borrowObject(int index, Student newStudent){
		if(capacity[index] > 0){
			capacity[index]--;
			classRosters.get(index).add(newStudent);
		}
		else{
			System.out.println("capacity full.");
		}
	}
	
	/**
	 * returnObject - takes in a student and a class and removes the student from the class
	 */
	public static void returnObject(int index, Student newStudent){
		capacity[index]++;
	}
	
	/**
	 * getCapacity - takes in an index for the class number
	 * (getActive)
	 * @return - int for the number of seats left in the class
	 */
	public static int getCapacity(int index){
		return capacity[index];
	}
	
	/**
	 * getRoster - takes in an index for the class number
	 * @return - Vector<Student> for the students in that class
	 */
	public Vector<Student> getRoster(int index){
		return classRosters.get(index);
	}
	
	//Never needed. The student assignments are what matter
	//public String toString(){
		//
	//}
	
}