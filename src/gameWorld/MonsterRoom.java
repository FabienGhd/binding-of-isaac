package gameWorld;
import gameobjects.*;
import gameWorld.*;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ObjectInfos;

import java.util.ArrayList;
import java.util.Random;

public class MonsterRoom extends Room {

	public MonsterRoom(Hero hero) {
		super(hero);	
		setAccessOtherRooms(false);	
	}
	
	
	public void generate() {
		
		
		ArrayList<Integer> positionsX = new ArrayList<>();
		ArrayList<Integer> positionsY = new ArrayList<>();
		
		for(int i = 2; i < 7; i++) {
			positionsX.add(i);
			positionsY.add(i);
		}
		
		Random random = new Random(); 
		
		//random generation of monsters
		for(int i = 0; i < 3; i++) {
			
			int randomIndexX = random.nextInt(positionsX.size());
			int randomIndexY = random.nextInt(positionsY.size());
			
			int mobNbr = random.nextInt(2); //generates a random integer between 0 and 1
			
			int coordinatesX = positionsX.get(randomIndexX); 
			int coordinatesY = positionsY.get(randomIndexY);
			
			positionsX.remove(randomIndexX);
			positionsY.remove(randomIndexY);
			
			Vector2 pos = Room.positionFromTileIndex(coordinatesX, coordinatesY);

			if(mobNbr == 0) {
				getMobs().add(new Fly(pos, getHero()));
			} 	
			else {
				getMobs().add(new Spider(pos, getHero()));
			}
		}
		
		//random generation of obstacles	
		for(int i = 0; i < 2; i++) {
		
			int mobNbr = random.nextInt(2); 
			
			int randomIndexX = random.nextInt(positionsX.size());
			int randomIndexY = random.nextInt(positionsY.size());
			
			int coordinatesX = positionsX.get(randomIndexX); 
			int coordinatesY = positionsY.get(randomIndexY);
			
			positionsX.remove(randomIndexX);
			positionsY.remove(randomIndexY);
			
			Vector2 pos = Room.positionFromTileIndex(coordinatesX, coordinatesY);
	
			if(mobNbr == 0) {
				getObstacles().add(new Spikes(pos, getHero().getSize()));
			} 	
			else {
				getObstacles().add(new Rock(pos, getHero().getSize()));
				}
		}
	}
	
	public void updateRoom() {
		super.updateRoom();
		if(isAccessOtherRooms()) {
			generateStuff();
		}
	}
	
	private void generateStuff() {
		//TODO
	}
	
	public void draw() {
	}
}
