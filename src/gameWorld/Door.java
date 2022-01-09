package gameWorld;

import libraries.Vector2;

public class Door {
	private Room nextRoom;
	private Vector2 position; //Changer en int? 0: Haut ; 1: Droite ; 2 : Bas ; ..
	private Vector2 size;
	private String imagePath;
	
	public Door(Vector2 position, Vector2 size, String imagePath, Room nextRoom) {
		this.position = position;
		this.size = size;
		this.imagePath = imagePath;
		this.nextRoom = nextRoom;
	}

	public Room getNextRoom() {
		return nextRoom;
	}

	public void setNextRoom(Room nextRoom) {
		this.nextRoom = nextRoom;
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
