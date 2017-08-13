package battleship;


import java.util.Arrays;
import java.util.Scanner;
/**
 * Battleship Game
 * @author Philip Kyd & Kevin Muriuki
 *
 */
public class BattleshipGame {
	Ocean ocean;
	
	/**
	 * method to run the battleship game
	 */
	public void playGame(){
		//welcome message and instructions
		System.out.println("Welcome to Battleship. You should attempt to sink the ships with least shots as possible.");
		System.out.println("A hit is when you hit a floating ship, "
				+ " A miss is when you hit an empty sea or sunk ship");
		System.out.println("'.' shows a location that hasn't been shot at. It may contain a ship or not");
		System.out.println("Shots are indicated by the characters shown below upon playing");
		System.out.println("       		'-' shows a miss");
		System.out.println("      		'S' shows a hit");
		System.out.println("       		'x' shows a sunken ship");
		System.out.println();
		//creates new ocean and places all the ships randomly
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		ocean.print();
		//creates scanner
		Scanner sc = new Scanner(System.in);
		//runs as long as the game is not over
		while(!ocean.isGameOver()){			
			System.out.println();					
			System.out.println("Please select a row and column that you would like to shoot at; enter it in the form of \"row,column\"");
			//asks for input from use
			String input = sc.nextLine();
			//sets input length
			
			String[] array = new String[10];
			for(int i = 0; i<10;i++){
				String inputString = Integer.toString(i);
				array[i] = inputString;
				
			}
			while(input.length()!=3 || !input.substring(1,2).contains(",") || !Arrays.asList(array).contains(input.substring(0,1)) || !Arrays.asList(array).contains(input.substring(2,3))) {
				System.out.println("Invalid input, please try again");
				input = sc.nextLine();

			}
			//separates by the ,
			String[] input2 = input.split(",");
			//saves integers from the user input as a row and column value to be used in shootAt
			int rowShoot = Integer.parseInt(input2[0]);
			int columnShoot = Integer.parseInt(input2[1]);
			//increments the hits and shoots fired accurately
			ocean.shootAt(rowShoot, columnShoot);
			//outputs messages for how many shots fired et cetera
			System.out.println("Shots Fired:"+" " +ocean.getShotsFired());
			System.out.println("Total Hits:"+" " +ocean.getHitCount());
			System.out.println("Ships Sunk:"+" " +ocean.getShipsSunk());
			//checks to make sure that a sunken ship cannot be sunken more than once
			if(ocean.getShipArray()[rowShoot][columnShoot].isSunk() && ocean.getShipArray()[rowShoot][columnShoot].sunkTwice == 0 ){
				System.out.println("Congratulations, you have sunk a " + ocean.getShipArray()[rowShoot][columnShoot].getShipType()+".");
				ocean.getShipArray()[rowShoot][columnShoot].sunkTwice = 1;
			}
			//prints ocean again for the user
			ocean.print();
		}
		//prints out end messages for when the game is over
		System.out.println();
		System.out.println("Game is Over");
		System.out.println("Congratulations! You have Sunk all the 10 ships!!!");
		System.out.println("You fired "+ocean.getShotsFired() +" shots");
		System.out.println("You had "+ocean.getHitCount() +" Hits");
		System.out.println("The best possible number of shots is 20");
		//asks if the user wants to play again
		System.out.println("Would you like to play again? Please press y for yes.");
		String again = sc.nextLine();
		//restarts game
		if(again.equals("y")){
			//ocean.resetShipsSunk();
			this.playGame();

		}

		//closes reader
		sc.close();


	}
	/**
	 * main method invoked that will start the game
	 * @param args
	 */
	public static void main(String[] args) {
		BattleshipGame game= new BattleshipGame();
		game.playGame();
	}
	
}



/*
The BattleshipGame class is the �main� class�that is, it contains a main method. In this
class you will set up the game; accept �shots� from the user; display the results; print
final scores; and ask the user if he/she wants to play again. All input/output is done here
(although some of it is done by calling a print() method in the Ocean class.) All computation
will be done in the Ocean class and the various Ship classes.
To aid the user, row numbers should be displayed along the left edge of the array, and
column numbers should be displayed along the top. Numbers should be 0 to 9, not 1 to 10.
The top left corner square should be 0, 0. Use different characters to indicate locations that
contain a hit, locations that contain a miss, and locations that have never been fired upon.
Use methods. Don�t cram everything into one or two methods, but try to divide up the
work into sensible parts with reasonable names.

 */