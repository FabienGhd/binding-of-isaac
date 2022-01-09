package resources;

import libraries.Vector2;

public class HeroInfos
{
	public static Vector2 ISAAC_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
	public static final double ISAAC_SPEED = 0.01;

	public static final int HEALTH = 6;
	public static final int COINS = 0;
	public static final int DAMAGE = 1;
	public static final int REACH = 30; // Nombre de ticks avant depop
	public static final double PROJECTILE_SPEED = ISAAC_SPEED*1.5;
	
}
