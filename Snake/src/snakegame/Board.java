package snakegame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener{
	

	//overriding related for ActionListener
	private static final long serialVersionUID = 1L;
	
	private int dots = 3;
	// images
	private Image dot;
	private Image apple;
	private Image head;
	// screen config
	private final int ALL_DOTS = 202500; // 450 x 450
	private final int DOT_SIZE = 10;
	// controls and game info
	private final int RANDOM_POS = 29;
	private boolean leftDirection = false;
	private boolean rightDirection = true;
	private boolean upDirection = false;
	private boolean downDirection = false;
	    
	private boolean inGame = true;
	
	private final int x[] = new int[ALL_DOTS];
	private final int y[] = new int[ALL_DOTS];
	private int apple_x = 0;
	private int apple_y = 0;
	private Timer timer;
 
	

	Board(){
		
		addKeyListener(new TAdapter());
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(450,450));
		setFocusable(true);
		
		loadImage();
		initGame();
	}
	
	public void loadImage() {
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png"));
		ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png"));
		ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
		apple = i1.getImage();
		dot = i2.getImage();
		head = i3.getImage();
		
	}
	
	public void initGame() {
		locateApple();
		// delay and listener - requires Action Listener
		timer = new Timer(140,this);
		timer.start();
		dots = 3;
		for(int i =0; i < dots;i++) {
			y[i] = 50;
			x[i] = 100 - i*DOT_SIZE;	
		}
		
		
	}
	
	
	public void locateApple() {
		
		int r = (int) (Math.random() * RANDOM_POS);
		apple_x = r*DOT_SIZE;
		r = (int) (Math.random() * RANDOM_POS);
		apple_y = r*DOT_SIZE ;
	}
	
	// to show the images on frame
	// method of graphics class, we need to pass the object of it's class
	// we need to call parent component using super.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		draw(g);
	}
	
	
	public void draw(Graphics g) {
		// draw food
		
		if(inGame) {
		g.drawImage(apple,  apple_x,apple_y, this);
		
		for(int i =0;i<dots;i++) {
			if(i==0) {
				// image, coordinates, observer as this
				g.drawImage(head, x[i],y[i], this);
			}
			else {
				g.drawImage(dot, x[i],y[i], this);
			}
		}
		
		Toolkit.getDefaultToolkit().sync();
		} else {
			gameOver(g);
		}
	}
	
	
	public void gameOver(Graphics g) {
		String message = "Game Over!";
		Font font = new Font("SAN_SERIF", Font.BOLD, 14);
		FontMetrics metrices = getFontMetrics(font);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(message, ((450-metrices.stringWidth(message))/2), 450/2);
	}
	
	
	public void move() {
		
		 for (int i = dots ; i > 0 ; i--) {
	            x[i] = x[i - 1];
	            y[i] = y[i - 1];
	        }
		 
	        
	        if (leftDirection) {
	            x[0] = x[0] - DOT_SIZE;
	        }
	        if (rightDirection) {
	            x[0] = x[0] + DOT_SIZE;
	        }
	        if (upDirection) {
	            y[0] = y[0] - DOT_SIZE;
	        }
	        if (downDirection) {
	            y[0] = y[0] + DOT_SIZE;
	        }
		
		
	}
	
	
	 public void checkCollision() {
	        for(int i = dots; i > 0; i--) {
	            if (( i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
	                inGame = false;
	            }
	        }
	        
	        if (y[0] >= (int) Math.sqrt(ALL_DOTS)) {
	            inGame = false;
	        }
	        if (x[0] >= (int) Math.sqrt(ALL_DOTS)) {
	            inGame = false;
	        }
	        if (y[0] < 0) {
	            inGame = false;
	        }
	        if (x[0] < 0) {
	            inGame = false;
	        }
	        
	        if (!inGame) {
	            timer.stop();
	        }
	    }
	 
	   public void checkApple() {
	        if ((x[0] == apple_x) && (y[0] == apple_y)) {
	            dots++;
	            locateApple();
	        }
	    }
	   
	   // overridden function of ActionListener interface.
	    public void actionPerformed(ActionEvent ae) {
	        if (inGame) {
	            checkApple();
	            checkCollision();
	            move();
	        }
	        
	        repaint();
	    }
	    
	    
// TAdapter is a class extending KeyAdapter to capture key strokes.
	    public class TAdapter extends KeyAdapter {
	    	// KeyListener Interface requires this
	        @Override
	        public void keyPressed(KeyEvent e) {
	            int key = e.getKeyCode();
	            // make left true only when right is false.
	            if (key == KeyEvent.VK_LEFT && (!rightDirection)) {
	                leftDirection = true;
	                upDirection = false;
	                downDirection = false;
	            }
	            
	            if (key == KeyEvent.VK_RIGHT && (!leftDirection)) {
	                rightDirection = true;
	                upDirection = false;
	                downDirection = false;
	            }
	            
	            if (key == KeyEvent.VK_UP && (!downDirection)) {
	                upDirection = true;
	                leftDirection = false;
	                rightDirection = false;
	            }
	            
	            if (key == KeyEvent.VK_DOWN && (!upDirection)) {
	                downDirection = true;
	                leftDirection = false;
	                rightDirection = false;
	            }
	        }
	    }
	
}
