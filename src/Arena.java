import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import acm.breadboards.OneButtonBreadboard;
import acm.graphics.GImage;
import acm.util.SoundClip;

public class Arena extends OneButtonBreadboard implements KeyListener{
    
    public static final int BREADBOARD_WIDTH = 900;
    public static final int BREADBOARD_HEIGHT = 600;
    
    private int ticker = 100;
    private int notkilled = 2;
    private int mogai = 100;
    private McCree McCree;
    private Explosion boom;
    private Random RDM = new Random();
    private GImage background;
    private GImage lose;
    private GImage ultra;
    private GImage win;
    private SoundClip backmusic;
    private SoundClip losemusic;
    private SoundClip ultramusic;
    private SoundClip winmusic;
    private ArrayList<Junkrat> JR = new ArrayList<Junkrat>();
    private ArrayList<Grenade> GND = new ArrayList<Grenade>();
    private ArrayList<Bullet> BLT = new ArrayList<Bullet>();
    private ArrayList<Ultra> ULT = new ArrayList<Ultra>();
    
    public void run() {
    	
    	background = new GImage("background.gif");
    	background.setSize(900,500);
    	this.add(background);
    	
    	backmusic = new SoundClip("backmusic.wav");
    	backmusic.setVolume(0.5);
    	backmusic.play();
    	
    	losemusic = new SoundClip("lose.wav");
    	losemusic.setVolume(0.8);
    	
    	winmusic = new SoundClip("winmusic.wav");
    	winmusic.setVolume(0.8);
    	
    	ultramusic = new SoundClip("ultra.wav");
    	ultramusic.setVolume(0.8);
    	
    	lose = new GImage("lose.gif");
    	lose.setSize(900,530);
    	
    	win = new GImage("win3.gif");
    	win.setSize(900,530);
    	
    	ultra = new GImage("ultra.gif");
    	
    	this.setSize(BREADBOARD_WIDTH,BREADBOARD_HEIGHT);
    	this.setBackground(Color.WHITE);
    	
    	McCree = new McCree(100, 420);
    	this.add(McCree);
    	McCree.setVelocity(0,0);
    	McCree.setAcceleration(0, 1);
    	
    	
    	JR.add(new Junkrat(850,400));
    	JR.get(0).setVelocity(-1, 0);
    	this.add(JR.get(0));
    	
    	this.getTimer().setDelay(10);
    	this.getTimer().start();  
    	
    	this.addKeyListeners (new KeyListener() {
	        
    		public void keyPressed(KeyEvent e) {
	
	            int key = e.getKeyCode();
	
	            if (key == KeyEvent.VK_LEFT) {
	                McCree.setLocation(McCree.getX()-20, McCree.getY());
	            }
	
	            if (key == KeyEvent.VK_RIGHT) {
	                McCree.setLocation(McCree.getX()+20, McCree.getY());
	            }
	
	            if (key == KeyEvent.VK_UP) {
	                McCree.jump();
	            } 
	            if (key == KeyEvent.VK_SPACE) {
	            	if (McCree.fireCD == 0) {
	            		BLT.add(new Bullet(McCree.getX()+20, McCree.getY()+15));
		            	BLT.get(BLT.size()-1).setVelocity(10, 0);
		            	BLT.get(BLT.size()-1).setAcceleration(-0.5, 0);
		            	Arena.this.add(BLT.get(BLT.size()-1));
		            	McCree.fireCD = 120;
	            	}
	            }
	            
	            if (key == KeyEvent.VK_Q) {		// ultra gif TODO
	            	if (McCree.ultCD == 0) {
		            	ultra.setLocation(McCree.getX(), McCree.getY()-80);
		            	//Arena.this.add(ultra);   
	            		ULT.add(new Ultra(McCree.getX()+20, McCree.getY()+15));
		            	ULT.get(ULT.size()-1).setVelocity(10, 0);
		            	ULT.get(ULT.size()-1).setAcceleration(-0.5, 0);
		            	//Arena.this.pause(1500);
		            	Arena.this.add(ULT.get(ULT.size()-1));
		            	ultramusic.play();
		            	Arena.this.getTimer().setDelay(100); // set delay back after ultra TODO
	            	}
	            }
	            
	            /*if (key == KeyEvent.VK_W) {		// ultra gif TODO
	            	if (McCree.HanzoCD == 0) {
		            	int k = GND.size();
		            	for (int i = 0; i < k; i++) {
		            		Arena.this.remove(GND.get(i));
		            		GND.remove(i);
		            	}
		            	McCree.HanzoCD = 200;
	            	}
	            } */
	        }

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
    	});
    	this.getGCanvas().requestFocus();
    }
    
    
    public void onTimerTick() {
    	this.getTextArea().setText("You have to kill " + notkilled +" more Junkrats" + "\n" +
    								"Your fire will cool down in " + McCree.fireCD/100 + "seconds!" + "\n" +
    								"It will be high noon in " + McCree.ultCD/100 + "seconds!");
    	
    	ticker--;
    	
    	if (McCree.fireCD > 0) {
    		McCree.fireCD--;
    	}
    	
    	if (McCree.ultCD > 0) {
    		McCree.ultCD--;
    	}
    	
    	if (ticker == 0) {
    		JR.add(new Junkrat(850,400));
    		JR.get(JR.size()-1).setVelocity(-1, 0);
        	this.add(JR.get(JR.size()-1));
        	ticker = 150 - RDM.nextInt(40); // random here TODO
    	}
    	
    	McCree.move();
    	
    	for (int i = 0; i < JR.size(); i++){
    		JR.get(i).move();
    	}
    	
    	for (int i = 0; i < GND.size(); i++){
    		GND.get(i).move();
    	}
    	
    	for (int i = 0; i < BLT.size(); i++){
    		BLT.get(i).move();
    	}
    	
    	for (int i = 0; i < ULT.size(); i++){
    		ULT.get(i).move();
    	}
    	
    	for (int i = 0; i < JR.size(); i++) {
    		if (McCree.getBounds().intersects(JR.get(i).getBounds())) {
    			McCree.kill();
    			this.remove(JR.get(i));
    			this.remove(McCree);
    		}
    	}
    	
    	for (int i = 0; i < GND.size(); i++) {
    		if (McCree.getBounds().intersects(GND.get(i).getBounds())) {
    			McCree.kill();
    			this.remove(GND.get(i));
    			this.remove(McCree);
    		}
    	}
    	
		for (int i = 0; i < BLT.size(); i++) {
	    	if (BLT.get(i).getX() > 900) {
				this.remove(BLT.get(i));
				BLT.remove(i);
			}
		}
		
		for (int i = 0; i < ULT.size(); i++) {
	    	if (ULT.get(i).getX() > 900) {
				this.remove(ULT.get(i));
				ULT.remove(i);
            	McCree.ultCD = 500;
				this.getTimer().setDelay(10);
				
			}
		}
		
    	for (int i = 0; i < GND.size(); i++) {
    		if (GND.get(i).hasExploded()){
    			this.remove(GND.get(i));
    			GND.remove(i);
    		}
    	}
    	
		for (int i = 0; i < BLT.size(); i++) {
			for (int j = 0; j < JR.size(); j++) {
				if (BLT.get(i).getBounds().intersects(JR.get(j).getBounds())) { //bug
					this.remove(BLT.get(i));
					this.remove(JR.get(j));
    				BLT.remove(i);
    				JR.remove(j);    
    				notkilled--;
				}
    		}
    	}	        			

		for (int i = 0; i < ULT.size(); i++) {
			for (int j = 0; j < JR.size(); j++) {
				if (ULT.get(i).getBounds().intersects(JR.get(j).getBounds())) {
					this.remove(JR.get(j));
    				JR.remove(j);     
    				notkilled--;
				}
    		}
    	}	
    	
    	
    	for (int i = 0; i < JR.size(); i++) {
    		JR.get(i).CD--;
    		if (JR.get(i).CD == 0) {
    			GND.add(new Grenade(JR.get(i).getX(),JR.get(i).getY()));
    			GND.get(GND.size()-1).setVelocity(-5 - 1.1 * Math.random(), -4 - 0.5*Math.random()); //Random here TODO
    			this.add(GND.get(GND.size()-1));
    			JR.get(i).CD = 100 + RDM.nextInt(20);
    		}
    	}
    	
    	if (McCree.hasDied()) {
             this.getTextArea().setText("You died!");
             boom = new Explosion(McCree.getX()-22, McCree.getY()-10);
             this.add(boom);
             this.pause(500);
             this.add(lose);
             //backmusic.stop();
             losemusic.play();
             this.getTimer().stop();
        }
    	
    	if (notkilled <= 0) {
    		this.getTextArea().setText("You win!");
    		this.add(win);
    		//backmusic.stop();
    		winmusic.play();
    		this.getTimer().stop();
    	}
    }
       
}