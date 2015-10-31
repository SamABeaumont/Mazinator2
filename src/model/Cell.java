package model;

public final class Cell {
	private boolean topOpen;
	private boolean bottomOpen;
	private boolean leftOpen;
	private boolean rightOpen;
	
	public Cell() {
		topOpen = false;
		bottomOpen = false;
		leftOpen = false;
		rightOpen = false;
	}
	
	public Cell(boolean topOpen, boolean bottomOpen, boolean leftOpen, boolean rightOpen) {
		this.topOpen = topOpen;
		this.bottomOpen = bottomOpen;
		this.leftOpen = leftOpen;
		this.rightOpen = rightOpen;
	}
	
	public void removeWall(Direction dir) {
		if (dir == Direction.UP) {
			topOpen = true;
		} else if (dir == Direction.DOWN) {
			bottomOpen = true;
		} else if (dir == Direction.LEFT) {
			leftOpen = true;
		} else if (dir == Direction.RIGHT) {
			rightOpen = true;
		}
	}
	
	public boolean isTopOpen() {
		return topOpen;
	}
	
	public boolean isBottomOpen() {
		return bottomOpen;
	}
	
	public boolean isLeftOpen() {
		return leftOpen;
	}
	
	public boolean isRightOpen() {
		return rightOpen;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Cell) {
			Cell c = (Cell) other;
			return c.topOpen == topOpen && c.bottomOpen == bottomOpen && c.leftOpen == leftOpen
					&& c.rightOpen == rightOpen;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[topOpen=" + topOpen + ",bottomOpen=" + bottomOpen
				+ ",leftOpen=" + leftOpen + ",rightOpen=" + rightOpen + "]@" + hashCode();
	}
}