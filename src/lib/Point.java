package lib;

import java.awt.geom.Point2D;

public class Point extends Point2D {
	private double x;
	private double y;
	
	public Point() {
		this(0, 0);
	}
	
	public Point(int x, int y) {
		this((double) x, (double) y);
	}
	
	public Point(double x, double y) {
		setLocation(x, y);
	}
	
	@Override
	public double getX() {
		return x;
	}
	
	@Override
	public double getY() {
		return y;
	}
	
	public double getDistanceFromOrigin() {
		return Math.sqrt(x * x + y * y);
	}
	
	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public Point clone() {
		return new Point(x, y);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Point) {
			Point p = (Point) other;
			return p.getX() == x && p.getY() == y;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[x=" + x + ",y=" + y + "]";
	}
}