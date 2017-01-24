import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Junkrat extends Objects{
	private GImage image;
    private String filename;
    protected int CD = 80;
    
    public Junkrat(double x, double y) {
    	filename="Junkrat.gif";
        image = new GImage(filename);
        image.setSize(60,80);
        this.add(image);
        this.setLocation(x,y);    
    }
	@Override
	public void move() {
        double vx = this.getXVelocity();
        double vy = this.getYVelocity();
        this.setLocation(this.getX() + vx, this.getY() + vy);
	}
    
}
