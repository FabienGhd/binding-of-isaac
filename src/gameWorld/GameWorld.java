package gameWorld;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
import resources.HeroInfos;
import resources.RoomInfos;
import gameloop.Main;


public class GameWorld 
{
	private Room currentRoom;
	private Hero hero;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		currentRoom = new Room(hero);
	}

	public void processUserInput()
	{
		processKeysForMovement();
		processKeysForShooting();
		processKeysForCheating();
	}

	public boolean gameOver()
	{
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
		
		if(StdDraw.isKeyPressed(Controls.cheatMoney)) {
			hero.setCoin(hero.getCoin() + 10);
		}
		
		if (StdDraw.isKeyPressed(Controls.cheatSpeed)) {
			//so that the speedCheat is not permanent, we just have to click again on the the 'l' to make the hero back at his speed
			if (hero.getSpeed() != HeroInfos.ISAAC_SPEED + 0.04) { 
				hero.setSpeed(hero.getSpeed() + 0.04);
			} else {
				hero.setSpeed(HeroInfos.ISAAC_SPEED);
			}
		}
		
		if(StdDraw.isKeyPressed(Controls.cheatKill)) { //makes all monsters disappear;
			currentRoom.removeAllMonsters();
		}
		
		if(StdDraw.isKeyPressed(Controls.invincible)) {
			if(hero.getHealth() != HeroInfos.HEALTH + 500) {
				hero.setHealth(hero.getHealth() + 500);
			} else {
				hero.setHealth(HeroInfos.HEALTH);
			}
		}
		
		//TODO: fix tears first 
		if(StdDraw.isKeyPressed(Controls.cheatKill)) {
			
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
