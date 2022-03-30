package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.Color;

public abstract class Started implements GameState {

    protected Board board;
    protected final Color turnColor;

    public Started(Board board, Color turnColor) {
        this.board = board;
        this.turnColor = turnColor;
    }
}
