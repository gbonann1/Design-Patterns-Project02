
package registrationScheduler.threadMgmt;

import java.io.IOException;
import java.lang.InterruptedException;

import registrationScheduler.store.Classes;
import registrationScheduler.store.Results;
import registrationScheduler.store.Student;
import registrationScheduler.store.StdoutDisplayInterface;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;



public class WorkerThread implements Runnable  {
	private FileProcessor f;
	private StdoutDisplayInterface r;
	private Classes c;
	private int counter;

	/**
	 * Constructor - assigns the f, r, and c to the appropriate params
	 */
	public WorkerThread(FileProcessor fpIn, StdoutDisplayInterface reIn, Classes clIn){
		f = fpIn;
		r = reIn;
		c = clIn;
		Logger.writeMessage("WorkerThread created", Logger.DebugLevel.CONSTRUCTOR);
	}
	
	/**
	 * run - method called by the threads to initialize reading of file, and creation of student
	 */
    public synchronized void run() {
    	Logger.writeMessage("run function called", Logger.DebugLevel.RUN);
    		while(!this.isAssigned() && counter < 2000){
    			this.execute();
    			counter++;
			}
    }
    
    public void execute(){
    	synchronized(r){
			String thisLine = null;
			try {
				thisLine = f.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}
			if(thisLine != null){
				int[] tempArray = new int[7];
				String[] tokens = thisLine.split("\\s+");
				tempArray[0] = Integer.valueOf(tokens[1]);
				tempArray[1] = Integer.valueOf(tokens[2]);
				tempArray[2] = Integer.valueOf(tokens[3]);
				tempArray[3] = Integer.valueOf(tokens[4]);
				tempArray[4] = Integer.valueOf(tokens[5]);
				tempArray[5] = Integer.valueOf(tokens[6]);
				tempArray[6] = Integer.valueOf(tokens[7]);		
				Student tempStudent = new Student(tempArray);
				r.addStudent(tempStudent);
				r.incrementCounter();
				//System.out.println(r.getStudent(0));
				this.assign();
			}
			else{
				r.incrementCounter();
				this.assign();
			}
			notifyAll();
		}
    }
    
    /**
	 * assign - helper method used to assign one class to a student
	 */
    public void assign(){
    	boolean pick = false;
    	int i = r.getCounter();
  		int loops = 0;
    	while(!pick){
	    	for(int pref = 1; pref < 8; pref++){
	    		int[] temp = r.getStudent(i).getPreferences();
	    		for (int j = 0; j < temp.length; j++){
	    			loops++;
	    			int[] temp2 = r.getStudent(i).getClasses();
	    			if (!pick && (temp[j] == pref) && (temp2[j] == 0) && 
	    			(c.getCapacity(j) > 0) && (r.getStudent(i).getNumClasses() < 5)){
	    				
	    				r.getStudent(i).assignClass(j);
	    				r.getStudent(i).updateScore(pref);
	    				c.borrowObject(j ,r.getStudent(i));
	    				pick = true;	
	    			}
	    			else if (loops > 8 * temp.length)
	    				pick = true; 
	    		}
	    	}
	    }
    }
    
    /**
	 * isAssigned - checks if all students have been assigned 5 classes
	 */
    public boolean isAssigned(){
        boolean assigned = true;
    	for(int i = 0;i<r.getBodySize(); i++){
    		if(r.getStudent(i).getNumClasses() != 5){
    			assigned = false;
    		}
    	}
    	if(r.getBodySize() == 0){
    		assigned = false;
    	}
    	return assigned;
    }
    

}