package org.felixan.tictacstones;

import javax.swing.*;

public class MainMenu {
    public JFrame mainMenuFrame = new JFrame("Tic-Tac-Stones");

    public MainMenu() {
        mainMenuFrame.setSize(500, 200);
        mainMenuFrame.setResizable(false);  // prevent maximizing and resizing
        mainMenuFrame.setLocationRelativeTo(null);  // this centers the window
        mainMenuFrame.setContentPane(new MainMenuForm().mainPanel);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setVisible(true);
    }


}
