import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Grenade extends Objects {
    
    private GImage image;
    private Boolean Bounce = false;
    private Boolean exploded = false;
    private int timeleft = 50;
    
    public Grenade(double x, double y) {
        image = new GImage("grenade.png");
        this.add(image);
        this.setLocation(x,y);
    }
    
    public boolean hasExploded() {
    	return exploded;
    }
    
    public void move() {
    	this.setAcceleration(0,0.2);
        double vx = this.getXVelocity();
        double vy = this.getYVelocity();
        this.setLocation(this.getX() + vx, this.getY() + vy);
        this.setVelocity(vx + this.getXAcceleration(), vy + this.getYAcceleration());
        
        if ((this.getY() >= 450) && (!Bounce)) {
	        this.setVelocity(vx, -vy);
	        this.setAcceleration(0, 1.2);
	        Bounce = true;
        }
        else if (this.getY() <= 0) {
        	this.setVelocity(vx, -vy);
        }
        
        if (Bounce) {
        	timeleft--;
        }
        if (timeleft <= 0) {
        	image = new GImage("explosion.png");
            this.add(image);
        }
        if (timeleft <= -2) {
        	exploded = true;
        }
    }
    
}
