package Game;

public class Score {
	private int score;
	
	public Score()
	{
		score = 0;
	}
	
	public void addScore(int score)
	{
		this.score += score;
	}
	
	public int getScore()
	{
		return score;
	}
}
