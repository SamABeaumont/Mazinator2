package model;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Graphics;

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
	
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < options.length; i++) {
			options[i].mouseClicked(e);
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		for (int i = 0; i < options.length; i++) {
			options[i].mouseMoved(e);
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
}