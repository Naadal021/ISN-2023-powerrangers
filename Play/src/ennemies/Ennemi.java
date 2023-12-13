package ennemies;
import model.Interface;
import java.util.Random;

import javax.swing.ImageIcon;

import model.Interface;



import model.Mov;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ennemi extends Mov {
     private ImageIcon[] runSpritesRightDemon = {
            new ImageIcon("Play/src/images/ennemies/big_demon_run_anim_f0.png"),
            new ImageIcon("Play/src/images/ennemies/big_demon_run_anim_f1.png"),
            new ImageIcon("Play/src/images/ennemies/big_demon_run_anim_f2.png"),
            new ImageIcon("Play/src/images/ennemies/big_demon_run_anim_f3.png")
    };
    private ImageIcon[] runSpritesLefDemon = {
            new ImageIcon("Play/src/images/ennemies/big_demon_run_anim_f00.png"),
            new ImageIcon("Play/src/images/ennemies/big_demon_run_anim_f10.png"),
            new ImageIcon("Play/src/images/ennemies/big_demon_run_anim_f20.png"),
            new ImageIcon("Play/src/images/ennemies/big_demon_run_anim_f30.png")
    };
    private ImageIcon[] runSpritesRightlutin = {
        new ImageIcon("Play/src/images/ennemies/elf_m_run_anim_f0.png"),
        new ImageIcon("Play/src/images/ennemies/elf_m_run_anim_f1.png"),
        new ImageIcon("Play/src/images/ennemies/elf_m_run_anim_f2.png"),
        new ImageIcon("Play/src/images/ennemies/elf_m_run_anim_f3.png")
};
private ImageIcon[] runSpritesLeftlutin = {
        new ImageIcon("Play/src/images/ennemies/elf_m_run_anim_f00.png"),
        new ImageIcon("Play/src/images/ennemies/elf_m_run_anim_f10.png"),
        new ImageIcon("Play/src/images/ennemies/elf_m_run_anim_f20.png"),
        new ImageIcon("Play/src/images/ennemies/elf_m_run_anim_f30.png")
};
private ImageIcon[] runSpritesRightmage = {
    new ImageIcon("Play/src/images/ennemies/knight_f_run_anim_f0.png"),
    new ImageIcon("Play/src/images/ennemies/knight_f_run_anim_f1.png"),
    new ImageIcon("Play/src/images/ennemies/knight_f_run_anim_f2.png"),
 
};
private ImageIcon[] runSpritesLeftmage = {
    new ImageIcon("Play/src/images/ennemies/knight_f_run_anim_f00.png"),
    new ImageIcon("Play/src/images/ennemies/knight_f_run_anim_f10.png"),
    new ImageIcon("Play/src/images/ennemies/knight_f_run_anim_f20.png"),
 
};
private ImageIcon[] runSpritesRightOgre = {
    new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f0.png"),
    new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f1.png"),
    new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f2.png"),
    new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f3.png")
};
private ImageIcon[] runSpritesLeftOgre = {
    new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f00.png"),
    new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f10.png"),
    new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f20.png"),
    new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f30.png")
};
    private ImageIcon[] runSpritesLeft;
    private ImageIcon[] runSpritesRight;


    private int currentFrame = 0;
    private boolean isRunning = false;
    private int animationDelay = 5;
    private int currentDirection; // added variable to store the current direction
    private int speedMultiplier = 1;
    private Random random = new Random(); // Initialize Random once
    private String nom;
    private int directionChangeTimer = 0;
    private int directionChangeInterval = 20; // Change direction every 60 frames (adjust as needed)

    
    Interface inter;

    public void setDefaultValues() {
        
        
        direction = " ";
        currentDirection =random.nextInt(4);
    }
    private int speed;
   

    public Ennemi(Interface inter,String nom , int x,int y,int speed, int solidareax,int solidareay,int width ,int height,int icone) {
        this.inter = inter;
        this.nom=nom;
        this.icone=icone;
        this.x=x;
        this.y=y;
        solidArea = new Rectangle();
        solidArea.x = solidareax;
        solidArea.y = solidareay;
        solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
        solidArea.width = width;
        solidArea.height = height;
        this.speed=speed;
        
        setDefaultValues();
    }
    public void getSprites(String nom ){
        if ("Demon".equals(nom)) { // Use double quotes for string comparison
            runSpritesLeft = runSpritesLefDemon;
            runSpritesRight = runSpritesRightDemon;
        } else if ("Lutin".equals(nom)) { // Use double quotes for string comparison
            runSpritesLeft = runSpritesLeftlutin;
            runSpritesRight = runSpritesRightlutin;
        }
        else if ("Ogre".equals(nom)) { // Use double quotes for string comparison
            runSpritesLeft = runSpritesLeftOgre;
            runSpritesRight = runSpritesRightOgre;
        }
        else if ("Mage".equals(nom)) { // Use double quotes for string comparison
            runSpritesLeft = runSpritesLeftmage;
            runSpritesRight = runSpritesRightmage;
        }
    }
    public void setDeathValues(){
        x=0;
        y=0;
        icone=0;
    }
    public void update() {
    	setaction();
    	collisionOn=false;
    	inter.cChecker.checkTile(this);
    	int Demonindex1 = inter.cChecker.checkMonster(this,inter.persoPrincipal);
    	interacte(Demonindex1);
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
  public void interacte(int index) {
	  if (index==0) {
		  inter.persoPrincipal.setDefaultValues();
		  inter.persoPrincipal.compteur++;
	  }
  }
    public String getNom() {
        return nom;
    }

    public int  icone(String nom){
        if("Lutin".equals(nom)||"Mage".equals(nom)){
            return   inter.titleSize+5; 
            
        }
        else{
            return   inter.titleSize+25; 

        }
        
    }
    public void draw(Graphics2D g2) {
        getSprites(nom);
        getNom();
        icone(nom);
        
        
        
    	ImageIcon[] sprites = null; 
	    if (isRunning) {
            sprites = (direction.equals("left")) ? runSpritesLeft : runSpritesRight;
        }else {
            // Initialize sprites with the appropriate set of images for the current direction
            sprites = (currentDirection == 2) ? runSpritesLeft : runSpritesRight;
        }

        int frameIndex = (currentFrame / animationDelay) % sprites.length;
       
        g2.drawImage(sprites[frameIndex].getImage(), x, y, icone,  icone, null);

        // Increment frame for the next iteration
        currentFrame++;
    }
}
    

