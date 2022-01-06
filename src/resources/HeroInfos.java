package resources;

import libraries.Vector2;

public class HeroInfos
{
	public static Vector2 ISAAC_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
	public static final double ISAAC_SPEED = 0.01;

	public static final double HEALTH = 6;
	public static final double COINS = 0;
	public static final double DAMAGE = 1;
	public static final double REACH = 20; // Nombre de ticks avant depop
	public static final double PROJECTILE_SPEED = ISAC_SPEED*1.5;
	
}
