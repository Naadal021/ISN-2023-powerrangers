package model;

import javax.swing.ImageIcon;



import java.awt.Graphics2D;



import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


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

	private  List<String> flagList = new ArrayList<>(Arrays.asList("blue", "green", "red", "yellow"));
	private  List<String> flagList_temp = new ArrayList<>(Arrays.asList("blue", "green", "red", "yellow"));

 // Use a List instead of an array

	// Initialize your flags list
	
	private int currentFrame = 0;
	private boolean isRunning = false;
	private int animationDelay = 5; 
	public String coloString;
	private String Currentdirection;
	public int tempscompteur=0;
	public int damage_points ;
	public int damage_Demon=0;
	public int damage_Ogre;
	public int damage_Mage=0;
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
	    
	    blueflag();
	    interactMage(mageindex);
	    greenflag();
		interactOgre(ogreindex);
		yellowflag();
		interactLutin(lutinindex);
		redflag();
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
	public List<String> colorsList = new ArrayList<>();
	private void checkCollisionWithBoules() {
		Iterator<Boule> iterator = inter.getBoules().iterator();
	
		while (iterator.hasNext()) {
			Boule boule = iterator.next();
			if (intersects(boule)) {
				int bouleIndex = inter.getBoules().indexOf(boule);
	
				if (bouleIndex >= 0 && bouleIndex < flagList.size()) {
					colorsList.add(flagList.get(bouleIndex));
	
				
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
	
	
	public void transferAndEmpty() {
		colorsList.clear();
		flagList.clear();
        flagList.addAll(flagList_temp);  // Transfer the content of bouleTemp to boules
              // Empty bouleTemp
    }
	public String getcolor() {
		if (!colorsList.isEmpty()) {
			coloString=colorsList.get(colorsList.size() - 1);
			return colorsList.get(colorsList.size() - 1);
		}
		
		return "No color available";
		
	}
	public void redflag() {
		
		if (!colorsList.isEmpty()) {
			if(colorsList.contains("red")) {
			
				inter.flagred-=(double)1/60;}
			}
}
	public void blueflag() {
		if (!colorsList.isEmpty()) {
			if(colorsList.contains("blue")) {
				
				inter.flagblue-=(double)1/60;}
			}
}
	public void greenflag() {
		
		if (!colorsList.isEmpty()) {
			if(colorsList.contains("green")) {
			
				inter.flaggreen-=(double)1/60;}
			}
}
public void yellowflag() {

    if (!colorsList.isEmpty()) {
        if (colorsList.contains("yellow")) {
            
            inter.flagyellow -= (double) 1 / 60;
        }
    }
}

	
		

	
	
	private boolean intersects(Boule boule) {
	   	Rectangle persoRectangle = new Rectangle(x, y, inter.titleSize, inter.titleSize);
		Rectangle bouleRectangle = new Rectangle(boule.getX(), boule.getY(), Boule.getDiametre(), Boule.getDiametre());
		return persoRectangle.intersects(bouleRectangle);
	}
	
	public void interactDemon(int index ) {
		tempscompteur++;
		if (index != 999  ) {
			String color = getcolor();// Assuming you have a method to get the color
	
			if (!colorsList.isEmpty()) {
			
				if(color=="red" && inter.flagred>0 ){
					inter.Demon.setDeathValues();
					damage_Demon++;
					inter.flagred-=1;
					}
					else{
						damage_points++;
					setDefaultValues();
					}
				}
			else{
				damage_points++;
				setDefaultValues();
			}
			}
		
		}
		public void interactOgre(int index ) {
		tempscompteur++;
		if (index != 999 ||inter.Ogre.greendamage>=1) {
			String color = getcolor(); // Assuming you have a method to get the color
	
			if (!colorsList.isEmpty()) {
				    
					if(color=="green"&& inter.flaggreen>0){
					inter.Ogre.setDeathValues();
					inter.flaggreen-=1;
					damage_Ogre++;
					}
					else{
						damage_points++;
				setDefaultValues();
					}
				}
			else{
				damage_points++;
				setDefaultValues();
			}
			}
		
		}
		public void interactLutin(int index ) {
		tempscompteur++;
		if (index != 999 ||inter.Lutin.yellowdamage>=1 ) {
			String color = getcolor(); // Assuming you have a method to get the color
	
			if (!colorsList.isEmpty()) {
				    
					if(color=="yellow"&& inter.flagyellow>0){
					inter.Lutin.setDeathValues();
					inter.flagyellow-=1;
					damage_Lutin++;
					}
					else{
						damage_points++;
				setDefaultValues();
					}
				}
			else{
				damage_points++;
				setDefaultValues();
			}
			}
		
		}
		public void interactMage(int index ) {
		tempscompteur++;
		if (index != 999 ||inter.Mage.bluedamage>=1) {
			String color = getcolor(); // Assuming you have a method to get the color
	
			if (!colorsList.isEmpty()) {
				    
					if(color=="blue"&& inter.flagblue>0){
					inter.Mage.setDeathValues();
					inter.flagblue-=1;
					damage_Mage++;
					}
					else{
						damage_points++;
				setDefaultValues();
					}
				}
			else{
				damage_points++;
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
	public int getLifePoints() {

		return damage_points;

		}



		public List<String> getColorsList() {

		return colorsList;

		}



		public Object getDamageDemon() {

		return damage_Demon;

		}



		public List<String> getFlagList() {

		return flagList;

		}
}
