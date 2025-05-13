package com.yourssu.view.implement;

import com.yourssu.model.Piece;

import java.util.HashMap;
import java.util.Map;

public class GomokuUIConstants {
    static final String COLUMN_LABELS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final int POSITION_LENGTH = 2;
    static final int BOARD_SIZE = 15;
    static final String GAME_ENDED_COMMAND = "q";
    static final Map<Piece, String> PLAYER_PROMPTS = new HashMap<>();

    static {
        PLAYER_PROMPTS.put(Piece.BLACK, "\n흑(%s)의 차례입니다. 위치를 입력하세요 (예: A1) 또는 '%s'로 종료: ".formatted(Symbol.BLACK.getString(), GAME_ENDED_COMMAND));
        PLAYER_PROMPTS.put(Piece.WHITE, "\n백(%s)의 차례입니다. 위치를 입력하세요 (예: A1) 또는 '%s'로 종료: ".formatted(Symbol.WHITE.getString(), GAME_ENDED_COMMAND));
    }
}
