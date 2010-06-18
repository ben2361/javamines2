package javamines.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javamines.model.ButtonState;


public class MineButton extends JToggleButton {

    private boolean flagged;
    private int gameValue;
    private boolean isMine;
    private int[] coords;

    private final static String ICON_FLAG = "flag";
    private final static String ICON_HOVER = "hover";
    private final static String ICON_MINE = "mine";
    private final static String ICON_REVEALED = "revealed";
    private final static String ICON_DEFAULT = "default";

    private ImageIcon myIcon = new ImageIcon("images/myPic.gif");


    public MineButton() {
        flagged = false;
        isMine = false;
        coords = new int[2];
        setButIcon(ButtonState.DEFAULT);
        flagButton();
    }
    
    private void setButIcon(ButtonState butState) {
    	ImageIcon icon = null;
    	
    	switch(butState) {
    	case HOVER:
    		icon = new ImageIcon("img/but_"+ICON_HOVER+".png");
    		break;
    	case FLAGGED:
    		icon = new ImageIcon("img/but_"+ICON_FLAG+".png");
    		break;
    	case MINE:
    		icon = new ImageIcon("img/but_"+ICON_MINE+".png");
    		break;
    	case NUMBER:
    		icon = new ImageIcon("img/but_"+getGameValue()+".png");
    		break;
    	case DEFAULT:
		default:
    		icon = new ImageIcon("img/but_"+ICON_DEFAULT+".png");
			break;
    	}
    	
		setIcon(icon);
    }

    public int getGameValue() {
        return gameValue;
    }

    public void setGameValue(int gameValue) {
        this.gameValue = gameValue;
    }

    public void flag() {
        flagged = true;
        flagButton();
    }
    public void unflag() {
        flagged = false;
        flagButton();
    }
    public void toggleMarked() {
        flagged = !flagged;
        flagButton();
    }
    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }
    public void setisMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isMarked() {
        return flagged;
    }
    public boolean isMine() {
        return isMine;
    }

    public void reveal() {
        /*if(isMine)
            this.setText(MINETEXT);
        else if(gameValue == 0)
            this.setText(EMPTYTEXT);
        else
            this.setText(String.valueOf(gameValue));*/

        setEnabled(false);
        setSelected(true);
    }

    /**
     *
     * @param x
     * @param y
     */
    public void setCoords(int x, int y){
        coords[0] = x;
        coords[1] = y;
    }
    public int[] getCoords() {
        return coords;
    }

    private void flagButton() {
        /*if(flagged)
            this.setText(MARKEDTEXT);
        else
            this.setText("");*/
    }
}
