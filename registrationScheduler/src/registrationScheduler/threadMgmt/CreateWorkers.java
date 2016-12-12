
package registrationScheduler.threadMgmt;

import registrationScheduler.store.Classes;
import registrationScheduler.store.Results;
import registrationScheduler.store.StdoutDisplayInterface;
import registrationScheduler.util.FileProcessor;
import registrationScheduler.util.Logger;


public class CreateWorkers  {

	private FileProcessor fp;
	private StdoutDisplayInterface r;
	private Classes c;
	
	/**
	 * Constructor - assigns the fp, r, and c to the appropriate params
	 */
    public CreateWorkers(FileProcessor fileProcessorIn, StdoutDisplayInterface resultsIn, Classes poolIn){
    	fp = fileProcessorIn;
    	r = resultsIn;
    	c = poolIn;
    	Logger.writeMessage("CreateWorkers created", Logger.DebugLevel.CONSTRUCTOR);
    }
    
    /**
	 * startWorkers - creates the number of threads equal to the param numThreads
	 */
    public void startWorkers(int numThreads) throws InterruptedException{
    	Thread[] threads = new Thread[numThreads];
    	for (int i = 0; i < numThreads; i++){
	    	WorkerThread w = new WorkerThread(fp, r, c);
	    	Thread t = new Thread(w);
	    	threads[i] = t;
	    	t.start();
	    }
	    for (int i = 0; i < numThreads; i++){
	    	threads[i].join();
	    }
    }

}