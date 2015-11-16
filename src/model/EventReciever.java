package model;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;

/**
 * An abstract class to represent objects that listen for AWT events.
 * @author Sam Beaumont
 */
public abstract class EventReciever {
	public void windowLostFocus(WindowEvent e) {}
	
	public void windowGainedFocus(WindowEvent e) {}
	
	public void windowActivated(WindowEvent e) {}
	
	public void windowClosed(WindowEvent e) {}
	
	public void windowClosing(WindowEvent e) {}
	
	public void windowDeactivated(WindowEvent e) {}
	
	public void windowDeiconified(WindowEvent e) {}
	
	public void windowIconified(WindowEvent e) {}
	
	public void windowOpened(WindowEvent e) {}
	
	public void windowStateChanged(WindowEvent e) {}
	
	public void focusLost(FocusEvent e) {}
	
	public void focusGained(FocusEvent e) {}
	
	public void keyPressed(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
	
	public void keyReleased(KeyEvent e) {}
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mouseDoubleclicked(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseDragged(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {}
	
	public void mouseWheelMoved(MouseWheelEvent e) {}
}