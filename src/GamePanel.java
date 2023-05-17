<<<<<<< HEAD
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
	MainCircle myCircle = new MainCircle(SCREEN_WIDTH/16, SCREEN_HEIGHT/3);
	MainSquare mySquare = new MainSquare(850, 570);
//	MainSquare mySquare = new MainSquare(800, 570, 130);
	MainDiamond myDiamond = new MainDiamond(1500, SCREEN_HEIGHT/3);
//	TriangleFaction myTriangle = new TriangleFaction(new int[]{100, 200, 150}, new int[]{100, 100, 200}, 3);
	MainTriangle myTriangle = new MainTriangle(850, 120);
	Point p;
	int x;
	int y;
	Timer timer;
	CircleFaction[] circleArray;
	List<CircleFaction> circles = new ArrayList<CircleFaction>();
	List<SquareFaction> squares = new ArrayList<SquareFaction>();
//	CircleFaction test = new CircleFaction(0, 0, "small");
	RectangularButton[] buttons = {
			new RectangularButton(30, 878, 110, 80),
			new RectangularButton(170, 878, 110, 80),
			new RectangularButton(310, 878, 110, 80)	
	};
	Random rand = new Random();
	RectangularButton button;
	

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
//		p = MouseInfo.getPointerInfo().getLocation();

		 for (int i = 0; i < buttons.length; i++) {
	   		 RectangularButton button = buttons[i];
	   		 button.draw(g);
	   	}	
		for (int i = 0; i < circles.size(); i++) {
		    CircleFaction circle = circles.get(i);
		    // Do something with car object
		    circle.draw(g);
		}
		for (int i = 0; i < squares.size(); i++) {
		    SquareFaction square = squares.get(i);
		    // Do something with car object
		    square.draw(g);
		}
		myCircle.draw(g);
		mySquare.draw(g);
		myDiamond.draw(g);
		myTriangle.draw(g);

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
//		test.move();
		for (int i = 0; i < circles.size(); i++) {
		    CircleFaction circle = circles.get(i);
		    // Do something with car object
		    circle.move();
		}
		
		for (int i = 0; i < squares.size(); i++) {
		    SquareFaction square = squares.get(i);
		    // Do something with car object
		    square.move();
		}
