package resources;

import libraries.Vector2;

public class DoorInfos {
	
	public static Vector2 UP_DOOR = new Vector2(0.5, 0.95);
	public static Vector2 DOWN_DOOR = new Vector2(0.5, 0.05);
	public static Vector2 RIGHT_DOOR = new Vector2(0.95, 0.5);
	public static Vector2 LEFT_DOOR = new Vector2(0.05, 0.5);
	
	public static Vector2 UP_DOOR_ACCESS = new Vector2(0.5, 0.99);
	public static Vector2 DOWN_DOOR_ACCESS = new Vector2(0.5, 0.01);
	public static Vector2 RIGHT_DOOR_ACCESS = new Vector2(0.99, 0.5);
	public static Vector2 LEFT_DOOR_ACCESS = new Vector2(0.01, 0.5);

	public static Vector2 DOOR_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(1);
	
	public static String UP_DOOR_PATH = "images/closed_door.png"; // TODO
	public static String DOWN_DOOR_PATH = "images/closed_door.png"; // TODO
	public static String RIGHT_DOOR_PATH = "images/closed_door.png"; // TODO
	public static String LEFT_DOOR_PATH = "images/closed_door.png"; // TODO
	
	public static String UP_DOOR_OPENED_PATH = "images/opened_door.png"; // TODO
	public static String DOWN_DOOR_OPENED_PATH = "images/opened_door.png"; // TODO
	public static String RIGHT_DOOR_OPENED_PATH = "images/opened_door.png"; // TODO
	public static String LEFT_DOOR_OPENED_PATH = "images/opened_door.png"; // TODO


}
