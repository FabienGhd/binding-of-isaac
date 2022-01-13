package gameWorld;
import gameobjects.*;
import gameWorld.*;
import libraries.StdDraw;
import libraries.Vector2;
import java.util.Random;

public class MonsterRoom extends Room {

	public MonsterRoom(Hero hero) {
		super(hero);
	
		Random random = new Random(); 
		
		//TODO:making sure 2 spiders don't spawn at the same position maybe. 
		for(int i = 0; i < 3; i++) {
			
			int mobNbr = random.nextInt(2); //generates a random integer between 0 and 1
			double coordinatesX = random.nextDouble(0.7)+0.2; ////generates a random double between 0.2 and 0.7 
			double coordinatesY = random.nextDouble(0.7)+0.2;

			if(mobNbr == 0) {
				mobs.add(new Fly(new Vector2(coordinatesX, coordinatesY), hero));
			} 	
			else {
				mobs.add(new Spider(new Vector2(coordinatesX, coordinatesY), hero));
			}
		}
		
	}
	
	public void draw() {
		super.drawRoom();
	}
}
