package scandium.lettercraze.controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import scandium.common.model.Board;
import scandium.common.model.BoardSquare;
import scandium.common.model.PuzzleLevel;
import scandium.common.model.Star;
import scandium.common.model.Word;
import scandium.common.tool.LetterDictionary;
import scandium.common.tool.WordDictionary;
import scandium.lettercraze.model.LevelProgress;
import scandium.lettercraze.model.Model;
import scandium.lettercraze.view.Application;
import scandium.lettercraze.view.LevelPlayerView;

/**
 * This class handles the removal of a word from the board in LetterCraze. It is initiated 
 * when the user releases their pressed mouse. If the selected word is valid, it is removed
 * from the board and new tiles are inserted to fill its place (dependent on level type). 
 * Both the model and the GUI are updated to reflect these changes. 
 * @author Scandium
 * @date 12/5/2016
 */
public class RemoveWordController extends MouseAdapter{

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
     * The dictionary of possible words for puzzle and lightning levels. With this, the 
     * controller has the ability to determine if a word is valid. 
     */
    WordDictionary dictionary;
    /**
     * The Letter Dictionary of all letters and their respective frequencies and score. 
     * With this the controller has the ability to refill non-populated tiles.
     */
    LetterDictionary letter_dictionary;

    /**
     * This constructor instantiates a new RemoveWordController. It accepts the LetterCraze 
     * model, the LetterCraze GUI, and a word dictionary.
     * @param model The Entire LetterCraze model.
     * @param app The Entire LetterCraze GUI
     * @param dictionary A word dictionary
     */
    public RemoveWordController(Model model, Application app, WordDictionary dictionary) {
        this.model = model;
        this.app = app;
        this.dictionary = dictionary;
        this.letter_dictionary = new LetterDictionary();
    }

    /**
     * This function handles a mouse release on one of the board square in the Level Player. 
     * It either removes the tiles from the board (if a valid word) or it simply unselects
     * them. Both the view and the model are updated to reflect this change. 
     * 
     * <p> Entry Condition: The mouse is released on a board square </p>
     * <p> Exit Condition: LetterCraze has been updated to reflect the changes. </p>
     * 
     * @param me The mouse event for this controller
     */
    @Override
    public void mouseReleased(MouseEvent me){
    	/* Check that the game is being played */
    	if(!model.getProgress().getCurrentLevelProgress().isPlaying()) return;
    	/* Get Word: return if no valid word*/
    	Word word = model.getProgress().getCurrentLevelProgress().getLevel().getBoard().getSelectedWord();
    	if (word == null) return;
    	
    	/* Remove highlight */
    	removeHighlight(word);
    	
    	/* Determine how to handle the removal of a word */
        String level_type = model.getProgress().getCurrentLevelProgress().getLevel().getType();
        if(level_type.equals("Puzzle")) removeFromPuzzleLevel(word);
        else if(level_type.equals("Lightning")) removeFromLightningLevel(word);
        else if(level_type.equals("Theme")) removeFromThemeLevel(word);
        else System.out.println("Invalid Level Type: " + level_type);	
    	
    }
    
    
    /**
     * This function removes the highlight from all selected squares
     * @param word The selected word
     */
    void removeHighlight(Word word){
    	for(BoardSquare bs : word.getBoardSquares()){
    		int row = bs.getRow();
    		int col = bs.getCol();
    		app.getLevelPlayer().getBoardView().getJLabel(row, col).setBackground(Color.WHITE);
    	}
    }
    
    /**
     * This function removes the given word from the board
     * @param word The word to be removed from the board
     */
    void removeWord(Word word){
		for(BoardSquare bs : word.getBoardSquares()){
			bs.setTile(null);
		}    	
    }
    
    /** 
     * This function determines if a new star was achieved and updates the model if it was.
     */
    void achieveStars(){
    	int score = model.getProgress().getCurrentLevelProgress().getScore();
    	int star_count = 0;
    	for(Star star : model.getProgress().getCurrentLevelProgress().getLevel().getStars()){
    		if(star.isObtained(score)) star_count++;
    	}
    	model.getProgress().getCurrentLevelProgress().setStarCount(star_count);
    }
    
