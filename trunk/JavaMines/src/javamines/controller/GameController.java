package javamines.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javamines.model.BoardModel;
import javamines.view.BoardPanel;
import javamines.view.GameFrame;

/**
 * Game Controller
 * controller for difficulty settings and new game button
 */
public class GameController {

    private BoardModel boardModel;
    private GameFrame gameFrame;
    private BoardPanel boardPanel;

    /**
     * Initiates the board variable with the appropriate difficulty setting
     * 
     * @param boardModel
     * @param gameFrame
     * @param boardPanel
     */
    public GameController(BoardModel boardModel, GameFrame gameFrame, BoardPanel boardPanel) {
        this.boardModel = boardModel;
        this.gameFrame = gameFrame;
        this.boardPanel = boardPanel;

        // listen for mouse clicks on newgame button
        this.gameFrame.addClickListener(new NewGameButtonListner());
        this.gameFrame.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
    }

    class NewGameButtonListner implements MouseListener {

        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {
            boardModel.newGame();
            boardPanel.build();
        }
        public void mouseReleased(MouseEvent e) {}
    }
    
}
