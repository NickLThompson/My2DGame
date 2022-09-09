package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	// DEBUG
	boolean checkDrawTime = false;

	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	public KeyHandler() {
		// fill
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// fill
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode(); // returns the integer KeyCode associated with the key in this event

		// playState
		if(gp.gameState == gp.playState) {
			
			if(code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if(code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if(code == KeyEvent.VK_A) {
				leftPressed = true;	
			}
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.pauseState;
				
			}
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
				
			}
							
			// DEBUG
			if(code == KeyEvent.VK_T) {
				if(checkDrawTime == false) {
					checkDrawTime = true;
				}
				else if(checkDrawTime == true) {
					checkDrawTime = false;
				}
			}

		}
		
		// if the pressed key is W, upPressed = true; and soforth

		// pause state
		else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;	
			}
		}

		// dialogue state
		if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		// when the key is released, the action is cancelled
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;	
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}

}
