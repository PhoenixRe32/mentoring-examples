package com.pittacode.obstruction;

public class MoveOutcome {

    public final boolean isValid;
    public final MoveError error;


    private MoveOutcome(boolean isValid, MoveError error) {
        this.isValid = isValid;
        this.error = error;
    }

    public static MoveOutcome aSuccessfulMove(){
        return new MoveOutcome(true, null);
    }

    public static MoveOutcome aFailedMove(MoveError error){
        return new MoveOutcome(false, error);
    }

    public enum MoveError {
        DOUBLE_PLAY("You can only move once per round."),
        OUT_OF_BOUNDS("Tile is out of bounds."),
        OCCUPIED("Tile is occupied. You can't move on dead space or other player's territory.");

        public final String message;

        MoveError(String message) {
            this.message = message;
        }
    }
}
