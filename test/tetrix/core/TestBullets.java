package tetrix.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class TestBullets implements Game{
	private Bullet bullet;
	private List bulletin;
	private Iterator<Bullet> itBullets;
	private PowerBullet pBullet;
	private Position pos;
	private boolean power;
	private boolean shot;
	private int bullets;

	public TestBullets(){
		power = false;
		shot = false;
		bullets = -1;
	}

	public static void main(String[] args){
		try {
			AppGameContainer app = new AppGameContainer(new TestBullets());
			app.setDisplayMode(500, 600, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean closeRequested() {
		return true;
	}

	@Override
	public String getTitle() {
		return "Test for Bullets";
	}

	public void init(GameContainer arg0) throws SlickException {
		pos = new Position(0,300);
		bulletin = new ArrayList();
	}

	public void update(GameContainer container, int arg1) throws SlickException {
		pos = new Position((float)Math.random()*500,0);
		Input input = container.getInput();

		if(input.isKeyPressed(Input.KEY_SPACE)){
			bullet = new Bullet(pos);
			bulletin.add(bullet);
			shot = true;
			power = false;
			bullets++;
		}

		for(int i = 0; i < bullets; i++){
			if(!(((Bullet) bulletin.iterator().next()).getGoing())){
				bulletin.remove(i);
				bullets--;
			}
		}

		if(input.isKeyPressed(Input.KEY_ENTER)){
			pBullet = new PowerBullet(pos);
			shot = true;
			power = true;
		}

		if(shot && power)
			pBullet.update();
		for(int i = 0; i < bullets; i++)
			if(shot && !power)
				((Bullet) bulletin.get(i)).update();

	}

	public void render(GameContainer arg0, Graphics g) throws SlickException {
		g.setColor(Color.white);
		for(int i = 0; i < bullets; i++)
			if(!power && shot)
				g.fillRect(((Bullet) bulletin.get(i)).getPos().getX(), ((Bullet) bulletin.get(i)).getPos().getY(), 5, 5);

		g.setColor(Color.blue);
		if(power && shot)
			g.fillRect(pBullet.getPos().getX(), pBullet.getPos().getY(), 10, 10);
	}
}
