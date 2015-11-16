package model;

import java.awt.Graphics;
import java.util.Random;
import java.util.Stack;

public final class Maze implements Displayable {
	public static final int WIDTH = 20;
	public static final int LENGTH = 150;
	
	private static Maze currentMaze;
	private static Maze nextMaze;
	private static int nextY = -1;
	
	private Cell[][] maze = new Cell[LENGTH][WIDTH];
	private int offset = 0;
	private int count = 0;
	
	public static void initialize() {
		currentMaze = new Maze();
		nextMaze = new Maze();
	}
	
	private Maze() {
		this(nextY);
	}
	
	private Maze(int startY) {
		// initialize all of the cells in the maze
		for (int i = 0; i < LENGTH; i++) {
			for (int j = 0; j < WIDTH; j++) {
				maze[i][j] = new Cell();
			}
		}
		
		if (startY >= 0 && startY < WIDTH) {
			maze[0][startY].removeWall(Direction.DOWN);
		}
		
		Stack<Cell> cells = new Stack<Cell>();
		int x = 0;
		Random r = new Random();
		int y = ((startY >= 0 && startY < WIDTH) ? startY : r.nextInt(WIDTH));
		cells.push(maze[x][y]);
		
		boolean[] adjacent = hasAdjacent(x, y);
		int cell = chooseCell(adjacent);
		cells.peek().removeWall(cell);
		int from;
		if (cell == Direction.UP) {
			x++;
			from = Direction.DOWN;
		} else if (cell == Direction.LEFT) {
			y--;
			from = Direction.RIGHT;
		} else { // cell == Direction.RIGHT
			y++;
			from = Direction.LEFT;
		}
		generate(cells, x, y, from);
		
		// remove a random wall from the top row of the maze
		nextY = r.nextInt(WIDTH);
		maze[LENGTH - 1][nextY].removeWall(Direction.UP);
		
		// loop through the maze and randomly remove some walls, to make it more easily navigable
		for (int i = LENGTH - 2; i >= 1; i--) {
			for (int j = 1; j < WIDTH - 1; j++) {
				if (r.nextInt(5) == 0) {
					int rand = r.nextInt(4);
					maze[i][j].removeWall(rand);
					if (rand == Direction.UP) {
						maze[i + 1][j].removeWall(Direction.DOWN);
					} else if (rand == Direction.DOWN) {
						maze[i - 1][j].removeWall(Direction.UP);
					} else if (rand == Direction.LEFT) {
						maze[i][j - 1].removeWall(Direction.RIGHT);
					} else { // rand == Direction.RIGHT
						maze[i][j + 1].removeWall(Direction.LEFT);
					}
				}
			}
		}
	}
	
	private void generate(Stack<Cell> cells, int x, int y, int from) {
		if (from < 0 || from >= 4) {
			throw new IllegalArgumentException("from must be a valid Direction.");
		}
		
		cells.push(maze[x][y]);
		cells.peek().removeWall(from);
		boolean[] adjacent = hasAdjacent(x, y);
		if (adjacent[4]) {
			int cell = chooseCell(adjacent);
			cells.peek().removeWall(cell);
			int x2 = x;
			int y2 = y;
			if (cell == Direction.UP) {
				x2++;
				from = Direction.DOWN;
			} else if (cell == Direction.DOWN) {
				x2--;
				from = Direction.UP;
			} else if (cell == Direction.LEFT) {
				y2--;
				from = Direction.RIGHT;
			} else { // cell == Direction.RIGHT
				y2++;
				from = Direction.LEFT;
			}
			generate(cells, x2, y2, from);
			adjacent = hasAdjacent(x, y);
			if (adjacent[4]) {
				cell = chooseCell(adjacent);
				cells.peek().removeWall(cell);
				int from2;
				int x3 = x;
				int y3 = y;
				if (cell == Direction.UP) {
					x3++;
					from2 = Direction.DOWN;
				} else if (cell == Direction.DOWN) {
					x3--;
					from2 = Direction.UP;
				} else if (cell == Direction.LEFT) {
					y3--;
					from2 = Direction.RIGHT;
				} else { // cell == Direction.RIGHT
					y3++;
					from2 = Direction.LEFT;
				}
				generate(cells, x3, y3, from2);
				adjacent = hasAdjacent(x, y);
				if (adjacent[4]) {
					cell = chooseCell(adjacent);
					cells.peek().removeWall(cell);
					int from3;
					int x4 = x;
					int y4 = y;
					if (cell == Direction.UP) {
						x4++;
						from3 = Direction.DOWN;
					} else if (cell == Direction.DOWN) {
						x4--;
						from3 = Direction.UP;
					} else if (cell == Direction.LEFT) {
						y4--;
						from3 = Direction.RIGHT;
					} else { // cell == Direction.RIGHT
						y4++;
						from3 = Direction.LEFT;
					}
					generate(cells, x4, y4, from3);
				}
			}
		}
		
		cells.pop();
	}
	
	private boolean[] hasAdjacent(int x, int y) {
		boolean[] adjacent = new boolean[5];
		if (x != LENGTH - 1) {
			if (maze[x + 1][y].isClosed()) {
				adjacent[Direction.UP] = true;
			}
		}
		
		if (x != 0) {
			if (maze[x - 1][y].isClosed()) {
				adjacent[Direction.DOWN] = true;
			}
		}
		
		if (y != 0) {
			if (maze[x][y - 1].isClosed()) {
				adjacent[Direction.LEFT] = true;
			} 
		}
		
		if (y != WIDTH - 1) {
			if (maze[x][y + 1].isClosed()) {
				adjacent[Direction.RIGHT] = true;
			}
		}
		
		adjacent[4] = adjacent[Direction.UP] && adjacent[Direction.DOWN]
				&& adjacent[Direction.LEFT] && adjacent[Direction.RIGHT];
		return adjacent;
	}
	
