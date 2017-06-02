import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatusPanel extends JPanel implements ActionListener{
	GameHandler handler = null;
	JButton save = new JButton(new ImageIcon(new ImageIcon("ButtonSaveGame.png").getImage().getScaledInstance(110, 30, Image.SCALE_DEFAULT)));
	JButton quit = new JButton(new ImageIcon(new ImageIcon("ButtonQuit.png").getImage().getScaledInstance(110, 30, Image.SCALE_DEFAULT)));
	JLabel currentTurn = new JLabel("Turn: ");
	Timer timer = new Timer(1, this);
	
	public StatusPanel(GameHandler handler, int gameMode, int AIplayer){
		setHandler(handler);
		//setBackground(Color.BLACK);
		setOpaque(false);
		JPanel panel = new JPanel(new GridLayout(3,1, 0, 75));
		//panel.setBackground(Color.BLACK);
		panel.setOpaque(false);
		currentTurn.setFont(new Font("OCR A Extended", Font.BOLD, 12));
		
		currentTurn.setForeground(Color.CYAN);
		
		save.setOpaque(false);
		save.setBackground(Color.BLACK);
		save.setForeground(Color.RED);
		save.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		quit.setOpaque(false);
		quit.setBackground(Color.BLACK);
		quit.setForeground(Color.RED);
		quit.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		panel.add(currentTurn);
		panel.add(save);
		panel.add(quit);
		add(panel);
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(Board.ballMoveCtr > 0){
					return;
				}
				// DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
				// Date date = new Date();
				// String fileName = "Saves/" + dateFormat.format(date) + ".txt";
				new Thread(){
					public void run(){
						String fileName = OptionPane.inputDialog(handler, "Enter save file name:");
						if(fileName == null){
							return;
						}
						fileName = "Saves/" + fileName + ".txt";
						//System.out.println(fileName);
						
						File file = new File(fileName);
						if(file.exists() && !file.isDirectory()){
							new Thread(){
								public void run(){
									int reply = OptionPane.optionDialog(GameHandler.gameMenu.gameHandler, "The game name already exist\nWould you like to overwrite the game?");
									if(reply == OptionPane.YES){
										
									}else{
										return;
									}
								}
							}.start();
						}
						try{
							BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
							DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
							Date date = new Date();
							bufferedWriter.write(dateFormat.format(date) + "");
							bufferedWriter.newLine();
							bufferedWriter.write(gameMode + "");
							bufferedWriter.newLine();
							bufferedWriter.write(AIplayer + "");
							bufferedWriter.newLine();
							bufferedWriter.write(handler.player + "");
							bufferedWriter.newLine();
							String toWrite = handler.mainBoard.saving();
							// System.out.println(toWrite);
							bufferedWriter.write(toWrite);
							bufferedWriter.close();
						}catch(Exception ex){}
					}
				}.start();
			}
		}
		);
		
		quit.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					new Thread(){
						public void run(){
							int reply = OptionPane.optionDialog(GameHandler.gameMenu.gameHandler, "You will lose the progress of the game if you quit without saving\nDo you really want to quit?");
							if(reply == OptionPane.YES){
								handler.starter();
							}
						}
					}.start();
					//GameHandler.gameMenu.gameHandler.starter();
				}
			}
		);
		
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == timer && Board.ballMoveCtr == 0 && !handler.ai.findingMove){
			currentTurn.setText("Turn: Player " + handler.player);
		}
	}
	
	public void setHandler(GameHandler handler){
		this.handler = handler;
	}
}