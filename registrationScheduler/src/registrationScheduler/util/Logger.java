
package registrationScheduler.util;

public class Logger{


    public static enum DebugLevel { CONSTRUCTOR, RUN, RESULTS, DATA, AVERAGE 
                                   };

    private static DebugLevel debugLevel;


    public static void setDebugValue (int levelIn) {
		switch (levelIn) {
		  case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
		  case 3: debugLevel = DebugLevel.RUN; break;
		  case 2: debugLevel = DebugLevel.RESULTS; break;
		  case 1: debugLevel = DebugLevel.DATA; break;
		  case 0: debugLevel = DebugLevel.AVERAGE; break;
		}
    }

    public static void setDebugValue (DebugLevel levelIn) {
	debugLevel = levelIn;
    }

    // @return None
    // Note: In class logger was explained in a way that made it seem like a lvl of 4 should
    // print all levels from 4-0, however we're sticking with the given design of only printing
    // the given level.
    public static void writeMessage (String     message  ,
                                     DebugLevel levelIn ) {
	if (levelIn == debugLevel)
	    System.out.println(message);
    }

    public String toString() {
	return "Debug Level is " + debugLevel;
    }
}
