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
		
		//TODO:review coordinates, collision monster and obstacle. 
		
		//random generation of monsters
		for(int i = 0; i < 3; i++) {
			
			int mobNbr = random.nextInt(2); //generates a random integer between 0 and 1
			double coordinatesX = random.nextDouble(0.5)+0.3; ////generates a random double between 0.2 and 0.7 
			double coordinatesY = random.nextDouble(0.5)+0.3;

			if(mobNbr == 0) {
				mobs.add(new Fly(new Vector2(coordinatesX, coordinatesY), hero));
			} 	
			else {
				mobs.add(new Spider(new Vector2(coordinatesX, coordinatesY), hero));
			}
		}
		
		//random generation of obstacles	
		for(int i = 0; i < 2; i++) {
		
		int mobNbr = random.nextInt(2); 
		
		double coordinatesX = random.nextDouble(0.5)+0.3; 
		double coordinatesY = random.nextDouble(0.5)+0.3;

		if(mobNbr == 0) {
			obstacles.add(new Spikes(new Vector2(coordinatesX, coordinatesY), hero.getSize()));
		} 	
		else {
			obstacles.add(new Rock(new Vector2(coordinatesX, coordinatesY), hero.getSize()));
		}
	}
		
		
		
	}
	
	public void draw() {
		super.drawRoom();
	}
}
