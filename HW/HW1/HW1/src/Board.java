/**	
 * Name: Donghun Yoo
 * SBU ID: 114000660
 * Do not import anything. You are free to add your own classes and methods.
 * Write lots of comments if you're unsure of your own implementation.
 */

public class Board {
	public static void main(String[] args) {
		/*
		 * TODO: Write a test code that demonstrates the following two scenarios A and B:
		 * A. Instantiate a Go board that has size 19x19
		 * 	1. Insert three black rocks (pieces) and two white rocks into arbitrary coordinates.
		 * 	2. getAllPieces(p) should return an array of three black pieces in any order, where 'p' is an arbitrary black rock.
		 * 	3. getAt(l) at a valid coordinate should return a corresponding rock. You should check that this method indeed returns the same rock.
		 *
		 * B. Instantiate a Chess board that has size 8x8 
		 * 	1. Insert a black king, three black pawns, two white knights, and a white bishop into any coordinates.
		 * 	2. Move a black pawn to a valid destination and check that it has indeed moved there.
		 * 	3. Move a white bishop to an invalid destination and check that the move didn't occur.
		 * 	4. List all possible destinations for one of the white knights.
		 */

		GridPlayable goBoard = new GoBoard();
		Piece black1 = new Black("black1", new Location(1,2));
		goBoard.addPiece(black1,black1.getLocation());
		Piece black2 = new Black("black2", new Location(9,16));
		goBoard.addPiece(black2,black2.getLocation());
		Piece black3 = new Black("black3", new Location(2,14));
		goBoard.addPiece(black3,black3.getLocation());

		Piece white1 = new White("white1", new Location(4,10));
		goBoard.addPiece(white1,white1.getLocation());
		Piece white2 = new White("white2", new Location(11,6));
		goBoard.addPiece(white2,white2.getLocation());

		Piece[] pieces = goBoard.getAllPieces(black1);
		for (int i = 0; i < pieces.length; i++){
			System.out.printf("(" + pieces[i].getLocation().getRow() + ", " + pieces[i].getLocation().getCol() + ")");
		} //getAllPieces(p) should return an array of three black pieces in any order, where 'p' is an arbitrary black rock.

		System.out.println();

		//getAt(l) at a valid coordinate should return a corresponding rock. You should check that this method indeed returns the same rock.
		if(white1.equals(goBoard.getAt(new Location(4,10)))){
			System.out.println(true);
		} else {
			System.out.println(false);
		}
		// find white1 using getAt and it proves two of rocks are the same rock

		GridPlayable chessBoard = new ChessBoard();
		Piece blackKing = new BlackKing("Black King", new Location(0,3));
		chessBoard.addPiece(blackKing, blackKing.getLocation());
		Piece blackPawn1 = new BlackPawn("Black Pawn1", new Location(1,0));
		chessBoard.addPiece(blackPawn1, blackPawn1.getLocation());
		Piece blackPawn2 = new BlackPawn("Black Pawn2", new Location(1,4));
		chessBoard.addPiece(blackPawn2, blackPawn2.getLocation());
		Piece blackPawn3 = new BlackPawn("Black Pawn3", new Location(1,7));
		chessBoard.addPiece(blackPawn3, blackPawn3.getLocation());

		Piece whiteKnight1 = new WhiteKnight("White Knight1", new Location(7,1));
		chessBoard.addPiece(whiteKnight1, whiteKnight1.getLocation());
		Piece whiteKnight2 = new WhiteKnight("White Knight2", new Location(7,7));
		chessBoard.addPiece(whiteKnight2, whiteKnight2.getLocation());
		Piece whiteBishop = new WhiteBishop("White Bishop", new Location(7, 2));
		chessBoard.addPiece(whiteBishop, whiteBishop.getLocation());

		System.out.println(blackPawn1.isMoveAllowed(blackPawn1.getLocation(),new Location(3,1)));
		chessBoard.moveTo(blackPawn1.getLocation(),new Location(3,1)); //Move a black pawn to a valid destination and check that it has indeed moved there.

		chessBoard.moveTo(whiteBishop.getLocation(), new Location(7,4));
		System.out.println(chessBoard.getAt(new Location(7, 4))); // Move a white bishop to an invalid destination and check that the move didn't occur.
		//null means it doesn't work.
		Location[] knightValidDestination = chessBoard.getValidDestinations(whiteKnight1, new Location(7, 1));
		for (int i = 0; i < knightValidDestination.length; i++){
			System.out.printf("(" + knightValidDestination[i].getRow() + ", " + knightValidDestination[i].getCol() + ")");
		}//List all possible destinations for one of the white knights.


	}
}

