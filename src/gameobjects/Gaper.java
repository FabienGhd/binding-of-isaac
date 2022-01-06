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
	
	//gaper is supposed to follow Isaac
	public void move() {
		if(count == 0) {
			setDirection(new Vector2(Math.random()*2-1, Math.random()*2-1));	
			count++;			
		}
		else if(count < 100) {
			count++;
		}
		else if(count < 120) {
			setDirection(new Vector2());
			count++;
		}
		else {count = 0;}
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
	}

		
}
