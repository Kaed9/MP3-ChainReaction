import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BallMoveAnimation{
	byte x = 0, y = 0;
	static byte mainVel = 1;
	short[][] ballCoordinates = null;
	byte[][] vel = null;
	short distance = 0;
	byte player = 0;
	
	public BallMoveAnimation(byte x, byte y, byte ctr, Board board, byte player){
		this.x = x;
		this.y = y;
		this.player = player;
		ballCoordinates = new short[ctr][2];
		vel = new byte[ctr][2];
		short xCenter = (short)board.tile[y][x].getLocation().x, xSize = (short)board.tile[y][x].getSize().width;
		short yCenter = (short)board.tile[y][x].getLocation().y, ySize = (short)board.tile[y][x].getSize().height;
		xCenter += (short)((float)xSize/2.0)-10;
		yCenter += (short)((float)ySize/2.0)-10;
		distance = (short)((xSize<=ySize?xSize:ySize)*2);
		//distance = (short)(distance%2==0?distance:distance+1);
		for(int i = 0; i < ctr; i++){
			ballCoordinates[i][0] = xCenter;
			ballCoordinates[i][1] = yCenter;
		}
		if(ctr == 2){
			vel[0][0] = (byte)(x==0?mainVel:-mainVel);
			vel[0][1] = 0;
			vel[1][0] = 0;
			vel[1][1] = (byte)(y==0?mainVel:-mainVel);
		}else if(ctr == 3){
			if(x==0 || x == board.tile[0].length-1){
				vel[0][0] = (byte)(x==0?mainVel:-mainVel);
				vel[0][1] = 0;
				vel[1][0] = 0;
				vel[1][1] = (byte)(-mainVel);
				vel[2][0] = 0;
				vel[2][1] = mainVel;
			}else if(y==0 || y == board.tile.length-1){
				vel[0][0] = 0;
				vel[0][1] = (byte)(y==0?mainVel:-mainVel);
				vel[1][0] = mainVel;
				vel[1][1] = 0;
				vel[2][0] = (byte)(-mainVel);
				vel[2][1] = 0;
			}
		}else if(ctr == 4){
			vel[0][0] = mainVel;
			vel[0][1] = 0;
			vel[1][0]  = (byte)(-mainVel);
			vel[1][1] = 0;
			vel[2][0] = 0;
			vel[2][1] = mainVel;
			vel[3][0] = 0;
			vel[3][1] = (byte)(-mainVel);
		}
		Board.ballMoveAnimations[Board.ballMoveCtr++] = this;
	}
	
	public void delete(Board board){
		Board.ballMoveCtr--;
		for(int i = 0; i < Board.ballMoveCtr+1; i++){
			if(Board.ballMoveAnimations[i] == this){
				for(int j = i; j < Board.ballMoveCtr; j++){
					Board.ballMoveAnimations[j] = Board.ballMoveAnimations[j+1];
				}
				break;
			}
		}
		board.update((int)this.x, (int)this.y, (byte)this.ballCoordinates.length, false, (int)this.player);
	}
}