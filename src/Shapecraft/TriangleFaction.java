package Shapecraft;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class TriangleFaction extends Shapes{
	public TriangleFaction(int x, int y, String size) {
    	super(x, y, size);
//        this.image = this.image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    	Image imageLocation = new ImageIcon("images//TriangleUnit.png").getImage();
    	this.image = imageLocation.getScaledInstance(95, 95, Image.SCALE_SMOOTH);
    }
}