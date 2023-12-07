package model;

import javax.swing.*;

public class GameLauncher {

    public static void main(String[] args) {
    	Interface pacrangers = new Interface();
    	JFrame window = new JFrame();
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setResizable(false);
    	window.setTitle("PacRangers - User name: " + Interface.getuserName() + "!");
    	
    	window.add(pacrangers);
    	window.pack();
    	window.setLocationRelativeTo(null);
    	window.setVisible(true);
    	pacrangers.startGame();

    }
}
