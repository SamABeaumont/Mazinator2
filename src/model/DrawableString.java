package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import lib.Point;

public class DrawableString implements Displayable {
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
	
	public int getX() {
		return (int) location.getX();
	}
	
	public int getY() {
		return (int) location.getY();
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
	
	public void display(Graphics g) {
		Color c = g.getColor();
		Font f = g.getFont();
		g.setColor(color);
		g.setFont(font);
		g.drawString(string, (int) location.getX(), (int) location.getY());
		g.setColor(c);
		g.setFont(f);
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
		return getClass().getName() + "[location=" + location + ",string=" + string + ",color="
				+ color + ",font=" + font + "]";
	}
}