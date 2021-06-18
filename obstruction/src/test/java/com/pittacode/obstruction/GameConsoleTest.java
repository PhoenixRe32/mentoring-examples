package com.pittacode.obstruction;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

public class GameConsoleTest {

    @Test
    void shouldAskForTileCoordinatesWhenAskingInput() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(outputStreamCaptor);
        GameConsole underTest = new GameConsole(outputStream);

        underTest.roundQuestion();

        assertThat(outputStreamCaptor.toString()).isEqualTo("Make your move, Player> ");
    }
}
