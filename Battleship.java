package battleship;

public class Battleship extends Ship {
	/**
	 * battleship class
	 */
	public Battleship() {
	}
//Each of these classes has a constructor, the purpose of which is to set the inherited length
//	variable to the correct value, and to initialize the hit array.
	
	/**
	 * overridse the ship type to return a string naming the type of ship
	 */
	@Override
	String getShipType() {
	
		return "battleship";
	}
	
	/**
	 * overrides the length to show the battleship length
	 */
	@Override
	public int getLength() {
		return 4;
	}

}
