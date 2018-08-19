package Game;

import java.util.Map;
import java.util.Set;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Letter extends Text{
	double vx;
	double vy;
	double speed = 80;
	String letter = "";
	boolean collide = false;
	boolean choosed = false;
	int column = 0;
	int floor = 0;

	public Letter (Group root,double vx, double vy, String letter, int column)
	{
		super(vx, vy,letter);
		this.letter = letter;
		this.vy = vy;
		this.setFont(Font.font("Tahoma", FontWeight.NORMAL,50));
		this.setFill(Color.BLACK);
		this.column = column;
		root.getChildren().add(this);
	}
	
	void updatePosition(double elapsed)
	{
		if (!collide)
			setY(getY() + (speed * elapsed));
	}
	
	void detectWallCollisions()
	{
		if (getY() >= 550)
		{
			setX(column);
			setY(550);
		}
	}
	
	void detectFloor()
	{
		if (getY() >= 50)
			floor = 1;
		
		if (getY() >= 100)
			floor = 2;
		
		if (getY() >= 150)
			floor = 3;
		
		if (getY() >= 200)
			floor = 4;
		
		if (getY() >= 250)
			floor = 5;
		
		if (getY() >= 300)
			floor = 6;
		
		if (getY() >= 350)
			floor = 7;
		
		if (getY() >= 400)
			floor = 8;
		
		if (getY() >= 450)
			floor = 9;
		
		if (getY() >= 500)
			floor = 10;
		
		if (getY() >= 550)
			floor = 11;
	}
	
	int getFloor()
	{
		return floor;
	}
	
	int getColumn()
	{
		return column;
	}
	
	void remove()
	{
		
		setY(-50);
	}
	
	void detectLetterCollisions(Map<Integer,Integer> columnSet, Map<Integer,Integer> floorSet)
	{
		Set<Integer> keySet = columnSet.keySet();

		loop: for (int key : keySet)
		{
			if (columnSet.get(key).equals(column))
			{
				if (floorSet.get(key).equals(floor + 1))
				{
					collide = true;
					
					switch(floor)
					{
					case 1:
						setX(column);
						setY(50);
						break;
					case 2:
						setX(column);
						setY(100);
						break;
					case 3:
						setX(column);
						setY(150);
						break;
					case 4:
						setX(column);
						setY(200);
						break;
					case 5:
						setX(column);
						setY(250);
						break;
					case 6:
						setX(column);
						setY(300);
						break;
					case 7:
						setX(column);
						setY(350);
						break;
					case 8:
						setX(column);
						setY(400);
						break;
					case 9:
						setX(column);
						setY(450);
						break;
					case 10:
						setX(column);
						setY(500);
						break;
					}
					break loop;
				}
			}
			else
				collide = false;
		}
	}
	
	String getLetter()
	{
		return letter;
	}
	
	// if the letter has been selected then change the style 
	void changeStyle()
	{
		choosed = true;
		this.setFill(Color.RED);
	}
	
	void resetStyle()
	{
		choosed = false;
		this.setFill(Color.BLACK);
	}
	
	boolean hasBeenChoosed()
	{
		return choosed;
	}
}