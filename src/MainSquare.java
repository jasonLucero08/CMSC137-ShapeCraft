import java.awt.*;

import javax.swing.ImageIcon;

public class MainSquare {
    private int x;
    private int y;
    private boolean isClicked;
    protected int width = 110;
	protected int height= 110;
	protected Image image;
	protected int health;


    public MainSquare(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 100;
        Image imageLocation = new ImageIcon("images//MainSquare.png").getImage();
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

    	g.setColor(Color.RED);
        int barWidth = width;
        int barHeight = 10;
        int barX = x;
        int barY = y - barHeight - 5;
        g.fillRect(barX, barY, barWidth, barHeight);

        g.setColor(Color.GREEN);
        int healthBarWidth = (int) (barWidth * (health / 100.0));
        g.fillRect(barX, barY, healthBarWidth, barHeight);

        g.drawImage(this.image, x, y, width, height, null);
    }

    public boolean isMouseInsideMainSquare(int mouseX, int mouseY) {
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
