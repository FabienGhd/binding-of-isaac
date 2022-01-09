package gameWorld;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
import resources.HeroInfos;
import resources.RoomInfos;

import java.util.Date;

import gameloop.Main;


public class GameWorld 
{
	private Room currentRoom;
	private Hero hero;
	private long lastInput;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		currentRoom = new Room(hero);
		lastInput = System.currentTimeMillis();
	}

	public void processUserInput()
	{
		processKeysForMovement();
		processKeysForShooting();
		processKeysForCheating();
	}

	//TODO
	public boolean gameOver()
	{
		/*
		if(hero.getHealth() == 0) {
			StdDraw.picture(0.7, 0.7, ImagePaths.GAMEOVER_SCREEN); //test
			System.out.println("game over man");
			return true;
		}
		*/
		return false;
		
	}

	public void updateGameObjects()
	{
		generateDoor();
		currentRoom.updateRoom();
	}

	public void drawGameObjects()
	{
		currentRoom.drawRoom();
	}
	
	//TODO 
	//temporary cuz not organized at all, jsut to test real quick
	public void generateDoor() {
		Vector2 hero_pos = hero.getPosition();
		Vector2 up = new Vector2(0.5,0.87);
		Vector2 left = new Vector2(0.13,0.5);
		Vector2 right = new Vector2(0.87,0.5);
		Vector2 down = new Vector2(0.5,0.13);

		for (Door door : currentRoom.Door()) {
			if(hero_pos == up || hero_pos == right ||hero_pos == left || hero_pos == down) {
				this.currentRoom = door.getNextRoom();
				hero.setPosition(RoomInfos.POSITION_CENTER_OF_ROOM);
			}
		}
	}

	/*
	 * Keys processing
	 */

	private void processKeysForMovement()
	{
		if (StdDraw.isKeyPressed(Controls.goUp))
		{
			hero.goUpNext();
		}
		if (StdDraw.isKeyPressed(Controls.goDown))
		{
			hero.goDownNext();
		}
		if (StdDraw.isKeyPressed(Controls.goRight))
		{
			hero.goRightNext();
		}
		if (StdDraw.isKeyPressed(Controls.goLeft))
		{
			hero.goLeftNext();
		}
	}
	
	private void processKeysForCheating() {
		// Money - o
		if(StdDraw.isKeyPressed(Controls.cheatMoney) && (System.currentTimeMillis() - lastInput > 50)) {
			hero.setCoin(hero.getCoin() + 10);
			lastInput = System.currentTimeMillis();
		}
		
		// Speed
		if (StdDraw.isKeyPressed(Controls.cheatSpeed) && (System.currentTimeMillis() - lastInput > 200)) {
			//so that the speedCheat is not permanent, we just have to click again on the the 'l' to make the hero back at his speed
			if (hero.getSpeed() != HeroInfos.ISAAC_SPEED) {
				hero.setSpeed(HeroInfos.ISAAC_SPEED);
			} else {
				hero.setSpeed(hero.getSpeed() + 0.02);
			}
			lastInput = System.currentTimeMillis();
		}
		
		// Kill mobs - k
		if(StdDraw.isKeyPressed(Controls.cheatKill)) { //makes all monsters disappear;
			currentRoom.removeAllMonsters();
			lastInput = System.currentTimeMillis();
		}
		
		// Invincible - i
		if(StdDraw.isKeyPressed(Controls.invincible) && (System.currentTimeMillis() - lastInput > 200)) {
			if(!hero.getInvincible()) {
				hero.setInvincible(true);
				hero.setDelay_invincible(-1); // Invincible locked
			} 
			else {
				hero.setInvincible(false);
			}
			lastInput = System.currentTimeMillis();
		}
		
		// Power - p
		if(StdDraw.isKeyPressed(Controls.cheatDamage) && (System.currentTimeMillis() - lastInput > 200)) {
			if(hero.getDamage() == HeroInfos.DAMAGE) {
				hero.setDamage(100);
			}
			else {
				hero.setDamage(HeroInfos.DAMAGE);
			}
			lastInput = System.currentTimeMillis();
			System.out.println(hero.getDamage());
		}
		
	}
	
	private void processKeysForShooting() {
		if (StdDraw.isKeyPressed(Controls.shootUp))
		{
			currentRoom.shoot(new Vector2(0, 1));
		}
		if (StdDraw.isKeyPressed(Controls.shootDown))
		{
			currentRoom.shoot(new Vector2(0, -1));
		}
		if (StdDraw.isKeyPressed(Controls.shootRight))
		{
			currentRoom.shoot(new Vector2(1, 0));
		}
		if (StdDraw.isKeyPressed(Controls.shootLeft))
		{
			currentRoom.shoot(new Vector2(-1, 0));
		}
	}
	
	
	
}
