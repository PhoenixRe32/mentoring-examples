package com.pittacode.obstruction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoundTest {

    private Round underTest;

    @BeforeEach
    void setUp() {
        underTest = new Round(3, 3);
    }

    @Test
    void shouldUpdateBoardWhenAMoveIsPlayed() {
        underTest.play(0, 0, "A");

        assertThat(underTest.getBoard()[0][0]).isEqualTo("A");
    }

    @Test
    void shouldNotUpdateBoardWhenAMoveIsPlayedOnExistingMove() {
        underTest.play(1, 0, "A");
        underTest.play(1, 0, "B");

        assertThat(underTest.getBoard()[1][0]).isEqualTo("A");
    }

    @Test
    void shouldReportSuccessOfMoveWhenAMoveIsPlayed() {
        boolean isSuccessful = underTest.play(0, 0, "A");

        assertThat(isSuccessful).isTrue();
    }

    @Test
    void shouldReportFailureOfMoveWhenAMoveIsNotPlayed() {
        underTest.play(1, 0, "A");
        boolean isSuccessful = underTest.play(1, 0, "B");

        assertThat(isSuccessful).isFalse();
    }

    @Test
    void shouldReportFailureOfMoveWhenAMoveIsInvalidInX() {
        boolean isSuccessful = underTest.play(-1, 0, "B");

        assertThat(isSuccessful).isFalse();
    }

    @Test
    void shouldReportFailureOfMoveWhenAMoveIsInvalidInY() {
        boolean isSuccessful = underTest.play(0, 100, "B");

        assertThat(isSuccessful).isFalse();
    }

    @Test
    void shouldGenerateEmptyBoardWhenJustCreatedBeforeAnyMovesPlayed() {
        underTest = new Round(2, 2);

        assertThat(underTest.getBoard()).isDeepEqualTo(new String[][]{
                {"", ""},
                {"", ""}
        });
    }

    @Test
    void shouldMarkSurroundingTilesOfPlayedTileAsDead() {
        underTest.play(1, 1, "B");

        assertThat(underTest.getBoard()).isDeepEqualTo(new String[][]{
                {"X", "X", "X"},
                {"X", "B", "X"},
                {"X", "X", "X"}
        });
    }

    @Test
    void shouldGenerateBoardVisualisation() {
        underTest.play(0, 1, "B");

        assertThat(underTest.generateBoardVisualisation()).isEqualTo(
                //@formatter:off
                "   X   X    \n" +
                "   B   X    \n" +
                "   X   X    \n"
                //@formatter:on
        );
    }
}