class Black extends Piece{

	public Black(String name, Location initLoc) {
		super(name, initLoc);
	}

	boolean isMoveAllowed(Location source, Location dest){
		return false;
	}
}

class White extends Piece{

	public White(String name, Location initLoc) {
		super(name, initLoc);
	}

	boolean isMoveAllowed(Location source, Location dest){
		return false;
	}
}

class BlackKing extends Piece{

	public BlackKing(String name, Location initLoc) {
		super(name, initLoc);
	}

	@Override
	boolean isMoveAllowed(Location source, Location dest) {
		boolean isValidDestination = false;
		Location[] locations = {new Location(0,1), new Location(1,1), new Location(1,0), new Location(1, -1), new Location(0, -1),
				new Location(-1,-1), new Location(-1,0), new Location(-1, 1)};
		Location sourceToDest = new Location(dest.getRow() - source.getRow(),dest.getCol() - source.getCol());
		for(int i = 0; i < locations.length; i++){
			if (locations[i].getRow() == sourceToDest.getRow() && locations[i].getCol() == sourceToDest.getCol()){
				isValidDestination = true;
			}
		}
		return isValidDestination;
	}
}

class BlackPawn extends Piece{
	public BlackPawn(String name, Location initLoc) {
		super(name, initLoc);
	}

	@Override
	boolean isMoveAllowed(Location source, Location dest) {
		boolean isValidDestination = false;
		Location[] locations = {new Location(1,0)};
		Location sourceToDest = new Location(dest.getRow() - source.getRow(),dest.getCol() - source.getCol());
		for(int i = 0; i < locations.length; i++){
			if (locations[i].getRow() == sourceToDest.getRow() && locations[i].getCol() == sourceToDest.getCol()){
				isValidDestination = true;
			}
		}
		return isValidDestination;
	}
}

class WhiteKnight extends Piece{
	public WhiteKnight(String name, Location initLoc) {
		super(name, initLoc);
	}

	@Override
	boolean isMoveAllowed(Location source, Location dest) {
		boolean isValidDestination = false;
		Location[] locations = {new Location(2,1), new Location(1,2), new Location(-1, 2), new Location(-2, 1), new Location(-2, -1),
				new Location(-1,-2), new Location(1, -2), new Location(2,-1)};
		Location sourceToDest = new Location(dest.getRow() - source.getRow(),dest.getCol() - source.getCol());
		for(int i = 0; i < locations.length; i++){
			if (locations[i].getRow() == sourceToDest.getRow() && locations[i].getCol() == sourceToDest.getCol()){
				isValidDestination = true;
			}
		}
		return isValidDestination;
	}
}

class WhiteBishop extends Piece{
	public WhiteBishop(String name, Location initLoc) {
		super(name, initLoc);
	}

