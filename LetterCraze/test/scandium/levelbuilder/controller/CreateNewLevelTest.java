package scandium.levelbuilder.controller;


import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import scandium.common.testresources.ScandiumLevelBuilderTestCase;
import scandium.levelbuilder.view.Application;

public class CreateNewLevelTest extends ScandiumLevelBuilderTestCase {
	Application app;

	protected void setUp() throws Exception {
		app = new Application();
		try{Thread.sleep(5000);}catch(Exception e){};
		app.setViewMainMenu();
		try{Thread.sleep(1000);}catch(Exception e){};
	}

	protected void tearDown() throws Exception {
		app.dispose();
	}

	/**
	 * Checks that clicking on the New Level Button displays the Level Editor View.
	 */
	public void testNewLevelButtonViewChange(){
		assertEquals(true,app.getMainMenu().isVisible());
		assertEquals(false,app.getSplashScreen().isVisible());
		assertEquals(false,app.getLevelEditor().isVisible());
		MouseEvent press = this.createClicked(app, app.getMainMenu().getNewLevelButton(), 0, 0);
		app.getMainMenu().getNewLevelButton().dispatchEvent(press);
		//check that correct panel is visible
		assertEquals(false,app.getMainMenu().isVisible());
		assertEquals(false,app.getSplashScreen().isVisible());
		assertEquals(true,app.getLevelEditor().isVisible());
		//check that default attributes are visible
		assertEquals(true,app.getLevelEditor().getBoardView().isVisible());
		assertEquals(true,app.getLevelEditor().getGenerateButton().isVisible());
		assertEquals(true,app.getLevelEditor().getMainMenuButton().isVisible());
		assertEquals(true,app.getLevelEditor().getSaveButton().isVisible());
		assertEquals(true,app.getLevelEditor().getLightningLevelButton().isVisible());
		assertEquals(true,app.getLevelEditor().getThemeLevelButton().isVisible());
		assertEquals(true,app.getLevelEditor().getPuzzleLevelButton().isVisible());
		assertEquals(true,app.getLevelEditor().getLevelNameTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getLevelNameLabel().isVisible());
		assertEquals(false,app.getLevelEditor().getLevelSpecificLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getOneStarLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getOneStarTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getOneStarUnitLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getThreeStarLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getThreeStarTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getThreeStarUnitLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getTwoStarLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getTwoStarTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getTwoStarUnitLabel().isVisible());
		//check Puzzle Level View attributes are visible (default)
		assertEquals(true,app.getLevelEditor().getPuzzleMaxNumWordsLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getPuzzleMaxNumWordsTextField().isVisible());
		//check Theme Level attributes are not visible
		assertEquals(false,app.getLevelEditor().getThemeNameTextField().isVisible());
		assertEquals(false,app.getLevelEditor().getThemeWordsTextArea().isVisible());
		//check that Lighting Level attributes are not visible
		assertEquals(false,app.getLevelEditor().getLightningTimeLimitLabel().isVisible());
	}

	public void testEditPuzzleLevelButtonViewChange(){
		//TODO create tests for if the items inside of the text fields are correct
		//can use .getSelectedText() on TextFields to get text inside of them
		assertEquals(true,app.getMainMenu().isVisible());
		assertEquals(false,app.getSplashScreen().isVisible());
		assertEquals(false,app.getLevelEditor().isVisible());
		MouseEvent clickEditButton = this.createClicked(app, app.getMainMenu().getNewLevelButton(), 0, 0);
		MouseEvent clickLevel = this.createClicked(app, app.getLevelIconView(0), 0, 0);
		app.getMainMenu().getLevelIconView(0).dispatchEvent(clickEditButton);
		app.getMainMenu().getEditLevelButton().dispatchEvent(clickEditButton);
		//check that correct panel is visible
		assertEquals(false,app.getMainMenu().isVisible());
		assertEquals(false,app.getSplashScreen().isVisible());
		assertEquals(true,app.getLevelEditor().isVisible());
		//check that default attributes are visible
		assertEquals(true,app.getLevelEditor().getBoardView().isVisible());
		assertEquals(true,app.getLevelEditor().getGenerateButton().isVisible());
		assertEquals(true,app.getLevelEditor().getMainMenuButton().isVisible());
		assertEquals(true,app.getLevelEditor().getSaveButton().isVisible());
		assertEquals(true,app.getLevelEditor().getLightningLevelButton().isVisible());
		assertEquals(true,app.getLevelEditor().getThemeLevelButton().isVisible());
		assertEquals(true,app.getLevelEditor().getPuzzleLevelButton().isVisible());
		assertEquals(true,app.getLevelEditor().getLevelNameTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getLevelNameLabel().isVisible());
		assertEquals(false,app.getLevelEditor().getLevelSpecificLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getOneStarLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getOneStarTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getOneStarUnitLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getThreeStarLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getThreeStarTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getThreeStarUnitLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getTwoStarLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getTwoStarTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getTwoStarUnitLabel().isVisible());
		//check Puzzle Level View attributes are visible (default)
		assertEquals(true,app.getLevelEditor().getPuzzleMaxNumWordsLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getPuzzleMaxNumWordsTextField().isVisible());
		//check Theme Level attributes are not visible
		assertEquals(false,app.getLevelEditor().getThemeNameTextField().isVisible());
		assertEquals(false,app.getLevelEditor().getThemeWordsTextArea().isVisible());
		//check that Lighting Level attributes are not visible
		assertEquals(false,app.getLevelEditor().getLightningTimeLimitLabel().isVisible());
	}

