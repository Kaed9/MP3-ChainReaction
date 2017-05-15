import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Board extends JPanel implements ActionListener{
	GameHandler handler;
	GameMenu gameMenu = new GameMenu();
	public static int width = 6, height = 6;
	Tile[][] tile = new Tile[height][width];
	Timer timer = new Timer(10, this);
	boolean foundWinner = false;
	
	static Icon[][] ballIcon = new Icon[2][5];
	private String[][] iconNames = {{"0.png", "1.png", "2.png", "3.png", "4.png"}, {"0.png", "1b.png", "2b.png", "3b.png", "4b.png"}};
	
	public Board(GameHandler handler){
		this();
		this.handler = handler;
	}
	
	public Board(){
		try{
			pImage[0] = ImageIO.read(new File("p1.png"));
			pImage[1] = ImageIO.read(new File("p2.png"));
		}catch(IOException ex){}
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 5; j++){
				ballIcon[i][j] = new ImageIcon(getClass().getResource(iconNames[i][j]));
			}
		}
		
		setBackground(Color.BLACK);
		setLayout(new GridLayout(tile.length, tile[0].length));
		for(int i = 0; i < tile.length; i++){
			for(int j = 0 ; j < tile[i].length; j++){
				tile[i][j] = new Tile();
				tile[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.CYAN));
				tile[i][j].setBackground(Color.BLACK);
				tile[i][j].setIcon(ballIcon[tile[i][j].player][tile[i][j].ctr]);
				add(tile[i][j]);
			}
		}
		resetGame();
		/*for(int i = 0; i < tile.length; i++){
			for(int j = 0; j < tile[i].length; j++){
				if(!(i+1==tile.length && j+1 == tile[i].length)){
					tile[i][j].ctr = 1;
					tile[i][j].player = 1;
					tile[i][j].setText(String.valueOf(tile[i][j].ctr));
				}
			}
		}*/
		
		/*for(int i = 1 ; i < 9; i++){
			tile[i][0].ctr = 2;
			tile[i][0].setText(String.valueOf(tile[i][0].ctr));
			tile[i][9].ctr = 2;
			tile[i][9].setText(String.valueOf(tile[i][9].ctr));
			tile[0][i].ctr = 2;
			tile[0][i].setText(String.valueOf(tile[0][i].ctr));
			tile[9][i].ctr = 2;
			tile[9][i].setText(String.valueOf(tile[9][i].ctr));
		}
		tile[0][0].ctr = 1;
		tile[0][0].setText(String.valueOf(tile[0][0].ctr));
		tile[9][0].ctr = 1;
		tile[9][0].setText(String.valueOf(tile[9][0].ctr));
		tile[0][9].ctr = 1;
		tile[0][9].setText(String.valueOf(tile[0][9].ctr));
		tile[9][9].ctr = 1;
		tile[9][9].setText(String.valueOf(tile[9][9].ctr));
		
		for(int i = 1; i < 9; i++){
			for(int j = 1; j < 9; j++){
				tile[i][j].ctr = 3;
				tile[i][j].setText(String.valueOf(tile[i][j].ctr));
			}
		}*/
		timer.start();
	}
	
	public void previousGame(String three) {
		
		int i = Integer.parseInt(three.substring(0, 1));
		int j = Integer.parseInt(three.substring(1, 2));
		int count = Integer.parseInt(three.substring(2, 3));
		int play = Integer.parseInt(three.substring(3, 4));
		tile[i][j].ctr = count;
		tile[i][j].player = play;
		if(play == 0)
			tile[i][j].setIcon(ballIcon[tile[i][j].player][tile[i][j].ctr]);
		else
			tile[i][j].setIcon(ballIcon[tile[i][j].player - 1][tile[i][j].ctr]);
		// add(tile[i][j]);
		// resetGame(i, j, true);
		// repaint();
		// revalidate();
		// System.out.println(two.length());
	}
	
	public String saving() {
		
		String toSave = "";
		
		for(int i = 0; i < tile.length; i++) {
			for(int j = 0 ; j < tile[i].length; j++) {
				String x = "" + i;
				String y = "" + j;
				String count = "" + tile[i][j].ctr;
				String play = "" + tile[i][j].player;
				toSave = toSave + x + y + count + play;
			}
		}
		
		return toSave;
	}
	
	/*public void score() {
		
		int scorep1, scorep2;
		
		for(int i = 0; i < tile.length; i++) {
			for(int j = 0 ; j < tile[i].length; j++) {
				if(tile[i][j].ctr != 0) {
					if(tile[i][j].player == 1) {
						
					}
				}
			}
		}
	}*/
	
	public Board(Board board, int xMove, int yMove, int player){
		setLayout(new GridLayout(tile.length, tile[0].length));
		for(int i = 0; i < tile.length; i++){
			for(int j = 0 ; j < tile[i].length; j++){
				tile[i][j] = new Tile(true);
				tile[i][j].ctr = board.tile[i][j].ctr;
				tile[i][j].player = board.tile[i][j].player;
				add(tile[i][j]);
			}
		}
		move(yMove, xMove, true, player);
	}
	
	public void move(int y, int x, boolean ifSimulation, int player){
		tile[y][x].ctr++;
		tile[y][x].player = player;
		tile[y][x].setIcon(ballIcon[tile[y][x].player-1][tile[y][x].ctr]);
		check(y, x, player, ifSimulation);
	}
	
	public void check(int y, int x, int player, boolean ifSimulation){
		//System.out.println("Location: (" + tile[y][x].getLocation().x + ", " + tile[y][x].getLocation().y + "), (" + tile[y][x].getSize().height + ", " + tile[y][x].getSize().width + ")");
		if( (x==0||x==tile[0].length-1) && (y==0||y==tile.length-1) && tile[y][x].ctr >= 2 ){
			tile[y][x].ctr -= 2;
			tile[y][x].player = 0;
			
			if(!ifSimulation){
				tile[y][x].setIcon(ballIcon[tile[y][x].player][tile[y][x].ctr]);
				new BallMoveAnimation((byte)x, (byte)y, (byte)2, this, (byte)player);
				//timer.start();
			}
		}else if( ( ( (x==0||x==tile[0].length-1) && (!(y==0)&&!(y==tile.length-1)) ) || (y==0||y==tile.length-1) && (!(x==0)&&!(x==tile[0].length-1)) ) && tile[y][x].ctr >= 3){
			tile[y][x].ctr -= 3;
			tile[y][x].player = 0;
			
			if(!ifSimulation){
				tile[y][x].setIcon(ballIcon[tile[y][x].player][tile[y][x].ctr]);
				new BallMoveAnimation((byte)x, (byte)y, (byte)3, this, (byte)player);
				//timer.start();
			}
		}else if(tile[y][x].ctr >= 4){
			tile[y][x].ctr -= 4;
			tile[y][x].player = 0;
			
			if(!ifSimulation){
				tile[y][x].setIcon(ballIcon[tile[y][x].player][tile[y][x].ctr]);
				new BallMoveAnimation((byte)x, (byte)y, (byte)4, this, (byte)player);
				//timer.start();
			}
		}
	}
	
	public void update(int x, int y, byte ctr, boolean ifSimulation, int player){
		if(ctr == 2){
			tile[(y==0?y+1:y-1)][x].ctr++;
			tile[(y==0?y+1:y-1)][x].player = player;
			
			tile[y][(x==0?x+1:x-1)].ctr++;
			tile[y][(x==0?x+1:x-1)].player = player;
			
			if(!ifSimulation){
				tile[(y==0?y+1:y-1)][x].setIcon(ballIcon[tile[(y==0?y+1:y-1)][x].player-1][tile[(y==0?y+1:y-1)][x].ctr]);
				tile[y][(x==0?x+1:x-1)].setIcon(ballIcon[tile[y][(x==0?x+1:x-1)].player-1][tile[y][(x==0?x+1:x-1)].ctr]);
				int winner = 0;
				if((winner = checkWinner())!= 0 && !foundWinner){
					foundWinner = true;
					declareWinner(winner);
				}
			}
			check((y==0?y+1:y-1), x, player, ifSimulation);
			check(y, (x==0?x+1:x-1), player, ifSimulation);
			return;
		}else if(ctr == 3){
			if((x==0||x==tile[0].length-1)){
				tile[y+1][x].ctr++;
				tile[y+1][x].player = player;
				
				tile[y-1][x].ctr++;
				tile[y-1][x].player = player;
				
				tile[y][(x==0?x+1:x-1)].ctr++;
				tile[y][(x==0?x+1:x-1)].player = player;
				
				if(!ifSimulation){
					tile[(y+1)][x].setIcon(ballIcon[tile[(y+1)][x].player-1][tile[(y+1)][x].ctr]);
					tile[(y-1)][x].setIcon(ballIcon[tile[(y-1)][x].player-1][tile[(y-1)][x].ctr]);
					tile[y][(x==0?x+1:x-1)].setIcon(ballIcon[tile[y][(x==0?x+1:x-1)].player-1][tile[y][(x==0?x+1:x-1)].ctr]);
					int winner = 0;
					if((winner = checkWinner())!= 0 && !foundWinner){
						foundWinner = true;
						declareWinner(winner);
					}
				}
				
				check(y+1, x, player, ifSimulation);
				check(y-1, x, player, ifSimulation);
				check(y, (x==0?x+1:x-1), player, ifSimulation);
				return;
			}else if((y==0||y==tile.length-1)){
				tile[y][x+1].ctr++;
				tile[y][x+1].player = player;
				
				tile[y][x-1].ctr++;
				tile[y][x-1].player = player;
				
				tile[(y==0?y+1:y-1)][x].ctr++;
				tile[(y==0?y+1:y-1)][x].player = player;
				
				if(!ifSimulation){
					tile[y][x+1].setIcon(ballIcon[tile[y][x+1].player-1][tile[y][x+1].ctr]);
					tile[y][x-1].setIcon(ballIcon[tile[y][x-1].player-1][tile[y][x-1].ctr]);
					tile[(y==0?y+1:y-1)][x].setIcon(ballIcon[tile[(y==0?y+1:y-1)][x].player-1][tile[(y==0?y+1:y-1)][x].ctr]);
					int winner = 0;
					if((winner = checkWinner())!= 0 && !foundWinner){
						foundWinner = true;
						declareWinner(winner);
					}
				}
				
				check(y, x-1, player, ifSimulation);
				check(y, x+1, player, ifSimulation);
				check((y==0?y+1:y-1), x, player, ifSimulation);
				return;
			}
		}else if(ctr == 4){
			tile[y+1][x].ctr++;
			tile[y+1][x].player = player;
			
			tile[y-1][x].ctr++;
			tile[y-1][x].player = player;
			
			tile[y][x+1].ctr++;
			tile[y][x+1].player = player;
			
			tile[y][x-1].ctr++;
			tile[y][x-1].player = player;
			
			if(!ifSimulation){
				tile[y][x-1].setIcon(ballIcon[tile[y][x-1].player-1][tile[y][x-1].ctr]);
				tile[y][x+1].setIcon(ballIcon[tile[y][x+1].player-1][tile[y][x+1].ctr]);
				tile[y+1][x].setIcon(ballIcon[tile[y+1][x].player-1][tile[y+1][x].ctr]);
				tile[y-1][x].setIcon(ballIcon[tile[y-1][x].player-1][tile[y-1][x].ctr]);
				int winner = 0;
				if((winner = checkWinner())!= 0 && !foundWinner){
					foundWinner = true;
					declareWinner(winner);
				}
			}
			check(y+1, x, player, ifSimulation);
			check(y-1, x, player, ifSimulation);
			check(y, x+1, player, ifSimulation);
			check(y, x-1, player, ifSimulation);
			return;
		}
	}
	
	public int checkWinner(){
		boolean p1Lose = true, p2Lose = true;
		for(int i = 0; i < tile.length; i++){
			for(int j = 0; j < tile[i].length; j++){
				if(!p1Lose && !p2Lose){
					return 0;
				}
				if(tile[i][j].player == 1){
					p1Lose = false;
				}
				if(tile[i][j].player == 2){
					p2Lose = false;
				}
			}
		}
		if(p1Lose && !p2Lose){
			return 2;
		}else if(!p1Lose && p2Lose){
			return 1;
		}else{
			return 0;
		}
	}
	
	public void declareWinner(int winner){
		handler.gamePlaying = false;
		int reply = JOptionPane.showConfirmDialog(null, "Winner is player " + winner + "\nWould you like to play again?", "Found Winner!", JOptionPane.YES_NO_OPTION);
		if(reply == JOptionPane.YES_OPTION){
			handler.startGame(handler.currentlyPlayed, (handler.currentlyPlayed == GameHandler.PLAYER_VS_AI?handler.ai.player:0), false, "");
			resetGame();
		}else{
			// gameMenu.printMenu();
			handler.starter();
			//System.exit(0);
		}
	}
	
	public void resetGame(){
		ballMoveCtr = 0;
		foundWinner = false;
		for(int i = 0; i < tile.length; i++){
			for(int j = 0; j < tile[i].length; j++){
				tile[i][j].player = 0;
				tile[i][j].ctr = 0;
				tile[i][j].setIcon(ballIcon[tile[i][j].player][tile[i][j].ctr]);
			}
		}
		tile[0][0].ctr = 1;
		tile[0][0].player = 1;
		tile[0][0].setIcon(ballIcon[tile[0][0].player-1][tile[0][0].ctr]);
		tile[0][tile[0].length-1].ctr = 1;
		tile[0][tile[0].length-1].player = 2;
		tile[0][tile[0].length-1].setIcon(ballIcon[tile[0][tile[0].length-1].player-1][tile[0][tile[0].length-1].ctr]);
		tile[tile.length-1][0].ctr = 1;
		tile[tile.length-1][0].player = 2;
		tile[tile.length-1][0].setIcon(ballIcon[tile[tile.length-1][0].player-1][tile[tile.length-1][0].ctr]);
		tile[tile.length-1][tile[0].length-1].ctr = 1;
		tile[tile.length-1][tile[0].length-1].player = 1;
		tile[tile.length-1][tile[0].length-1].setIcon(ballIcon[tile[tile.length-1][tile[0].length-1].player-1][tile[tile.length-1][tile[0].length-1].ctr]);
	}
	
	public float evaluateBoard(){
		int[] ret = new int[2];
		ret[0] = 0;
		ret[1] = 0;
		for(int i = 0; i < tile.length; i++){
			for(int j = 0 ; j < tile[i].length; j++){
				if(!(tile[i][j].player==0)){
					ret[tile[i][j].player-1] += tile[i][j].ctr;
				}
			}
		}
		return (float)ret[0]/(float)ret[1];
		//closer to infinity player 1 wins, closer to zero player 2 wins
	}
	
	public void actionPerformed(ActionEvent ev){
		repaint();
	}
	
	static int ballMoveCtr = 0;
	static BallMoveAnimation[] ballMoveAnimations = new BallMoveAnimation[width*height];
	Color[] color = {Color.RED, Color.BLUE};
	BufferedImage[] pImage = new BufferedImage[2];
	public void paint(Graphics g){
		super.paint(g);
		for(int i = 0; i < ballMoveCtr; i++){
			for(int j = 0; j < ballMoveAnimations[i].ballCoordinates.length; j++){
				//g.setColor(color[(ballMoveAnimations[i].player-1)]);
				//g.fillOval((int)ballMoveAnimations[i].ballCoordinates[j][0], (int)ballMoveAnimations[i].ballCoordinates[j][1], 20, 20);
				g.drawImage(pImage[ballMoveAnimations[i].player-1], (int)ballMoveAnimations[i].ballCoordinates[j][0], (int)ballMoveAnimations[i].ballCoordinates[j][1], null);
				ballMoveAnimations[i].ballCoordinates[j][0] += ballMoveAnimations[i].vel[j][0];
				ballMoveAnimations[i].ballCoordinates[j][1] += ballMoveAnimations[i].vel[j][1];
				ballMoveAnimations[i].distance-= BallMoveAnimation.mainVel;
			}
		}
		for(int i = 0; i < ballMoveCtr; i++){
			if(ballMoveAnimations[i].distance <= 0){
				ballMoveAnimations[i].delete(this);
			}
		}
		if(ballMoveCtr <= 0){
			//timer.stop();
			repaint();
		}
	}
}