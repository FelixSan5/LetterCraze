package scandium.lettercraze.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;

import scandium.common.model.BoardSquare;
import scandium.common.model.Word;
import scandium.lettercraze.model.Model;
import scandium.lettercraze.view.Application;

/**
 * This class handles the selection of a word through the dragging of the mouse across the 
 * board squares. It shows updates the view and the model to reflect the selection of letters. 
 * @author Scandium
 * @date 12/5/2016
 */
public class WordDragController extends MouseMotionAdapter{

	/** 
	 * The entire LetterCraze model. With this, the controller has access to all entities
	 * that it may need. 
	 */
	Model model;
	/** 
	 * The entire LetterCraze GUI. With this, the controller has access to all widgets
	 * that it may need. 
	 */
    Application app;
    
    /**
     * This constructor instantiates a new WordDragController. It accepts the entire LetterCraze
     * model and the entire LetterCraze GUI.
     * @param model The entire LetterCraze model.
     * @param app The entire LetterCraze GUI.
     */
    public WordDragController(Model model, Application app) {
        this.model = model;
        this.app = app;
    }

   
    /** 
     * This function handles a mouse drag across one of the board squares in the Level Player. 
     * It updates the view and the model to reflect the addition of tile on this board square
     * to the entire selected word. 
     * 
     * <p>Entry Condition: The mouse is pressed and being dragged across the current board square. (JLabel).</p>
     * <p>Exit Condition: The current JLabel has been highlighted in the GUI and the model has been updated
     * to reflect the addition of this letter to the currently selected word. </p>
     * 
     * @param me The MouseEvent representing the user's drag across the current board square. 
     */
    @Override 
    public void mouseDragged(MouseEvent me){
    	/* Check that the game is being played */
    	if(!model.getProgress().getCurrentLevelProgress().isPlaying()) return;
    	/* Determine if there is a valid word selected */
    	Word word = model.getProgress().getCurrentLevelProgress().getLevel().getBoard().getSelectedWord();
    	if(word == null) return;
    	/* Get the location of the mouse */
    	Point point = getRelativePoint(me);
    	/* Get the component at that point */
    	Component c = app.getLevelPlayer().getBoardView().findComponentAt(point);
    	/* If the drag left the board view */
    	if(c == null || !c.getClass().toString().equals("class javax.swing.JLabel")){
    		abortSelection();
    		return;
    	}
    	/* Get the label being dragged over */
    	JLabel selected_label = (JLabel) c;
    	   	
    	/* Determine if in 'hitbox' */
    	boolean is_in_hitbox = isInHitBox(point, selected_label);
    	if(!is_in_hitbox) return;
    	
    	/* Get the Corresponding BoardSquare */
    	BoardSquare square = getLabelBoardSquare(selected_label);
    
    	/* Determine if the drag has changed squares */
    	if(isSameSquare(square))return;
    	
    	/* Determine if the Drag is a Drag to an enabled Square */
    	/* Determine if the Drag is to an already selected Square */
    	/* Determine if the Drag is to an adjacent Square */
    	if(!square.isEnabled() || isAlreadySelected(square) || !isAdjacent(square)){
    		abortSelection();
    		return;
    	}
    	
    	/* Add selected Square to the model */   	
    	model.getProgress().getCurrentLevelProgress().getLevel().getBoard().selectSquare(square);
    	
    	/* Adjust View */
    	app.getLevelPlayer().getBoardView().highlight(selected_label);
    }
    
    /**
     * This function returns a point that is the location of the JLabel being dragged over. 
     * Relative to Proper JPanels.
     * @param me The MouseEvent of the drag
     * @return Point the point of the JPanel
     */
    Point getRelativePoint(MouseEvent me){
    	Point relative_point = me.getPoint();
    	Point starting_point = me.getComponent().getLocation();
    	return new Point((int)(starting_point.getX() + relative_point.getX()), (int)(starting_point.getY() + relative_point.getY()));
    }
    
