package resources;

import libraries.Vector2;

public class GaperInfos {
	
	//not stated in the instructions so we set those information freely ourselves
	public static Vector2 GAPER_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
	public static final double GAPER_SPEED = 0.005; //slower than the hero so it's playable
	public static final int GAPER_DAMAGE = 2;
	public static final int GAPER_HEALTH = 5;

}
