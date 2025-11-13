package com.yourssu.controller;

import com.yourssu.model.Board;
import com.yourssu.model.BoardImpl;
import com.yourssu.view.InputView;
import com.yourssu.view.OutputView;
import com.yourssu.view.implement.ConsoleInputView;
import com.yourssu.view.implement.ConsoleOutputView;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import com.yourssu.model.Board;
import com.yourssu.model.BoardImpl;
import com.yourssu.model.Piece;
import com.yourssu.view.dto.CoordinateDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ControllerTest {
    private static Scanner makeScanner(String[] blackInput, String[] whiteInput) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < blackInput.length; i++) {
            sb.append(blackInput[i]);
            sb.append("\n");
            sb.append(whiteInput[i]);
            sb.append("\n");
        }

        return new Scanner(sb.toString());
    }


    @Test
    void 스캐너가_잘_작동하는가() {
        String[] blackInput = {"A0", "B0",  "C0", "D0", "E0"};
        String[] whiteInput = {"A1", "B2", "C3", "D4", "E4"};

        Scanner scanner = ControllerTest.makeScanner(blackInput, whiteInput);

        assertThat(scanner.nextLine()).isEqualTo("A0");
        assertThat(scanner.nextLine()).isEqualTo("A1");
    }

    private Controller makeController(String[] blackInput, String[] whiteInput, int size) {
        Scanner scanner = makeScanner(blackInput, whiteInput);


        Board board = new BoardImpl(size);
        OutputView output = new ConsoleOutputView(board.getSize());
        InputView input = new ConsoleInputView(scanner, board.getSize());

        return new ControllerImpl(output, input, board);
    }

    @Test
    void 흑이_게임을_이길수_있는가() {
        String[] blackInput = {"A1", "B1",  "C1", "D1", "E1"};
        String[] whiteInput = {"A2", "B3", "C4", "D5", "E6"};

        Controller controller = makeController(blackInput, whiteInput, 15);

        assertThat(controller.run()).isNotNull().isEqualTo(Piece.BLACK);
    }

    @Test
    void 백이_게임을_이길수_있는가() {
        String[] blackInput = {"A3", "B3", "C4", "D5", "E6"};
        String[] whiteInput = {"A1", "B2", "C3", "D4", "E5"};

        Controller controller = makeController(blackInput, whiteInput, 15);

        assertThat(controller.run()).isNotNull().isEqualTo(Piece.WHITE);
    }

    @Test
    void 무승부일때_게임이_종료_되는가() {
        String[] blackInput = {
                "A1", "A3", "A5",
                "B2", "B4",
                "C1", "C3", "C5",
                "D2", "D4",
                "E2", "E3", "E4"
        };
        String[] whiteInput = {
                "A2", "A4",
                "B1", "B3", "E5",
                "C2", "C4",
                "D1", "D3", "D5",
                "E1", "E5", "E6"
        };

        Controller controller = makeController(blackInput, whiteInput, 5);

        assertThat(controller.run()).isNull();
    }

    @Test
    void 잘못된_입력을_받을_시에_예외처리_후_종료되는가() {
        String[] blackInput = {"A-1"};
        String[] whiteInput = {"A1111"};

        Controller controller = makeController(blackInput, whiteInput, 15);

        assertThatThrownBy(controller::run).isInstanceOf(IllegalArgumentException.class);
    }
}
