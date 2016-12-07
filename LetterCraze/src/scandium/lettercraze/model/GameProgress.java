/**
 * GameProgress.java
 * 
 * @author Scandium
 */
package scandium.lettercraze.model;

import java.util.HashMap;

import scandium.common.model.Level;

/**
 * A collection of progresses to track the Player's total progress on a set of Levels.
 */
public class GameProgress {
	private HashMap<Level, LevelProgress> levelProgresses;
	private LevelProgress currentLevelProgress;
	//to create a flag so that the GameProgress is a singleton
	private boolean created = false;

	/**
	 * Creates a completely new game progress with nothing stored in it
	 * Creates a currentLevelProgress with a null level and isPlaying equals false
	 * @throws IllegalStateException if the current level progress is already running, 
	 * 						or if one of the LevelProgress initializations fail
	 */
	public GameProgress() throws IllegalStateException{
		//if current level progress already exists, throw exception
		if (this.created == false){
			this.levelProgresses = new HashMap<Level, LevelProgress>();
			currentLevelProgress.setPlaying(false);
			this.created = true;
		} else{
			throw new IllegalStateException("The Game Progress Already Exists");
		}
	}

	/**
	 * Creates a new game progress from the given HashMap
	 * Creates a currentLevelProgress with a null level and isPlaying equals false
	 * @param storedProgress a HashMap of the current stored progress to be loaded
	 * @throws IllegalStateException 
	 */
	public GameProgress(HashMap<Level, LevelProgress> storedProgress) throws IllegalStateException{
		if (this.created == false){
			this.levelProgresses = storedProgress;
		//TODO Replace this with loading the storedProgress from file
		this.currentLevelProgress = new LevelProgress(null);
		currentLevelProgress.setPlaying(false);
		this.created = true;
		} else{
			throw new IllegalStateException("The Game Progress Already Exists");
		}
	}

	/**
	 * @return the currentLevelProgress
	 */
	public LevelProgress getCurrentLevelProgress() {
		return currentLevelProgress;
	}

	/**
	 * @param level the level to set the current level playing to 
	 * @throws LevelNotUpdatedProperly
	 */
	public boolean setCurrentLevelProgress(Level level){
		return this.currentLevelProgress.setLevel(level);
	}

	/**
	 * This method removes the LevelProgress from LevelProgresses and returns true if the state has changed.
	 * @return if the LevelProgress was added and if the state of LevelProgresses has changed
	 */
	public boolean removeLevelProgresses(LevelProgress lp) {
		if(levelProgresses.remove(lp.getLevel()).equals(lp)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Gets the progress for a specific level.
	 * @param level The Level to retrieve the progress for.
	 * @return The progress for the given Level.
	 */
	public LevelProgress getProgressForLevel(Level level) {
		return levelProgresses.get(level);
	}

	/**
	 * This method adds the LevelProgress to LevelProgresses and returns true if the state has changed.
	 * @param lp
	 * @return true if the LevelProgress was added and if the state of LevelProgresses has changed
	 */
	public boolean addLevelProgress(LevelProgress lp){
		if(levelProgresses.get(lp.getLevel()).equals(lp)){
			return false;
		}
		if(levelProgresses.put(lp.getLevel(), lp).equals(null)){
			return levelProgresses.get(lp.getLevel()).equals(lp);
		}else{
			return levelProgresses.get(lp.getLevel()).equals(lp);
		}
	}
	
}