package org.felixan.tictacstones;

import java.lang.RuntimeException;

public class InvalidMoveException extends RuntimeException {
    /**
     * Thrown by GamePiece when they try to make an illegal move. Inherited from RuntimeException.
     *
     * @param errorMsg The error message to show.
     */
    public InvalidMoveException(String errorMsg) {
        super(errorMsg);
    }
}
