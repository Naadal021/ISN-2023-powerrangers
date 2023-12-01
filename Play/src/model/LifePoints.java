package model;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

public class LifePoints {
    private int maxLifePoints;
    private int currentLifePoints;
    public  List<ImageIcon> heartImages;

    public LifePoints(int maxLifePoints) {
        this.maxLifePoints = maxLifePoints;
        this.currentLifePoints = maxLifePoints;
        this.heartImages = initializeHeartImages();
    }

    // Get the current life points
    public int getCurrentLifePoints() {
        return currentLifePoints;
    }

    // Decrease life points by a specified amount
    public void decreaseLifePoints(int amount) {
        if (amount > 0) {
            currentLifePoints -= amount;

            // Ensure that life points don't go below 0
            if (currentLifePoints < 0) {
                currentLifePoints = 0;
            }

            // Update graphical representation
            updateHeartImages();
        }
    }

    // Example method: Initialize heart images
    public List<ImageIcon> initializeHeartImages() {
        List<ImageIcon> hearts = new ArrayList<>();
        hearts.add(new ImageIcon("images/sprites/ui_heart_full.png"));
        hearts.add(new ImageIcon("images/sprites/ui_heart_half.png"));
        hearts.add(new ImageIcon("images/sprites/ui_heart_empty.png"));
        return hearts;
    }

    // Example method: Update heart images based on current life points
    private void updateHeartImages() {
        // Determine how many full hearts, half hearts, and empty hearts to display
        int fullHearts = currentLifePoints / 2;
        int halfHeart = currentLifePoints % 2;
        int emptyHearts = (maxLifePoints - currentLifePoints) / 2;

        // Update the graphical representation (e.g., by displaying images in a UI)
        // You might use a UI component to display the heart images here
        // For simplicity, this is just a console output
        System.out.println("Full Hearts: " + fullHearts);
        System.out.println("Half Heart: " + halfHeart);
        System.out.println("Empty Hearts: " + emptyHearts);
    }

    // Example method: Check if the player is alive
    public boolean isAlive() {
        return currentLifePoints > 0;
    }
}
