package Game;

import java.util.ArrayList;
import java.util.Collections;

public class Choice {
	private int choice;
	private boolean result;
	private ArrayList<String> possibleWordsArray;
	private ArrayList<Word> words;
	
	public Choice()
	{
		choice = 0;
		result = false;
		possibleWordsArray = new ArrayList<String>();	
		words = new ArrayList<Word>();
	}

	public void ComputeChoice(String bucket)
	{
		ReadDict dictionary = new ReadDict();
		dictionary.readWords();
		
		// scan the dictionary and compare letters
		for (int i = 0; i < dictionary.sizeDict(); i++)
		{		
			String word = dictionary.getWord(i);
			
			CompareStrings compareChar = new CompareStrings();
			compareChar.compare(word, bucket);
			result = compareChar.isMatched();
			
			if (result)
			{
				possibleWordsArray.add(word);
				choice += 1;		
			}
		}
	}
	
	public int getChoice()
	{
		return choice;
	}
	
	public ArrayList<String> getList()
	{
		return possibleWordsArray;
	}
	
	// find the longest word currently in the bucket
	public void findLongest()
	{
		for (int i = 0; i < possibleWordsArray.size(); i++)
		{
			String word = possibleWordsArray.get(i);
			int length = word.length();
			words.add(new Word(word,length));
		}
		
		Collections.sort(words);
	}
	
	public String getLongest()
	{
		String longest = "";
		
		if (words.size() == 0)
		{
			return "";	
		}
		else
		{
			longest = words.get(words.size() - 1).toString();
			return longest;
		}
	}
}