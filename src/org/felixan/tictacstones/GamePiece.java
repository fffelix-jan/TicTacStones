package org.felixan.tictacstones;

import becker.robots.*;

public class GamePiece extends Robot {
    /**
     * A game piece for the Tic-Tac-Stones game. Inherited from the becker.robots.Robot class.
     *
     * @param city      The city to create the GamePiece in.
     * @param x         The x-coordinate (avenue) where it will be created.
     * @param y         The y-coordinate (street) where it will be created.
     * @param direction The direction that will be facing in when it is created.
     */
    public GamePiece(City city, int x, int y, Direction direction) {
        super(city, y, x, direction);
    }

    /**
     * Moves the GamePiece one step forward in the desired direction.
     * If the destination is not clear, an InvalidMoveException will be thrown.
     *
     * @param targetDirection The direction to move a step forward in.
     * @throws InvalidMoveException Thrown if the destination is not clear.
     */
    public void move(Direction targetDirection) throws InvalidMoveException {
        switch (targetDirection) {
            case NORTH:
                if (super.getStreet() - 1 < 0) {
                    throw new InvalidMoveException("Robot at (" + super.getStreet() + ", " + super.getAvenue() + ") attempted to move off the north end of the board.");
                }
                break;
            case EAST:
                if (super.getAvenue() + 1 > 3) {
                    throw new InvalidMoveException("Robot at (" + super.getStreet() + ", " + super.getAvenue() + ") attempted to move off the east end of the board.");
                }
                break;
            case SOUTH:
                if (super.getStreet() + 1 > 3) {
                    throw new InvalidMoveException("Robot at (" + super.getStreet() + ", " + super.getAvenue() + ") attempted to move off the south end of the board.");
                }
                break;
            case WEST:
                if (super.getAvenue() - 1 < 0) {
                    throw new InvalidMoveException("Robot at (" + super.getStreet() + ", " + super.getAvenue() + ") attempted to move off the west end of the board.");
                }
                break;
        }

        while (super.getDirection() != targetDirection) {
            super.turnLeft();
        }

        if (super.frontIsClear()) {
            super.move();
        } else {
            throw new InvalidMoveException("Robot at (" + super.getStreet() + ", " + super.getAvenue() + ") attempted to move into an obstacle.");
        }
    }

    public boolean checkNeighbours() {
        // TODO: write this method
        return false;
    }
}
