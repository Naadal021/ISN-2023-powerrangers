package model;

import javax.swing.*;

public class Gamelauncher {

    public static void main(String[] args) {
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("PacRangers");
    Interface pacrangers = new Interface();
    window.add(pacrangers);
    window.pack();
    window.setLocationRelativeTo(null);
    window.setVisible(true);

    }
}
