package model;

import java.awt.event.MouseEvent;

import lib.Rectangle;

public final class MenuOption {
	private Model model;
	private boolean hasFocus;
	private Background background;
	private Background focusBackground;
	private Rectangle bounds;
	private DrawableString text;
	private DrawableString focusText;
	private int screen;
	
	public MenuOption(Model model, Background background, Rectangle bounds, DrawableString text,
			int screen) {
		this(background, background, bounds, text, text, screen);
	}
	
	public MenuOption(Background background, Background focusBackground, Rectangle bounds,
			DrawableString text, DrawableString focusText, int screen) {
		this.background = background;
		this.focusBackground = focusBackground;
		this.bounds = bounds;
		this.text = text;
		this.focusText = text;
		this.screen = screen;
	}
	
	public Background getBackground() {
		if (hasFocus) {
			return focusBackground;
		} else {
			return background;
		}
	}
	
	public Rectangle getBounds() {
		return (Rectangle) bounds.clone();
	}
	
	public DrawableString getText() {
		if (hasFocus) {
			return focusText;
		} else {
			return text;
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (bounds.contains((double) e.getX(), (double) e.getY())) {
			hasFocus = true;
			model.setScreen(screen);
		} else {
			hasFocus = false;
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if (bounds.contains((double) e.getX(), (double) e.getY())) {
			hasFocus = true;
		} else {
			hasFocus = false;
		}
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[hasFocus = " + hasFocus + ",background=" + background
				+ ",focusBackground=" + focusBackground + ",bounds=" + bounds + ",text=" + text
				+ ",focusText=" + focusText + "]";
	}
}