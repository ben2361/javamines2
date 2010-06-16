package javamines.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JToggleButton;


public class MineButton extends JToggleButton {

    private boolean marked;
    private int gameValue;
    private boolean isMine;
    private int[] coords;

    private final static String MARKEDTEXT = "!";
    private final static String MINETEXT = "X";
    private final static String EMPTYTEXT = " ";

    private final static Font fnt = new Font(null, Font.BOLD, 12);


    public MineButton() {
        marked = false;
        isMine = false;
        coords = new int[2];
        setFont(fnt);
        setForeground(Color.RED);
        setBackground(Color.WHITE);
        markButtonText();
    }

    public int getGameValue() {
        return gameValue;
    }

    public void setGameValue(int gameValue) {
        this.gameValue = gameValue;
    }

    public void mark() {
        marked = true;
        markButtonText();
    }
    public void unmark() {
        marked = false;
        markButtonText();
    }
    public void toggleMarked() {
        marked = !marked;
        markButtonText();
    }
    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }
    public void setisMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isMarked() {
        return marked;
    }
    public boolean isMine() {
        return isMine;
    }

    public void reveal() {
        if(isMine)
            this.setText(MINETEXT);
        else if(gameValue == 0)
            this.setText(EMPTYTEXT);
        else
            this.setText(String.valueOf(gameValue));

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

    private void markButtonText() {
        if(marked)
            this.setText(MARKEDTEXT);
        else
            this.setText("");
    }
}
