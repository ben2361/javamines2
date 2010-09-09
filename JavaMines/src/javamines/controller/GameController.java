package javamines.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;

import javamines.model.BoardModel;
import javamines.model.Difficulty;
import javamines.view.BoardPanel;
import javamines.view.GameFrame;

/**
 * Game Controller
 * controller for:
 *   - difficulty settings
 *   - new game button
 *   - menubar
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

        // listen for mouse clicks
        this.gameFrame.addClickListener(new NewGameButtonListner());
        this.gameFrame.addDifficultyListener(new DiffChoiceListener());
        this.gameFrame.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
    }
    
    public GameController() throws Exception {
    	throw new Exception("GameController needs instances of: BoardModel, GameFrame and BoardPanel to function");
    }

    class NewGameButtonListner implements MouseListener {
        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {
        	if(e.getSource() instanceof JMenuItem) {
        		JMenuItem source = (JMenuItem) e.getSource();
        		
        		if(source == gameFrame.getMnuGameNewGame()) {
                    gameFrame.resetTimePlayed();
                    boardModel.newGame();
                    boardPanel.build();
        		}
        		else if(source == gameFrame.getMnuGameViewHighscores()) {
        			gameFrame.showHighscores();
        		}
        		else if(source == gameFrame.getMnuHelpAbout()) {
        			gameFrame.showAbout();
        		}
        	}

        }
        public void mouseReleased(MouseEvent e) {}
    }
    
    class DiffChoiceListener implements ActionListener, ItemListener {
		public void itemStateChanged(ItemEvent e) {}
		public void actionPerformed(ActionEvent e) {
			String diff = e.getActionCommand();
			Difficulty difficulty = Difficulty.fromString(diff);
			
			// change the difficulty in boardModel and create a new game
			boardModel.setDifficulty(difficulty);
            boardModel.newGame();
			// change the panel dimensions
			boardPanel.setPanelSize(boardModel.getMaxSize());
			// rebuild the gamefield with MineButtons
			boardPanel.setRebuild(true);
            boardPanel.build();
            
            // update the parent gameFrame with new dimensionx
            gameFrame.resetTimePlayed();
            gameFrame.setFrameSize();
            gameFrame.repaint();
		}
    }
}
