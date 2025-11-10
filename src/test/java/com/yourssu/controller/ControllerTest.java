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
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class ControllerTest {
    private ByteArrayOutputStream outputStreamCaptor;

    void systemIn(String input) {
        System.out.println("왜 안돼 ㅠ");
        ByteArrayInputStream inputs = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputs);
        System.out.println(inputs);
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
        systemIn("A1\n"); // 흑

        controller.run();



        outputStreamCaptor.flush();


        assertThat(outputStreamCaptor.toString()).
                contains("BLACK wins");
    }

    @Test
    public void inputTest() {
        String input = "test";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        assertThat(input).isEqualTo(scanner.nextLine());
    }
}
