package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import lib.Point;

/**
 * A class to represent text that can be drawn to the screen using a {@link Graphics} object.
 * @author Sam Beaumont
 */
public class DrawableString implements Displayable {
	private Point location;
	private String string;
	private Color color;
	private Font font;
	
	/**
	 * Initializes a new {@code DrawableString} object.
	 * @param x the x-coordinate of this string in relation to the component that it is being
	 * 			drawn to.
	 * @param y the y-coordinate of this string in relation to the component that it is being
	 * 			drawn to.
	 * @param string the text to be displayed.
	 * @param color the color of the text.
	 * @param font the font of the text.
	 */
	public DrawableString(int x, int y, String string, Color color, Font font) {
		this(new Point(x, y), string, color, font);
	}
	
	/**
	 * Initializes a new {@code DrawableString} object.
	 * @param location the x- and y- coordinates where the string is to be displayed, relative
	 * 			the frame that it is being displayed in.
	 * @param string the text to be displayed.
	 * @param color the color of the text.
	 * @param font the font of the text.
	 */
	public DrawableString(Point location, String string, Color color, Font font) {
		this.location = location;
		this.string = string;
		this.color = color;
		this.font = font;
	}
	
	/**
	 * @return the {@link Point} that represents the location at which this object is displayed
	 * 			relative to the frame that it is being drawn to.
	 */
	public Point getLocation() {
		return location.clone();
	}
	
	/**
	 * @return the x-coordinate of this object.
	 */
	public int getX() {
		return (int) location.getX();
	}
	
	/**
	 * @return the y-coordinate of this object.
	 */
	public int getY() {
		return (int) location.getY();
	}
	
	/**
	 * @return the text to be displayed.
	 */
	public String getString() {
		return string;
	}
	
	/**
	 * @return the color of the text to be displayed.
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * @return the font of the text to be displayed.
	 */
	public Font getFont() {
		return font;
	}
	
	/**
	 * Draws the text to the screen at the specified location, with the specified color and font.
	 */
	@Override
	public void display(Graphics g) {
		Color c = g.getColor();
		Font f = g.getFont();
		g.setColor(color);
		g.setFont(font);
		g.drawString(string, (int) location.getX(), (int) location.getY());
		g.setColor(c);
		g.setFont(f);
	}
	
	/**
	 * Tests another object for equality with this object.
	 * @param other the object to be tested for equality.
	 * @return {@code true} if {@code other} is a {@code DrawableString} object and the the text
	 * 			string, the color, the font, and the location of {@code other} are identical to
	 * 			those of this {@code DrawableString} object.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof DrawableString) {
			DrawableString s = (DrawableString) other;
			return string.equals(s.getString()) && color.equals(s.getColor())
					&& font.equals(s.getFont());
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[location=" + location + ",string=" + string + ",color="
				+ color + ",font=" + font + "]@" + Integer.toHexString(hashCode());
	}
}