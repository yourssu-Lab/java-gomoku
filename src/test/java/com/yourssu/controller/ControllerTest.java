package com.yourssu.controller;

import com.yourssu.model.BoardImpl;
import com.yourssu.model.Turn;
import com.yourssu.strategy.WinnerChecker;
import com.yourssu.view.implement.ConsoleInputView;
import com.yourssu.view.implement.ConsoleOutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ControllerTest {
    private ByteArrayOutputStream outputStreamCaptor;

    void systemIn(String input) {
        ByteArrayInputStream inputs = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputs);
    }

    @BeforeEach
    void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void 흑이_이기는_시나리오_1() throws IOException {
        Controller controller = new BoardController(
                new ConsoleInputView(),
                new ConsoleOutputView(),
                new Turn(),
                new WinnerChecker(),
                new BoardImpl(10)
        );
        systemIn("""
                A1
                B1
                A2
                B2
                A3
                B3
                A4
                B4
                A5
                """);

        controller.run();

        outputStreamCaptor.flush();

        assertThat(outputStreamCaptor.toString()).
                contains("BLACK wins");
    }

    @Test
    void 백이_이기는_시나리오_1() throws IOException {
        Controller controller = new BoardController(
                new ConsoleInputView(),
                new ConsoleOutputView(),
                new Turn(),
                new WinnerChecker(),
                new BoardImpl(10)
        );
        systemIn("""
                A1
                B1
                A2
                B2
                A3
                B3
                A4
                B4
                A4
                B5
                """);

        controller.run();

        outputStreamCaptor.flush();

        assertThat(outputStreamCaptor.toString()).
                contains("WHITE wins");
    }

    @Test
    void 게임_진행_중_q를_입력하면_게임을_종료한다() {
        Controller controller = new BoardController(
                new ConsoleInputView(),
                new ConsoleOutputView(),
                new Turn(),
                new WinnerChecker(),
                new BoardImpl(10)
        );

        systemIn("q");

        controller.run();

        assertThat(outputStreamCaptor.toString()).
                contains("게임이 종료되었습니다.");
    }
}
