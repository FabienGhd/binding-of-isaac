package gameobjects;

import libraries.Vector2;
import resources.FlyInfos;
import resources.ImagePaths;

public class Fly extends Monster {
	
	private int projectile_damage;
	private double projectile_speed;
	private int reach;

	public Fly(Vector2 position, Hero hero) {
		super(position, hero);
		setSize(FlyInfos.FLY_SIZE);
		setImagePath(ImagePaths.FLY);
		setSpeed(FlyInfos.FLY_SPEED);
		setDamage(FlyInfos.FLY_DAMAGE);
		setHealth(FlyInfos.FLY_HEALTH);
		setCollide(false);
		projectile_damage = FlyInfos.PROJECTILE_DAMAGE;
		projectile_speed = FlyInfos.PROJECTILE_SPEED;
		reach = FlyInfos.REACH;
		
	}
	
	public void updateGameObject() {
		setOld_pos(getPosition());
		move();
		shoot();
	}
	
	public void move() {
	
		Vector2 new_dir = new Vector2(getHero().getPosition().getX() - getPosition().getX(), getHero().getPosition().getY() - getPosition().getY());
		setDirection(new_dir);
		
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
	}
	
	private void shoot() {
		
	}
}
