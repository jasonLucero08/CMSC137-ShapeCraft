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
        
        this.isClicked = false;
        this.isMoving = false;

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
//        g.setColor(Color.WHITE);
//        g.fillRect(x, y, width, height);
        g.drawRect(x, y, width, height);
        
        // Draw the image
        g.drawImage(this.image, x, y, width, height, null);
    }

    public boolean isMouseInsideShape(int mouseX, int mouseY) {
        return (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
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
        if (this.distance > 0) {
            this.x += this.dx;
            this.y += this.dy;
            this.distance -= this.speed;
        } else {
            this.isMoving = false;
        }
    }

    public boolean getIsMoving() {
        return isMoving;
    }

    public void setIsMoving(boolean bool) {
        this.isMoving = bool;
    }
    
}
