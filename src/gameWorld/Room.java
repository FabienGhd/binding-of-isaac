package gameWorld;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import gameobjects.Fly;
import gameobjects.Gaper;
import gameobjects.Hero;
import gameobjects.Monster;
import gameobjects.Projectile;
import gameobjects.Rock;
import gameobjects.Spider;
import gameobjects.Spikes;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.DoorInfos;
import resources.ImagePaths;
import resources.ObjectInfos;
import resources.RoomInfos;
import gameobjects.StaticObject;
import gameobjects.PickableObject;


public abstract class Room
{
	private Hero hero;
	private String groundTile; //tile for background
	
	 //set to 'public' so we can use them all in other classes
	private ArrayList<StaticObject> obstacles;
	private ArrayList<Monster> mobs;
	private ArrayList<Projectile> projs;
	private ArrayList<Projectile> enemy_proj;
	private ArrayList<PickableObject> pickable;
	private ArrayList<Door> doors;
	
	private boolean accessOtherRooms;
	private Room topRoom;
	private Room bottomRoom;
	private Room rightRoom;
	private Room leftRoom;


	public Room(Hero hero)
	{
		this.setHero(hero);
		this.groundTile = ImagePaths.DEFAULT_TILE;
		this.doors = new ArrayList<Door>(); // 0 : HAUT; 1 : DROITE; 2 : BAS; 3 : GAUCHE
		
		
		this.mobs = new ArrayList<Monster>(); //TEST
		this.projs = new ArrayList<Projectile>();
		this.enemy_proj = new ArrayList<Projectile>();
		this.obstacles = new ArrayList<StaticObject>();
		this.pickable = new ArrayList<PickableObject>();
		
		generate();
	}
	

	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		makeHeroPlay();
		makeMobsPlay();
		makeProjPlay(); // On pourra changer le nom de la fonction
		makeObjPlay();
		roomDone();
		
		
	}
		

	private void roomDone() {
		if(mobs.isEmpty()) {
			accessOtherRooms = true;
			for(Door door : doors) {
				door.setOpened(true);
			}
		}
	}
	

	private void makeHeroPlay()
	{
		getHero().updateGameObject();
		getHero().collision(obstacles, mobs, enemy_proj, pickable);
	}
	
	
	/*
	 * Parcours la liste des mobs presents dans la piece, les fait avancer d'un tick
	 */
	private void makeMobsPlay() {
		List<Monster> removeList = new ArrayList<Monster>();
		
		for(Monster mob : mobs) {
			mob.updateGameObject();
			mob.collision(obstacles);

			if(mob.isShoot() && (System.currentTimeMillis() - mob.getLastShoot() > 700)) {
				enemy_proj.add(mob.shoot());
				mob.setLastShoot(System.currentTimeMillis());
			}
			
			if(mob.getHealth() <= 0) removeList.add(mob); // Mort mob
		}
		
		for(Monster mob: removeList) {
			mobs.remove(mob);
		}
	}
	
	
	private void makeProjPlay() {
		List<Projectile> removeList = new ArrayList<Projectile>();
		for(Projectile proj : projs) {
			proj.updateGameObject();
			
						
			// Gestion reach
			if(proj.getCount() >= proj.getReach()) {
				removeList.add(proj);
			}
			
			// Si hors zone de jeu : kill
			else if(!Physics.rectangleCollision(proj.getPosition(), proj.getSize(), RoomInfos.POSITION_CENTER_OF_ROOM, RoomInfos.TILE_SIZE.scalarMultiplication(7))) {
				removeList.add(proj);
			}
			
			// Collision avec monstres
			else {
				for(int j = 0; j < mobs.size(); j++) {
					if(Physics.rectangleCollision(proj.getPosition(), proj.getSize(), mobs.get(j).getPosition(), mobs.get(j).getSize())) {
						mobs.get(j).attacked(proj.getDamage());
						removeList.add(proj);
					}
				}
			}
		}
		
		// Enemy proj
		for(Projectile proj: enemy_proj) {
			proj.updateGameObject();
			
			
			// Gestion reach
			if(proj.getCount() >= proj.getReach()) {
				removeList.add(proj);
			}
			
			// Si hors zone de jeu : kill
			else if(!Physics.rectangleCollision(proj.getPosition(), proj.getSize(), RoomInfos.POSITION_CENTER_OF_ROOM, RoomInfos.TILE_SIZE.scalarMultiplication(7))) {
				removeList.add(proj);
			}
			
			// Collision avec monstres
			else {
				if(Physics.rectangleCollision(proj.getPosition(), proj.getSize(), hero.getPosition(), hero.getSize())) {
					hero.attacked(proj.getDamage());
					removeList.add(proj);
				}
			}
		}
		
		// Pour eviter l'erreur 'ConcurrentModification'
		for(Projectile proj: removeList) {
			projs.remove(proj);
		}
	}
	
	
	public void makeObjPlay() {
		List<PickableObject> removeList = new ArrayList<PickableObject>();
		
		for(PickableObject obj: pickable) {
			if(obj.isTaken()) removeList.add(obj);
		}
		
		for(PickableObject obj: removeList) {
			pickable.remove(obj);
		}
	}
	
	
	//used for cheating
	public void removeAllMonsters() {
		mobs.clear();
	}
	
	
	public void addObstacle(PickableObject obj, Vector2 pos) {
		obj.setPosition(pos);
		pickable.add(obj);
	}
	
	public void addPickableObject(PickableObject obj, Vector2 pos) {
		obj.setPosition(pos);
		pickable.add(obj);
	}
	
	
	
	public abstract void generate();
	
	
	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		drawTiles();
		drawWalls();
		drawDoors();
		drawEntities();
		
		StdDraw.textLeft(0, 0.9, getHero().getPosition().toString()); //TODO: suppr plus tard
		
	}
	
	private void drawTiles() {
		// For every tile, set background color.
		StdDraw.setPenColor(StdDraw.CYAN);
		for (int i = 0; i < RoomInfos.NB_TILES; i++)
		{
			for (int j = 0; j < RoomInfos.NB_TILES; j++)
			{
				Vector2 position = positionFromTileIndex(i, j);
				
				StdDraw.picture(position.getX(), position.getY(), groundTile, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);

				//StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
						//RoomInfos.HALF_TILE_SIZE.getY());
			}
		}
	}
	
	private void drawWalls() {
		
		//lower left corner (0,0)
		StdDraw.picture(positionFromTileIndex(0, 0).getX(), positionFromTileIndex(0, 0).getY(), ImagePaths.CORNER_DEFAULT_BOTTOMLEFT, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);

		//lower right corner(nb_tiles-1, 0)
		StdDraw.picture(positionFromTileIndex(RoomInfos.NB_TILES - 1, 0).getX(), positionFromTileIndex(RoomInfos.NB_TILES - 1, 0).getY(), ImagePaths.CORNER_DEFAULT_BOTTOMRIGHT, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		
		//top right corner (nb_tiles-1, nb_tiles-1)
		StdDraw.picture(positionFromTileIndex(RoomInfos.NB_TILES - 1, RoomInfos.NB_TILES - 1).getX(), positionFromTileIndex(RoomInfos.NB_TILES - 1, RoomInfos.NB_TILES - 1).getY(), ImagePaths.CORNER_DEFAULT_TOPRIGHT, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		
		//top left corner
		StdDraw.picture(positionFromTileIndex(0, RoomInfos.NB_TILES - 1).getX(), positionFromTileIndex(0, RoomInfos.NB_TILES - 1).getY(), ImagePaths.CORNER_DEFAULT_TOPLEFT, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		
		//draws walls
		for(int i = 1; i < RoomInfos.NB_TILES-1; i++) {
			
			//left walls (x=0, y=i) 
			StdDraw.picture(positionFromTileIndex(0, i).getX(),positionFromTileIndex(0, i).getY(), ImagePaths.WALL_LEFT, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
			//bottom walls (x=i, y=0)
			StdDraw.picture(positionFromTileIndex(i, 0).getX(),positionFromTileIndex(i, 0).getY(), ImagePaths.WALL_BOTTOM, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
			//right walls (x=nb_tiles-1, y=i)
			StdDraw.picture(positionFromTileIndex(RoomInfos.NB_TILES-1, i).getX(),positionFromTileIndex(RoomInfos.NB_TILES-1, i).getY(), ImagePaths.WALL_RIGHT, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
			//top walls (x=i, y=nb_tiles-1)
			StdDraw.picture(positionFromTileIndex(i, RoomInfos.NB_TILES-1).getX(),positionFromTileIndex(i, RoomInfos.NB_TILES-1).getY(), ImagePaths.WALL_TOP, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		}
		
	}
	
	private void drawEntities() {
		//Draws all the mobs for the room
		for(Monster mob : mobs) {
			mob.drawGameObject();
		}
		
		//Draws all the projectiles
		for(Projectile tear : projs) {
			tear.drawGameObject();
		}
		
		for(Projectile tear : enemy_proj) {
			tear.drawGameObject();
		}
		
		//draws the obstacles
		for(StaticObject obs : obstacles) {
			obs.drawGameObject();
		}
		
		//draws pickable objects
		for(PickableObject obj : pickable) {
			obj.drawGameObject();
		}
		
		getHero().drawGameObject();
	}
	
	private void drawDoors() {		
		for(Door door: doors) {
			door.draw();
		}
	}
	
	public boolean isGaperDead() {
		return false;
	}
	
	
	
	
	
	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	public static Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}

	
	
	/*
	 * Getters and Setters
	 */
	public ArrayList<Projectile> getProjs() {
		return projs;
	}

	public void setProjs(ArrayList<Projectile> projs) {
		this.projs = projs;
	}


	public Hero getHero() {
		return hero;
	}


	public void setHero(Hero hero) {
		this.hero = hero;
	}


	public String getGroundTile() {
		return groundTile;
	}


	public void setGroundTile(String groundTile) {
		this.groundTile = groundTile;
	}


	public ArrayList<Door> getDoors() {
		return doors;
	}


	public void setDoors(ArrayList<Door> doors) {
		this.doors = doors;
	}


	public ArrayList<StaticObject> getObstacles() {
		return obstacles;
	}


	public void setObstacles(ArrayList<StaticObject> obstacles) {
		this.obstacles = obstacles;
	}


	public ArrayList<Monster> getMobs() {
		return mobs;
	}


	public void setMobs(ArrayList<Monster> mobs) {
		this.mobs = mobs;
	}


	public ArrayList<Projectile> getEnemy_proj() {
		return enemy_proj;
	}


	public void setEnemy_proj(ArrayList<Projectile> enemy_proj) {
		this.enemy_proj = enemy_proj;
	}


	public ArrayList<PickableObject> getPickable() {
		return pickable;
	}


	public void setPickable(ArrayList<PickableObject> pickable) {
		this.pickable = pickable;
	}


	public boolean isAccessOtherRooms() {
		return accessOtherRooms;
	}


	public void setAccessOtherRooms(boolean accessOtherRooms) {
		this.accessOtherRooms = accessOtherRooms;
	}


	public Room getTopRoom() {
		return topRoom;
	}


	public void setTopRoom(Room topRoom) {
		this.topRoom = topRoom;
	}


	public Room getBottomRoom() {
		return bottomRoom;
	}


	public void setBottomRoom(Room bottomRoom) {
		this.bottomRoom = bottomRoom;
	}


	public Room getRightRoom() {
		return rightRoom;
	}


	public void setRightRoom(Room rightRoom) {
		this.rightRoom = rightRoom;
	}


	public Room getLeftRoom() {
		return leftRoom;
	}


	public void setLeftRoom(Room leftRoom) {
		this.leftRoom = leftRoom;
	}
	
	
}
