import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Explosion extends Objects{
	private GImage image;
    
    public Explosion(double x, double y) {
        image = new GImage("explosion.png");
        this.add(image);
        this.setLocation(x,y);
    }
	@Override
	public void move() {
	}
    
}
