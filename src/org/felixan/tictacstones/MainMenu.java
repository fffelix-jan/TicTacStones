package org.felixan.tictacstones;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class MainMenu {
    private static JFrame mainMenuFrame = new JFrame("Tic-Tac-Stones");

    /**
     * Shows the Main Menu.
     */
    public static void show() {
        mainMenuFrame.setSize(550, 250);
        mainMenuFrame.setResizable(false);  // prevent maximizing and resizing
        mainMenuFrame.setLocationRelativeTo(null);  // this centers the window
        mainMenuFrame.setContentPane(new MainMenuForm().mainPanel);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setVisible(true);
    }

    /**
     * Hides the Main Menu.
     */
    public static void hide() {
        mainMenuFrame.setVisible(false);
    }

    /**
     * Plays the theme song.
     */
    public static void playThemeSong() {
        try {
            URL themeSongPath = MainMenu.class.getClassLoader().getResource("The_Decades_Intro.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(themeSongPath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
