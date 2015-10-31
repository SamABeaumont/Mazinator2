package model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public final class Instructions extends Screen {
	public static final DrawableString[] INSTRUCTIONS = {
			new DrawableString(20, 20,
					"Use the the up and down arrow keys to move forward and backward.",
					Preferences.getWallsColor(), Preferences.getSmallFont()),
			new DrawableString(20, 40, "Use the left and right arrow keys to turn.",
					Preferences.getWallsColor(), Preferences.getSmallFont()),
			new DrawableString(20, 60, "Press M to shoot.", Preferences.getWallsColor(),
					Preferences.getSmallFont()),
			new DrawableString(20, 80, "Press P to pause.", Preferences.getWallsColor(),
					Preferences.getSmallFont()),
			new DrawableString(20, 100, "Enjoy.", Preferences.getWallsColor(),
					Preferences.getSmallFont())
	};
	
	private boolean showMenu = false;
	private Menu menu; 
	
	public Instructions(Model model) {
		super(model);
		System.out.println(this);
		menu = new Menu(getModel(), Preferences.getColor(),
				new DrawableString(20, 20, "instructions", Preferences.getWallsColor(),
						Preferences.getLargeFont()), new MenuOption[] {
								
						});
	}
	
	@Override
	public void display(Graphics g) {
		if (showMenu) {
			menu.display(g);
		} else {
			for (int i = 0; i < INSTRUCTIONS.length; i++) {
				INSTRUCTIONS[i].display(g);
			}
			
			new DrawableString(20, Preferences.getWindowHeight() - 20,
					"Press any key to continue...", Preferences.getWallsColor(),
					Preferences.getSmallFont()).display(g);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		showMenu = true;
	}
	
	public static void main(String[] args) {
		Instructions i = new Instructions(new Model());
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[showMenu=" + showMenu + "]@" + hashCode();
	}
}