package scandium.levelbuilder.controller;



import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import scandium.levelbuilder.model.Model;
import scandium.levelbuilder.view.Application;

/**
 * @author Felix
 */
public class DisableBoardSquareController {

	//attributes
    Model model;
    Application app;
    
    /**
     * @param m 
     * @param a
     */
    public DisableBoardSquareController(Model m, Application a) {
    	this.model = m;
    	this.app = a;
    }

    /**
     * @param MouseEvent me
     */
    public void mousePressed(MouseEvent me) {
    	((JLabel)this).setBackground(Color.BLACK);
    }

}