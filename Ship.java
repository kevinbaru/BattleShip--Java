package battleship;

/**
 * abstract class template for ships that will be used by sublcasses
 * 
 * @author philkyd
 * 
 */
public abstract class Ship {
	protected int bowRow; // � the row (0 to 9) which contains the bow (front) of the
				// ship.
	protected int bowColumn; // � the column (0 to 9) which contains the bow
								// (front) of the ship.
	protected int length; // � the number of squares occupied by the ship. An
							// �empty sea� location has length 1.
	protected boolean horizontal; // � true if the ship occupies a single row,
									// false otherwise.
	protected boolean[] hit; // = new boolean[4]; � an array of booleans
								// telling whether that part of the ship has
								// been hit. Only battleships use all four
								// locations; cruisers use the first three;
								// destroyers 2; submarines 1; and �empty
								// sea� either one or none.

	protected int sunkTwice;


	/**
	 * constructor initializes instance variables
	 */
	public Ship() {
		bowRow = 2;
		bowColumn = 2;
		length = getLength();
		horizontal = false;
		hit = new boolean[4];
		sunkTwice = 0;
	}

	
	/**
	 * returns the boolean array holding whether a ship has been hit
	 * @return
	 */
	public boolean[] getHit() {
		return hit;
	}


	/**
	 * sets the boolean array holding whether a ship has been hit
	 * @param hit
	 */
	public void setHit(boolean[] hit) {
		this.hit = hit;
	}

	/**
	 * abstract method which will return lengths by subclasses
	 * 
	 * @return
	 */
	public abstract int getLength();

	// � Originally: Returns the length of this particular ship. This method
	// exists only to be overridden, so it doesn�t much matter what it
	// returns; an abstract �ship� doesn�t have a fixed length.
	// Error in the design. No points will be taken off if you implemented the
	// original design.

	// But ideally you want that method to be abstract and the overridden in
	// every class.<--- did it this way, simpler

	/**
	 * returns the bow row
	 * 
	 * @return
	 */
	int getBowRow() {
		// � Returns bowRow
		return bowRow;
	}

	/**
	 * returns the bow column
	 * 
	 * @return
	 */
	int getBowColumn() {
		return bowColumn;
		// � Returns bowColumn
	}

	/**
	 * returns horizontal
	 * 
	 * @return
	 */
	boolean isHorizontal() {
		return horizontal;
		// � Returns horizontal
	}

	/**
	 * sets the bow row
	 * 
	 * @param row
	 */
	void setBowRow(int row) {
		bowRow = row;
		// � Sets the value of bowRow
	}

	/**
	 * sets the bow column
	 * 
	 * @param column
	 */
	void setBowColumn(int column) {
		bowColumn = column;
		// � Sets the value of bowColumn
	}

