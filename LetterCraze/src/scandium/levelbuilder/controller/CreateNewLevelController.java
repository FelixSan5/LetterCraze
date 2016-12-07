package scandium.levelbuilder.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import scandium.levelbuilder.model.Model;
import scandium.levelbuilder.view.Application;

/**
 * @author Felix
 */
public class CreateNewLevelController extends MouseAdapter{

	//attributes
    Model model;
    Application app;

    
    /**
     * @param model 
     * @param app
     */
    public CreateNewLevelController(Model m, Application a) {
        this.model = m;
        this.app = a;
    }

    
    /**
     * @param MouseEvent me
     */
    public void mousePressed(MouseEvent me) {
    	app.setViewLevelEditor();
    }

}