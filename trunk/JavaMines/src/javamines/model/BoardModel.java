package javamines.model;

/**
 * MineSweeper Business Logic
 */
public class BoardModel {

    private int maxSize;
    private int mines;
    
    private int[][] board;
    private boolean[][] checked;
    private boolean[][] revealed;

    public static final int MINEVALUE = -1;
    public static final int EMPTYVALUE = 0;

    private int revealedCount;

    
    public BoardModel(Difficulty diff) {

        switch(diff) {
            case MEDIUM:
                setMaxSize(9);
                setMines(15);
                break;
            case HARD:
                setMaxSize(9);
                setMines(10);
                break;
            case EASY:
                setMaxSize(9);
                setMines(9);
                break;
            default:
                System.out.println("Wrong difficultysetting");
                break;
        }
    }

    /**
     *
     * @param maxSize maximum dimension of the board
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    public int getMaxSize() {
        return maxSize;
    }

    /**
     *
     * @param mines number of mines to create
     */
    public void setMines(int mines) {
        this.mines = mines;
    }
    public int getMines() {
        return mines;
    }

    /**
     *
     * @return number of revealed fields
     */
    public int getRevealedCount() {
        int count = 0;
        
        for (int x = 0; x < maxSize; x++) {
            for (int y = 0; y < maxSize; y++) {
                if(revealed[x][y])
                    count++;
            }
        }
        
        return count;
    }
    
    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isRevealed(int x, int y) {
        return revealed[x][y];
    }

    /**
     * 
     * @param x
     * @param y
     * @return
     */
    public int getCellValue(int x, int y) {
        return board[x][y];
    }

    /**
     *
     * @return true if won, else false
     */
    public boolean checkWon() {
        return getRevealedCount() + getMines() == getMaxSize() * getMaxSize();
    }

    /**
     * generates the necessary number of mines
     * and initializes the array
     */
    public void newGame() {
        board = new int[maxSize][maxSize];
        revealed = new boolean[maxSize][maxSize];
        checked = new boolean[maxSize][maxSize];
        
        revealedCount = 0;

        int counter = 1;
        int x = 0, y = 0;

        while(counter <= mines) {
            x = (int) (Math.random() * maxSize);
            y = (int) (Math.random() * maxSize);

            if(board[x][y] != MINEVALUE) {
                // add mine
                board[x][y] = MINEVALUE;
                // add numbers
                addHintNumbers(x, y);
                
                counter++;
            }
        }
    }

    /**
     * add 8 numbers around the given coordinates
     * 
     * @param x
     * @param y
     */
    private void addHintNumbers(int x, int y) {
        for (int i2 = y - 1; i2 <= y + 1; i2++) {
            for (int i1 = x - 1; i1 <= x + 1; i1++) {
                if(i1 < 0 || i1 >= maxSize || i2 < 0 || i2 >= maxSize || board[i1][i2] == MINEVALUE)
                    continue;
                board[i1][i2]++;
            }
        }
    }

     /**
     *
     * @param coords array with x and y coordinates (0 = x coord, 1 = y coord)
     * @return true when game over, false when OK
     */
    public boolean makeMove(int[] coords) {
        int x = coords[0];
        int y = coords[1];

        if(board[x][y] == MINEVALUE)  {
            revealAll();
            return true;
        }

        reveal(x, y);

        return false;
    }

    /**
     * reveals the chosen field
     *
     * @param x
     * @param y
     */
    public void reveal(int x, int y) {
        revealedCount++;
        revealed[x][y] = true;

        if(board[x][y] != EMPTYVALUE)
            return;

        checked = new boolean[maxSize][maxSize];
        revealMore(x, y);
    }

    /**
     * reveals empty fields and numberfields when a empty field is chosen
     * this function calls itself recursively
     *
     * @param startX
     * @param startY
     */
    private void revealMore(int startX, int startY) {

        for (int x = startX - 1; x <= startX + 1; x++) {
            for (int y = startY - 1; y <= startY + 1; y++) {
                // x or y = start coords?
                if(x == startX && y == startY)
                    continue;
                
                // out of bounds?
                if(x < 0 || x > maxSize - 1 || y < 0 || y > maxSize - 1)
                    continue;
                // field already checked?
                if(checked[x][y])
                    continue;

                checked[x][y] = true;
                // call this function (recursively) when value is "empty"
                if(board[x][y] == EMPTYVALUE) 
                    revealMore(x, y);

                revealed[x][y] = true;
                revealedCount++;
            }
        }

    }

    /**
     * reveals all fields (mostly called when game over)
     */
    public void revealAll() {
        for (int x = 0; x < maxSize; x++) {
            for (int y = 0; y < maxSize; y++) {
                revealed[x][y] = true;
            }
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isMine(int x, int y) {
        if(board[x][y] == MINEVALUE)
            return true;
        
        return false;
    }

}
