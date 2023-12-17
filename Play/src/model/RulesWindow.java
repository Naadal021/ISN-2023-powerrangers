package model;

import javax.swing.*;
import java.awt.*;

public class RulesWindow extends JFrame {

    public RulesWindow(ImageIcon rulesImage) {
        super("Rules");

        JLabel label = new JLabel(rulesImage);
        getContentPane().add(label);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400); // ajustez la taille selon vos besoins
        setLocationRelativeTo(null); // centrez la fenêtre sur l'écran
        setVisible(true);
    }
}
