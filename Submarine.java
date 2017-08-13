package battleship;

public class Submarine extends Ship{
	/**
	 * submarine class
	 */
	public Submarine() {
	
	}
	/**
	 * overrides shiptype to return string submarine
	 */
	@Override
	String getShipType() {

		return "submarine";
	}
	/**
	 * overrides length to return 1
	 */
	@Override
	public int getLength() {
	
		return 1;
	}

}
