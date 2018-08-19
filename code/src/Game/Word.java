package Game;

public class Word implements Comparable<Object> {
	String word;
	int lengthWord;
	
	public Word(String word,int lengthWord)
	{
		this.lengthWord = lengthWord;
		this.word = word;
	}

	public String toString() {
		return word;
	}

	@Override
	public int compareTo(Object object) {
		if ((this.word).length() == (((Word) object).word).length())
			return 0;
		else
		{
			if ((this.word).length() < (((Word) object).word).length())
				return -1;
			else
				return 1;	
		}
	}
}
