package com.pittacode.obstruction;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoundTest {

    @Test
    void shouldUpdateBoardWhenAMoveIsPlayed() {
        Round underTest = new Round(3, 3);

        underTest.play(0, 0, "A");

        assertThat(underTest.getBoard()[0][0]).isEqualTo("A");
    }

    @Test
    void shouldNotUpdateBoardWhenAMoveIsPlayedOnExistingMove() {
        Round underTest = new Round(3, 3);

        underTest.play(1, 0, "A");
        underTest.play(1, 0, "B");

        assertThat(underTest.getBoard()[1][0]).isEqualTo("A");
    }

    @Test
    void shouldReportSuccessOfMoveWhenAMoveIsPlayed() {
        Round underTest = new Round(3, 3);

        boolean isSuccessful = underTest.play(0, 0, "A");

        assertThat(isSuccessful).isTrue();
    }

    @Test
    void shouldReportFailureOfMoveWhenAMoveIsNotPlayed() {
        Round underTest = new Round(3, 3);

        underTest.play(1, 0, "A");
        boolean isSuccessful = underTest.play(1, 0, "B");

        assertThat(isSuccessful).isFalse();
    }

    @Test
    void shouldReportFailureOfMoveWhenAMoveIsInvalidInX() {
        Round underTest = new Round(3, 3);

        boolean isSuccessful = underTest.play(-1, 0, "B");

        assertThat(isSuccessful).isFalse();
    }

    @Test
    void shouldReportFailureOfMoveWhenAMoveIsInvalidInY() {
        Round underTest = new Round(3, 3);

        boolean isSuccessful = underTest.play(0, 100, "B");

        assertThat(isSuccessful).isFalse();
    }

    @Test
    void shouldGenerateEmptyBoardWhenJustCreatedBeforeAnyMovesPlayed() {
        Round underTest = new Round(3, 3);

        underTest = new Round(2, 2);

        assertThat(underTest.getBoard()).isDeepEqualTo(new String[][]{
                {"", ""},
                {"", ""}
        });
    }

    @Test
    void shouldMarkSurroundingTilesOfPlayedTileAsDead() {
        Round underTest = new Round(3, 3);

        underTest.play(1, 1, "B");

        assertThat(underTest.getBoard()).isDeepEqualTo(new String[][]{
                {"X", "X", "X"},
                {"X", "B", "X"},
                {"X", "X", "X"}
        });
    }

    @Test
    void shouldGenerateBoardVisualisation() {
        Round underTest = new Round(3, 3);

        underTest.play(0, 1, "B");

        assertThat(underTest.generateBoardVisualisation()).isEqualTo(
                //@formatter:off
                "   X   X    \n" +
                "   B   X    \n" +
                "   X   X    \n"
                //@formatter:on
        );
    }

    @Test
    void shouldKnowWhoPlayedTheMoveThatResultedInTheBoard() {
        Round underTest = new Round(3, 3);

        underTest.play(2, 1, "V");

        assertThat(underTest.getMovePlayer()).isEqualTo("V");
    }

    @Test
    void shouldCreateNewRoundWithPreviousRoundBoard() {
        Round previousRound = new Round(3, 3);
        previousRound.play(2, 0, "A");

        Round currentRound = new Round(previousRound);

        assertThat(currentRound.getBoard()[2][0]).isEqualTo("A");
    }

    @Test
    void shouldOnlyUpdateBoardOfNewBoardWhenPlayingAMove() {
        Round previousRound = new Round(3, 3);
        previousRound.play(2, 0, "A");

        Round currentRound = new Round(previousRound);
        currentRound.play(2, 2, "B");

        assertThat(currentRound.getBoard()[2][2]).isEqualTo("B");
        assertThat(previousRound.getBoard()[2][2]).isEqualTo("");
    }
}