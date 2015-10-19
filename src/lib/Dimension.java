package lib;

import java.awt.geom.Dimension2D;

public class Dimension extends Dimension2D {
	private double width;
	private double height;
	
	public Dimension() {
		this(0, 0);
	}
	
	public Dimension(int width, int height) {
		this((double) width, (double) height);
	}
	
	public Dimension(double width, double height) {
		setSize(width, height);
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public Dimension clone() {
		return new Dimension(width, height);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Dimension) {
			Dimension d = (Dimension) other;
			return d.getWidth() == width && d.getHeight() == height;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[width=" + width + ",height=" + height + "]";
	}
}