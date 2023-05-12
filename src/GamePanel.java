import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
	Image image = Toolkit.getDefaultToolkit().getImage("images//2.png");
	Image newImage = image.getScaledInstance(1850, 1000, Image.SCALE_SMOOTH);
	static final int SCREEN_WIDTH = 1850;
	static final int SCREEN_HEIGHT = 1000;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 30;
	char direction = 'R';
	MainCircle myCircle = new MainCircle(SCREEN_WIDTH/16, SCREEN_HEIGHT/3, 150);
	SquareFaction mySquare = new SquareFaction(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 100, 100);
	TriangleFaction myTriangle = new TriangleFaction(new int[]{100, 200, 150}, new int[]{100, 100, 200}, 3);
	Point p;
	int x;
	int y;
	Timer timer;
	CircleFaction[] circleArray;
	List<CircleFaction> circles = new ArrayList<CircleFaction>();
	RectangularButton[] buttons = {
			new RectangularButton(30, 878, 110, 80),
			new RectangularButton(170, 878, 110, 80),
			new RectangularButton(310, 878, 110, 80)	
	};
	Random rand = new Random();
	

	GamePanel(){
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.white);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void draw(Graphics g) {
		p = MouseInfo.getPointerInfo().getLocation();
		myCircle.draw(g);
		mySquare.draw(g);
		myTriangle.draw(g);
		 for (int i = 0; i < buttons.length; i++) {
	   		 RectangularButton button = buttons[i];
	   		 button.draw(g);
	   	}	
		for (int i = 0; i < circles.size(); i++) {
		    CircleFaction circle = circles.get(i);
		    // Do something with car object
		    circle.draw(g);
		}

//		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(newImage, 0, 0, null);
        draw(g);
    }
	
	public class MyKeyAdapter extends KeyAdapter{
		// TODO Auto-generated method stub
		
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction!='R') {
					direction = 'L';
					System.out.println("red");
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction!='L') {
					direction = 'R';
					System.out.println("red");
				}
				break;
			case KeyEvent.VK_UP:
				if(direction!='D') {
					direction = 'U';
					System.out.println("red");
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction!='U') {
					direction = 'D';
					System.out.println("red");
				}
				break;
			}
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//			System.out.println("You must move!");
		myCircle.move();
		mySquare.move();
		for (int i = 0; i < circles.size(); i++) {
		    CircleFaction circle = circles.get(i);
		    // Do something with car object
		    circle.move();
		}
		repaint();
		
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (myCircle.isMouseInsideCircle(mouseX, mouseY) && e.getButton() == MouseEvent.BUTTON1) {
        	System.out.println("Mouse clicked inside Circle");
            myCircle.setIsClicked(true);
        }
        
        else if (e.getButton() == MouseEvent.BUTTON1 && !(myCircle.isMouseInsideCircle(mouseX, mouseY))) {
        	myCircle.setIsClicked(false);
        	System.out.println("circle clicked set to false");
        }
        
        
        if (mySquare.isMouseInsideSquare(mouseX, mouseY)&& e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Mouse clicked inside Square");
            mySquare.setIsClicked(true);
        }
        
        else if (e.getButton() == MouseEvent.BUTTON1 && !(mySquare.isMouseInsideSquare(mouseX, mouseY))) {
        	mySquare.setIsClicked(false);
        	System.out.println("square clicked set to false");
        }

        
        if (myTriangle.isMouseInsideTriangle(mouseX, mouseY)) {
            System.out.println("Mouse clicked inside Triangle");
        }
        
//        else if (e.getButton() == MouseEvent.BUTTON1) {
//        	mySquare.setIsClicked(false);
//        	myCircle.setIsClicked(false);
//        }
        for (int i = 0; i < circles.size(); i++) {
		    CircleFaction circle = circles.get(i);
		    // Do something with car object
		    if (circle.isMouseInsideCircle(mouseX, mouseY) && e.getButton() == MouseEvent.BUTTON1) {
	        	System.out.println("Mouse clicked inside Circle");
	            circle.setIsClicked(true);
	        }
		    else if (e.getButton() == MouseEvent.BUTTON1 && !(circle.isMouseInsideCircle(mouseX, mouseY))) {
		    	circle.setIsClicked(false);
	        	System.out.println("circle clicked set to false");
	        }
		}
        
        for (int i = 0; i < buttons.length; i++) {
	   		 RectangularButton button = buttons[i];
	   		 if (button.isMouseInsideSquare(mouseX, mouseY)) {
//	   			button.setColor(Color.cyan);
	   			while(true) {
	   				int randx = 130 + rand.nextInt(400 - 130 + 1);
	   				int randy = 200 + rand.nextInt(500 - 200 + 1);
	   				if(!myCircle.isMouseInsideCircle(randx, randy)) {
	   					circles.add(new CircleFaction(randx, randy, 100));
	   					break;
	   				}
				}
	   			
	   	     }
	   	}	
		    
    }
	
	 @Override
    public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
        int mouseY = e.getY();
        
		if (e.getButton() == MouseEvent.BUTTON3) {
        	if (myCircle.getIsClicked()) {
        		myCircle.getVector(mouseX - myCircle.getRadius()/2, mouseY - myCircle.getRadius()/2);
            	myCircle.setNewPosition(mouseX - myCircle.getRadius()/2, mouseY - myCircle.getRadius()/2);
            }
        	if (mySquare.getIsClicked()){
        		mySquare.getVector(mouseX, mouseY);
        		mySquare.setNewPosition(mouseX, mouseY);
        	}
	    	 for (int i = 0; i < circles.size(); i++) {
	    		 CircleFaction circle = circles.get(i);
	    		 if (circle.getIsClicked()) {
	    			 circle.getVector(mouseX - myCircle.getRadius()/2, mouseY - myCircle.getRadius()/2);
	    			 circle.setNewPosition(mouseX - myCircle.getRadius()/2, mouseY - myCircle.getRadius()/2);
	             }
	    	 }
        }
//		else if (e.getButton() == MouseEvent.BUTTON2) {
//			while(True) {
//				
//			}
//			circles.add(new CircleFaction(e.getX(),  e.getY(), 100));
//        }
        
	 }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }
    

    @Override
    public void mouseExited(MouseEvent e) {

    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX();
        int mouseY = e.getY();
        for (int i = 0; i < buttons.length; i++) {
	   		 RectangularButton button = buttons[i];
	   		 if (button.isMouseInsideSquare(mouseX, mouseY)) {
	   			button.setColor( new Color(128, 128, 128, 128));
	         }
	   		 else {
	   			button.setColor(new Color(255, 255, 255, 0));
	   		 }
	   	}	
	}
	

	
}
