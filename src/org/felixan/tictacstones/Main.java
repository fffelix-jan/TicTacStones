package org.felixan.tictacstones;

import becker.robots.Direction;
import becker.robots.RobotException;
import becker.robots.icons.CircleIcon;

import javax.swing.*;
import java.awt.Color;

public class Main {
    public static boolean ready = false;
    private static GamePiece[] player1Pieces;
    private static GamePiece[] player2Pieces;
    private static int[][] player1Pos;
    private static int[][] player2Pos;

    // Main function
    public static void main(String[] args) throws Exception {
        // Show the Main Menu (title screen)
        MainMenu.show();

        // Play the theme song
        MainMenu.playThemeSong();

        // Wait until the user is ready to begin
        while (!ready) {
            Thread.sleep(100);
        }

        // Create the board and pieces
        GameCity board = new GameCity(4, 4);
        board.setFrameTitle("Tic-Tac-Stones");
        GamePiece[] player1Pieces = new GamePiece[]{
                new GamePiece(board, 0, 0, Direction.EAST),
                new GamePiece(board, 3, 1, Direction.WEST),
                new GamePiece(board, 0, 2, Direction.EAST),
                new GamePiece(board, 3, 3, Direction.WEST)
        };
        GamePiece[] player2Pieces = new GamePiece[]{
                new GamePiece(board, 3, 0, Direction.WEST),
                new GamePiece(board, 0, 1, Direction.EAST),
                new GamePiece(board, 3, 2, Direction.WEST),
                new GamePiece(board, 0, 3, Direction.EAST),
        };
        player1Pos = new int[][]{{0, 0}, {3, 1}, {0, 2}, {3, 3}};
        player2Pos = new int[][]{{3, 0}, {0, 1}, {3, 2}, {0, 3}};

        // Set the icon and name
        char[] abcd = {'A', 'B', 'C', 'D'};
        for (int c = 0; c < 4; c++) {
            player1Pieces[c].setIcon(new CircleIcon(Color.cyan));
            player1Pieces[c].setLabel("P1" + abcd[c]);
        }
        for (int c = 0; c < 4; c++) {
            player2Pieces[c].setIcon(new CircleIcon(Color.green));
            player2Pieces[c].setLabel("P2" + abcd[c]);
        }

        // Create everything needed for the dialogs
        JFrame gameMessageFrame = new JFrame();
        final String[] PIECE_CHOICES = {"A", "B", "C", "D", "Pass"};
        final String[] DIRECTION_CHOICES = {"Up", "Down", "Left", "Right"};
        final Direction[] DIRECTIONS = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

        boolean successfulMove = false;

        int toMovePiece;
        int toMoveDirectionChoice = 0;
        int winRes = 0;

        for (; ; ) {
            for (; ; ) {
                successfulMove = false;
                // Prompt player 1 to choose a piece to move
                toMovePiece = JOptionPane.showOptionDialog(gameMessageFrame, "Player 1: Please choose a piece to move.", "Player 1: Piece Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, PIECE_CHOICES, JOptionPane.DEFAULT_OPTION);
                // Exit the game if the red X is clicked
                if (toMovePiece == -1) {
                    System.exit(0);
                }
                // Prompt player 1 to choose the direction if "Pass" was not selected
                if (toMovePiece != 4) {
                    toMoveDirectionChoice = JOptionPane.showOptionDialog(gameMessageFrame, "Player 1: Please choose a direction to move the piece in.", "Player 1: Piece Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, DIRECTION_CHOICES, JOptionPane.DEFAULT_OPTION);
                } else {
                    break;
                }
                // Exit the game if the red X is clicked
                if (toMoveDirectionChoice == -1) {
                    System.exit(0);
                }


                // Move player 1's piece
                try {
                    movePiece(false, toMovePiece, DIRECTIONS[toMoveDirectionChoice], player1Pieces[toMovePiece]);
                    break;
                } catch (RobotException e) {
                    JOptionPane.showMessageDialog(gameMessageFrame, "You cannot go in that direction!", "Illegal Move", JOptionPane.WARNING_MESSAGE);
                }
            }

            // Check if won
            winRes = checkWin();
            if (winRes != 0) {
                break;
            }


            // Prompt player 2 to choose a piece to move
            for (; ; ) {
                successfulMove = false;
                // Prompt player 2 to choose a piece to move
                toMovePiece = JOptionPane.showOptionDialog(gameMessageFrame, "Player 2: Please choose a piece to move.", "Player 2: Piece Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, PIECE_CHOICES, JOptionPane.DEFAULT_OPTION);
                // Exit the game if the red X is clicked
                if (toMovePiece == -1) {
                    System.exit(0);
                }
                // Prompt player 1 to choose the direction if "Pass" was not selected
                if (toMovePiece != 4) {
                    toMoveDirectionChoice = JOptionPane.showOptionDialog(gameMessageFrame, "Player 2: Please choose a direction to move piece ", "Player 2: Piece Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, DIRECTION_CHOICES, JOptionPane.DEFAULT_OPTION);
                } else {
                    break;
                }
                // Exit the game if the red X is clicked
                if (toMoveDirectionChoice == -1) {
                    System.exit(0);
                }
                // Move player 2's piece
                try {
                    movePiece(true, toMovePiece, DIRECTIONS[toMoveDirectionChoice], player2Pieces[toMovePiece]);
                    break;
                } catch (RobotException e) {
                    JOptionPane.showMessageDialog(gameMessageFrame, "You cannot go in that direction!", "Illegal Move", JOptionPane.WARNING_MESSAGE);
                }
            }
            // Check if won
            winRes = checkWin();
            if (winRes != 0) {
                break;
            }
        }
        // Show the message that says who won while playing the theme song
        MainMenu.playThemeSong();
        JOptionPane.showOptionDialog(gameMessageFrame, "PLAYER " + winRes + " WINS!!!\nThanks for playing!", "Winner!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Exit - see you next time!"}, JOptionPane.DEFAULT_OPTION);
        // Exit the game
        System.exit(0);
    }

    /**
     * Moves a piece on the game board one step in the desired direction.
     * If the move would take the piece off the game board, an IllegalMoveException
     * is thrown.
     *
     * @param player2   If true, moves player 2's piece. If false, moves player 1's piece.
     * @param piece     The index of the piece to move. 0 is A, 1 is B, etc.
     * @param direction The `Direction` to move the piece in.
     */
    public static void movePiece(boolean player2, int piece, Direction direction, GamePiece pieceObj) throws IllegalMoveException {
        int[] nextPos = new int[0];

        if (player2) {
            // Keep track of the position of the piece
            player2Pos[piece][0] = pieceObj.getAvenue();
            player2Pos[piece][1] = pieceObj.getStreet();

            // Check if the destination is already occupied by another piece
            nextPos = calculateNextPos(piece, direction, true);
            if (ArrayTools.checkIfArrayInArrayOfArrays(nextPos, player1Pos) || ArrayTools.checkIfArrayInArrayOfArrays(nextPos, player2Pos)) {
                throw new IllegalMoveException("Player 2's GamePiece " + piece + " attempted to move on top of another robot");
            }

            // Move the piece
            pieceObj.move(direction);

            // Keep track of the position of the piece
            player2Pos[piece][0] = pieceObj.getAvenue();
            player2Pos[piece][1] = pieceObj.getStreet();
        } else {
            // Keep track of the position of the piece
            player1Pos[piece][0] = pieceObj.getAvenue();
            player1Pos[piece][1] = pieceObj.getStreet();

            // Check if the destination is already occupied by another piece
            nextPos = calculateNextPos(piece, direction, false);
            if (ArrayTools.checkIfArrayInArrayOfArrays(nextPos, player1Pos) || ArrayTools.checkIfArrayInArrayOfArrays(nextPos, player2Pos)) {
                throw new IllegalMoveException("Player 1's GamePiece " + piece + " attempted to move on top of another robot");
            }

            // Move the piece
            pieceObj.move(direction);

            // Keep track of the position of the piece
            player1Pos[piece][0] = pieceObj.getAvenue();
            player1Pos[piece][1] = pieceObj.getStreet();
        }
    }

    private static int[] calculateNextPos(int pieceNum, Direction toTravelDir, boolean player2) {
        int[][] gamePieces;
        int[] res;
        if (player2) {
            gamePieces = player2Pos;
        } else {
            gamePieces = player1Pos;
        }
        switch (toTravelDir) {
            case NORTH:
                res = new int[]{gamePieces[pieceNum][0], gamePieces[pieceNum][1] - 1};
                return res;
            case SOUTH:
                res = new int[]{gamePieces[pieceNum][0], gamePieces[pieceNum][1] + 1};
                return res;
            case EAST:
                res = new int[]{gamePieces[pieceNum][0] + 1, gamePieces[pieceNum][1]};
                return res;
            case WEST:
                res = new int[]{gamePieces[pieceNum][0] - 1, gamePieces[pieceNum][1]};
                return res;
        }
        return new int[0];
    }

    /**
     * Checks to see who the winner is, if there is any.
     *
     * @return 1 if player 1 won and 2 if player 2 won, otherwise 0 if no one won yet.
     */
    public static int checkWin() {
        // This algorithm first checks for one neighbouring piece to determine the type of win pattern (diagonal /, diagonal \, horizontal or vertical).
        // Once it knows, it then checks if the other corresponding piece is present.
        // It took me so many tries and hours of pain to perfect the algorithm!
        for (int[] piece : player1Pos) {
            for (int[] piece2 : player1Pos) {
                if (piece2[0] == piece[0] + 1 && piece2[1] == piece[1] - 1) {
                    // diagonal /
                    for (int[] piece3 : player1Pos) {
                        if (piece3[0] == piece[0] - 1 && piece3[1] == piece[1] + 1) {
                            return 1;
                        }
                    }
                } else if (piece2[0] == piece[0] - 1 && piece2[1] == piece[1] - 1) {
                    // diagonal \
                    for (int[] piece3 : player1Pos) {
                        if (piece3[0] == piece[0] + 1 && piece3[1] == piece[1] + 1) {
                            return 1;
                        }
                    }
                } else if (piece2[0] == piece[0] + 1 && piece2[1] == piece[1]) {
                    // horizontal
                    for (int[] piece3 : player1Pos) {
                        if (piece3[0] == piece[0] - 1 && piece3[1] == piece[1]) {
                            return 1;
                        }
                    }
                } else if (piece2[0] == piece[0] && piece2[1] == piece[1] - 1) {
                    // vertical
                    for (int[] piece3 : player1Pos) {
                        if (piece3[0] == piece[0] && piece3[1] == piece[1] + 1) {
                            return 1;
                        }
                    }
                }
            }
        }

        // Now do the same thing for player 2
        for (int[] piece : player2Pos) {
            for (int[] piece2 : player2Pos) {
                if (piece2[0] == piece[0] + 1 && piece2[1] == piece[1] - 1) {
                    // diagonal /
                    for (int[] piece3 : player2Pos) {
                        if (piece3[0] == piece[0] - 1 && piece3[1] == piece[1] + 1) {
                            return 2;
                        }
                    }
                } else if (piece2[0] == piece[0] - 1 && piece2[1] == piece[1] - 1) {
                    // diagonal \
                    for (int[] piece3 : player2Pos) {
                        if (piece3[0] == piece[0] + 1 && piece3[1] == piece[1] + 1) {
                            return 2;
                        }
                    }
                } else if (piece2[0] == piece[0] + 1 && piece2[1] == piece[1]) {
                    // horizontal
                    for (int[] piece3 : player2Pos) {
                        if (piece3[0] == piece[0] - 1 && piece3[1] == piece[1]) {
                            return 2;
                        }
                    }
                } else if (piece2[0] == piece[0] && piece2[1] == piece[1] - 1) {
                    // vertical
                    for (int[] piece3 : player2Pos) {
                        if (piece3[0] == piece[0] && piece3[1] == piece[1] + 1) {
                            return 2;
                        }
                    }
                }
            }
        }
        return 0;
    }
}
