package com.yourssu.model;

import com.yourssu.view.implement.Symbol;

public class BoardProxy implements Board {

    Board board = new BoardImpl(7);

    @Override
    public Piece getPiece(int row, int column) {
        System.out.println("해당 row, column의 해당 바둑알을 확인합니다");
        Piece piece = board.getPiece(row,column);
        System.out.println( "바둑알은 " + Symbol.of(piece) + "으로 확인했습니다.");
        return piece;
    }

    @Override
    public void placePiece(int row, int column, Piece piece) {
        System.out.println("해당 row, column에 바둑알을 두겠습니다");
        board.placePiece(row,column,piece);
        System.out.println("바둑알을 뒀습니다.");
    }

    @Override
    public int getSize() {
        System.out.println("바둑판 크기를 출력합니다.");
        int size = board.getSize();
        System.out.println("바둑판 크기는 "+ size + "입니다.");
        return size;
    }
}
