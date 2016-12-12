
package registrationScheduler.store;

public interface StdoutDisplayInterface {

    public void writeSchedulesToScreen();
    
    public int getBodySize();
    public int getCounter();
    public double getAveragePref();
    public void calcPref();
    public void addStudent(Student s);
    public Student getStudent(int index);
    public String toString();
    public void incrementCounter();
    public void printStoreContents();
    public void printAveragePreference();

} 
