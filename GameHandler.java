import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
// import java.util.Date;
// import java.text.DateFormat;
// import java.text.SimpleDateFormat;

public class GameHandler extends JFrame implements MouseListener, ActionListener {
	static final int PLAYER_VS_PLAYER = 12345, PLAYER_VS_AI = 23456;
	int currentlyPlayed = 0;
	Board mainBoard = null;
	static GameMenu gameMenu = new GameMenu();
	int player = 0;
	Timer timer = new Timer(2000, this);//ai listener
	boolean gamePlaying = false;
	AI ai = new AI(2);// enter in parameter player number for ai
	//AI ai2 = new AI(2);
	public GameHandler() {
		
		super("Chain Reaction");
		setIconImage(new ImageIcon("Icon.png").getImage());
		setSize(600,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// printMenu();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//setLocation(100, 100);
		starter();
		//startGame(PLAYER_VS_AI);
		timer.start();
		setUndecorated(true);
		setVisible(true);
	}
	
	public void starter() {
		
		getContentPane().removeAll(); // do not remove
		repaint(); // do not remove
		revalidate(); // do not remove
		gameMenu.setGameHandler(this);
		gameMenu.printMenu();
		repaint(); // do not remove
		revalidate(); // do not remove
	}
	
	/*public void printMenu(){
		getContentPane().removeAll();//do not remove
		repaint();//do not remove
		revalidate();//do not remove
		JButton pvp = new JButton("Player vs. player");
		JButton pva = new JButton("Player vs. ai");
		setLayout(new FlowLayout());
		add(pvp);
		add(pva);
		pvp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startGame(PLAYER_VS_PLAYER, 0);
			}
		}
		);
		pva.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getContentPane().removeAll();
				repaint();
				revalidate();
				JButton p1B = new JButton("Player 1");
				JButton p2B = new JButton("Player 2");
				add(p1B);
				add(p2B);
				repaint();
				revalidate();
				p1B.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						startGame(PLAYER_VS_AI, 2);
					}
				}
				);
				p2B.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						startGame(PLAYER_VS_AI, 1);
					}
				}
				);
			}
		}
		);
		repaint();//do not remove
		revalidate();//do not remove
	}*/
	
	public void startGame(int gameMode, int AIplayer, boolean isLoad, String pattern){
		gamePlaying = true;
		getContentPane().removeAll();
		repaint();
		revalidate();
		//setLayout(new BorderLayout());
		setLayout(null);
		mainBoard = null;
		mainBoard = new Board(this);
		if(isLoad) {
			int c = 0;
			for(int d = 0; d < pattern.length() + 4; d+=4) {
				if(d != 0) {
					mainBoard.previousGame(pattern.substring(c, d));
					c = d;
				}
			}
		}
		player = 1;
		for(int i = 0; i < mainBoard.tile.length; i++){
			for(int j = 0; j < mainBoard.tile[i].length; j++){
				mainBoard.tile[i][j].addMouseListener(this);
			}
		}
		if(gameMode == PLAYER_VS_AI){
			ai.enabled = false;
			ai = null;
			ai = new AI(AIplayer);
			ai.enabled = true;
		}
		currentlyPlayed = gameMode;
		JLabel gamemode = new JLabel((gameMode==PLAYER_VS_AI?"Player VS. AI":"Player VS. Player"), SwingConstants.CENTER);
		JPanel title = new JPanel();
		title.setOpaque(false);
		//title.setBackground(Color.BLACK);
		title.add(gamemode);
		gamemode.setForeground(Color.CYAN);
		gamemode.setBackground(Color.BLACK);
		gamemode.setFont(new Font("OCR A Extended", Font.BOLD, 12));
		StatusPanel statusPanel = new StatusPanel(this, gameMode, AIplayer);
		this.add(mainBoard, BorderLayout.CENTER);
		this.add(title, BorderLayout.NORTH);
		this.add(statusPanel, BorderLayout.EAST);
		//this.add(save, BorderLayout.NORTH);
		JLabel background = new JLabel();
		background.setIcon(new ImageIcon(new ImageIcon("background.png").getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT)));
		mainBoard.setBounds(1,25, 474, 474);
		title.setBounds(0,0,600,25);
		statusPanel.setBounds(475,25,125,475);
		background.setBounds(0,0,600,500);
		this.add(background);
		repaint();
		revalidate();
	}
	
	
	public void actionPerformed(ActionEvent e){
		if(Board.ballMoveCtr > 0 || !gamePlaying){
			return;
		}
		if(ai.player == player && !ai.findingMove && ai.enabled){
			new Thread(){
				public void run(){
					//try{
						System.out.println((Runtime.getRuntime().maxMemory()/1024/1024) + " " + (Runtime.getRuntime().totalMemory()/1024/1024) + " " + (Runtime.getRuntime().freeMemory()/1024/1024));
						ai.findMove(mainBoard);
						System.out.println((Runtime.getRuntime().maxMemory()/1024/1024) + " " + (Runtime.getRuntime().totalMemory()/1024/1024) + " " + (Runtime.getRuntime().freeMemory()/1024/1024));
						//Thread.sleep(250);
					//}catch(InterruptedException iEx){}
				}
			}.start();
			stackOverflow++;
			//mainBoard.move(move[1], move[0], false, player);
			if(player == 1){
				player = 2;
			}else if(player == 2){
				player = 1;
			}
		}/*else if(ai2.player == player && !ai2.findingMove && ai.enabled){
			ai2.findMove(mainBoard);
			stackOverflow++;
			//mainBoard.move(move[1], move[0], false, player);
			if(player == 1){
				player = 2;
			}else if(player == 2){
				player = 1;
			}
		}*/
	}
	
	int stackOverflow = 0;
	public void mouseClicked(MouseEvent e){
		if(Board.ballMoveCtr > 0 || ai.findingMove || !gamePlaying){
			return;
		}
		for(int i = 0; i < mainBoard.tile.length; i++){
			for(int j = 0; j < mainBoard.tile[i].length; j++){
				if(e.getSource() == mainBoard.tile[i][j] && (mainBoard.tile[i][j].player == player || mainBoard.tile[i][j].player == 0) && gamePlaying){
					try{
						stackOverflow++;
						mainBoard.move(i, j, false, player);
						if(player == 1){
							player = 2;
						}else if(player == 2){
							player = 1;
						}
					}catch(StackOverflowError ex){
						System.out.println("StackOverflow after " + stackOverflow + " clicks");
					}
					//System.out.println("Clicked (" + j + ", " + i + "), (" + mainBoard.tile[i][j].getLocation().x + ", " + mainBoard.tile[i][j].getLocation().y + ")");
				}
			}
		}
	}
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	
	public static void main(String[] args){
		
		Runnable runner = new Runnable() {
			public void run() {
			new GameHandler();
			gameMenu.printMenu();
			}
		};
		EventQueue.invokeLater(runner); // purpose: to avoid ClassCastException
	}
}