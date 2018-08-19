package Game;

public class DropTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Drop drop = new Drop();
		drop.ComputePlaces();
		
		for (int i = 0; i < 110; i++)
			System.out.println(drop.getPlace());
		
		System.out.println(drop.getPlace());
	}
}
