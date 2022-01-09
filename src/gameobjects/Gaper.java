package gameobjects;
import libraries.Vector2;
import resources.GaperInfos;
import resources.ImagePaths;

public class Gaper extends Monster {
	
	private int count = 0;
		
	public Gaper(Vector2 position, Hero hero) {
		super(position, hero);
		setSize(GaperInfos.GAPER_SIZE);
		setImagePath(ImagePaths.GAPER);
		setSpeed(GaperInfos.GAPER_SPEED);
		setDamage(GaperInfos.GAPER_DAMAGE);
		setHealth(GaperInfos.GAPER_HEALTH);
		setCollide(false);
	}
	
	public void updateGameObject() {
		setOld_pos(getPosition());
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

		
}
