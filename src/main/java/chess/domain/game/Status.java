package chess.domain.game;

import java.util.List;

import chess.domain.board.Board;
import chess.domain.Color;
import chess.dto.Response;
import chess.dto.ScoreResponse;

public class Status extends Started {

    public Status(Board board, Color turnColor) {
        super(board, turnColor);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException("[ERROR] 게임이 이미 시작되었습니다.");
    }

    @Override
    public GameState finish() {
        return new Finished(board, turnColor);
    }

    @Override
    public GameState move(List<String> arguments) {
        if (!board.move(arguments, turnColor)) {
            return new Running(board, turnColor.toggle());
        }
        return new Finished(board, turnColor);
    }

    @Override
    public GameState status() {
        return new Status(board, turnColor);
    }

    @Override
    public boolean isRunnable() {
        return true;
    }

    @Override
    public Response getResponse() {
        return ScoreResponse.of(board.calculateScore());
    }
}