//		
//        for (int i = 0; i < squares.size(); i++) {
//		    SquareFaction square = squares.get(i);
//		    if (mySquare.intersects(square)) {
//		    	System.out.println("Intersecting");
//		    	square.setDistance(0);
//		    }
//		    else {
//		    	System.out.println("Not intersecting");
//		    	
//		    }
//        }
        for (int i = 0; i < squares.size(); i++) {
        	SquareFaction shapeOne = squares.get(i);
        	for (int j = 0; j < squares.size(); j++) {
        		if (i == j) {
        			continue;
        		}
        		SquareFaction shapeTwo = squares.get(j);
        		if (shapeOne.intersects(shapeTwo)) {
//    		    	System.out.println("Intersecting");
        			if(shapeTwo.getDirection() == "First Quadrant" || shapeTwo.getDirection() == "Fourth Quadrant") {
        				System.out.println("Collision will happen go back!");
        				shapeTwo.getVector(shapeTwo.getX() - 10, shapeTwo.getY());
        			}
        			else if(shapeTwo.getDirection() == "Second Quadrant" || shapeTwo.getDirection() == "Third Quadrant") {
        				System.out.println("Collision will happen go back!");
        				shapeTwo.getVector(shapeTwo.getX() + 10, shapeTwo.getY());
        			}
        			
//    		    	shapeOne.setDistance(0);
    		    }
    		    else {
//    		    	System.out.println("Not intersecting");
    		    	
    		    }
        	}
        }
        
        for (int i = 0; i < squares.size(); i++) {
        	Shapes shapeOne = squares.get(i);
        	if(myCircle.intersects(shapeOne)) {
        		squares.remove(shapeOne);
        		myCircle.health -= 10;
        	}
        }
		
        for (int i = 0; i < circles.size(); i++) {
        	Shapes shapeOne = circles.get(i);
        	for (int j = 0; j < squares.size(); j++) {
//        		if (i == j) {
//        			continue;
//        		}
        		Shapes shapeTwo = squares.get(j);
        		if (shapeOne.intersects(shapeTwo)) {
    		    	if(shapeOne.getHeight() > shapeTwo.getHeight()) {
    		    		squares.remove(shapeTwo);
    		    	}
    		    	else if (shapeOne.getHeight() < shapeTwo.getHeight()){
    		    		circles.remove(shapeOne);
    		    	}
    		    	else {
    		    		shapeOne.setDistance(0);
    		    	}
    		    	
    		    }
    		    else {
//    		    	System.out.println("Not intersecting");
    		    	
    		    }
        	}
        }
        
        for (int i = 0; i < squares.size(); i++) {
        	Shapes shapeOne = squares.get(i);
        	for (int j = 0; j < circles.size(); j++) {
//        		if (i == j) {
//        			continue;
//        		}
        		Shapes shapeTwo = circles.get(j);
        		if (shapeOne.intersects(shapeTwo)) {
//    		    	System.out.println("Intersecting");
    		    	shapeOne.setDistance(0);
    		    }
    		    else {
//    		    	System.out.println("Not intersecting");
    		    	
    		    }
        	}
        }
        
        
        
        for (int i = 0; i < circles.size(); i++) {
        	Random random = new Random();
        	Shapes shapeOne = circles.get(i);
        	for (int j = 0; j < circles.size(); j++) {
        		if (i == j) {
        			continue;
        		}
        		Shapes shapeTwo = circles.get(j);
        		if (shapeOne.intersects(shapeTwo)) {
//    		    	System.out.println("Intersecting");
        			System.out.println("Collision will happen go back!");
        			if(shapeTwo.getDirection() == "First Quadrant") {
        				
        				if(shapeTwo.getSize() == "small") {
        					shapeTwo.getVector(shapeTwo.getX() - random.nextInt(6), shapeTwo.getY() + random.nextInt(6));
        				}
        				else if(shapeTwo.getSize() == "medium") {
        					shapeTwo.getVector(shapeTwo.getX() - random.nextInt(3), shapeTwo.getY() + random.nextInt(3));
        				}
        				else if(shapeTwo.getSize() == "large"){
        					shapeTwo.getVector(shapeTwo.getX() - random.nextInt(2), shapeTwo.getY() + random.nextInt(2));
        				}
        				
        			}

        			else if(shapeTwo.getDirection() == "Second Quadrant") {
        				System.out.println("Collision will happen go back!");
        				if(shapeTwo.getSize() == "small") {
        					shapeTwo.getVector(shapeTwo.getX() + random.nextInt(6), shapeTwo.getY() + random.nextInt(6));
        				}
        				else if(shapeTwo.getSize() == "medium") {
        					shapeTwo.getVector(shapeTwo.getX() + random.nextInt(3), shapeTwo.getY() + random.nextInt(3));
        				}
        				else if(shapeTwo.getSize() == "large"){
        					shapeTwo.getVector(shapeTwo.getX() + random.nextInt(2), shapeTwo.getY() + random.nextInt(2));
        				}
        			}
        			else if(shapeTwo.getDirection() == "Third Quadrant") {
        				System.out.println("Collision will happen go back!");
        				if(shapeTwo.getSize() == "small") {
        					shapeTwo.getVector(shapeTwo.getX() + random.nextInt(6), shapeTwo.getY() - random.nextInt(6));
        				}
        				else if(shapeTwo.getSize() == "medium") {
        					shapeTwo.getVector(shapeTwo.getX() + random.nextInt(3), shapeTwo.getY() - random.nextInt(3));
        				}
        				else if(shapeTwo.getSize() == "large"){
        					shapeTwo.getVector(shapeTwo.getX() + random.nextInt(2), shapeTwo.getY() - random.nextInt(2));
        				}
        			}
        			else if(shapeTwo.getDirection() == "Fourth Quadrant") {
        				if(shapeTwo.getSize() == "small") {
        					shapeTwo.getVector(shapeTwo.getX() - random.nextInt(6), shapeTwo.getY() - random.nextInt(6));
        				}
        				else if(shapeTwo.getSize() == "medium") {
        					shapeTwo.getVector(shapeTwo.getX() - random.nextInt(3), shapeTwo.getY() - random.nextInt(3));
        				}
        				else if(shapeTwo.getSize() == "large"){
        					shapeTwo.getVector(shapeTwo.getX() - random.nextInt(2), shapeTwo.getY() - random.nextInt(2));
        				}
        			}
//    		    	shapeOne.setDistance(0);
    		    }
    		    else {
//    		    	System.out.println("Not intersecting");
    		    	
    		    }
        	}
        }
    


		repaint();
		
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (myCircle.isMouseInsideMainCircle(mouseX, mouseY) && e.getButton() == MouseEvent.BUTTON1) {
        	System.out.println("Mouse clicked inside Circle");
            myCircle.setIsClicked(true);
        }
        
