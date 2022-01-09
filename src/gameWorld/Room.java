package gameWorld;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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


public class Room
{
	private Hero hero;
	private String groundTile; //tile for background
	private LinkedList<Door> doors; //maybe set 'public' cuz we're gonna use it another class? 
	
	private ArrayList<StaticObject> obstacles;
	private ArrayList<Monster> mobs;
	private ArrayList<Projectile> projs;
	private ArrayList<Projectile> enemy_proj;
	private ArrayList<PickableObject> pickable;
	

	public Room(Hero hero)
	{
		this.hero = hero;
		this.groundTile = ImagePaths.DEFAULT_TILE;
		this.doors = new LinkedList<Door>(); //we choose LinkedList because 
											//there will be a really dynamic system (lots of deletion and addition) for each room entered
		
		
		this.mobs = new ArrayList<Monster>(); //TEST
		mobs.add(new Spider(new Vector2(0.3, 0.5), hero));
		mobs.add(new Gaper(RoomInfos.POSITION_TOP_OF_ROOM, hero));
		mobs.add(new Fly(new Vector2(0.8, 0.5), hero));
		
		this.projs = new ArrayList<Projectile>();
		this.enemy_proj = new ArrayList<Projectile>();
		
		this.obstacles = new ArrayList<StaticObject>();
		obstacles.add(new Rock(new Vector2(0.8, 0.3), hero.getSize()));
		obstacles.add(new Spikes(new Vector2(0.3, 0.3), hero.getSize()));
		
		this.pickable = new ArrayList<PickableObject>();
		addPickableObject(ObjectInfos.hp_up, new Vector2(0.6, 0.6));
	}
	
	public LinkedList<Door> Door() {
		return doors;
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
	}


	private void makeHeroPlay()
	{
		hero.updateGameObject();
		hero.collision(obstacles, mobs, enemy_proj, pickable);
	}
	
	/*
	 * Parcours la liste des mobs presents dans la piece, les fait avancer d'un tick
	 */
	private void makeMobsPlay() {
		List<Monster> removeList = new ArrayList<Monster>();
		
		for(Monster mob : mobs) {
			mob.updateGameObject();
			mob.collision(obstacles);
			
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

	
	
	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		drawTiles();
		drawWalls();
		drawEntities();
		
		StdDraw.textLeft(0, 0.9, hero.getPosition().toString()); //TODO: suppr plus tard
		
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
		//the corners have a brown background with a rock 
		//lower left corner (0,0)
		StdDraw.picture(positionFromTileIndex(0, 0).getX(), positionFromTileIndex(0, 0).getY(), ImagePaths.BROWN, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		StdDraw.picture(positionFromTileIndex(0, 0).getX(), positionFromTileIndex(0, 0).getY(), ImagePaths.ROCK, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);

		//lower right corner(nb_tiles-1, 0)
		StdDraw.picture(positionFromTileIndex(RoomInfos.NB_TILES - 1, 0).getX(), positionFromTileIndex(RoomInfos.NB_TILES - 1, 0).getY(), ImagePaths.BROWN, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		StdDraw.picture(positionFromTileIndex(RoomInfos.NB_TILES - 1, 0).getX(), positionFromTileIndex(RoomInfos.NB_TILES - 1, 0).getY(), ImagePaths.ROCK, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		//top right corner (nb_tiles-1, nb_tiles-1)
		StdDraw.picture(positionFromTileIndex(RoomInfos.NB_TILES - 1, RoomInfos.NB_TILES - 1).getX(), positionFromTileIndex(RoomInfos.NB_TILES - 1, RoomInfos.NB_TILES - 1).getY(), ImagePaths.BROWN, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		StdDraw.picture(positionFromTileIndex(RoomInfos.NB_TILES - 1, RoomInfos.NB_TILES - 1).getX(), positionFromTileIndex(RoomInfos.NB_TILES - 1, RoomInfos.NB_TILES - 1).getY(), ImagePaths.ROCK, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		//top left corner
		StdDraw.picture(positionFromTileIndex(0, RoomInfos.NB_TILES - 1).getX(), positionFromTileIndex(0, RoomInfos.NB_TILES - 1).getY(), ImagePaths.BROWN, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		StdDraw.picture(positionFromTileIndex(0, RoomInfos.NB_TILES - 1).getX(), positionFromTileIndex(0, RoomInfos.NB_TILES - 1).getY(), ImagePaths.ROCK, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		
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
		
		//test - draws the 4 doors
		//right
		StdDraw.picture(positionFromTileIndex(RoomInfos.NB_TILES - 1, 4).getX(), positionFromTileIndex(RoomInfos.NB_TILES - 1, 4).getY(), ImagePaths.CLOSED_DOOR, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		//up
		StdDraw.picture(positionFromTileIndex(4, RoomInfos.NB_TILES - 1).getX(), positionFromTileIndex(4, RoomInfos.NB_TILES - 1).getY(), ImagePaths.CLOSED_DOOR, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		//down
		StdDraw.picture(positionFromTileIndex(4,0).getX(), positionFromTileIndex(4,0).getY(), ImagePaths.CLOSED_DOOR, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		//left
		StdDraw.picture(positionFromTileIndex(0,4).getX(), positionFromTileIndex(0,4).getY(), ImagePaths.CLOSED_DOOR, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);

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
		
		//draws the obstacles
		for(StaticObject obs : obstacles) {
			obs.drawGameObject();
		}
		
		//draws pickable objects
		for(PickableObject obj : pickable) {
			obj.drawGameObject();
		}
		
		hero.drawGameObject();
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

	
	/**
	 * Fonction qui gere les entrees clavier pour tirer un projectile
	 */	
	public void shoot(Vector2 dir) {
		if(hero.getCanShoot()) {
			Projectile shot = new Projectile(hero.getPosition(), hero.getSize().scalarMultiplication(0.4),
					ImagePaths.TEAR, hero.getProjectile_speed(), dir, hero.getDamage(), hero.getReach());
			projs.add(shot);
			hero.setCanShoot(false);
			hero.setDelay_shoot(0);
		}
	}
	
	
}
