package chess.domain.game;

import chess.dto.Arguments;
import chess.domain.Color;
import chess.domain.board.Board;

public class Finished extends GameState {

    Finished(Board board, Color turnColor) {
        super(board, turnColor);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("[ERROR] 이미 게임이 시작되었습니다.");
    }

    @Override
    public GameState finish() {
        throw new UnsupportedOperationException("[ERROR] 이미 게임이 끝났습니다.");
    }

    @Override
    public GameState move(Arguments arguments) {
        throw new UnsupportedOperationException("[ERROR] 지원하지 않는 명령입니다.");
    }

    @Override
    public boolean isRunnable() {
        return false;
    }
}
