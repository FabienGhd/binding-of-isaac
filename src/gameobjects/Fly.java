package gameobjects;

import libraries.Vector2;
import resources.FlyInfos;
import resources.ImagePaths;

public class Fly extends Monster {
	
	private int count = 0; // A supprimer après

	public Fly(Vector2 position) {
		super(position);
		setSize(FlyInfos.FLY_SIZE);
		setImagePath(ImagePaths.FLY);
		setSpeed(FlyInfos.FLY_SPEED);
		setDamage(FlyInfos.FLY_DAMAGE);
		setHealth(FlyInfos.FLY_HEALTH);
		setCollide(false);
	}
	
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