	public void testEditLightingLevelButtonViewChange(){
		//TODO create tests for if the items inside of the text fields are correct
		//can use .getSelectedText() on TextFields to get text inside of them
		assertEquals(true,app.getMainMenu().isVisible());
		assertEquals(false,app.getSplashScreen().isVisible());
		assertEquals(false,app.getLevelEditor().isVisible());
		MouseEvent clickEditButton = this.createClicked(app, app.getMainMenu().getNewLevelButton(), 0, 0);
		MouseEvent clickLevel = this.createClicked(app, app.getLevelIconView(1), 0, 0);
		app.getMainMenu().getLevelIconView(1).dispatchEvent(clickEditButton);
		app.getMainMenu().getEditLevelButton().dispatchEvent(clickEditButton);
		//check that correct panel is visible
		assertEquals(false,app.getMainMenu().isVisible());
		assertEquals(false,app.getSplashScreen().isVisible());
		assertEquals(true,app.getLevelEditor().isVisible());
		//check that default attributes are visible
		assertEquals(true,app.getLevelEditor().getBoardView().isVisible());
		assertEquals(true,app.getLevelEditor().getGenerateButton().isVisible());
		assertEquals(true,app.getLevelEditor().getMainMenuButton().isVisible());
		assertEquals(true,app.getLevelEditor().getSaveButton().isVisible());
		assertEquals(true,app.getLevelEditor().getLightningLevelButton().isVisible());
		assertEquals(true,app.getLevelEditor().getThemeLevelButton().isVisible());
		assertEquals(true,app.getLevelEditor().getPuzzleLevelButton().isVisible());
		assertEquals(true,app.getLevelEditor().getLevelNameTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getLevelNameLabel().isVisible());
		assertEquals(false,app.getLevelEditor().getLevelSpecificLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getOneStarLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getOneStarTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getOneStarUnitLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getThreeStarLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getThreeStarTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getThreeStarUnitLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getTwoStarLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getTwoStarTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getTwoStarUnitLabel().isVisible());
		//check Puzzle Level View attributes are visible (default)
		assertEquals(false,app.getLevelEditor().getPuzzleMaxNumWordsLabel().isVisible());
		assertEquals(false,app.getLevelEditor().getPuzzleMaxNumWordsTextField().isVisible());
		//check Theme Level attributes are not visible
		assertEquals(false,app.getLevelEditor().getThemeNameTextField().isVisible());
		assertEquals(false,app.getLevelEditor().getThemeWordsTextArea().isVisible());
		//check that Lighting Level attributes are not visible
		assertEquals(true,app.getLevelEditor().getLightningTimeLimitLabel().isVisible());
	}

