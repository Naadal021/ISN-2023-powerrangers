package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLauncher {

    public static void main(String[] args) {
        // Créer un panneau personnalisé avec un champ de texte et un bouton "Play"
        JPanel panel = new JPanel(new BorderLayout());
        JTextField usernameField = new JTextField();
        JButton playButton = new JButton("Play");

        panel.add(new JLabel("Enter your username: "), BorderLayout.WEST);
        panel.add(usernameField, BorderLayout.CENTER);
        panel.add(playButton, BorderLayout.SOUTH);

        // Ajouter un ActionListener pour le bouton "Play"
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer le nom d'utilisateur
                String username = usernameField.getText();

                // Fermer la boîte de dialogue
                closeDialog(panel);

                // Lancer le jeu avec le nom d'utilisateur (vous pouvez le passer au besoin)
                launchGame(username);
            }
        });

        // Créer la boîte de dialogue avec le panneau personnalisé
        showDialog(panel, "Welcome");
    }

    private static void launchGame(String username) {
        // Vous pouvez utiliser le nom d'utilisateur comme nécessaire ici
        // Lancer le jeu avec le nom d'utilisateur
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("PacRangers - Welcome, " + username + "!");
        Interface pacrangers = new Interface();
        window.add(pacrangers);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        pacrangers.startGame();
    }

    private static void showDialog(JPanel panel, String title) {
        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = optionPane.createDialog(title);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);
    }

    private static void closeDialog(JPanel panel) {
        Container container = panel.getTopLevelAncestor();
        if (container instanceof JDialog) {
            JDialog dialog = (JDialog) container;
            dialog.dispose();
        }
    }
}
