package org.felixan.tictacstones;

import becker.robots.*;
import becker.robots.icons.CircleIcon;

import java.awt.Color;

/**
 * Class that represents the game board. Contains a becker.robots.City and some other things.
 */
public class GameBoard {
    private City boardCity = new City(4, 4);
    private RobotUIComponents boardUI;
    private GamePiece[] player1Pieces = {
            new GamePiece(boardCity, 0, 0, Direction.EAST),
            new GamePiece(boardCity, 3, 1, Direction.WEST),
            new GamePiece(boardCity, 0, 2, Direction.EAST),
            new GamePiece(boardCity, 3, 3, Direction.WEST)
    };
    private GamePiece[] player2pieces = {
            new GamePiece(boardCity, 3, 0, Direction.WEST),
            new GamePiece(boardCity, 0, 1, Direction.EAST),
            new GamePiece(boardCity, 3, 2, Direction.WEST),
            new GamePiece(boardCity, 0, 3, Direction.EAST),
    };
    private CircleIcon player1Icon = new CircleIcon(Color.cyan);
    private CircleIcon player2Icon = new CircleIcon(Color.white);

    public GameBoard() {
        City.showFrame(false);
        boardUI = new RobotUIComponents(boardCity, 0, 0, 10, 10);
        boardUI.getControlPanel().setVisible(false);
        boardUI.getMenuBar().setVisible(false);
        char[] abcd = {'A', 'B', 'C', 'D'};
        for (int c = 0; c < 4; c++) {
            player1Pieces[c].setIcon(player1Icon);
            player1Pieces[c].setLabel("P1 Piece " + abcd[c]);
        }
        for (int c = 0; c < 4; c++) {
            player2pieces[c].setIcon(player2Icon);
            player2pieces[c].setLabel("P2 Piece " + abcd[c]);
        }
    }

    /**
     * Returns the City that the GameBoard contains.
     */
    public City getCity() {

        return boardCity;
    }

    /**
     * Returns the UI of the board.
     */
    public RobotUIComponents getBoardUI() {

        return boardUI;
    }

    /**
     * Moves a piece on the game board one step in the desired direction.
     * If the move would take the piece off the game board, an InvalidMoveException
     * is thrown.
     *
     * @param player2   If true, moves player 2's piece. If false, moves player 1's piece.
     * @param piece     The index of the piece to move. 0 is A, 1 is B, etc.
     * @param direction The `Direction` to move the piece in.
     */
    public void movePiece(boolean player2, int piece, Direction direction) throws InvalidMoveException {

        if (player2) {
            player2pieces[piece].move(direction);
        } else {
            player1Pieces[piece].move(direction);
        }
    }
}
