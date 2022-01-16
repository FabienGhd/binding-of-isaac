package gameWorld;
import gameobjects.*;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.ObjectInfos;

public class ShopRoom extends Room {

	public ShopRoom(Hero hero) {
		super(hero);
	}
	
	public void generate() {
		//TODO: les aligner + texte
		addPickableObject(ObjectInfos.hp_up, new Vector2(0.6, 0.6));
		addPickableObject(ObjectInfos.blood_of, new Vector2(0.4, 0.5));
		addPickableObject(ObjectInfos.NICKEL, new Vector2(0.3, 0.7));
		addPickableObject(ObjectInfos.DIME, new Vector2(0.6, 0.3));
		addPickableObject(ObjectInfos.FULL_HEART, new Vector2(0.6, 0.3));
	}
	
	public void drawItems() {
		super.drawRoom();
		
	}
}
