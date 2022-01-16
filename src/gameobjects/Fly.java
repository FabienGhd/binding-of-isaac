package gameobjects;

import libraries.Vector2;
import resources.FlyInfos;
import resources.HeroInfos;
import resources.ImagePaths;

public class Fly extends Monster {
	
	private int projectile_damage;
	private double projectile_speed;
	private int reach;

	private long lastShoot;

	public Fly(Vector2 position, Hero hero) {
		super(position, hero);
		setSize(FlyInfos.FLY_SIZE);
		setImagePath(ImagePaths.FLY);
		setSpeed(FlyInfos.FLY_SPEED);
		setDamage(FlyInfos.FLY_DAMAGE);
		setHealth(FlyInfos.FLY_HEALTH);
		setCollide(false);
		projectile_damage = FlyInfos.PROJECTILE_DAMAGE;
		projectile_speed = HeroInfos.ISAAC_SPEED*1.5;
		reach = FlyInfos.REACH;
		setLastShoot(System.currentTimeMillis());
		
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
	
	public Projectile shoot() {
		Vector2 dir = new Vector2(getHero().getPosition().getX() - getPosition().getX(), getHero().getPosition().getY() - getPosition().getY());
		
		Projectile proj = new Projectile(getPosition(), getSize().scalarMultiplication(0.4), ImagePaths.TEAR, projectile_speed, dir, projectile_damage, reach); // ENEMY TEAR?
		
		return proj;
		
	}

	public int getProjectile_damage() {
		return projectile_damage;
	}

	public void setProjectile_damage(int projectile_damage) {
		this.projectile_damage = projectile_damage;
	}

	public double getProjectile_speed() {
		return projectile_speed;
	}

	public void setProjectile_speed(double projectile_speed) {
		this.projectile_speed = projectile_speed;
	}

	public int getReach() {
		return reach;
	}

	public void setReach(int reach) {
		this.reach = reach;
	}

	public long getLastShoot() {
		return lastShoot;
	}

	public void setLastShoot(long lastShoot) {
		this.lastShoot = lastShoot;
	}
	
	
}