	@Override
	boolean isMoveAllowed(Location source, Location dest) {
		boolean isValidDestination = false;
		Location[] locations = {new Location(1,1), new Location(2,2), new Location(3, 3), new Location(4, 4), new Location(5, 5),
				new Location(6,6), new Location(7, 7), new Location(-1,1), new Location(-2,2), new Location(-3, 3), new Location(-4, 4),
				new Location(-5, 5), new Location(-6,6), new Location(-7, 7),new Location(-1,-1), new Location(-2,-2), new Location(-3, -3),
				new Location(-4, -4), new Location(-5, -5), new Location(-6,-6), new Location(-7, -7),new Location(1,-1), new Location(2,-2),
				new Location(3, -3), new Location(4, -4), new Location(5, -5), new Location(6,-6), new Location(7, -7)};
		Location sourceToDest = new Location(dest.getRow() - source.getRow(),dest.getCol() - source.getCol());
		for(int i = 0; i < locations.length; i++){
			if (locations[i].getRow() == sourceToDest.getRow() && locations[i].getCol() == sourceToDest.getCol()){
				isValidDestination = true;
			}
		}
		return isValidDestination;
	}
}

/*
 * TODO: Implement the following two classes for a Chess and a Go board, respectively.
 * A game board is basically a 2D grid that can host multiple pieces, where duplicate pieces are allowed.
 */
class ChessBoard implements GridPlayable {
	Piece [][] chessBoard = new Piece[8][8];

	// Return the piece at location 'l'. What should it be if there's none? Or if the location is wrong?
	public Piece getAt(Location l){
		if (chessBoard[l.getRow()][l.getCol()] == null){
			return null;
		} else {
			return chessBoard[l.getRow()][l.getCol()];
		}

	}

	// Introduce a new piece 'p' into location 'l', and return true if insertion is a success, false otherwise.
	public boolean addPiece(Piece p, Location l){
		int col = l.getCol();
		int row = l.getRow();
		if(col < 8 && row < 8) {
			if(chessBoard[row][col] == null) {
				chessBoard[row][col] = p;
				return true;
			}else {
				return false;
			}
		} else {
			return false;
		}

	}

	// Remove the piece 'p' from the grid. Return a true if removal was a success, and false otherwise.
	public boolean removePiece(Piece p){
		boolean success = false;
		Location location= p.getLocation();
		chessBoard[location.getRow()][location.getCol()] = null;
		if (chessBoard[location.getRow()][location.getCol()] == null){
			success = true;
		}
		return success;
	}

	/* Move the piece at 'from' to 'to'. The return value should be the old piece at 'to' (i.e., capturing that piece).
	 * A captured piece is removed from the board.
	 * If there is no piece at 'from', then return a null with the board remaining as it is.
	 * Keep in mind that this method is only valid for movable pieces.
	 * In Go, the pieces cannot move around. What should the behavior be in this case?
	 */
	public Piece moveTo(Location from, Location to) {
		Piece piece = getAt(from);
		if (chessBoard[from.getRow()][from.getCol()] != null && piece.isMoveAllowed(from, to)) {
			chessBoard[to.getRow()][to.getCol()] = piece;
			return piece;
		}	else {
			return null;
		}

	}

	// Return the size of the board in terms of Location.
	public Location getSize(){
		int row = chessBoard.length;
		int col = chessBoard[0].length;
		Location endOfBoard = new Location(row, col);
		return endOfBoard;
	}

