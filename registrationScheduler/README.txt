Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

## To compile: 
ant -buildfile src/build.xml all

## To run by specifying arguments from command line [similarly for the 2nd argument and so on ...]
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=firstarg 

## To run by specifying args in build.xml (just for debugging, not for submission)
ant -buildfile src/build.xml run

## To create tarball for submission
ant -buildfile src/build.xml tarzip


CS542 Design Patterns
Fall 2016
PROJECT 2 README FILE

Due Date: Friday, September 30th, 2016
Submission Date: Friday, September 30th, 2016
Grace Period Used This Project: 0 Days
Grace Period Remaining: 4 Days
Author(s): Gianluca Bonanno, Ryan Simpson
e-mail(s): gbonann1@binghamton.edu, rsimpso1@binghamton.edu


PURPOSE:

[
  Reads in an input file of students with class preferences and tries to
  schedule the students to their preferred classes. Uses multithreading.
]

PERCENT COMPLETE:

	I believe We have completed 100% of this project.
	
 

PARTS THAT ARE NOT COMPLETE:

	N/A

BUGS:

	N/A

FILES:

	Included with this project are 12 files:

  	README, the text file you are presently reading
  	Driver.java
  	Classes.java
  	FileDisplayInterface.java
  	Results.java
  	StdoutDisplayInterface.java
  	Student.java
  	CreateWorkers.java
  	WorkerThread.java
  	FileProcessor.java
  	Logger.java
  	build.xml

SAMPLE OUTPUT:

	java Driver input1.txt output1.txt
	(output1.txt):
	Student_0 A B C D F 15
	Student_1 A D E F G 15
	Student_2 A C E F G 15
	Student_3 A B D F G 15
	Student_4 A B C F G 15
	Student_5 B C D E G 15
	Student_6 B C E F G 15
	Student_7 A C D E G 15
	Student_8 A C D F G 15
	Student_9 A C D E F 15
	.
	.
	.
	The average preference value is 15.0125

TO COMPILE:

	ant -buildfile src/build.xml all

TO RUN:

  	ant -buildfile src/build.xml run
 
DATA STRUCTURES:

	We created student objects and stored these student objects in
	a vector to represent all students to be assigned
	 	
	Space Complexity:
	There are 80 total students. The studentBody vector holds 80 student objects.
	
	Each student object has 2 int arrays of size 7 that correspond to
	the student's preferences and assigned courses.
	
	We use a vector of student vectors called classRosters to keep track
	of which students have been assigned to a given class.
	The size of this 2d vector is 7x60
	
	We use vectors because of their inherent synchronization.
	
	Our algorithm complexity is O(numberClassOffered^2 * numberStudents * numberClassesRequired)
	For this given example it would be O(7^2 * 80 * 5)
	Because we assign 1 class to a student at a time, and then loop through all 
	80 students 5 times so they get 5 classes each. Then for each individual 
	assignement we are looping through preferences 1 to 7 and for each preference 
	we loop through the students list of preferences to find the most preferred 
	available course.
	
	

EXTRA CREDIT:

	N/A


BIBLIOGRAPHY:

This serves as evidence that we are in no way intending Academic Dishonesty.
Gianluca Bonanno
Ryan Simpson

ACKNOWLEDGEMENT: