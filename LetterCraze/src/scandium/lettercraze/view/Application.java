/**
 * Application.java
 * 
 * @author Scandium
 */
package scandium.lettercraze.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import scandium.common.tool.WordDictionary;
import scandium.lettercraze.controller.*;
import scandium.lettercraze.model.Model;

/**
 * The LetterCraze application window.
 */
public class Application extends JFrame {
	static final long serialVersionUID = -7053432922965446279L;
	private Model model;
	private MainMenuView mainMenu;
	private LevelPlayerView levelPlayer;

	/**
	 * Creates a new LetterCraze application window without a model.
	 */
	public Application() {
		initializeModel();
		initializeView();
		initializeControllers();
		setView(mainMenu);
	}

	/**
	 * Creates a new LetterCraze application window.
	 * 
	 * @param model
	 *            The model of the application.
	 */
	public Application(Model model) {
		this.model = model;

		initializeModel();
		initializeView();
		initializeControllers();
		setView(mainMenu);
	}

	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @return the mainMenu
	 */
	public MainMenuView getMainMenu() {
		return mainMenu;
	}

	/**
	 * @return the levelPlayer
	 */
	public LevelPlayerView getLevelPlayer() {
		return levelPlayer;
	}

	/**
	 * Sets the main view of the application.
	 * @param view The view to change the application to.
	 */
	public void setView(JPanel view) {
		getContentPane().removeAll();
		getContentPane().add(view);
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	/**
	 * Gets the main view of the application.
	 */
	public JPanel getView() {
		return (JPanel) getContentPane().getComponents()[0];
	}
	
	/**
	 * Shows the splash screen.
	 */
	public void showSplashScreen() {
		boolean previousIsVisible = isVisible();
		boolean previousIsResizable = isResizable();
		JPanel previousView = getView();
		JPanel splashScreen = new JPanel();
		splashScreen.add(new JLabel(new ImageIcon(LevelIconView.class.getResource("/scandium/lettercraze/resources/lettercraze-splash.gif"))));
		setView(splashScreen);
		setResizable(false);
		setVisible(true);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(previousIsVisible);
		setResizable(previousIsResizable);
		setView(previousView);
	}
	
	/**
	 * Initialize the model.
	 */
	private void initializeModel() {
		model = new Model();
	}

	/**
	 * Initialize the view.
	 */
	private void initializeView() {
		setTitle("LetterCraze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// if we can't change to look, who cares
		}
		this.mainMenu = new MainMenuView();
		this.levelPlayer = new LevelPlayerView(null);
	}

	/**
	 * Initialize the controllers.
	 */
	private void initializeControllers() {
		mainMenu.getLevelIconView(0).addMouseListener(new OpenLevelController(model,this));
		levelPlayer.getLeaveButton().addActionListener(new ExitLevelController(model,this));
		
		/* Create WordDictionary for the controllers */
		WordDictionary dictionary = new WordDictionary();
		/* Initialize controllers for the 'board squares' */
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 6; j++){
				
				levelPlayer.getBoardView().getJLabel(i, j).addMouseListener(new SelectTileController(model, this));
				levelPlayer.getBoardView().getJLabel(i, j).addMouseListener(new RemoveWordController(model, this, dictionary));
				levelPlayer.getBoardView().getJLabel(i, j).addMouseMotionListener(new WordDragController(model, this));
				
			}
		}
		levelPlayer.getResetButton().addMouseListener(new ResetLevelController(model, this));
	}
}

