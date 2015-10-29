package model;

import java.awt.event.KeyEvent;
import java.awt.Graphics;

public final class Game extends Screen {
	private boolean paused = false;
	private Maze[] mazes = new Maze[3];
	
	public Game(Model model) {
		super(model);
	}
	
	void start() {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_P) {
			paused = true;
		}
	}
	
	@Override
	public void display(Graphics g) {
		
	}
}