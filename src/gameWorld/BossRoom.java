package gameWorld;
import gameobjects.*;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.*;

public class BossRoom extends Room {
	
	public BossRoom(Hero hero) {
		super(hero);
		
		mobs.add(new Gaper(RoomInfos.POSITION_TOP_OF_ROOM, hero));
	}
	
	public void draw() {
		super.drawRoom();
	}

}
