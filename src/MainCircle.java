import java.awt.*;

public class MainCircle {
    private int x;
    private int y;
    private int radius;
    private boolean isClicked;

    public MainCircle(int x, int y, int radius) {
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

    public void draw(Graphics g) {
        g.setColor(Color.cyan);
        g.fillOval(x, y, radius, radius);
        g.drawOval(x, y, radius, radius);
    }

    public boolean isMouseInsideCircle(int mouseX, int mouseY) {
        int distanceX = mouseX - (x + radius / 2);
        int distanceY = mouseY - (y + radius / 2);
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        return distance <= radius / 2;
    }
    
    public void setNewPosition(int x, int y) {
        // This method is not needed for a main circle that cannot move
    }

    public void getVector(double newXPos, double newYPos) {
        // This method is not needed for a main circle that cannot move
    }

    public void move() {
        // This method is not needed for a main circle that cannot move
    }
}
