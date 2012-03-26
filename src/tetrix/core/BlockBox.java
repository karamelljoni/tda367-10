package tetrix.core;

public class BlockBox {
	private boolean[][] fallingBlocks;
	private boolean[][] lockedBlocks;
	private Tetromino mino;
	private boolean[][] bMino;
	private Position[][] posi;

	public BlockBox(){
		fallingBlocks = new boolean[10][20];
		lockedBlocks = new boolean[10][20];

		for(int h = 0; h < 20; h++){
			for(int i = 0; i < 10; i++){
				fallingBlocks[i][h] = false;
				lockedBlocks[i][h] = false;
			}
		}
		newBlock();
	}

	public BlockBox(boolean[][] fallingBlocks, boolean[][] lockedBlocks){
		this.fallingBlocks = fallingBlocks.clone();
		this.lockedBlocks = lockedBlocks.clone();

		newBlock();
	}
	
	public void newBlock(){
		mino = new Tetromino();
		bMino = mino.getShape();
		
		for(int h = 0; h < 20; h++){
			for(int i = 0; i < 10; i++){
				if(i < 4 && h < 3){
					fallingBlocks[i][h] = bMino[i][h];
				}
			}
		}
	}
	
	public void falling(){
		//TODO send a tell to blocks so they fall one step.
	}
	
	public void freeze(){
		//TODO put the block to the lockedBlocks matrix.
	}
	
}
