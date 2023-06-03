package ChatFeatureWithGui;

import java.io.Serializable;

public class Shape implements Serializable {
    public int x;
    public int y;
    
    public void setX(int x) {
    	this.x = x;
    }
    
    public void setY(int y) {
    	this.y = y;
    }
    
    public int getX() {
    	return this.x;
    }
 
    public int getY() {
    	return this.y;
    }
    // Other methods
}