package battleship;

import java.util.Random;
/**
 * Ocean class where all ships will be placed
 * @author philkyd
 *
 */
public class Ocean {
	private Ship[][] ships = new Ship[10][10]; //� Used to quickly determine which ship is in any given
	//location.
	private int shotsFired;  // � The total number of shots fired by the user.
	private int hitCount; // � The number of times a shot hit a ship. If the user shoots the same part
	//of a ship more than once, every hit is counted, even though the additional �hits� don�t
	//do the user any good.
	//ships to be placed
	private Battleship newBattleship;
	private Cruiser newCruiser1, newCruiser2;
	private Destroyer newDestroyer1,newDestroyer2,newDestroyer3;
	private Submarine submarine1, submarine2, submarine3, submarine4;
	//an array holding sips that is used to place ships
	Ship[] shipsArray = new Ship[10];
	private int shipsSunk;  // � The number of ships sunk (10 ships in all).

	/**
	 * Ocean constructor, initiates instance variables and places emptySeas to set teh ocean
	 */
	public Ocean() {
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
		for(int i = 0; i<10; i++){
			for(int y = 0; y<10; y++){
				ships[i][y] = new EmptySea();
			}
		}
		// TODO Auto-generated constructor stub
		//The constructor. Creates an �empty� ocean (fills the ships array with EmptySeas).
		//Also initializes any game variables, such as how many shots have been fired.
	}
	
	/**
	 * method which places all of the ships randomly in the ocean
	 */
	void placeAllShipsRandomly(){
		//declares the ships
		newBattleship = new Battleship();
		newCruiser1 = new Cruiser();
		newCruiser2 = new Cruiser();
		newDestroyer1 = new Destroyer();
		newDestroyer2 = new Destroyer();
		newDestroyer3 = new Destroyer();
		submarine1 = new Submarine();
		submarine2 = new Submarine();
		submarine3 = new Submarine();
		submarine4 = new Submarine();
		
		//creates new array holding all of the ship objects that will be placed
		Ship[] shipsArray = {newBattleship,newCruiser1,newCruiser2,newDestroyer1,newDestroyer2,
				newDestroyer3,submarine1,submarine2,submarine3,submarine4};
		//initiates loop that goes through the ship array
		for(Ship kind:shipsArray){
			//generates random numbers to place the ships
			Random rand= new Random();
			int row = rand.nextInt(10);
			int column = rand.nextInt(10);
			//genrates random boolean to place the ships
			boolean horizontal= rand.nextBoolean();
			//makes sure that okToPlaceShipAt is met with the randomly generated row, column, horizontal, if these aren't it generates another set
			while(!kind.okToPlaceShipAt(row,column,horizontal,this)){
				row=(int)(Math.random()*10);
				column=(int)(Math.random()*10);
				horizontal=rand.nextBoolean();

			}	
			//places ship in the ocean if the above is met
			kind.placeShipAt(row, column, horizontal, this);
		}

	}

	/**
	 * checks via row and column if a square [row,column] contains a ship and returns true or false
	 * @param row
	 * @param column
	 * @return
	 */
	boolean isOccupied(int row, int column){
		//checks if the row and column [square] is an instance of the class EmptySea
		if(this.ships[row][column].getShipType() == "empty"){
			//returns false if there is an EmptySea and therefore is not occupied
			return false;
		}
		else{
			return true;
		}
		//Returns true if the given location contains a ship, false if it does not.
	}
	

	/**
	 * uses a row and column to return true or false if something has been hit
	 * @param row
	 * @param column
	 * @return
	 * this method also increments statistics (shots fired, hits, ships that have been sunk)
	 */
	boolean shootAt(int row, int column){
		//increments shots fired
		this.shotsFired++;
		//checks if a square is occupied and not sunk
		if(this.isOccupied(row, column) && !this.ships[row][column].isSunk()){
			//increments the hit count
			this.hitCount++;
			//calls the shootAt method from the ship class
			this.ships[row][column].shootAt(row, column);
			//increments the number of ships that are sunk if a ship is sunk
			if(this.ships[row][column].isSunk()){
				this.shipsSunk = shipsSunk +1;
			}
			return true;
		}
		else{
			//changes the hit to true for empty sea
			this.ships[row][column].getHit()[0] = true;
			//states an emptySea as being hit
			return false;
		}
	}
	/**
	 * returns the number of shots fired
	 * @return
	 */
	int getShotsFired(){
		return shotsFired;
	}
	/**
	 * returns the hitcount
	 * @return
	 */
	int getHitCount(){
		return hitCount;
	}
	/**
	 * returns if a ship is sunk
	 * @return
	 */
	int getShipsSunk(){
		return shipsSunk;
	}
	/**
	 * this method resets the number of ships sunk to 0
	 * @return
	 */
	int resetShipsSunk(){
		return shipsSunk = 0;
	}
	/**
	 * method checks if all ships are sunk and returns true 
	 * @return
	 */
	boolean isGameOver(){
		//checks if the total number of ships sunk = the total number of ships
		if(this.shipsSunk == 10){
			return true;
		}
		return false;
	}
	
	/**
	 * returns the ships array holding the ships in the ocean
	 * @return
	 */
	Ship[][] getShipArray(){
		return this.ships;
	}
	
	/**
	 * print method that prints out the ocean using special characters to represent hit, ships hit, ships sunk, empty spaces hit
	 */
	void print(){
		//Prints the column numbers
		System.out.print("\t");
		for (int k=0;k<9;k++)
			System.out.print(k+"\t");
		System.out.println(9);
		for (int i=0;i<10;i++){
			System.out.println("\n");//skips a line
			System.out.print(i);//print the column numbers
			System.out.print("\t");//print the tab spaces

			for (int j=0;j<10;j++){
				//print the ships
				if(ships[i][j].getShipType().equals("empty")){
					if(ships[i][j].getHit()[0]){
						System.out.print("-"+"\t");	// shot empty sea										
					}
					else{
						System.out.print("."+"\t");//empty sea
					}

				}
				else{
					//0 through 1 below the length of the ship
					for (int y = 0; y < ships[i][j].getLength(); y++){
						//if horizontal is true/faces left
						if(ships[i][j].horizontal){
							//if the ships column = the input square column+distance and if the ships row = the input square row
							if (ships[i][j].getBowColumn() +y == j && ships[i][j].getBowRow()==i){
								if(ships[i][j].getHit()[0+y]){
									System.out.print(ships[i][j].toString()+"\t");
								}
								else{
									System.out.print("."+"\t");
								}
							}
						}
						if(!ships[i][j].horizontal){
							//if the ships column = the input square column+distance and if the ships row = the input square row
							if (ships[i][j].getBowColumn() == j && ships[i][j].getBowRow()+y==i){
								if(ships[i][j].getHit()[0+y]){
									System.out.print(ships[i][j].toString()+"\t");
								}
								else{
									System.out.print("."+"\t");
								}
							}
						}
					}

				}
			}
		}

	}
}
