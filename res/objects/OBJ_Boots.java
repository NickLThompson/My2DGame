package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import object.SuperObject;

public class OBJ_Boots extends SuperObject	{
	
	
	GamePanel gp;

	public OBJ_Boots(GamePanel gp) {
		
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Boots.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		solidArea.x = 5;
	}
	
}
