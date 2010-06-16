package javamines.view;

import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javamines.model.BoardModel;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private MineButton jButtons[];
    private BoardModel boardModel;
    private int maxSize;
    private boolean initialBuild = true;


    public BoardPanel() {
        // TODO: exception => model not recieved
    }

    /**
     * 
     * @param boardModel BoardModel
     */
    public BoardPanel(BoardModel boardModel) {
        this.boardModel = boardModel;
        this.maxSize = boardModel.getMaxSize();
        jButtons = new MineButton[maxSize * maxSize];

        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(maxSize);
        gridLayout.setColumns(maxSize);
        setLayout(gridLayout);
    }

    /**
     * 
     * @param e MousListener
     */
    public void addClickListener(MouseListener e) {
        for(MineButton but:jButtons)
            but.addMouseListener(e);
    }

    public void build() {
        int butCount = 0;

        for (int y = 0; y  < maxSize; y++) {
            for (int x = 0; x < maxSize; x++) {
                if(initialBuild) {
                    jButtons[butCount] = new MineButton();
                    jButtons[butCount].setCoords(x, y);
                    add(jButtons[butCount]);
                }
                jButtons[butCount].setText("");
                jButtons[butCount].setisMine(boardModel.isMine(x, y));
                jButtons[butCount].setGameValue(boardModel.getCellValue(x, y));
                jButtons[butCount].setEnabled(true);
                jButtons[butCount].setSelected(false);
                jButtons[butCount].unmark();

                butCount++;
            }
        }

        setEnabled(true);

        if(initialBuild)
            initialBuild = false;
        else
            repaint();
    }

    public void resetButtons_BAK() {
        int butCount = 0;

        for (int y = 0; y  < maxSize; y++) {
            for (int x = 0; x < maxSize; x++) {
                jButtons[butCount].setisMine(boardModel.isMine(x, y));
                jButtons[butCount].setGameValue(boardModel.getCellValue(x, y));
                jButtons[butCount].setText("");
                jButtons[butCount].setEnabled(true);
                jButtons[butCount].setSelected(false);
                jButtons[butCount].unmark();
                butCount++;
            }
        }

        repaint();
    }

    public void reDraw() {
        int[] coords;

        for (MineButton but:jButtons) {
            coords = but.getCoords();
            if(!boardModel.isRevealed(coords[0], coords[1]))
                continue;
            but.reveal();
        }
    }

    public void disableAllButtons() {
        for (MineButton but:jButtons) {
            but.setEnabled(false);
        }
    }

}
