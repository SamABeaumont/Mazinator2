package view;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
	
	@Override
	public void keyPressed(KeyEvent e) {
		model.keyPressed(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		model.mouseMoved(e);
	}
}