    /**
     * This function determines if the given point falls within the hitbox of the given JPanel.
     * @param point The point of the mouse being dragged
     * @param selected_label The JLabel being dragged over 
     * @return boolean Is the drag in the HitBox?
     */
    boolean isInHitBox(Point point, JLabel selected_label){
    	int width = selected_label.getWidth();
    	int height = selected_label.getHeight();
    	int x = (int)selected_label.getLocation().getX();
    	int y = (int)selected_label.getLocation().getY();
    	int xp = (int)point.getX();
    	int yp = (int)point.getY();
    	return (xp >= x + 20 && yp >= y + 20 && xp <= x + (width - 20) && yp <= y + (height - 20));
    }
    
    /**
     * This function returns the BoardSquare that is associated with the given JLabel.
     * It returns null if no square is associated with the label.
     * @return BoardSquare the label's corresponding board square.
     */
    BoardSquare getLabelBoardSquare(JLabel label){
    	int row = -1;
    	int col = -1;
    	for(int i = 0; i < 6; i++){
    		for(int j = 0; j < 6; j++){
    			if(app.getLevelPlayer().getBoardView().getJLabel(i, j).equals(label)){
    				row = i;
    				col = j;
    				break;
    			}
    		}
    	}
    	if(row == -1 || col == -1) return null;
    	return model.getProgress().getCurrentLevelProgress().getLevel().getBoard().getBoardSquare(col, row);
    }
    
    /** 
     * This function determines if the current boardSquare is the same as the last boardSquare.
     * @param square The current Board Square
     * @return boolean indicating if the current boardsquare is the same as the last boardSquare.
     */
    boolean isSameSquare(BoardSquare square){
    	Word word = model.getProgress().getCurrentLevelProgress().getLevel().getBoard().getSelectedWord();
    	BoardSquare last_square = word.getBoardSquares().get(word.getBoardSquares().size()-1);
    	return square.equals(last_square);
    }
    
    /**
     * This function determines if the given square has already been selected.
     * @param square The current Board Square
     * @return boolean indicating if the square has already been selected. 
     */
    boolean isAlreadySelected(BoardSquare square){
    	Word word = model.getProgress().getCurrentLevelProgress().getLevel().getBoard().getSelectedWord();
    	return word.getBoardSquares().contains(square);
    }
    
    /** This function determines if the given square is adjacent to the previously selected square.
     * @param square The current BoardSquare
     * @return boolean indicating if the current square is adjacent to the past square.
     */
    boolean isAdjacent(BoardSquare square){
    	Word word = model.getProgress().getCurrentLevelProgress().getLevel().getBoard().getSelectedWord();
    	/* Determine if the Drag is to an adjacent square */
    	BoardSquare last_square = word.getBoardSquares().get(word.getBoardSquares().size() - 1);
    	int x = (int)last_square.getRow();
    	int y = (int)last_square.getCol();
    	int row = square.getRow();
    	int col = square.getCol();
    	return (x==row || x==row-1 || x==row+1)&&(y==col || y==col-1 || y==col+1);
    }
    
    /**
     * This function removes the current word selection from the model and from the view.
     * This is down when the user has not released the mouse press, but the selection is
     * no longer valid.
     */
    void abortSelection(){
    	/* Un-highlight the word on the board view */
    	Word word = model.getProgress().getCurrentLevelProgress().getLevel().getBoard().getSelectedWord();
    	/* If no selection */
    	if(word == null) return;
    	for(BoardSquare bs : word.getBoardSquares()){
    		int row = bs.getRow();
    		int col = bs.getCol();
    		app.getLevelPlayer().getBoardView().getJLabel(row, col).setBackground(Color.WHITE);
    	}
    	/* Un-select the word in the model */
		model.getProgress().getCurrentLevelProgress().getLevel().getBoard().removeSelectedWord();
    }
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    