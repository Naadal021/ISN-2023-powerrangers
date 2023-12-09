package model;

import javax.swing.ImageIcon;

import ennemies.Demon;

import java.awt.Graphics2D;
import static java.lang.Thread.sleep;


import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

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
	private ImageIcon blue= new ImageIcon(getClass().getResource("/images/flags/flagblue.jpeg"));
    private ImageIcon green =  new ImageIcon(getClass().getResource("/images/flags/flaggreen.jpeg"));
    private  ImageIcon red=   new ImageIcon(getClass().getResource("/images/flags/flagred.jpeg"));
    private   ImageIcon yellow=  new ImageIcon(getClass().getResource("/images/flags/flagyellow.jpeg"));
	private static final List<String> flagList = new ArrayList<>(Arrays.asList("blue", "green", "red", "yellow"));

 // Use a List instead of an array

	// Initialize your flags list
	
	private int currentFrame = 0;
	private boolean isRunning = false;
	private int animationDelay = 5; 
	
	private String Currentdirection;
	public int tempscompteur=0;
	public int compteur ;
	public int damage_Demon;
	public int damage_Ogre;
	public int damage_Mage;
	public int damage_Lutin;

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
		solidArea.x=8;
		solidArea.y=10;
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		solidArea.width=14;
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
	    
	    int Demonindex = inter.cChecker.checkEntity(this,inter.Demon);
	    int mageindex = inter.cChecker.checkEntity(this,inter.Mage);
	    int ogreindex = inter.cChecker.checkEntity(this,inter.Ogre);
	    int lutinindex = inter.cChecker.checkEntity(this,inter.Lutin);
	    
	    
	    interactMage(mageindex);
		interactOgre(ogreindex);
		interactLutin(lutinindex);
		interactDemon(Demonindex);
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
	private List<String> colorsList = new ArrayList<>();
	private void checkCollisionWithBoules() {
		Iterator<Boule> iterator = inter.getBoules().iterator();
	
		while (iterator.hasNext()) {
			Boule boule = iterator.next();
			if (intersects(boule)) {
				int bouleIndex = inter.getBoules().indexOf(boule);
	
				if (bouleIndex >= 0 && bouleIndex < flagList.size()) {
					colorsList.add(flagList.get(bouleIndex));
	
					inter.incrementScore();
					iterator.remove();
					flagList.remove(bouleIndex);
	
					if (!colorsList.isEmpty()) {
						System.out.println(colorsList.get(colorsList.size() - 1));
					}
				} else {
					// Handle the case where the index is out of bounds
					System.err.println("Invalid boule index: " + bouleIndex);
				}
			}
		}
	}
	
	
	
	public String getcolor() {
		if (!colorsList.isEmpty()) {
			// Access the last color only if the list is not empty
			return colorsList.get(colorsList.size() - 1);
		}
	
		return "No color available";
	}
		

	
	
	private boolean intersects(Boule boule) {
	    // Créez un rectangle autour du personnage principal
	    Rectangle persoRectangle = new Rectangle(x, y, inter.titleSize, inter.titleSize);

	    // Créez un rectangle autour de la boule
	    Rectangle bouleRectangle = new Rectangle(boule.getX(), boule.getY(), Boule.getDiametre(), Boule.getDiametre());

	    // Vérifiez si les rectangles intersectent
	    return persoRectangle.intersects(bouleRectangle);
	}
	// public void interact( int index1, int index2, int index3) {
	// 	tempscompteur++;
	// 	if ( index1 != 999 || index2 != 999 || index3 != 999) {
	// 		String color = getcolor(); // Assuming you have a method to get the color
	
	// 		if (!colorsList.isEmpty()) {
	// 			switch (color) {
	// 				case "red":
	// 					inter.Demon.setDeathValues();
	// 					damage_Demon++;
	// 					colorsList.remove(colorsList.size() - 1);
	// 					break;
	// 				case "green":
	// 					inter.Ogre.setDeathValues();
	// 					damage_Ogre++;
	// 					colorsList.remove(colorsList.size() - 1);
	// 					break;
	// 				case "blue":
	// 					inter.Mage.setDeathValues();
	// 					damage_Mage++;
	// 					colorsList.remove(colorsList.size() - 1);
	// 					break;
	// 				case "yellow":
	// 					inter.Lutin.setDeathValues();
	// 					damage_Lutin++;
	// 					colorsList.remove(colorsList.size() - 1);
	// 					break;
	// 				default:
	// 				break;
	// 			}
				
	// 		}
	// 	compteur++;
	// 	setDefaultValues();
	// 	}
	// }
	public void interactDemon(int index ) {
		tempscompteur++;
		if (index != 999 ) {
			String color = getcolor(); // Assuming you have a method to get the color
	
			if (!colorsList.isEmpty()) {
					if(color=="red"){
					inter.Demon.setDeathValues();
					damage_Demon++;
					}
					else{
						compteur++;
				setDefaultValues();
					}
				}
			else{
				compteur++;
				setDefaultValues();
			}
			}
		
		}
		public void interactOgre(int index ) {
		tempscompteur++;
		if (index != 999 ) {
			String color = getcolor(); // Assuming you have a method to get the color
	
			if (!colorsList.isEmpty()) {
					if(color=="green"){
					inter.Ogre.setDeathValues();
					damage_Ogre++;
					}
					else{
						compteur++;
				setDefaultValues();
					}
				}
			else{
				compteur++;
				setDefaultValues();
			}
			}
		
		}
		public void interactLutin(int index ) {
		tempscompteur++;
		if (index != 999 ) {
			String color = getcolor(); // Assuming you have a method to get the color
	
			if (!colorsList.isEmpty()) {
					if(color=="yellow"){
					inter.Lutin.setDeathValues();
					damage_Lutin++;
					}
					else{
						compteur++;
				setDefaultValues();
					}
				}
			else{
				compteur++;
				setDefaultValues();
			}
			}
		
		}
		public void interactMage(int index ) {
		tempscompteur++;
		if (index != 999 ) {
			String color = getcolor(); // Assuming you have a method to get the color
	
			if (!colorsList.isEmpty()) {
					if(color=="blue"){
					inter.Mage.setDeathValues();
					damage_Mage++;
					}
					else{
						compteur++;
				setDefaultValues();
					}
				}
			else{
				compteur++;
				setDefaultValues();
			}
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

