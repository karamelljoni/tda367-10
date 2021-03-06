package tetrix.core;

import tetrix.core.tetrominos.I;
import tetrix.core.tetrominos.J;
import tetrix.core.tetrominos.L;
import tetrix.core.tetrominos.O;
import tetrix.core.tetrominos.S;
import tetrix.core.tetrominos.Square;
import tetrix.core.tetrominos.T;
import tetrix.core.tetrominos.Tetromino;
import tetrix.core.tetrominos.Z;
import tetrix.util.Util;

public class TetrominoFactory {
	Tetromino t;
	Tetromino I;
	Tetromino J;
	Tetromino L;
	Tetromino O;
	Tetromino T;
	Tetromino S;
	Tetromino Z;



	public TetrominoFactory(){
		Tetromino I = new I(0, null);
		Tetromino J = new J(0, null);
		Tetromino L = new L(0, null);
		Tetromino O = new O(0, null);
		Tetromino T = new T(0, null);
		Tetromino S = new S(0, null);
		Tetromino Z = new Z(0, null);
	}

	public void createRandomTetromino(BlockBox bB) {		
		createTetromino(bB, (int) (Math.random()*6));

	}

	public void createTetromino(BlockBox bB, int whichBlock) {		
		System.out.println(whichBlock);
		switch(whichBlock) {
		case 0:
			bB.addMino(new I((int)(Math.random()*7), bB));
			break;
		case 1:
			bB.addMino(new J((int)(Math.random()*8), bB));
			break;
		case 2:
			bB.addMino(new L((int)(Math.random()*8), bB));
			break;
		case 3:
			bB.addMino(new O((int)(Math.random()*9), bB));
			break;
		case 4:
			bB.addMino(new S((int)(Math.random()*8), bB));
			break;
		case 5:
			bB.addMino(new T((int)(Math.random()*8), bB));
			break;
		case 6:
			bB.addMino(new Z((int)(Math.random()*8), bB));
			break;
		}

	}



	public void createBrokenTetromino(BlockBox bB, Tetromino te, int newSqr, Position pos, int x) {	
		System.out.println("Creating broken tetromino" + newSqr + "    x: " + pos.getX() + "    y: " + pos.getY());

		if(t.toString().equals("I")){
			t = new I(x, bB);
		}else if(t.toString().equals("J")){
			t = new J(x, bB);
		}else if(t.toString().equals("L")){
			t = new L(x, bB);
		}else if(t.toString().equals("O")){
			t = new O(x, bB);
		}else if(t.toString().equals("T")){
			t = new T(x, bB);
		}else if(t.toString().equals("S")){
			t = new S(x, bB);
		}else if(t.toString().equals("Z")){
			t = new Z(x, bB);
		}
		
		Square[] s = t.getSquares();
		s[0].destroy();
		s[0].use();
		s[1].destroy();
		s[1].use();
		s[2].destroy();
		s[2].use(); 
		s[3].destroy();
		s[3].use();
		s[newSqr].setY(pos.getY()); 
		s[newSqr].unDestroy();
		s[newSqr].unUse();

		bB.addMino(t);
	}
}

