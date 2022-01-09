package gameobjects;

import libraries.Vector2;
import resources.ImagePaths;

public class Spikes extends StaticObject {
	
	public Spikes(Vector2 position, Vector2 size) {
		super(position, size, ImagePaths.SPIKES, 1, false);
	}
	
}
