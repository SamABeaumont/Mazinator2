package model;

import java.awt.Color;
import java.awt.Font;

import lib.Point;

public class DrawableString {
	private Point location;
	private String string;
	private Color color;
	private Font font;
	
	public DrawableString(int x, int y, String string, Color color, Font font) {
		this(new Point(x, y), string, color, font);
	}
	
	public DrawableString(Point location, String string, Color color, Font font) {
		this.location = location;
		this.string = string;
		this.color = color;
		this.font = font;
	}
	
	public Point getLocation() {
		return location.clone();
	}
	
	public String getString() {
		return string;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Font getFont() {
		return font;
	}
	
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
		return getClass().getName() + "[string=" + string + ",color=" + color + ",font="
				+ font + "]";
	}
}