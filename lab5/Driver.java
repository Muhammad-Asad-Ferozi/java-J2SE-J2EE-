import java.util.*;

class Health
{
		String name;
		Double weight, bmi, height;
		
		public Health()
		{
			name = "";
			weight = 0.0;
			height = 0.0;
			bmi = 0.0;
		}
		
		
		public Health(String na, Double wi, Double he)
		{
			name = na;
			weight = wi;
			height = he;
			bmi = weight/height;
		}
		
		void setInfo(String na, Double wi, Double he)
		{
			name = na;
			weight = wi;
			height = he;
			bmi = weight/height;
		}
		
		public String toString()
		{
			return("Name = " +name + "Weight = " + weight + "Height = " + height + "BMI = " + bmi+ "");
		}
		
		
}

public class Driver
{
	public static void main(String[] args)
	{
		int n=0;
		Scanner in = new Scanner(System.in);
		
		do{
		System.out.print("Enter the size of array: ");
		n = in.nextInt();
		try
		{
			doWork(n);
		}
		catch(NegativeArraySizeException e)
		{
			System.out.println("Exception" + e);
		}
		}while(n<0);
		
		Health[] ar = new Health[n];
		
		String tempName="";
		Double tempWeight = 0.0;
		Double tempHeight = 0.0;
		int temp=n;
		char check;
		
		do{
		
		System.out.print("Enter the index You want to check: ");
		n = in.nextInt();
		
		System.out.print("Enter the Name: ");
		tempName = in.nextLine();
		tempName = in.nextLine();
		
		System.out.print("Enter the Weight: ");
		tempWeight = in.nextDouble();
		
		System.out.print("Enter the Height: ");
		tempHeight = in.nextDouble();
		
		try
		{
			save(ar,n,tempName,tempWeight,tempHeight);
		}
		catch (indexOccupiedException e)
		{
			System.out.println("Exception" + e);
		}
		
		System.out.print("Do you want to repete again, Enter q for quit and any other char to continue: ");
		check = in.next().charAt(0);
		}while(check != 'q');
		
		do{	
		System.out.print("Enter the index You want to retrive data: ");
		n = in.nextInt();
		try
		{
			retrive(ar,n,temp);
		}
		catch(indexException e)
		{
			System.out.println("Exception " + e);
		}
		System.out.print("Do you want to repete again, Enter q for quit and any other char to continue: ");
		check = in.next().charAt(0);
		}while(check != 'q');
	}
	public static void doWork(int value) throws NegativeArraySizeException
	{
		if(value < 0){ throw new NegativeArraySizeException(value);}		
	}
	
	public static void save(Health ar[],int index, String name, Double weight, Double height) throws indexOccupiedException
	{
		if(ar[index] != null ) { throw new indexOccupiedException(index); }
		else { ar[index] = new Health(name, weight,height); }
	}
	public static void retrive(Health ar[],int index, int temp) throws indexException
	{
		if(index<0 || index >temp || ar[index] == null) { throw new indexException(index); }
		else{ System.out.println(ar[index]); }
	}
}



class NegativeArraySizeException extends Exception
{
	int value;

    NegativeArraySizeException(int v)
    {
        value = v;
    }
	
	public String toString()
	{
		return("This is toString of negative Array index exception" + value);
	}

}
class indexOccupiedException extends Exception
{
	int value;

    indexOccupiedException(int v)
    {
        value = v;
    }
	public String toString()
	{
		return("This is toString of index Occupied exception"+value);
	}

	
}
class indexException extends Exception
{
	int value;
    indexException(int v)
    {
        value = v;
    }
	
	public String toString()
	{
		return("This is toString of index exception"+value);
	}

}