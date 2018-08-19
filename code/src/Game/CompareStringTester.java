package Game;
import static org.junit.Assert.*;

import java.util.Scanner;
import org.junit.Test;

public class CompareStringTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		do
		{
			Scanner in = new Scanner(System.in);
			System.out.print("Enter a word: ");
			String word = in.nextLine();
			
			System.out.print("Enter bucket: ");
			String bucket = in.nextLine();
			
			CompareStrings test = new CompareStrings();
			
			test.compare(word, bucket);
			boolean outcome = test.isMatched();
			
			System.out.println(outcome);
			System.out.println(test.getBucket());
			System.out.println(test.getRemoved());

		} while ( 1 > 0);
	}
	
	@Test
	public void CompareStringTest()
	{
		CompareStrings test = new CompareStrings();
		
		test.compare("abc", "abc");
		assertTrue(test.isMatched());
		assertTrue(test.getRemoved() == 3);
		assertTrue(test.getBucket().equals(""));

		test.compare("ab", "bc");
		assertTrue(!test.isMatched());
		assertTrue(test.getRemoved() == 1);
		assertTrue(test.getBucket().equals("c"));
		
		test.compare("ab", "cd");
		assertTrue(!test.isMatched());
		assertTrue(test.getRemoved() == 0);		
		assertTrue(test.getBucket().equals("cd"));
		
		test.compare("cat", "abcdefghijklmnopqrstuvwxyz");
		assertTrue(test.isMatched());
		assertTrue(test.getRemoved() == 3);		
		assertTrue(test.getBucket().equals("bdefghijklmnopqrsuvwxyz"));
	}
}
