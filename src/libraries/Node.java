package libraries;

import gameWorld.BossRoom;
import gameWorld.MonsterRoom;
import gameWorld.Room;
import gameWorld.ShopRoom;
import gameobjects.Hero;

public class Node {
	
	private Room room;
	private Node child1; // HAUT
	private Node child2; // BAS
	private Node child3; // GAUCHE
	private Node child4; // DROITE
	private Node parent;
	private int nbChild; // Nombre de rooms liees
	private Hero hero;
	
	private boolean bossPlaced;
	private boolean shopPlaced;
	
	
	public Node(Room r, int nbC, Hero h, boolean bossP, boolean shopP) {
		generate();
		hero = h;
	}
	
	private void generate() {
		double random = Math.random();
		double coef = 2/4;
		
		Room roomType = null;
		
		if(bossPlaced) coef += 1/4;
		if(shopPlaced) coef += 1/4;
		
		if(random < coef) {
			roomType = new MonsterRoom(hero); // Monster
		}
		else if(random < coef + 1/4 && !shopPlaced) {
			roomType = new ShopRoom(hero); // Shop
			shopPlaced = true;
		}
		else if(!shopPlaced) {
			roomType = new BossRoom(hero); // Boss
			bossPlaced = true;
		}
		
		
		if(nbChild == 3) {
			child1 = new Node(roomType, nbChild - 1, hero, bossPlaced, shopPlaced);
			child2 = null;
			child4 = null;
		}
		else if(nbChild == 2) {
			child1 = null;
			child2 = null;
		}
		else if(nbChild == 1) {
			
		}
		else {
			
		}
	}
	
}
