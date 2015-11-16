package model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.util.Arrays;

import lib.Point;

public class Menu extends Screen {
	private Color backgroundColor;
	private DrawableString title;
	private MenuOption[] options;
	
	public Menu(Model model, Color backgroundColor, DrawableString title, MenuOption[] options) {
		super(model);
		this.backgroundColor = backgroundColor;
		this.title = title;
		this.options = options;
	}
	
	public void exit() {
		getModel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < options.length; i++) {
			options[i].mouseClicked(e);
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		boolean optionHasFocus = false;
		for (int i = 0; i < options.length; i++) {
			if (options[i].contains(new Point(e.getX(), e.getY()))) {
				options[i].setFocus(true);
				getModel().setCursor(new Cursor(Cursor.HAND_CURSOR));
				optionHasFocus = true;
			} else {
				options[i].setFocus(false);
			}
		}
		
		if (!optionHasFocus) {
			getModel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	@Override
	public void display(Graphics g) {
		Color c = g.getColor();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, Preferences.getWindowWidth(), Preferences.getWindowHeight());
		g.setColor(c);
		
		title.display(g);
		
		for (int option = 0; option < options.length; option++) {
			options[option].display(g);
		}
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[backgroundColor=" + backgroundColor + ",title=" + title
				+ ",options=" + Arrays.toString(options) + "]@" + Integer.toHexString(hashCode());
	}
}