	/**
	 * sets horizontal
	 * 
	 * @param horizontal
	 */
	void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
		// � Sets the value of the instance variable horizontal.
	}

	/**
	 * abstract method which will return a string describing the ship in the
	 * subclasses
	 * 
	 * @return
	 */
	abstract String getShipType();

	// This is an abstract method, so obviously it has no body.

	/**
	 * takes the row, column, horizontal and ocean values
	 * 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 *            and returns a boolean that is true when it is okay to place
	 *            the ship and false otherwise
	 * @return The method disallows placement next to or diagonally in contact
	 *         with any ship
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		boolean holder = true;
		int checkColumn = column;
		int checkRow = row;
		// i from 0 to make =5 for battleship
		for (int i = 0; i <= this.length + 1; i++) {
			// if horizontal is false/ship faces up
			if (!horizontal) {
				// y sets column left and right of each row [0,1,2]
				for (int y = 0; y < 3; y++) {
					/*
					 * checks if anything is occuping the given row or column
					 * and checks if column is out of bounds example if a
					 * battleship is placed at 4,7 it occupies 4,7; 5,7; 6,7;
					 * 7,7 therefore the following loops through: i = 0, y = 0:
					 * 3,6 and 6 | i=0, y = 1; 3,7 and 6; i=0, y=2: 3,8 and 2 i
					 * = 1, y = 0: 4,6 and 6 | i=0, y = 1; 4,7 and 6; i=0, y=2:
					 * 3,8 and 3 i = 2, y = 0: 5,6 and 6 | i=0, y = 1; 5,7 and
					 * 6; i=0, y=2: 3,8 and 4 i = 3, y = 0: 6,6 and 6 | i=0, y =
					 * 1; 6,7 and 6; i=0, y=2: 3,8 and 5 i = 4, y = 0: 7,6 and 6
					 * | i=0, y = 1; 7,7 and 6; i=0, y=2: 3,8 and 6 i = 5, y =
					 * 0: 8,6 and 6 | i=0, y = 1; 8,7 and 6; i=0, y=2: 3,8 and 7
					 */
					if ((row - 2) + i > 9) {
						holder = false;
					}

					// makes sure doesn't check for out of bounds on the left
					// and right/top and bottom
					if ((row - 1) + i >= 0 && row - 1 + i <= 9
							&& column - 1 + y >= 0 && column - 1 + y <= 9) {
						// goes through the adjacent row or columns and
						// diagonals
						if (ocean.isOccupied((row - 1 + i), (column - 1 + y))) {
							holder = false;
						}
					}
				}

			}
			// if horizontal is true, ship faces left
			if (horizontal) {
				// makes sure doesn't check for out of bounds on the left and
				// right
				// y sets row up and down
				for (int y = 0; y < 3; y++) {
					if ((column - 2 + i) > 9) {
						holder = false;
					}
					// for 4,9 and a destroyer where a battleship is already in
					// 5,5 5,6 5,7 5, 9.
					// destroyer on 4,1 traces: 3,0|1|2|; 4,0|1|2, 5,0|1|2
					if (column - 1 + i >= 0 && column - 1 + i <= 9
							&& row - 1 + y >= 0 && row - 1 + y <= 9) {
						// checks if anything is occuping the given row or
						// column and checks if the row is out of bonds
						// if column = 9 and destroyer, i=0 through3 therefore
						// outof bounds will hold fo column-2+i = 7,8,9,10
						if (ocean.isOccupied((row - 1 + y), (column - 1 + i))) {
							holder = false;
						}
					}

				}
			}
		}
		//checks bounds
		if (checkColumn > 9 || checkRow > 9) {
			holder = false;
		}
		return holder;

	}

	// not if horizontal is false, the ship faces up, if false the ship faces
	// left, therefore if the row,int = 5,5 and horizontal is false
	// and the ship length =4 (Battleship) it will fill up 5,5; 6,5; 7,5 , 8, 5

	/*
	 * Returns true if it is okay to put a ship of this length with its bow in
	 * this location, with the given orientation, and returns false otherwise.
	 * The ship must not overlap another ship, or touch another ship
	 * (vertically, horizontally, or diagonally), and it must not �stick
	 * out� beyond the array. Does not actually change either the ship or the
	 * Ocean, just says whether it is legal to do so.
	 */

	/**
	 * takes in the following parameters to set a ship and then place it in the ocean
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// sets bow row and column to given input
		this.bowRow = row;
		this.bowColumn = column;
		// sets horizontal to given input
		this.horizontal = horizontal;
		// creates loop so indices of the ship array can be assigned to hold the
		// ship object
		for (int i = 0; i < this.length; i++) {
			// if horizontal is true/ship is facing left
			if (horizontal) {
				// reassigns the row/column to the ship
				ocean.getShipArray()[row][column + i] = this;
			}
			// if horizontal is false/ship is facing up
			if (!horizontal) {
				ocean.getShipArray()[row + i][column] = this;
			}
		}
	}

	/*
	 * �Puts� the ship in the ocean. This involves giving values to the
	 * bowRow, bowColumn, and horizontal instance variables in the ship, and it
	 * also involves putting a reference to the ship in each of 1 or more
	 * locations (up to 4) in the ships array in the Ocean object. (Note: This
	 * will be as many as four identical references; you can�t refer to a
	 * �part� of a ship, only to the whole ship.)
	 */

	/**
	 * uses the row and column of a ship to shoot at it
	 * @param row
	 * @param column
	 * @return
	 * returns true or false whether the ship has been hit, this method
	 * also changes the boolean array holding the parts of the ship
	 */
	boolean shootAt(int row, int column) {

		/*
		 * If a part of the ship occupies the given row and column, and the ship
		 * hasn't been sunk, mark that part of the ship as hit (in the hit
		 * array, 0 indicates the bow) and return true, otherwise return false.
		 */
		// if isSunk is false
		if (!this.isSunk()) {
			// checks if horizontal is true/faces left
			if (this.horizontal) {
				// loops from 0 to the length of the ship-1
				for (int i = 0; i < this.getLength(); i++) {
					// checks if the bow column and row are equal to the input
					// and then records a hit for square
					// and saves it to the hit area
					if (this.getBowColumn() + i == column
							&& this.getBowRow() == row) {
						this.hit[i] = true;

						// System.out.println(1==2);
						return true;
					}
				}
				return false;
			} else {
				// repeats above for when ship faces up
				for (int i = 0; i < this.getLength(); i++) {
					if (this.getBowColumn() == column
							&& this.getBowRow() + i == row) {
						this.hit[i] = true;
						return true;
					}
				}

				return false;
			}
		}
		return false;
	}

	/**
	 * determines whether a ship has been sunk, returns true if it has
	 * works by checking the boolean array if all parts of the ship have been hit
	 * @return
	 */
	boolean isSunk() {
		// Return true if every part of the ship has been hit, false otherwise.
		for (int i = 0; i < this.getLength(); i++) {
			if (this.hit[i] != true) {
				return false;
			}

		}

		return true;
	}
	/**
	 * returns the type of ship
	 * @return
	 */
	Ship getShipKind() {
		return this;
	}

	/**
	 * overrides the toString to output whether a ship has been sunken or hit
	 */
	@Override
	public String toString() {
		if (isSunk()) {
			return "x";
		} else {
			return "S";
		}
		/*
		 * Returns a single-character String to use in the Ocean�s print
		 * method (see below). This method should return �x� if the ship has
		 * been sunk, �S� if it has not been sunk. This method can be used
		 * to print out locations in the ocean that have been shot at; it should
		 * not be used to print locations that have not been shot at. Since
		 * toString
		 */
	}
}
