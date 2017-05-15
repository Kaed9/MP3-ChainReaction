import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.awt.Font;
import java.awt.Graphics;

public class GameMenu extends JPanel {
	
	static final int PLAYER_VS_PLAYER = 12345, PLAYER_VS_AI = 23456;
	private GameHandler gameHandler;
	
	public void setGameHandler(GameHandler gameHandler) {
		
		this.gameHandler = gameHandler;
	}
	
	public void printMenu() {
		
		gameHandler.getContentPane().removeAll();//do not remove
		gameHandler.repaint();//do not remove
		gameHandler.revalidate();//do not remove
		gameHandler.setLayout(null);
		JLabel background = new JLabel();
		background.setIcon(new ImageIcon(new ImageIcon("background.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT)));
		JLabel title = new JLabel("C H A I N  R E A C T I O N", JLabel.CENTER);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 32));
		JButton newGame = new JButton("New Game");
		JButton load = new JButton("Load Game");
		JButton exit = new JButton("Exit");
		// gameHandler.setLayout(new FlowLayout());
		newGame.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 24));
		load.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 24));
		exit.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 24));
		newGame.setOpaque(false);
		newGame.setBackground(Color.BLACK);
		newGame.setForeground(Color.RED);
		newGame.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		load.setOpaque(false);
		load.setBackground(Color.BLACK);
		load.setForeground(Color.RED);
		load.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		exit.setOpaque(false);
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.RED);
		exit.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		gameHandler.add(title);
		gameHandler.add(newGame);
		gameHandler.add(load);
		gameHandler.add(exit);
		gameHandler.add(background);
		background.setBounds(0, 0, 500, 500);
		title.setBounds(25, 50, 450, 70);
		newGame.setBounds(100, 180, 300, 70);
		load.setBounds(100, 260, 300, 70);
		exit.setBounds(100, 340, 300, 70);
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameHandler.getContentPane().removeAll();
				gameHandler.repaint();
				gameHandler.revalidate();
				JLabel background = new JLabel();
				background.setIcon(new ImageIcon(new ImageIcon("background.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT)));
				JLabel title = new JLabel("C H A I N  R E A C T I O N", JLabel.CENTER);
				title.setForeground(Color.WHITE);
				title.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 32));
				gameHandler.add(title);
				title.setBounds(25, 50, 450, 70);
				JButton pvp = new JButton("Player vs. Player");
				JButton pva = new JButton("Player vs. AI");
				pvp.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 24));
				pva.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 24));
				pvp.setOpaque(false);
				pvp.setBackground(Color.BLACK);
				pvp.setForeground(Color.RED);
				pvp.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				pva.setOpaque(false);
				pva.setBackground(Color.BLACK);
				pva.setForeground(Color.RED);
				pva.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				gameHandler.add(pvp);
				gameHandler.add(pva);
				gameHandler.add(background);
				background.setBounds(0, 0, 500, 500);
				pvp.setBounds(100, 180, 300, 70);
				pva.setBounds(100, 260, 300, 70);
				
				pvp.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						gameHandler.startGame(PLAYER_VS_PLAYER, 0, false, "");
					}
				}
				);
				pva.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						gameHandler.getContentPane().removeAll();
						gameHandler.repaint();
						gameHandler.revalidate();
						JLabel background = new JLabel();
						background.setIcon(new ImageIcon(new ImageIcon("background.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT)));
						JLabel title = new JLabel("C H A I N  R E A C T I O N", JLabel.CENTER);
						title.setForeground(Color.WHITE);
						title.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 32));
						gameHandler.add(title);
						title.setBounds(25, 50, 450, 70);
						JButton p1B = new JButton("Player 1");
						JButton p2B = new JButton("Player 2");
						p1B.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 24));
						p2B.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 24));
						p1B.setOpaque(false);
						p1B.setBackground(Color.BLACK);
						p1B.setForeground(Color.RED);
						p1B.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
						p2B.setOpaque(false);
						p2B.setBackground(Color.BLACK);
						p2B.setForeground(Color.RED);
						p2B.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
						gameHandler.add(p1B);
						gameHandler.add(p2B);
						gameHandler.add(background);
						background.setBounds(0, 0, 500, 500);
						p1B.setBounds(100, 180, 300, 70);
						p2B.setBounds(100, 260, 300, 70);
						gameHandler.repaint();
						gameHandler.revalidate();
						p1B.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								gameHandler.startGame(PLAYER_VS_AI, 2, false, "");
							}
						}
						);
						p2B.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								gameHandler.startGame(PLAYER_VS_AI, 1, false, "");
							}
						}
						);
					}
				}
				);
			}
		}
		);
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					File file = new File("Saves/2017-05-12.txt");
					Scanner scan = new Scanner(file);
					// while(scan.hasNext()) {
						// System.out.println("" + scan.next() + "");
					// }
					int tempMode = Integer.parseInt(scan.next());
					int tempAI = Integer.parseInt(scan.next());
					String pattern = scan.next();
					System.out.println("| " + tempMode + ", " + tempAI + ", " + pattern + " |");
					scan.close();
					gameHandler.startGame(tempMode, tempAI, true, pattern);
				} catch(FileNotFoundException fnfEx) { }
			}
		}
		);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}	
		);
		gameHandler.repaint();//do not remove
		gameHandler.revalidate();//do not remove
	}
}