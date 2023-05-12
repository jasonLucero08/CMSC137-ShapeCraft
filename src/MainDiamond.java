import java.awt.*;

public class MainDiamond {
    private int x;
    private int y;
    private int size;
    private boolean isClicked;

    public MainDiamond(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void draw(Graphics g) {
        g.setColor(Color.green);
        int[] xPoints = { x + size/2, x + size, x + size/2, x };
        int[] yPoints = { y, y + size/2, y + size, y + size/2 };
        g.fillPolygon(xPoints, yPoints, 4);
        g.setColor(Color.black);
        g.drawPolygon(xPoints, yPoints, 4);
    }

    public boolean isMouseInsideDiamond(int mouseX, int mouseY) {
        int dx = Math.abs(mouseX - (x + size / 2));
        int dy = Math.abs(mouseY - (y + size / 2));
        return dx + dy <= size / 2;
    }
    
    public void setNewPosition(int x, int y) {
        // This method is not needed for a main diamond that cannot move
    }

    public void getVector(double newXPos, double newYPos) {
        // This method is not needed for a main diamond that cannot move
    }

    public void move() {
        // This method is not needed for a main diamond that cannot move
    }
}
