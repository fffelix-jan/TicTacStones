package org.felixan.tictacstones;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            // Set Look and Feel to the System Look and Feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } finally {
            // create main menu frame
            MainMenu mm = new MainMenu();
        }
    }
}
