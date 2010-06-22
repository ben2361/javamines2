package javamines.view;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;


@SuppressWarnings("serial")
public class GameFrame extends JFrame {

    private JPanel contentPanel;
    private BoardPanel boardPanel;
    private JToolBar toolbar;
    private JMenuBar menuBar;
    private JLabel timeLabel;
    private JMenuItem mnuGameNewGame;
    
    
    /**
     * 
     * @param boardPanel BoardPanel
     */
    public GameFrame(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        
        setSize(178, 234);
        //setSize((int) 45 * boardModel.getMaxSize(), (int) 45 * boardModel.getMaxSize());
        setContentPane(contentPanel);
        setTitle("Minesweeper v1.0");
        setVisible(true);
        setResizable(false);
        
        build();
    }

    public void build() {
        // menubar 
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnuGame = new JMenu("Game");
        mnuGameNewGame = new JMenuItem("New Game");
        //mnuGameNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        mnuGame.add(mnuGameNewGame);
        
        mnuGame.addSeparator();
        
        // diff menu
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem mnuGameDiffEasy = new JRadioButtonMenuItem("Easy");
        JRadioButtonMenuItem mnuGameDiffMedium = new JRadioButtonMenuItem("Medium");
        JRadioButtonMenuItem mnuGameDiffHard = new JRadioButtonMenuItem("Hard");
        
        group.add(mnuGameDiffEasy);
        group.add(mnuGameDiffMedium);
        group.add(mnuGameDiffHard);
        
        mnuGame.add(mnuGameDiffEasy);
        mnuGame.add(mnuGameDiffMedium);
        mnuGame.add(mnuGameDiffHard);
        
        menuBar.add(mnuGame);

        // toolbar met newgame button
        toolbar = new JToolBar();
        toolbar.setFloatable(false);
        
        timeLabel = new JLabel("0");
        toolbar.add(new JLabel("time played: "));
        toolbar.add(timeLabel);

        contentPanel.add(toolbar, BorderLayout.NORTH);
        contentPanel.add(boardPanel, BorderLayout.CENTER);
    }
    
    /**
     * 
     * @param e MouseListener
     */
    public void addClickListener(MouseListener e) {
    	mnuGameNewGame.addMouseListener(e);
    }
}
