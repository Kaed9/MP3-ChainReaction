import java.util.Random;

public class AI{
	byte player = 0;
	int depth2;
	Random randomizer = new Random();
	int[] move = null;
	boolean findingMove = false, enabled = false;
	
	public AI(int player){
		this.player = (byte)player;
	}
	
	public void findMove(Board board){
		System.out.println("AI player " + player + " finding move");
		findingMove = true;
		depth2 = 3;
		PseudoBoard pseudoBoard = new PseudoBoard(board);
		float[] move = function((byte)1,(byte)1, pseudoBoard, (byte)depth2, true);
		//board = null;
		int[] ret = new int[2];
		ret[0] = (int)move[1];
		ret[1] = (int)move[2];
		move = null;
		System.gc();
		board.move(ret[1], ret[0], false, player);
		findingMove = false;
		System.out.println("AI player " + player + " found move (" + ret[0] + ", " + ret[1] + ")");
		//return ret;
	}
	
	public float[] function(byte xPositionMove, byte yPositionMove, PseudoBoard board, byte depth, boolean myMove){
		if(depth == 0){
			float[] ret = new float[3];
			ret[0] = board.evaluateBoard(this.player);
			ret[1] = (float)xPositionMove;
			ret[2] = (float)yPositionMove;
			return ret;
		}else{
			float[] bestMove = null;
			for(int i = 0; i < board.tile.length; i++){
				for(int j = 0; j < board.tile[i].length; j++){
					if(player == board.tile[i][j].player || board.tile[i][j].player == 0){
						PseudoBoard buffer = new PseudoBoard(board, j, i, (byte)(myMove?this.player:(this.player==1?2:1)));
						float[] move = function((byte)j, (byte)i, buffer, (byte)(depth-1), !myMove);
						buffer = null;
						//move[1] = (float)xPositionMove;
						//move[2] = (float)yPositionMove;
						if(myMove){
							//System.out.println("possible move found at depth " + depth + "(" + j + ", " + i +")" + myMove);
							if(bestMove == null || move[0] > bestMove[0]){
								bestMove = new float[3];
								bestMove = move;
							}else if(move[0] == bestMove[0]){
								//bestMove = new float[3];
								if(randomizer.nextBoolean()){
									bestMove = move;
								}
							}
							if(bestMove[0] == 10000.0){
								bestMove[1] = xPositionMove;
								bestMove[2] = yPositionMove;
								return bestMove;
							}
						}else if(!myMove){
							//System.out.println("possible move found at depth " + depth + "(" + j + ", " + i +")" + myMove);
							if(bestMove == null || move[0] < bestMove[0]){
								bestMove = new float[3];
								bestMove = move;
							}else if(move[0] == bestMove[0]){
								//bestMove = new float[3];
								if(randomizer.nextBoolean()){
									bestMove = move;
								}
							}
							if(bestMove[0] == -10000.0){
								bestMove[1] = xPositionMove;
								bestMove[2] = yPositionMove;
								return bestMove;
							}
						}
						move = null;
						//moves[possible][1] = (float)xPositionMove;
						//moves[possible][2] = (float)yPositionMove;
					}/*else{
						//System.out.println("Cannot move in (" + j + ", " + i + ") at depth " + depth);
						//System.out.println("(" + j + ", " + i + ") is now null");
					}*/
				}
			}
			if(depth2 == depth+1){
				bestMove[1] = xPositionMove;
				bestMove[2] = yPositionMove;
			}
			//System.out.println("best move (" + bestMove[1] + ", " + bestMove[2] + ") at depth" + depth);
			System.gc();
			return bestMove;
		}
	}
	
	private class PseudoBoard{
		PseudoTile[][] tile = new PseudoTile[Board.height][Board.width];
		
		public PseudoBoard(Board board){
			for(int i = 0; i < tile.length; i++){
				for(int j = 0; j < tile[0].length; j++){
					tile[i][j] = new PseudoTile();
					tile[i][j].ctr = (byte)board.tile[i][j].ctr;
					tile[i][j].player = (byte)board.tile[i][j].player;
				}
			}
		}
		
		public PseudoBoard(PseudoBoard board, int xMove, int yMove, byte player){
			for(int i = 0; i < tile.length; i++){
				for(int j = 0; j < tile[0].length; j++){
					tile[i][j] = new PseudoTile();
					tile[i][j].ctr = board.tile[i][j].ctr;
					tile[i][j].player = board.tile[i][j].player;
				}
			}
			move((byte)yMove, (byte)xMove, player);
		}
		
		public void move(byte y, byte x, byte player){
			tile[y][x].ctr++;
			tile[y][x].player = player;
			try{
				check((byte)y, x, player);
			}catch(StackOverflowError e){
				return;
			}
		}
		
