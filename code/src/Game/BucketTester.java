package Game;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BucketTester {
  @Test
  public void BucketTest()
  {
	  Bucket test = new Bucket();
	  assertTrue(test.getBucket().equals(""));
	//check initial state
	  
	  test.addNewLetter("a");
	  assertTrue(test.getBucket().equals("a"));
	  assertTrue(test.getNumBucket() == 1);
	/*check the correctness of the function addNewLetter
	ensure that the number of added letters is one and the letter 
	added is the letter we want to add*/ 
	  
	  test.addNewLetter("h");
	  test.addNewLetter("g");
	  test.addNewLetter("b");
	  
	  assertTrue(test.getBucket().equals("ahgb"));
	  assertTrue(test.getNumBucket() == 4);
	/*check if we add several letters into the bucket the letters in new bucket
	 will appear in the correct order and the function getNumBucket can count the 
	 number of letters in the current bucket*/
	  
	  test.updateBucket("hquwe");
	  assertTrue(test.getBucket().equals("hquwe"));
	 /*check the function updateBucket will change the current bucket into a 
	   new bucket. In this test we use the given bucket "hquwe" to instead of
	   the current bucket */
	  
	  
	  
  }
}
