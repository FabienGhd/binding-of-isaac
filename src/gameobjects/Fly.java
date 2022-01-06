package gameobjects;

import libraries.Vector2;
import resources.FlyInfos;
import resources.ImagePaths;

public class Fly extends Monster {
	

	public Fly(Vector2 position, Hero hero) {
		super(position, hero);
		setSize(FlyInfos.FLY_SIZE);
		setImagePath(ImagePaths.FLY);
		setSpeed(FlyInfos.FLY_SPEED);
		setDamage(FlyInfos.FLY_DAMAGE);
		setHealth(FlyInfos.FLY_HEALTH);
		setCollide(false);
	}
	
	public void move() {
	
		Vector2 new_dir = new Vector2(getHero().getPosition().getX() - getPosition().getX(), getHero().getPosition().getY() - getPosition().getY());
		setDirection(new_dir);
		
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
	}
}
