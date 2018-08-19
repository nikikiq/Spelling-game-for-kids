package Game;

public class RGLTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RGL tester = new RGL();
		
		for (int i = 0; i < 100;i++)
		{
			tester.generate();
			System.out.print(tester.getLetter());
			System.out.print(" ");
		}
	}
}
