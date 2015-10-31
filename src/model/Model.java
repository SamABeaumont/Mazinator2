package model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import lib.Rectangle;

public final class Model extends EventReciever implements Displayable {
	private Screen[] screens = new Screen[4];
	private Screen currentScreen;
	private Game game;
	
	public Model() {
		screens[Screens.HOME] = new Menu(this, Preferences.getColor(),
				new DrawableString(80, 80, "mazinator", Preferences.getWallsColor(),
						Preferences.getLargeFont()),
				new MenuOption[] {
						new MenuOption(this, Preferences.getColor(), Preferences.getWallsColor(),
								new Rectangle(0, 100, Preferences.getWindowWidth(),
										100),
										new DrawableString(90, 155, "play game",
												Preferences.getWallsColor(),
												Preferences.getMediumFont()),
										new DrawableString(90, 155, "play game",
												Preferences.getColor(),
												Preferences.getMediumFont())) {
							@Override
							protected void onClick() {
								System.out.println(getModel());
								getModel().setScreen(Screens.GAME);
							}
						},
						new MenuOption(this, Preferences.getColor(), Preferences.getWallsColor(),
								new Rectangle(0, 200, Preferences.getWindowWidth(), 100),
								new DrawableString(90, 255, "instructions",
										Preferences.getWallsColor(),
										Preferences.getMediumFont()),
								new DrawableString(90, 255, "instructions",
										Preferences.getColor(), Preferences.getMediumFont())) {
							@Override
							protected void onClick() {
								getModel().setScreen(Screens.INSTRUCTIONS);
							}
						},
						new MenuOption(this, Preferences.getColor(), Preferences.getWallsColor(),
								new Rectangle(0, 300, Preferences.getWindowWidth(), 100),
								new DrawableString(90, 355, "preferences",
										Preferences.getWallsColor(), Preferences.getMediumFont()),
								new DrawableString(90, 355, "preferences",
										Preferences.getColor(), Preferences.getMediumFont())) {
							@Override
							protected void onClick() {
								getModel().setScreen(Screens.PREFERENCES);
							}
						}
				});
		screens[Screens.GAME] = game;
		screens[Screens.INSTRUCTIONS] = new Screen(this) {
			private boolean showMenu = false;
			private DrawableString[] instructions = {
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
			
			private Menu menu = new Menu(Model.this, Preferences.getColor(),
					new DrawableString(20, 20, "instructions", Preferences.getWallsColor(),
							Preferences.getLargeFont()), new MenuOption[] {
									
							});
			
			@Override
			public void display(Graphics g) {
				if (showMenu) {
					menu.display(g);
				} else {
					for (int i = 0; i < instructions.length; i++) {
						instructions[i].display(g);
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				showMenu = true;
			}
		};
		screens[Screens.PREFERENCES] = new Preferences(this);
		currentScreen = screens[Screens.HOME];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		currentScreen.keyPressed(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		currentScreen.mouseMoved(e);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		currentScreen.mouseClicked(e);
	}
	
	void setScreen(int screen) {
		currentScreen.exit();
		if (screen == Screens.GAME) {
			game = new Game(this);
			currentScreen = screens[Screens.GAME];
			game.start();
		} else {
			currentScreen = screens[screen];
		}
	}
	
	@Override
	public void display(Graphics g) {
		currentScreen.display(g);
	}
}