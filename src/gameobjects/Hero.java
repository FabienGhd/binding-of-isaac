package gameobjects;

import java.util.ArrayList;
import java.util.List;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import libraries.Physics;


public class Hero
{
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private int health;
	private int coin; 
	private boolean canShoot;
	private boolean canMove; // False si son prochain mouvement est invalide


	public Hero(Vector2 position, Vector2 size, double speed, String imagePath, int health, int coin) 
	{
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.imagePath = imagePath;
		this.direction = new Vector2();
		this.health = health;
		this.coin = coin;
		this.canShoot = true;
		this.canMove = true;
	}
	
	// Nouveau constructeur : copie ; il servira pour les collisions.
	public Hero(Hero h) {
		this.position = h.position;
		this.size = h.size;
		this.speed = h.speed;
		this.imagePath = h.imagePath;
		this.direction = new Vector2();
		this.health = h.health;
		this.canShoot = true;
		this.canMove = true;
	}

	public void updateGameObject()
	{
		if(canMove == true) move();
		canMove = true;
	}

	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		
		if(inRoom(positionAfterMoving)) {
			setPosition(positionAfterMoving);
		}
		direction = new Vector2();
	}
	
	//coin values information: https://bindingofisaacrebirth.fandom.com/wiki/Coins
	private void pickableObjects(ArrayList<PickableObject> objects) {
		if(objects != null) { 
			
			List<PickableObject> removeList = new ArrayList<PickableObject>();
			
			for(int i = 0; i < objects.size(); i++) {
				
				if(objects.get(i) != null) {
					
					if(objects.get(i).getImagePath() ==  "images/Penny.png") {
						this.coin += 1;
						removeList.add(objects.get(i));
					}	
					else if(objects.get(i).getImagePath() == "images/Dime.png") {
						this.coin =+ 10;
						removeList.add(objects.get(i));
					}
					else if(objects.get(i).getImagePath() == "images/Nickel.png") {
						this.coin += 5;
						removeList.add(objects.get(i));
					}
					else if(objects.get(i).getImagePath() == "images/Half_Red_Heart.png" && this.health <= 5) {
						this.health += 1;
						removeList.add(objects.get(i));
					}
					else if(objects.get(i).getImagePath() == "images/Red_Heart.png" && this.health <= 4) {
						this.health += 2;
						removeList.add(objects.get(i));
					}
				} 
			}
			//we delete what has been picked 
			for(int i = 0; i < removeList.size(); i++) {
				objects.remove(removeList.get(i));
			}
		}
	}
	
	//map boundaries
	private boolean inRoom(Vector2 position) {
		if(position.getY() > 0.12 && position.getY() < 0.90 && position.getX() > 0.12 && position.getX() < 0.90) {
			return true;
		} else {
			return false;
		}
	}
	

	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
		
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(0.2, 0.025, "Health : " + this.health);
		StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
		StdDraw.text(0.2, 0.055, "Coins : " + this.coin);
		
		
		/* -- HEARTS --
		Isaac's health is 6 
		1 full heart gives 2 health points 
		1/2 hearts gives 1 health point
		*/
		
		if(this.health != 0) { //TODO 
			
			//draw full hearts is Isaac's health is an even number 
			boolean full = (this.health % 2 == 0);
			int fullHearts = this.health / 2; //number of full hearts isaac has
			//draw half hearts if Isaac's health is a odd number
			boolean half = (this.health % 2 == 1);
			
			if(half) {
				StdDraw.picture(0.25, 0.75, ImagePaths.HALF_HEART_HUD); //TEST - random coordinates 
			} else {
				StdDraw.picture(0.05, 0.95, ImagePaths.EMPTY_HEART_HUD);
			}
		}
	}

	/*
	 * Moving from key inputs. Direction vector is later normalised.
	 */
	public void goUpNext()
	{
		getDirection().addY(1);
	}

	public void goDownNext()
	{
		getDirection().addY(-1);
	}

	public void goLeftNext()
	{
		getDirection().addX(-1);
	}

	public void goRightNext()
	{
		getDirection().addX(1);
	}

	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}


	/*
	 * Getters and Setters
	 */
	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	public Vector2 getSize()
	{
		return size;
	}

	public void setSize(Vector2 size)
	{
		this.size = size;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public Vector2 getDirection()
	{
		return direction;
	}

	public void setDirection(Vector2 direction)
	{
		this.direction = direction;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean getCanShoot() {
		return canShoot;
	}

	public void setCanShoot(boolean canShoot) {
		this.canShoot = canShoot;
	}

	public boolean getCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
	
	
}
