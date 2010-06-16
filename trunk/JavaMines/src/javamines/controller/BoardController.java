package javamines.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javamines.model.BoardModel;
import javamines.view.BoardPanel;
import javamines.view.MineButton;

import javax.swing.JOptionPane;

/**
 * Board Controller
 * controls the togglebutton events
 */
public class BoardController {
    
    private BoardModel boardModel;
    private BoardPanel boardPanel;

    /**
     * Initiates the board variable with the apropreate difficulty setting
     *
     * @param diff difficulty setting
     */
    public BoardController(BoardModel boardModel, BoardPanel boardPanel) {
        this.boardModel = boardModel;
        this.boardPanel = boardPanel;

        this.boardModel.newGame();
        this.boardPanel.build();
        this.boardPanel.repaint();

        // listen for mouseclicks on the boardgrid
        this.boardPanel.addClickListener(new ToggleButtonListner());
    }

    private void showCell(MouseEvent e) {
        MineButton but = (MineButton) e.getSource();
        int[] coords = new int[2];

        coords = but.getCoords();

        if (but.isEnabled()) {
            but.setEnabled(false);

            if (but.isMine()) {
                // game over
                boardModel.revealAll();
                boardPanel.reDraw();
                but.setEnabled(false);
                JOptionPane.showMessageDialog(null, "You lost", "You Lost!", JOptionPane.INFORMATION_MESSAGE);
                //disableBoard();
            }
            else {
                boardModel.reveal(coords[0], coords[1]);
                boardPanel.reDraw();

                if(boardModel.checkWon()) {
                    boardPanel.disableAllButtons();
                    JOptionPane.showMessageDialog(null, "Congratz, You Won!", "You Won!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    class ToggleButtonListner implements MouseListener {

        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {
            MineButton but = (MineButton) e.getSource();

            if(e.getButton() == MouseEvent.BUTTON3) {
                if(but.isEnabled())
                    but.toggleMarked();
            }
            else if(e.getButton() == MouseEvent.BUTTON1) {
                if(but.isMarked()) {
                    but.setSelected(true);
                    return;
                }

                showCell(e);
            }
        }
        public void mouseReleased(MouseEvent e) {}
    }
}
