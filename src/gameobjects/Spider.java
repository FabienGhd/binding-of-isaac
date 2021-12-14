package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;
import resources.SpiderInfos;

public class Spider extends Monster {
	
	private int count = 0; // A supprimer après

	public Spider(Vector2 position) {
		super(position);
		setSize(SpiderInfos.SPIDER_SIZE);
		setImagePath(ImagePaths.SPIDER);
		setSpeed(SpiderInfos.SPIDER_SPEED);
		setDamage(SpiderInfos.SPIDER_DAMAGE);
		setHealth(SpiderInfos.SPIDER_HEALTH);
	}
	
	public void move() {
		// L'araignee court pendant x ticks, et s'arrete pendant y ticks
		if(count == 0) {
			setDirection(new Vector2(Math.random()*2-1, Math.random()*2-1));	
			count++;			
		}
		else if(count < 15) {
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
