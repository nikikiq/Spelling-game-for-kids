package Game;

import java.util.ArrayList;
import java.util.TimerTask;

public class RGLTask extends TimerTask {
	private ArrayList<String> letterArray; 
	private String newLetter;
	private String preview;
	private String tmp;
	private int index;
	
	public RGLTask()
	{	
		letterArray = new ArrayList<String>();
		newLetter = "";
		preview = "";
		tmp = "";
		index = 0;

    	// random letter generator
    	RGL generator = new RGL();
    	// generate letters
    	generator.generate();
    	// get letters
    	letterArray = generator.getArray();
	}

	@Override
	public void run() 
	{
		newLetter = letterArray.get(index);
		preview += letterArray.get(index + 1);  
		preview += letterArray.get(index + 2);
		index++;
	}
	
	public String getNewLetter()
	{
		return newLetter;
	}

	public String getPreview()
	{
		if (preview.equals(""))
		{
			return tmp;
		}
		else
		{
			tmp = preview;
			preview = "";
			return tmp;
		}
	}
	
	public int getTimerCount()
	{
		return index;
	}
}
