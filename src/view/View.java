package view;

import java.awt.Graphics;

import model.Model;

public final class View extends Sketchpad {
	private Model model;
	
	public View (Model model) {
		this.model = model;
	}
	
	@Override
	public void setup (Graphics g) {
		setSize(400, 600);
		setResizable(false);
	}
	
	@Override
	public void draw(Graphics g) {
		
	}
}