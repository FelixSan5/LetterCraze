/**
 * WordDictionary.java
 * 
 * @author Scandium
 */
package scandium.common.tool;

import java.util.HashSet;

/**
 * A tool to contain a list of words.
 */
public class WordDictionary {
    private HashSet<String> words;

    /**
     * Create a new dictionary.
     * @param words The words in the dictionary.
     */
    public WordDictionary() {
    	this.words = new HashSet<String>();
    	initialize();
    }
    
    /**
     * This function initializes the dictionary to hold the words stored in dictionary.txt
     */
    void initialize(){
    	
    }

    /**
     * Check whether a word is contained in the dictionary.
     * @param word The word to check.
     * @return Whether the given word is in this dictionary.
     */
    public boolean isWord(String word) {
    	return words.contains(word);
    }

}