package model;

import java.awt.Graphics;

public abstract class Screen {
	private Model model;
	
	public Screen(Model model) {
		this.model = model;
	}
	
	protected Model getModel() {
		return model;
	}
	
	public abstract void display(Graphics g);
}