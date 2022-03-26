package chess.controller;

import chess.domain.game.Game;
import chess.dto.Request;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputVIew) {
        this.inputView = inputView;
        this.outputView = outputVIew;
    }

    public void run() {
        Game game = new Game();
        outputView.printIntroduction();
        playGame(game);
        outputView.printEnd();
    }

    private void playGame(Game game) {
        tryExecute(game);
        if (!game.isRunnable()) {
            return;
        }
        outputView.printResponse(game.getResponse());
        playGame(game);
    }

    private void tryExecute(Game game) {
        try {
            game.execute(Request.of(inputView.inputCommand()));
        } catch (RuntimeException e) {
            outputView.printException(e);
        }
    }
}
