package tetrix;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tetrix.core.Cannon;
import tetrix.core.Util;

public class GameplayView extends BasicGameState {

	private int stateID;
	
	private Image background;
	private Image cannonImage;
	private Cannon cannon;
	
	public GameplayView(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		background= new Image("img/game-background.png");
		cannonImage = new Image("img/cannon2.png");
		cannon = new Cannon(225,525);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		background.draw(0,0);
		cannonImage.draw(cannon.getX(), cannon.getY());	
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			cannon.move(delta);
		}
		
		if(input.isKeyDown(Input.KEY_LEFT)) {
			cannon.move(-delta);
		}
		cannonImage.setRotation(cannon.getRotation());
		
	}

	@Override
	public int getID() {
		return stateID;
	}

}
