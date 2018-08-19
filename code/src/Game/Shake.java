package Game;

import java.util.ArrayList;
import java.util.Random;

public class Shake {
	ArrayList<Character> array = new ArrayList<Character>();
	Random rand = new Random();
	String bucket = "";

	public void rearrange(String candidate)
	{
		bucket = "";
		
		for (int i = 0; i < candidate.length(); i++)
		{
			array.add(candidate.charAt(i));
		}

		while(!(array.isEmpty()))
		{
			int index = rand.nextInt(array.size());
			bucket += array.get(index);
			array.remove(index);
		}
	}

	public String getRBucket()
	{
		String copy = bucket;
		return copy;
	}
}
