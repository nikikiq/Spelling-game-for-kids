package Game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ShakeTester {
	@Test
	public void shakeTest()
	{
		Shake test = new Shake();
		
		test.rearrange("aa");
		assertTrue(test.getRBucket().length() == 2);
		assertTrue(test.getRBucket().equals("aa"));
		/*test the function shake
		 * ensure this function will not change the elements in current
		 * bucket and hence the number of letters in current bucket will
		 * not be changed
		 * for instance, if the current bucket has elements "aa"
		 * even if we shake the bucket, the number of letters and the new 
		 * bucket will still be 2 and "aa" */

		
		test.rearrange("ab");
		assertTrue(test.getRBucket().length() == 2);
		assertTrue(test.getRBucket().equals("ab") || test.getRBucket().equals("ba"));
		/*but if in the current bucket we have "ab", obviously the possible new 
		 * bucket after shaking will be "ab" or "ba" and the number of letters in
		 * current bucket will keep same*/
		
		
		test.rearrange("abcdefghijklmnopqrstuvwxyz");
		assertTrue(test.getRBucket().length() == 26);
		assertTrue(!test.getRBucket().equals("abcdefghijklmnopqrstuvwxyz"));
		/*for game, the situation will be more complex so here we use all 26 letters 
		 * to test the function shake
		 * we need to ensure after shaking, the elements in bucket will not change 
		 * and hence the amount of letters will remain but we need to guarantee the 
		 * new bucket after shaking should be different from the old one or keep the 
		 * possibility of that the new bucket we get is as same as the old one low 
		 * enough  */
		for (int i = 0; i < 10000; i++)
		{
			String previous = "";
			test.rearrange("abcdefghijklmnopqrstuvwxyz");
			assertTrue(test.getRBucket().length() == 26);
			previous = test.getRBucket();
			
			test.rearrange("abcdefghijklmnopqrstuvwxyz");
			assertTrue(test.getRBucket().length() == 26);
			assertFalse(test.getRBucket().equals(previous));
			previous = "";
		}
		/*we shake the bucket which has all 26 letters for 10000 times and 
		 * found no same bucket after shaking so that we can ensure a low 
		 * possibility of getting the same bucket after shaking*/
	}
}
