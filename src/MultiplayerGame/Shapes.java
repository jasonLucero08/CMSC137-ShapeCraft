package MultiplayerGame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Shapes {
	protected int x;
	protected int y;
	protected int newXpos;
	protected int newYpos;
	protected int width;
	protected int height;
	protected boolean isClicked;
	protected boolean isMoving;
	protected double dx;
	protected double dy;
	protected double distance;
	protected double speed;
	protected String size;
	protected String direction;
	protected Image image;

    
    public Shapes(int x, int y, String size) {
        this.x = x;
        this.y = y;
        if(size == "small") {
        	this.width = 25;
            this.height = 25;
            this.speed = 5;
        }
        else if (size == "medium") {
        	this.width = 50;
            this.height = 50;
            this.speed = 3;
        }
        else if (size == "large") {
        	this.width = 80;
            this.height = 80;
            this.speed = 2;
        }
        this.size = size;
        this.isClicked = false;
        this.isMoving = false;

    }
    
    public String getSize() {
    	return this.size;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setIsClicked(boolean bool) {
    	this.isClicked = bool;
    }
    
    public boolean getIsClicked() {
    	return isClicked;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setDistance(int distance) {
        this.distance = distance;
    }
    

    public void draw(Graphics g) {
//        
    	if(this.getIsClicked() == true) {
    	  g.setColor(Color.WHITE);
//          g.fillRect(x, y, width, height);
    	  g.drawRect(this.x -4, this.y - 4, this.width + 7, this.height + 7);
    	}
        
        
        // Draw the image
        g.drawImage(this.image, this.x, this.y, this.width, this.height, null);
    }

    public boolean isMouseInsideShape(int mouseX, int mouseY) {
        return (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height);
    }
    
    public void setNewPosition(int x, int y) {
    	this.newXpos = x;
    	this.newYpos = y;
    	
    }
    
    public boolean intersects(Shapes shape) {
        int thisLeft = this.x;
        int thisRight = this.x + this.width;
        int thisTop = this.y;
        int thisBottom = this.y + this.height;

        int otherLeft = shape.getX();
        int otherRight = shape.getX() + shape.getWidth();
        int otherTop = shape.getY();
        int otherBottom = shape.getY() + shape.getHeight();

        return thisLeft < otherRight && thisRight > otherLeft &&
               thisTop < otherBottom && thisBottom > otherTop;
    }


    public void getVector(double newXPos, double newYPos) {
        double dx = newXPos - this.getX();
        double dy = newYPos - this.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Calculate the magnitude of the vector
        double magnitude = this.speed; // Adjust the speed factor as needed

        // Normalize the vector and multiply by the speed factor
        this.dx = dx * magnitude / distance;
        this.dy = dy * magnitude / distance;

        // Update the distance value
        this.distance = distance;
        this.isMoving = true;
    }

    public void move() {
        if (this.distance > 0 && this.x > 70 && this.x < 1270 && this.y > 70 && this.y < 680) {
            this.x += this.dx;
            this.y += this.dy;
            this.distance -= this.speed;
        } else {
            this.isMoving = false;
        }
    }
    
    public void getQuadrant(double newXPos, double newYPos) {
    	if(newXPos > this.x && newYPos < this.y) {
    		System.out.println("First Quadrant");
    		this.direction = "First Quadrant" ;
    	}
    	else if (newXPos < this.x && newYPos < this.y){
    		this.direction = "Second Quadrant" ;
    	}
    	else if (newXPos < this.x && newYPos > this.y){
    		this.direction = "Third Quadrant" ;
    	}
    	else if (newXPos > this.x && newYPos > this.y){
    		this.direction = "Fourth Quadrant" ;
    	}
    }
    
    public String getDirection() {
    	return this.direction;
    }

    public boolean getIsMoving() {
        return isMoving;
    }

    public void setIsMoving(boolean bool) {
        this.isMoving = bool;
    }
    
}
