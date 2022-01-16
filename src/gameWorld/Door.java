package gameWorld;

import libraries.StdDraw;
import libraries.Vector2;
import resources.DoorInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Door {
	private String direction;
	
	private Room nextRoom;
	
	private String imagePath;
	private String openedPath;
	
	private boolean opened;
	
	private Vector2 access_position;
	private Vector2 position;
	private Vector2 size;
	
	public Door(String direction, Room nextRoom) {
		this.nextRoom = nextRoom;
		opened = false;
		size = DoorInfos.DOOR_SIZE;
		
		if(direction == "UP") {
			position = DoorInfos.UP_DOOR;
			imagePath = DoorInfos.UP_DOOR_PATH;
			access_position = DoorInfos.UP_DOOR_ACCESS;
			openedPath = DoorInfos.UP_DOOR_OPENED_PATH;
			
		}
		else if(direction == "RIGHT") {
			position = DoorInfos.RIGHT_DOOR;
			imagePath = DoorInfos.RIGHT_DOOR_PATH;
			access_position = DoorInfos.RIGHT_DOOR_ACCESS;	
			openedPath = DoorInfos.RIGHT_DOOR_OPENED_PATH;		
		}
		else if(direction == "DOWN") {
			position = DoorInfos.DOWN_DOOR;
			imagePath = DoorInfos.DOWN_DOOR_PATH;
			access_position = DoorInfos.DOWN_DOOR_ACCESS;
			openedPath = DoorInfos.DOWN_DOOR_OPENED_PATH;
		}
		else if(direction == "LEFT") {
			position = DoorInfos.LEFT_DOOR;
			imagePath = DoorInfos.LEFT_DOOR_PATH;
			access_position = DoorInfos.LEFT_DOOR_ACCESS;
			openedPath = DoorInfos.LEFT_DOOR_OPENED_PATH;
		}
	}
	
	public void draw() {
		String image;
		if(opened) image = openedPath;
		else image = imagePath;
		StdDraw.picture(position.getX(), position.getY(), image, size.getX(), size.getY());
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public Vector2 getAccess_position() {
		return access_position;
	}

	public void setAccess_position(Vector2 access_position) {
		this.access_position = access_position;
	}

	public String getOpenedPath() {
		return openedPath;
	}

	public void setOpenedPath(String openedPath) {
		this.openedPath = openedPath;
	}
	
	

}
