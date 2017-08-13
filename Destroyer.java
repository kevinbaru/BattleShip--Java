package battleship;

public class Destroyer extends Ship {
	/**
	 * destroyer class
	 */
	public Destroyer() {
		
	}

	/**
	 * overridse the ship type to return a string naming the type of ship
	 */
	@Override
	String getShipType() {
		return "destroyer";
	}
	/**
	 * overrides the length to show the destroyer length
	 */
	@Override
	public int getLength() {
		return 2;
	}



}
