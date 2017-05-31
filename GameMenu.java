import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.awt.Font;
import java.awt.Graphics;
import java.nio.file.*;

public class GameMenu extends JPanel {
	
	static final int PLAYER_VS_PLAYER = 12345, PLAYER_VS_AI = 23456;
	GameHandler gameHandler;
	
	public void setGameHandler(GameHandler gameHandler) {
		
		this.gameHandler = gameHandler;
	}
	
	public void printMenu() {
		
		gameHandler.getContentPane().removeAll();//do not remove
		gameHandler.repaint();//do not remove
		gameHandler.revalidate();//do not remove
		gameHandler.setLayout(null);
		JLabel background = new JLabel();
		background.setIcon(new ImageIcon(new ImageIcon("background.png").getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT)));
		JLabel title = new JLabel(new ImageIcon(new ImageIcon("TitleMenu.png").getImage().getScaledInstance(450, 114, Image.SCALE_DEFAULT)));
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 32));
		JButton newGame = new JButton(new ImageIcon(new ImageIcon("ButtonNewGame.png").getImage().getScaledInstance(300, 70, Image.SCALE_DEFAULT)));
		JButton load = new JButton(new ImageIcon(new ImageIcon("ButtonLoadGame.png").getImage().getScaledInstance(300, 70, Image.SCALE_DEFAULT)));
		JButton exit = new JButton(new ImageIcon(new ImageIcon("ButtonQuit.png").getImage().getScaledInstance(300, 70, Image.SCALE_DEFAULT)));
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
		background.setBounds(0, 0, 600, 500);
		title.setBounds(75, 45, 450, 114);
		newGame.setBounds(150, 180, 300, 70);
		load.setBounds(150, 260, 300, 70);
		exit.setBounds(150, 340, 300, 70);
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameHandler.getContentPane().removeAll();
				gameHandler.repaint();
				gameHandler.revalidate();
				JLabel background = new JLabel();
				background.setIcon(new ImageIcon(new ImageIcon("background.png").getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT)));
				JLabel title = new JLabel(new ImageIcon(new ImageIcon("TitleMenu.png").getImage().getScaledInstance(450, 114, Image.SCALE_DEFAULT)));
				title.setForeground(Color.WHITE);
				title.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 32));
				gameHandler.add(title);
				JButton pvp = new JButton(new ImageIcon(new ImageIcon("ButtonPVP.png").getImage().getScaledInstance(300, 70, Image.SCALE_DEFAULT)));
				JButton pva = new JButton(new ImageIcon(new ImageIcon("ButtonPVA.png").getImage().getScaledInstance(300, 70, Image.SCALE_DEFAULT)));
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
				JButton back = new JButton(new ImageIcon(new ImageIcon("ButtonBack.png").getImage().getScaledInstance(300, 70, Image.SCALE_DEFAULT)));
				back.setOpaque(false);
				back.setBackground(Color.BLACK);
				back.setForeground(Color.RED);
				back.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				back.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent backEv){
							printMenu();
						}
					}
				);
				gameHandler.add(back);
				gameHandler.add(pvp);
				gameHandler.add(pva);
				gameHandler.add(background);
				title.setBounds(75, 45, 450, 114);
				background.setBounds(0, 0, 600, 500);
				pvp.setBounds(150, 180, 300, 70);
				pva.setBounds(150, 260, 300, 70);
				back.setBounds(150, 340, 300, 70);
				
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
						background.setIcon(new ImageIcon(new ImageIcon("background.png").getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT)));
						JLabel title = new JLabel(new ImageIcon(new ImageIcon("TitleMenu.png").getImage().getScaledInstance(450, 114, Image.SCALE_DEFAULT)));
						title.setForeground(Color.WHITE);
						title.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 32));
						gameHandler.add(title);
						JButton p1B = new JButton(new ImageIcon(new ImageIcon("ButtonPlayer1.png").getImage().getScaledInstance(300, 70, Image.SCALE_DEFAULT)));
						JButton p2B = new JButton(new ImageIcon(new ImageIcon("ButtonPlayer2.png").getImage().getScaledInstance(300, 70, Image.SCALE_DEFAULT)));
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
						JButton back = new JButton(new ImageIcon(new ImageIcon("ButtonBack.png").getImage().getScaledInstance(300, 70, Image.SCALE_DEFAULT)));
						back.setOpaque(false);
						back.setBackground(Color.BLACK);
						back.setForeground(Color.RED);
						back.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
						back.addActionListener(
							new ActionListener(){
								public void actionPerformed(ActionEvent backEv){
									printMenu();
								}
							}
						);
						gameHandler.add(back);
						gameHandler.add(p1B);
						gameHandler.add(p2B);
						gameHandler.add(background);
						title.setBounds(75, 45, 450, 114);
						background.setBounds(0, 0, 600, 500);
						p1B.setBounds(150, 180, 300, 70);
						p2B.setBounds(150, 260, 300, 70);
						back.setBounds(150, 340, 300, 70);
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
				load();
			}
			
			public void load(){
				gameHandler.getContentPane().removeAll();
				gameHandler.repaint();
				gameHandler.revalidate();
				JLabel background = new JLabel();
				background.setIcon(new ImageIcon(new ImageIcon("background.png").getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT)));
				JLabel title = new JLabel(new ImageIcon(new ImageIcon("TitleLoad.png").getImage().getScaledInstance(550, 65, Image.SCALE_DEFAULT)));
				title.setForeground(Color.WHITE);
				title.setFont(new Font("Chain Reaction Itaric", Font.PLAIN, 32));
				title.setBounds(25, 45, 550, 65);
				background.setBounds(0, 0, 600, 500);
				File file = new File("./Saves");
				File[] listOfFiles = file.listFiles();
				String[][] listNames = new String[listOfFiles.length][3];
				for(int i = 0; i < listOfFiles.length; i++){
					if(listOfFiles[i].isFile()){
						//System.out.println("File " + listOfFiles[i].getName());
						try{
							Scanner scan = new Scanner(listOfFiles[i]);
							listNames[i][0] = String.valueOf(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length()-4));
							listNames[i][2] = scan.next();
							if(Integer.parseInt(scan.next()) == GameHandler.PLAYER_VS_AI){
								listNames[i][1] = "Player VS. AI";
							}else{
								listNames[i][1] = "Player VS. Player";
							}
							scan.close();
						}catch(FileNotFoundException fnfEx){}
					}else if(listOfFiles[i].isDirectory()){
						//System.out.println("Directory " + listOfFiles[i].getName());
					}
				}
				LoadList loadList = new LoadList(listNames);
				JButton loadButton = new JButton(new ImageIcon(new ImageIcon("ButtonLoadButton.png").getImage().getScaledInstance(150, 35, Image.SCALE_DEFAULT)));
				//loadButton.setOpaque(false);
				//loadButton.setBackground(Color.BLACK);
				//loadButton.setForeground(Color.RED);
				//loadButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				loadButton.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent butEv){
							try{
								File f = null;
								try{
									f = new File(".\\Saves\\" + listNames[loadList.selectedIndex-1][0] + ".txt");
									System.out.println("/Saves/" + listNames[loadList.selectedIndex-1][0] + ".txt");
								}catch(ArrayIndexOutOfBoundsException e){
									return;
								}
								Scanner scan = new Scanner(f);
								// while(scan.hasNext()) {
									// System.out.println("" + scan.next() + "");
								// }
								scan.next();
								int tempMode = Integer.parseInt(scan.next());
								int tempAI = Integer.parseInt(scan.next());
								int turn = Integer.parseInt(scan.next());
								String pattern = scan.next();
								System.out.println("| " + tempMode + ", " + tempAI + ", " + pattern + " |");
								scan.close();
								gameHandler.startGame(tempMode, tempAI, true, pattern);
								gameHandler.player = turn;
							}catch(FileNotFoundException fnfEx){}
						}
					}
				);
				JButton deleteButton = new JButton(new ImageIcon(new ImageIcon("ButtonDeleteButton.png").getImage().getScaledInstance(150, 35, Image.SCALE_DEFAULT)));
				deleteButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						try{
							String fileName = ".\\Saves\\" + listNames[loadList.selectedIndex-1][0] + ".txt";
						}catch(ArrayIndexOutOfBoundsException e){
							return;
						}
						try{
							int reply = JOptionPane.showConfirmDialog(GameHandler.gameMenu.gameHandler, "Are you sure you want to delete?", "Delete Game", JOptionPane.YES_NO_OPTION);
							if(reply == JOptionPane.YES_OPTION){
								Files.delete(FileSystems.getDefault().getPath("Saves", String.valueOf(listNames[loadList.selectedIndex-1][0] + ".txt")));
								load();
							}else{
								return;
							}
						}catch(IOException e){
							System.out.println("Something went wrong!");
							e.printStackTrace();
						}
					}
				});
				JButton back = new JButton(new ImageIcon(new ImageIcon("ButtonBack.png").getImage().getScaledInstance(150, 35, Image.SCALE_DEFAULT)));
				//back.setOpaque(false);
				//back.setBackground(Color.BLACK);
				//back.setForeground(Color.RED);
				//back.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				back.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent backEv){
							printMenu();
						}
					}
				);
				gameHandler.add(back);
				gameHandler.add(deleteButton);
				gameHandler.add(loadButton);
				gameHandler.add(loadList);
				gameHandler.add(title);
				gameHandler.add(background);
				back.setBounds(400, 460, 150, 35);
				deleteButton.setBounds(225, 460, 150, 35);
				loadButton.setBounds(50, 460, 150, 35);
				loadList.setBounds(30, 125, 540, 325);
				gameHandler.getContentPane().repaint();
				revalidate();
			}
		}
		);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(gameHandler, "Are you sure you want to quit?", "Exit Game", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		}	
		);
		gameHandler.repaint();//do not remove
		gameHandler.revalidate();//do not remove
	}
}