	/* Return all possible valid destinations for a piece 'p' at location 'l'.
	 * Restriction same as moveTo().
	 */
	public Location[] getValidDestinations(Piece p, Location from){
		int row = from.getRow();
		int col = from.getCol();
		Location[] validLocations = {};
		if (p instanceof BlackKing){
			Location forward = new Location(row + 1, col);
			Location rightForward = new Location(row + 1, col + 1);
			Location right = new Location(row, col + 1);
			Location rightBackward = new Location(row - 1, col + 1);
			Location backward = new Location(row - 1, col);
			Location leftBackward = new Location(row - 1, col - 1);
			Location left = new Location(row, col - 1);
			Location leftForward = new Location(row + 1, col - 1);
			if(0 < row && row < 7){
				if(0 < col && col <7){
                    validLocations = new Location[]{forward, rightForward, right, rightBackward, backward, leftBackward, left, leftForward};
				} else if (col == 7) {
                    validLocations = new Location[]{forward, backward, leftBackward, left, leftForward};
				} else if (col == 0) {
                    validLocations = new Location[]{forward, rightForward, right, rightBackward, backward};
				}
			} else if (row == 0) {
				if(0 < col && col <7){
                    validLocations = new Location[]{forward, rightForward, right, left, leftForward};
				} else if (col == 7) {
                    validLocations = new Location[]{forward, left, leftForward};
				} else if (col == 0) {
                    validLocations = new Location[]{forward, rightForward, right};
				}
			} else if (row == 7) {
				if(0 < col && col <7){
                    validLocations = new Location[]{right, rightBackward, backward, leftBackward, left};
				} else if (col == 7) {
                    validLocations = new Location[]{backward, leftBackward, left};
				} else if (col == 0) {
                    validLocations = new Location[]{forward, rightForward, right};
				}
			}
		} else if (p instanceof BlackPawn) {
			Location forward = new Location(row + 1, col);
			if (row < 7){
                validLocations = new Location[]{forward};
			} else {
                validLocations = new Location[]{};
			}
		} else if (p instanceof WhiteKnight) {
			Location forwardRight1 = new Location(row + 2, col + 1);
			Location forwardRight2 = new Location(row + 1, col + 2);
			Location backwardRight1 = new Location(row - 1, col + 2);
			Location backwardRight2 = new Location(row - 2, col + 1);
			Location forwardLeft1 = new Location(row - 2, col - 1);
			Location forwardLeft2 = new Location(row - 1, col - 2);
			Location backwardLeft1 = new Location(row + 1, col - 2);
			Location backwardLeft2 = new Location(row + 2, col - 1);
            validLocations = new Location[]{forwardRight1, forwardRight2, forwardLeft1, forwardLeft2, backwardRight1, backwardRight2,
                    backwardLeft1, backwardLeft2};
			Location[] locations = removeNonvalidLocation(validLocations);
			validLocations = locations;
		} else if (p instanceof WhiteBishop) {
            // Maximum number of times that can be moved diagonally in one direction * four diagonal ways = 28
            validLocations = new Location[]{new Location(row + 1, col + 1), new Location(row + 2, col + 2), new Location(row + 3, col + 3), new Location(row + 4, col + 4),
                    new Location(row + 5, col + 5), new Location(row + 6, col + 6), new Location(row + 7, col + 7),
                    new Location(row - 1, col + 1), new Location(row - 2, col + 2), new Location(row - 3, col + 3), new Location(row - 4, col + 4),
                    new Location(row - 5, col + 5), new Location(row - 6, col + 6), new Location(row - 7, col + 7),
					new Location(row - 1, col - 1), new Location(row - 2, col - 2), new Location(row - 3, col - 3), new Location(row - 4, col - 4),
					new Location(row - 5, col - 5), new Location(row - 6, col - 6), new Location(row - 7, col - 7),
					new Location(row + 1, col - 1), new Location(row + 2, col - 2), new Location(row + 3, col - 3), new Location(row + 4, col - 4),
					new Location(row + 5, col - 5), new Location(row + 6, col - 6), new Location(row + 7, col - 7)
            };
			Location[] locations = removeNonvalidLocation(validLocations);
			validLocations = locations;
		} else {
			return null;
		}
		return validLocations;
	}

	public boolean isInTheChessBoard (Location location){
		int row = location.getRow();
		int col = location.getCol();
		if (0 <= row && row < 8 && 0 <= col && col <8){
			return true;
		} else {
			return false;
		}
	}

