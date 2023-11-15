package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
public class Boule {
	
	private int x, y;
	private static int diametre = 20; // Ajustez la taille de la boule selon vos besoins

	public Boule(int x, int y) {
	    this.x = x;
	    this.y = y;
	}

	public static int getDiametre() {
        return diametre;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED); // Couleur de la boule
        g2.fillOval(x, y, diametre, diametre);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

	    
}


