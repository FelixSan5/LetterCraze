/**
 * LevelPlayerView.java
 * 
 * @author Scandium
 */
package scandium.lettercraze.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import scandium.lettercraze.model.Model;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;


/**
 * The view for the Level Player screen.
 */
public class LevelPlayerView extends JPanel {
	private static final long serialVersionUID = 2542897782615081218L;
	private Model model;
	private JLabel levelNameLabel;
	private JPanel infoPanel;
	private Box buttonsBox;
	private JButton leaveButton;
	private JButton resetButton;
	private JButton undoButton;
	private Box starBox;
	private JLabel starOneLabel;
	private JLabel starTwoLabel;
	private JLabel starThreeLabel;
	private Box scoreBox;
	private JLabel scoreLabel;
	private JLabel scoreValueLabel;
	private Box nextStarBox;
	private JLabel nextStarLabel;
	private JLabel nextStarValueLabel;
	private Box maxNumWordsBox;
	private JLabel maxNumWordsLabel;
	private JLabel maxNumWordsValueLabel;
	private JScrollPane foundWordsScrollPane;
	private JLabel foundWordsLabel;
	private JList<String> foundWordsList;
	private DefaultListModel<String> foundWordsListModel;
	private BoardView boardView;

	/**
	 * Creates a new view for the Level Player screen.
	 * 
	 * @param model
	 *            The model.
	 */
	public LevelPlayerView(Model model) {
		this.model = model;

		initialize();
	}

	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @return the leaveButton
	 */
	public JButton getLeaveButton() {
		return leaveButton;
	}

	/**
	 * @return the resetButton
	 */
	public JButton getResetButton() {
		return resetButton;
	}

	/**
	 * @return the undoButton
	 */
	public JButton getUndoButton() {
		return undoButton;
	}

	/**
	 * @return the boardView
	 */
	public BoardView getBoardView() {
		return boardView;
	}

	/**
	 * @return the maxNumWordsLabel
	 */
	public JLabel getMaxNumWordsLabel() {
		return maxNumWordsLabel;
	}
	
	/** 
	 * @return the maxNumWordsValueLabel
	 */
	public JLabel getMaxNumWordsValueLabel(){
		return maxNumWordsValueLabel;
	}
	
	/**
	 * @return the timerLabel
	 */
	public JLabel getTimerLabel() {
		return null;
	}

	/**
	 * @return the themeLabel
	 */
	public JLabel getThemeLabel() {
		return null;
	}

	/**
	 * @return the levelNameLabel
	 */
	public JLabel getLevelNameLabel() {
		return levelNameLabel;
	}

	/**
	 * @return the starLabels
	 */
	public JLabel[] getStarLabels() {
		return new JLabel[] { starOneLabel, starTwoLabel, starThreeLabel };
	}

	/**
	 * @return the scoreLabel
	 */
	public JLabel getScoreLabel() {
		return scoreLabel;
	}
	
	/**
	 * @return the scoreValueLabel
	 * @return
	 */
	public JLabel getScoreValueLabel(){
		return scoreValueLabel;
	}

	/**
	 * @return the starThresholdLabel
	 */
	public JLabel getStarThresholdLabel() {
		return nextStarValueLabel;
	}

	/**
	 * @return the foundWordsLabel
	 */
	public JLabel getFoundWordsLabel() {
		return foundWordsLabel;
	}

	/**
	 * @return the foundWordsListModel
	 */
	public DefaultListModel<String> getFoundWordsListModel(){
		return foundWordsListModel;
	}
	
