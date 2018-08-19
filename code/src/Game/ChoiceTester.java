package Game;


import java.util.Scanner;

public class ChoiceTester {
	public static void main(String[] args) {

			int outcome = 0;
			Scanner in = new Scanner(System.in);
			System.out.print("Enter a bucket: ");
			String bucket = in.nextLine();
			
			Choice test = new Choice();
			test.ComputeChoice(bucket);
			
			outcome = test.getChoice();
			
			System.out.println(outcome);
			System.out.println(test.getList());
	}
}
