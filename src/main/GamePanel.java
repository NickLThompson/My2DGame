/* this is the foundation of the game
 * this class creates the actual window that this game will run in
*/
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{


	
		// Screen Settings
		public final int originalTileSize = 16; // 16x16 tiles for creating sprites
		public final int scale = 3;
		
		public final int tileSize = originalTileSize * scale; // scaling the 16x16 tile sprite to 48x48
		public final int MaxScreenCol = 16; // How many tiles the game will display vertically
		public final int MaxScreenRow = 12; // How many tiles the game will display vertically
		public final int screenWidth = tileSize * MaxScreenCol; // 48/48 * 16 = 768 pixels
		public final int screenHeight = tileSize * MaxScreenRow; // 48/48 * 12 = 576 pixels
		
		
		int FPS = 60;
		
		/*****************************************************
		 * the number of pixels in this game is going to be: *
		 * 768 (16) pixels horizontally (left and right) by	 *
		 * 576 (12) pixels vertically (up and down)			 *
		 *****************************************************/
		TileManager tileM = new  TileManager(this);
		KeyHandler keyH = new KeyHandler(this);
		Sound music = new Sound();
		Sound se = new Sound();
		public CollisionChecker cChecker = new CollisionChecker(this);
		public AssetSetter aSetter = new AssetSetter(this);
		public UI ui = new UI(this);
		Thread gameThread;
		
		// ENTITY AND OBJECT
		public Player player = new Player(this,keyH);
		public SuperObject obj[] = new SuperObject[10];
		/****************************************************************************
		 * 10 means we can display up to 10 different objects at the same time		*
		 * This does not mean that we can only have 10 objects in the game			*
		 * Displaying too many objects at once can slow down your game's performance*
		 ****************************************************************************/
		public Entity npc[] = new Entity[10];
		
		
		
		
		
		
		
		
		// set player's default position
		int playerX = 100;
		int playerY = 100;
		int playerSpeed = 4;
		// world settings
		public final int maxWorldCol = 50;
		public final int maxWorldRow = 50;
		public final int worldWidth = tileSize * maxWorldCol;
		public final int worldHeight = tileSize * maxWorldRow;
		
		// Game state
		public int gameState;
		public final int playState = 1;
		public final int pauseState = 2;
		
	
	
		public GamePanel() {
			
			// https://gyazo.com/2e1c03f14da91240c14f7a672fae34c7 16x12 sized JPanel
			this.setPreferredSize(new Dimension(768, 576));
			this.setBackground(Color.black);
			this.setDoubleBuffered(true); // increases game's rendering performance
			this.addKeyListener(keyH); // this GamePanel recognizes the key inputs 
			this.setFocusable(true);		
		}
		
		public void setupGame() {
			
			aSetter.setObject();
			aSetter.setNPC();
			playMusic(0); // MUSIC HERE 
			stopMusic();
			gameState = playState;
			
		}
		
		public void startGameThread() {
		
				gameThread = new Thread(this); 
				gameThread.start();				
				/********************************
				 * this allows the thread to    *
				 * pass gamePanel and come here *
				 ********************************/
				
				// gameThread calls on this code ^ and gameThread.start();
				//  calls on "run" below this comment 
				
		}
		
		@Override // this is automatically generated when we fix the error after adding "Implement Runnable"
		public void run() {
			// here we will create a game loop, which is the core of our game	
			
			double drawInterval = 1000000000/60; 
			double delta = 0;
			long lastTime = System.nanoTime();
			long currentTime;
			long timer = 0;
			int drawCount = 0;
			
			// test comment for git push

			while(gameThread != null) {
		   /*************************************************************************
			* while the gameThread is not equal to null, which means it is running, *
			* it will repeat what is written inside of the brackets					*
			*************************************************************************/
					currentTime = System.nanoTime();
					
					delta += (currentTime - lastTime) / drawInterval;
					timer += (currentTime - lastTime);
					lastTime = currentTime;
					
					if(delta >= 1) {
					// 1 UPDATE: Update information such as character positions
					update();
					// 2 DRAW: Draw the screen with the updated information
					repaint();
					delta--;
					drawCount++;
					
					}
					
					if(timer >= 1000000000) {
						// System.out.println("FPS: "+drawCount);
						drawCount = 0;
						timer = 0;
						
					}
			
			// https://gyazo.com/4e524fb32710c932dc114536e956fc87
			
			
			 // https://gyazo.com/2fc6fc22d7dd0ccab7ec39d051826697
			
			/******************************************************************
			 * If you character is at x: 100 | y: 100, if the player presses  *
			 * down arrow key (or S), the player will move down 3 pixels.	  *
			 * We can add +3 to the y coordinate to signify the character	  *
			 * moves down 3 pixels.											  *
			 * Now we have to have the game update the location of the 		  *
			 * Character, and then draw the updated information on the screen *
			 ******************************************************************/
			
			
			
			}
			
			
		}
		// In java, the upper left corner is x: 0 | y: 0
		// x values increase to the right
		// y values increase as they go down
		public void update() {
			
			if(gameState == playState) {
				// PLAYER
				player.update();
				// NPC
				for(int i = 0; i < npc.length; i++) {
					if(npc[i] != null) {
						npc[i].update();
					}
				}		
			}
			if(gameState == pauseState) {
				// nothing
			}
			
			
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			
			// DEBUG
			long drawStart = 0;
			if(keyH.checkDrawTime) {
				drawStart = System.nanoTime();
			}
			
			
			// tile
			tileM.draw(g2);
			
			// tileM.draw(g2); draws the tile | player.draw(g2); draws the player
			
			// object
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
				
			}
			// NPC
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			// player
			player.draw(g2);
			
			// UI
			ui.draw(g2);
			
			
			// DEBUG
			if(keyH.checkDrawTime) {
				long drawEnd = System.nanoTime();
				long passed = drawEnd - drawStart;
				g2.setColor(Color.white);
				g2.drawString("Draw Time: " + passed, 10, 400);
				System.out.println("Draw Time: "+passed);
			}	
			
			g2.dispose();
			
			/*****************************************************************************************
			* the code above allowed us to create a 2D graphic of a white rectangle at the position  *
			* x: 100 | y: 100, with a size (to scale) of 48 x 48 pixels								 *
			* g2.setColor allowed us to set the color of the rectangle, while						 *
			* g2.fillRect let us set the position of where the white color starts and ends so the	 *
			* white color was set to color in at x: 100 | y: 100 and color a space size of 48x48	 *
			******************************************************************************************/
			
			
			
			
		}
		public void playMusic(int i) {
			
			music.setFile(i);
			music.play();
			music.loop();
		}
		public void stopMusic() {
			
			music.stop();
		}
		public void playSE(int i) {
			
			se.setFile(i);
			se.play();
			
		}
		
}

