package org.felixan.tictacstones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuForm {
    public JPanel mainPanel;
    private JButton onePlayerButton;
    private JButton twoPlayerButton;
    private JButton instructionsButton;

    public MainMenuForm() {


        instructionsButton.addActionListener(new ActionListener() {
            /**
             * Show the instructions when the "How To Play" button is clicked.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainMenu.mainMenuFrame, "HOW TO PLAY TIC-TAC-STONES:\n\n1. Each player starts with 4 pieces arranged on a board that is 4 squares by 4 squares.\n2. A turn consists of a one-space move, horizontally or vertically. The piece cannot overlap another piece.\n3. The game is won when 1 player has managed to line up 3 of his or her pieces horizontally, vertically, or diagonally.\n\nHave fun!", "How To Play", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        twoPlayerButton.addActionListener(new ActionListener() {
            /**
             * 2 Player button action.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.ready = true;
                MainMenu.hide();
            }
        });
    }
}
