package tetrix.core;

import java.util.ArrayList;
import java.util.List;

import tetrix.util.Util;

public abstract class Tetromino {
	private Square[] square;
	private int startX;
	private int fallspeed;
	private boolean isMoving;
	private int leftIn;
	private List<Square> squares;
	
	
	public Tetromino(int startX){
		this(startX, (Util.WINDOW_WIDTH-Util.BOX_WIDTH)/2);
	}

	public Tetromino(int startX, int leftIn){
		this(startX, leftIn, Util.SQUARE_SIZE);
	}
	
	public Tetromino(int startX, int leftIn, int fallspeed) {
		this.startX = startX;
		this.fallspeed = fallspeed;
		square = new Square[4];
		squares = new ArrayList();
		this.leftIn = leftIn;
		isMoving = true;
		build();
		
	}
	
	public void build(){
		
	}

	public float falling(float f){
		return f+fallspeed;
	}

	public void update(){
		for(Square s : squares){
			if(s.destroyed()){
			
			}
		}
		for(Square s : square){
			s.setY(falling(s.getY()));
		}
	}

	public Position[] getPos(){
		Position[] pos = new Position[4];
		int i = 0;
		for(Square s : square){
			
			pos[i] = s.getPos();
			i++;
		}

		return pos.clone();
	}
	
	public Square[] getSquares(){
		return square;
	}
	
	public void stop(){
		isMoving = false;
	}
	
	public boolean isMoving(){
		return isMoving;
	}
	
	public int getStartX(){
		return startX;
	}
	
	public int getLeftIn(int xValue){
		return (leftIn + xValue);
	}
}
