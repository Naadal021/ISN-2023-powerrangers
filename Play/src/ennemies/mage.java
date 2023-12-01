package ennemies;
import model.Interface;
import javax.swing.ImageIcon;

import model.Mov;
import model.CollisionChecker;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public class mage extends Mov {

    private ImageIcon[] runSprites = {
            new ImageIcon("Play/src/images/ennemies/knight_f_run_anim_f0.png"),
            new ImageIcon("Play/src/images/ennemies/knight_f_run_anim_f1.png"),
            new ImageIcon("Play/src/images/ennemies/knight_f_run_anim_f2.png"),
         
    };
    private int currentFrame = 0;
    private boolean isRunning = false;
    private int animationDelay = 5;
    private int currentDirection; // added variable to store the current direction
    private int speedMultiplier = 2;
    private Random random = new Random(); // Initialize Random once

    private int directionChangeTimer = 0;
    private int directionChangeInterval = 60; // Change direction every 60 frames (adjust as needed)


    Interface inter;

    public void setDefaultValues() {
        x = 600;
        y = 100;
        speed = 1;
        direction = " ";
        currentDirection =random.nextInt(4);
    }

    public mage (Interface inter) {
        this.inter = inter;
        solidArea = new Rectangle();
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        setDefaultValues();
    }

    public void update() {
        directionChangeTimer++;
        if (directionChangeTimer >= directionChangeInterval) {
            currentDirection = random.nextInt(4);
            directionChangeTimer = 0; // Reset the timer
        }
        switch (currentDirection) {
            case 0:
                if (y - speed * speedMultiplier >= 0) {
                    y -= speed * speedMultiplier;
                }
                break;
            case 1:
                if (y + speed * speedMultiplier + this.inter.titleSize <= this.inter.screenHeight) {
                    y += speed * speedMultiplier;
                }
                break;
            case 2:
                if (x - speed * speedMultiplier >= 0) {
                    x -= speed * speedMultiplier;
                }
                break;
            case 3:
                if (x + speed * speedMultiplier + this.inter.titleSize <= this.inter.screenWidth) {
                    x += speed * speedMultiplier;
                }
                break;
        }
    }
    public void draw(Graphics2D g2) {
        int frameIndex = (currentFrame / animationDelay) % runSprites.length;
        g2.drawImage(runSprites[frameIndex].getImage(), x, y, inter.titleSize, inter.titleSize, null);

        // Increment frame for the next iteration
        currentFrame++;
    }
}