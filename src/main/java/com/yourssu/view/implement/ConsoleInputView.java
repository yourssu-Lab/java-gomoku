package com.yourssu.view.implement;

import com.yourssu.view.InputView;
import com.yourssu.view.dto.CoordinateDTO;

import java.util.Scanner;

import static com.yourssu.view.implement.GomokuUIConstants.*;

public class ConsoleInputView implements InputView {
    private final Scanner scanner;
    private final int size;

    public ConsoleInputView(Scanner scanner, int size) {
        if (scanner == null) {
            this.scanner = new Scanner(System.in);
            this.size = size;
            return;
        }
        this.scanner = scanner;
        this.size = size;
    }


    private String getInput() {
        return scanner.nextLine().trim();
    }

    @Override
    public CoordinateDTO getInputForCoordinate() {
        String input = getInput();
        if (GAME_ENDED_COMMAND.equalsIgnoreCase(input)) {
            return null;
        }
            return convertCoordinate(input);
    }

    private CoordinateDTO convertCoordinate(String position) {
        validatePositionLength(position);
        int col = COLUMN_LABELS.indexOf(position.toUpperCase().charAt(0));
        try {
            int row = size - Integer.parseInt(position.substring(1));
            validateCoordinate(row, col);
            return new CoordinateDTO(row, col);
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
        if (row < 0 || row >= size || col >= size) {
            throw new IllegalArgumentException();
        }
    }
}
