package com.yourssu;

import com.yourssu.controller.BoardGameController;
import com.yourssu.controller.Controller;
import com.yourssu.model.GomokBoard;
import com.yourssu.view.implement.ConsoleInputView;
import com.yourssu.view.implement.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new BoardGameController(new ConsoleInputView(), new ConsoleOutputView(), new GomokBoard());
        controller.run();
    }
}
