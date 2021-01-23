package org.felixan.tictacstones;

import becker.robots.RobotException;

public class IllegalMoveException extends RobotException {
    /**
     * Thrown by GamePiece when they try to make an illegal move. Inherited from RobotException.
     *
     * @param errorMsg The error message to show.
     */
    public IllegalMoveException(String errorMsg) {
        super(errorMsg);
    }
}
