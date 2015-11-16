package model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JOptionPane;

import lib.Rectangle;

public final class Model extends Sketchpad {
	private static boolean created = false;
	
	private Screen[] screens = new Screen[4];
	private Screen currentScreen;
	private Game game;
	
	public Model() {
		if (created) {
			JOptionPane.showMessageDialog(null,
					"Only one mazinator game may be running at any given time", "",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		created = true;
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
		screens[Screens.INSTRUCTIONS] = new Instructions(this);
		screens[Screens.PREFERENCES] = new Preferences(this);
		currentScreen = screens[Screens.HOME];
		
		start();
	}
	
	@Override
	public void setup(Graphics g) {
		setTitle("mazinator");
		setSize(400, 600);
		setResizable(false);
	}
	
	@Override
	public void draw(Graphics g) {
		currentScreen.display(g);
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
			System.out.println("game=" + game);
			screens[Screens.GAME] = game;
			currentScreen = screens[Screens.GAME];
			game.start();
		} else {
			currentScreen = screens[screen];
		}
	}
	
	public String toString() {
		return getClass().getName() + "[" + super.toString()
				+ ",screens=" + Arrays.toString(screens)
				+ ",currentScreen=" + currentScreen + ",game=" + game + "]@"
				+ Integer.toHexString(hashCode());
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Model model = new Model();
	}
}