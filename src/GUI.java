import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GUI extends JFrame{
	
	private JPanel boardPanel;

	
	private JMenuBar menuBar;
	private JMenu mnOptions;
	
	 private static JLabel[][] squares;
	
	
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		this.setBounds(100, 100, 1096, 815);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		boardPanel = new JPanel();
		boardPanel.setBounds(0, 16, 720, 736);
		boardPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		boardPanel.setPreferredSize(new Dimension(720,720));
		this.getContentPane().add(boardPanel);
		boardPanel.setLayout(new GridLayout(KnightMove.SIZE,KnightMove.SIZE,0,0));




		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
	
		
		initializeBoard();
	}
	
	private void initializeBoard() {
		squares = new JLabel[KnightMove.SIZE][KnightMove.SIZE];
		for(int i = 0; i < KnightMove.SIZE; i++) {
			for(int y = 0; y < KnightMove.SIZE; y++) {
				squares[i][y] = new JLabel();
				squares[i][y].setOpaque(true);
				squares[i][y].setSize(new Dimension(KnightMove.SIZE*KnightMove.SIZE,KnightMove.SIZE*KnightMove.SIZE));
				squares[i][y].setHorizontalAlignment(SwingConstants.CENTER);
				
				if((i+y) % 2 == 0)
					squares[i][y].setBackground(new Color(184,134,11));
				else
					squares[i][y].setBackground(new Color(255,255,255));
					
				boardPanel.add(squares[i][y]);
			}
			
		}
		System.out.println("init board");
	}
	
	public void setVis(boolean vis) {
		setVisible(vis);
	}
	
	public void setNumbers(int[][] numbers) {
		System.out.println("her");
		for(int i = 0; i < squares.length; i++) {
			for(int y = 0; y < squares[0].length; y++) {
				squares[i][y].setText("" + numbers[i][y]);
				squares[i][y].setFont(new Font("Serif", Font.PLAIN, 30));
			}
		}
	}
}
