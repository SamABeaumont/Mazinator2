package model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Graphics;

import lib.Point;
import lib.Rectangle;

/**
 * A class to represent an option in a menu.
 * @author Sam Beaumont
 */
public abstract class MenuOption extends EventReciever implements Displayable {
	private Model model;
	private boolean hasFocus = false;
	private Color color;
	private Color focusColor;
	private Rectangle bounds;
	private DrawableString text;
	private DrawableString focusText;
	
	public MenuOption(Model model, int offset, int num, String text) {
		this(model, Preferences.getColor(), Preferences.getWallsColor(),
				new Rectangle(0, offset + num * 100, Preferences.getWindowWidth(), 100),
				new DrawableString(90, offset + num * 100 + 55, text, Preferences.getWallsColor(),
						Preferences.getMediumFont()),
				new DrawableString(90, offset + num * 100 + 55, text, Preferences.getColor(),
						Preferences.getMediumFont()));
	}
	
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
	public MenuOption(Model model, Color color, Rectangle bounds, DrawableString text) {
		this(model, color, color, bounds, text, text);
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
			Rectangle bounds, DrawableString text, DrawableString focusText) {
		this.model = model;
		this.color = color;
		this.focusColor = focusColor;
		this.bounds = bounds;
		this.text = text;
		this.focusText = focusText;
	}
	
	protected final Rectangle getBounds() {
		return bounds.clone();
	}
	
	protected final Model getModel() {
		return model;
	}
	
	/**
	 * Called if and only if a {@link MouseEvent#MOUSE_CLICKED} event occurs while the menu
	 * 			containing this {@code MenuOption} is being displayed. If the location of the
	 * 			{@link MouseEvent} is within the bounds of this {@link MenuOption} object,
	 * 			
	 * @param e
	 * @see MouseListener#mouseClicked(MouseEvent)
	 */
	@Override
	public final void mouseClicked(MouseEvent e) {
		if (bounds.contains((double) e.getX(), (double) e.getY())) {
			hasFocus = true;
			onClick();
		} else {
			hasFocus = false;
		}
	}
	
	abstract void onClick();
	
	public boolean contains(Point p) {
		return bounds.contains(p);
	}
	
	public final void setFocus(boolean hasFocus) {
		this.hasFocus = hasFocus;
	}
	
	@Override
	public final void display(Graphics g) {
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
	public final String toString() {
		return getClass().getName() + "[hasFocus = " + hasFocus + ",color=" + color
				+ ",focusColor=" + focusColor + ",bounds=" + bounds + ",text=" + text
				+ ",focusText=" + focusText + "]@" + Integer.toHexString(hashCode());
	}
}