package scandium.lettercraze.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import scandium.common.model.Board;
import scandium.common.tool.LetterDictionary;
import scandium.lettercraze.model.LevelProgress;
import scandium.lettercraze.model.Model;
import scandium.lettercraze.view.Application;
import scandium.lettercraze.view.LevelPlayerView;

/**
 * This class handles the reseting of the board in a LetterCraze Game. 
 * It is initiated by a user mouse click on the reset button. For a theme level, 
 * The board is returned to its initial configuration. For a lightning Level, the
 * tiles are regenerated but the timer is not refreshed. For a puzzle level, 
 * the tiles are regenerated. 
 * @author Scandium
 * @date 12/6/2016
 */
public class ResetLevelController extends MouseAdapter{

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
     * A instance of a LetterDictionary. With this, the controller can populate the board's tiles.
     */
    LetterDictionary dictionary;


    /**
     * This constructor instantiates a new instance of the Reset level Controller.
     * @param model The entire LetterCraze Model
     * @param app The entire LetterCraze GUI
     */
    public ResetLevelController(Model model, Application app) {
        this.model = model;
        this.app = app;
        this.dictionary = new LetterDictionary();
    }

    /**
     * This function handles the user's Mouse click on the reset board button.
     * It resets the level specific fields (except for the lightning level's timer),
     * removes all found words, and creates a new auto generation of tiles (unless
     * the level is a theme level, in which the tiles are generated as before 
     * @param me The user's mouse click on the reset button
     */
    @Override
    public void mouseClicked(MouseEvent me) {
    	/* Determine how to handle the reset of the board */
        String level_type = model.getProgress().getCurrentLevelProgress().getLevel().getType();
        if(level_type.equals("Puzzle")) resetPuzzleLevel();
        else if(level_type.equals("Lightning")) resetLightningLevel();
        else if(level_type.equals("Theme")) resetThemeLevel();
        else System.out.println("Invalid Level Type: " + level_type);
    }
    
    /**
     * This function handles the reseting of the puzzle level board. It removes
     * all found words, resets the score, resets the achieved stars, and 
     * generates new tiles for all enabled board squares. 
     */
    public void resetPuzzleLevel(){
    	/* Reset the model */
    	LevelProgress current_level_progress = model.getProgress().getCurrentLevelProgress();
    	current_level_progress.getFoundWords().clear();
    	current_level_progress.setScore(0);
    	current_level_progress.setStarCount(0);
    	current_level_progress.setPlaying(true);
    	regenerateBoardTiles();
    	
    	/* Reset View */
    	refreshView();
    	
    }
    
    /**
     * 
     */
    void resetLightningLevel(){
    	
    }
    
    /** 
     * 
     */
    void resetThemeLevel(){
    	
    }
    
    
    /**
     * This function regenerates the tiles on the board
     */
    void regenerateBoardTiles(){
    	Board board = model.getProgress().getCurrentLevelProgress().getLevel().getBoard();
    	for(int i = 0; i < 6; i++){
    		for(int j = 0; j < 6; j++){
    			if(board.getBoardSquare(j, i).isEnabled())
    				board.setBoardSquare(j, i, dictionary.getRandomTile());
    		}
    	}
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