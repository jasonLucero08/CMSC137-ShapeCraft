package Shapecraft;
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

import MultiplayerGame.GameServer;
import MultiplayerGame.PlayerFrame;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
	Image menuBackground = Toolkit.getDefaultToolkit().getImage("images//1.png").getScaledInstance(1360, 960, Image.SCALE_SMOOTH);
	Image playBackground = Toolkit.getDefaultToolkit().getImage("images//2.png").getScaledInstance(1360, 960, Image.SCALE_SMOOTH);
	Image singlePlayerButton = Toolkit.getDefaultToolkit().getImage("images//SingleplayerButton.png").getScaledInstance(70, 50, Image.SCALE_SMOOTH);
	Image multiPlayerButton = Toolkit.getDefaultToolkit().getImage("images//MultiplayerButton.png").getScaledInstance(70, 50, Image.SCALE_SMOOTH);
	static final int SCREEN_WIDTH = 1360;
	static final int SCREEN_HEIGHT = 960;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 30;
	char direction = 'R';
	MainCircle myCircle = new MainCircle(SCREEN_WIDTH/16, SCREEN_HEIGHT/3);
	MainSquare mySquare = new MainSquare(635, 570);
//	MainSquare mySquare = new MainSquare(800, 570, 130);
	MainDiamond myDiamond = new MainDiamond(1170, SCREEN_HEIGHT/3);
//	TriangleFaction myTriangle = new TriangleFaction(new int[]{100, 200, 150}, new int[]{100, 100, 200}, 3);
	MainTriangle myTriangle = new MainTriangle(635, 75);
	Point p;
	int x;
	int y;
	Timer timer;
	CircleFaction[] circleArray;
	List<CircleFaction> circles = new ArrayList<CircleFaction>();
	List<SquareFaction> squares = new ArrayList<SquareFaction>();
