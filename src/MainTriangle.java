import java.awt.*;

public class MainTriangle {
    private int[] xPoints;
    private int[] yPoints;
    private int nPoints;
    private boolean isClicked;

    public MainTriangle(int centerX, int centerY, int size) {
        // Calculate the x and y coordinates for the other two vertices
        int halfSize = size / 2;
        int x1 = centerX - halfSize;
        int y1 = centerY + halfSize;
        int x2 = centerX + halfSize;
        int y2 = centerY + halfSize;
        
        // Store the coordinates in the xPoints and yPoints arrays
        this.xPoints = new int[] { centerX, x1, x2 };
        this.yPoints = new int[] { centerY, y1, y2 };
        
        this.nPoints = 3;
    }

    public int[] getXPoints() {
        return xPoints;
    }

    public void setXPoints(int[] xPoints) {
        this.xPoints = xPoints;
    }

    public int[] getYPoints() {
        return yPoints;
    }

    public void setYPoints(int[] yPoints) {
        this.yPoints = yPoints;
    }

    public void setIsClicked(boolean bool) {
        this.isClicked = bool;
    }

    public boolean getIsClicked() {
        return isClicked;
    }

    public int getNPoints() {
        return nPoints;
    }

    public void setNPoints(int nPoints) {
        this.nPoints = nPoints;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillPolygon(xPoints, yPoints, nPoints);
        g.drawPolygon(xPoints, yPoints, nPoints);
    }

    public boolean isMouseInsideTriangle(int mouseX, int mouseY) {
        int x1 = xPoints[0];
        int y1 = yPoints[0];
        int x2 = xPoints[1];
        int y2 = yPoints[1];
        int x3 = xPoints[2];
        int y3 = yPoints[2];

        int area = Math.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2);
        int s = y1*x2 - x1*y2 + x2*mouseY - y2*mouseX + mouseX*y1 - mouseY*x1;
        int t = y2*x3 - x2*y3 + x3*mouseY - y3*mouseX + mouseX*y2 - mouseY*x2;
        int u = y3*x1 - x3*y1 + x1*mouseY - y1*mouseX + mouseX*y3 - mouseY*x3;

        return (s >= 0 && t >= 0 && u >= 0) || (s <= 0 && t <= 0 && u <= 0);
    }

    public void setNewPosition(int[] xPoints, int[] yPoints) {
        // This method is not needed for a main triangle that cannot move
    }

    public void getVector(double newXPos, double newYPos) {
        // This method is not needed for a main triangle that cannot move
    }

    public void move() {
        // This method is not needed for a main triangle that cannot move
    }
}
