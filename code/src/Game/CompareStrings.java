package Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CompareStrings {
	private boolean result;
	private int bukcetRemoved;
	private ArrayList<String> wordArray;
	private Set<String> bucketSet;

	public CompareStrings()
	{
		result = false;
		bukcetRemoved = 0;
		wordArray = new ArrayList<String>();
		bucketSet = new HashSet<String>(101);
	}

	public void compare(String shortString,String longString)
	{
		result = false;
		bukcetRemoved = 0;
		wordArray.clear();
		bucketSet.clear(); 
		
		if (shortString.length() <= longString.length())
		{	
			// split strings into sets
			for (int i = 0; i < shortString.length();i++)
			{
				wordArray.add(Character.toString(shortString.charAt(i)));
			}

			for (int i = 0;i < longString.length();i++)
			{
				bucketSet.add(Character.toString(longString.charAt(i)));
			}

			for (int i = 0; i < wordArray.size(); i++)
			{
				String letter = wordArray.get(i);
				if (bucketSet.contains(letter))
				{
					bukcetRemoved++;
					bucketSet.remove(letter);
				}
			}

			if (bukcetRemoved == wordArray.size())
				result = true;
			else
				result = false;

		}
		else
		{
			result = false;
		}
	}

	public boolean isMatched()
	{
		return result;
	}

	public String getBucket()
	{
		bucketSet.toArray();
		String bucket = "";
		Iterator<String> iter = bucketSet.iterator();
		
		while (iter.hasNext())
		{
			bucket = iter.next() + bucket;
		}
		
		return bucket;
	}

	public int getRemoved()
	{
		return bukcetRemoved;
	}
}