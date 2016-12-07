/**
 * GameLoader.java
 * 
 * @author Scandium
 */
package scandium.common.tool;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

/**
 * A tool which can be used to save and load files related to the game.
 */
public class GameLoader {
	private String savePath;

	/**
	 * Creates a new game loader which will load and save to the default save file.
	 */
	public GameLoader() {
		savePath = Paths.get(System.getProperty("user.home"), "LetterCrazeGame.xml").toString();
	}
	
	/**
	 * Creates a new game loader which will load and save to the given file.
	 * 
	 * @param savePath
	 *            The path to save and load level data from.
	 */
	public GameLoader(String savePath) {
		savePath = Paths.get(savePath).toString();
	}

	/**
	 * Loads all of the levels from the save file and returns them as a list.
	 * Precondition: None. 
	 * Postcondition: Gather's a list of loaded levels if they exist in the save file.
	 * 
	 * @return The list of levels loaded from file, or an empty list of no
	 *         levels were loaded.
	 */
	public List<Level> LoadLevels() throws JAXBException {
		return LoadLevels(new ArrayList<Level>());
	}

	/**
	 * Loads all of the levels from the save file and returns them as a list.
	 * Precondition: The given list is not null. 
	 * Postcondition: Gather's a list of loaded levels if they exist in the save file.
	 * 
	 * @param list
	 *            The list object to add all the loaded levels to.
	 * 
	 * @return The list of levels loaded from file, or an empty list of no
	 *         levels were loaded.
	 */
	public List<Level> LoadLevels(List<Level> list) {
		try {
			JAXBContext context = JAXBContext.newInstance(LevelContainer.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			// load the file
			LevelContainer unmarshalled = (LevelContainer) unmarshaller.unmarshal(new File(savePath));

			// add the results to the passed in list
			for (Level level : unmarshalled.getLevels()) {
				list.add(level);
			}
		} catch (JAXBException e) {
			System.err.println("Could not load levels from \"" + savePath + "\"!");
			e.printStackTrace();
		}

		// return the list
		return list;
	}

	/**
	 * Gets the game token which is unique to the level save.
	 * Precondition: None.
	 * Postcondition: Loads the token of this game save.
	 *  
	 * @return The game token loaded from file, or zero if the token couldn't be loaded.
	 */
	public long GetGameToken(List<Level> list) {
		long token = 0;
		try {
			JAXBContext context = JAXBContext.newInstance(LevelContainer.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			// load the file
			LevelContainer unmarshalled = (LevelContainer) unmarshaller.unmarshal(new File(savePath));
			token = unmarshalled.getToken();
			
		} catch (JAXBException e) {
			System.err.println("Could not load game token from \"" + savePath + "\"!");
			e.printStackTrace();
		}
		// return the token
		return token;
	}
	
	/**
	 * Saves all of the given levels to the save file. 
	 * Precondition: The given list is not null. 
	 * Postcondition: The list of levels are saved in a serialized form to the save file.
	 * 
	 * @param levels
	 *            The levels to save.
	 * 
	 * @return Whether any levels were saved.
	 */
	public boolean SaveLevels(List<Level> levels) {
		try {
			JAXBContext context = JAXBContext.newInstance(LevelContainer.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			File file = new File(savePath);

			// save the levels
			marshaller.marshal(new LevelContainer(levels), file);
			
		} catch (JAXBException e) {
			System.err.println("Could not save levels to \"" + savePath + "\"!");
			e.printStackTrace();
			return false;
		}
		// return whether any levels were saved
		return !levels.isEmpty();
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
	private static class LevelContainer {
		@XmlElement
		private long token;
		@XmlElementWrapper(name = "levels")
		@XmlElement(name = "level")
		private List<Level> levels;

		/**
		 * Creates a new Level container with default information.
		 */
		@SuppressWarnings("unused")
		private LevelContainer() { 
			levels = new ArrayList<Level>();
		}

		/**
		 * Creates a new Level container with the given levels.
		 * 
		 * @param levels
		 *            The levels to be contained.
		 */
		public LevelContainer(List<Level> levels) {
			this.levels = levels;
			// generate a random token that is not zero
			Random random = new Random();			
			do {
				token = random.nextLong();
			} while (token == 0);
		}

		/**
		 * @return the levels
		 */
		public List<Level> getLevels() {
			return levels;
		}

		/**
		 * @return the token
		 */
		public long getToken() {
			return token;
		}
	}
}
