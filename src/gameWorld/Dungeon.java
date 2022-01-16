package gameWorld;

import gameobjects.Hero;
import libraries.Node;

public class Dungeon {
	
	private boolean shopPlaced;
	private boolean bossPlaced;
	public Node root;
	private int nbChild;
	private Hero hero;
	
	public Dungeon(Hero h) {
		hero = h;
		this.root = generate();
		nbChild = 3;
	}
	
	public Node generate() {
		Node r = new Node(new Spawn(hero), nbChild, hero, false, false);
		return root;
	}
	
}