    /** 
     * This function determines if the maximum number of words have been found for a puzzle level.
     */
    void foundMaxNumWords(){
		if(model.getProgress().getCurrentLevelProgress().getLevel().getType().equals("Puzzle")){
			int num_words = model.getProgress().getCurrentLevelProgress().getFoundWords().size();
			PuzzleLevel level = (PuzzleLevel) model.getProgress().getCurrentLevelProgress().getLevel();
			if(num_words >= level.getMaxNumWords()){
				model.getProgress().getCurrentLevelProgress().setPlaying(false);
			}
		}
    }
    
    /**
     * This function handles the removal of a word from a puzzle level. 
     * @param word The word to be removed
     */
    public void removeFromPuzzleLevel(Word word){
       	
        if(!(dictionary.isWord(word.generateString()) && word.getBoardSquares().size() >= 3)){
        	model.getProgress().getCurrentLevelProgress().getLevel().getBoard().setSelectedWord(null);
        	return;
        }
        
        /* Remove word from board and model*/
        removeWord(word);
		model.getProgress().getCurrentLevelProgress().getLevel().getBoard().setSelectedWord(null);

    	/* Apply gravity and generate tiles */
    	Board board = model.getProgress().getCurrentLevelProgress().getLevel().getBoard();
    	board.applyGravity();
    	board.fillEmptySquares(letter_dictionary);
    	
    	/* Add the Score to the level Progress */
    	int score = model.getProgress().getCurrentLevelProgress().getScore();
    	score += word.calculateScore();
    	model.getProgress().getCurrentLevelProgress().setScore(score);
    	/* Add the word to the list of found words */
    	
    	model.getProgress().getCurrentLevelProgress().addFoundWord(word.generateString());
    	app.getLevelPlayer().getFoundWordsListModel().addElement(word.generateString());

    		
    	/* Determine if a star was achieved */
    	achieveStars();
    	

    	/* Check if the maximum number of words has been reached */
    	foundMaxNumWords();
    		
    	/* Refresh the view to reflect the changes to the model */
    	refreshView();
    	
    }
    
    /**
     * 
     */
    void removeFromLightningLevel(Word word){
    	
    }
    
    /** 
     * 
     */
    void removeFromThemeLevel(Word word){
    	
    }
    
    /** 
     * This function refreshes the view from the current state of the model
     */
    void refreshView(){
    	LevelProgress CLP = model.getProgress().getCurrentLevelProgress();
    	LevelPlayerView  level_player = app.getLevelPlayer();
    	/* Remove all found words */
    	level_player.getFoundWordsListModel().clear();
    	/* Load found words from model */
    	for(String word : CLP.getFoundWords()){
    		level_player.getFoundWordsListModel().addElement(word);
    	}
    	/* Refresh Score */
    	level_player.getScoreValueLabel().setText(CLP.getScore() + "");
    	/* Refresh Stars */
    	for(JLabel star : level_player.getStarLabels()){
    		star.setIcon(new ImageIcon(LevelPlayerView.class.getResource(
    				"/scandium/lettercraze/resources/star-icon-off.png")));
    	}
    	for(int i = 0; i < 3 && i < CLP.getStarCount(); i++){
    		level_player.getStarLabels()[i].setIcon(new ImageIcon(LevelPlayerView.class.getResource(
    				"/scandium/lettercraze/resources/star-icon-on.png")));
    	}
    	/* Refresh Board View */
    	Board board = model.getProgress().getCurrentLevelProgress().getLevel().getBoard();
    	for(int i = 0; i < 6; i++){
    		for(int j = 0; j < 6; j++){
    			if(board.getBoardSquare(j, i).isEnabled())
    				app.getLevelPlayer().getBoardView().getJLabel(i, j).setText(board.getBoardSquare(j, i).getTile().getContent());
    		}
    	}
    }
   

}