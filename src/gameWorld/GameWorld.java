package gameWorld;

import gameobjects.Hero;
import gameobjects.Projectile;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.Controls;
import resources.DoorInfos;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

import java.util.Date;

import gameloop.Main;


public class GameWorld 
{
	private Dungeon dungeon;
	private Room currentRoom;
	private Hero hero;
	private long lastInput;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		lastInput = System.currentTimeMillis();
		dungeon = new Dungeon(hero);
		currentRoom = dungeon.getCurrentRoom();
	}

	public void processUserInput()
	{
		processKeysForMovement();
		processKeysForShooting();
		processKeysForCheating();
	}

	
	public boolean gameOver()
	{
		if(hero.getHealth() == 0) {
			return true;
		}
		return false;
		
	}

	public void updateGameObjects()
	{
		currentRoom.updateRoom();
		accessRooms2();
	}

	public void drawGameObjects()
	{
		currentRoom.drawRoom();
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
		}
		
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	private void processKeysForShooting() {
		if (StdDraw.isKeyPressed(Controls.shootUp))
		{
			shoot(new Vector2(0, 1));
		}
		if (StdDraw.isKeyPressed(Controls.shootDown))
		{
			shoot(new Vector2(0, -1));
		}
		if (StdDraw.isKeyPressed(Controls.shootRight))
		{
			shoot(new Vector2(1, 0));
		}
		if (StdDraw.isKeyPressed(Controls.shootLeft))
		{
			shoot(new Vector2(-1, 0));
		}
	}
	
	/**
	 * Fonction qui gere les entrees clavier pour tirer un projectile
	 */	
	public void shoot(Vector2 dir) {
		if(hero.getCanShoot()) {
			Projectile shot = new Projectile(hero.getPosition(), hero.getSize().scalarMultiplication(0.4),
					ImagePaths.TEAR, hero.getProjectile_speed(), dir, hero.getDamage(), hero.getReach());
			currentRoom.getProjs().add(shot);
			hero.setCanShoot(false);
			hero.setDelay_shoot(0);
		}
	}
	
	
	private void accessRooms2() {
		if(currentRoom.isAccessOtherRooms()) {
			for(Door door : currentRoom.getDoors()) {
				if(Physics.rectangleCollision(door.getAccess_position(), RoomInfos.TILE_SIZE.scalarMultiplication(1.5), hero.getPosition(), hero.getSize()) && (System.currentTimeMillis() - lastInput > 100)) {
					currentRoom = door.getNextRoom();
					hero.getPosition().setX(1 - hero.getPosition().getX());
					hero.getPosition().setY(1 - hero.getPosition().getY()); // Compl???ment ??? 1 de la position	
					
					lastInput = System.currentTimeMillis();			
				}
			}
		}
	}

	
}
