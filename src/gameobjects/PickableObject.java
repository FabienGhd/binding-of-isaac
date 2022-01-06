package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;

public class PickableObject {
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private boolean taken;
	private int coins;
	private int health;
	
	
	public PickableObject(Vector2 position, Vector2 size, String imagePath, boolean taken, int coins, int health) {
		this.position = position;
		this.size = size;
		this.imagePath = imagePath;
		this.taken = taken;
		this.coins = coins;
		this.health = health;
	}
	
	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
}
