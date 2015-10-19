package lib;

import java.awt.geom.Rectangle2D;

public class Rectangle extends Rectangle2D {
	private Point location;
	private Dimension size;

	public Rectangle() {
		this(new Point(), new Dimension());
	}
	
	public Rectangle(int x, int y, int width, int height) {
		this(new Point(x, y), new Dimension(width, height));
	}
	
	public Rectangle(double x, double y, double width, double height) {
		this(new Point(x, y), new Dimension(width, height));
	}
	
	public Rectangle(Point location, Dimension size) {
		this.location = location;
		this.size = size;
	}
	
	@Override
	public Rectangle2D createIntersection(Rectangle2D other) {
		double x = location.getX();
		double y = location.getY();
		double width = size.getWidth();
		double height = size.getHeight();
		double ox = other.getX();
		double oy = other.getY();
		double ow = other.getWidth();
		double oh = other.getHeight();
		double newX;
		double newY;
		double newWidth;
		double newHeight;
		
		// check to see if the two rectangles actually intersect
		if (Math.min(x, ox) + ((x < ox) ? width : ow) < ((x < ox) ? ox : x)
				|| Math.max(y, oy) + ((y < oy) ? height : oh) < ((y < oy) ? oy : y)) {
			return null;
		}
		
		if (x < ox && y < oy) {
			newX = x;
			newY = y;
		} else if (x >= ox && y >= oy) {
			newX = ox;
			newY = oy;
		} else if (x > ox && y < oy) {
			newX = x;
			newY = oy;
		} else { // x < ox && y > oy
			newX = ox;
			newY = y;
		}
		
		return new Rectangle(newX, newY, 0., 0.);
	}
	
	@Override
	public Rectangle2D createUnion(Rectangle2D r) {		
		double x = location.getX();
		double y = location.getY();
		double width = size.getWidth();
		double height = size.getHeight();
		double ox = r.getX();
		double oy = r.getY();
		double ow = r.getWidth();
		double oh = r.getHeight();
		double newX;
		double newY;
		double newWidth;
		double newHeight;
		newX = Math.min(x, ox);
		newY = Math.max(y, oy);
		
		if (x <= ox) {
			newWidth = width - (ox - x) + ow;
		} else {
			newWidth = ow - (x - ox) + width;
		}
		
		if (y <= oy) {
			newHeight = height - (y - oy) + oh;
		} else {
			newHeight = oh - (oy - y) + height;
		}
		
		return new Rectangle(newX, newY, newWidth, newHeight);
	}
	
	@Override
	public int outcode(double arg0, double arg1) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setRect(double x, double y, double width, double height) {
		this.location = new Point(x, y);
		this.size = new Dimension(width, height);
	}
	
	@Override
	public double getHeight() {
		return size.getHeight();
	}
	
	@Override
	public double getWidth() {
		return size.getWidth();
	}
	
	@Override
	public double getX() {
		return location.getX();
	}
	
	@Override
	public double getY() {
		return location.getY();
	}
	
	@Override
	public boolean isEmpty() {
		return size.equals(new Dimension());
	}
	
	@Override
	public Rectangle clone() {
		return new Rectangle(location.clone(), size.clone());
	}
}