	public void testEditThemeLevelButtonViewChange(){
		//TODO create tests for if the items inside of the text fields are correct
		//can use .getSelectedText() on TextFields to get text inside of them
		assertEquals(true,app.getMainMenu().isVisible());
		assertEquals(false,app.getSplashScreen().isVisible());
		assertEquals(false,app.getLevelEditor().isVisible());
		MouseEvent clickEditButton = this.createClicked(app, app.getMainMenu().getNewLevelButton(), 0, 0);
		MouseEvent clickLevel = this.createClicked(app, app.getLevelIconView(2), 0, 0);
		app.getMainMenu().getLevelIconView(2).dispatchEvent(clickEditButton);
		app.getMainMenu().getEditLevelButton().dispatchEvent(clickEditButton);
		//check that correct panel is visible
		assertEquals(false,app.getMainMenu().isVisible());
		assertEquals(false,app.getSplashScreen().isVisible());
		assertEquals(true,app.getLevelEditor().isVisible());
		//check that default attributes are visible
		assertEquals(true,app.getLevelEditor().getBoardView().isVisible());
		assertEquals(true,app.getLevelEditor().getGenerateButton().isVisible());
		assertEquals(true,app.getLevelEditor().getMainMenuButton().isVisible());
		assertEquals(true,app.getLevelEditor().getSaveButton().isVisible());
		assertEquals(true,app.getLevelEditor().getLightningLevelButton().isVisible());
		assertEquals(true,app.getLevelEditor().getThemeLevelButton().isVisible());
		assertEquals(true,app.getLevelEditor().getPuzzleLevelButton().isVisible());
		assertEquals(true,app.getLevelEditor().getLevelNameTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getLevelNameLabel().isVisible());
		assertEquals(false,app.getLevelEditor().getLevelSpecificLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getOneStarLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getOneStarTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getOneStarUnitLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getThreeStarLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getThreeStarTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getThreeStarUnitLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getTwoStarLabel().isVisible());
		assertEquals(true,app.getLevelEditor().getTwoStarTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getTwoStarUnitLabel().isVisible());
		//check Puzzle Level View attributes are visible (default)
		assertEquals(false,app.getLevelEditor().getPuzzleMaxNumWordsLabel().isVisible());
		assertEquals(false,app.getLevelEditor().getPuzzleMaxNumWordsTextField().isVisible());
		//check Theme Level attributes are not visible
		assertEquals(true,app.getLevelEditor().getThemeNameTextField().isVisible());
		assertEquals(true,app.getLevelEditor().getThemeWordsTextArea().isVisible());
		//check that Lighting Level attributes are not visible
		assertEquals(false,app.getLevelEditor().getLightningTimeLimitLabel().isVisible());
	}

	public void testLeaveLevelEditorViewChange(){
		assertEquals(true,app.getMainMenu().isVisible());
		assertEquals(false,app.getSplashScreen().isVisible());
		assertEquals(false,app.getLevelEditor().isVisible());
		MouseEvent clickEditButton = this.createClicked(app, app.getMainMenu().getNewLevelButton(), 0, 0);
		MouseEvent clickLevel = this.createClicked(app, app.getLevelIconView(0), 0, 0);
		app.getMainMenu().getLevelIconView(0).dispatchEvent(clickEditButton);
		app.getMainMenu().getEditLevelButton().dispatchEvent(clickEditButton);
		//check that correct panel is visible
		assertEquals(false,app.getMainMenu().isVisible());
		assertEquals(false,app.getSplashScreen().isVisible());
		assertEquals(true,app.getLevelEditor().isVisible());
		//click on the Main Menu Button to return to Main Menu
		MouseEvent clickExit = this.createClicked(app, app.getLevelEditor().getMainMenuButton(), 0, 0);
		app.getLevelEditor().getMainMenuButton().dispatchEvent(clickEditButton);
		//check that correct panel is visible
		assertEquals(true,app.getMainMenu().isVisible());
		assertEquals(false,app.getSplashScreen().isVisible());
		assertEquals(false,app.getLevelEditor().isVisible());
	}

	public void testEnableDisableBoardSquare(){
		assertEquals(true,app.getMainMenu().isVisible());
		assertEquals(false,app.getSplashScreen().isVisible());
		assertEquals(false,app.getLevelEditor().isVisible());
		//click on the New Level Button
		MouseEvent press = this.createClicked(app, app.getMainMenu().getNewLevelButton(), 0, 0);
		app.getMainMenu().getNewLevelButton().dispatchEvent(press);
		//check that correct panel is visible
		assertEquals(false,app.getMainMenu().isVisible());
		assertEquals(false,app.getSplashScreen().isVisible());
		assertEquals(true,app.getLevelEditor().isVisible());
		//assert top left BoardSquare in enabled
		assertEquals(true,app.getModel().getEditProgress().getModified().getBoard().getBoardSquare(0, 0).isEnabled());
		//click on the top left Board Square
		MouseEvent clickSquare = this.createClicked(app, app.getLevelEditor().getBoardView(), 21, 21);
		app.getLevelEditor().getBoardView().dispatchEvent(clickSquare);
		//assert top left Board Square is disabled
		assertEquals(false,app.getModel().getEditProgress().getModified().getBoard().getBoardSquare(0, 0).isEnabled());
		//repeat click on top left BoardSquare
		app.getLevelEditor().getBoardView().dispatchEvent(clickSquare);
		//assert top left Board Square is enabled
		assertEquals(true,app.getModel().getEditProgress().getModified().getBoard().getBoardSquare(0, 0).isEnabled());
	}

}
