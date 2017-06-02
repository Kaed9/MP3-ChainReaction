import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OptionPane{
	static final int YES = 192837, NO = 918273;
	static int reply = 0;
	static String inputReply = null;
	static boolean actionMade = false;
	public static int optionDialog(JFrame relativeLocation, String message){
		JFrame frame = new JFrame();
		OptionPane.reply = 0;
		OptionPane.actionMade = false;
		//new Thread(){
			//public void run(){
				frame.setType(javax.swing.JFrame.Type.UTILITY);
				//frame.setLayout(new FlowLayout());
				frame.setLayout(null);
				frame.setSize(350, 150);
				frame.setUndecorated(true);
				frame.setAlwaysOnTop(true);
				if(relativeLocation == null){
				}else{
					relativeLocation.setAlwaysOnTop(true);
					int xLoc = relativeLocation.getLocation().x + (int)((float)(relativeLocation.getWidth())/2.0) - 175;
					int yLoc = relativeLocation.getLocation().y + (int)((float)(relativeLocation.getHeight())/2.0) - 75;
					frame.setLocation(xLoc, yLoc);
				}
				JLabel background = new JLabel(new ImageIcon(new ImageIcon("OptionPaneBackground.png").getImage().getScaledInstance(350, 150, Image.SCALE_DEFAULT)));
				
				JTextArea messageLabel = new JTextArea(3, 35);
				messageLabel.setForeground(Color.WHITE);
				messageLabel.setFont(new Font("Arial", Font.PLAIN, 15));
				messageLabel.setEditable(false);
				messageLabel.setOpaque(false);
				messageLabel.setText(message);
				
				JButton yesButton = new JButton(new ImageIcon(new ImageIcon("OptionPaneYesButton.png").getImage().getScaledInstance(100, 25, Image.SCALE_DEFAULT)));
				JButton noButton = new JButton(new ImageIcon(new ImageIcon("OptionPaneNoButton.png").getImage().getScaledInstance(100, 25, Image.SCALE_DEFAULT)));
				
				yesButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						OptionPane.reply = OptionPane.YES;
						OptionPane.actionMade = true;
						frame.setVisible(false);
					}
				});
				
				noButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						OptionPane.reply = OptionPane.NO;
						OptionPane.actionMade = true;
						frame.setVisible(false);
					}
				});
				
				frame.add(messageLabel);
				frame.add(yesButton);
				frame.add(noButton);
				frame.add(background);
				background.setBounds(-1,-1,350, 150);
				noButton.setBounds(200, 105, 100, 25);
				yesButton.setBounds(50, 105, 100, 25);
				messageLabel.setBounds(50, 20, 250, 80);
				frame.setVisible(true);
				
				frame.addWindowListener(new WindowListener(){
					public void windowClosing(WindowEvent e){
						OptionPane.reply = OptionPane.NO;
						OptionPane.actionMade = true;
						frame.setVisible(false);
					}
					public void windowDeactivated(WindowEvent e){
						relativeLocation.toFront();
						frame.requestFocus();
						frame.toFront();
					}
					public void windowIconified(WindowEvent e){
						relativeLocation.toFront();
						frame.requestFocus();
						frame.toFront();
					}
					public void windowActivated(WindowEvent e){}
					public void windowDeiconified(WindowEvent e){}
					public void windowClosed(WindowEvent e){}
					public void windowOpened(WindowEvent e){}
				});
				
				//System.out.println("2");
				while(!actionMade){
					System.out.print("");
				}
			//}
		//}.start();
		//System.out.println("1");
		System.gc();
		relativeLocation.setAlwaysOnTop(false);
		frame.setAlwaysOnTop(false);
		frame.dispose();
		//System.out.println("4");
		return OptionPane.reply;
	}
	
	public static String inputDialog(JFrame relativeLocation, String message){
		inputReply = null;
		OptionPane.actionMade = false;
		JFrame frame = new JFrame();
		frame.setType(javax.swing.JFrame.Type.UTILITY);
		//frame.setLayout(new FlowLayout());
		frame.setLayout(null);
		frame.setSize(350, 150);
		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);
		if(relativeLocation == null){
		}else{
			relativeLocation.setAlwaysOnTop(true);
			int xLoc = relativeLocation.getLocation().x + (int)((float)(relativeLocation.getWidth())/2.0) - 175;
			int yLoc = relativeLocation.getLocation().y + (int)((float)(relativeLocation.getHeight())/2.0) - 75;
			frame.setLocation(xLoc, yLoc);
		}
		JLabel background = new JLabel(new ImageIcon(new ImageIcon("OptionPaneBackground.png").getImage().getScaledInstance(350, 150, Image.SCALE_DEFAULT)));
		
		JTextArea messageLabel = new JTextArea(3, 35);
		messageLabel.setForeground(Color.WHITE);
		messageLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		messageLabel.setEditable(false);
		messageLabel.setOpaque(false);
		messageLabel.setText(message);
		
		JTextField inputField = new JTextField(25);
		inputField.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JButton okButton = new JButton(new ImageIcon(new ImageIcon("OptionPaneOkButton.png").getImage().getScaledInstance(100, 25, Image.SCALE_DEFAULT)));
		JButton cancel = new JButton(new ImageIcon(new ImageIcon("OptionPaneCancelButton.png").getImage().getScaledInstance(100, 25, Image.SCALE_DEFAULT)));
		
		frame.add(messageLabel);
		frame.add(inputField);
		frame.add(okButton);
		frame.add(cancel);
		frame.add(background);
		background.setBounds(-1,-1, 350, 150);
		okButton.setBounds(50, 108, 100, 25);
		cancel.setBounds(200, 108, 100, 25);
		inputField.setBounds(25, 85, 300, 18);
		messageLabel.setBounds(25, 20, 300, 63);
		if(!(relativeLocation==null)){
			relativeLocation.repaint();
			relativeLocation.revalidate();
		}
		frame.repaint();
		frame.revalidate();
		
		inputField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				inputReply = inputField.getText();
				OptionPane.actionMade = true;
			}
		});
		
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				inputReply = inputField.getText();
				OptionPane.actionMade = true;
			}
		});
		
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				OptionPane.actionMade = true;
				frame.setVisible(false);
			}
		});
		
		frame.addWindowListener(new WindowListener(){
			public void windowClosing(WindowEvent e){
				OptionPane.actionMade = true;
				frame.setVisible(false);
			}
			public void windowDeactivated(WindowEvent e){
				relativeLocation.toFront();
				frame.requestFocus();
				frame.toFront();
			}
			public void windowIconified(WindowEvent e){
				relativeLocation.toFront();
				frame.requestFocus();
				frame.toFront();
			}
			public void windowActivated(WindowEvent e){}
			public void windowDeiconified(WindowEvent e){}
			public void windowClosed(WindowEvent e){}
			public void windowOpened(WindowEvent e){}
		});
		
		frame.setVisible(true);
		while(!actionMade){
			System.out.print("");
		}
		frame.setAlwaysOnTop(false);
		relativeLocation.setAlwaysOnTop(false);
		frame.dispose();
		return inputReply;
	}
	
	
	
	public static void main(String[] args){
		JFrame f = new JFrame("Sareabtbtbav");
		//f.setType(javax.swing.JFrame.Type.UTILITY);
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500,500);
		f.setLocation(250,250);
		JButton b = new JButton("try");
		f.add(b);
		f.setVisible(true);
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Thread(){
					public void run(){
						int r = OptionPane.optionDialog(f, "Question");
						if(r == OptionPane.YES){
							System.out.println("yes");
						}else if(r == OptionPane.NO){
							System.out.println("NO");
						}
						
						//String sr = OptionPane.inputDialog(f, "Input message:");
						//System.out.println(sr);
					}
				}.start();
			}
		});
	}
}