package MultiplayerGame;

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D;

public class MainPlayerShapeSprite {
	protected Image shapeImage;
//	private String shapeImage;
	protected double x, y, size;
	protected Color color;
	
	protected double dx;
	protected double dy;
	
	protected double distance;
	protected double speed;
	
	protected boolean isClicked;
	protected boolean isMoving;

	public MainPlayerShapeSprite(double x, double y, double size, Color colore) {
//		try {
//		    img = ImageIO.read(new File("images/MainCircle.png"));
//		} catch (IOException e) {
//		}
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
		this.isClicked = false;
        this.isMoving = false;
		this.speed = 1;
	
	}
	
	public boolean isMouseInsideShape(int mouseX, int mouseY) {
        return (mouseX >= this.x && mouseX <= this.x + this.size && mouseY >= this.y && mouseY <= this.y + this.size);
    }
	
	public void drawSprite(Graphics2D g2d) {
		Rectangle2D.Double imageSquare = new Rectangle2D.Double(this.x, this.y, size, size);
		g2d.drawImage(shapeImage, (int) imageSquare.getX(), (int) imageSquare.getY(), (int) imageSquare.getWidth(), (int) imageSquare.getHeight(), null);

//		g2d.setColor(color);
//		g2d.fill(square);
	}
	
	public boolean containsPoint(double x, double y) {
	    Rectangle2D.Double bounds = new Rectangle2D.Double(this.x, this.y, size, size);
	    return bounds.contains(x, y);
	}
	
	 public void move() {
	    System.out.println("inside dx: " + this.dx);
		System.out.println("inside dy: " + this.dy);
        if (this.distance > 0) {
//        	System.out.println(this.distance + " is greater");
            // Check if the object hits the walls
//            if (this.x <= 70)
//                this.dx += 1; // Reverse the horizontal direction
//            if (this.x >= 1270)
//            	this.dx -= 1;
//            
//            if (this.y <= 70)
//                this.dy += 1; // Reverse the vertical direction
//            if (this.y >= 680)
//            	this.dy -= 1;
        	
            // Update the object's position
        	System.out.println("BLUE");
            this.x += this.dx;
            this.y += this.dy;
//            
            this.distance -= this.speed;
        } 
        else {
        	System.out.println("RED");
            this.isMoving = false;
        }
     }
	 
	 public void getVector(double newXPos, double newYPos) {
        double dx = newXPos - this.getX();
        double dy = newYPos - this.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
//	        System.out.println("RUNS");
//	        System.out.println(distance);
        // Calculate the magnitude of the vector
        double magnitude = this.speed; // Adjust the speed factor as needed

        // Normalize the vector and multiply by the speed factor
        this.dx = dx * magnitude / distance;
        this.dy = dy * magnitude / distance;

        // Update the distance value
        this.distance = distance;
        this.isMoving = true;
    }


	
	public void moveH(double n) {
		this.x +=  n;
	}
	

	public void moveV(double n) {
		this.y +=  n;
	}
	

	public void setX(double n) {
		this.x = n;
	}
	

	public void setY(double n) {
		this.y = n;
	}
	
	public double getX() {
		return this.x;
	}
	

	public double getY() {
		return this.y;
	}
}
