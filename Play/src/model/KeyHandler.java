package model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class KeyHandler extends KeyAdapter {
    private Set<Integer> keysPressed = new HashSet<>();

    private static final int KEY_W = KeyEvent.VK_W;
    private static final int KEY_S = KeyEvent.VK_S;
    private static final int KEY_A = KeyEvent.VK_A;
    private static final int KEY_D = KeyEvent.VK_D;

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyCode());
    }

    public boolean isUpPressed() {
        return keysPressed.contains(KEY_W);
    }

    public boolean isDownPressed() {
        return keysPressed.contains(KEY_S);
    }

    public boolean isLeftPressed() {
        return keysPressed.contains(KEY_A);
    }

    public boolean isRightPressed() {
        return keysPressed.contains(KEY_D);
    }
}
