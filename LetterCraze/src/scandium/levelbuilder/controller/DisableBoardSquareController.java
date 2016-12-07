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
    JLabel square;
    
    /**
     * @param m 
     * @param a
     */
    public DisableBoardSquareController(JLabel jl, Model m, Application a) {
    	this.model = m;
    	this.app = a;
    	this.square = jl;
    }

    /**
     * @param MouseEvent me
     */
    public void mousePressed(MouseEvent me) {
    	this.square.setBackground(Color.BLACK);
    }

}