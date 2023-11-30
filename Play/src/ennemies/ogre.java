package ennemies;
import model.Interface;
import javax.swing.ImageIcon;

import model.Mov;

import java.awt.Graphics2D;
import java.awt.Rectangle;


public class ogre extends Mov {

    private ImageIcon[] runSprites = {
            new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f0.png"),
            new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f1.png"),
            new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f2.png"),
            new ImageIcon("Play/src/images/ennemies/ogre_run_anim_f3.png")
    };
    private int currentFrame = 0;
    private boolean isRunning = false;
    private int animationDelay = 5;

    Interface inter;

    public void setDefaultValues() {
        x = 700;
        y = 130;
        speed = 1;
        direction = " ";
    }

    public ogre (Interface inter) {
        this.inter = inter;
        solidArea = new Rectangle();
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        setDefaultValues();
    }

    public void update() {
        // Ajoutez ici toute logique de mise à jour de l'ennemi si nécessaire
    }

    public void draw(Graphics2D g2) {
        int frameIndex = (currentFrame / animationDelay) % runSprites.length;
        g2.drawImage(runSprites[frameIndex].getImage(), x, y, 2*inter.titleSize, 2*inter.titleSize, null);

        // Increment frame for the next iteration
        currentFrame++;
    }
}