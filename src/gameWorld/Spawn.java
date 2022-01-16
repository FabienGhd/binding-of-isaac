package gameWorld;
import gameobjects.*;
import gameWorld.*;
import libraries.StdDraw;
import libraries.Vector2;


public class Spawn extends Room {

	public Spawn(Hero hero) {
		super(hero);
		
		//no monsters and obstacles in the spawn room
		
	}
	
	public void generate() {}
	
	public void draw() {
		super.drawRoom();
		
	}
}
