/**
 * LetterDictionary.java
 * 
 * @author Scandium
 * Date: 12/1/2016
 * Description: This class will allow the controllers to easily generate new 'random' letters based
 * on their respective frequency.
 */
package scandium.common.tool;

import java.util.Random;

import scandium.common.model.Tile;

/**
 * A tool used for generating Tiles.
 */
public class LetterDictionary {

	/* Class attributes                                                                          */
	private String[] letters;
	private double[] frequencies;
	private int[] scores;
    Random random;

    /**
     * Creates a new LetterDictionary.
     */
    public LetterDictionary() {
    	this(System.currentTimeMillis());
    }
    
    /**
     * Creates a new LetterDictionary.
     * @param seed The seed for the random generator.
     */
    public LetterDictionary(long seed) {
    	this.letters = new String[26];
    	this.frequencies = new double[26];
    	this.scores = new int[26];
    	this.random = new Random(seed);
    	initialize();
    }
    
    /** 
     * This function initializes the letter dictionary. It adds all letters, their scores, 
     * and their frequencies. 
     */
    void initialize(){
    	/* Add A                                                                                 */
    	letters[0] = "A";
    	frequencies[0] = 8.17;
    	scores[0] = 2;
    	
    	/* Add B                                                                                 */
    	letters[1] = "B";
    	frequencies[1] = 1.49;
    	scores[1] = 4;
    	
    	/* Add C                                                                                 */
    	letters[2] = "C";
    	frequencies[2] = 2.78;
    	scores[2] = 3;  
    	
    	/* Add D                                                                                 */
    	letters[3] = "D";
    	frequencies[3] = 4.25;
    	scores[3] = 3;
    	
    	/* Add E                                                                                 */
    	letters[4] = "E";
    	frequencies[4] = 12.7;
    	scores[4] = 1;
    	
    	/* Add F                                                                                 */
    	letters[5] = "F";
    	frequencies[5] = 2.23;
    	scores[5] = 4;
    	
    	/* Add G                                                                                 */
    	letters[6] = "G";
    	frequencies[6] = 2.02;
    	scores[6] = 4;
    	
    	/* Add H                                                                                 */
    	letters[7] = "H";
    	frequencies[7] = 6.09;
    	scores[7] = 2;
    	
    	/* Add I                                                                                 */
    	letters[8] = "I";
    	frequencies[8] = 6.97;
    	scores[8] = 2;
    	
    	/* Add J                                                                                 */
    	letters[9] = "J";
    	frequencies[9] = 0.15;
    	scores[9] = 7;
    	
    	/* Add K                                                                                 */
    	letters[10] = "K";
    	frequencies[10] = 0.77;
    	scores[10] = 5;
    	
    	/* Add L                                                                                 */
    	letters[11] = "L";
    	frequencies[11] = 4.03;
    	scores[11] = 3;
    	
    	/* Add M                                                                                 */
    	letters[12] = "M";
    	frequencies[12] = 2.41;
    	scores[12] = 3;
    	
    	/* Add N                                                                                 */
    	letters[13] = "N";
    	frequencies[13] = 6.75;
    	scores[13] = 2;
    	
    	/* Add O                                                                                 */
    	letters[14] = "O";
    	frequencies[14] = 7.51;
    	scores[14] = 2;
    	
    	/* Add P                                                                                 */
    	letters[15] = "P";
    	frequencies[15] = 1.93;
    	scores[15] = 4;
    	
    	/* Add QU                                                                                */
    	letters[16] = "QU";
    	frequencies[16] = 2.86;
    	scores[16] = 11;
    	
    	/* Add R                                                                                 */
    	letters[17] = "R";
    	frequencies[17] = 5.99;
    	scores[17] = 2;
    	
    	/* Add S                                                                                 */
    	letters[18] = "S";
    	frequencies[18] = 6.33;
    	scores[18] = 2;
    	
    	/* Add T                                                                                 */
    	letters[19] = "T";
    	frequencies[19] = 9.06;
    	scores[19] = 1;
    	
    	/* Add U                                                                                 */
    	letters[20] = "U";
    	frequencies[20] = 2.76;
    	scores[20] = 3;
    	
    	/* Add V                                                                                 */
    	letters[21] = "V";
    	frequencies[21] = 0.98;
    	scores[21] = 5;
    	
    	/* Add W                                                                                 */
    	letters[22] = "W";
    	frequencies[22] = 2.36;
    	scores[22] = 3;
    	
    	/* Add X                                                                                 */
    	letters[23] = "X";
    	frequencies[23] = 0.15;
    	scores[23] = 7;
    	
    	/* Add Y                                                                                 */
    	letters[24] = "Y";
    	frequencies[24] = 1.97;
    	scores[24] = 4;
    	
    	/* Add Z                                                                                 */
    	letters[25] = "Z";
    	frequencies[25] = 0.07;
    	scores[25] = 8;
    }

    /**
     * This function creates and returns a tile with a random letter. 
     * @return Tile
     */
    public Tile getRandomTile() {
    	int rand = random.nextInt(10000);
    	String letter = null;
    	int score = -1;
    	double relative_freq = 0;
    	for(int i = 0; i < 26; i++){
    		relative_freq += frequencies[i];
    		if(rand < relative_freq * 100){
    			letter = letters[i];
    			score = scores[i];
    			break;
    		}
    	}
    	
    	if(letter == null){
    		System.out.println("Error Occured while generating random tile");
    		return null;
    	}
    	Tile tile = new Tile(letter, score);
		return tile;
    }
    
    /** 
     * This function returns the score of the given letter 
     * returns -1 if an error occurred
     * @return int
     */
    public int getLetterScore(String letter){
    	int score = -1;
    	for(int i = 0; i < 26; i++){
    		if(letters[i].equals(letter)){
    			score = scores[i];
    			break;
    		}
    	}
    	return score;
    }
 

    /**
     * This function returns the frequency of the given letter
     * returns -1 if an error occurred
     * @return double
     */
    public double getLetterFrequency(String letter){
    	double frequency = -1;
    	for(int i = 0; i < 26; i++){
    		if(letters[i].equals(letter)){
    			frequency = frequencies[i];
    			break;
    		}
    	}
    	return frequency;
    }
}