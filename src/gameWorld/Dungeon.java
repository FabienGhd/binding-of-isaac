package gameWorld;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Hero;
import resources.DoorInfos;

public class Dungeon {
	
	private Hero hero;
	private Room[][] dung;
	private Room currentRoom;
	
	public Dungeon(Hero h) {
		hero = h;
		dung = new Room[4][3];
		generate();
		currentRoom = dung[2][1];
	}
	
	public void generate() {
		MonsterRoom mr1 = new MonsterRoom(hero);
		ArrayList<Door> mr1_doors = new ArrayList<>();
		
		MonsterRoom mr2 = new MonsterRoom(hero);
		ArrayList<Door> mr2_doors = new ArrayList<>();
		
		MonsterRoom mr3 = new MonsterRoom(hero);
		ArrayList<Door> mr3_doors = new ArrayList<>();
		
		
		Spawn spawn = new Spawn(hero);
		ArrayList<Door> spawn_doors = new ArrayList<>();
		
		ShopRoom shop = new ShopRoom(hero);
		ArrayList<Door> shop_doors = new ArrayList<>();
		
		BossRoom boss = new BossRoom(hero);
		ArrayList<Door> boss_doors = new ArrayList<>();
		
		
		// De gauche a droite, de haut en bas :
		
		dung[2][0] = shop; // Shop
		shop.getDoors().add(new Door("RIGHT", spawn));
		
		dung[0][1] = boss; // Boss
		boss.getDoors().add(new Door("DOWN", mr1));
		
		dung[1][1] = mr1; // MR1
		mr1.getDoors().add(new Door("DOWN", spawn));
		mr1.getDoors().add(new Door("UP", boss));
		
		dung[2][1] = spawn; // Spawn
		spawn.getDoors().add(new Door("UP", mr1));
		spawn.getDoors().add(new Door("RIGHT", mr3));
		spawn.getDoors().add(new Door("DOWN", mr2));
		spawn.getDoors().add(new Door("LEFT", shop));
		
		dung[3][1] = mr2; // MR2
		mr2.getDoors().add(new Door("UP", spawn));
		
		dung[2][2] = mr3; // MR3d
		mr3.getDoors().add(new Door("LEFT", spawn));
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Room[][] getDung() {
		return dung;
	}

	public void setDung(Room[][] dung) {
		this.dung = dung;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	
	
}