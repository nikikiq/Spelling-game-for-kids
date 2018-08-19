package Game;
import java.util.ArrayList;
import java.util.Random;

public class RGL {
	private ArrayList<String> letterArray; 
	private Random generator;
	private String letter;
	private int count;
	private String preview;

	public RGL()
	{
		generator = new Random();
		letterArray = new ArrayList<String>();
	}
	
	public void generate()
	{
		for (int i = 0; i < 1000; i++)
		{
			int random = generator.nextInt(100);
			
			if (random >= 88 && random <= 99)
				letter = "e";
			if (random >= 79 && random <= 87)
				letter = "s";
			if (random >= 71 && random <= 78)
				letter = "a";
			if (random >= 64 && random <= 70)
				letter = "i";
			if (random >= 57 && random <= 63)
				letter = "r";
			if (random >= 51 && random <= 56)
				letter = "o";
			if (random >= 45 && random <= 50)
				letter = "n";
			if (random >= 39 && random <= 44)
				letter = "t";
			if (random >= 34 && random <= 38)
				letter = "l";
			if (random >= 30 && random <= 33)
				letter = "d";
			if (random >= 26 && random <= 29)
				letter = "u";
			if (random >= 23 && random <= 25)
				letter = "c";
			if (random >= 20 && random <= 22)
				letter = "g";
			if (random >= 17 && random <= 19)
				letter = "p";
			if (random >= 14 && random <= 16)
				letter = "m";
			if (random >= 12 && random <= 13)
				letter = "h";
			if (random >= 10 && random <= 11)
				letter = "b";
			if (random >= 8 && random <= 9)
				letter = "y";
			if (random == 7)
				letter = "f";
			if (random == 6)
				letter = "k";
			if (random == 5)
				letter = "w";
			if (random == 4)
				letter = "v";
			if (random == 3)
				letter = "z";
			if (random == 2)
				letter = "x";
			if (random == 1)
				letter = "j";
			if (random == 0)
				letter = "q";
			
			letterArray.add(letter);
		}
	}
	
	public ArrayList<String> getArray()
	{
		return letterArray;
	}
	
	public String getLetter()
	{
		letter = letterArray.get(count);
		preview += letterArray.get(count + 1);  
		preview += letterArray.get(count + 2);   
		count++;
		return letter;
	}
	
	public String getPreview()
	{
		String tmp = preview;
		preview = "";
		return tmp;
	}
}
