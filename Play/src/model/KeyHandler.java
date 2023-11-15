package model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class KeyHandler extends KeyAdapter {
    private Set<Integer> keysPressed = new HashSet<>();

    private static final int KEY_UP = KeyEvent.VK_UP;
    private static final int KEY_DOWN = KeyEvent.VK_DOWN;
    private static final int KEY_LEFT = KeyEvent.VK_LEFT;
    private static final int KEY_RIGHT = KeyEvent.VK_RIGHT;

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyCode());
    }

    public boolean isUpPressed() {
        return keysPressed.contains(KEY_UP);
    }

    public boolean isDownPressed() {
        return keysPressed.contains(KEY_DOWN);
    }

    public boolean isLeftPressed() {
        return keysPressed.contains(KEY_LEFT);
    }
 
    public boolean isRightPressed() {
        return keysPressed.contains(KEY_RIGHT);
    }
}
