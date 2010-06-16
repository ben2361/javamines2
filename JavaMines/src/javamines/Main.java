/**
 * JavaMines
 * 
 * basic mines game made in java with SWING
 * 
 * @author Nik Van Looy
 */

package javamines;

import javamines.controller.BoardController;
import javamines.controller.GameController;
import javamines.model.BoardModel;
import javamines.model.Difficulty;
import javamines.view.BoardPanel;
import javamines.view.GameFrame;

public class Main {

    public static void main(String[] args) {
        Difficulty diff = Difficulty.MEDIUM;

        BoardModel boardModel = new BoardModel(diff);
        BoardPanel boardPanel = new BoardPanel(boardModel);
        GameFrame gameFrame = new GameFrame(boardPanel);

        new BoardController(boardModel, boardPanel);
        new GameController(boardModel, gameFrame, boardPanel);

        gameFrame.repaint();
        gameFrame.validate();
        //game.play();
    }

}
