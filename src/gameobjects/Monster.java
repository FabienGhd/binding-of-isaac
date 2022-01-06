package gameobjects;

import java.util.ArrayList;

import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

public abstract class Monster {
	
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private int damage;
	private int health;
	private Vector2 old_pos;
	private boolean collide;
	private Hero hero;


	public Monster(Vector2 position, Hero hero)
	{
		this.position = position;
		this.direction = new Vector2();
		this.hero = hero;
	}
	
	/**
	 * Les 4 prochaines fonctions sont une simple copie des fonctions de 'Hero', � voir si il faut les modifier un peu
	 */
	public void updateGameObject()
	{
		old_pos = position;
		move();
		//shoot()?
	}
	
	// Chaque type de monstre a sa fa�on de se d�placer, on sp�cifiera leurs mouvements dans leur classe
	abstract void move();

	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	

	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
	
	/**
	 * Cette fonction g�re toutes les collisions entre les monstres et les projectiles.
	 * Les collisions avec les obstacles sont g�r�s dans les classes filles (car Fly passe au dessus).
	 * @param projectiles
	 */
	public void collision(ArrayList<StaticObject> obstacles) {
			
		// Collisions avec les obstacles; Les mouches survolents
		for(StaticObject obs : obstacles) {
			if(Physics.rectangleCollision(getPosition(), getSize(), obs.getPosition(), obs.getSize()) && getCollide()) {
				position = old_pos;
			}
		}
		
		// Collisions avec les murs
		// Pour celle-ci on fait une n�gation de la fonction de base afin de voir si le joueur est bien dans la zone de jeu
		if(!Physics.rectangleCollision(getPosition(), getSize(), RoomInfos.POSITION_CENTER_OF_ROOM, RoomInfos.TILE_SIZE.scalarMultiplication(6))) {
			position = old_pos;
		}
		
		// Les collisions avec les projectiles sont gerees dans la classe Room
	}
	
	/*
	 * 
	 */
	public void attacked(int damage) {
		health -= damage;
	}
	
	
	/*
	 * Getters and Setters
	 */
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Vector2 getOld_pos() {
		return old_pos;
	}

	public void setOld_pos(Vector2 old_pos) {
		this.old_pos = old_pos;
	}

	public boolean getCollide() {
		return collide;
	}

	public void setCollide(boolean collide) {
		this.collide = collide;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	
}