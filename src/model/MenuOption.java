package model;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Graphics;

import lib.Rectangle;

/**
 * A class to represent an option in a menu.
 * @author Sam Beaumont
 */
public final class MenuOption implements Displayable {
	private Model model;
	private boolean hasFocus = false;
	private Color color;
	private Color focusColor;
	private Rectangle bounds;
	private DrawableString text;
	private DrawableString focusText;
	private int screen;
	
	/**
	 * Initializes a new {@code MenuOption} object with exactly one {@link Background} and exactly
	 * one {@link DrawableString}, so that the focused and unfocused states of this object are
	 * visually indistinguishable.
	 * @param model The {@link Model} object that will receive notifications when this
	 * 			{@code MenuOption} is clicked
	 * @param background The {@link Background} object to be displayed with this {@code MenuOption}
	 * @param bounds The {@link Rectangle} object that contains information on the height, width,
	 * 			and location of the {@code MenuOption}
	 * @param text A formatted {@link DrawableString} that is displayed with the {@code MenuOption}
	 * @param screen The {@link Screen} object to be displayed when this {@code MenuOption} is
	 * clicked.
	 */
	public MenuOption(Model model, Color color, Rectangle bounds, DrawableString text,
			int screen) {
		this(model, color, color, bounds, text, text, screen);
	}
	
	/**
	 * Initializes a new {@code MenuOption} object with exactly two {@link Background}s and exactly
	 * two {@link DrawableString}s, so that the focused and unfocused states of this object are
	 * visually distinguishable.
	 * @param model The {@link Model} object that will receive notifications when this
	 * 			{@code MenuOption} is clicked
	 * @param background The {@link Background} object to be displayed with this {@code MenuOption}
	 * 			when it does not have focus
	 * @param focusBackground the {@link Background} object to be displayed with this
	 * 			{@code MenuOption} when it has focus
	 * @param bounds The {@link Rectangle} object that contains information on the height, width,
	 * 			and location of the {@code MenuOption}
	 * @param text A formatted {@link DrawableString} that is displayed with the {@code MenuOption}
	 * @param screen The {@link Screen} object to be displayed when this {@code MenuOption} is
	 * clicked.
	 */
	public MenuOption(Model model, Color color, Color focusColor,
			Rectangle bounds, DrawableString text, DrawableString focusText, int screen) {
		this.color = color;
		this.focusColor = focusColor;
		this.bounds = bounds;
		this.text = text;
		this.focusText = text;
		this.screen = screen;
	}
	
	protected Rectangle getBounds() {
		return bounds.clone();
	}
	
	/**
	 * Called if and only if a {@link MouseEvent#MOUSE_CLICKED} event occurs while the menu
	 * 			containing this {@code MenuOption} is being displayed. If the location of the
	 * 			{@link MouseEvent} is within the bounds of this {@link MenuOption} object,
	 * 			
	 * @param e
	 * @see MouseListener#mouseClicked(MouseEvent)
	 */
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
	public void display(Graphics g) {
		Color color;
		if (hasFocus) {
			color = focusColor;
		} else {
			color = this.color;
		}
		
		Color c = g.getColor();
		g.setColor(color);
		g.fillRect((int) bounds.getX(), (int) bounds.getY(), (int) bounds.getWidth(),
				(int) bounds.getHeight());
		
		if (hasFocus) {
			focusText.display(g);
		} else {
			text.display(g);
		}
		
		g.setColor(c);
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[hasFocus = " + hasFocus + ",color=" + color
				+ ",focusColor=" + focusColor + ",bounds=" + bounds + ",text=" + text
				+ ",focusText=" + focusText + "]";
	}
}