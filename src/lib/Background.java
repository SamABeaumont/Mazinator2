package lib;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public final class Background implements ImageObserver {
	private Dimension size;
	private Color color;
	private Image image;
	
	public Background() {
		this(new Color(255, 255, 255, 1), new Dimension(25, 25));
	}
	
	public Background(Color color) {
		this(color, new Dimension(25, 25));
	}
	
	public Background(Color color, int width, int height) {
		this(color, new Dimension(width, height));
	}
	
	public Background(Color color, Dimension size) {
		this.color = color;
		this.size = size;
	}
	
	public Background(Image image) {
		this.color = new Color(0, 0, 0, 1);
		this.image = image;
	}
	
	public Background(Color color, Image image) {
		this.color = color;
		this.image = image;
	}
	
	public int getWidth() {
		if (image == null) {
			return (int) size.getWidth();
		} else {
			return image.getWidth(this);
		}
	}
	
	public int getHeight() {
		if (image == null) {
			return (int) size.getHeight();
		} else {
			return image.getHeight(this);
		}
	}
	
	public Image getImage() {
		if (image != null) {
			return image;
		} else {
			BufferedImage img = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) img.getGraphics();
			g.setColor(color);
			g.fillRect(0, 0, 25, 25);
			return img;
		}
	}
	
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[color=" + color + ",image=" + image + "]";
	}
}