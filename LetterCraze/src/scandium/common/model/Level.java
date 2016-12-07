/**
 * Level.java
 * 
 * @author Scandium
 */
package scandium.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * A class which holds basic information about a Level.
 */
@XmlSeeAlso({PuzzleLevel.class, LightningLevel.class, ThemeLevel.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Level {
    @XmlElement
    private String name;
    @XmlElement
    private Board board;
    @XmlElementWrapper(name = "stars")
    @XmlElement(name = "star")
    private Star[] stars;
    
    /**
     * Creates a new Level with the default information.
     */
    public Level() {
    	this.name = new String();
    	this.board = new Board();
    	this.stars = new Star[] {
    			new Star(),
    			new Star(),
    			new Star()
    	};
    }

    /**
     * Creates a new Level with the given information.
     * @param name The name of the Level.
     * @param board The Board that the Level will use.
     * @param stars The Stars that the Level will use.
     */
    public Level(String name, Board board, Star[] stars) {
    	this.name = name;
    	this.board = board;
    	this.stars = stars;
    }

    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * @return the stars
	 */
	public Star[] getStars() {
		return stars;
	}

	/**
     * @return the user friendly type of the Level
     */
    public abstract String getType();

    /**
     * Copies the data in this Level into a new Level.
     * @return A copy of this Level.
     */
    public abstract Level copy();

    /**
     * Determines whether this Level is valid.
     * @return Whether this Level is valid.
     */
    public abstract boolean isValid(); //For each of the different isValid methods, it might need to get more specific with how it is implemented in the future.
}