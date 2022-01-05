package gameWorld;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
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
		processOtherKeys();
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
	public void generateDoor() {
		Vector2 hero_pos = hero.getPosition();

		for (Door door : currentRoom.Door()) {
			
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
	
	private void processOtherKeys() {
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