//        if (test.isMouseInsideCircle(mouseX, mouseY)){
//        	test.setIsClicked(true);
//        }
        
        else if (e.getButton() == MouseEvent.BUTTON1 && !(myCircle.isMouseInsideMainCircle(mouseX, mouseY))) {
        	myCircle.setIsClicked(false);
        	System.out.println("circle clicked set to false");
        }
        
        
        if (mySquare.isMouseInsideMainSquare(mouseX, mouseY)&& e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Mouse clicked inside Square");
            mySquare.setIsClicked(true);
        }
        
        else if (e.getButton() == MouseEvent.BUTTON1 && !(mySquare.isMouseInsideMainSquare(mouseX, mouseY))) {
        	mySquare.setIsClicked(false);
        	System.out.println("square clicked set to false");
        }

        
        if (myTriangle.isMouseInsideMainTriangle(mouseX, mouseY)) {
            System.out.println("Mouse clicked inside Triangle");
        }
        

        
        button = buttons[0];
		if (button.isMouseInsideSquare(mouseX, mouseY)) {
	//  			button.setColor(Color.cyan);
			int randy = rand.nextInt(900);
			CircleFaction circle = new CircleFaction(150, 360, "small");
			circles.add(circle);
			circle.getVector(500, randy);
			
	     }
		
		button = buttons[1];
		if (button.isMouseInsideSquare(mouseX, mouseY)) {
			//  			button.setColor(Color.cyan);
			int randy = rand.nextInt(900);
			CircleFaction circle = new CircleFaction(150, 360, "medium");
			circles.add(circle);
			circle.getVector(500, randy);
			
	    }
		
		button = buttons[2];
		if (button.isMouseInsideSquare(mouseX, mouseY)) {
			//  			button.setColor(Color.cyan);
			int randy = rand.nextInt(900);
			CircleFaction circle = new CircleFaction(150, 360, "large");
			circles.add(circle);
			circle.getVector(500, randy);
						
	     }
		
