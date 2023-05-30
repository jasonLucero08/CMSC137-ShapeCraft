package Shapecraft;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class RectangularButton extends JPanel {
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
    private Color color;

    public RectangularButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isClicked = false;
        this.isMoving = false;
        this.color = new Color(255, 255, 255, 0);

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
    
    public void setColor(Color color) {
    	this.color = color;
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
        g.setColor(color);
        g.fillRect(x, y, width, height);
//        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    public boolean isMouseInsideSquare(int mouseX, int mouseY) {
        return (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
    }

    public void setNewPosition(int x, int y) {
        this.newXpos = x;
        this.newYpos = y;

    }

    public boolean getIsMoving() {
        return isMoving;
    }

    public void setIsMoving(boolean bool) {
        this.isMoving = bool;
    }
}
