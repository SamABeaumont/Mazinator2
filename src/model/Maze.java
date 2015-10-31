package model;

import java.awt.Graphics;

public final class Maze implements Displayable {
	public static final int WIDTH = 20;
	public static final int LENGTH = 150;
	
	private Cell[][] maze = new Cell[LENGTH][WIDTH];
	
	public Maze() {
		this(-1);
	}
	
	public Maze(int startY) {
		// initialize all of the cells in the maze
		for (int i = 0; i < LENGTH; i++) {
			for (int j = 0; j < WIDTH; j++) {
				maze[i][j] = new Cell();
			}
		}
		
		if (startY >= 0 && startY < WIDTH) {
			maze[0][startY].removeWall(Direction.DOWN);
		}
		
		
	}
	
	@Override
	public void display(Graphics g) {
		
	}
}