package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;

public class PickableObject {
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	
	public PickableObject(Vector2 position, Vector2 size, String imagePath) {
		this.position = position;
		this.size = size;
		this.imagePath = imagePath;
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
	
}
