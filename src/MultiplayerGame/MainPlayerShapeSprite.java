package MultiplayerGame;

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D;

public class MainPlayerShapeSprite {
	protected Image shapeImage;
//	private String shapeImage;
	protected double x, y, size;
	protected Color color;

	public MainPlayerShapeSprite(double x, double y, double size, Color colore) {
//		try {
//		    img = ImageIO.read(new File("images/MainCircle.png"));
//		} catch (IOException e) {
//		}
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
		
	
	}
	
	
	public void drawSprite(Graphics2D g2d) {
		Rectangle2D.Double imageSquare = new Rectangle2D.Double(this.x, this.y, size, size);
		g2d.drawImage(shapeImage, (int) imageSquare.getX(), (int) imageSquare.getY(), (int) imageSquare.getWidth(), (int) imageSquare.getHeight(), null);
//		g2d.setColor(color);
//		g2d.fill(square);
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
