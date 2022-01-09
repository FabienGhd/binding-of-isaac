package gameobjects;

import java.util.ArrayList;

import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.ObjectInfos;
import resources.RoomInfos;
import libraries.Physics;

public class Hero
{
	private Vector2 position;
	private Vector2 size;
	private Vector2 old_pos;
	private Vector2 direction;
	
	private String imagePath;
	private String transparentPath;
	
	private double speed;
	private int health;
	private int max_health;
	private int coin; 
	private int damage; // Degats des larmes
	private int reach;
	private double projectile_speed;
	
	private boolean canShoot;
	private boolean invincible;
	
	private int delay_invincible;
	private int delay_shoot;
	

	public Hero(Vector2 position, Vector2 size, double sp, String imPath, int h, int c, int d, int r, double proj_sp) 
	{
		this.position = position;
		this.size = size;
		this.direction = new Vector2();
		this.imagePath = imPath;

		this.speed = sp;
		this.health = h;
		this.max_health = h;
		this.coin = c;
		this.damage = d;
		this.reach = r;
		this.projectile_speed = proj_sp;
		
		this.canShoot = true;
		this.invincible = false;
		
		this.delay_invincible = -1;
		this.delay_shoot = -1;
	}

	public void updateGameObject()
	{
		move();
		manageDelay();
	}

	private void move()
	{
		old_pos = position;
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
	}
	
	/**
	 * Cette fonction g�re toutes les collisions entre le personnage et les autres entit�s.
	 * @param obstacles
	 * @param mobs
	 * @param projectiles
	 * @param pickable
	 */
	public void collision(ArrayList<StaticObject> obstacles, ArrayList<Monster> mobs, ArrayList<Projectile> projectiles, ArrayList<PickableObject> pickable) {
		
		// Collisions avec les obstacles
		for(StaticObject obs : obstacles) {
			if(Physics.rectangleCollision(getPosition(), getSize(), obs.getPosition(), obs.getSize())) {
				attacked(obs.getDamage());
				
				if(obs.doBlock()) position = old_pos;
			}
		}
		
		// Collisions avec les murs
		// Pour celle-ci on fait une n�gation de la fonction de base afin de voir si le joueur est bien dans la zone de jeu
		if(!Physics.rectangleCollision(getPosition(), getSize(), RoomInfos.POSITION_CENTER_OF_ROOM, RoomInfos.TILE_SIZE.scalarMultiplication(6))) {
			position = old_pos;
		}
		
		
		// Collisions avec les monstres - delay invicible a ajouter (2sec?)
		for(Monster mob : mobs) {
			if(Physics.rectangleCollision(getPosition(), getSize(), mob.getPosition(), mob.getSize())) {
				attacked(1);
			}
		}
		
		// Collisions avec les projectiles
		for(Projectile proj : projectiles) {
			if(Physics.rectangleCollision(getPosition(), getSize(), proj.getPosition(), proj.getSize())) {
				attacked(proj.getDamage());
			}
		}
		
		for(PickableObject obj : pickable) {
			
			//we call Physics in order to know if the hero touches the pickable objects
			if(Physics.rectangleCollision(getPosition(), getSize(), obj.getPosition(), obj.getSize())) {
				this.coin += obj.getCoins();
				this.health += obj.getHealth();
				this.max_health += obj.getMax_health();
				this.damage += obj.getDamage();
				obj.setTaken(true); // On supprime l'objet dans la classe Room
			}
		}
	}
	
	public void manageDelay() {
		// Gestion invincibilite
		if(delay_invincible >= 0 && delay_invincible < 60) {delay_invincible++;} // TODO: ajouter variable globale
		else if(delay_invincible >= 60) {delay_invincible = -1; invincible = false;} // Nous utiliserons delay = -1 pour le cheat
		
		// Gestion delai shoot
		if(delay_shoot >= 0 && delay_shoot < 20) {delay_shoot++;} // TODO: ajouter variable globale
		else {delay_shoot = -1; canShoot = true;}
	}
	
	
	public void attacked(int damage) {
		if(!invincible && damage > 0) {
			health -= damage;
			delay_invincible = 0;
			invincible = true;
		}
	}

	public void drawGameObject()
	{
		String imPath = getImagePath();
		if(invincible) imPath = ImagePaths.ISAAC_TRANSPARENT;
		StdDraw.picture(getPosition().getX(), getPosition().getY(), imPath, getSize().getX(), getSize().getY(), 0);
		hudDraw();
	}

	public void hudDraw() {
		//HEARTS status bar 
		if (this.health > 0) {
		
			for (int i = 0; i < max_health/ 2; i++) {
				
				int even = (i + 1) * 2;
				int odd = even - 1;
				double left = 0.04 * (4 + i);
				double bottom = 0.025;
				
				if (this.health >= even) {
					StdDraw.picture(left, bottom, ImagePaths.HEART_HUD);
				} else if (this.health >= odd) { 
					StdDraw.picture(left, bottom, ImagePaths.HALF_HEART_HUD);
				} else {
					StdDraw.picture(left, bottom, ImagePaths.EMPTY_HEART_HUD);
				}
	
			} //end for loop
		} 
		//when game over 
		else {
			for (int i = 0; i < HeroInfos.HEALTH / 2; i++) {
				StdDraw.picture(0.04 * (4 + i), 0.025, ImagePaths.EMPTY_HEART_HUD);
			}
		}
		
		//COINS status bar 
		StdDraw.picture(0.155, 0.065, ImagePaths.COIN);
		StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
		StdDraw.text(0.2, 0.065, "" + this.coin);
	}
	
	
	/*
	 * Moving from key inputs. Direction vector is later normalised.
	 */
	public void goUpNext()
	{
		getDirection().addY(1);
	}

	public void goDownNext()
	{
		getDirection().addY(-1);
	}

	public void goLeftNext()
	{
		getDirection().addX(-1);
	}

	public void goRightNext()
	{
		getDirection().addX(1);
	}

	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}


	
	
	/*
	 * Getters and Setters
	 */
	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	public Vector2 getSize()
	{
		return size;
	}

	public void setSize(Vector2 size)
	{
		this.size = size;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public Vector2 getDirection()
	{
		return direction;
	}

	public void setDirection(Vector2 direction)
	{
		this.direction = direction;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean getCanShoot() {
		return canShoot;
	}

	public void setCanShoot(boolean canShoot) {
		this.canShoot = canShoot;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public Vector2 getOld_pos() {
		return old_pos;
	}

	public void setOld_pos(Vector2 old_pos) {
		this.old_pos = old_pos;
	}

	public int getDelay_invincible() {
		return delay_invincible;
	}

	public void setDelay_invincible(int delay_invincible) {
		this.delay_invincible = delay_invincible;
	}

	public int getDelay_shoot() {
		return delay_shoot;
	}

	public void setDelay_shoot(int delay_shoot) {
		this.delay_shoot = delay_shoot;
	}

	public boolean getInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public int getMax_health() {
		return max_health;
	}

	public void setMax_health(int max_health) {
		this.max_health = max_health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getReach() {
		return reach;
	}

	public void setReach(int reach) {
		this.reach = reach;
	}

	public double getProjectile_speed() {
		return projectile_speed;
	}

	public void setProjectile_speed(double projectile_speed) {
		this.projectile_speed = projectile_speed;
	}
	
	
}
