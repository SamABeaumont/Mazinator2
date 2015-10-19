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
}