package javamines.view;

import java.awt.Color;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javamines.model.ButtonState;


@SuppressWarnings("serial")
public class MineButton extends JButton {

    private boolean flagged;
    private int gameValue;
    private boolean isMine;
    private int[] coords;
    private ButtonState currentState;
    private ButtonState previousState;
    
    private final static String ICON_FLAG = "flag";
    private final static String ICON_HOVER = "hover";
    private final static String ICON_MINE = "mine";
    private final static String ICON_REVEALED = "revealed";
    private final static String ICON_DEFAULT = "default";


    public MineButton() {
        flagged = false;
        isMine = false;
        coords = new int[2];
        setButIcon(ButtonState.DEFAULT);
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        //flagButton();
    }
    
    /**
     * 
     * @param butState
     */
    private void setButIcon(ButtonState butState) {
    	previousState = currentState;
    	currentState = butState;
    	String imgLocation = new String();
    	  
    	switch(butState) {
    	case REVEALED:
    		imgLocation = "/img/but_"+ICON_REVEALED+".png";
    		break;
    	case HOVER:
    		imgLocation = "/img/but_"+ICON_HOVER+".png";
    		break;
    	case FLAGGED:
    		imgLocation = "/img/but_"+ICON_FLAG+".png";
    		break;
    	case MINE:
    		imgLocation = "/img/but_"+ICON_MINE+".png";
    		break;
    	case NUMBER:
    		imgLocation = "/img/but_"+getGameValue()+".png";
    		break;
    	case DEFAULT:
		default:
			imgLocation = "/img/but_"+ICON_DEFAULT+".png";
			break;
    	}
    	
    	URL url = this.getClass().getResource(imgLocation);
    	ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(url));
		setIcon(img);
    }

    public int getGameValue() {
        return gameValue;
    }
    
    public void mouseEnter() {
    	if(getCurrentState() == ButtonState.DEFAULT)
    		setButIcon(ButtonState.HOVER);
    }
    public void mouseExit() {
    	if(getCurrentState() == ButtonState.HOVER)
    		setButIcon(ButtonState.DEFAULT);
    }

    /**
     * 
     * @param gameValue
     */
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
    public void toggleFlagged() {
        flagged = !flagged;
        flagButton();
    }
    
    public void reset() {
        setEnabled(true);
        setSelected(false);
        flagged = false;
        setButIcon(ButtonState.DEFAULT);
    }
    
 
    public ButtonState getCurrentState() {
    	return currentState;
    }
    
    
    /**
     * 
     * @param isMine
     */
    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isFlagged() {
        return flagged;
    }
    public boolean isMine() {
        return isMine;
    }

    public void reveal() {
        if(isMine)
            setButIcon(ButtonState.MINE);
        else if(gameValue == 0)
        	setButIcon(ButtonState.REVEALED);
        else
        	setButIcon(ButtonState.NUMBER);

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
        if(flagged)
        	setButIcon(ButtonState.FLAGGED);
        else
        	setButIcon(previousState);
    }
}
