package Game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ReadDict 
{
	private ArrayList<String> legalWords;
	private Set<String> WordsSet;
	private boolean result;
	
	public ReadDict()
	{
		legalWords = new ArrayList<String>();
		WordsSet = new HashSet<String>(80369);
		result = false;
	}
	
    public void readWords()
    {
    	String FilePath = "dictionary.txt";
        FileReader fr = null;
        BufferedReader br = null;
        
        try
        {
            try
            {
                fr = new FileReader(FilePath);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            br = new BufferedReader(fr);
            String Line = br.readLine();
      
            while (Line != null)
            {
            	legalWords.add(Line);
            	WordsSet.add(Line);
                Line = br.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public String getWord(int i)
    {
    	String word = legalWords.get(i); 
    	return word;  
    }
    
    public int sizeDict()
    {
    	int size = legalWords.size(); 
    	return size;
    }
    
    public boolean isLegalWord(String candidate) {
    	result = legalWords.contains(candidate);
    	return result;
	}
}