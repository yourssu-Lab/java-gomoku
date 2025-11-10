package com.yourssu.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TurnTest {
    @Test
    void getTurnCount는_첫_턴에_1을_반환한다() {
        // given
        Turn turn = new Turn();

        // when & then
        assertThat(turn.getCurrentTurn()).isEqualTo(1);
    }

    @Test
    void 첫_턴은_흑을_반환한다() {
        // given
        Turn turn = new Turn();

        // when & then
        assertThat(turn.getCurrnetPiece()).isEqualTo(Piece.BLACK);
    }

    @Test
    void 다음턴이_되면_turnCount는_1_증가한다() {
        // given
        Turn turn = new Turn();
        // when
        turn.next();
        // then
        assertThat(turn.getCurrentTurn()).isEqualTo(2);

    }

    @Test
    void 흑차례에서_다음턴이_되면_백차례가_된다() {
        // given
        Turn turn = new Turn();
        // when
        turn.next();
        // then
        assertThat(turn.getCurrnetPiece()).isEqualTo(Piece.WHITE);
    }

    @Test
    void 백차례에서_다음턴이_되면_흑차례가_된다() {
        // given
        Turn turn = new Turn();
        turn.next();
        // when
        turn.next();
        // then
        assertThat(turn.getCurrnetPiece()).isEqualTo(Piece.BLACK);
    }
}
