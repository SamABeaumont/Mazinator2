package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import lib.Dimension;

public final class Background implements ImageObserver {
	private Dimension size;
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
		createImage(color, null);
		this.size = size;
	}
	
	public Background(Image image) {
		this(new Color(0, 0, 0, 0), image);
	}
	
	public Background(Color color, Image image) {
		createImage(color, image);
		size = new Dimension(image.getWidth(this), image.getHeight(this));
	}
	
	private void createImage(Color color, Image image) {
		BufferedImage img;
		if (image == null) {
			img = new BufferedImage((int) size.getWidth(), (int) size.getHeight(),
					BufferedImage.TYPE_INT_ARGB);
		} else {
			img = new BufferedImage(image.getWidth(this), image.getHeight(this),
					BufferedImage.TYPE_INT_ARGB);
		}
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		if (image != null) {
			g.drawImage(image, 0, 0, this);
		}
		this.image = img;
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
		return image;
	}
	
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[size=" + size + ",image=" + image + "]@" + hashCode();
	}
}