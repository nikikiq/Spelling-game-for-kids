package Game;

public class ReadTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReadDict test = new ReadDict ();
		test.readWords();
		
		System.out.println(test.isLegalWord("or"));

	}

}
