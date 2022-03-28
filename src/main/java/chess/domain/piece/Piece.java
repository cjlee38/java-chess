package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Point;

public abstract class Piece {

    private final Color color;
    private final PieceType type;

    public Piece(Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameType(PieceType type) {
        return this.type == type;
    }

    public double getScore() {
        return this.type.getScore();
    }

    public abstract boolean move(Board board, Point from, Point to);
}