	private int chooseCell (boolean[] adjacent) {
		if (adjacent.length != 5) {
			throw new IllegalArgumentException();
		}
		
		Random r = new Random();
		if (adjacent[Direction.UP] && !adjacent[Direction.DOWN] && !adjacent[Direction.LEFT]
				&& !adjacent[Direction.RIGHT]) {
			return Direction.UP;
		} else if (!adjacent[Direction.UP] && adjacent[Direction.DOWN] && !adjacent[Direction.LEFT]
				&& !adjacent[Direction.RIGHT]) {
			return Direction.DOWN;
		} else if (!adjacent[Direction.UP] && !adjacent[Direction.DOWN] && adjacent[Direction.LEFT]
				&& !adjacent[Direction.RIGHT]) {
			return Direction.LEFT;
		} else if (!adjacent[Direction.UP] && !adjacent[Direction.DOWN] && !adjacent[Direction.LEFT]
				&& adjacent[Direction.RIGHT]) {
			return Direction.RIGHT;
		} else if (adjacent[Direction.UP] && adjacent[Direction.DOWN] && !adjacent[Direction.LEFT]
				&& !adjacent[Direction.RIGHT]) {
			return r.nextInt(2);
		} else if (adjacent[Direction.UP] && !adjacent[Direction.DOWN] && adjacent[Direction.LEFT]
				&& !adjacent[Direction.RIGHT]) {
			int n = r.nextInt(2);
			if (n == 0) {
				return Direction.UP;
			} else {
				return Direction.LEFT;
			}
		} else if (adjacent[Direction.UP] && !adjacent[Direction.DOWN] && !adjacent[Direction.LEFT]
				&& adjacent[Direction.RIGHT]) {
			int n = r.nextInt(2);
			if (n == 0) {
				return Direction.UP;
			} else {
				return Direction.RIGHT;
			}
		} else if (!adjacent[Direction.UP] && adjacent[Direction.DOWN] && adjacent[Direction.LEFT]
				&& !adjacent[Direction.RIGHT]) {
			return r.nextInt(2) + 1;
		} else if (!adjacent[Direction.UP] && adjacent[Direction.DOWN] && !adjacent[Direction.LEFT]
				&& adjacent[Direction.RIGHT]) {
			int n = r.nextInt(2);
			if (n == 0) {
				return Direction.DOWN;
			} else {
				return Direction.RIGHT;
			}
		} else if (!adjacent[Direction.UP] && !adjacent[Direction.DOWN] && adjacent[Direction.LEFT]
				&& adjacent[Direction.RIGHT]) {
			return r.nextInt(2) + 2;
		} else if (adjacent[Direction.UP] && adjacent[Direction.DOWN] && adjacent[Direction.LEFT]
				&& !adjacent[Direction.RIGHT]) {
			return r.nextInt(3);
		} else if (adjacent[Direction.UP] && !adjacent[Direction.DOWN] && adjacent[Direction.LEFT]
				&& adjacent[Direction.RIGHT]) {
			int n = r.nextInt(3);
			if (n == 0) {
				return Direction.UP;
			} else {
				return n + 1;
			}
		} else if (adjacent[Direction.UP] && adjacent[Direction.DOWN] && !adjacent[Direction.LEFT]
				&& adjacent[Direction.RIGHT]) {
			int n = r.nextInt(3);
			if (n == 2) {
				return Direction.DOWN;
			} else {
				return n;
			}
		} else if (!adjacent[Direction.UP] && adjacent[Direction.DOWN] && adjacent[Direction.LEFT]
				&& adjacent[Direction.RIGHT]) {
			return r.nextInt(3) + 1;
		} else { // adjacent[Direction.UP] && adjacent[Direction.DOWN] && adjacent[Direction.LEFT] 
				 // && adjacent[Direction.RIGHT]
			return r.nextInt(4);
		}
	}
	
	public boolean isWall(int px, int py) {
		Cell loc = maze[px / 25][py / 25];
		int cx = px % 25;
		int cy = py % 25;
		if (cx >= 2 && cx < 23) {
			return true;
		} else if ((cx < 2 || cx > 22) && cy < 2 || (cx < 2 || cx > 22) && cy > 22) {
			return false;
		} else if (cx >= 2 && cx < 22 && cy < 2) {
			return loc.isTopOpen();
		} else if (cx >= 2 && cx < 22 && cy > 22) {
			return loc.isBottomOpen();
		} else if (cx < 2 && cy >= 2 && cy < 22) {
			return loc.isLeftOpen();
		} else { // cx > 22 && cy >= 2 && cy < 22
			return loc.isRightOpen();
		}
	}
	
	public int getOffset() {
		return offset;
	}
	
	public static void displayMazes(Graphics g) {
		if (currentMaze.getOffset() == 150) {
			currentMaze = nextMaze;
			nextMaze = new Maze();
		}
		
		currentMaze.display(g);
		nextMaze.display(g);
	}
	
	@Override
	public void display(Graphics g) {
		offset = ++count / 5;
		
	}
}