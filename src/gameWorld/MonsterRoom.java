package gameWorld;
import gameobjects.*;
import gameWorld.*;
import libraries.StdDraw;
import libraries.Vector2;

public class MonsterRoom extends Room {

	public MonsterRoom(Hero hero) {
		super(hero);
		
		mobs.add(new Spider(new Vector2(0.3, 0.5), hero));
		mobs.add(new Fly(new Vector2(0.8, 0.5), hero));
	
	}
	
	public void draw() {
		super.drawRoom();
	}
}
