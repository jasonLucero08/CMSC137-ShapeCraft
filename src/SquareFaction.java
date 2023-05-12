import java.awt.Color;
import java.awt.Graphics;

public class SquareFaction {
    private int x;
    private int y;
    private int newXpos;
    private int newYpos;
    private int width;
    private int height;
    private boolean isClicked;
    private boolean isMoving;
    private double dx;
    private double dy;
    private double distance;

    public SquareFaction(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
        g.drawRect(x, y, width, height);
    }

    public boolean isMouseInsideSquare(int mouseX, int mouseY) {
        return (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
    }
    
    public void setNewPosition(int x, int y) {
    	this.newXpos = x;
    	this.newYpos = y;
    	
    }

    public void getVector(double newXPos, double newYPos) {
        double dx = newXPos - this.getX();
        double dy = newYPos - this.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Calculate the magnitude of the vector
        double magnitude = 5.0; // Adjust the speed factor as needed

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
            this.distance -= 5;
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
