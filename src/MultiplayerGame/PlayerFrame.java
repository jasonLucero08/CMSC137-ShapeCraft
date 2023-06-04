package MultiplayerGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Shapecraft.CircleFaction;
import Shapecraft.SquareFaction;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerFrame extends JFrame{
	private Image playBackground = Toolkit.getDefaultToolkit().getImage("images//2.png").getScaledInstance(1360, 960, Image.SCALE_SMOOTH);
	private int width, height;
	private Container contentPane;
	
	private MainPlayerShapeSprite me;
	private MainPlayerShapeSprite enemy;
	
	private String currentPlayer;
	
	private DrawingComponent dc;
	private Timer animationTimer;
	private boolean up, down, left, right;
	private Socket socket;
	private int playerID;
	private ReadFromServer rfsRunnable;
	private WriteToServer wtsRunnable;

	List<MainPlayerShapeSprite> circles = new ArrayList<MainPlayerShapeSprite>();
	List<MainPlayerShapeSprite> squares = new ArrayList<MainPlayerShapeSprite>();
	
	public PlayerFrame(int w, int h) {
		this.width = w;
		this.height = h;
		up = false;
		down = false;
		left = false;
		right = false;
	}

	public void setUpGUI() {
		contentPane = this.getContentPane();
		this.setTitle("Player #" + playerID);
		contentPane.setPreferredSize(new Dimension(this.width, this.height));;
		this.createSprites();
		this.dc = new DrawingComponent();
		contentPane.add(this.dc);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

		setUpAnimationTimer();
		setUpKeyListener();
		setUpMouseListener();

	}

	private void createSprites() {
		if(playerID == 1) {
			me = new SquarePlayer(100, 400, 50, Color.BLUE);
			squares.add(me);
			enemy = new CirclePlayer(1170, 400, 50, Color.RED);
			circles.add(enemy);
			currentPlayer = "me";
		}
		else {
			enemy = new SquarePlayer(100, 400, 50, Color.BLUE);
			squares.add(enemy);
			me = new CirclePlayer(1170, 400, 50, Color.RED);
			circles.add(me);
			currentPlayer = "enemy";

		}
	}

	private void setUpAnimationTimer() {
		int interval = 10;
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(currentPlayer == "me") {
					for (int i = 0; i < squares.size(); i++) {
						MainPlayerShapeSprite square = squares.get(i);
						square.move();
			    	 }
				}
				else if(currentPlayer == "enemy"){
					for (int i = 0; i < circles.size(); i++) {
						MainPlayerShapeSprite circle = circles.get(i);
						circle.move();
			    	 }
				}
//				System.out.println("Animation should it move: " + me.isMoving);
//				System.out.println("outside distance: " + me.distance);
//				System.out.println("outside x: " + me.getX());
//				System.out.println("outside dx: " + me.dx);
//				System.out.println("outside dy: " + me.dy);
				double speed = 5;
				if(up) {
					me.moveV(-speed);
				} 
				else if(down) {
					me.moveV(speed);
				}
				else if(left) {
					me.moveH(-speed);
				}
				else if(right) {
					me.moveH(speed);
				}
				dc.repaint();
			}
		};
		this.animationTimer = new Timer(interval, al);
		this.animationTimer.start();
	}

	private void connectToServer() {
		try {
			socket = new Socket("localhost", 45371);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			playerID = in.readInt();
			System.out.println("You are player#" + playerID);

			if(playerID == 1) {
				System.out.println("Waiting for Player #2 to connect...");
			}

			rfsRunnable = new ReadFromServer(in);
			wtsRunnable = new WriteToServer(out);
			rfsRunnable.waitForStartMsg();
		}
		catch(IOException ex) {
			System.out.println("IOException from connectTOserver");
		}
	}

	private void setUpKeyListener() {
		KeyListener kl = new KeyListener() {			
			public void keyReleased(KeyEvent ke) {
				int keyCode = ke.getKeyCode();

				switch(keyCode) {
					case KeyEvent.VK_UP:
						up = false;
						break;
					case KeyEvent.VK_DOWN:
						down = false;
						break;
					case KeyEvent.VK_LEFT:
						left = false;
						break;
					case KeyEvent.VK_RIGHT:
						right = false;
						break;
					}
			}


			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub


			}
			

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyCode = e.getKeyCode();

				switch(keyCode) {
					case KeyEvent.VK_UP:
						up = true;
						break;
					case KeyEvent.VK_DOWN:
						down = true;
						break;
					case KeyEvent.VK_LEFT:
						left = true;
						break;
					case KeyEvent.VK_RIGHT:
						right = true;
						break;
				}
			}
		};
		contentPane.addKeyListener(kl);
		contentPane.setFocusable(true);
	}
	
	private void setUpMouseListener() {
		MouseListener ml = new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int mouseX = e.getX();
		        int mouseY = e.getY();
				// TODO Auto-generated method stub
		        if(me.getIsClicked()) {
		        	me.getVector(mouseX, mouseY);
		        }
		        
		        if(me.isMouseInsideShape(mouseX, mouseY)) {
		        	System.out.println("Mouse x and y:" + mouseX + ", " + mouseY);
					System.out.println("Client object x and y:" + me.getX() + ", " + me.getY());
					System.out.println("Mouse x and y inside shape");
					me.setIsClicked(true);
		        }
		        else {
		        	me.setIsClicked(false);
		        }

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}			
			
		};
		contentPane.addMouseListener(ml);
		contentPane.setFocusable(true);
	}

	private class DrawingComponent extends JComponent{
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(playBackground, 0, 0, getWidth(), getHeight(), null);
			enemy.drawSprite(g2d);
			me.drawSprite(g2d);
		}
	}

	private class ReadFromServer implements Runnable {
		private DataInputStream dataIn;

		public ReadFromServer(DataInputStream in) {
			dataIn = in;
			System.out.println("RFS Runnable created");
		}

		public void run() {
			try {
				while(true) {
					if(enemy!=null) {
//						System.out.println(dataIn.readDouble());
//						System.out.println(dataIn.readDouble());
						if(currentPlayer == "enemy") {
							for (int i = 0; i < squares.size(); i++) {
								MainPlayerShapeSprite square = squares.get(i);
								square.setX(dataIn.readDouble());
								square.setY(dataIn.readDouble());
					    	 }
							
						}
						else {
							for (int i = 0; i < circles.size(); i++) {
								MainPlayerShapeSprite circle = circles.get(i);
								circle.setX(dataIn.readDouble());
								circle.setY(dataIn.readDouble());
					    	 }
						}
						
//						System.out.println("enemy shape x and y" + enemy.getX() + ' ' + enemy.getY());
//						System.out.println("me shape x and y" + me.getX() + ' ' + me.getY());
					}
				}
			} 
			catch(IOException ex) {
				System.out.println("IOException from RFS run()");
			}
		}

		public void waitForStartMsg() {
			try {
				String startMsg = dataIn.readUTF();
				System.out.println("Message from server: " + startMsg);
				Thread readThread = new Thread(rfsRunnable);
				Thread writeThread = new Thread(wtsRunnable);
				readThread.start();	
				writeThread.start();	
			} 
			catch(IOException ex) {
				System.out.println("IOException from waitForStartMsg");
			}
		}
	}

	private class WriteToServer implements Runnable {
		private DataOutputStream dataOut;

		public WriteToServer(DataOutputStream out) {
			dataOut = out;
			System.out.println("WTS Runnable created");
		}

		public void run() {
			try {
				while(true) {
					if(me != null) {
						if(currentPlayer == "enemy") {
							for (int i = 0; i < circles.size(); i++) {
								MainPlayerShapeSprite circle = circles.get(i);
								dataOut.writeDouble(circle.getX());
								dataOut.writeDouble(circle.getY());
								dataOut.flush();
							}
						}
						else {
							for (int i = 0; i < squares.size(); i++) {
								MainPlayerShapeSprite square = squares.get(i);
								dataOut.writeDouble(square.getX());
								dataOut.writeDouble(square.getY());
								dataOut.flush();
					    	 }
						}
					}
					try {
						Thread.sleep(1);
					} catch(InterruptedException ex) {
						System.out.println("InterruptedException from WTS run()");
					}
				}
			} 
			catch(IOException ex) {
				System.out.println("IOException from WTS run()");
			}
		}
	}


	public static void main(String[]args) {
		PlayerFrame pf = new PlayerFrame(1360, 960);
		pf.connectToServer();
		pf.setUpGUI();
	}
}