package model;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import static java.lang.Thread.sleep;


import java.awt.Rectangle;
import java.util.Iterator;

public class PersoPrincipal extends Mov {
	
	private ImageIcon[] runSpritesRight = {
	        new ImageIcon("Play/src/images/sprites/dwarf_f_run_anim_f0.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_run_anim_f1.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_run_anim_f2.png"),
	        new ImageIcon("Play/src/images/spritesdwarf_f_run_anim_f3.png")
	};

	private ImageIcon[] idleSpritesRight = {
	        new ImageIcon("Play/src/images/sprites/dwarf_f_idle_anim_f0.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_idle_anim_f1.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_idle_anim_f2.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_idle_anim_f3.png")
	};
	private ImageIcon[] runSpritesLeft = {
	        new ImageIcon("Play/src/images/sprites/dwarf_f_run_anim_f00.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_run_anim_f10.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_run_anim_f20.png"),
	        new ImageIcon("Play/src/images/spritesdwarf_f_run_anim_f30.png")
	};

	private ImageIcon[] idleSpritesLeft = {
	        new ImageIcon("Play/src/images/sprites/dwarf_f_idle_anim_f00.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_idle_anim_f10.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_idle_anim_f20.png"),
	        new ImageIcon("Play/src/images/sprites/dwarf_f_idle_anim_f30.png")
	};

	private int currentFrame = 0;
	private boolean isRunning = false;
	private int animationDelay = 5; 
	
	private String Currentdirection;
	public int tempscompteur=0;
	public int compteur ;
	Interface inter; 
	KeyHandler keyH;
	 	
	public void setDefaultValues() {
		x=100;
		y=200; 
		speed=4;
		direction =" ";
		Currentdirection="up";
		
	}
	
	public PersoPrincipal(Interface inter, KeyHandler keyH) {
		this.inter=inter;
		this.keyH=keyH;
		this.compteur=compteur;
		solidArea= new Rectangle();
		solidArea.x=0;
		solidArea.y=0;
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		solidArea.width=16;
		solidArea.height=16;
		setDefaultValues();
	}

	public void update() {
	    if (keyH.isUpPressed() && y - speed >= 0) {
	        direction="up";
	        Currentdirection="up";
	    } else if (keyH.isDownPressed() && y + speed + inter.titleSize <= inter.screenHeight) {
	        direction="down";
	        Currentdirection="down";
	    }

	    if (keyH.isLeftPressed() && x - speed >= 0) {
	        direction="left";
	        Currentdirection="left";
	    } else if (keyH.isRightPressed() && x + speed + inter.titleSize <= inter.screenWidth) {
	        direction="right";
	        Currentdirection="right";
	    }
	    collisionOn= false;
	    inter.cChecker.checkTile(this);
	    
	    int Demonindex = inter.cChecker.checkEntity(this,inter.Demon1);
	    int mageindex = inter.cChecker.checkEntity(this,inter.mage1);
	    int ogreindex = inter.cChecker.checkEntity(this,inter.ogre1);
	    int lutinindex = inter.cChecker.checkEntity(this,inter.lutin1);
	    
	    
	    interact(Demonindex,mageindex,ogreindex,lutinindex);
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
	public void interact(int index, int index1, int index2, int index3) {
        tempscompteur++;
        if (index != 999 || index1 != 999 || index2 != 999 || index3 != 999) {
            System.out.println(compteur);
            compteur++;
			setDefaultValues();
			
		

            
        }
    }
	

	public void draw(Graphics2D g2) {
	    ImageIcon[] sprites;
	    if (isRunning) {
            sprites = (Currentdirection.equals("left")) ? runSpritesLeft : runSpritesRight;
        } else {
            sprites = (Currentdirection.equals("left")) ? idleSpritesLeft : idleSpritesRight;
        }

	    int frameIndex = (currentFrame / animationDelay) % sprites.length;
	    g2.drawImage(sprites[frameIndex].getImage(), x, y, inter.titleSize, inter.titleSize, null);

	    // Increment frame for the next iteration
	    currentFrame++;
	}

}