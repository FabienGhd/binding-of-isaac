package gameWorld;
import gameobjects.*;
import gameWorld.*;
import libraries.StdDraw;
import libraries.Vector2;


public class Spawn extends Room {

	public Spawn(Hero hero) {
		super(hero);
		
		//no monsters and obstacles in the spawn room
		
		/*
		obstacles.add(new Rock(new Vector2(0.8, 0.3), hero.getSize()));
		obstacles.add(new Spikes(new Vector2(0.3, 0.3), hero.getSize()));
		obstacles.add(new Rock(new Vector2(0.8, 0.2), hero.getSize()));
		*/
		
	}
	
	public void draw() {
		super.drawRoom();
		
	}
}
