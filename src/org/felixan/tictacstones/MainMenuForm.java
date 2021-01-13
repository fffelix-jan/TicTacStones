package org.felixan.tictacstones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuForm extends JFrame {
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
                JFrame messageFrame = new JFrame();
                JOptionPane.showMessageDialog(messageFrame, "HOW TO PLAY TIC-TAC-STONES\n\n1. Each player starts with 4 pieces arranged in the pictured locations on a board that is 4 squares by 4 squares.\n2. A turn consists of a one-space move, horizontally or vertically.\n3. The game is won when 1 player has managed to line up 3 of his or her pieces horizontally, vertically, or diagonally.\n\nHave fun!", "How To Play", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        twoPlayerButton.addActionListener(new ActionListener() {
            /**
             * 2 Player button action.
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
