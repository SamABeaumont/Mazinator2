package view;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;

import model.Model;

public final class View extends Sketchpad {
	private Model model;
	
	public View () {
		model = new Model();
		start();
	}
	
	@Override
	public void setup (Graphics g) {
		setTitle("mazinator");
		setSize(400, 600);
		setResizable(false);
	}
	
	@Override
	public void draw(Graphics g) {
		if (model != null) {
			model.display(g);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (model != null) {
			model.keyPressed(e);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("called View.mouseClicked(MouseEvent)");
		if (model != null) {
			model.mouseClicked(e);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (model != null) {
			model.mouseMoved(e);
		}
	}
	
	public static void main (String[] args) {
		@SuppressWarnings("unused")
		View view = new View();
	}
}