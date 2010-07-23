package javamines.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

import javamines.model.BoardModel;
import javamines.model.Difficulty;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;

import nvl.highscores.HighScores;


@SuppressWarnings("serial")
public class GameFrame extends JFrame implements Observer {

    private BoardPanel boardPanel;
    private BoardModel boardModel;
    
    private JPanel contentPanel;
    private JToolBar toolbar;
    private JMenuBar menuBar;
    private JLabel timeLabel;
    
    private JMenuItem mnuGameNewGame;
    private JMenuItem mnuGameViewHighscores;
    private JMenuItem mnuHelpAbout;
    
    private ButtonGroup diffGroup;
    private JRadioButtonMenuItem mnuGameDiffEasy;
    private JRadioButtonMenuItem mnuGameDiffMedium;
    private JRadioButtonMenuItem mnuGameDiffHard;
    
    /**
     * 
     * @param boardPanel BoardPanel
     */
    public GameFrame(BoardPanel boardPanel, BoardModel boardModel) {
        this.boardPanel = boardPanel;
        this.boardModel = boardModel;
        
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        
        setFrameSize();
        
        setContentPane(contentPanel);
        setTitle("Minesweeper v0.4");
        setVisible(true);
        setResizable(false);
        
        build();
    }
    
    public void setFrameSize() {
    	setSize((boardModel.getMaxSize() * 17) + 25, (boardModel.getMaxSize() * 17) + 75);
    }
    
    public GameFrame() throws Exception {
    	throw new Exception("GameFrame needs instances of BoardPanel and BoardModel to function");
    }

    public void build() {
        // menubar 
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Game Menu
        JMenu mnuGame = new JMenu("Game");
        mnuGameNewGame = new JMenuItem("New Game");
        //mnuGameNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        mnuGame.add(mnuGameNewGame);
        
        mnuGame.addSeparator();
        mnuGameViewHighscores = new JMenuItem("View Highscores");
        mnuGame.add(mnuGameViewHighscores);
        
        mnuGame.addSeparator();
        
        // diff menu
        diffGroup = new ButtonGroup();

        mnuGameDiffEasy = new JRadioButtonMenuItem("Easy");
        mnuGameDiffMedium = new JRadioButtonMenuItem("Medium");
        mnuGameDiffHard = new JRadioButtonMenuItem("Hard");
        
        diffGroup.add(mnuGameDiffEasy);
        diffGroup.add(mnuGameDiffMedium);
        diffGroup.add(mnuGameDiffHard);
        
        // select the appropriate diff radio
        Difficulty diff = boardModel.getDifficulty(); 
        
        if(diff == Difficulty.EASY)
        	mnuGameDiffEasy.setSelected(true);
        else if(diff == Difficulty.MEDIUM)
        	mnuGameDiffMedium.setSelected(true);
        else if(diff == Difficulty.HARD)
        	mnuGameDiffHard.setSelected(true);
        
        mnuGame.add(mnuGameDiffEasy);
        mnuGame.add(mnuGameDiffMedium);
        mnuGame.add(mnuGameDiffHard);

        // About Menu
        JMenu mnuHelp = new JMenu("Help");
        mnuHelpAbout = new JMenuItem("About");
        mnuHelp.add(mnuHelpAbout);
        
        // add menus to menubar
        menuBar.add(mnuGame);
        menuBar.add(mnuHelp);

        // toolbar met timelabel
        toolbar = new JToolBar();
        toolbar.setFloatable(false);
        
        timeLabel = new JLabel("0");
        toolbar.add(new JLabel("time played: "));
        toolbar.add(timeLabel);

        contentPanel.add(toolbar, BorderLayout.NORTH);
        contentPanel.add(boardPanel, BorderLayout.CENTER);
    }
    
    /*
     * (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    public void update(Observable obs, Object obj) {
    	if(obs == boardModel)
    		timeLabel.setText(String.valueOf(boardModel.getTimePlayed()));
    }
    
    public void resetTimePlayed() {
    	timeLabel.setText(String.valueOf(0));
    }
    
    /**
     * 
     * @param e MouseListener
     */
    public void addClickListener(MouseListener e) {
    	mnuGameNewGame.addMouseListener(e);
    	mnuGameViewHighscores.addMouseListener(e);
    	mnuHelpAbout.addMouseListener(e);
    }

    /**
     * 
     * @param diffChoiceListener
     */
	public void addDifficultyListener(ActionListener diffChoiceListener) {
		mnuGameDiffEasy.addActionListener(diffChoiceListener);
		mnuGameDiffMedium.addActionListener(diffChoiceListener);
		mnuGameDiffHard.addActionListener(diffChoiceListener);
	}

	public JMenuItem getMnuGameNewGame() {
		return mnuGameNewGame;
	}

	public JMenuItem getMnuGameViewHighscores() {
		return mnuGameViewHighscores;
	}

	public JMenuItem getMnuHelpAbout() {
		return mnuHelpAbout;
	}

	public void showHighscores() {
    	// show all highscores
    	try {
			HighScores.showAll(BoardModel.GAMENAME, Difficulty.EASY, false);
	    	HighScores.showAll(BoardModel.GAMENAME, Difficulty.MEDIUM, false);
	    	HighScores.showAll(BoardModel.GAMENAME, Difficulty.HARD, false);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showAbout() {
		JOptionPane.showMessageDialog(null, "<html>JavaMines is an opensource MineSweeper clone written by Nik Van Looy.<br/><br/>" +
				"Get the source from http://code.google.com/p/javamines2</html>", "JavaMines", JOptionPane.PLAIN_MESSAGE);
	}
	
}
