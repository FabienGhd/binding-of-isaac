package gameobjects;

import libraries.Vector2;
import resources.FlyInfos;
import resources.ImagePaths;

public class Fly extends Monster {

	public Fly(Vector2 position) {
		super(position);
		setSize(FlyInfos.FLY_SIZE);
		setImagePath(ImagePaths.FLY);
		setSpeed(FlyInfos.FLY_SPEED);
		setDamage(FlyInfos.FLY_DAMAGE);
		setHealth(FlyInfos.FLY_HEALTH);
	}
}
