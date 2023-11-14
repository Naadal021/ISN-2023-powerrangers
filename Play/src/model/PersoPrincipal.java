package model;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics2D;

import model.Interface;
import model.KeyHandler;
public class PersoPrincipal extends Mov {
	
	private ImageIcon[] runSprites = {
	        new ImageIcon("Play/src/images/sprites/dwarf_f_run_anim_f0.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_run_anim_f1.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_run_anim_f2.png"),
	        new ImageIcon("Play/src/images/spritesdwarf_f_run_anim_f3.png")
	    };

	    private ImageIcon[] idleSprites = {
	        new ImageIcon("Play/src/images/sprites/dwarf_f_idle_anim_f0.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_idle_anim_f1.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_idle_anim_f2.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_idle_anim_f3.png")
	    };

	    private int currentFrame = 0;
	    private boolean isRunning = false;
	    private int animationDelay = 5; 
	
	Interface inter;
	KeyHandler keyH;
	 
	
	public PersoPrincipal(Interface inter, KeyHandler keyH) {
		this.inter=inter;
		this.keyH=keyH;
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		x= 100;
		y=100; 
		speed=4; 
	}
	public void update() {
		
		if (keyH.isUpPressed() || keyH.isDownPressed() || keyH.isLeftPressed() || keyH.isRightPressed()) {
	        isRunning = true;
	    } else {
	        isRunning = false;
	    }
		
	    if (keyH.isUpPressed() && y - speed >= 0) {
	        y -= speed;
	    } else if (keyH.isDownPressed() && y + speed + inter.titleSize <= inter.screenHeight) {
	        y += speed;
	    } else if (keyH.isLeftPressed() && x - speed >= 0) {
	        x -= speed;
	    } else if (keyH.isRightPressed() && x + speed + inter.titleSize <= inter.screenWidth) {
	        x += speed;
	    }
	}


	public void draw(Graphics2D g2) {
	    ImageIcon[] sprites = isRunning ? runSprites : idleSprites;
	    int frameIndex = (currentFrame / animationDelay) % sprites.length;
	    g2.drawImage(sprites[frameIndex].getImage(), x, y, inter.titleSize, inter.titleSize, null);

	    // Increment frame for the next iteration
	    currentFrame++;
	}

}