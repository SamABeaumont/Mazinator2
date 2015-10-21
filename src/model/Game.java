package model;

import java.awt.Graphics;

public final class Game extends Screen {
	private Maze[] mazes = new Maze[3];
	
	public Game(Model model) {
		super(model);
	}
	
	void start() {
		
	}
	
	@Override
	public void display(Graphics g) {}
}