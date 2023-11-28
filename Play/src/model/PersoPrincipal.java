package model;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics2D;

import model.Interface;
import model.KeyHandler;

import java.awt.Rectangle;
import java.util.Iterator;

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
	 	
	public void setDefaultValues() {
		x=100;
		y=200; 
		speed=2;
		direction =" ";
	}
	
	public PersoPrincipal(Interface inter, KeyHandler keyH) {
		this.inter=inter;
		this.keyH=keyH;
		solidArea= new Rectangle();
		solidArea.x=0;
		solidArea.y=0;
		solidArea.width=16;
		solidArea.height=16;
		setDefaultValues();
	}

	public void update() {
	    if (keyH.isUpPressed() && y - speed >= 0) {
	        direction="up";
	    } else if (keyH.isDownPressed() && y + speed + inter.titleSize <= inter.screenHeight) {
	        direction="down";
	    }

	    if (keyH.isLeftPressed() && x - speed >= 0) {
	        direction="left";
	    } else if (keyH.isRightPressed() && x + speed + inter.titleSize <= inter.screenWidth) {
	        direction="right";
	    }
	    collisionOn= false;
	    inter.cChecker.checkTile(this);
	    if(collisionOn==false){
	    	switch(direction) {
	    			case "up":
	    				 y -= speed;
	    				 direction=" ";
	    			break;
	    			case "down":
	    				 y += speed;
	    				 direction=" ";
	    			break;
	    			case "left":
	    				x -= speed;
	    				direction=" ";
	    			break;
	    			case "right":
	    				x += speed;
	    				direction=" ";
	    			break;
	    			}
	    	
	    }

	    checkCollisionWithBoules();
	}
 
	
	private void checkCollisionWithBoules() {
	    Iterator<Boule> iterator = inter.getBoules().iterator();
	    while (iterator.hasNext()) {
	        Boule boule = iterator.next();
	        if (intersects(boule)) {
	            inter.incrementScore(); // Incrémente le score
	            iterator.remove(); // Supprime la boule touchée
	        } 
	    } 
	}
	
	private boolean intersects(Boule boule) {
	    // Créez un rectangle autour du personnage principal
	    Rectangle persoRectangle = new Rectangle(x, y, inter.titleSize, inter.titleSize);

	    // Créez un rectangle autour de la boule
	    Rectangle bouleRectangle = new Rectangle(boule.getX(), boule.getY(), Boule.getDiametre(), Boule.getDiametre());

	    // Vérifiez si les rectangles intersectent
	    return persoRectangle.intersects(bouleRectangle);
	}

	public void draw(Graphics2D g2) {
	    ImageIcon[] sprites = isRunning ? idleSprites :runSprites ;
	    int frameIndex = (currentFrame / animationDelay) % sprites.length;
	    g2.drawImage(sprites[frameIndex].getImage(), x, y, inter.titleSize, inter.titleSize, null);

	    // Increment frame for the next iteration
	    currentFrame++;
	}

}