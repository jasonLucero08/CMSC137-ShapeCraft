import java.awt.*;

import javax.swing.ImageIcon;

public class MainTriangle {
    private int x;
    private int y;
    private boolean isClicked;
    protected int width = 150;
	protected int height= 150;
	protected Image image;
	

    public MainTriangle(int x, int y) {
        this.x = x;
        this.y = y;
        Image imageLocation = new ImageIcon("images//MainTriangle.png").getImage();
    	this.image = imageLocation.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
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

    public void draw(Graphics g) {
//        g.setColor(Color.ORANGE);
//        g.fillRect(x, y, width, height);
//        g.drawRect(x, y, width, height);
        
        g.drawImage(this.image, x, y, width, height, null);
    }

    public boolean isMouseInsideMainTriangle(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public void setNewPosition(int x, int y) {
        // This method is not needed for a main square that cannot move
    }

    public void getVector(double newXPos, double newYPos) {
        // This method is not needed for a main square that cannot move
    }

    public void move() {
        // This method is not needed for a main square that cannot move
    }
}
