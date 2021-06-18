package com.pittacode.obstruction;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

public class GameConsoleTest {

    @Test
    void shouldAskForTileCoordinatesWithPlayerNameWhenAskingInput() throws IOException {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(outputStreamCaptor);
        BufferedReader inputReader = new BufferedReader(new StringReader("0 0"));
        GameConsole underTest = new GameConsole(outputStream, inputReader);

        underTest.roundQuestion("A");

        assertThat(outputStreamCaptor.toString()).isEqualTo("Make your move, A> ");
    }

    @Test
    void shouldReturnTileCoordinatesWhenAskingInput() throws IOException {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(outputStreamCaptor);
        String linesOfInput = "3 4\n"
                +"    3 4\n"
                +"3 4   \n"
                +"3    4\n"
                +"  3   4   \n";
        BufferedReader inputReader = new BufferedReader(new StringReader(linesOfInput));
        GameConsole underTest = new GameConsole(outputStream, inputReader);

        Tile tile = underTest.roundQuestion("A");
        assertThat(tile.x).isEqualTo(3);
        assertThat(tile.y).isEqualTo(4);

        tile = underTest.roundQuestion("A");
        assertThat(tile.x).isEqualTo(3);
        assertThat(tile.y).isEqualTo(4);

        tile = underTest.roundQuestion("A");
        assertThat(tile.x).isEqualTo(3);
        assertThat(tile.y).isEqualTo(4);

        tile = underTest.roundQuestion("A");
        assertThat(tile.x).isEqualTo(3);
        assertThat(tile.y).isEqualTo(4);

        tile = underTest.roundQuestion("A");
        assertThat(tile.x).isEqualTo(3);
        assertThat(tile.y).isEqualTo(4);
    }
}
