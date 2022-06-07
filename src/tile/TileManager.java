package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	
		GamePanel gp;
		public Tile[] tile;
		public int mapTileNum[][];
		
		
		public TileManager(GamePanel gp) {
		
			this.gp = gp;
			
			tile = new Tile[50]; // this means we will create a bunch of tiles
			mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
			
			
			getTileImage();
			loadMap("/maps/worldV2.txt"); 
			
		}
	
		public void getTileImage() {
			
			// Placeholder
			setup(0, "grass00", false);
			setup(1, "grass00", false);
			setup(2, "grass00", false);
			setup(3, "grass00", false);
			setup(4, "grass00", false);
			setup(5, "grass00", false);
			setup(6, "grass00", false);
			setup(7, "grass00", false);
			setup(8, "grass00", false);
			setup(9, "grass00", false);
			// Placeholder
			
			
			setup(10, "grass00", false);
			setup(11, "grass01", false);
			setup(12, "water00", true);
			setup(13, "water01", true);
			setup(14, "water02", true);
			setup(15, "water03", true);
			setup(16, "water04", true);
			setup(17, "water05", true);
			setup(18, "water06", true);
			setup(19, "water07", true);
			setup(20, "water08", true);
			setup(21, "water09", true);
			setup(22, "water10", true);
			setup(23, "water11", true);
			setup(24, "water12", true);
			setup(25, "water13", true);
			setup(26, "road00", false);
			setup(27, "road01", false);
			setup(28, "road02", false);
			setup(29, "road03", false);
			setup(30, "road04", false);
			setup(31, "road05", false);
			setup(32, "road06", false);
			setup(33, "road07", false);
			setup(34, "road08", false);
			setup(35, "road09", false);
			setup(36, "road10", false);
			setup(37, "road11", false);
			setup(38, "road12", false);
			setup(39, "earth", false);
			setup(40, "wall", true);
			setup(41, "tree", true);
			
		}
		public void setup(int index, String imageName, boolean collision) {
			
			UtilityTool uTool = new UtilityTool();
			
			try {
				tile[index] = new Tile();
				tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName +".png"));
				tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
				tile[index].collision = collision;
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		public void loadMap(String filePath) {
			
			try {
				InputStream is = getClass().getResourceAsStream(filePath);
				// this InputStream ^ imports the text file we created 
				
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				// this BufferedReader ^ reads the content of the imported text file
				
				int col = 0;
				int row = 0;
				
				while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
					
					/**************************************************************************
					 * this limits where it reads the text file to only where we typed things *
					 * it does not read anything outside of where we told it to read		  *
					 * which in this case, is 16x12											  *
					 **************************************************************************/
					String line = br.readLine(); // this reads a line of the map file and
												 // puts it into the String "line"
					
					while(col < gp.maxWorldCol) {
						
						String numbers[] = line.split(" ");
						
						int num = Integer.parseInt(numbers[col]);
						
						mapTileNum[col][row] = num;
						col++;
						// continue this until everything in the numbers[] is stored in the mapTileNum[][]
						
					}
					if (col == gp.maxWorldCol) {
						col = 0;
						row++;
					}
					
				}
				br.close();
				
				/***************************************************
				 * This is scanning the text file line by line and *
				 * dividing each individual number and giving that *
				 * number to mapTileNum until the loop is over	   *
				 ***************************************************/
				
			}catch(Exception e) {
				
				
			}
		}
		public void draw(Graphics2D g2) {
			
			int worldCol = 0;
			int worldRow = 0;
			
			
			while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
				
				int tileNum = mapTileNum[worldCol][worldRow];
				// everything is stored in the mapTileNum array
				
				int worldX =  worldCol * 48;
				int worldY = worldRow * 48;
				int screenX = worldX - gp.player.worldX + gp.player.screenX;
				int screenY = worldY - gp.player.worldY + gp.player.screenY;
				
				// Stop moving the camera at the edge
				if(gp.player.screenX > gp.player.worldX) {
					screenX = worldX;
				}
				if(gp.player.screenY > gp.player.worldY) {
					screenY = worldY;
				}
				int rightOffset = gp.screenWidth - gp.player.screenX;
				if(rightOffset > gp.worldWidth - gp.player.worldX) {
					screenX = gp.screenWidth - (gp.worldWidth - worldX);
				}
				int bottomOffset = gp.screenHeight - gp.player.screenY;
				if(bottomOffset > gp.worldHeight - gp.player.worldY) {
					screenY = gp.screenHeight - (gp.worldHeight - worldY);
				}
				
				
				
				if(worldX + 48 > gp.player.worldX - gp.player.screenX &&
				   worldX - 48 < gp.player.worldX + gp.player.screenX &&
				   worldY + 48 > gp.player.worldY - gp.player.screenY &&
			 	   worldY - 48 < gp.player.worldY + gp.player.screenY) {
					
				// this draws a tile at 0, 0 (top left)
				g2.drawImage(tile[tileNum].image, screenX, screenY,	null);
				}
				else if(gp.player.screenX > gp.player.worldX ||
						gp.player.screenY > gp.player.worldY ||
						rightOffset > gp.worldWidth - gp.player.worldX ||
						bottomOffset > gp.worldHeight - gp.player.worldY) {
					g2.drawImage(tile[tileNum].image, screenX, screenY,	null);
				}

				worldCol++; // increase worldCol by 1
				
				
				if(worldCol == gp.maxWorldCol) {
					worldCol = 0;
					worldRow++;
				
				// this all only creates every block as a grass type
					
				}
			
			// inefficient and tedious
			/*
			g2.drawImage(tile[1].image, 0, 0, 48, 48, null);
			g2.drawImage(tile[1].image, 48, 0, 48, 48, null);
			g2.drawImage(tile[1].image, 96, 0, 48, 48, null);
			g2.drawImage(tile[1].image, 144, 0, 48, 48, null);
			g2.drawImage(tile[1].image, 192, 0, 48, 48, null);
			
			g2.drawImage(tile[1].image, 0, 48, 48, 48, null);
			g2.drawImage(tile[0].image, 48, 48, 48, 48, null);
			g2.drawImage(tile[0].image, 96, 48, 48, 48, null);
			g2.drawImage(tile[0].image, 144, 48, 48, 48, null);
			g2.drawImage(tile[1].image, 192, 48, 48, 48, null);
			
			g2.drawImage(tile[1].image, 0, 96, 48, 48, null);
			g2.drawImage(tile[0].image, 48, 96, 48, 48, null);
			g2.drawImage(tile[0].image, 96, 96, 48, 48, null);
			g2.drawImage(tile[0].image, 144, 96, 48, 48, null);
			g2.drawImage(tile[0].image, 192, 96, 48, 48, null);
			
			g2.drawImage(tile[1].image, 0, 144, 48, 48, null);
			g2.drawImage(tile[0].image, 48, 144, 48, 48, null);
			g2.drawImage(tile[0].image, 96, 144, 48, 48, null);
			g2.drawImage(tile[0].image, 144, 144, 48, 48, null);
			g2.drawImage(tile[1].image, 192, 144, 48, 48, null);
			
			g2.drawImage(tile[1].image, 0, 192, 48, 48, null);
			g2.drawImage(tile[2].image, 48, 192, 48, 48, null);
			g2.drawImage(tile[2].image, 96, 192, 48, 48, null);
			g2.drawImage(tile[2].image, 144, 192, 48, 48, null);
			g2.drawImage(tile[1].image, 192, 192, 48, 48, null);
			*/
		}
		

}
}
