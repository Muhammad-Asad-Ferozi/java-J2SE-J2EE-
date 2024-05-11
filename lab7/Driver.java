import java.util.*;
class Word
{
	String word_name;
	String meaning;
	
	Word()
	{
		word_name = null;
		meaning = null;
	}
	Word(String na,String me)
	{
		word_name = na;
		meaning = me;
	}
	Word(final Word ob)
	{
		word_name = ob.word_name;
		meaning = ob.meaning;
	}
	
	void setWord_name(String wo)
	{
		word_name = wo;
	}
	void setMeaning(String wo)
	{
		meaning = wo;
	}
	
	String getWord_name()
	{
		return word_name;
	}
	String getMeaning()
	{
		return meaning;
	}
}

class Dictionary
{
	HashMap<String, String> dic;
	Dictionary()
	{
		dic = new HashMap<String, String>();
	}
	void insertRecord()
	{
		String w,m;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter word: ");
		w = in.nextLine();
		System.out.print("Enter Meaning: ");
		m = in.nextLine();
		dic.put(w, m);
		
		//in.close();
	}
	void find(String word)
	{
		if(dic.containsKey(word))
		{
			
			System.out.println("Word = " + word + "\tMeaning = " + dic.get(word));
		}
		else
		{
			System.out.println("record Not found");
		}		
	}
}

public class Driver
{
	public static void main(String [] args)
	{
		Dictionary ob = new Dictionary();
		ob.insertRecord();
		ob.insertRecord();
		ob.insertRecord();
		Scanner in = new Scanner(System.in);
		String n;
		
		n = in.nextLine();
		ob.find(n);
		
		n = in.nextLine();
		ob.find(n);
		
	}
	
}