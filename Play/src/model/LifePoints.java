package model;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class LifePoints{
    public Interface Inter;
    private int LP;
    private int max_LP;
    private List<ImageIcon> Current_lifepoints;
    private int x;  // You need to define the x and y variables
    private int y;
    public int c;

    public ImageIcon[] Hearts = {
        new ImageIcon(getClass().getResource("/images/wall/ui_heart_full.png")),
        new ImageIcon(getClass().getResource("/images/wall/ui_heart_empty.png")),
    };

    public LifePoints(Interface Inter) {
        this.Inter = Inter;
        
        this.c = 2;  // Set initial values for x and y
       
    }

    
    public void draw(Graphics2D g2) {
      
        if(c==0){
          // Ensure the index is within bounds
        g2.drawImage(Hearts[0].getImage(), 20, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[0].getImage(), 55, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[0].getImage(), 90, 40, Inter.titleSize, Inter.titleSize, null);
        }
        if(c==1){
          // Ensure the index is within bounds
        g2.drawImage(Hearts[0].getImage(), 20, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[0].getImage(), 55, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[1].getImage(), 90, 40, Inter.titleSize, Inter.titleSize, null);
        }
        if(c==2){
          // Ensure the index is within bounds
        g2.drawImage(Hearts[0].getImage(), 20, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[1].getImage(), 55, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[1].getImage(), 90, 40, Inter.titleSize, Inter.titleSize, null);
        }
        if(c>=3){
          // Ensure the index is within bounds
        g2.drawImage(Hearts[1].getImage(), 20, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[1].getImage(), 55, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[1].getImage(), 90, 40, Inter.titleSize, Inter.titleSize, null);
        }

        

                // Increment x for the next image
                
            
        }
    
}
