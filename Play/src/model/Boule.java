package model;

import java.awt.Graphics2D;


import javax.swing.ImageIcon;
import java.net.URL;

public class Boule {
    private int x, y;
    private static int diametre = 30; // Ajustez la taille de la boule selon vos besoins
     private ImageIcon blue= new ImageIcon(getClass().getResource("/images/flags/flagblue.jpeg"));
    private ImageIcon green =  new ImageIcon(getClass().getResource("/images/flags/flaggreen.jpeg"));
    private  ImageIcon red=   new ImageIcon(getClass().getResource("/images/flags/flagred.jpeg"));
    private   ImageIcon yellow=  new ImageIcon(getClass().getResource("/images/flags/flagyellow.jpeg"));
    private ImageIcon[] flagIcons = {blue,green,red,yellow};
            
    public String getCurrentIconPath() {
        URL imageUrl = getClass().getResource(currentIcon.toString());
        return imageUrl != null ? imageUrl.getPath() : "";
    }
    private ImageIcon currentIcon; // Icône actuelle de la boule
    private static int nextFlagIndex = 0;
    public Boule(int x, int y) {
        this.x = x;
        this.y = y;
        this.currentIcon = flagIcons[nextFlagIndex];
        
        nextFlagIndex = (nextFlagIndex + 1) % flagIcons.length;
    }
   
    public static int getDiametre() {
        return diametre;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(currentIcon.getImage(), x, y, diametre, diametre, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
