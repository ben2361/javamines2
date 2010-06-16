package javamines.view;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class GameFrame extends JFrame {

    private JPanel contentPanel;
    private BoardPanel boardPanel;
    private JToolBar toolbar;
    private JButton btnNewGame;
    private JMenuBar menuBar;
    

    public GameFrame(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        
        setSize(395, 395);
        //setSize((int) 45 * boardModel.getMaxSize(), (int) 45 * boardModel.getMaxSize());
        setContentPane(contentPanel);
        setTitle("Minesweeper v1.0");
        setVisible(true);

        build();
    }

    public void build() {
        btnNewGame = new JButton();
        btnNewGame.setText("New Game");

        // menubar 
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // diff menu
        //menuBar.add();

        // toolbar met newgame button
        toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(btnNewGame);

        contentPanel.add(toolbar, BorderLayout.NORTH);
        contentPanel.add(boardPanel, BorderLayout.CENTER);
    }
    
    public void addClickListener(MouseListener e) {
        btnNewGame.addMouseListener(e);
    }
}
