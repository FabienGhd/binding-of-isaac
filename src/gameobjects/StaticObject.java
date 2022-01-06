package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;

public class StaticObject {
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private int damage; // degats infliges (pour piques)
	private boolean block; // bloque le passage (pour rocher)
	
	public StaticObject(Vector2 position, Vector2 size, String imagePath, int d, boolean b) 	{
		this.position = position;
		this.size = size;
		this.imagePath = imagePath;
		this.damage = d;
		this.block = b;
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

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean doBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}
	

}
