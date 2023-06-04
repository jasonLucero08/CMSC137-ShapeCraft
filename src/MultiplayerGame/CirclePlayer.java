package MultiplayerGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class CirclePlayer extends MainPlayerShapeSprite {
	
	
	public CirclePlayer(double x, double y, int size, Color color) {
		super(x, y, size, color);
		this.shapeImage = Toolkit.getDefaultToolkit().getImage("images//MainCircle.png").getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		
	}
}
