/**
 * ProgressLoader.java
 * 
 * @author Scandium
 */
package scandium.lettercraze.tool;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import scandium.common.model.Level;
import scandium.lettercraze.model.LevelProgress;

/**
 * A tool which can be used to save and load files related to the game.
 */
public class ProgressLoader {
	private String savePath;

	/**
	 * Creates a new progress loader which will load and save to the default save file.
	 */
	public ProgressLoader() {
		savePath = Paths.get(System.getProperty("user.home"), "LetterCrazeGame.xml").toString();
	}
	
	/**
	 * Creates a new progress loader which will load and save to the given file.
	 * 
	 * @param savePath
	 *            The path to save and load progress data from.
	 */
	public ProgressLoader(String savePath) {
		savePath = Paths.get(savePath).toString();
	}


	/**
	 * Loads all of the progress from the save file and returns it as a list.
	 * Precondition: The given levels list is not null. 
	 * Postcondition: Gather's a list of progress if it exist in the save file.
	 * 
	 * @param levels
	 *              The levels that the progresses are for.
	 * @param gameToken
	 *              The unique game token used to determine whether the progresses are valid.
	 * 
	 * @return The progress loaded from file or an empty list if the file couldn't be loaded.
	 */
	public List<LevelProgress> LoadLevelProgress(List<Level> levels, long gameToken) {
		return LoadLevelProgress(levels, gameToken, new ArrayList<LevelProgress>());
	}

	/**
	 * Loads all of the progress from the save file and returns it as a list.
	 * Precondition: The given levels list and the list to fill are not null. 
	 * Postcondition: Gather's a list of loaded progress based on the given list of levels.
	 *
	 * @param levels
	 *              The levels that the progresses are for.
	 * @param gameToken
	 *              The unique game token used to determine whether the progresses are valid.
	 * @param list
	 *            The list object to add all the loaded levels to.
	 * 
	 * @return The progress loaded from file or an empty list if the file couldn't be loaded.
	 */
	public List<LevelProgress> LoadLevelProgress(List<Level> levels, long gameToken, List<LevelProgress> list) {
		try {
			JAXBContext context = JAXBContext.newInstance(ProgressContainer.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			// load the file
			ProgressContainer unmarshalled = (ProgressContainer) unmarshaller.unmarshal(new File(savePath));

			if (unmarshalled.getToken() == gameToken) {
				// add the results to the passed in list
				int pos = 0;
				for (LevelProgress progress : unmarshalled.getProgress()) {
					progress.setLevel(levels.get(pos++));
					list.add(progress);
				}
			} else {
				// the token doesn't match so the progress is reset
				for (Level level : levels) {
					list.add(new LevelProgress(level));
				}
			}
		} catch (JAXBException e) {
			System.err.println("Could not load progress from \"" + savePath + "\"!");
			e.printStackTrace();
		}

		// return the list
		return list;

	}

	/**
	 * Saves all of the progress to the save file.
	 * Precondition: The given list is not null.
	 * Postcondition: The level progress is saved in a serialized form to the save file.
	 * 
	 * @param progress
	 *            The progress to save.
	 * @param gameToken
	 *              The unique game token.
	 * 
	 * @return Whether any progress was saved.
	 */
	public boolean SaveLevelProgress(List<LevelProgress> progress, long gameToken) throws JAXBException {
		try {
			JAXBContext context = JAXBContext.newInstance(ProgressContainer.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			File file = new File(savePath);

			// save the levels
			marshaller.marshal(new ProgressContainer(progress, gameToken), file);

		} catch (JAXBException e) {
			System.err.println("Could not save progress to \"" + savePath + "\"!");
			e.printStackTrace();
			return false;
		}
		// return whether any levels were saved
		return !progress.isEmpty();
	}

	/**
	 * @return the savePath
	 */
	public String getSavePath() {
		return savePath;
	}

	/**
	 * A helper class which holds all of the levels and is a container for serialization.
	 */
	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	private static class ProgressContainer {
		@XmlElement
		private long token;
		@XmlElementWrapper(name = "progresses")
		@XmlElement(name = "progress")
		private List<LevelProgress> progress;

		/**
		 * Creates a new Level container with default information.
		 */
		@SuppressWarnings("unused")
		private ProgressContainer() {
			progress = new ArrayList<LevelProgress>();
		}


		/**
		 * Creates a new Progress container with the given progress.
		 * 
		 * @param progress
		 *            The progress to be contained.
		 * @param gameToken
		 *            The game token for the levels the progresses are for.
		 */
		public ProgressContainer(List<LevelProgress> progress, long gameToken) {
			this.progress = progress;
			token = gameToken;
		}

		/**
		 * @return the levels
		 */
		public List<LevelProgress> getProgress() {
			return progress;
		}

		/**
		 * @return the token
		 */
		public long getToken() {
			return token;
		}
	}
}