	public Location[] removeNonvalidLocation (Location[] locations) {
		int count = 0;
		for (int i = 0; i < locations.length; i ++){
			if (isInTheChessBoard(locations[i])) {
				count ++;
			}
		}
		Location[] temporaryArray = new Location[count];
		for (int i = 0; i < locations.length; i ++){
			if (isInTheChessBoard(locations[i])) {
				temporaryArray[count - 1] = locations[i];
				count --;
			}
		}
		return temporaryArray;
	}

	// Return all pieces that are equal (i.e., have the same name) to the given piece 'p'
	public Piece[] getAllPieces(Piece p){
		int count = 0;
		if (p instanceof BlackKing){
			for (int i = 0; i < 19; i++) {
				for(int j = 0; j < 19; j++){
					if (chessBoard[i][j] instanceof BlackKing) {
						count++;
					}
				}
			}
		} else if (p instanceof BlackPawn) {
			for (int i = 0; i < 19; i++) {
				for(int j = 0; j < 19; j++){
					if (chessBoard[i][j] instanceof BlackPawn) {
						count++;
					}
				}
			}
		}else if (p instanceof WhiteKnight) {
			for (int i = 0; i < 19; i++) {
				for(int j = 0; j < 19; j++){
					if (chessBoard[i][j] instanceof WhiteKnight) {
						count++;
					}
				}
			}
		}else if (p instanceof WhiteBishop) {
			for (int i = 0; i < 19; i++) {
				for(int j = 0; j < 19; j++){
					if (chessBoard[i][j] instanceof WhiteBishop) {
						count++;
					}
				}
			}
		}
		Piece[] pieces = new Piece[count];
		if (p instanceof BlackKing){
			for (int i = 0; i < 19; i++) {
				for(int j = 0; j < 19; j++){
					if (chessBoard[i][j] instanceof BlackKing) {
						pieces[count-1] = chessBoard[i][j];
						count --;
					}
				}
			}
		} else if (p instanceof BlackPawn) {
			for (int i = 0; i < 19; i++) {
				for(int j = 0; j < 19; j++){
					if (chessBoard[i][j] instanceof BlackPawn) {
						pieces[count-1] = chessBoard[i][j];
						count --;
					}
				}
			}
		}else if (p instanceof WhiteKnight) {
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 19; j++) {
					if (chessBoard[i][j] instanceof WhiteKnight) {
						pieces[count - 1] = chessBoard[i][j];
						count--;
					}
				}
			}
		} else if (p instanceof WhiteBishop) {
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 19; j++) {
					if (chessBoard[i][j] instanceof WhiteBishop) {
						pieces[count - 1] = chessBoard[i][j];
						count--;
					}
				}
			}
		}
		return pieces;
	}
}

class GoBoard implements GridPlayable {

	Piece [][] goBoard = new Piece[19][19];

	// Return the piece at location 'l'. What should it be if there's none? Or if the location is wrong?
	public Piece getAt(Location l){
		if (goBoard[l.getRow()][l.getCol()] == null){
			return null;
		} else {
			return goBoard[l.getRow()][l.getCol()];
		}

	}

	// Introduce a new piece 'p' into location 'l', and return true if insertion is a success, false otherwise.
    public boolean addPiece(Piece p, Location l){
		int col = l.getCol();
		int row = l.getRow();
		if(col < 19 && row <19) {
			if(goBoard[row][col] == null) {
				goBoard[row][col] = p;
				return true;
			}else {
				return false;
			}
		} else {
			return false;
		}

	}

	// Remove the piece 'p' from the grid. Return a true if removal was a success, and false otherwise.
	public boolean removePiece(Piece p){
		boolean success = false;
		Location location= p.getLocation();
		goBoard[location.getRow()][location.getCol()] = null;
		if (goBoard[location.getRow()][location.getCol()] == null){
			success = true;
		}
		return success;
	}

