/**
 * LightningLevel.java
 * 
 * @author Scandium
 */
package scandium.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A type of Level which has a time limit.
 */
@XmlRootElement(name = "lightningLevel")
@XmlAccessorType(XmlAccessType.FIELD)
public class LightningLevel extends Level {
	@XmlElement
    private int timeLimit;
    
    /**
     * Creates a new Lightning Level with the default information.
     */
    public LightningLevel() {
    	super();
    	timeLimit = 0;
    }

    /**
     * Creates a new Lightning Level with the given information.
     * @param name The name of the Level.
     * @param board The Board of the Level. 
     * @param stars The Stars used in the Level.
     * @param timeLimit The time limit for the Level.
     */
    public LightningLevel(String name, Board board, Star[] stars, int timeLimit) {
        super(name, board, stars);
        this.timeLimit = timeLimit;
    }

    /**
	 * @return the timeLimit
	 */
	public int getTimeLimit() {
		return timeLimit;
	}

	/**
	 * @param timeLimit the timeLimit to set
	 */
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	/* (non-Javadoc)
     * @see scandium.common.model.Level#getType()
     */
    @Override
    public String getType() {
        return "Lightning";
    }

    /* (non-Javadoc)
     * @see scandium.common.model.Level#copy()
     */
    @Override
    public LightningLevel copy() {
    	LightningLevel levelCopy = new LightningLevel(super.getName(), super.getBoard(), super.getStars(), timeLimit);
        return levelCopy;
    }

    /* (non-Javadoc)
     * @see scandium.common.model.Level#isValid()
     */
    @Override
    public boolean isValid() {
    	if ((super.getName() != null) && (super.getBoard() != null) && (super.getStars() != null) && ((Integer) timeLimit != null) && (super.getBoard().isValid() == true)){
    		return true;
    	}
        return false;
    }
}