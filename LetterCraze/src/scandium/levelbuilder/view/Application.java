/**
 * Application.java
 * 
 * @author Scandium
 * Date: 11/22/2016
 * Description: This class represents the level builder Application (View/GUI) It stores the
 * Main Menu and Level Editor views.
 */
package scandium.levelbuilder.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import scandium.levelbuilder.model.Model;

public class Application extends JFrame{

	/* Serial ID                                                                                 */
	private static final long serialVersionUID = -7300085623783459664L;
	
	/* Game Window Width and Height                                                              */
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;

	/* ~~~~~                                                                               ~~~~~ *
	 * Class Attributes                                                                          *
	 * ~~~~~                                                                               ~~~~~ */
	Model model;
	MainMenuView main_menu;
	LevelEditorView level_editor;
	
	/* ~~~~~                                                                               ~~~~~ *
	 * Constructors and Initialization                                                           *
	 * ~~~~~                                                                               ~~~~~ */
	
	/**
	 * Creates a new LevelBuilder application window without a model.
	 */
	public Application(){
		initialize();
	}
	
	/**
	 * Creates a new LetterCraze application window with a model.
	 * @param model The LevelBuilder Model
	 */
	public Application(Model model){
		this.model = model;
		initialize();
	}
	
	/**
	 * Initializes the LevelBuilder View and Controllers
	 */
	void initialize(){
		this.main_menu = new MainMenuView();
		this.level_editor = new LevelEditorView();
		/* Add both panels to the view                                                           */ 
		getContentPane().add(main_menu);
		getContentPane().add(level_editor);
		/* Set both to invisible                                                                 */
		main_menu.setVisible(true);
		level_editor.setVisible(false);
		/* Maintenance of JFrame                                                                 */
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setVisible(true);
		setSize(WIDTH, HEIGHT);
		pack();
	}
	
	/* ~~~~~                                                                               ~~~~~ *
	 * Getter Methods                                                                            *
	 * ~~~~~                                                                               ~~~~~ */
	
	/**
	 * This function returns the MainMenuView for LevelBuilder
	 * @return MainMenuView
	 */
	public MainMenuView getMainMenu(){
		return main_menu;
	}
	
	/**
	 * This function returns the LevelEditorView for LevelBuilder
	 * @return LevelEditorView
	 */
	public LevelEditorView getLevelEditor(){
		return level_editor;
	}
	
	/* ~~~~~                                                                               ~~~~~ *
	 * GUI Logic for Controllers                                                                 *
	 * ~~~~~                                                                               ~~~~~ */
	
	/**
	 * This function sets the application view to display the Main Menu View
	 */
	public void setViewMainMenu(){
		level_editor.setVisible(false);
		main_menu.setVisible(true);
	}
	
	/** 
	 * This function set the application view to display the Level Editor
	 */
	public void setViewLevelEditor(){
		main_menu.setVisible(false);
		level_editor.setVisible(true);
	}

	
}