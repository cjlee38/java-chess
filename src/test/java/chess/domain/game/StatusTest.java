package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;
import chess.domain.piece.King;
import chess.domain.piece.Queen;
import chess.dto.Response;
import chess.dto.ScoreResponse;

class StatusTest {

    @Test
    @DisplayName("게임 시작시 에러가 발생한다.")
    void startTest() {
        GameState gameState = new Status(BoardFixtures.INITIAL, Color.WHITE);

        assertThatThrownBy(gameState::start)
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("게임 종료시 종료 상태로 변한다.")
    void finishTest() {
        GameState gameState = new Status(BoardFixtures.INITIAL, Color.WHITE);

        assertThat(gameState.finish()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("move 명령시 running 상태로 변한다.")
    void moveToRunningTest() {
        List<String> arguments = List.of("a2", "a3");
        GameState gameState = new Status(BoardFixtures.INITIAL, Color.WHITE);

        GameState movedState = gameState.move(arguments);

        assertThat(movedState).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("move 명령시 king이 죽으면 종료 상태로 변한다.")
    void moveToFinishTest() {
        List<String> arguments = List.of("e1", "e8");
        GameState gameState = new Status(BoardFixtures.create(Map.of(
            Point.of("e8"), new King(Color.BLACK),
            Point.of("e1"), new Queen(Color.WHITE)
        )), Color.WHITE);

        GameState movedState = gameState.move(arguments);

        assertThat(movedState).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("status 상태로 변한다.")
    void turnIntoStatusState() {
        GameState state = new Status(BoardFixtures.EMPTY, Color.WHITE);

        GameState changed = state.status();

        assertThat(changed).isInstanceOf(Status.class);
    }

    @Test
    @DisplayName("status 상태는 실행가능한 상태이다.")
    void runningIsRunnable() {
        GameState state = new Status(BoardFixtures.EMPTY, Color.WHITE);

        boolean isRunnable = state.isRunnable();

        assertThat(isRunnable).isTrue();
    }

    @Test
    @DisplayName("status 상태에서는 응답을 얻을 수 있다.")
    void gettingResponse() {
        GameState state = new Status(BoardFixtures.EMPTY, Color.WHITE);

        Response response = state.getResponse();

        assertThat(response).isInstanceOf(ScoreResponse.class);
    }
}
