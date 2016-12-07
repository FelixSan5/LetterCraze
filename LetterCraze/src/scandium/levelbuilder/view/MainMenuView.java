/**
 * MainMenuView.java
 * 
 * @author Scandium
 * Date: 11/22/2016
 * Description: This class represents the main menu View/GUI. It allows the controllers to 
 * easily access all of the buttons, labels, and textfields within the window.
 */
package scandium.levelbuilder.view;

import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuView extends JPanel{
	
	/* Serial ID                                                                                 */
	private static final long serialVersionUID = -6718767832989688466L;
	
	/* ~~~~~                                                                               ~~~~~ *
	 * Class Attributes                                                                          *
	 * ~~~~~                                                                               ~~~~~ */
	JLabel title_label;
	JButton new_level_button;
	JButton edit_level_button;
	JButton delete_level_button;

	/* ~~~~~                                                                               ~~~~~ *
	 * Constructors And Initialization                                                           *
	 * ~~~~~                                                                               ~~~~~ */
	
	/**
	 * Creates a new MainMenuView object
	 */
	public MainMenuView() {
		initialize();
	}
	
	/**
	 * This function initializes the MainMenu view (GUI and Controllers)
	 */
	void initialize(){
		initializeView();
		initializeControllers();
	}
	
	/** 
	 * This function initializes the main menu gui
	 */
	void initializeView(){
		instantiateAttributes();
		initializeAttributes();
		
		/* JPanel Management                                                                     */
		setLayout(null);
		setBounds(0,0,1280,720);
	}
	
	/**
	 * This function instantiates new instances of all the objects
	 */
	void instantiateAttributes(){
		this.title_label = new JLabel("LevelBuilder: MainMenu");
		this.new_level_button = new JButton("New Level");
		this.edit_level_button = new JButton("Edit Level");
		this.delete_level_button = new JButton("Delete Level");
	}
	
	/**
	 * This function initializes the GUI widgets (attributes)
	 */
	void initializeAttributes(){
		/* Initialize Title Label                                                                */
		title_label.setBounds(10, 10, 1280, 40);
		title_label.setFont(new Font(title_label.getFont().getName(), Font.BOLD, 36));
		add(title_label);
		/* Initialize Create New Level Button                                                    */
		new_level_button.setBounds(1000, 100, 125, 50);
		add(new_level_button);
		/* Initialize Edit Level Button                                                          */
		edit_level_button.setBounds(1000, 200,  125, 50);
		add(edit_level_button);
		/* Initialize Delete Level Button                                                        */
		delete_level_button.setBounds(1000,  300,  125,  50);
		add(delete_level_button);
	}
	
	/**
	 * This function initializes the main menu controllers
	 */
	void initializeControllers(){
		//TODO
	}
	
	/* ~~~~~                                                                               ~~~~~ *
	 * Getter Methods                                                                            *
	 * ~~~~~                                                                               ~~~~~ */
	
	/**
	 * This function returns the JLabel representing the title
	 * @return JLabel
	 */
	public JLabel getTitleLabel(){
		return title_label;
	}
	
	/**
	 * This function returns the JButton that is used to create a new level. 
	 * @return JButton
	 */
	public JButton getNewLevelButton(){
		return new_level_button;
	}
	
	/**
	 * This function returns the JButton that is used to edit a selected level.
	 * @return JButton
	 */
	public JButton getEditLevelButton(){
		return edit_level_button;
	}
	
	/**
	 * This function returns the JButton that is used to delete a selected level
	 * @return JButton
	 */
	public JButton getDeleteLevelButton(){
		return delete_level_button;
	}
	
	/* ~~~~~                                                                               ~~~~~ *
	 * GUI Logic for Controllers                                                                 *
	 * ~~~~~                                                                               ~~~~~ */
	

}
