import java.util.Random;
import java.util.Scanner;

public class Player
{
	public static final int MAX_ROUNDS = 10;
	public static final int MAX_PINS = 10;
	public static final int END_GAME = -1;

	private String name;
	private int totalScore;
	private Scanner scanner;

	public Player(String name)
	{
		this.name = name;
		this.totalScore = 0;
		scanner = new Scanner(System.in);
	}
	
	private int handleRoll(String rollNum, int pinsLeft) 
	{
		System.out.println("throwing...");
		try 
		{
			Thread.sleep(3000);
		} 
		catch (InterruptedException e) {}
		Random random = new Random();
		int pins = random.nextInt(pinsLeft + 1);
		roll(pins);	
		
		return pins;
	}

	private void roll(int pins)
	{
		System.out.println("You knocked down "+ pins +" pins !");
		totalScore +=pins;
	}

	public void play()
	{ 
		boolean strike = false , spare = false;
		System.out.println("Hello "+ this.name +"! let's play KBowl");

		for(int i=1 ; i<=MAX_ROUNDS ; i++)
		{ 
			System.out.println("Round " + i + "/10");
			
			int pinsFirstRoll = handleRoll("first",10);
			
			// spare or strike from prev round , need to add the first roll count to score
			if(spare || strike)
			{
				totalScore += pinsFirstRoll;
			}

			if(pinsFirstRoll != MAX_PINS)
			{
				int pinsSecondRoll = handleRoll("second",10-pinsFirstRoll);

				// strike from prev round , need to add the second roll count to score
				if(strike)
				{
					totalScore += pinsSecondRoll;
				}

				if(pinsFirstRoll + pinsSecondRoll == MAX_PINS)
				{
					System.out.println("SPARE !!!");
					spare = true;
				}
				else
				{
					strike = spare = false;
				}
			}

			else
			{
				System.out.println("STRIKE !!!");
				strike = true;
			}

			System.out.println("your score is: " + totalScore);
			
			if(spare || strike)
			{
				System.out.println("note, this score not including yet the bonus from the next round");
			}
			System.out.println();
			
			if(i==10 || !coutinuePlaying()) return;
		}

		// if last round was spare or strike, there is 1or 2 bonus roll
		if(spare || strike)
		{
			int pinsFirstRoll = handleBonusRoll("bonus first", 10);
			if(strike && pinsFirstRoll != MAX_PINS)
			{
				handleBonusRoll("bonus second", pinsFirstRoll);
			}
		}
		
	}
	
	private int handleBonusRoll(String rollName, int pinsLeft) 
	{
		System.out.println("You got a bonus roll !");
		int pinsBonusRoll = handleRoll(rollName, pinsLeft);
		return pinsBonusRoll;
	}

	public int score()
	{
		return totalScore;
	}
	
	private boolean coutinuePlaying() 
	{
		System.out.println("Continue to next round (y/n) ?");
		String input = scanner.nextLine();
		while(input.charAt(0) != 'y' && input.charAt(0) != 'n') 
		{
			System.out.println("please choose y or n");
			input = scanner.nextLine();
		}

		return (input.charAt(0) == 'y') ? true : false;
	}
}