//		button = buttons[2];
//		if (button.isMouseInsideSquare(mouseX, mouseY)) {
//			//  			button.setColor(Color.cyan);
//			while(true) {
//				int randx = 130 + rand.nextInt(400 - 130 + 1);
//				int randy = 200 + rand.nextInt(500 - 200 + 1);
//				if(!myCircle.isMouseInsideMainCircle(randx, randy)) {
//					circles.add(new CircleFaction(randx, randy, "large"));
//					break;
//				}
//			}
//			
//	     }
		    
    }
	
	 @Override
    public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
        int mouseY = e.getY();
        
		if (e.getButton() == MouseEvent.BUTTON3) {
        	if (myCircle.getIsClicked()) {
        		myCircle.getVector(mouseX, mouseY);
            	myCircle.setNewPosition(mouseX, mouseY);
            	
            }
        	if (mySquare.getIsClicked()){
        		mySquare.getVector(mouseX, mouseY);
        		mySquare.setNewPosition(mouseX, mouseY);
        	}
	    	 for (int i = 0; i < circles.size(); i++) {
	    		 CircleFaction circle = circles.get(i);
	    		 if (circle.getIsClicked()) {
	    			 circle.getVector(mouseX, mouseY);
	    			 circle.setNewPosition(mouseX, mouseY);
	    			 circle.getQuadrant(mouseX, mouseY);
	             }
	    	 }
	    	 
	    	 for (int i = 0; i < squares.size(); i++) {
	    		 SquareFaction square = squares.get(i);
	    		 if (square.getIsClicked()) {
	    			 square.getVector(mouseX, mouseY);
	    			 square.setNewPosition(mouseX, mouseY);
	    			 square.getQuadrant(mouseX, mouseY);
	             }
	    	 }
	    	 
//	    	 if (test.getIsClicked()) {
//	    		 System.out.println(mouseX + ", " + mouseY);
//	    		 
//	    		 test.getVector(mouseX, mouseY);
//	    		 test.setNewPosition(mouseX, mouseY);
//	    	 }
        }
		
		else if (e.getButton() == MouseEvent.BUTTON2) {
			squares.add(new SquareFaction(e.getX(),  e.getY(), "small"));
        }
		
        for (int i = 0; i < circles.size(); i++) {
		    CircleFaction circle = circles.get(i);
		    // Do something with car object
		    if (circle.isMouseInsideShape(mouseX, mouseY) && e.getButton() == MouseEvent.BUTTON1) {
//	        	System.out.println("Mouse clicked inside Circle");
	            circle.setIsClicked(true);
	            break;
	        }
//		    else if (e.getButton() == MouseEvent.BUTTON1 && !circle.isMouseInsideShape(mouseX, mouseY)) {
//		    	circle.setIsClicked(false);
////	        	System.out.println("circle clicked set to false");
//	        }
		    else {
		    	circle.setIsClicked(false);
//	        	System.out.println("circle clicked set to false");
	        }
		}
        
        for (int i = 0; i < squares.size(); i++) {
		    SquareFaction square = squares.get(i);
		    // Do something with car object
		    if (square.isMouseInsideShape(mouseX, mouseY) && e.getButton() == MouseEvent.BUTTON1) {
//	        	System.out.println("Mouse clicked inside Circle");
	        	square.setIsClicked(true);
	        	break;
	        }
		    
		    else if (e.getButton() == MouseEvent.BUTTON1 && !square.isMouseInsideShape(mouseX, mouseY)) {
		    	square.setIsClicked(false);
//	        	System.out.println("circle clicked set to false");
	        }
		}
		
		
        
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
	   			button.setColor(new Color(128, 128, 128, 128));
	         }
	   		 else {
	   			button.setColor(new Color(255, 255, 255, 0));
	   		 }
	   	}	
	}
	

	
}
=======
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
	MainCircle myCircle = new MainCircle(SCREEN_WIDTH/16, SCREEN_HEIGHT/3);
	MainSquare mySquare = new MainSquare(850, 570);
//	MainSquare mySquare = new MainSquare(800, 570, 130);
	MainDiamond myDiamond = new MainDiamond(1500, SCREEN_HEIGHT/3);
//	TriangleFaction myTriangle = new TriangleFaction(new int[]{100, 200, 150}, new int[]{100, 100, 200}, 3);
	MainTriangle myTriangle = new MainTriangle(850, 120);
	Point p;
	int x;
	int y;
	Timer timer;
	CircleFaction[] circleArray;
	List<CircleFaction> circles = new ArrayList<CircleFaction>();
	List<SquareFaction> squares = new ArrayList<SquareFaction>();
