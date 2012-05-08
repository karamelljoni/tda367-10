package tetrix.view;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tetrix.core.BlockBox;
import tetrix.core.Bullet;
import tetrix.core.Cannon;
import tetrix.core.CollisionHandler;
import tetrix.core.HighScore;
import tetrix.core.Player;
import tetrix.core.Position;
import tetrix.util.Util;
import tetrix.view.StateHandler.States;

/**
 * Class responsible for updating and rendering of the gameplay view.
 * @author Magnus Huttu, Linus Karlsson
 *
 */
public class GameplayView extends BasicGameState {

	private int stateID;

	private Image background;
	private Image cannonImage;
	private Cannon cannon;
	private List<Bullet> bulletList;
	private Bullet bullet; 
	private BlockBox blockBox;
	private Image block;
	private int p = 0;
	private List<Image> blocks;
	private CollisionHandler ch;

	private UnicodeFont scoreDisplay;
	private Player player;
	private HighScore highScore;

	public GameplayView(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background= new Image("img/game_background.png");
		cannonImage = new Image("img/cannon2.png");
		block = new Image("img/block/purple.png");
		cannon = new Cannon();
		bulletList = new ArrayList<Bullet>();
		blockBox = new BlockBox();
		blocks = new ArrayList<Image>();
		ch = new CollisionHandler(blockBox);
		player = new Player();
		highScore = HighScore.instance();

		Font font = new Font("Verdana", Font.PLAIN,55);

		scoreDisplay = new UnicodeFont(font , 15, true, false);
		scoreDisplay.addAsciiGlyphs();
		scoreDisplay.addGlyphs(400, 600);
		scoreDisplay.getEffects().add(new ColorEffect(java.awt.Color.YELLOW));
		try {
			scoreDisplay.loadGlyphs();
		} catch (SlickException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw(0,0);
		cannonImage.draw(cannon.getX(), cannon.getY());
		scoreDisplay.drawString(5, 0, player.getScore() + "");

		if(blockBox.isInUse()){
			int i = 0;

			blocks.clear();

			for(int o = 0; o < blockBox.getTetroList().size()*4; o++)
				blocks.add(block);
			blockBox.update();

			for(Position[] p : blockBox.getPos()){
				for(Position pe : p){
					blocks.get(i).draw(pe.getX(), pe.getY());
					i++;
				}
			}
		}

		g.setColor(Color.black);
		for(int i = 0; i < bulletList.size(); i++){
			g.fillRect(((Bullet) bulletList.get(i)).getX(), ((Bullet) bulletList.get(i)).getY(), 5, 5);

		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		int updateSpeed = 500 /Util.FPS;



		if(input.isKeyDown(Input.KEY_M)) {
			player.setName("Mushu");
			highScore.addToHighScore(player);
			sbg.enterState(States.HIGHSCOREVIEW.getID());
		}

		if(blockBox.isRowFilled()) {
			player.setScore(20);
		}

		if(input.isKeyDown(Input.KEY_0)) {
			blockBox.clearRow(445);
		}

		if(input.isKeyDown(Input.KEY_RIGHT)) {
			cannon.move(updateSpeed);
		}

		if(input.isKeyDown(Input.KEY_LEFT)) {
			cannon.move(-updateSpeed);
		}

		if(input.isKeyDown(Input.KEY_D)) {
			cannon.move(updateSpeed);
		}

		if(input.isKeyDown(Input.KEY_A)) {
			cannon.move(-updateSpeed);
		}

		if(input.isKeyPressed(Input.KEY_ENTER)) {
			blockBox.newBlock((int)(Math.random()*7+0.5));
		}

		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			blockBox.newBlock((int)(Math.random()*7+0.5));
		}

		if(input.isKeyPressed(Input.KEY_SPACE)) {
			bullet = new Bullet(cannon.getPosition(), cannon.getValue());
			bulletList.add(bullet);
		}

		if(input.isKeyPressed(Input.KEY_P)) {
			blockBox.clearBoard();
		}


		int size = bulletList.size();
		for(int i = 0; i < size; i++){
			if(!ch.checkCollision(bulletList.get(i))){
				bulletList.get(i).update();
			} else{
				bulletList.remove(i);
				size--;
			}
		}

		cannonImage.setRotation(cannon.getRotation());
	}

	@Override
	public int getID() {
		return stateID;
	}

}
