package gameWorld;

import java.util.ArrayList;

import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Monster;
import gameobjects.Projectile;
import gameobjects.Spider;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;
import gameobjects.StaticObject; //new import

public class Room
{
	private Hero hero;
	private String groundTile; //tile for background
	private StaticObject[] obstacle;
	private ArrayList<Monster> mobs;
	private ArrayList<Projectile> proj;
	

	public Room(Hero hero)
	{
		this.hero = hero;
		this.groundTile = ImagePaths.DEFAULT_TILE;
		this.mobs = new ArrayList<Monster>(); //TEST
		mobs.add(new Spider(new Vector2(0.5, 0.5)));
		this.proj = new ArrayList<Projectile>();
		
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
	}
	
	/**
	 * Parcours la liste des mobs presents dans la piece, les fait avancer d'un tick
	 */
	private void makeMobsPlay() {
		for(int i = 0; i < mobs.size(); i++) {
			mobs.get(i).updateGameObject(); 
		}
	}
	
	private void makeProjPlay() {
		for(int i = 0; i < proj.size(); i++) {
			proj.get(i).updateGameObject(); 
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
		hero.drawGameObject();
		
		//Dessine tous les mobs de la pièce
		for(int i = 0; i < mobs.size(); i++) {
			mobs.get(i).drawGameObject();
		}
		
		//Dessine tous les projectiles
		for(int i = 0; i < proj.size(); i++) {
			proj.get(i).drawGameObject();
		}
		
		
		
				
			
		
		//lower left corner (0,0)
		StdDraw.picture(positionFromTileIndex(0, 0).getX(), positionFromTileIndex(0, 0).getY(), ImagePaths.WALL, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		//lower right corner(nb_tiles-1, 0)
		StdDraw.picture(positionFromTileIndex(RoomInfos.NB_TILES - 1, 0).getX(), positionFromTileIndex(RoomInfos.NB_TILES - 1, 0).getY(), ImagePaths.WALL, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		//top right corner (nb_tiles-1, nb_tiles-1)
		StdDraw.picture(positionFromTileIndex(RoomInfos.NB_TILES - 1, RoomInfos.NB_TILES - 1).getX(), positionFromTileIndex(RoomInfos.NB_TILES - 1, RoomInfos.NB_TILES - 1).getY(), ImagePaths.WALL, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);
		//top left corner
		StdDraw.picture(positionFromTileIndex(0, RoomInfos.NB_TILES - 1).getX(), positionFromTileIndex(0, RoomInfos.NB_TILES - 1).getY(), ImagePaths.CORNER_TOP_LEFT, RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT);

	}
	
	
	
	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	private static Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}

	
	/**
	 * Fonctions qui gerent les entrees clavier pour tirer un projectile
	 * TODO: ajouter un delay entre les tirs
	 */
	public void shootUp() {
		Projectile projectile_test = new Projectile(hero.getPosition(), hero.getSize(), ImagePaths.TEAR, hero.getSpeed(), new Vector2(0, 1), 0, 0);
		proj.add(projectile_test);
	}


	public void shootDown() {
		Projectile projectile_test = new Projectile(hero.getPosition(), hero.getSize(), ImagePaths.TEAR, hero.getSpeed(), new Vector2(0, -1), 0, 0);
		proj.add(projectile_test);
	}


	public void shootRight() {
		Projectile projectile_test = new Projectile(hero.getPosition(), hero.getSize(), ImagePaths.TEAR, hero.getSpeed(), new Vector2(1, 0), 0, 0);
		proj.add(projectile_test);
	}


	public void shootLeft() {
		Projectile projectile_test = new Projectile(hero.getPosition(), hero.getSize(), ImagePaths.TEAR, hero.getSpeed(), new Vector2(-1, 0), 0, 0);
		proj.add(projectile_test);
	}
}