//	CircleFaction test = new CircleFaction(0, 0, "small");
	RectangularButton[] buttons = {
			new RectangularButton(30, 878, 110, 80),
			new RectangularButton(170, 878, 110, 80),
			new RectangularButton(310, 878, 110, 80)
	};
	Random rand = new Random();
	RectangularButton button;


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
//		test.draw(g);
		myCircle.draw(g);
		mySquare.draw(g);
		myDiamond.draw(g);
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
		for (int i = 0; i < squares.size(); i++) {
		    SquareFaction square = squares.get(i);
		    // Do something with car object
		    square.draw(g);
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//			System.out.println("You must move!");
		myCircle.move();
		mySquare.move();
//		test.move();
		for (int i = 0; i < circles.size(); i++) {
		    CircleFaction circle = circles.get(i);
		    // Do something with car object
		    circle.move();
		}

		for (int i = 0; i < squares.size(); i++) {
		    SquareFaction square = squares.get(i);
		    // Do something with car object
		    square.move();
		}
//
//        for (int i = 0; i < squares.size(); i++) {
//		    SquareFaction square = squares.get(i);
//		    if (mySquare.intersects(square)) {
//		    	System.out.println("Intersecting");
//		    	square.setDistance(0);
//		    }
//		    else {
//		    	System.out.println("Not intersecting");
//
//		    }
//        }
        for (int i = 0; i < squares.size(); i++) {
        	SquareFaction shapeOne = squares.get(i);
        	for (int j = 0; j < squares.size(); j++) {
        		if (i == j) {
        			continue;
        		}
        		SquareFaction shapeTwo = squares.get(j);
        		if (shapeOne.intersects(shapeTwo)) {
    		    	System.out.println("Intersecting");
    		    	shapeOne.setDistance(0);
    		    }
    		    else {
    		    	System.out.println("Not intersecting");

    		    }
        	}
        }

        for (int i = 0; i < circles.size(); i++) {
        	Shapes shapeOne = circles.get(i);
        	for (int j = 0; j < squares.size(); j++) {
//        		if (i == j) {
//        			continue;
//        		}
        		Shapes shapeTwo = squares.get(j);
        		if (shapeOne.intersects(shapeTwo)) {
    		    	System.out.println("Intersecting");
    		    	shapeOne.setDistance(0);
    		    }
    		    else {
    		    	System.out.println("Not intersecting");

    		    }
        	}
        }

        for (int i = 0; i < squares.size(); i++) {
        	Shapes shapeOne = squares.get(i);
        	for (int j = 0; j < circles.size(); j++) {
//        		if (i == j) {
//        			continue;
//        		}
        		Shapes shapeTwo = circles.get(j);
        		if (shapeOne.intersects(shapeTwo)) {
    		    	System.out.println("Intersecting");
    		    	shapeOne.setDistance(0);
    		    }
    		    else {
    		    	System.out.println("Not intersecting");

    		    }
        	}
        }



        for (int i = 0; i < circles.size(); i++) {
        	Shapes shapeOne = circles.get(i);
        	for (int j = 0; j < circles.size(); j++) {
        		if (i == j) {
        			continue;
        		}
        		Shapes shapeTwo = circles.get(j);
        		if (shapeOne.intersects(shapeTwo)) {
    		    	System.out.println("Intersecting");
    		    	shapeOne.setDistance(0);
    		    }
    		    else {
    		    	System.out.println("Not intersecting");

    		    }
        	}
        }



		repaint();

	}

	public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (myCircle.isMouseInsideMainCircle(mouseX, mouseY) && e.getButton() == MouseEvent.BUTTON1) {
        	System.out.println("Mouse clicked inside Circle");
            myCircle.setIsClicked(true);
        }

