package Game;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Bucket {
	String bucket = "";
	Set<String> bucketSet = new HashSet<String>(101);;
	
	public void updateBucket(String bucket)
	{
		this.bucket = bucket;
		
	}
	
	public void addNewLetter(String letter)
	{
		bucket += letter;
	}
	
	public int getNumBucket()
	{
		return  bucket.length();
	}
	
	public String getBucket()
	{
		return bucket;
	}
	
/**
	public void removeLetters(String candidate)
	{
		for (int i = 0; i < bucket.length(); i++)
		{
			bucketSet.add(Character.toString(bucket.charAt(i)));
		}
		
		for (int i = 0; i < candidate.length(); i++)
		{
			String letter = "";
			letter = Character.toString(candidate.charAt(i));
			
			if (bucketSet.contains(letter))
				bucketSet.remove(letter);
		}
		
		String temp = "";
		Iterator<String> iter = bucketSet.iterator();
		while (iter.hasNext())
		{
			temp += iter.next();
		}
		
		bucket = temp;
	}
**/
}
