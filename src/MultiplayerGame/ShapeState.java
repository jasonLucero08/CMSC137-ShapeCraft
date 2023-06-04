package MultiplayerGame;

import java.io.Serializable;

public class ShapeState implements Serializable{
	protected double x;
	protected double y;
	
	public ShapeState(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
}