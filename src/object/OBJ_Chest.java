package object;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject{

	public OBJ_Chest() {
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResource("/objects/chest.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
