package scandium.lettercraze.controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import scandium.common.model.Board;
import scandium.common.model.GravityDirection;
import scandium.common.model.PuzzleLevel;
import scandium.common.model.Star;
import scandium.common.tool.LetterDictionary;
import scandium.lettercraze.model.LevelProgress;
import scandium.lettercraze.model.Model;
import scandium.lettercraze.view.Application;
import scandium.lettercraze.view.LevelPlayerView;

/**
 * This class handles the opening of a level in LetterCraze. It Transitions the view from the Main 
 * Menu to the Level Player for the specific level.
 * @author Scandium
 * @date 12/5/16
 */
public class OpenLevelController extends MouseAdapter{
	
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
     * This constructor instantiates a new OpenLevelController. It accepts the LetterCraze model
     * and GUI. 
	 * @param model The entire LetterCraze Model.
	 * @param app The entire LetterCraze GUI.
	 */
    public OpenLevelController(Model model, Application app) {
        this.model = model;
        this.app = app;
        this.dictionary = new LetterDictionary();
    }

    /**
     * This function handles a mouse click on a level in the Main Menu. It transitions the 
     * view from the Main Menu to the Level Player, and loads the selected level. 
     * 
     * <p>Entry Condition: the button this controller is attached to is pressed.</p>
	 * <p>Exit Condition: the View has been set to the proper LevelPlayerView for the selected level.</p>
	 * 
	 * @param me The MouseEvent representing the user's mouse click on the level.
	 */
    
    @Override
    public void mouseClicked(MouseEvent me) {  
    	/* Initialize Fake Level */
    	Board board = new Board(true, GravityDirection.Up);
    	board.getBoardSquare(0,3).setEnabled(false);
    	board.getBoardSquare(1, 2).setEnabled(false);
    	Star[] stars = new Star[3];
    	stars[0] = new Star(1);
    	stars[1] = new Star(10);
    	stars[2] = new Star(100);
    	String name = "Test";
    	int max_num_words = 5;
    	PuzzleLevel level = new PuzzleLevel(name, board, stars, max_num_words);
    	
    	/* Store the selected level as the current level progress */
    	model.getProgress().getCurrentLevelProgress().setLevel(level);
    	/* Generate tiles */
    	regenerateBoardTiles();
    	
    	/* Refresh the View to show the new status of the model */
    	refreshView();
    	
    	/* Load the Level Name to the view */
    	app.getLevelPlayer().getLevelNameLabel().setText(level.getName());

    	/* Load Level Type Specific things */
    	String level_type = level.getType();
    	if(level_type.equals("Puzzle")) loadPuzzleLevel();
        else if(level_type.equals("Lightning")) loadLightningLevel();
        else if(level_type.equals("Theme")) loadThemeLevel();
        else System.out.println("Invalid Level Type: " + level_type);
    	
    	/* Set the view to the Level Player */
    	app.setView(app.getLevelPlayer());
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
    			else 
    				app.getLevelPlayer().getBoardView().getJLabel(i, j).setBackground(Color.BLACK);
    		}
    	}
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
     * This function loads the Puzzle Level View attributes 
     */
    void loadPuzzleLevel(){
    	PuzzleLevel level = (PuzzleLevel) model.getProgress().getCurrentLevelProgress().getLevel();
		app.getLevelPlayer().getMaxNumWordsValueLabel().setText(level.getMaxNumWords()+ "");
    }
    
    /**
     * This function loads the Lightning Level View attributes 
     */
    void loadLightningLevel(){
    	return;
    }
    
    /**
     * This function loads the Theme Level View attributes 
     */
    void loadThemeLevel(){
    	return;
    }
    
}