	/**
	 * Initializes the view.
	 */
	private void initialize() {
		setBackground(new Color(0, 191, 255));
		setBorder(new EmptyBorder(20, 20, 20, 20));
		setLayout(new BorderLayout(0, 0));

		levelNameLabel = new JLabel("Puzzled");
		levelNameLabel.setForeground(Color.BLACK);
		levelNameLabel.setFont(levelNameLabel.getFont().deriveFont(levelNameLabel.getFont().getSize() + 50f));
		add(levelNameLabel, BorderLayout.NORTH);

		infoPanel = new JPanel();
		infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setOpaque(false);

		// set up the buttons
		buttonsBox = Box.createHorizontalBox();
		buttonsBox.setBorder(new EmptyBorder(10, 10, 10, 10));
		// leave button
		leaveButton = new JButton("Leave");
		leaveButton.setFont(leaveButton.getFont().deriveFont(leaveButton.getFont().getSize() + 10f));
		buttonsBox.add(leaveButton);

		buttonsBox.add(Box.createRigidArea(new Dimension(20, 20)));
		// reset button
		resetButton = new JButton("Reset");
		resetButton.setFont(resetButton.getFont().deriveFont(resetButton.getFont().getSize() + 10f));
		buttonsBox.add(resetButton);

		buttonsBox.add(Box.createRigidArea(new Dimension(20, 20)));
		// undo button
		undoButton = new JButton("Undo");
		undoButton.setFont(undoButton.getFont().deriveFont(undoButton.getFont().getSize() + 10f));
		buttonsBox.add(undoButton);

		infoPanel.add(buttonsBox);

		// set up the stars
		starBox = Box.createHorizontalBox();
		starBox.setBorder(new EmptyBorder(7, 7, 7, 7));
		starOneLabel = new JLabel();
		starOneLabel.setIcon(
				new ImageIcon(LevelPlayerView.class.getResource("/scandium/lettercraze/resources/star-icon-off.png")));
		starBox.add(starOneLabel);

		starTwoLabel = new JLabel();
		starTwoLabel.setIcon(
				new ImageIcon(LevelPlayerView.class.getResource("/scandium/lettercraze/resources/star-icon-off.png")));
		starBox.add(starTwoLabel);

		starThreeLabel = new JLabel();
		starThreeLabel.setIcon(
				new ImageIcon(LevelPlayerView.class.getResource("/scandium/lettercraze/resources/star-icon-off.png")));
		starBox.add(starThreeLabel);
		infoPanel.add(starBox);

		// set up the score
		scoreBox = Box.createHorizontalBox();
		scoreBox.setBorder(new EmptyBorder(7, 7, 7, 7));
		// the label for the score
		scoreLabel = new JLabel("Score:");
		scoreLabel.setFont(scoreLabel.getFont().deriveFont(scoreLabel.getFont().getSize() + 15f));
		scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreBox.add(scoreLabel);

		scoreBox.add(Box.createRigidArea(new Dimension(20, 20)));
		// the value for the score
		scoreValueLabel = new JLabel("0 pts");
		scoreValueLabel.setFont(scoreValueLabel.getFont().deriveFont(scoreValueLabel.getFont().getSize() + 15f));
		scoreValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreBox.add(scoreValueLabel);
		infoPanel.add(scoreBox);

		// set up next star score
		nextStarBox = Box.createHorizontalBox();
		// the label for the next star
		nextStarLabel = new JLabel("Next Star:");
		nextStarLabel.setFont(nextStarLabel.getFont().deriveFont(nextStarLabel.getFont().getSize() + 15f));
		nextStarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		nextStarBox.add(nextStarLabel);

		nextStarBox.add(Box.createRigidArea(new Dimension(20, 20)));
		// the value for the next star
		nextStarValueLabel = new JLabel("10 pts");
		nextStarValueLabel
				.setFont(nextStarValueLabel.getFont().deriveFont(nextStarValueLabel.getFont().getSize() + 15f));
		nextStarValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		nextStarBox.add(nextStarValueLabel);

		// set up max number of words
		maxNumWordsBox = Box.createHorizontalBox();
		maxNumWordsBox.setBorder(new EmptyBorder(7, 7, 7, 7));
		// label for max number of words
		maxNumWordsLabel = new JLabel("Max Number of Words:");
		maxNumWordsLabel.setFont(maxNumWordsLabel.getFont().deriveFont(maxNumWordsLabel.getFont().getSize() + 15f));
		maxNumWordsBox.add(maxNumWordsLabel);

		maxNumWordsBox.add(Box.createRigidArea(new Dimension(20, 20)));
		// value for the max number of words
		maxNumWordsValueLabel = new JLabel("10");
		maxNumWordsValueLabel
				.setFont(maxNumWordsValueLabel.getFont().deriveFont(maxNumWordsValueLabel.getFont().getSize() + 15f));
		maxNumWordsBox.add(maxNumWordsValueLabel);
		infoPanel.add(maxNumWordsBox);

		// set up the scroll pane for found words
		foundWordsScrollPane = new JScrollPane();
		foundWordsScrollPane.setBorder(new EmptyBorder(7, 7, 7, 7));
		foundWordsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// the title for the scroll pane
		foundWordsLabel = new JLabel("Found Words");
		foundWordsLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		foundWordsLabel.setFont(foundWordsLabel.getFont().deriveFont(foundWordsLabel.getFont().getSize() + 15f));
		foundWordsScrollPane.setColumnHeaderView(foundWordsLabel);
		// the data for the scroll pane
		foundWordsList = new JList<String>();
		foundWordsList.setFont(foundWordsList.getFont().deriveFont(foundWordsList.getFont().getSize() + 10f));
		foundWordsListModel = new DefaultListModel<String>();
		for (String element : new String[] { }) {
			foundWordsListModel.addElement(element);
		}
		foundWordsList.setModel(foundWordsListModel);
		foundWordsScrollPane.setViewportView(foundWordsList);
		infoPanel.add(foundWordsScrollPane);
		add(infoPanel, BorderLayout.WEST);

		// add the board view
		boardView = new BoardView();
		//boardView.setBorder(new EmptyBorder(10, 10, 10, 10));
		//boardView.setPreferredSize(new Dimension(750, 750));
		add(boardView, BorderLayout.CENTER);
	}
}