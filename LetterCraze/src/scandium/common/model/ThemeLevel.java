/**
 * ThemeLevel.java
 * 
 * @author Scandium
 */
package scandium.common.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A type of Level with a predefined list of theme words.
 */
@XmlRootElement(name = "themeLevel")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThemeLevel extends Level {
	@XmlElement
	private String theme;
	@XmlElementWrapper(name = "themeWords")
	@XmlElement(name = "word")
	private ArrayList<String> themeWords;

    /**
     * Creates a new Theme level with the default information.
     */
    public ThemeLevel() {
    	super();
    	theme = new String();
    	themeWords = new ArrayList<String>();
    }

    /**
     * Creates a new Theme Level with the given information.
     * @param name The name of the Level.
     * @param board The Board of the Level.
     * @param stars The Stars in the Level.
     * @param theme The theme name for the Level.
     * @param themeWords The predefined theme words for the Level.
     */
    public ThemeLevel(String name, Board board, Star[] stars, String theme, ArrayList<String> themeWords) {
    	super(name, board, stars);
    	this.theme = theme;
    	this.themeWords = themeWords;
    }

    /**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @return the themeWords
	 */
	public ArrayList<String> getThemeWords() {
		return themeWords;
	}

	/**
	 * @param themeWords the themeWords to set
	 */
	public void setThemeWords(ArrayList<String> themeWords) {
		this.themeWords = themeWords;
	}

	/* (non-Javadoc)
     * @see scandium.common.model.Level#getType()
     */
    @Override
    public final String getType() {
        return "Theme";
    }

    /* (non-Javadoc)
     * @see scandium.common.model.Level#copy()
     */
    @Override
    public ThemeLevel copy() {
    	ThemeLevel levelCopy = new ThemeLevel(super.getName(), super.getBoard(), super.getStars(), theme, new ArrayList<String>(themeWords));
        return levelCopy;
    }

    /* (non-Javadoc)
     * @see scandium.common.model.Level#isValid()
     */
    @Override
    public boolean isValid() {
    	if ((super.getName() != null) && (super.getBoard() != null) && (super.getStars() != null) && (theme != null) && (themeWords.isEmpty() == false) && (super.getBoard().isValid() == true)){
    		return true;
    	}
        return false;
    }

    /**
     * Adds the given word to the list of theme words.
     * @param word The word to add.
     * @return Whether the word was added.
     */
    public boolean addThemeWord(String word) {
    	return themeWords.add(word); 
    }

    /**
     * Removes the given word from the list of theme words.
     * @param word The word to remove.
     * @return Whether the word was removed.
     */
    public boolean removeThemeWord(String word) {
    	return themeWords.remove(word);
    }
}