package model;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;

/**
 * A class to represent
 * @author Sam Beaumont
 */
public final class Game extends Screen {
	private boolean paused = false;
	private PausedMenu pausedMenu;
	
	public Game(Model model) {
		super(model);
		pausedMenu = new PausedMenu();
	}
	
	void start() {
		Maze.initialize();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_P && !paused) {
			paused = true;
		} else if (paused) {
			pausedMenu.keyPressed(e);
		}
		System.out.println("paused=" + paused);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (paused) {
			pausedMenu.mouseClicked(e);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused) {
			pausedMenu.mouseMoved(e);
		}
	}
	
	@Override
	public void display(Graphics g) {		
		if (paused) {
			pausedMenu.display(g);
		}
	}
	
	private class PausedMenu extends Screen {
		private boolean showInstructions = false;
		private Menu menu = new Menu(getModel(), Preferences.getColor(),
				new DrawableString(60, 60, "game paused", Preferences.getWallsColor(),
						Preferences.getLargeFont()),
				new MenuOption[] {
				new MenuOption(getModel(), 100, 0, "instructions") {
					@Override
					public void onClick() {
						showInstructions = true;
					}
				},
				new MenuOption(getModel(), 100, 1, "resume game") {
					@Override
					public void onClick() {
						Game.this.paused = false;
					}
				},
				new MenuOption(getModel(), 100, 2, "quit") {
					@Override
					public void onClick() {
						Game.this.exit();
						getModel().setScreen(Screens.HOME);
					}
				}
		});
		
		public PausedMenu() {
			super(Game.this.getModel());
		}
		
		@Override
		public void display(Graphics g) {
			Color c = g.getColor();
			g.setColor(new Color(0, 0, 0, 127));
			g.fillRect(0, 0, Preferences.getWindowWidth(), Preferences.getWindowHeight());
			g.setColor(c);
			if (showInstructions) {
				new DrawableString(80, 50, "game paused", Preferences.getWallsColor(),
						Preferences.getLargeFont()).display(g);
				for (int i = 0; i < Instructions.INSTRUCTIONS.length; i++) {
					new DrawableString(20, i * 20 + 100, Instructions.INSTRUCTIONS[i],
							Preferences.getWallsColor(), Preferences.getSmallFont()).display(g);
				}
				
				new DrawableString(20, Preferences.getWindowHeight() - 50,
						"Press any key to continue...", Preferences.getWallsColor(),
						Preferences.getSmallFont()).display(g);
			} else {
				menu.display(g);
			}
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			showInstructions = false;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (!showInstructions) {
				menu.mouseClicked(e);
			}
		}
		
		public void mouseMoved(MouseEvent e) {
			if (!showInstructions) {
				menu.mouseMoved(e);
			}
		}
	}
}