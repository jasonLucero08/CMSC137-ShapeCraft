import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class CircleFaction {
    private int x;
    private int y;
    private int newXpos;
    private int newYpos;
    private int radius;
    private boolean isClicked;
    private boolean isMoving;
    private double dx;
	private double dy;
	private double distance;

    public CircleFaction(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public void setIsClicked(boolean bool) {
    	this.isClicked = bool;
    }
    
    public boolean getIsClicked() {
    	return isClicked;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    public boolean getIsMoving() {
    	return isMoving;
    }
    
    public void setIsMoving(boolean bool) {
    	 this.isMoving = bool;
    }


    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x, y, radius, radius);
        g.drawOval(x, y, radius, radius);
    }
    
    public boolean isMouseInsideCircle(int mouseX, int mouseY) {
        int distanceX = mouseX - (x + radius/2);
        int distanceY = mouseY - (y + radius/2);
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
//        System.out.println(radius/2);
//        System.out.println(distance);
        return distance <= radius/2;
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
        
    }

    // + + - -
    // + - - +
    public void move() {

//    	System.out.println("The dx value: " + this.dx);
//        System.out.println("The dy value: " + this.dy);
//        System.out.println("new x value: " + this.newXpos);
//        System.out.println("new y value: " + this.newYpos);
//
//        System.out.println("Current x value: " + this.x);
//        System.out.println("Current y value: " + this.y);

        if (this.distance > 0) {
            this.x += this.dx;
            this.y += this.dy;
            this.distance -= 5;
        } else {
            this.setIsMoving(false);
        }
    }
}
