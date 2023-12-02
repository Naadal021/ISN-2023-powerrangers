package model;

import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.ImageIcon;

public class Boule {
    private int x, y;
    private static int diametre = 20; // Ajustez la taille de la boule selon vos besoins
    private ImageIcon[] flagIcons = {
            new ImageIcon(getClass().getResource("/images/flags/flagblue.jpeg")),
            new ImageIcon(getClass().getResource("/images/flags/flaggreen.jpeg")),
            new ImageIcon(getClass().getResource("/images/flags/flagred.jpeg")),
            new ImageIcon(getClass().getResource("/images/flags/flagyellow.jpeg"))
    };
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
