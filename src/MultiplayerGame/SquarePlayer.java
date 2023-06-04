package MultiplayerGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

public class SquarePlayer extends MainPlayerShapeSprite {
	
	
	public SquarePlayer(double x, double y, int size, Color color) {
		super(x, y, size, color);
		this.shapeImage = Toolkit.getDefaultToolkit().getImage("images//MainSquare.png").getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		
	}
	
	public void drawSprite(Graphics2D g2d) {
		Rectangle2D.Double imageSquare = new Rectangle2D.Double(this.x, this.y, size, size);
		g2d.drawImage(shapeImage, (int) imageSquare.getX(), (int) imageSquare.getY(), (int) imageSquare.getWidth(), (int) imageSquare.getHeight(), null);
//		g2d.setColor(color);
//		g2d.fill(square);
	}
}