import java.awt.*;

import javax.swing.ImageIcon;

public class MainDiamond {
    private int x;
    private int y;
    private boolean isClicked;
    protected int width = 110;
	protected int height= 110;
	protected Image image;


    public MainDiamond(int x, int y) {
        this.x = x;
        this.y = y;
        Image imageLocation = new ImageIcon("images//MainDiamond.png").getImage();
    	this.image = imageLocation.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
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

    public boolean isMouseInsideMainDiamond(int mouseX, int mouseY) {
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
