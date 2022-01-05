package gameWorld;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Monster;
import gameobjects.Projectile;
import gameobjects.Spider;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;
import gameobjects.StaticObject;
import gameobjects.PickableObject;


public class Room
{
	private Hero hero;
	private String groundTile; //tile for background
	private ArrayList<StaticObject> obstacles;
	private ArrayList<Monster> mobs;
	private ArrayList<Projectile> proj;
	private ArrayList<Projectile> enemy_proj;
	private ArrayList<PickableObject> pickable;

	public Room(Hero hero)
	{
		this.hero = hero;
		this.groundTile = ImagePaths.DEFAULT_TILE;
		
		this.mobs = new ArrayList<Monster>(); //TEST
		mobs.add(new Spider(new Vector2(0.3, 0.5)));
		
		this.proj = new ArrayList<Projectile>();
		this.enemy_proj = new ArrayList<Projectile>();
		
		this.obstacles = new ArrayList<StaticObject>();
		obstacles.add(new StaticObject(new Vector2(0.8, 0.3), hero.getSize(), ImagePaths.ROCK));
		
		this.pickable = new ArrayList<PickableObject>();
		pickable.add(new PickableObject(new Vector2(0.2, 0.8), RoomInfos.TILE_SIZE.scalarMultiplication(0.3), ImagePaths.DIME));
		
	}


	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		makeHeroPlay();
		makeMobsPlay();
		makeProjPlay(); // On pourra changer le nom de la fonction
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
		for(int i = 0; i < mobs.size(); i++) {
			mobs.get(i).updateGameObject();
			mobs.get(i).collision(obstacles);
			
			if(mobs.get(i).getHealth() == 0) mobs.remove(i);
		}
	}
	
	private void makeProjPlay() {
		for(int i = 0; i < proj.size(); i++) {
			proj.get(i).updateGameObject();
			
			//TODO: optimiser car code moche
			
			// Gestion reach
			if(proj.get(i).getCount() >= proj.get(i).getReach()) {
				proj.remove(proj.get(i));
			}
			
			// Si hors zone de jeu : kill
			else if(!Physics.rectangleCollision(proj.get(i).getPosition(), proj.get(i).getSize(), RoomInfos.POSITION_CENTER_OF_ROOM, RoomInfos.TILE_SIZE.scalarMultiplication(7))) {
				proj.remove(proj.get(i));
			}
			
			// Collision avec monstres
			else {
				for(int j = 0; j < mobs.size(); j++) {
					if(Physics.rectangleCollision(proj.get(i).getPosition(), proj.get(i).getSize(), mobs.get(j).getPosition(), mobs.get(j).getSize())) {
						mobs.get(j).attacked(proj.get(i).getDamage());
						proj.remove(proj.get(i));
					}
				}
			}
		}
	}
	

	/*
	 * Drawing
	 */
	public void drawRoom()
	{
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
		
		hero.drawGameObject();
		
		StdDraw.picture(0.7,0.7,ImagePaths.COIN); //TEST
		
		//Dessine tous les mobs de la piece
		for(int i = 0; i < mobs.size(); i++) {
			mobs.get(i).drawGameObject();
		}
		
		//Dessine tous les projectiles
		for(int i = 0; i < proj.size(); i++) {
			proj.get(i).drawGameObject();
		}
		
		//draws the obstacles
		for(StaticObject obs : obstacles) {
			obs.drawGameObject();
		}
		
		//draws pickable objects
		for(PickableObject obj : pickable) obj.drawGameObject();

		StdDraw.textLeft(0, 0.9, hero.getPosition().toString());
		
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
	 * Fonctions qui gerent les entrees clavier pour tirer un projectile
	 * TODO: ajouter un delay entre les tirs
	 */
	public void shootUp() {
		Projectile projectile_test = new Projectile(hero.getPosition(), hero.getSize().scalarMultiplication(0.4), ImagePaths.TEAR, hero.getSpeed(), new Vector2(0, 1), 1, 40);
		proj.add(projectile_test);
	}


	public void shootDown() {
		Projectile projectile_test = new Projectile(hero.getPosition(), hero.getSize().scalarMultiplication(0.4), ImagePaths.TEAR, hero.getSpeed(), new Vector2(0, -1), 1, 40);
		proj.add(projectile_test);
	}


	public void shootRight() {
		Projectile projectile_test = new Projectile(hero.getPosition(), hero.getSize().scalarMultiplication(0.4), ImagePaths.TEAR, hero.getSpeed(), new Vector2(1, 0), 1, 40);
		proj.add(projectile_test);
	}


	public void shootLeft() {
		Projectile projectile_test = new Projectile(hero.getPosition(), hero.getSize().scalarMultiplication(0.4), ImagePaths.TEAR, hero.getSpeed(), new Vector2(-1, 0), 1, 40);
		proj.add(projectile_test);
	}
	
	
	public void shoot(Vector2 dir) {
		if(hero.getCanShoot()) {
			Projectile shot = new Projectile(hero.getPosition(), hero.getSize().scalarMultiplication(0.4), ImagePaths.TEAR, hero.getSpeed()*1.5, dir, 1, 40);
			proj.add(shot);
			hero.setCanShoot(false);
			hero.setDelay_shoot(0);
		}
	}
}
