package Game;

import java.util.Scanner;

public class LongestTester {

	private static Scanner in;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int outcome = 0;
		in = new Scanner(System.in);
		System.out.print("Enter a bucket: ");
		String bucket = in.nextLine();
		
		Choice test = new Choice();
		test.ComputeChoice(bucket);
		outcome = test.getChoice();

		test.findLongest();
		String longest = test.getLongest();
		
		System.out.println("Outcome: " + outcome);
		System.out.println("Words: " + test.getList());
		System.out.println("Longest: " + longest);
	}
}
