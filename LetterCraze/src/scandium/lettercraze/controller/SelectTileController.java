package scandium.lettercraze.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import scandium.common.model.BoardSquare;
import scandium.common.model.Word;
import scandium.lettercraze.model.Model;
import scandium.lettercraze.view.Application;

/**
 * This class handles the beginning of the user's word selection in LetterCraze. It uses 
 * a mouse press event to determine which tile starts the currently selected word. 
 * @author Scandium
 * @date 12/5/16
 */
public class SelectTileController extends MouseAdapter{
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
     * This constructor instantiates a new SelectTileController. It accepts the LetterCraze model
     * and the LetterCraze GUI.
     * @param model The entire LetterCraze model.
     * @param app The entire LetterCraze GUI.
     */
    public SelectTileController(Model model, Application app) {
        this.model = model;
        this.app = app;
    }

    /**
     * This function handles a mouse press on one of the board Squares in the Level Player. 
     * This signals the start of a word selection by the user. It creates a new word entity in
     * the LetterCraze Model and highlights the selected board square in the GUI.
     * 
     * <p>Entry Condition: The user generates a mouse press on a board square</p>
     * <p>Exit Condition: The board square has been selected and a new word has been started</p>
     * 
     * @param me The mouse event for this controller
     */
    @Override
    public void mousePressed(MouseEvent me){
    	/* Check that the game is being played */
    	if(!model.getProgress().getCurrentLevelProgress().isPlaying()) return;
    	/* AdjustView */
    	JLabel label = (JLabel) me.getComponent();
    	BoardSquare square = getLabelBoardSquare(label);
    	/* Check that the board square is enabled */
    	if(!square.isEnabled()) return;
    	/* Highlight the square in the view */
    	app.getLevelPlayer().getBoardView().highlight(label);
    	/* Register the new Word in the model */
    	Word word = new Word(square);
    	model.getProgress().getCurrentLevelProgress().getLevel().getBoard().setSelectedWord(word);
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
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    