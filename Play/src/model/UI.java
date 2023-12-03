package model;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
public class UI {
    Interface inter;
    Font arial_40;
    BufferedImage heart_full,heart_empty;
    public UI(Interface inter){
        this.inter=inter;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        SuperObject Heart = new LifePoints(inter);
        heart_full = Heart.image;
        heart_empty=Heart.image2;
    }
    
    public void draw(Graphics2D g2){
        
        g2.setFont(arial_40);
        g2.setColor(Color.BLUE);
        g2.drawImage(heart_full, null, 20, 20);
        

    }

}
