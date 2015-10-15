package view;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Sketchpad {
	private Graphics g;
	private JFrame f;
	private JPanel p;
	private long lastClickTime;
	
	protected Sketchpad() {
		addEventListeners();
	}
	
	private void addEventListeners() {
		f = new JFrame();
		p = new JPanel(true);
		
		f.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				Sketchpad.this.focusLost(e);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				Sketchpad.this.focusGained(e);
			}
		});
		
		p.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				Sketchpad.this.keyPressed(e);
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				Sketchpad.this.keyTyped(e);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				Sketchpad.this.keyReleased(e);
			}
		});
		
		p.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				long startTime = System.currentTimeMillis();
				if (startTime - lastClickTime <= 500 && lastClickTime > 0L) {
					Sketchpad.this.mouseDoubleclicked(e);
					lastClickTime = 0L;
				} else {
					try {
						Thread.sleep(500);
					} catch (InterruptedException ex) {
						Sketchpad.this.mouseClicked(e);
					}
					
					if (lastClickTime == startTime) {
						Sketchpad.this.mouseClicked(e);
					}
					
					lastClickTime = startTime;
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Sketchpad.this.mouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Sketchpad.this.mouseExited(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sketchpad.this.mousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Sketchpad.this.mouseReleased(e);
			}
		});
		
		p.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Sketchpad.this.mouseDragged(e);
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				Sketchpad.this.mouseMoved(e);
			}
		});
		
		p.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				Sketchpad.this.mouseWheelMoved(e);
			}
		});
	}
	
	public abstract void setup(Graphics g);
	
	public abstract void draw(Graphics g);
	
	public void focusLost(FocusEvent e) {}
	
	public void focusGained(FocusEvent e) {}
	
	public void keyPressed(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
	
	public void keyReleased(KeyEvent e) {}
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mouseDoubleclicked(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseDragged(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {}
	
	public void mouseWheelMoved(MouseWheelEvent e) {}
	
	public final int getHeight() {
		return p.getHeight();
	}
	
	public Dimension getMaximumSize() {
		return f.getMaximumSize();
	}
	
	public Dimension getMinimumSize() {
		return f.getMinimumSize();
	}
}