//        if (test.isMouseInsideCircle(mouseX, mouseY)){
//        	test.setIsClicked(true);
//        }

        else if (e.getButton() == MouseEvent.BUTTON1 && !(myCircle.isMouseInsideMainCircle(mouseX, mouseY))) {
        	myCircle.setIsClicked(false);
        	System.out.println("circle clicked set to false");
        }


        if (mySquare.isMouseInsideMainSquare(mouseX, mouseY)&& e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Mouse clicked inside Square");
            mySquare.setIsClicked(true);
        }

        else if (e.getButton() == MouseEvent.BUTTON1 && !(mySquare.isMouseInsideMainSquare(mouseX, mouseY))) {
        	mySquare.setIsClicked(false);
        	System.out.println("square clicked set to false");
        }


        if (myTriangle.isMouseInsideMainTriangle(mouseX, mouseY)) {
            System.out.println("Mouse clicked inside Triangle");
        }

        for (int i = 0; i < circles.size(); i++) {
		    CircleFaction circle = circles.get(i);
		    // Do something with car object
		    if (circle.isMouseInsideShape(mouseX, mouseY) && e.getButton() == MouseEvent.BUTTON1) {
	        	System.out.println("Mouse clicked inside Circle");
	            circle.setIsClicked(true);
	        }
		    else if (e.getButton() == MouseEvent.BUTTON1 && !(circle.isMouseInsideShape(mouseX, mouseY))) {
		    	circle.setIsClicked(false);
	        	System.out.println("circle clicked set to false");
	        }
		}

        for (int i = 0; i < squares.size(); i++) {
		    SquareFaction square = squares.get(i);
		    // Do something with car object
		    if (square.isMouseInsideShape(mouseX, mouseY) && e.getButton() == MouseEvent.BUTTON1) {
	        	System.out.println("Mouse clicked inside Circle");
	        	square.setIsClicked(true);
	        }
		    else if (e.getButton() == MouseEvent.BUTTON1 && !(square.isMouseInsideShape(mouseX, mouseY))) {
		    	square.setIsClicked(false);
	        	System.out.println("circle clicked set to false");
	        }
		}

        button = buttons[0];
		if (button.isMouseInsideSquare(mouseX, mouseY)) {
	//  			button.setColor(Color.cyan);
			while(true) {
				int randx = 130 + rand.nextInt(400 - 130 + 1);
				int randy = 200 + rand.nextInt(500 - 200 + 1);
				if(!myCircle.isMouseInsideMainCircle(randx, randy)) {
					circles.add(new CircleFaction(randx, randy, "small"));
					break;
				}
			}

	     }

		button = buttons[1];
		if (button.isMouseInsideSquare(mouseX, mouseY)) {
			//  			button.setColor(Color.cyan);
			while(true) {
				int randx = 130 + rand.nextInt(400 - 130 + 1);
				int randy = 200 + rand.nextInt(500 - 200 + 1);
				if(!myCircle.isMouseInsideMainCircle(randx, randy)) {
					circles.add(new CircleFaction(randx, randy, "medium"));
					break;
				}
			}

	    }

		button = buttons[2];
		if (button.isMouseInsideSquare(mouseX, mouseY)) {
			//  			button.setColor(Color.cyan);
			while(true) {
				int randx = 130 + rand.nextInt(400 - 130 + 1);
				int randy = 200 + rand.nextInt(500 - 200 + 1);
				if(!myCircle.isMouseInsideMainCircle(randx, randy)) {
					circles.add(new CircleFaction(randx, randy, "large"));
					break;
				}
			}

	     }

    }

	 public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
        int mouseY = e.getY();

		if (e.getButton() == MouseEvent.BUTTON3) {
        	if (myCircle.getIsClicked()) {
        		myCircle.getVector(mouseX, mouseY);
            	myCircle.setNewPosition(mouseX, mouseY);
            }
        	if (mySquare.getIsClicked()){
        		mySquare.getVector(mouseX, mouseY);
        		mySquare.setNewPosition(mouseX, mouseY);
        	}
	    	 for (int i = 0; i < circles.size(); i++) {
	    		 CircleFaction circle = circles.get(i);
	    		 if (circle.getIsClicked()) {
	    			 circle.getVector(mouseX, mouseY);
	    			 circle.setNewPosition(mouseX, mouseY);
	             }
	    	 }

	    	 for (int i = 0; i < squares.size(); i++) {
	    		 SquareFaction square = squares.get(i);
	    		 if (square.getIsClicked()) {
	    			 square.getVector(mouseX, mouseY);
	    			 square.setNewPosition(mouseX, mouseY);
	             }
	    	 }

//	    	 if (test.getIsClicked()) {
//	    		 System.out.println(mouseX + ", " + mouseY);
//
//	    		 test.getVector(mouseX, mouseY);
//	    		 test.setNewPosition(mouseX, mouseY);
//	    	 }
        }


		else if (e.getButton() == MouseEvent.BUTTON2) {
			squares.add(new SquareFaction(e.getX(),  e.getY(), "small"));
        }

	 }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX();
        int mouseY = e.getY();
        for (int i = 0; i < buttons.length; i++) {
	   		 RectangularButton button = buttons[i];
	   		 if (button.isMouseInsideSquare(mouseX, mouseY)) {
	   			button.setColor(new Color(128, 128, 128, 128));
	         }
	   		 else {
	   			button.setColor(new Color(255, 255, 255, 0));
	   		 }
	   	}
	}



}
>>>>>>> with-chat
