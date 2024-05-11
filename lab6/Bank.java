import java.io.*;
import java.util.*;

public class Bank
{
	private String name;
	private String accNo;
	private int balance;
	
	public Bank(String n,String acc)
	{
		name = n;
		accNo = acc;
		balance = 1000;
	}
	
	
	void setName(String n)
	{
		name = n;
	}
	void setAccNo(String acc)
	{
		accNo = acc;
	}
	void setBalance(int ba)
	{
		balance = ba;
	}
	
	
	String getName()
	{
		return name;
	}
	String getAccNo()
	{
		return accNo;
	}
	int getBalance()
	{
		return balance;
	}
	
	String getInfo()
	{
		return  name + ";" + accNo + ";" +balance ;
	}
	
	
	
	public static void addNewRecord(String name, String accNo)
	{
		try
		{
			FileWriter  fw= new FileWriter("AccountBook.txt",true);
			PrintWriter pw = new PrintWriter(fw);
			Bank ob = new Bank(name, accNo);
			pw.println(ob.getInfo());
			
			pw.flush();
			pw.close();
			fw.close();
		}
		catch (Exception e)
		{
			System.out.println("Exception " + e);
		}
	}
	
	public static void transfer(String sendAcc, String recAcc, int amount)
	{
		ArrayList <Bank> arr = new ArrayList<Bank>();
		String token[] = null;
		int bal=0;
		try
		{
			FileReader fr = new FileReader("AccountBook.txt");
			BufferedReader br = new BufferedReader(fr);
			try
			{
			String line = br.readLine();
			while(line != null)
			{
				token = line.split(";");
				Bank ob = new Bank(token[0], token[1]);
				bal = Integer.parseInt(token[2]);
				ob.setBalance(bal);
				arr.add(ob);
				line = br.readLine();
				
			}
			}
			catch(Exception ex)
			{
				System.out.println("exception1111:");		
			}
			
			br.close();
			fr.close();
			
	
		
			
			boolean flag = false;
			int index=0;
			Bank ob2 = null;
			for(int i=0; i< arr.size(); i++)
			{
				if(arr.get(i).getAccNo().equals(sendAcc) && arr.get(i).getBalance() >= amount)
				{
					ob2 = new Bank(arr.get(i).getName(), arr.get(i).getAccNo());
					ob2.setBalance(arr.get(i).getBalance());
					flag = true;
					index = i;
					
					break;
				}
			}
			if(flag == true)
			{
				for(int i=0; i< arr.size(); i++)
				{
					if(arr.get(i).getAccNo().equals(recAcc))
					{
						Bank ob1 = null;
						ob1 = new Bank(arr.get(i).getName(), arr.get(i).getAccNo());
						ob1.setBalance(arr.get(i).getBalance() + amount);
						arr.set(i, ob1);
						
						ob2.setBalance(ob2.getBalance()-amount);
						arr.set(index,ob2);
						flag = false;
					}
				}
				
				if (flag == true)
				{
					throw new noAccountFoundEx(-1);
				}
			}
			
		}
		catch(noAccountFoundEx ex)
		{
			System.out.println(ex);
		}
		catch(Exception ex)
		{
			System.out.println("Exception: " +ex);
		}
		
		
		try
		{
			FileWriter  fw= new FileWriter("AccountBook.txt");
			PrintWriter pw = new PrintWriter(fw);
			for(Bank it: arr)
			{
				pw.println(it.getInfo());
			}
				
			
			pw.flush();
			pw.close();
			fw.close();
		}
		catch (Exception e)
		{
			System.out.println("Exception " + e);
		}
		
		
	}
	
	public static void main(String[] args)
	{
		
		int check;
		menu();
		Scanner in = new Scanner(System.in);
		check = in.nextInt();
		
		while(check != 3)
		{
			if(check == 1)
			{
				String name, accNo;
				int bal;
				in.nextLine();
				System.out.println("Enter the name:");
				name = in.nextLine();
				
				System.out.println("Enter theaccount nO:");
				accNo = in.nextLine();
	
				
				addNewRecord(name, accNo);
				
			}	
			else if(check == 2)
			{
				int amount;
				String send,rec;
				in.nextLine();
				System.out.println("Enter the sender acc:");
				send = in.nextLine();
				System.out.println("Enter the reciever account nO:");
				rec= in.nextLine();
				
				
				System.out.println("Enter the amount:");
				amount = in.nextInt();
				
				transfer(send,rec,amount);
				
			}
	
			else
			{
				System.exit(0);
			}
			
			menu();
			check = in.nextInt();
			
		}
		System.out.println("Thaks for using me");
		
	}
	
	public static void menu()
	{
		System.out.println("1 add new Account");
		System.out.println("2 transfer");
		System.out.println("3 exit");
	}
}
class noAccountFoundEx extends Exception
{
	int value;
	noAccountFoundEx(int v)
	{
		value = v;
	}
	public String toString()
	{
		return "Exception reciever Account not found" + value;
	}
}