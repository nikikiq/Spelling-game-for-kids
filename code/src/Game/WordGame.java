package Game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WordGame extends Application {
	Text fps = new Text(0, 30 , "");
	Text txtBucket = new Text(50, 600, "");
	Text txtInput = new Text(50, 700, "");
	Text txtNumber = new Text(700, 50, "");
	Text txtIssued = new Text(700, 120, "");
	Text txtLongest = new Text(700, 180, "");

	long lastFrame = 0;
	long frameCount = 0;
	int lastTimerCount = 0;
	int timerCount = 0;
	int issued = 0;
	int choice = 0;
	String newLetter = "";
	String preview = "";
	int score = 0;
	String longest = "";
	Timer timer;
	int place = 0;
	int addingCount = 0;

	// GUI
	private String bucket;
	private String word; // input
	private boolean isLegal;
	private boolean isValid;
	private int numBucket;
	private int lettersRemoved;
	private Set<String> inputSet;
	private Map<Integer,Letter> letterSet;
	private Map<Integer,Integer> floorSet;
	private Map<Integer,Integer> columnSet;
	private Set<Integer> keyRemove;

	public WordGame()
	{
		bucket = "";
		word = "";
		isLegal = false;
		isValid = false;
		numBucket = 0;
		lettersRemoved = 0;
		inputSet = new HashSet<String>(101);

		// GUI
		letterSet = new HashMap<Integer,Letter>();
		floorSet = new HashMap<Integer,Integer>();
		columnSet = new HashMap<Integer,Integer>();
		keyRemove = new HashSet<Integer>(101);
	}

	void updateGameState(long nowNanos) {
		if (lastFrame != 0) {
			frameCount++;
			long nanos = nowNanos - lastFrame; 

			txtBucket.setText(String.format(bucket));
			txtInput.setText(String.format("input: " + word));
			txtNumber.setText(String.format("number: " + numBucket));
			txtIssued.setText(String.format("issued: " + issued));
			txtLongest.setText(String.format("longest: " + longest));

			updateFPS(nanos);
		}
		lastFrame = nowNanos;
	}

	private void updateFPS(long elapsedNanos) {
		double elapsedSec =  elapsedNanos / 1000000000.0;
		//GUI
		// building blocks and letters 
		building(elapsedSec);

		if (frameCount % 10 == 0)
			fps.setText(String.format("%.1f", 1/elapsedSec));	
	}

	// GUI
	// build blocks and letters
	void building(double elapsed)
	{
		Set<Integer> keySet = letterSet.keySet();

		for (int key : keySet)
		{
			// detecting and moving
			letterSet.get(key).updatePosition(elapsed); 
			letterSet.get(key).detectFloor();
			letterSet.get(key).detectWallCollisions();

			// update floors
			floorSet.put(key, letterSet.get(key).getFloor());

			// indicate columns
			if (!columnSet.containsKey(key))
				columnSet.put(key, letterSet.get(key).getColumn());

			//  detect collisions
			letterSet.get(key).detectLetterCollisions(columnSet, floorSet);
		}
	}

	// find letters have been selected
	void highlightLetters()
	{
		Set<Integer> keySet = letterSet.keySet();

		for (int key : keySet)
		{
			if (inputSet.contains(letterSet.get(key).getLetter()))
			{
				if (!letterSet.get(key).hasBeenChoosed())
				{
					letterSet.get(key).changeStyle();
					inputSet.remove(letterSet.get(key).getLetter());
					System.out.println(letterSet.get(key).getLetter() + " " + "has been selected");
				}
			}
		}
		inputSet.clear();
	}

	// resetStyle
	void resetStyle()
	{
		Set<Integer> keySet = letterSet.keySet();

		for (int key : keySet)
		{
			if (letterSet.get(key).hasBeenChoosed())
			{
				letterSet.get(key).resetStyle();
				System.out.println(letterSet.get(key).getLetter() + " " + "has been changed style");
			}
		}
	}

	// remove letters
	void removeLetters()
	{
		Set<Integer> keySet = letterSet.keySet();

		for (int key : keySet)
		{
			if (letterSet.get(key).hasBeenChoosed())
			{
				letterSet.get(key).remove();
				System.out.println(letterSet.get(key).getLetter() + " " + "has been removed");
				keyRemove.add(key);
			}
		}

		for (int key : keyRemove)
		{
			columnSet.remove(key);
			floorSet.remove(key);
			letterSet.remove(key);
		}
		keyRemove.clear();
	}
	
	// reset One Style
	void resetOneStyle(String candidate)
	{
		Set<Integer> keySet = letterSet.keySet();

		loop2: for (int key : keySet)
		{
			if (letterSet.get(key).getLetter().equals(candidate))
			{
				if (letterSet.get(key).hasBeenChoosed())		
					letterSet.get(key).resetStyle();
					break loop2;
			}
		}
		candidate = "";
	}
	
	String getRealBucket()
	{
		String realBucket = "";
		Set<Integer> keySet = letterSet.keySet();

		for (int key : keySet)
		{
			realBucket += letterSet.get(key).getLetter();
		}
		return realBucket;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Frame Rate");
		final Group root = new Group();	    
		Scene scene = new Scene(root,1024,768);
		primaryStage.setScene(scene);

		//set the background
		String leafURL = this.getClass().getResource("bg.jpg").toString();
		ImageView background = new ImageView(leafURL);
		background.setFitHeight(768);
		background.setFitWidth(1024);
		root.getChildren().add(background);

		// display the frame rate
		fps.setFont(Font.font("Tahoma", FontWeight.NORMAL,40));
		fps.setOpacity(0.4);
		root.getChildren().add(fps);	

		// display the bucket
		txtBucket.setFont(Font.font("Tahoma", FontWeight.NORMAL,40));
		txtBucket.setOpacity(0.4);
		root.getChildren().add(txtBucket);	

		// display the input
		txtInput.setFont(Font.font("Tahoma", FontWeight.NORMAL,40));
		txtInput.setOpacity(0.4);
		root.getChildren().add(txtInput);	

		// display the number
		txtNumber.setFont(Font.font("Tahoma", FontWeight.NORMAL,40));
		txtNumber.setOpacity(0.4);
		root.getChildren().add(txtNumber);	

		// display issued
		txtIssued.setFont(Font.font("Tahoma", FontWeight.NORMAL,40));
		txtIssued.setOpacity(0.4);
		root.getChildren().add(txtIssued);	

		// display choice
		txtLongest.setFont(Font.font("Tahoma", FontWeight.NORMAL,40));
		txtLongest.setOpacity(0.4);
		root.getChildren().add(txtLongest);	

		// Bucket
		final Bucket letterBucket = new Bucket();

		// Rate
		timer = new Timer();
		final RGLTask task = new RGLTask();
		timer.schedule(task, 2000, 2000);  

		// Drop
		// where to drop blocks and letters
		final Drop drop = new Drop();
		drop.ComputePlaces();

		// draw edges of the bucket
		Line line = new Line();
		line.setStartX(250);
		line.setStartY(0);
		line.setEndX(250);
		line.setEndY(768);

		Line line2 = new Line();
		line2.setStartX(750);
		line2.setStartY(0);
		line2.setEndX(750);
		line2.setEndY(768);

		root.getChildren().add(line);	
		root.getChildren().add(line2);	

		// draw edges of the scene
		Line line3 = new Line();
		line3.setStartX(0);
		line3.setStartY(768);
		line3.setEndX(1024);
		line3.setEndY(768);

		Line line4 = new Line();
		line4.setStartX(1024);
		line4.setStartY(0);
		line4.setEndX(1024);
		line4.setEndY(768);

		root.getChildren().add(line3);	
		root.getChildren().add(line4);	

		// KeyEvent Handler
		scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				boolean isletter = false;
				// display the input	
				if (! keyEvent.getCharacter().equals(""))
					isletter = true;
				if (isletter)
				{
					// isChooshed
					//inputLetters.add(keyEvent.getCharacter());
					inputSet.add(keyEvent.getCharacter());

					highlightLetters();

					word = word + keyEvent.getCharacter();
					issued = lettersRemoved + numBucket;
				}
			}
		});

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode().equals(KeyCode.ENTER))
				{
					// if the player enters the letter 's' on its own
					if (word.equals("s"))
					{
						Shake shakeBucket = new Shake();
						shakeBucket.rearrange(bucket);
						bucket = shakeBucket.getRBucket();
					}
					
					System.out.println();
					System.out.println("beforeBucket: " + bucket);
					System.out.println();
					
					CompareStrings twoStrings = new CompareStrings ();
					twoStrings.compare(word, bucket);
					isValid = twoStrings.isMatched();

					// a scoring system 
					Score reward = new Score();

					if (isValid)
					{
						ReadDict searcher = new ReadDict();
						searcher.readWords();
						isLegal = searcher.isLegalWord(word);
						
						System.out.println();
						System.out.println("word(search): " + word);
						System.out.println();
					}

					if (isLegal)
					{	
						//remove letters have been selected
						removeLetters();
						
						// update the bucket
						letterBucket.updateBucket(getRealBucket());
						
						// update the display of the bucket
						bucket = letterBucket.getBucket();
						// count the number of letter have bee removed
						lettersRemoved += twoStrings.getRemoved();
						// update score
						reward.addScore(lettersRemoved); 
						score = reward.getScore(); 
						// empty longest
						longest = "";

						
						System.out.println(word + " has been removed from the bucket");
						System.out.println();
						System.out.println("RealBucket: " + bucket);
						System.out.println();
					}
					else
					{
						isLegal = false;
						// reset the style
						resetStyle();
					}
					
					word = "";
					issued = lettersRemoved + numBucket;
				}

				// delete letters
				if (keyEvent.getCode().equals(KeyCode.BACK_SPACE))
				{
					String letterDelete = "";

					if (word.length() != 0)
					{
						letterDelete = Character.toString(word.charAt(word.length() - 1));
						word =  word.substring(0, word.length() - 1);
						if (inputSet.contains(letterDelete))
							inputSet.remove(letterDelete);
						
						resetOneStyle(letterDelete);
						// highlight letters again
						highlightLetters();
					}
				}
			}
		});

		new AnimationTimer() {
			@Override
			public void handle(long now) {
				// update GameState
				updateGameState(now);
				
				//if (numBucket < 32 && (issued < 48))
				//{
				// update the bucket
				timerCount = task.getTimerCount();
				if (timerCount != lastTimerCount)
				{
					
					// get a new letter
					newLetter = task.getNewLetter();

					// add a new letter into the bucket
					letterBucket.addNewLetter(newLetter);
					
					// update the number of letters in the bucket
					numBucket = letterBucket.getNumBucket();

					// display the bucket
					bucket = letterBucket.getBucket();
					System.out.println("AnimationTimer: " + letterBucket.getBucket());

					// GUI letters
					place = drop.getPlace();
					letterSet.put(addingCount, new Letter(root, place, -50, newLetter, place));
					addingCount++;

					// display the preview
					preview = task.getPreview();

					// Choice starts
					Choice possibleChoices = new Choice ();
					possibleChoices.ComputeChoice(bucket);
					choice = possibleChoices.getChoice();
					// update the longest word currently in the bucket
					possibleChoices.findLongest();
					longest = possibleChoices.getLongest();
					// Choice ends
				
				}
				lastTimerCount = timerCount;
				//}
				/**
				else
				{
					if (issued >= 48)
					{
						System.out.println("You Win! #(* ~ *)#");
						stop();
						Platform.exit();
					}
					else
					{
						System.out.println("You Lose! &(@ ~ @)&");
						stop();
						Platform.exit();
					}
				}
				 **/

			}
		}.start();

		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