//	CircleFaction test = new CircleFaction(0, 0, "small");
	RectangularButton[] menuButtons = {
			new RectangularButton(163, 370, 287, 75),
			new RectangularButton(163, 575, 287, 75),
			new RectangularButton(192, 712, 228, 60),
			new RectangularButton(220, 820, 173, 45),
	};
	RectangularButton[] buttons = {
			new RectangularButton(22, 842, 82, 80),
			new RectangularButton(125, 842, 82, 80),
			new RectangularButton(228, 842, 82, 80)
	};
	Random rand = new Random();
	RectangularButton button;

	// Game States
	public final int menuState = 1;
	public final int singleState = 2;
	public final int multiState = 3;
	public int gameState = menuState;

	private CountdownTimer countdownTimer;

	GamePanel(){

		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.white);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		timer = new Timer(DELAY, this);
		timer.start();

		countdownTimer = new CountdownTimer();
		countdownTimer.setBounds(360, 780, 650, 160); // Set the position and size of the countdown timer
		this.setLayout(null); // Set the layout manager to null for absolute positioning
		this.add(countdownTimer);
	}

	public void draw(Graphics g) {
		if (gameState == menuState) {
			for (int i = 0; i < menuButtons.length; i++) {
				RectangularButton button = menuButtons[i];
		   		button.draw(g);
			}
		} else if (gameState == singleState) {
//			p = MouseInfo.getPointerInfo().getLocation();

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
		}
//
	}

	int count = 0;
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameState == menuState) {
        	g.drawImage(menuBackground, 0, 0, null);
            draw(g);
        } else if (gameState == singleState) {
        	g.drawImage(playBackground, 0, 0, null);
            draw(g);
        } else {
        	if (count == 0) {
        		PlayerFrame multiplayer = new PlayerFrame(SCREEN_WIDTH, SCREEN_HEIGHT);
            	multiplayer.connectToServer();
            	multiplayer.setUpGUI();

            	count++;
        	}
        }
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
        		if (shapeOne.getSize() == "small") {
        			myCircle.health -= 10;
        		} else if (shapeOne.getSize() == "medium") {
        			myCircle.health -= 15;
        		} else if (shapeOne.getSize() == "large") {
        			myCircle.health -= 20;
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


        for (int i = 0; i < circles.size(); i++) {
        	Shapes shapeOne = circles.get(i);
        	if(mySquare.intersects(shapeOne)) {
        		circles.remove(shapeOne);
        		if (shapeOne.getSize() == "small") {
        			mySquare.health -= 10;
        		} else if (shapeOne.getSize() == "medium") {
        			mySquare.health -= 15;
        		} else if (shapeOne.getSize() == "large") {
        			mySquare.health -= 20;
        		}

        	}
        }

        for (int i = 0; i < squares.size(); i++) {
        	Shapes shapeOne = squares.get(i);
        	if(myTriangle.intersects(shapeOne)) {
        		squares.remove(shapeOne);
        		if (shapeOne.getSize() == "small") {
        			myTriangle.health -= 10;
        		} else if (shapeOne.getSize() == "medium") {
        			myTriangle.health -= 15;
        		} else if (shapeOne.getSize() == "large") {
        			myTriangle.health -= 20;
        		}
        	}
        }

        for (int i = 0; i < squares.size(); i++) {
        	Shapes shapeOne = squares.get(i);
        	if(myDiamond.intersects(shapeOne)) {
        		squares.remove(shapeOne);
        		if (shapeOne.getSize() == "small") {
        			myDiamond.health -= 10;
        		} else if (shapeOne.getSize() == "medium") {
        			myDiamond.health -= 15;
        		} else if (shapeOne.getSize() == "large") {
        			myDiamond.health -= 20;
        		}
        	}
        }



		if (myCircle.health <= 0) {
            // Game over screen logic
            int option = JOptionPane.showOptionDialog(this, "YOU WIN!", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Close"}, "Close");
            if (option == JOptionPane.OK_OPTION) {
                // Additional actions after the game over screen is closed
                // Close the game
                System.exit(0);
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


        button = menuButtons[0];
        if (button.isMouseInsideSquare(mouseX, mouseY)) {
        	gameState = singleState;
	    }

        button = menuButtons[1];
        if (button.isMouseInsideSquare(mouseX, mouseY)) {
        	gameState = multiState;
	    }

        button = menuButtons[2];
        if (button.isMouseInsideSquare(mouseX, mouseY)) {
        	GameServer multiplayerServer = new GameServer();
        	multiplayerServer.acceptConnections();
	    }

        button = menuButtons[3];
        if (button.isMouseInsideSquare(mouseX, mouseY)) {
        	System.exit(0);
	    }

        button = buttons[0];
		if (button.isMouseInsideSquare(mouseX, mouseY)) {
	//  			button.setColor(Color.cyan);
			int randx = rand.nextInt(1360);
			SquareFaction square = new SquareFaction(650, 600, "small");
//			CircleFaction circle = new CircleFaction(150, 360, "small");
			squares.add(square);
			square.getVector(randx, 100);

	     }

		button = buttons[1];
		if (button.isMouseInsideSquare(mouseX, mouseY)) {
			//  			button.setColor(Color.cyan);
			int randx = rand.nextInt(1360);
			SquareFaction square = new SquareFaction(650, 600, "medium");
//			CircleFaction circle = new CircleFaction(150, 360, "small");
			squares.add(square);
			square.getVector(randx, 100);

	    }

		button = buttons[2];
		if (button.isMouseInsideSquare(mouseX, mouseY)) {
			//  			button.setColor(Color.cyan);
			int randx = rand.nextInt(1360);
			SquareFaction square = new SquareFaction(650, 600, "large");
//			CircleFaction circle = new CircleFaction(150, 360, "small");
			squares.add(square);
			square.getVector(randx, 100);

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
			circles.add(new CircleFaction(e.getX(),  e.getY(), "small"));
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
        if (gameState == menuState) {
        	for (int i = 0; i < menuButtons.length; i++) {
	   	   		 RectangularButton button = menuButtons[i];
	   	   		 if (button.isMouseInsideSquare(mouseX, mouseY)) {
	   	   			button.setColor(new Color(128, 128, 128, 128));
	   	         }
	   	   		 else {
	   	   			button.setColor(new Color(255, 255, 255, 0));
	   	   		 }
        	}

        } else {
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



}
