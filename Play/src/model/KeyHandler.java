package model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class KeyHandler extends KeyAdapter {
    private Set<Integer> keysPressed = new HashSet<>();

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyCode());
    }

    public boolean isUpPressed() {
        return keysPressed.contains(KeyEvent.VK_W);
    }

    public boolean isDownPressed() {
        return keysPressed.contains(KeyEvent.VK_S);
    }

    public boolean isLeftPressed() {
        return keysPressed.contains(KeyEvent.VK_A);
    }

    public boolean isRightPressed() {
        return keysPressed.contains(KeyEvent.VK_D);
    }
}
