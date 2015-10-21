package model;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Font;

import lib.Background;
import lib.Point;
import view.View;

public final class Model {
	private Screen[] screens;
	Screen currentScreen;
	private View view;
	
	public Model() {
		this.view = new View(this);
		initializeScreens();
	}
	
	private void initializeScreens() {
		screens[Screens.HOME] = null;
		screens[Screens.GAME] = null;
		screens[Screens.INSTRUCTIONS] = null;
		screens[Screens.PREFERENCES] = null;
	}
	
	public void keyPressed(KeyEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Model m = new Model();
	}
}