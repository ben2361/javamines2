package javamines.view;

import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javamines.model.BoardModel;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

    private BoardModel boardModel;

    private int maxSize;
    private boolean rebuild = false, initBuild = true;
    private MineButton jButtons[];
    private GridLayout gridLayout;
    
    private MouseListener mouseListener;

    /**
     * 
     * @param boardModel BoardModel
     */
    public BoardPanel(BoardModel boardModel) {
        this.boardModel = boardModel;

        gridLayout = new GridLayout();
        setPanelSize(boardModel.getMaxSize());
        
        setLayout(gridLayout);
    }

    public BoardPanel() throws Exception {
        throw new Exception("BoardPanel needs boardModel to function");
    }
    
    /**
     * 
     * @param maxSize
     */
    public void setPanelSize(int maxSize) {
    	this.maxSize = maxSize;
    	
        gridLayout.setRows(maxSize);
        gridLayout.setColumns(maxSize);
        
        removeAll();
        
        jButtons = new MineButton[maxSize * maxSize];
    }

    public void build() {
        int butCount = 0;

        for (int y = 0; y  < maxSize; y++) {
            for (int x = 0; x < maxSize; x++) {
                if(initBuild || rebuild) {
                    jButtons[butCount] = new MineButton();
                    jButtons[butCount].setCoords(x, y);
                    add(jButtons[butCount]);
                }
                
                jButtons[butCount].setIsMine(boardModel.isMine(x, y));
                jButtons[butCount].setGameValue(boardModel.getCellValue(x, y));
                jButtons[butCount].reset();

                butCount++;
            }
        }

        setEnabled(true);
        
        if(initBuild)
        	initBuild = false;

        if(rebuild) {
        	rebuild = false;
        	addListeners();
        }
        else {
           // repaint();
        }
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

	public void setRebuild(boolean rebuild) {
		this.rebuild = rebuild;
	}

	/**
     * 
     * @param e MousListener
     */
    public void addClickListener(MouseListener e) {
    	mouseListener = e;
    	
    	addListeners();
    }

	private void addListeners() {
        for(MineButton but:jButtons)
            but.addMouseListener(mouseListener);
	}
}