    /* Move the piece at 'from' to 'to'. The return value should be the old piece at 'to' (i.e., capturing that piece).
     * A captured piece is removed from the board.
     * If there is no piece at 'from', then return a null with the board remaining as it is.
     * Keep in mind that this method is only valid for movable pieces.
     * In Go, the pieces cannot move around. What should the behavior be in this case?
     */
    public Piece moveTo(Location from, Location to) {
        return null;
    }

	// Return the size of the board in terms of Location.
	public Location getSize(){
		int row = goBoard.length;
		int col = goBoard[0].length;
		Location endOfBoard = new Location(row, col);
		return endOfBoard;
	}

	/* Return all possible valid destinations for a piece 'p' at location 'l'.
	 * Restriction same as moveTo().
	 */
	public Location[] getValidDestinations(Piece p, Location from){
		return null;
	}

	// Return all pieces that are equal (i.e., have the same name) to the given piece 'p'
	public Piece[] getAllPieces(Piece p){
		int count = 0;
		if (p instanceof Black){
			for (int i = 0; i < 19; i++) {
				for(int j = 0; j < 19; j++){
					if (goBoard[i][j] instanceof Black) {
						count++;
					}
				}
			}
		} else if (p instanceof White) {
			for (int i = 0; i < 19; i++) {
				for(int j = 0; j < 19; j++){
					if (goBoard[i][j] instanceof White) {
						count++;
					}
				}
			}
		}
		Piece[] pieces = new Piece[count];
		if (p instanceof Black){
			for (int i = 0; i < 19; i++) {
				for(int j = 0; j < 19; j++){
					if (goBoard[i][j] instanceof Black) {
						pieces[count-1] = goBoard[i][j];
						count --;
					}
				}
			}
		} else if (p instanceof White) {
			for (int i = 0; i < 19; i++) {
				for(int j = 0; j < 19; j++){
					if (goBoard[i][j] instanceof White) {
						pieces[count-1] = goBoard[i][j];
						count --;
					}
				}
			}
		}
		return pieces;
	}
}

/*
 * Do not modify the following classes and interface. Doing so will result in a score of 0.
 * 'row' and 'col' all follow the usual index rule in 2D arrays.
 * That is, you should consider the board to be a 2D array, such that the first row and column have index 0.
 */
class Location {
	private int row, col;
	public int getRow() { return row; }
	public int getCol() { return col; }
	public Location(int r, int c) { this.row = r; this.col = c; }
}

abstract class Piece {
	private String name;
	private Location loc;
	public Piece(String name, Location initLoc) {
		this.name = name;
		this.loc = initLoc;
	}
	
	abstract boolean isMoveAllowed(Location source, Location dest); // Can this piece move from 'source' to 'dest'?
	
	public void setLocation(Location l) { loc = l; }
	public Location getLocation() { return loc; }
}

interface GridPlayable {
	// Return the piece at location 'l'. What should it be if there's none? Or if the location is wrong?
	Piece getAt(Location l); 
	
	// Introduce a new piece 'p' into location 'l', and return true if insertion is a success, false otherwise.
	boolean addPiece(Piece p, Location l); 
	
	// Remove the piece 'p' from the grid. Return a true if removal was a success, and false otherwise.
	boolean removePiece(Piece p);
	
	/* Move the piece at 'from' to 'to'. The return value should be the old piece at 'to' (i.e., capturing that piece).
	 * A captured piece is removed from the board.
	 * If there is no piece at 'from', then return a null with the board remaining as it is.
	 * Keep in mind that this method is only valid for movable pieces. 
	 * In Go, the pieces cannot move around. What should the behavior be in this case?
	 */
    Piece moveTo(Location from, Location to);
	
	// Return the size of the board in terms of Location.
	Location getSize(); 
	
	/* Return all possible valid destinations for a piece 'p' at location 'l'.
	 * Restriction same as moveTo().
	 */
    Location[] getValidDestinations(Piece p, Location from);
	
	// Return all pieces that are equal (i.e., have the same name) to the given piece 'p'
	Piece[] getAllPieces(Piece p);
}
