package model;

import java.awt.event.MouseEvent;
import java.awt.Graphics;

import lib.Background;

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
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
	
	@Override
	public void display(Graphics g) {
		
	}
}