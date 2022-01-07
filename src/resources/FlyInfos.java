package resources;

import libraries.Vector2;

public class FlyInfos {
	
	public static Vector2 FLY_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
	public static final double FLY_SPEED = HeroInfos.ISAAC_SPEED/8.0;
	public static final int FLY_HEALTH = 3;
	public static final int FLY_DAMAGE = 1;

	public static final int PROJECTILE_DAMAGE = 1;
	public static final double PROJECTILE_SPEED = 1;
	public static final int REACH = 1;

}
