package Game;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScoreTester {
	@Test
	public void Tester()
	{
		Score test = new Score ();
		assertTrue(test.getScore() == 0);
		/*check the initial state score = 0 */
		
		test.addScore(11);
		assertTrue(test.getScore() == 11);
		/* check the function of addScore which will calculate the sum 
		  of all scores the player has already got */
		
		test.addScore(-7);
		assertTrue(test.getScore() == 4);
		/* check the function of addScore which will calculate the sum 
		  of all scores the player has already got */
		
		
	}

}
