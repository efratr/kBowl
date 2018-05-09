import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		System.out.println("Welcome to KBowl !");
		System.out.println("Please enter your name: ");
	    Scanner scanner = new Scanner( System.in );
	    String name = scanner.nextLine();
	    Player player = new Player(name);
	    player.play();
	    System.out.println("Game Over!");
	    int score = player.score();
	    System.out.println("Your score is: "+ score);
	    scanner.close();
	}

}
