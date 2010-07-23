package javamines.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javamines.model.BoardModel;
import javamines.model.ButtonState;
import javamines.view.BoardPanel;
import javamines.view.MineButton;

import javax.swing.JOptionPane;

/**
 * Board Controller
 * controls the toggle button events
 */
public class BoardController {
    
    private BoardModel boardModel;
    private BoardPanel boardPanel;

    /**
     * Initiates the board variable with the appropriate difficulty setting
     * 
     * @param boardModel
     * @param boardPanel
     */
    public BoardController(BoardModel boardModel, BoardPanel boardPanel) {
        this.boardModel = boardModel;
        this.boardPanel = boardPanel;

        this.boardModel.newGame();
        this.boardPanel.build();
        this.boardPanel.repaint();

        // listen for mouse clicks on the boardgrid
        this.boardPanel.addClickListener(new ToggleButtonListner());
    }

    private void showCell(MouseEvent e) {
        MineButton but = (MineButton) e.getSource();
        int[] coords = new int[2];

        coords = but.getCoords();

        if (but.getCurrentState() == ButtonState.DEFAULT || but.getCurrentState() == ButtonState.HOVER) {

            if (but.isMine()) {
                // game over
                boardModel.endGame();
                boardPanel.reDraw();
            	int timePlayed = boardModel.getTimePlayed();
                JOptionPane.showMessageDialog(null, "You lost. You played "+timePlayed+" seconds.", "You Lost" , JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                boardModel.reveal(coords[0], coords[1]);
                boardPanel.reDraw();

                if(boardModel.checkWon()) { // game finished
                	boardModel.endGame();
                    boardPanel.reDraw();
                	int timePlayed = boardModel.getTimePlayed();
                    JOptionPane.showMessageDialog(null, "Congratz, You Won! It took "+timePlayed+"seconds to complete the game", "You Won!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    class ToggleButtonListner implements MouseListener {

        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {
        	MineButton but = (MineButton) e.getSource();
        	
        	but.mouseEnter();
        }
        public void mouseExited(MouseEvent e) {
        	MineButton but = (MineButton) e.getSource();
        
        	but.mouseExit();
        }
        public void mousePressed(MouseEvent e) {
            MineButton but = (MineButton) e.getSource();

            if(e.getButton() == MouseEvent.BUTTON3) {
                if(but.getCurrentState() == ButtonState.DEFAULT || but.getCurrentState() == ButtonState.FLAGGED  || but.getCurrentState() == ButtonState.HOVER)
                    but.toggleFlagged();
            }
            else if(e.getButton() == MouseEvent.BUTTON1) {
                if(but.isFlagged()) {
                    but.setSelected(true);
                    return;
                }

                showCell(e);
            }
        }
        public void mouseReleased(MouseEvent e) {}
    }
}
