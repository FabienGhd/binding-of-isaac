package gameWorld;
import gameobjects.*;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.*;

public class BossRoom extends Room {
	
	public BossRoom(Hero hero) {
		super(hero);
	}
	
	public void generate() {
		getMobs().add(new Gaper(RoomInfos.POSITION_TOP_OF_ROOM, getHero()));
	}
	
	public void draw() {
		super.drawRoom();
	}

}
