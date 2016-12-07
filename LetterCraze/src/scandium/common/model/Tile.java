/**
 * Tile.java
 * 
 * @author Scandium
 * Date: 11/28/2016
 * Description: This class represents the tiles to be placed on the board Square. Each tile 
 * has a content (qu, or any letter other than q), and a score dependent on the content. This
 * Class is used to store information and has no real functionality.
 */
package scandium.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An item which contains content and a score that is associated with it.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Tile {
	@XmlElement
    private String content;
	@XmlElement
    private int score;

    /**
	 * Creates a tile with default information. 
	 * Precondition: None.
	 * Postcondition: The tile is created with default information.
	 */
	public Tile() {
		this(new String(), 0);
	}
    
    /**
	 * Creates a tile with the given content and score. 
	 * Precondition: The content is not null and not empty, and the score is non-negative.
	 * Postcondition: A tile is created with the given information.
	 * 
	 * @param content
	 *            The String letter
	 * @param score
	 *            The integer score for the tile
	 * @throws IllegalArgumentException
	 *             Thrown if the content is empty or the score is negative.
	 * @throws NullPointerException
	 *             Thrown if content is null.
	 */
    public Tile(String content, int score)
    		throws IllegalArgumentException, NullPointerException {
    	this.content = content;
    	this.score = score;
    }
    
    /**
     * This function returns the string representing the tiles content
     * @return String The tile's content
     */
	public String getContent() {
		return content;
	}

	/**
	 * This function returns an integer representing this tile's score
	 * @return int The tile's score 
	 */
	public int getScore() {
		return score;
	}
}