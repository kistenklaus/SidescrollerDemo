package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	
	private static boolean[] keys = new boolean[512];
	
	
	public static boolean isKeyDown(int keyCode){
		if(keyCode>=0&&keyCode<keys.length) return keys[keyCode];
		else return false;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode>=0&&keyCode<keys.length) keys[keyCode]=true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode>=0&&keyCode<keys.length) keys[keyCode]=false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Unn�tig
	@Override
	public void keyTyped(KeyEvent arg0) {}
		

}
