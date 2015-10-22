package model;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Image;

public class Menu extends Screen {
	private Background background;
	private DrawableString title;
	private MenuOption[] options;
	
	public Menu(Model model, Background background, DrawableString title, MenuOption[] options) {
		super(model);
		this.background = background;
		this.title = title;
		this.options = options;
	}
	
	public Background getMenuBackground() {
		return background;
	}
	
	public DrawableString getTitle() {
		return title;
	}
	
	public MenuOption getMenuOptionAt(int option) {
		return options[option];
	}
	
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < options.length; i++) {
			options[i].mouseClicked(e);
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		for (int i = 0; i < options.length; i++) {
			options[i].mouseClicked(e);
		}
	}
	
	@Override
	public void display(Graphics g) {
		for (int row = 0; row < Preferences.getWindowHeight() / background.getHeight(); row++) {
			for (int col = 0; col < Preferences.getWindowWidth() / background.getHeight(); col++) {
				g.drawImage(background.getImage(), row * background.getHeight(),
						col * background.getHeight(), background);
			}
		}
		
		title.display(g);
		
		for (int option = 0; option < options.length; option++) {
			
		}
	}
}