package chess.domain.piece.strategy;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BishopStrategy extends MovingStrategy {
    private static final List directions = Direction.diagonalDirection();

    @Override
    protected void checkDirection(Position source, Position target) {
        Direction direction = Direction.getDirection(source, target);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("해당 방향으로 이동할 수 없습니다.");
        }
    }

    @Override
    protected void checkObstacle(Position source, Position target, Map<Position, Team> boardDto) {
        Direction direction = Direction.getDirection(source, target);
        Position pathPosition = source.plusDirection(direction);
        while (!pathPosition.equals(target)) {
            if (!Objects.isNull(boardDto.get(pathPosition))) {
                throw new IllegalArgumentException("해당 방향에 장애물이 존재합니다.");
            }
            pathPosition = pathPosition.plusDirection(direction);
        }
    }
}
