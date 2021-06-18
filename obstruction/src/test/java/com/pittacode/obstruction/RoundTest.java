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
}