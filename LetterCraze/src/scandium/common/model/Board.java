/**
 * Board.java
 * 
 * @author Scandium
 */
package scandium.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import scandium.common.tool.LetterDictionary;

/**
 * A play area container for tiles, which is made out of board squares.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Board {
	@XmlElementWrapper(name = "rows")
	@XmlElement(name = "row")
    private BoardSquare[][] squares;
	@XmlTransient
	private Word selectedWord;
	@XmlElement
	private GravityDirection gravityDirection;
	@XmlElement
	private boolean shouldRegenerate;

	/**
	 * Creates a new Board with the default information.
	 * Precondition: None.
	 * Postcondition: The board is created with default information.
	 */
	public Board() {
		this(true, GravityDirection.Up);
	}

	/**
	 * Creates a new Board with the given information.
	 * Precondition: None.
	 * Postcondition: The board is created with the given information.
	 */
	public Board(boolean shouldRegenerate, GravityDirection gravityDirection) {
		this.shouldRegenerate = shouldRegenerate;
		this.gravityDirection = gravityDirection;
		this.squares = new BoardSquare[6][6];

		// create all of the squares
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 6; col++) {
				squares[row][col] = new BoardSquare(row, col, this, true);
			}
		}
	}
	
	/**
	 * Gets the square at the given position. 
	 * Precondition: The indicated position is a valid one. 
	 * Postcondition: The indicated square on the board is returned.
	 * 
	 * @param row
	 *            The row of the square.
	 * @param col
	 *            The column of the square.
	 * @return The indicated square.
	 * @throws IndexOutOfBoundsException
	 *             Thrown if the given row or column is out of bounds.
	 */

	public BoardSquare getSquare(int row, int col) throws IndexOutOfBoundsException {
		return squares[row][col];
	}

	/**
	 * Precondition: None.
	 * Postcondition: The selected word has been returned without removing it.
	 * @return the selectedWord, or null if no selection has been made.
	 */
	public Word getSelectedWord() {
		return this.selectedWord;
	}

	/**
	 * @return the gravityDirection
	 */
	public GravityDirection getGravityDirection() {
		return this.gravityDirection;
	}
	
	/**
	 * Sets the gravity direction for the board.
	 * @param direction The direction to change it to.
	 */
	public void setGravityDirection(GravityDirection direction) {
		this.gravityDirection = direction;
	}


	/**
	 * @return the whether tiles shouldRegenerate
	 */
	public boolean shouldRegenerate() {
		return shouldRegenerate;
	}

	/**
	 * Insert a Word into the Board, shifting tiles in the direction opposite to
	 * gravity.
	 * Precondition: The word is not null and the word consists of board squares on this board.
	 * Postcondition: Any tiles in the given word are inserted into the board, and any tiles that 
	 * need to be moved for this insert were moved in the direction opposite to gravity.
	 * 
	 * @param word
	 *            The Word to insert.
	 * @return Whether any tiles were inserted.
	 * @exception IllegalArgumentException Thrown if the word consists of boards squares which are
	 * not in this board.
	 */
	public boolean insertWord(Word word) throws IllegalArgumentException {
		// TODO implement here
		return false;
	}

	/**
	 * Removes the selected Word from the Board.
	 * Precondition: None.
	 * Postcondition: The selected word is removed from the board if it exists and the
	 * selected word is deselected.
	 * 
	 * @return Whether a word was removed.
	 */
	public boolean removeSelectedWord() {
		this.selectedWord = null;
		return (this.selectedWord == null);
	}

	/**
	 * Apply gravity to the board, moving Tiles to fill empty squares in the
	 * direction of gravity.
	 * Precondition: None.
	 * Postcondition: All tiles will be moved in the direction of gravity to fill
	 * empty squares if there are any. The board will have the same number of tiles
	 * and the same values after applying gravity.
	 * 
	 * @return Whether any tiles were moved.
	 */
	public boolean applyGravity() {
		/* indicator of tile movement */
		boolean moved_tiles = false;
		/* Apply Upwards Gravity */
		if(getGravityDirection() == GravityDirection.Up){
			for(int row = 0; row < 6; row++){
				boardSquareLoop:
				for(int col = 0; col < 6; col++){
					/* Get the current Square */
					BoardSquare this_square = squares[row][col];
					/* Determine if it needs to be filled */
					if(this_square.isEnabled() && this_square.isEmpty()){
						/* Determine if the lower squares have a tile to fill it with */
						for(int i = row + 1; i < 6; i++){
							BoardSquare possible_filler = squares[i][col];
							if(possible_filler.isEnabled() && !possible_filler.isEmpty()){
								/* Transfer Tile contents */
								Tile t = possible_filler.getTile();
								possible_filler.setTile(null);
								this_square.setTile(t);
								/* Continue to the next board Square in BoardQuareLoop */
								continue boardSquareLoop;
							}
						}
					}
				}
			}
		}else System.out.println("Have not yet implemented other gravity directions");
		
		return moved_tiles;
		
		/*
		boolean didItWork = false;
		if(this.getGravityDirection() == GravityDirection.Up){
	        int indicator = 0;
	        for (int i = 0; i < squares.length; i++){
	        	for (int j = 0; j < squares.length; j++){
	        		if ((squares[i-1][j].getTile() == null) && (squares[i-1][j].isEnabled() == true) && (squares[i][j].getTile() == null) && (squares[i][j].isEnabled() == true)){
	        			//^^^^^This logic probably doesn't work
	        			//move shit from one to the next above
	        			squares[i-1][j].setTile(squares[i][j].getTile());
	        			squares[i][j].setTile(null);
	        			indicator = 1;
	        		}
	        	}
	        }
	        if (indicator == 1){
	        	didItWork = true;
	        }else{
	        	didItWork = false;
	        }
		}
		if(this.getGravityDirection() == GravityDirection.Right && didItWork == false){
	        int indicator = 0;
	        for (int i = 0; i < squares.length; i++){
	        	for (int j = 0; j < squares.length; j++){
	        		if ((squares[i][j+1].getTile() == null) && (squares[i][j+1].isEnabled() == true) && (squares[i][j].getTile() == null) && (squares[i][j].isEnabled() == true)){
	        			//^^^^^This logic probably doesn't work
	        			//move shit from one to the next right one
	        			squares[i][j+1].setTile(squares[i][j].getTile());
	        			squares[i][j].setTile(null);
	        			indicator = 1;
	        		}
	        	}
	        }
	        if (indicator == 1){
	        	didItWork = true;
	        }else{
	        	didItWork = false;
	        }
		}
		if(this.getGravityDirection() == GravityDirection.Down && didItWork == false){
	        int indicator = 0;
	        for (int i = 0; i < squares.length; i++){
	        	for (int j = 0; j < squares.length; j++){
	        		if ((squares[i+1][j].getTile() == null) && (squares[i+1][j].isEnabled() == true) && (squares[i][j].getTile() == null) && (squares[i][j].isEnabled() == true)){
	        			//^^^^^This logic probably doesn't work
	        			//move shit from one to the next below
	        			squares[i+1][j].setTile(squares[i][j].getTile());
	        			squares[i][j].setTile(null);
	        			indicator = 1;
	        		}
	        	}
	        }
	        if (indicator == 1){
	        	didItWork = true;
	        }else{
	        	didItWork = false;
	        }
		}
		if(this.getGravityDirection() == GravityDirection.Left && didItWork == false){
	        int indicator = 0;
	        for (int i = 0; i < squares.length; i++){
	        	for (int j = 0; j < squares.length; j++){
	        		if ((squares[i][j-1].getTile() == null) && (squares[i][j-1].isEnabled() == true) && (squares[i][j].getTile() == null) && (squares[i][j].isEnabled() == true)){
	        			//^^^^^This logic probably doesn't work
	        			//move shit from one to the next left one
	        			squares[i][j-1].setTile(squares[i][j].getTile());
	        			squares[i][j].setTile(null);
	        			indicator = 1;
	        		}
	        	}
	        }
	        if (indicator == 1){
	        	didItWork = true;
	        }else{
	        	didItWork = false;
	        }
		}
		
		return didItWork;
		*/
	}
	
	/**
	 * Fills empty squares on the Board with random tiles from the given
	 * dictionary. 
	 * Precondition: The dictionary is not null. 
	 * Postcondition: All empty squares on the board have been populated with a new tile
	 * while existing squares are not changed.
	 * 
	 * @param dictionary
	 *            The dictionary used to generate tiles.
	 * @return Whether any empty squares were filled.
	 */
	public boolean fillEmptySquares(LetterDictionary dictionary) {
        int indicator = 0;
        for (int i = 0; i < squares.length; i++){
        	for (int j = 0; j < squares.length; j++){
        		if ((squares[i][j].getTile() == null) && (squares[i][j].isEnabled() == true)){
        			squares[i][j].setTile(dictionary.getRandomTile());
        			indicator = 1;
        		}
        	}
        }
        if (indicator == 1){
        	return true;
        }else{
        	return false;
        }
    }

	/**
	 * Clears the tiles from the board.
	 * Precondition: None.
	 * Postcondition: All tiles on the board have been removed.
	 * 
	 * @return Whether any of the tiles were removed.
	 */
	public boolean clearExistingTiles() {
		int indicator = 0;
        for (int i = 0; i < squares.length; i++){
        	for (int j = 0; j < squares.length; j++){
        		if (squares[i][j].getTile() != null){
        			squares[i][j].setTile(null);
        			indicator = 1;
        		}
        	}
        }
        if (indicator == 1){
        	return true;
        }else{
        	return false;
        }
	}

	/**
	 * Selects a new square on the board.
	 * Precondition: The given board square is on this board.
	 * Postcondition: The indicated square will be added to the selected word if the selection is
	 * valid (e.g. the selection is consecutive, not repeating, and it contains a tile).
	 * 
	 * @param square
	 *            The square to select.
	 * @return Whether the square was selected.
	 * @throws IllegalArgumentException
	 *             Thrown if the given square is not in this board.
	 */
	public boolean selectSquare(BoardSquare square) throws IllegalArgumentException {
		if (selectedWord == null) return false;
		if (selectedWord.addSelectedBoardSquare(square)){
    		return true;
    	}else{
    		return false;
    	}

	}

	/**
	 * Selects a new square on the board. 
	 * Precondition: The given position exists on the board.
	 * Postcondition: The indicated square will be added to the selected word if the selection is
	 * valid (e.g. the selection is consecutive, not repeating, and it contains a tile).
	 * 
	 * @param row
	 *            The row that the square is in.
	 * @param col
	 *            The column that the square is in.
	 * @return Whether the square was selected.
	 * @throws IndexOutOfBoundsException
	 *             Thrown if the given row or column is out of bounds.
	 */
	public boolean selectSquare(int row, int col) throws IndexOutOfBoundsException {
	BoardSquare bs = getBoardSquare(row, col);

        if (selectedWord.addSelectedBoardSquare(bs)){
        	return true;
        }else{
        	return false;
        }
	}


	/**
	 * Deselects the selected word.
	 * Precondition: None.
	 * Postcondition: Any word that is currently selected will be deselected.
	 * 
	 * @return Whether any word was deselected.
	 */
	public boolean deselectWord() {
		if (selectedWord != null){
			selectedWord = null;
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Determines whether the Board is valid.
	 * 
	 * @return Whether the Board is valid.
	 */
	public boolean isValid() {
		boolean isItValid = true;
		
		if(this.squares == null){isItValid = false;}
		if(this.gravityDirection == null){isItValid = false;}
		if(getNumEnabled(this.squares) >= 9){isItValid = false;};
		
		return isItValid;
	}
	
	//helper for isValid()
	//gets the number of enabled squares
	public int getNumEnabled(BoardSquare[][] squares) {
		int indicator = 0;
        for (int i = 0; i < squares.length; i++){
        	for (int j = 0; j < squares.length; j++){
        		if (squares[i][j].isEnabled()){
        			indicator++;
        		}
        	}
        }
        return indicator;
	}
	
	/*-----Get Methods-----*/
    public BoardSquare getBoardSquare(int col, int row){
		BoardSquare bs = squares[row][col];
		return bs;
    }
    

	public boolean getShouldRegenerate(){
		return this.shouldRegenerate;
	}
	
	/*-----Set Methods-----*/
    public void setBoardSquare(int col, int row, Tile tile){
		this.squares[row][col].setTile(tile);
    }
    
	public void setSelectedWord(Word word){
		this.selectedWord = word;
	}
	
	public void setShouldRegenerate(boolean regen){
		this.shouldRegenerate = regen;
	}
	
}