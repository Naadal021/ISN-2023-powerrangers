package model;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;

import java.util.List;

public class LifePoints{
    public Interface Inter;
    KeyHandler keyH=new KeyHandler();
    int c ;

    PersoPrincipal pp = new PersoPrincipal(this.Inter, keyH);
    
    private List<ImageIcon> Current_lifepoints;
    
    
    public ImageIcon[] Hearts = {
        new ImageIcon(getClass().getResource("/images/wall/ui_heart_full.png")),
        new ImageIcon(getClass().getResource("/images/wall/ui_heart_empty.png")),
    };

    public LifePoints(Interface Inter) {
        this.Inter = Inter;
        
        
         // Set initial values for x and y
       
    }
    public void updateCompteur() {
      c = pp.compteur;
      
  }
    
  
    
    public void draw(Graphics2D g2) {
   
       if(pp.compteur==0){
          // Ensure the index is within bounds
        g2.drawImage(Hearts[0].getImage(), 20, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[0].getImage(), 55, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[0].getImage(), 90, 40, Inter.titleSize, Inter.titleSize, null);
        }
        if(pp.compteur==1){
          // Ensure the index is within bounds
        g2.drawImage(Hearts[0].getImage(), 20, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[0].getImage(), 55, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[1].getImage(), 90, 40, Inter.titleSize, Inter.titleSize, null);
        }
        if(pp.compteur==2){
          // Ensure the index is within bounds
        g2.drawImage(Hearts[0].getImage(), 20, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[1].getImage(), 55, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[1].getImage(), 90, 40, Inter.titleSize, Inter.titleSize, null);
        }
        if(pp.compteur>=3){
          // Ensure the index is within bounds
        g2.drawImage(Hearts[1].getImage(), 20, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[1].getImage(), 55, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[1].getImage(), 90, 40, Inter.titleSize, Inter.titleSize, null);
        }

        

            
        }
    
}
