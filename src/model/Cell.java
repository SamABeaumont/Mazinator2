package model;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * A class to represent cells that are stored within {@link Maze} objects.
 * @author Sam Beaumont
 */
public final class Cell {
	private static BufferedImage open;
	private static BufferedImage topClosed;
	private static BufferedImage bottomClosed;
	private static BufferedImage leftClosed;
	private static BufferedImage rightClosed;
	private static BufferedImage topBottomClosed;
	private static BufferedImage topLeftClosed;
	private static BufferedImage topRightClosed;
	private static BufferedImage bottomLeftClosed;
	private static BufferedImage bottomRightClosed;
	private static BufferedImage leftRightClosed;
	private static BufferedImage topBottomLeftClosed;
	private static BufferedImage topBottomRightClosed;
	private static BufferedImage topLeftRightClosed;
	private static BufferedImage bottomLeftRightClosed;
	private static BufferedImage allClosed;
	
	static {
		try {
			open = ImageIO.read(new File("open.gif"));
			topClosed = ImageIO.read(new File("topClosed.gif"));
			bottomClosed = ImageIO.read(new File("bottomClosed.gif"));
			leftClosed = ImageIO.read(new File("leftClosed.gif"));
			rightClosed = ImageIO.read(new File("rightClosed.gif"));
			topBottomClosed = ImageIO.read(new File("topBottomClosed.gif"));
			topLeftClosed = ImageIO.read(new File("topLeftClosed.gif"));
			topRightClosed = ImageIO.read(new File("topRightClosed.gif"));
			bottomLeftClosed = ImageIO.read(new File("bottomLeftClosed.gif"));
			bottomRightClosed = ImageIO.read(new File("bottomRightClosed.gif"));
			leftRightClosed = ImageIO.read(new File("leftRightClosed.gif"));
			topBottomLeftClosed = ImageIO.read(new File("topBottomLeftClosed.gif"));
			topBottomRightClosed = ImageIO.read(new File("topBottomRightClosed.gif"));
			topLeftRightClosed = ImageIO.read(new File("topLeftRightClosed.gif"));
			allClosed = ImageIO.read(new File("allClosed.gif"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unable to load image files to display maze.", "",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private boolean topOpen;
	private boolean bottomOpen;
	private boolean leftOpen;
	private boolean rightOpen;
	private BufferedImage image;
	
	/**
	 * Initializes a new {@code Cell} object with all walls closed.
	 */
	public Cell() {
		this(false, false, false, false);
	}
	
	/**
	 * Initializes a new cell {@code Cell} object with the specified walls closed.
	 * @param topOpen specifies whether the top wall is closed.
	 * @param bottomOpen specifies whether the bottom wall is closed.
	 * @param leftOpen specifies whether the left wall is closed.
	 * @param rightOpen specifies whether the right wall is closed.
	 */
	public Cell(boolean topOpen, boolean bottomOpen, boolean leftOpen, boolean rightOpen) {
		this.topOpen = topOpen;
		this.bottomOpen = bottomOpen;
		this.leftOpen = leftOpen;
		this.rightOpen = rightOpen;
		selectImage();
	}
	
	/**
	 * Based on the setup of the walls, selects which 25-pixel GIF image will be used to display
	 * this cell.
	 */
	private void selectImage() {
		if (topOpen && bottomOpen && leftOpen && rightOpen) {
			image = open;
		} else if (!topOpen && bottomOpen && leftOpen && rightOpen) {
			image = topClosed;
		} else if (topOpen && !bottomOpen && leftOpen && rightOpen) {
			image = bottomClosed;
		} else if (topOpen && bottomOpen && !leftOpen && rightOpen) {
			image = leftClosed;
		} else if (topOpen && bottomOpen && leftOpen && !rightOpen) {
			image = rightClosed;
		} else if (!topOpen && !bottomOpen && leftOpen && rightOpen) {
			image = topBottomClosed;
		} else if (!topOpen && bottomOpen && !leftOpen && rightOpen) {
			image = topLeftClosed;
		} else if (!topOpen && bottomOpen && leftOpen && !rightOpen) {
			image = topRightClosed;
		} else if (topOpen && !bottomOpen && !leftOpen && rightOpen) {
			image = bottomLeftClosed;
		} else if (topOpen && bottomOpen && !leftOpen && !rightOpen) {
			image = bottomRightClosed;
		} else if (topOpen && bottomOpen && !leftOpen && !rightOpen) {
			image = leftRightClosed;
		} else if (!topOpen && !bottomOpen && !leftOpen && rightOpen) {
			image = topBottomLeftClosed;
		} else if (!topOpen && !bottomOpen && leftOpen && !rightOpen) {
			image = topBottomRightClosed;
		} else if (!topOpen && bottomOpen && !leftOpen && !rightOpen) {
			image = topLeftRightClosed;
		} else if (topOpen && !bottomOpen && !leftOpen && !rightOpen) {
			image = bottomLeftRightClosed;
		} else { // !topOpen && !bottomOpen && !leftOpen && !rightOpen
			image = allClosed;			
		}
	}
	
	/**
	 * Removes the specified wall from this cell and calls {@link Cell#selectImage() selectImage()}
	 * to re-adjust the image that is used to display this cell.
	 * @param dir the wall to be removed
	 */
	public void removeWall(int dir) {
		if (dir == Direction.UP) {
			topOpen = true;
		} else if (dir == Direction.DOWN) {
			bottomOpen = true;
		} else if (dir == Direction.LEFT) {
			leftOpen = true;
		} else if (dir == Direction.RIGHT) {
			rightOpen = true;
		}
		selectImage();
	}
	
	/**
	 * @return {@code true} if all walls of this cell are closed, {@code false} otherwise.
	 */
	public boolean isClosed () {
		return !topOpen && !bottomOpen && !leftOpen && !rightOpen;
	}
	
	/**
	 * @return {@code false} if the top wall of this cell is closed, {@code true} otherwise.
	 */
	public boolean isTopOpen() {
		return topOpen;
	}
	
	/**
	 * @return {@code false} if the bottom wall of this cell is closed, {@code true} otherwise.
	 */
	public boolean isBottomOpen() {
		return bottomOpen;
	}
	
	/**
	 * @return {@code false} if the left wall of this cell is closed, {@code true} otherwise.
	 */
	public boolean isLeftOpen() {
		return leftOpen;
	}
	
	/**
	 * @return {@code false} if the right wall of this cell is closed, {@code true} otherwise.
	 */
	public boolean isRightOpen() {
		return rightOpen;
	}
	
	/**
	 * @return the 25-pixel GIF image used to display this cell.
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	public void display(Graphics g) {
		
	}
	
	/**
	 * @param other the {@link Object} to be tested for equality with this cell.
	 * @return {@code true} if {@code other} is a {@code Cell} object with the exact same
	 * 			combination of open and closed walls as this {@code Cell} object, {@code false}
	 * 			otherwise.
	 */
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
				+ ",leftOpen=" + leftOpen + ",rightOpen=" + rightOpen + "]@"
				+ Integer.toHexString(hashCode());
	}
}