		public void check(byte y, byte x, byte player){
			//System.out.println("Location: (" + tile[(byte)y][x].getLocation().x + ", " + tile[(byte)y][x].getLocation().y + "), (" + tile[(byte)y][x].getSize().height + ", " + tile[(byte)y][x].getSize().width + ")");
			if( (x==0||x==tile[0].length-1) && (y==0||y==tile.length-1) && tile[y][x].ctr >= 2 ){
				tile[y][x].ctr -= 2;
				tile[y][x].player = 0;
				
				tile[(y==0?y+1:y-1)][x].ctr++;
				tile[(y==0?y+1:y-1)][x].player = player;
				
				tile[y][(x==0?x+1:x-1)].ctr++;
				tile[y][(x==0?x+1:x-1)].player = player;
				
				check((byte)(y==0?y+1:y-1), (byte)x, player);
				check((byte)y, (byte)(x==0?x+1:x-1), player);
				return;
			}else if( ( ( (x==0||x==tile[0].length-1) && (!(y==0)&&!(y==tile.length-1)) ) || (y==0||y==tile.length-1) && (!(x==0)&&!(x==tile[0].length-1)) ) && tile[y][x].ctr >= 3){
				tile[y][x].ctr -= 3;
				tile[y][x].player = 0;
				
				if((x==0||x==tile[0].length-1)){
					tile[y+1][x].ctr++;
					tile[y+1][x].player = player;
					
					tile[y-1][x].ctr++;
					tile[y-1][x].player = player;
					
					tile[y][(x==0?x+1:x-1)].ctr++;
					tile[y][(x==0?x+1:x-1)].player = player;
					
					check((byte)(y+1), (byte)x, player);
					check((byte)(y-1), (byte)x, player);
					check((byte)y, (byte)(x==0?x+1:x-1), player);
					return;
				}else if((y==0||y==tile.length-1)){
					tile[y][x+1].ctr++;
					tile[y][x+1].player = player;
					
					tile[y][x-1].ctr++;
					tile[y][x-1].player = player;
					
					tile[(y==0?y+1:y-1)][x].ctr++;
					tile[(y==0?y+1:y-1)][x].player = player;
					
					check((byte)y, (byte)(x-1), player);
					check((byte)y, (byte)(x+1), player);
					check((byte)(y==0?y+1:y-1), (byte)x, player);
					return;
				}
			}else if(tile[y][x].ctr >= 4){
				tile[y][x].ctr -= 4;
				tile[y][x].player = 0;
				
				tile[y+1][x].ctr++;
				tile[y+1][x].player = player;
				
				tile[y-1][x].ctr++;
				tile[y-1][x].player = player;
				
				tile[y][x+1].ctr++;
				tile[y][x+1].player = player;
				
				tile[y][x-1].ctr++;
				tile[y][x-1].player = player;
				
				check((byte)(y+1), (byte)x, player);
				check((byte)(y-1), (byte)x, player);
				check((byte)y, (byte)(x+1), player);
				check((byte)y, (byte)(x-1), player);
				return;
			}
		}
		
		public float evaluateBoard(int player){
			/*int[] ret = new int[2];
			ret[0] = 0;
			ret[1] = 0;
			for(int i = 0; i < tile.length; i++){
				for(int j = 0 ; j < tile[i].length; j++){
					if(!(tile[i][j].player==0)){
						ret[tile[i][j].player-1] += 1 + (int)tile[i][j].ctr;
					}
				}
			}
			// System.out.println("ret[0] = " + ret[0] + "; ret[1] = " + ret[1]);
			//return (float)ret[0]/(float)ret[1];
			if(player==1){
				// System.out.println("Player 1 = " + ((float)ret[0]-(float)ret[1]));
				return (float)ret[0]-(float)ret[1];
			}else{
				// System.out.println("Player 2 = " + ((float)ret[1]-(float)ret[0]));
				return (float)ret[1]-(float)ret[0];
			}
			//closer to infinity player 1 wins, closer to zero player 2 wins
			*/
			int value = 0;
			int myOrbs = 0, aiOrbs = 0;
			boolean notnearai = false;
			
			for(int i = 0; i < tile.length; i++) { // height
				for(int j = 0; j < tile.length; j++) { // width
					if(tile[i][j].player == 1) {
						myOrbs += 1; // 1 // not sure
						notnearai = true;
						// 2 wa pa
						if(notnearai) {
							if(tile[i][j].ctr == 3)
								value += 2; // 3
							else if(tile[i][j].ctr == 2)
								value += 3; // 4
							
							if(tile[i][j].ctr == 2 || tile[i][j].ctr == 3 || tile[i][j].ctr == 4) // sayop
								value +=2; // 5
						}
					} else
						aiOrbs += 1; // not sure
				}
			}
			value += myOrbs;
			if(aiOrbs == 0 && myOrbs > 1)
				return (float)10000; // win // 6
			else if(myOrbs == 0 && aiOrbs > 1)
				return (float)-10000; // lose // 7
			// 8 wa pa
			
			//System.out.println(value);
			return (float)value;
		}
		
		private class PseudoTile{
			byte player = 0;
			byte ctr = 0;
		}
	}
}