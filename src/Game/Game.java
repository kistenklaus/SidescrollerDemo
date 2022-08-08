package Game;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class Game {
	//INITIALISIEREN
	public static WindowDimension window= new WindowDimension(800,600);
	static Dummy dummy = new Dummy(300,365);
	static Player player = new Player(300,350,dummy);
	static Background bg = new Background(-120,window,player,50);
	
	public static void main(String[] args) {
		player.giveBg(bg);
		dummy.giveBg(bg);
		gui f= new gui(player,bg,dummy);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setUndecorated(true);
		f.setResizable(false);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.makeStrat();
		
		
		long lastFrame = System.currentTimeMillis();
		while(true){
			//Rechnen
			if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE))System.exit(0);
			long thisFrame = System.currentTimeMillis();
			float fD= (float)(thisFrame-lastFrame)/1000f;
			lastFrame = thisFrame;
			
			player.update(fD);
			bg.update(fD);
			dummy.update(fD);
			//Anzeigen
			f.repaintScreen();
			
			
			//Thread
			try {
				Thread.sleep(15);
				} catch (InterruptedException e) {e.printStackTrace();}
		}
		
		
	}

}
