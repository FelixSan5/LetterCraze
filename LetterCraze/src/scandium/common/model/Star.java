/**
 * Star.java
 * 
 * @author Scandium
 * Date: 11/28/2016
 * Description: This class represents a star for a level. A star has a threshold that when
 * surpassed, indicates that this star has been achieved. 
 */
package scandium.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A star is an object which can be obtained during game play.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Star {
	@XmlElement
	private int threshold;

	/**
	 * Creates a new star with default information.
	 * Precondition: None.
	 * Postcondition: A new star is created with a default score threshold.
	 */
    public Star() {
    	threshold = 0;
    }

    /**
	 * Creates a new star with the given threshold. 
	 * Precondition: The threshold is not negative. 
	 * Postcondition: A new star is created with the given score threshold.
	 * 
	 * @param threshold
	 *            The score threshold needed to obtain the star.
	 * @exception IllegalArgumentException
	 *                Thrown if the threshold is negative.
	 */
    public Star(int threshold) throws IllegalArgumentException {
    	this.threshold = threshold;
    }
    
    /**
	 * Gets the score threshold required to obtain this star. 
	 * Precondition: None. 
	 * Postcondition: The score threshold is returned.
	 * 
	 * @return The score threshold.
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * Sets the score threshold required to obtain this star. 
	 * Precondition: The score is not negative. 
	 * Postcondition: The score threshold for this star is updated.
	 * 
	 * @param threshold
	 *            The integer threshold value
	 * @exception IllegalArgumentException
	 *                Thrown if the threshold is negative.
	 */
	public void setThreshold(int threshold) throws IllegalArgumentException {
		this.threshold = threshold;
	}
	
	/**
	 * Determines whether this star is obtained based on its threshold and the
	 * given score. 
	 * Precondition: None.
	 * Postcondition: Returns whether the given score is enough to obtain this star.
	 * 
	 * @param score
	 *            The integer representing the game's current score
	 * @return Whether the given score is enough to obtain this star.
	 */
    public boolean isObtained(int score) {
        return score >= threshold;
    }
    
    /**
     * Determines whether this star is valid (e.g. the threshold has been initialized).
     * Precondition: None.
     * Postcondition: Returns whether this star is valid.
     * 
     * @return Whether this star is valid.
     */
    public boolean isValid() {
        return threshold >= 0;
    }

}