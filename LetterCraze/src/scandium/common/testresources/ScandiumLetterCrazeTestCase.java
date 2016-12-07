package scandium.common.testresources;

import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;

import junit.framework.TestCase;
import scandium.lettercraze.view.Application;

/**
 * Defines useful helper methods for testing LetterCraze.
 * <p>
 * If you would like to use this capability, have your JUnit test case
 * extend from this class instead of {@link TestCase}.
 * 
 * @author Modified from George Heineman's KSTestCase
 */
public abstract class ScandiumLetterCrazeTestCase extends TestCase {
	/**
	 * Creates Mouse Pressed in a JPanel.
	 * @param app the application currently running
	 * @param view the JPanel that is being clicked in
	 * @param dx (dx,dy) are offsets into the widget space.
	 * @param dy (dx,dy) are offsets into the widget space.
	 * @return
	 */
	public MouseEvent createPressed (Application app, JPanel view, int dx, int dy) {
		MouseEvent me = new MouseEvent(view, MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}
	
	/**
	 *  Creates Mouse RightClick in a JPanel.
	 * @param app the application currently running
	 * @param view the JPanel that is being clicked in
	 * @param dx (dx,dy) are offsets into the widget space.
	 * @param dy (dx,dy) are offsets into the widget space.
	 * @return
	 */
	public MouseEvent createRightClick (Application app, JPanel view, int dx, int dy) {
		MouseEvent me = new MouseEvent(view, MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, true);
		return me;
	}
	
	/**
	 * Creates Mouse Released in a JPanel.
	 * @param app the application currently running
	 * @param view the JPanel that is being clicked in
	 * @param dx (dx,dy) are offsets into the widget space.
	 * @param dy (dx,dy) are offsets into the widget space.
	 * @return
	 */
	public MouseEvent createReleased (Application app, JPanel view, int dx, int dy) {
		MouseEvent me = new MouseEvent(view, MouseEvent.MOUSE_RELEASED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}
	
	/**
	 * Creates Mouse Click in a JPanel.
	 * @param app the application currently running
	 * @param view the JPanel that is being clicked in
	 * @param dx (dx,dy) are offsets into the widget space.
	 * @param dy (dx,dy) are offsets into the widget space.
	 * @return
	 */
	public MouseEvent createClicked (Application app, JPanel view, int dx, int dy) {
		MouseEvent me = new MouseEvent(view, MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 1, false);
		return me;
	}
	
	/**
	 * Creates Mouse DoubleClick in a JPanel.
	 * @param app the application currently running
	 * @param view the JPanel that is being clicked in
	 * @param dx (dx,dy) are offsets into the widget space.
	 * @param dy (dx,dy) are offsets into the widget space.
	 * @return
	 */
	public MouseEvent createDoubleClicked (Application app, JPanel view, int dx, int dy) {
		MouseEvent me = new MouseEvent(view, MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 2, false);
		return me;
	}
	
	/**
	 * Creates Mouse Pressed in a JComponent.
	 * @param app the application currently running
	 * @param component the JComponent that is being clicked in
	 * @param dx (dx,dy) are offsets into the widget space.
	 * @param dy (dx,dy) are offsets into the widget space.
	 * @return
	 */
	public MouseEvent createPressed (Application app, JComponent component, int dx, int dy) {
		MouseEvent me = new MouseEvent(component, MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				component.getX()+dx, component.getY()+dy, 0, false);
		return me;
	}
	
	/**
	 * Creates Mouse RightClick in a JComponent.
	 * @param app the application currently running
	 * @param component the JComponent that is being clicked in
	 * @param dx (dx,dy) are offsets into the widget space.
	 * @param dy (dx,dy) are offsets into the widget space.
	 * @return
	 */
	public MouseEvent createRightClick (Application app, JComponent component, int dx, int dy) {
		MouseEvent me = new MouseEvent(component, MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				component.getX()+dx, component.getY()+dy, 0, true);
		return me;
	}
	
	/**
	 * Creates Mouse Click in a JComponent.
	 * @param app the application currently running
	 * @param component the JComponent that is being clicked in
	 * @param dx (dx,dy) are offsets into the widget space.
	 * @param dy (dx,dy) are offsets into the widget space.
	 * @return
	 */
	public MouseEvent createReleased (Application app, JComponent component, int dx, int dy) {
		MouseEvent me = new MouseEvent(component, MouseEvent.MOUSE_RELEASED, 
				System.currentTimeMillis(), 0, 
				component.getX()+dx, component.getY()+dy, 0, false);
		return me;
	}
	
	/**
	 * Creates Mouse Click in a JComponent.
	 * @param app the application currently running
	 * @param component the JComponent that is being clicked in
	 * @param dx (dx,dy) are offsets into the widget space.
	 * @param dy (dx,dy) are offsets into the widget space.
	 * @return
	 */
	public MouseEvent createClicked (Application app, JComponent component, int dx, int dy) {
		MouseEvent me = new MouseEvent(component, MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				component.getX()+dx, component.getY()+dy, 1, false);
		return me;
	}
	
	/**
	 * Creates Mouse DoubleClick in a JComponent.
	 * @param app the application currently running
	 * @param component the JComponent that is being clicked in
	 * @param dx (dx,dy) are offsets into the widget space.
	 * @param dy (dx,dy) are offsets into the widget space.
	 * @return
	 */
	public MouseEvent createDoubleClicked (Application app, JComponent component, int dx, int dy) {
		MouseEvent me = new MouseEvent(component, MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				component.getX()+dx, component.getY()+dy, 2, false);
		return me;
	}
}
