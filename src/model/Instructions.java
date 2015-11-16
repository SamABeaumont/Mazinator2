package model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public final class Instructions extends Screen {
	public static final String[] INSTRUCTIONS = {
			"Use the the up and down arrow keys",
			"to move forward and backward.",
			"Use the left and right arrow keys to turn.",
			"Press M to shoot.",
			"Press P to pause.",
			"Enjoy."
	};
	
	private boolean showMenu = false;
	private Menu menu; 
	
	public Instructions(Model model) {
		super(model);
		
		menu = new Menu(getModel(), Preferences.getColor(),
				new DrawableString(40, 60, "instructions", Preferences.getWallsColor(),
						Preferences.getLargeFont()), new MenuOption[] {
								new MenuOption(getModel(), 100, 0, "play game") {
									@Override
									public void onClick() {
										showMenu = false;
										model.setScreen(Screens.GAME);
									}
								},
								
								new MenuOption(getModel(), 100, 1, "repeat instructions") {
									@Override
									public void onClick() {
										showMenu = false;
									}
								},
								
								new MenuOption(getModel(), 100, 2, "home menu") {
									@Override
									public void onClick() {
										showMenu = false;
										getModel().setScreen(Screens.HOME);
									}
								},
						});
	}
	
	@Override
	public void display(Graphics g) {
		if (showMenu) {
			menu.display(g);
		} else {
			new DrawableString(40, 60, "instructions", Preferences.getWallsColor(),
					Preferences.getLargeFont()).display(g);
			for (int i = 0; i < INSTRUCTIONS.length; i++) {
				new DrawableString(20, i * 20 + 100, INSTRUCTIONS[i], Preferences.getWallsColor(),
						Preferences.getSmallFont()).display(g);
			}
			
			new DrawableString(20, Preferences.getWindowHeight() - 50,
					"Press any key to continue...", Preferences.getWallsColor(),
					Preferences.getSmallFont()).display(g);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		showMenu = true;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (showMenu) {
			menu.mouseClicked(e);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (showMenu) {
			menu.mouseMoved(e);
		}
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[showMenu=" + showMenu + "]@"
				+ Integer.toHexString(hashCode());
	}
}