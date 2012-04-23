package tetrix.core;

public abstract class Tetromino {
	private Square[] square;
	private int startX;
	private boolean[] hasSquare;
	private int fallspeed;
	private boolean stop;
	private int leftIn;
	
	
	public Tetromino(int startX){
		this(startX, 150, 20);
	}

	public Tetromino(int startX, int leftIn){
		this(startX, leftIn, 20);
	}

	public Tetromino(int startX, int leftIn, int fallspeed) {
		hasSquare = new boolean[8];
		this.startX = startX;
		this.fallspeed = fallspeed;
		square = new Square[4];
		this.leftIn = leftIn;
		build();
		stop = false;
	}
	

	public void init(){
		for(boolean b : hasSquare)
			b = false;
	}
	
	public void build(){
		
	}

	public float falling(float f){
		return f+fallspeed;
	}

	public void update(){
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
		stop = true;
	}
	
	public boolean isMoving(){
		return stop;
	}
	
	public int getStartX(){
		return startX;
	}
	
	public int getLeftIn(int addera){
		return (leftIn+addera);
	}
	
	public boolean isPainted(float y, float x){
		return false;
	}
	
	
}
