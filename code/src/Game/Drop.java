package Game;

import java.util.ArrayList;
import java.util.Random;

public class Drop
{
	private int place;
	private Random rand;
	private ArrayList<Integer> places;
	private ArrayList<Integer> temp;
	private int count;
	
	public Drop()
	{
		count = 0;
		place = 0;
		rand = new Random ();
		temp = new ArrayList<Integer>();
		places = new ArrayList<Integer>();
	}
	
	public void ComputePlaces()
	{
		int set[] = {250,300,350,400,450,500,550,600,650,700};
		
		for (int i = 0; i < 10; i ++)
		{
			for (int j = 0; j < 11; j++)
			{
				temp.add(set[i]);
			}
		}
		
		// shake places
		while(!(temp.isEmpty()))
		{
			int index = rand.nextInt(temp.size());
			places.add(temp.get(index));
			temp.remove(index);
		}
	}
	
	public int getPlace()
	{
		int area = places.get(count); 
		count++;
		return area;
	}
}
