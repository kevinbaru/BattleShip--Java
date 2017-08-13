package battleship;

public class Cruiser extends Ship{

	/**
	 * cruiser class
	 */
	public Cruiser() {
		// TODO Auto-generated constructor stub
		
	}
	/**
	 * sets the cruiser length as 3
	 */
	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 3;
	}
	/**
	 * overrides the ship type to return a string naming the type of ship
	 */
	@Override
	String getShipType() {
		// TODO Auto-generated method stub
		return "cruiser";
	}

}
