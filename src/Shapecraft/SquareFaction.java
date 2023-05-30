package Shapecraft;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class SquareFaction extends Shapes{
	public SquareFaction(int x, int y, String size) {
    	super(x, y, size);
//        this.image = this.image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    	Image imageLocation = new ImageIcon("images//SquareUnit.png").getImage();
    	this.image = imageLocation.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
    }
}
