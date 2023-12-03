package model;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class LifePoints extends SuperObject {
    public Interface Inter;
    private int LP;
    private int max_LP;
    private List<ImageIcon> Current_lifepoints;
    private int x;  // You need to define the x and y variables
    private int y;

    public ImageIcon[] Hearts = {
        new ImageIcon(getClass().getResource("/images/wall/ui_heart_full.png")),
        new ImageIcon(getClass().getResource("/images/wall/ui_heart_empty.png")),
    };

    public LifePoints(Interface Inter) {
        this.Inter = Inter;
        
        this.x = 0;  // Set initial values for x and y
        this.y = 0;
    }

    
    public void draw(Graphics2D g2) {
      

          // Ensure the index is within bounds
        g2.drawImage(Hearts[0].getImage(), 20, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[0].getImage(), 55, 40, Inter.titleSize, Inter.titleSize, null);
        g2.drawImage(Hearts[0].getImage(), 90, 40, Inter.titleSize, Inter.titleSize, null);

        

                // Increment x for the next image
                
            
        }
    
}
