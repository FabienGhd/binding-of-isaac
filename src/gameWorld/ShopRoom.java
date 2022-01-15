package gameWorld;
import gameobjects.*;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.ObjectInfos;

public class ShopRoom extends Room {

	public ShopRoom(Hero hero) {
		super(hero);
		
		addPickableObject(ObjectInfos.hp_up, new Vector2(0.6, 0.6));
		addPickableObject(ObjectInfos.blood_of, new Vector2(0.4, 0.5));
	}
	
	public void drawItems() {
		super.drawRoom();
		
	}
}
