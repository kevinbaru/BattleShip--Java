package battleship;

public class EmptySea extends Ship {
	/**
	 * emptySea class, holds an empty space as a ship so it can be hit etc
	 */
	public EmptySea() {
	}
	/**
	 * overridse the ship type to return a string naming the type of ship
	 */
	@Override
	String getShipType() {
		return "empty";
	}
	/**
	 * overrides shootAt to always return false
	 */
	 @Override
	boolean shootAt(int row, int column){
		 return false;
		 
	 }
	 /**
	  * overrides isSunk to always return false
	  */
	 @Override
	 boolean isSunk(){
		 return false;
	 }

	 /**
	  * sets length to 1
	  */
	@Override
	public int getLength() {

		return 1;
	}
}
