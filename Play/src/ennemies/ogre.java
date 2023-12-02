package ennemies;
import model.Interface;
import javax.swing.ImageIcon;

import model.Mov;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public class ogre extends Mov {

    private ImageIcon[] runSprites = {
            new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f0.png"),
            new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f1.png"),
            new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f2.png"),
            new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f3.png")
    };
    private int currentFrame = 0;
    private boolean isRunning = false;
    private int animationDelay = 5;
    private int currentDirection; // added variable to store the current direction
    private int speedMultiplier = 2;
    private Random random = new Random(); // Initialize Random once

    private int directionChangeTimer = 0;
    private int directionChangeInterval = 60; // Change direction every 60 frames (adjust as needed)

    Interface inter;

    public void setDefaultValues() {
        x = 1000;
        y = 600;
        speed = 1;
        direction = " ";
        currentDirection = random.nextInt(4);
    }

    public ogre (Interface inter) {
        this.inter = inter;
        solidArea = new Rectangle();
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        setDefaultValues();
    }

    public void update() {
    	setaction();
    	collisionOn=false;
    	inter.cChecker.checkTile(this);
    	if(collisionOn==false){
	    	switch(direction) {
	    			case "up":
	    				y -= speed * speedMultiplier;
	    				 
	    			break;
	    			case "down":
	    				y += speed * speedMultiplier;
	    				 
	    			break;
	    			case "left":
	    				x -= speed * speedMultiplier;
	    				
	    			break;
	    			case "right":
	    				x += speed * speedMultiplier;
	    				
	    			break;
	    			}
    }
    }
    public void setaction() {
    	directionChangeTimer++;
        if (directionChangeTimer == directionChangeInterval) {
        	Random random = new Random();
            currentDirection = random.nextInt(4);
            switch (currentDirection) {
            case 0:
                if (y - speed * speedMultiplier >= 0) {
                    direction ="up";
                }
                break;
            case 1:
                if (y + speed * speedMultiplier + this.inter.titleSize <= this.inter.screenHeight) {
                    direction ="down";
                }
                break;
            case 2:
                if (x - speed * speedMultiplier >= 0) {
                    direction ="left";
                }
                break;
            case 3:
                if (x + speed * speedMultiplier + this.inter.titleSize <= this.inter.screenWidth) {
                    direction = "right";
                }
                break;
        }
            directionChangeTimer = 0;
        }
    }


    public void draw(Graphics2D g2) {
        int frameIndex = (currentFrame / animationDelay) % runSprites.length;
        g2.drawImage(runSprites[frameIndex].getImage(), x, y, 2*inter.titleSize, 2*inter.titleSize, null);

        // Increment frame for the next iteration
        currentFrame++;
    }
}