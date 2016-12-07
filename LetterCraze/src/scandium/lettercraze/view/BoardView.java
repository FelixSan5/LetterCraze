/**
 * BoardView.java
 * 
 * @author Scandium
 */
package scandium.lettercraze.view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * 
 */
public class BoardView extends JPanel {
	private static final long serialVersionUID = 8871823801671988312L;

	ArrayList<JLabel> board_squares;
	
	/**
	 * Instantiates a copy of the boardView 
	 */
	public BoardView() {
		setLayout(new GridLayout(6, 6, 0, 0));
		setOpaque(false);
		board_squares = new ArrayList<JLabel>();
		
		/* Intialize each View */
		for(int i = 0; i < 36; i++){
			JLabel label = new JLabel();
			label.setOpaque(true);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(label.getFont().deriveFont(label.getFont().getSize() + 20f));
			label.setBorder(new LineBorder(new Color(0, 0, 0), 1));
			label.setBackground(Color.WHITE);
			add(label);
			board_squares.add(label);
		}
		revalidate();
	}
	
	/**
	 * This function returns the JLabel at the given row and column of the BoardView 
	 * Indexed by 0
	 */
	public JLabel getJLabel(int row, int col){
		if (row >= 6 || col >= 6 || row < 0 || col < 0) return null;
		return board_squares.get(row * 6 + col);
	}
	
	/**
	 * This function highlights the given JLabel
	 * @param label The JLabel to be highlighted
	 */

	public void highlight(JLabel label){
		if(label == null){
			System.out.println("Error: Null Label in scandium.lettercraze.view.BoardView.highlight");
			return;
		}
		label.setBackground(Color.GRAY);
	}
}
