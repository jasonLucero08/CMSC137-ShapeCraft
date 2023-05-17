<<<<<<< HEAD
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class TriangleFaction extends Shapes{
	public TriangleFaction(int x, int y, String size) {
    	super(x, y, size);
//        this.image = this.image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    	Image imageLocation = new ImageIcon("images//TriangleUnit.png").getImage();
    	this.image = imageLocation.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    }
=======
import java.awt.Color;
import java.awt.Graphics;

public class TriangleFaction {
    private int[] xPoints;
    private int[] yPoints;
    private int nPoints;
    private int isClicked;

    public TriangleFaction(int[] xPoints, int[] yPoints, int nPoints) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.nPoints = nPoints;
        this.isClicked = isClicked;
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

    public int getNPoints() {
        return nPoints;
    }

    public void setNPoints(int nPoints) {
        this.nPoints = nPoints;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillPolygon(xPoints, yPoints, nPoints);
        g.drawPolygon(xPoints, yPoints, nPoints);
    }

    public boolean isMouseInsideTriangle(int mouseX, int mouseY) {
        int j = nPoints - 1;
        boolean isInside = false;
        for (int i = 0; i < nPoints; i++) {
            if (yPoints[i] < mouseY && yPoints[j] >= mouseY || yPoints[j] < mouseY && yPoints[i] >= mouseY) {
                if (xPoints[i] + (mouseY - yPoints[i]) / (double) (yPoints[j] - yPoints[i]) * (xPoints[j] - xPoints[i]) < mouseX) {
                    isInside = !isInside;
                }
            }
            j = i;
        }
        return isInside;
    }

    public void move(int deltaX, int deltaY) {
        for (int i = 0; i < nPoints; i++) {
            xPoints[i] += deltaX;
            yPoints[i] += deltaY;
        }
    }
>>>>>>> with-chat
}