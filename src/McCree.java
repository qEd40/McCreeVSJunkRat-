import java.awt.Color;
import java.awt.event.KeyEvent;

import acm.graphics.GCompound;
import acm.graphics.GImage;

public class McCree extends Objects {

    private boolean died;
    private GImage image;
    private int JumpMan;
    protected int fireCD = 0;
    protected int ultCD = 0;
    protected int HanzoCD = 500;
    
    public McCree(double x, double y) {
    	image = new GImage("McCree.gif");
    	this.setLocation(x, y);
        this.add(image);
    }
    
    public boolean hasDied() {
        return this.died;
    }
    
    public void kill() {
        this.died = true;
    }
    
    public void move() {
        if (! died) {
        	if (JumpMan > 0) {
                JumpMan--;
            }
        	else{
                this.setAcceleration(0, 1.2);
            }
        }
            
        double vx = this.getXVelocity();
        double vy = this.getYVelocity();
        this.setLocation(this.getX() + vx, this.getY() + vy);
        this.setVelocity(vx + this.getXAcceleration(), vy + this.getYAcceleration());
            
        if (this.getX() > Arena.BREADBOARD_WIDTH) {
        	this.setLocation(0,this.getY());
        }
            
        if (this.getX() < 0) {
        	this.setLocation(Arena.BREADBOARD_WIDTH,this.getY());
        }
            
        if (this.getY() >= 419)  {
        	this.setVelocity(0, 0);
        	this.setAcceleration(0, 0);
        }
    }
    
    
    public void jump() {
    	if (this.getY() >= 419) {
    		JumpMan = 10;
    		this.setVelocity(0, -10);
    	}
    }
       
}
