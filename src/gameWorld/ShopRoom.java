package gameWorld;
import gameobjects.*;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.ObjectInfos;

public class ShopRoom extends Room {

	public ShopRoom(Hero hero) {
		super(hero);
		setAccessOtherRooms(true);
	}
	
	public void generate() {
		//TODO: les aligner + texte
		addPickableObject(ObjectInfos.blood_of_shop, new Vector2(0.4, 0.5));
		addPickableObject(ObjectInfos.FULL_HEART_SHOP, new Vector2(0.6, 0.5));
	}
	
	public void drawItems() {
		super.drawRoom();
	}
}
