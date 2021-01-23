package org.felixan.tictacstones;

import javax.swing.*;

public class ClickStartReminder {
    private static final JFrame reminderFrame = new JFrame("Click \"Start\" to Continue");
    public static boolean firstTime = true;

    /**
     * Shows the click start reminder window.
     */
    public static void show() {
        if (firstTime) {
            reminderFrame.setSize(400, 250);
            reminderFrame.setResizable(false);  // prevent maximizing and resizing
            reminderFrame.setLocationRelativeTo(null);  // this centers the window
            reminderFrame.setContentPane(new ClickStartReminderForm().reminderPanel);
            reminderFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            reminderFrame.setVisible(true);
        }
    }

    /**
     * Hides the click start reminder window.
     */
    public static void hide() {
        reminderFrame.setVisible(false);
    }
}
