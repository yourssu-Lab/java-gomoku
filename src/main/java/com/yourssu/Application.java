package com.yourssu;

import com.yourssu.controller.BoardController;
import com.yourssu.controller.Controller;
import com.yourssu.model.BoardImpl;
import com.yourssu.model.Turn;
import com.yourssu.strategy.WinnerChecker;
import com.yourssu.view.implement.ConsoleInputView;
import com.yourssu.view.implement.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new BoardController(
                new ConsoleInputView(),
                new ConsoleOutputView(),
                new Turn(),
                new WinnerChecker(),
                new BoardImpl(10)
        );
        controller.run();
    }
}
