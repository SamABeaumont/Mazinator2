package model;

import java.awt.Color;
import java.awt.Rectangle;

import lib.Point;

public final class MenuOption {
	private Color bgColor;
	private Color bgFocusColor;
	private Rectangle bounds;
	private String text;
	private Color textColor;
	private Color textFocusColor;
	private Point textLoc;
	
	public MenuOption(Color bgColor, Color bgFocusColor, Rectangle bounds, String text,
			Color textColor, Color textFocusColor, Point textLoc) {
		this.bgColor = bgColor;
		this.bgFocusColor = bgFocusColor;
		this.bounds = bounds;
		this.text = text;
		this.textColor = textColor;
		this.textFocusColor = textFocusColor;
		this.textLoc = textLoc;
	}
	
	public Color getBackgroundColor() {
		return bgColor;
	}
	
	public Color getBackgroundFocusColor() {
		return bgFocusColor;
	}
	
	public Rectangle getBounds() {
		return (Rectangle) bounds.clone();
	}
	
	public String getText() {
		return text;
	}
	
	public Color getTextColor() {
		return textColor;
	}
	
	public Color getTextFocusColor() {
		return textFocusColor;
	}
	
	public Point getTextLoc() {
		return textLoc.clone();
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[bgColor=" + bgColor + ",bgFocusColor=" + bgFocusColor
				+ ",bounds=" + bounds + ",text=" + text + ",textColor=" + textColor +
				",textFocusColor=" + textFocusColor + ",textLoc=" + textLoc + "]";
		
	}
}