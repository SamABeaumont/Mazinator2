package model;

import java.awt.Color;

import lib.Dimension;

public final class Preferences {
	public static final int DEFAULT_WINDOW_WIDTH = 400;
	public static final int DEFAULT_WINDOW_HEIGHT = 600;
	public static final int DEFAULT_ZOOM = 1;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
	public static final Color DEFAULT_WALLS_COLOR = new Color(63, 255, 63);
	public static final Color DEFAULT_PLAYER_COLOR = new Color(63, 63, 255);
	public static final Color DEFAULT_ENEMIES_COLOR = new Color(255, 63, 63);
	
	private static Dimension windowSize = new Dimension(DEFAULT_WINDOW_WIDTH,
			DEFAULT_WINDOW_HEIGHT);
	private static int zoom = DEFAULT_ZOOM;
	private static Color bgColor = DEFAULT_BACKGROUND_COLOR;
	private static Color wallsColor = DEFAULT_WALLS_COLOR;
	private static Color playerColor = DEFAULT_PLAYER_COLOR;
	private static Color enemiesColor = DEFAULT_ENEMIES_COLOR;
	
	private Preferences () {}
	
	public static Dimension getWindowSize() {
		return windowSize;
	}
	
	public static int getZoom() {
		return zoom;
	}
	
	public static Color getBackgroundColor() {
		return bgColor;
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