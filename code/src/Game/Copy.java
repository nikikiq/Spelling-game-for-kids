package Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Copy extends Application {
	static final long Billion = 2000000000;
	long lastFrame = 0;
	
	private String bucket;
	private String word; // input
	private boolean isLegal;
	private boolean isValid;
	private int numBucket;
	private int lettersRemoved;
	private String newLetter;
	private int issued;
	private int choice;
	private String preview;
	private int score;
	private String longest;

	public Copy()
	{
		bucket = "";
		word = "";
		newLetter = "";
		isLegal = false;
		isValid = false;
		numBucket = 0;
		lettersRemoved = 0;
		issued = 0;
		choice = 0;
		preview = "";
		score = 0;
		longest = "";
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		// Bucket
		final Bucket bin = new Bucket();
		
    	// random letter generator
    	RGL generator = new RGL();
    	// generate letters
    	generator.generate();
		
		// KeyEvent Handler
		scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				System.out.println("the number: " + numBucket + " issued: " + issued + " preview: "+ preview +" choice: " + choice + " score: " + score + " longest: " + longest);
				System.out.println("bucket: " + bucket);
				System.out.println("input: " + word);
				System.out.println();
				
				// display the input
				boolean isletter = Character.isLetter(keyEvent.getCharacter().charAt(0));
				if (isletter)
				{
					word = word + keyEvent.getCharacter();
					issued = lettersRemoved + numBucket;
				}
				System.out.println("the number: " + numBucket + " issued: " + issued + " preview: "+ preview +" choice: " + choice + " score: " + score + " longest: " + longest);
				System.out.println("bucket: " + bucket);
				System.out.println("input: " + word);
				System.out.println();
			}
		});
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				System.out.println("the number: " + numBucket + " issued: " + issued + " preview: "+ preview +" choice: " + choice + " score: " + score + " longest: " + longest);
				System.out.println("bucket: " + bucket);
				System.out.println("input: " + word);
				System.out.println();
				
				if (keyEvent.getCode() == KeyCode.ENTER)
				{
					// if the player enters the letter 's' on its own
					if (word.equals("s"))
					{
						Shake shakeBucket = new Shake();
						shakeBucket.rearrange(bucket);
						bucket = shakeBucket.getRBucket();
					}
					
					CompareStrings twoStrings = new CompareStrings ();
					twoStrings.compare(word, bucket);
					isValid = twoStrings.isMatched();
					String fakebucket = twoStrings.getBucket();
					
					// a scoring system 
					Score reward = new Score();

					if (isValid)
					{
						ReadDict searcher = new ReadDict();
						searcher.readWords();
						isLegal = searcher.isLegalWord(word);
					}
					if (isLegal)
					{	
						// update the bucket
						bin.updateBucket(fakebucket);
						// update the display of the bucket
						bucket = fakebucket;
						// count the number of letter have bee removed
						lettersRemoved += twoStrings.getRemoved();
						// update score
						reward.addScore(lettersRemoved); 
						score = reward.getScore(); 
						// empty longest
						longest = "";
					}
					word = "";
					issued = lettersRemoved + numBucket;
				}
				
				System.out.println("the number: " + numBucket + " issued: " + issued + " preview: "+ preview +" choice: " + choice + " score: " + score + " longest: " + longest);
				System.out.println("bucket: " + bucket);
				System.out.println("input: " + word);
				System.out.println();
			}
		});
	
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				/**
				if (now - lastFrame > Billion) 
				{
					
					if (numBucket < 32 && (issued < 48))
					{
						lastFrame = now;
						
						// get a new letter
						newLetter = generator.getLetter();
						
						// add a new letter into the bucket
						bin.addNewLetter(newLetter);
						
						// update the number of letters in the bucket
						numBucket = bin.getNumBucket();
						
						// display the bucket
						bucket = bin.getBucket();
						
						// preview
						preview = generator.getPreview();
						
						// Choice starts
						Choice possibleChoices = new Choice ();
						possibleChoices.ComputeChoice(bucket);
						choice = possibleChoices.getChoice();
						// update the longest word currently in the bucket
						possibleChoices.findLongest();
						longest = possibleChoices.getLongest();
						// Choice ends
					}
					else
					{
						if (issued >= 48)
						{
							System.out.println("You Win! #(* ~ *)#");
							Platform.exit();
							stop();
						}
						else
						{
							System.out.println("You Lose! &(@ ~ @)&");
							Platform.exit();
							stop();
						}
					}
					
					System.out.println("the number: " + numBucket + " issued: " + issued + " preview: "+ preview +" choice: " + choice + " score: " + score + " longest: " + longest);
					System.out.println("bucket: " + bucket);
					System.out.println("input: " + word);
					System.out.println();
				}
				**/
				
			}
		}.start();	
		primaryStage.show();
		System.out.println("Game Start! ~(^_^)~");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}