import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.event.*;

public class Tile extends JPanel{// implements ActionListener{
	int player = 0;
	int ctr = 0;
	int angle = 0;
	TileLabel label = new TileLabel("", SwingConstants.CENTER);
	//Timer timer = new Timer(1, this);
	Color[] ctrColors = {Color.GRAY, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN};
	
	public Tile(){
		super(new BorderLayout());
		add(label);
		//timer.start();
	}
	
	public Tile(boolean ifSimulation){
		super(new BorderLayout());
		add(label);
		//super("", SwingConstants.CENTER);
		/*if(ifSimulation){
			timer.start();
		}*/
	}
	
	public void setIcon(Icon icon){
		label.setIcon(icon);
	}
	
	private class TileLabel extends JLabel{
		public TileLabel(String s, int o){
			super(s, o);
		}
		
		int interval = 0;
		public void paintComponent(Graphics g){
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			AffineTransform aT = g2.getTransform();
			Shape oldshape = g2.getClip();
			double x = getWidth()/2.0;
			double y = getHeight()/2.0;
			aT.rotate(Math.toRadians(angle), x, y);
			g2.setTransform(aT);
			g2.setClip(oldshape);
			if(interval++ == 4 || Board.ballMoveCtr > 0){
				angle++;
				interval = 0;
			}
			angle = (angle>=360?0:angle);
			super.paintComponent(g);
		}
	}
	
	//public void actionPerformed(ActionEvent e){
		/*ImageIcon img = new ImageIcon();
		BufferedImage bi = this.getIcon().getImage();
		Graphics2D g2d = (Graphics2D)bi.getGraphics();
		g2d.rotate(Math.toRadians(angle), 10, 10);
		g2d.drawImage(bi, this.getLocation().x, this.getLocation().y, null);*/
		//setIcon(this.getIcon().paintIcon(this, (Graphics)g2d, this.getLocation().x, this.getLocation().y));
		/*if(player == 0){
			this.setForeground(ctrColors[player]);
			//setHorizontalAlignment(SwingConstants.CENTER);
		}else if(player == 1){
			this.setForeground(ctrColors[player]);
			//setHorizontalAlignment(SwingConstants.LEFT);
		}else if(player == 2){
			this.setForeground(ctrColors[player]);
			//setHorizontalAlignment(SwingConstants.RIGHT);
		}*/
	//}
}