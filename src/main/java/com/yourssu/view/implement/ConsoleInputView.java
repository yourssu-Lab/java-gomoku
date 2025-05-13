package com.yourssu.view.implement;

import com.yourssu.utils.Pair;
import com.yourssu.view.InputView;

import java.util.Scanner;

import static com.yourssu.view.implement.GomokuUIConstants.*;

public class ConsoleInputView implements InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private String getInput() {
        return scanner.nextLine().trim();
    }

    @Override
    public Pair<Integer, Integer> getInputForCoordinate() {
        String input = getInput();
        if (GAME_ENDED_COMMAND.equalsIgnoreCase(input)) {
            return null;
        }
            return convertCoordinate(input);
    }

    private Pair<Integer, Integer> convertCoordinate(String position) {
        validatePositionLength(position);
        int col = COLUMN_LABELS.indexOf(position.toUpperCase().charAt(0));
        try {
            int row = BOARD_SIZE - Integer.parseInt(position.substring(1));
            validateCoordinate(row, col);
            return new Pair<>(row, col);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private static void validatePositionLength(String position) {
        if (position.length() != POSITION_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    private void validateCoordinate(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col >= BOARD_SIZE) {
            throw new IllegalArgumentException();
        }
    }
}
