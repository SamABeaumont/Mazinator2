package view;

import java.awt.Color;
import java.awt.Cursor;
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import model.EventReciever;

public abstract class Sketchpad extends EventReciever implements Runnable {
	private Color background = Color.BLACK;
	private Graphics g;
	private JFrame f;
	private JPanel p;
	private boolean canDraw = false;
	private long lastClickTime;
	
	protected Sketchpad() {
		f = new JFrame();
		p = new JPanel(true) {
			@Override
			public void paint(Graphics g) {
				if (canDraw) {
					Color c = g.getColor();
					g.setColor(background);
					g.fillRect(0, 0, p.getWidth(), p.getHeight());
					g.setColor(c);
					draw(g);
					repaint();
				}
			}
			
			private static final long serialVersionUID = 7689980947403877048L;
		};
		f.add(p);
		addEventListeners();
		f.setVisible(true);
		f.pack();
		setup(g);
	}
	
	private void addEventListeners() {	
		f.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				Sketchpad.this.windowLostFocus(e);
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				Sketchpad.this.windowGainedFocus(e);
			}
		});
		
		f.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent e) {
				Sketchpad.this.windowActivated(e);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				Sketchpad.this.windowClosed(e);
			}

			@Override
			public void windowClosing(WindowEvent e) {
				Sketchpad.this.windowClosing(e);
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				Sketchpad.this.windowDeactivated(e);
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				Sketchpad.this.windowDeiconified(e);
			}

			@Override
			public void windowIconified(WindowEvent e) {
				Sketchpad.this.windowIconified(e);
			}

			@Override
			public void windowOpened(WindowEvent e) {
				Sketchpad.this.windowOpened(e);
			}
			
		});
		
		f.addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				Sketchpad.this.windowStateChanged(e);
			}
		});
		
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
	
	protected final void start() {
		canDraw = true;
	}
	
	public final Color getBackground() {
		return background;
	}
	
	public final void setBackground(Color background) {
		this.background = background;
	}
	
	public final Dimension getMaximumSize() {
		return f.getMaximumSize();
	}
	
	public final Dimension getMinimumSize() {
		return f.getMinimumSize();
	}
	
	public final Dimension getPreferredSize() {
		return f.getPreferredSize();
	}
	
	public final Dimension getSize() {
		return f.getSize();
	}
	
	public final int getWidth() {
		return f.getWidth();
	}
	
	public final void setSize(int width, int height) {
		f.setSize(width, height);
	}
	
	public final void setSize(Dimension d) {
		f.setSize(d);
	}
	
	public final void setMaximumSize(Dimension maximumSize) {
		f.setMaximumSize(maximumSize);
	}
	
	public final void setMinimumSize(Dimension minimumSize) {
		f.setMinimumSize(minimumSize);
	}
	
	public final Rectangle getBounds() {
		return f.getBounds();
	}
	
	public final int getHeight() {
		return f.getHeight();
	}
	
	public final void setIconImage(Image image) {
		f.setIconImage(image);
	}
	
	public final JMenuBar getJMenuBar() {
		return f.getJMenuBar();
	}
	
	public final void setJMenuBar(JMenuBar menuBar) {
		f.setJMenuBar(menuBar);
	}
	
	public final Rectangle getMaximizedBounds() {
		return f.getMaximizedBounds();
	}
	
	public final String getTitle() {
		return f.getTitle();
	}
	
	public final boolean isResizable() {
		return f.isResizable();
	}
	
	public final void setMaximizedBounds(int x, int y, int width, int height) {
		f.setMaximizedBounds(new Rectangle(x, y, width, height));
	}

	public final void setMaximizedBounds(Rectangle bounds) {
		f.setMaximizedBounds(bounds);
	}
	
	public final void setResizable(boolean resizable) {
		f.setResizable(resizable);
	}
	
	public final void setTitle(String title) {
		f.setTitle(title);
	}

	public final void setDefaultCloseOperation(int operation) {
		f.setDefaultCloseOperation(operation);
	}
	
	public final Cursor getCursor() {
		return f.getCursor();
	}
	
	public final void setCursor(Cursor cursor) {
		f.setCursor(cursor);
	}
	
	public final Point getLocationOnScreen() {
		return f.getLocationOnScreen();
	}
	
	public final boolean isCursorSet() {
		return f.isCursorSet();
	}
	
	public final boolean isFocusOwner() {
		return f.isFocusOwner();
	}
	
	public final boolean isWindowMaximumSizeSet() {
		return f.isMaximumSizeSet();
	}
	
	public final boolean isWindowMinimumSizeSet() {
		return f.isMinimumSizeSet();
	}
	
	public final boolean isWindowPreferredSizeSet() {
		return f.isPreferredSizeSet();
	}
	
	@Override
	public final void run() {
		while(true);
	}
}