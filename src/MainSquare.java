import java.awt.*;

public class MainSquare {
    private int x;
    private int y;
    private int sideLength;
    private boolean isClicked;

    public MainSquare(int x, int y, int sideLength) {
        this.x = x;
        this.y = y;
        this.sideLength = sideLength;
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

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, sideLength, sideLength);
        g.drawRect(x, y, sideLength, sideLength);
    }

    public boolean isMouseInsideSquare(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + sideLength && mouseY >= y && mouseY <= y + sideLength;
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
