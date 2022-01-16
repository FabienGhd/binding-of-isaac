package gameobjects;
import libraries.Vector2;
import resources.FlyInfos;
import resources.GaperInfos;
import resources.HeroInfos;
import resources.ImagePaths;

public class Gaper extends Monster {
	
	private int projectile_damage;
	private double projectile_speed;
	private int reach;

	private long lastShoot;
	
	private int count = 0;
		
	public Gaper(Vector2 position, Hero hero) {
		super(position, hero);
		setSize(GaperInfos.GAPER_SIZE);
		setImagePath(ImagePaths.GAPER);
		setSpeed(GaperInfos.GAPER_SPEED);
		setDamage(GaperInfos.GAPER_DAMAGE);
		setHealth(GaperInfos.GAPER_HEALTH);
		setCollide(true);
		projectile_damage = FlyInfos.PROJECTILE_DAMAGE;
		projectile_speed = HeroInfos.ISAAC_SPEED*1.5;
		reach = FlyInfos.REACH;
		setShoot(true);
		setLastShoot(System.currentTimeMillis());
	}
	
	public void updateGameObject() {
		setOld_pos(getPosition());
		isGaperDead();
		move();
	}
	
	//gaper is supposed to follow Isaac
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
	
	public boolean isGaperDead() {
		return getHealth() == 0;
	}


	public long getLastShoot() {
		return lastShoot;
	}

	public void setLastShoot(long lastShoot) {
		this.lastShoot = lastShoot;
	}
		
}
