import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoadList extends JPanel implements MouseListener{
	JPanel[] listPanels = null;
	JPanel[] itemsPanel = null;
	JLabel[][] items = null;
	int selectedIndex = 0, currentListPanel = 0;
	JButton swipeLeft = new JButton(new ImageIcon(new ImageIcon("LoadSwipeLeft.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
	JButton swipeRight = new JButton(new ImageIcon(new ImageIcon("LoadSwipeRight.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
	JLabel name = new JLabel(new ImageIcon(new ImageIcon("LoadName.png").getImage().getScaledInstance(160, 50, Image.SCALE_DEFAULT)));
	JLabel gameMode = new JLabel(new ImageIcon(new ImageIcon("LoadGameMode.png").getImage().getScaledInstance(160, 50, Image.SCALE_DEFAULT)));
	JLabel dateSaved = new JLabel(new ImageIcon(new ImageIcon("LoadDateSaved.png").getImage().getScaledInstance(160, 50, Image.SCALE_DEFAULT)));
	JPanel header = new JPanel(new BorderLayout(1, 3));
	
	public LoadList(String[][] list){
		super(new BorderLayout(1, list.length+1));
		setOpaque(false);
		items = new JLabel[list.length][3];
		itemsPanel = new JPanel[list.length];
		header.setOpaque(false);
		name.setForeground(Color.RED);
		gameMode.setForeground(Color.RED);
		dateSaved.setForeground(Color.RED);
		header.add(name);
		header.add(gameMode);
		header.add(dateSaved);
		name.setBounds(0, 0, 160, 45);
		name.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		gameMode.setBounds(160, 0, 160, 45);
		gameMode.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		dateSaved.setBounds(320, 0, 160, 45);
		dateSaved.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
		this.add(header);
		header.setBounds(30, 0, 480, 50);
		listPanels = new JPanel[((int)((float)list.length/11.0))+(list.length%11==0?0:1)];
		for(int k = 0; k < listPanels.length;k++){
			listPanels[k] = new JPanel(new BorderLayout(11,1));
			listPanels[k].setOpaque(false);
			for(int i = 0; i < 11 && i+(k*11) < list.length; i++){
				//System.out.println("i="+i + ", k="+k + ", list length="+list.length);
				final int index = i+1;
				itemsPanel[i+(k*11)] = new JPanel(new BorderLayout(1, 3)){
					public void paintComponent(Graphics g){
						ImageIcon backGround = new ImageIcon(new ImageIcon("LoadList" + (index) + ".png").getImage().getScaledInstance(460, 19, Image.SCALE_DEFAULT));
						Image background = backGround.getImage();
						super.paintComponent(g);
						g.drawImage(background, 10, 3, null);
					}
				};
				itemsPanel[i+(k*11)].setOpaque(false);
				itemsPanel[i+(k*11)].addMouseListener(this);
				for(int j = 0; j < 3; j++){
					items[i+(k*11)][j] = new JLabel(list[i+(k*11)][j], SwingConstants.CENTER);
					items[i+(k*11)][j].setBackground(Color.GREEN);
					items[i+(k*11)][j].setForeground(Color.BLACK);
					items[i+(k*11)][j].setFont(new Font("OCR A Extended", Font.BOLD, 14));
					this.itemsPanel[i+(k*11)].add(items[i+(k*11)][j]);
					items[i+(k*11)][j].setBounds((j*160), 0, 160, 25);
				}
				itemsPanel[i+(k*11)].setBounds(0, (i*25), 480, 25);
				listPanels[k].add(itemsPanel[i+(k*11)]);
			}
			listPanels[k].setBounds(30, 50, 480, 275);
			this.add(listPanels[k]);
			this.remove(listPanels[k]);
		}
		this.add(listPanels[currentListPanel]);
		/*for(int i = 0; i < list.length; i++){
			itemsPanel[i] = new JPanel(new BorderLayout(1, 3));
			for(int j = 0; j < 3; j++){
				items[i][j] = new JLabel(list[i][j], SwingConstants.CENTER);
				items[i][j].setBackground(Color.GREEN);
				items[i][j].setForeground(Color.RED);
				items[i][j].setFont(new Font("OCR A Extended", Font.BOLD, 11));
				this.itemsPanel[i].add(items[i][j]);
				items[i][j].setBounds(j*180, 0, 180, 25);
			}
			this.add(itemsPanel[i]);
			this.itemsPanel[i].addMouseListener(this);
			this.itemsPanel[i].setOpaque(false);
			itemsPanel[i].setBounds(0, 50+(i*25), 540, 25);
			repaint();
			revalidate();
		}*/
		//swipeLeft.setOpaque(false);
		swipeLeft.setBorder(null);
		swipeLeft.setBorderPainted(false);
		swipeLeft.setMargin(new Insets(0,0,0,0));
		swipeLeft.setContentAreaFilled(false);
		//swipeRight.setOpaque(false);
		swipeRight.setBorder(null);
		swipeRight.setBorderPainted(false);
		swipeRight.setMargin(new Insets(0,0,0,0));
		swipeRight.setContentAreaFilled(false);
		add(swipeLeft);
		add(swipeRight);
		swipeLeft.setBounds(0, 125, 30, 30);
		swipeRight.setBounds(510, 125, 30, 30);
		swipeLeft.addMouseListener(this);
		swipeRight.addMouseListener(this);
	}
	
	public void reposition(){
		dateSaved.setBounds(320, 0, 160, 45);
		name.setBounds(0, 0, 160, 45);
		gameMode.setBounds(160, 0, 160, 45);
		for(int k = 0; k < listPanels.length; k++){
			for(int i = 0 ; i < 11 && i+(k*11) < itemsPanel.length; i++){
				for(int j = 0; j < 3; j++){
					items[i+(k*11)][j].setBounds((j*160), 0, 160, 25);
				}
				itemsPanel[i+(k*11)].setBounds(0, (i*25), 480, 25);
			}
			listPanels[k].setBounds(30, 50, 480, 275);
		}
	}
	
	public void mouseClicked(MouseEvent e){
		//System.out.println("ListPanels="+listPanels.length+ ", currentListPanel="+currentListPanel);
		for(int i = 0; i < itemsPanel.length; i++){
			if(e.getSource() == itemsPanel[i]){
				for(int j = 0; j < itemsPanel.length; j++){
					itemsPanel[j].setOpaque(false);
					itemsPanel[j].repaint();
				}
				itemsPanel[i].setOpaque(true);
				itemsPanel[i].setBackground(Color.CYAN);
				itemsPanel[i].repaint();
				selectedIndex = i+1;
				//System.out.println("Selected item = " + selectedIndex);
				return;
			}
		}
		if(e.getSource() == swipeLeft){
			remove(listPanels[currentListPanel]);
			currentListPanel = (currentListPanel==0?listPanels.length-1:--currentListPanel);
			add(listPanels[currentListPanel]);
			listPanels[currentListPanel].repaint();
			//listPanels[currentListPanel].revalidate();
		}else if(e.getSource() == swipeRight){
			remove(listPanels[currentListPanel]);
			currentListPanel = (currentListPanel+1)%listPanels.length;
			add(listPanels[currentListPanel]);
			listPanels[currentListPanel].repaint();
			//listPanels[currentListPanel].revalidate();
		}
		//System.out.println("|ListPanels="+listPanels.length+ ", currentListPanel="+currentListPanel+ "|");
		reposition();
	}
	
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
}