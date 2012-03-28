package tetrix.core;

public class Player{
	private int score;
	private String name;
	
	public Player() {
		score = 0;
	}
	
	public Player(int score, String name) {
		this.score = score;
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore() {
		this.score = score;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
