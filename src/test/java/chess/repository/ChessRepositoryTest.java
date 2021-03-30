package chess.repository;

import chess.domain.history.History;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ChessRepositoryTest {

    private ChessRepository chessRepository;
    private Connection connection;
    private History testHistory = new History("3e", "4a", "BLACK");

    @BeforeEach
    void setup() {
        connection = ConnectionManager.makeConnection();
        chessRepository = new ChessRepository(connection);
    }

    @AfterEach
    void teardown() throws SQLException {
        connection.close();
    }

    @DisplayName("history를 추가 및 조한다.")
    @Test
    void insertHistory() throws SQLException {
        chessRepository.insertHistory("3e", "4a", "BLACK");

        History result = chessRepository.findHistoryById(1)
                .get();

        assertThat(result).isEqualTo(testHistory);
    }
}
