package model;

import java.awt.Graphics;

/**
 * An interface to represent objects that can be displayed onscreen.
 * @author Sam Beaumont
 */
public interface Displayable {
	/**
	 * Should display the given object onscreen.
	 * @param g The {@link Graphics} object used to draw to the screen.
	 */
	public void display(Graphics g);
}