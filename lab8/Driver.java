import java.io.*;
class ReadFile implements Runnable
{       
	String fileName;              // param constructor  
	public ReadFile(String fn) {  fileName = fn;  } 
 
// overriding run method 
// this method contains the code for file reading  
// Read and print data line by line with the fileName 
public void run ( ) { 
	try
	{
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line;
		int count =1;
		while((line = br.readLine()) != null)
		{
			System.out.println(Thread.currentThread() +"\t" + fileName+ "\tLine No: " + count + "\t" + line );
			count++;
		}
		
		br.close();
		fr.close();
	}
	catch (Exception e)
	{
		System.out.println(e);
	}
	
 
} } // end of class 
 
    
 
public class Driver{  
	public static void main (String args[]) { 
 
		String file1= "file1.txt";
		String file2= "file2.txt";
		// create ReadFile objects by passing file names to them // create and start multiple threads (at least two) 
		
		ReadFile ob1 = new ReadFile(file1);
		ReadFile ob2 = new ReadFile(file2);
		
		
		try
		{
			Thread t1 = new Thread(ob1, "file1");
			Thread t2 = new Thread(ob2, "file2");
			
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
 
	}
}