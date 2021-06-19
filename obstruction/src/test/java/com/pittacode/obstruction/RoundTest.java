package com.pittacode.obstruction;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoundTest {

    @Test
    void shouldUpdateBoardWhenAMoveIsPlayed() {
        Round underTest = new Round(new Dimensions(3, 3), "A");

        underTest.play(new Tile(0, 0));

        assertThat(underTest.getBoard()[0][0]).isEqualTo("A");
    }

    @Test
    void shouldNotUpdateBoardWhenAMoveIsPlayedOnExistingMove() {
        Round underTest = new Round(new Dimensions(3, 3), "A");

        underTest.play(new Tile(1, 0));
        underTest.play(new Tile(1, 0));

        assertThat(underTest.getBoard()[1][0]).isEqualTo("A");
    }

    @Test
    void shouldReportSuccessOfMoveWhenAMoveIsPlayed() {
        Round underTest = new Round(new Dimensions(3, 3), "A");

        MoveOutcome moveOutcome = underTest.play(new Tile(0, 0));

        assertThat(moveOutcome.isValid).isTrue();
    }

    @Test
    void shouldReportFailureOfMoveWhenAMoveIsInvalidInX() {
        Round underTest = new Round(new Dimensions(3, 3), "A");

        MoveOutcome moveOutcome = underTest.play(new Tile(-1, 0));

        assertThat(moveOutcome.isValid).isFalse();
        assertThat(moveOutcome.error).isEqualTo(MoveOutcome.MoveError.OUT_OF_BOUNDS);
    }

    @Test
    void shouldReportFailureOfMoveWhenAMoveIsInvalidInY() {
        Round underTest = new Round(new Dimensions(3, 3), "A");

        MoveOutcome moveOutcome = underTest.play(new Tile(0, 100));

        assertThat(moveOutcome.isValid).isFalse();
        assertThat(moveOutcome.error).isEqualTo(MoveOutcome.MoveError.OUT_OF_BOUNDS);
    }

    @Test
    void shouldGenerateEmptyBoardWhenJustCreatedBeforeAnyMovesPlayed() {
        Round underTest = new Round(new Dimensions(2, 2), "A");

        assertThat(underTest.getBoard()).isDeepEqualTo(new String[][]{
                {"✹", "✹"},
                {"✹", "✹"}
        });
    }

    @Test
    void shouldMarkSurroundingTilesOfPlayedTileAsDead() {
        Round underTest = new Round(new Dimensions(3, 3), "B");

        underTest.play(new Tile(1, 1));

        assertThat(underTest.getBoard()).isDeepEqualTo(new String[][]{
                {"☥", "☥", "☥"},
                {"☥", "B", "☥"},
                {"☥", "☥", "☥"}
        });
    }

    @Test
    void shouldGenerateBoardVisualisation() {
        Round underTest = new Round(new Dimensions(3, 3), "A");

        underTest.play(new Tile(0, 1));

        assertThat(underTest.generateBoardVisualisation()).isEqualTo(
                //@formatter:off
                "   ☥   ☥   ✹\n" +
                "   A   ☥   ✹\n" +
                "   ☥   ☥   ✹\n"
                //@formatter:on
        );
    }

    @Test
    void shouldKnowWhoPlayedTheMoveThatResultedInTheBoard() {
        Round underTest = new Round(new Dimensions(3, 3), "V");

        underTest.play(new Tile(2, 1));

        assertThat(underTest.getRoundPlayer()).isEqualTo("V");
    }

    @Test
    void shouldCreateNewRoundWithPreviousRoundBoard() {
        Round previousRound = new Round(new Dimensions(3, 3), "A");
        previousRound.play(new Tile(2, 0));

        Round currentRound = new Round(previousRound, "C");

        assertThat(currentRound.getBoard()[2][0]).isEqualTo("A");
    }

    @Test
    void shouldOnlyUpdateBoardOfNewBoardWhenPlayingAMove() {
        Round previousRound = new Round(new Dimensions(3, 3), "A");
        previousRound.play(new Tile(2, 0));

        Round currentRound = new Round(previousRound, "B");
        currentRound.play(new Tile(2, 2));

        assertThat(currentRound.getBoard()[2][2]).isEqualTo("B");
        assertThat(previousRound.getBoard()[2][2]).isEqualTo("✹");
    }

    @Test
    void shouldNoBeAbleToPlayOnDeadTiles() {
        Round previousRound = new Round(new Dimensions(3, 3), "A");
        previousRound.play(new Tile(0, 0));

        Round currentRound = new Round(previousRound, "C");
        MoveOutcome moveOutcome = currentRound.play(new Tile(0, 1));

        assertThat(moveOutcome.isValid).isFalse();
        assertThat(moveOutcome.error).isEqualTo(MoveOutcome.MoveError.OCCUPIED);
    }

    @Test
    void shouldNoBeAbleToPlayOnOccupiedTiles() {
        Round previousRound = new Round(new Dimensions(3, 3), "A");
        previousRound.play(new Tile(0, 0));

        Round currentRound = new Round(previousRound, "C");
        MoveOutcome moveOutcome = currentRound.play(new Tile(0, 0));

        assertThat(moveOutcome.isValid).isFalse();
        assertThat(moveOutcome.error).isEqualTo(MoveOutcome.MoveError.OCCUPIED);
    }

    @Test
    void shouldOnlyBeAbleToPlayOnce() {
        Round currentRound = new Round(new Dimensions(6, 6), "A");
        MoveOutcome firstMoveOutcome = currentRound.play(new Tile(0, 0));
        // Make sure the second move is not invalid
        MoveOutcome secondMoveOutcome = currentRound.play(new Tile(5, 5));

        assertThat(firstMoveOutcome.isValid).isTrue();
        assertThat(secondMoveOutcome.isValid).isFalse();
        assertThat(secondMoveOutcome.error).isEqualTo(MoveOutcome.MoveError.DOUBLE_PLAY);
    }

    @Test
    void shouldReportIfBoardIsInEndState() {
        Round underTest = new Round(new Dimensions(3, 3), "A");
        underTest.play(new Tile(1, 1));

        assertThat(underTest.isInEndState()).isTrue();
    }
}