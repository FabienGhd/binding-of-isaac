package gameWorld;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Hero;

public class Dungeon {
	
	private Hero hero;
	public Room[][] dung;
	
	public Dungeon(Hero h) {
		hero = h;
		generate();
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
		dung[2][0].setRightRoom(spawn);
		shop_doors.add(null) //TODO
		
		dung[0][1] = boss; // Boss
		dung[0][1].setBottomRoom(mr1);
		
		dung[1][1] = mr1; // MR1
		dung[1][1].setTopRoom(boss);
		dung[1][1].setBottomRoom(spawn);
		
		dung[2][1] = spawn; // Spawn
		dung[1][1].setTopRoom(mr1);
		dung[1][1].setBottomRoom(mr2);
		dung[2][0].setRightRoom(mr3);
		dung[2][0].setLeftRoom(shop);
		
		dung[3][1] = mr2; // MR2
		dung[1][1].setTopRoom(spawn);
		
		dung[2][2] = mr3; // MR3
		dung[2][0].setLeftRoom(spawn);
	}
	
}