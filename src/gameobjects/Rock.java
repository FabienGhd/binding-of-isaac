package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;

public class Rock extends StaticObject {
	
	public Rock(Vector2 position, Vector2 size) {
		super(position, size, ImagePaths.ROCK, 0, true);
	}
	
}
