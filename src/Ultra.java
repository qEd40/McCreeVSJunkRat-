import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Ultra extends Objects {
    
    private GImage image;
    private Boolean exploded = false;
    
    public Ultra(double x, double y) {
        image = new GImage("ultra.png");
        image.setSize(25,15);
        this.add(image);
        this.setLocation(x,y);
    }
    
    public boolean hasExploded() {
    	return exploded;
    }
    
    public void move() {
    	this.setAcceleration(-0.5,0);
        double vx = this.getXVelocity();
        double vy = this.getYVelocity();
        this.setLocation(this.getX() + vx, this.getY() + vy);
        
        if (this.getX() >= 900) {
        	exploded = true;
        }
        
    }
    
}
