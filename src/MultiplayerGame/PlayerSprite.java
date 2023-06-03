package MultiplayerGame;

import java.awt.*;
import java.awt.geom.*;

public class PlayerSprite {
	
	private double x, y, size;
	private Color color;
	
	public PlayerSprite(double x, double y, double size, Color color) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
				
	}
	
	public void drawSprite(Graphics2D g2d) {
		Rectangle2D.Double square = new Rectangle2D.Double(x, y, size, size);
		g2d.setColor(color);
		g2d.fill(square);
	}
	
	public void moveH(double n) {
		this.x +=  n;
	}
	

	public void moveV(double n) {
		this.y +=  n;
	}
	

	public void setX(double n) {
		this.x = n;
	}
	

	public void setY(double n) {
		this.y = n;
	}
	
	public double getX() {
		return this.x;
	}
	

	public double getY() {
		return this.y;
	}
}
