package resources;

import gameobjects.PickableObject;
import libraries.Vector2;

public class ObjectInfos {
	
	public static final Vector2 COIN_SIZE = new Vector2(0.03, 0.03);
	public static final Vector2 LOOT_SIZE = HeroInfos.ISAAC_SIZE;
	public static final Vector2 HEART_SIZE = new Vector2(0.03, 0.03);
	
	public static PickableObject PENNY = new PickableObject(
			new Vector2(0, 0), COIN_SIZE, ImagePaths.COIN, false, 1, 0, 0, 0, 0);
	
	public static PickableObject NICKEL = new PickableObject(
			new Vector2(0, 0), COIN_SIZE, ImagePaths.NICKEL, false, 5, 0, 0, 0, 0);
	
	public static PickableObject DIME = new PickableObject(
			new Vector2(0, 0), COIN_SIZE, ImagePaths.DIME, false, 10, 0, 0, 0, 0);
	
	
	public static PickableObject HALF_HEART = new PickableObject(
			new Vector2(0, 0), HEART_SIZE, ImagePaths.HALF_HEART_PICKABLE, false, 0, 1, 0, 0, 0);
	
	public static PickableObject FULL_HEART = new PickableObject(
			new Vector2(0, 0), HEART_SIZE, ImagePaths.HEART_PICKABLE, false, 0, 2, 0, 0, 0);
	
	
	public static PickableObject hp_up = new PickableObject(
			new Vector2(0, 0), HeroInfos.ISAAC_SIZE, ImagePaths.HP_UP, false, 0, 0, 2, 0, 0);
	
	public static PickableObject blood_of = new PickableObject(
			new Vector2(0, 0), LOOT_SIZE, ImagePaths.BLOOD_OF_THE_MARTYR, false, 0, 0, 0, 1, 0);
	

	public static PickableObject FULL_HEART_SHOP = new PickableObject(
			new Vector2(0, 0), HEART_SIZE, ImagePaths.HEART_PICKABLE, false, 0, 2, 0, 0, 15);
	
	public static PickableObject blood_of_shop = new PickableObject(
			new Vector2(0, 0), LOOT_SIZE, ImagePaths.BLOOD_OF_THE_MARTYR, false, 0, 0, 0, 1, 30);
}
