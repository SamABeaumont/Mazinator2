package model;

import java.awt.Color;
import java.awt.Font;

import lib.Background;
import lib.Dimension;
import lib.Point;

public final class Preferences extends Menu {
	public static final int DEFAULT_WINDOW_WIDTH = 400;
	public static final int DEFAULT_WINDOW_HEIGHT = 600;
	public static final int DEFAULT_ZOOM = 1;
	public static final Background DEFAULT_BACKGROUND = new Background(Color.BLACK);
	public static final Color DEFAULT_WALLS_COLOR = new Color(63, 255, 63);
	public static final Color DEFAULT_TEXT_COLOR = new Color(63, 255, 63);
	public static final Font DEFAULT_TITLE_FONT = new Font("Consolas", Font.BOLD, 48);
	public static final Color DEFAULT_PLAYER_COLOR = new Color(63, 63, 255);
	public static final Color DEFAULT_ENEMIES_COLOR = new Color(255, 63, 63);
	
	private static Dimension windowSize = new Dimension(DEFAULT_WINDOW_WIDTH,
			DEFAULT_WINDOW_HEIGHT);
	private static int zoom = DEFAULT_ZOOM;
	private static Background background = DEFAULT_BACKGROUND;
	private static Color wallsColor = DEFAULT_WALLS_COLOR;
	private static Font titleFont = DEFAULT_TITLE_FONT;
	private static Color playerColor = DEFAULT_PLAYER_COLOR;
	private static Color enemiesColor = DEFAULT_ENEMIES_COLOR;
	
	public Preferences (Model model) {
		super(model, background,
				new DrawableString(new Point(20, 20), "preferences", wallsColor, titleFont),
				new MenuOption[] {
					
				});
	}
	
	public static Dimension getWindowSize() {
		return windowSize;
	}
	
	public static int getZoom() {
		return zoom;
	}
	
	public static Background getBackground() {
		return background;
	}
	
	public static Color getWallsColor() {
		return wallsColor;
	}
	
	public static Color getPlayerColor() {
		return playerColor;
	}
	
	public static Color getEnemiesColor() {
		return enemiesColor;
	}
}