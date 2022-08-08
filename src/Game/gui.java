package Game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class gui extends JFrame{
	private BufferStrategy strat;
	private Player player;
	private Background bg;
	private Dummy dummy;
	
	public gui(Player player,Background bg,Dummy dummy){//CONSTRUCKTOR
		addKeyListener(new Keyboard());
		this.player= player;
		this.bg = bg;
		this.dummy=dummy;
	}
	public void makeStrat(){
		createBufferStrategy(2);
		strat = getBufferStrategy();
	}
	
	public void repaintScreen(){
		Graphics g = strat.getDrawGraphics();
		draw(g);
		g.dispose();
		strat.show();
	}
	
	private void draw(Graphics g){
		g.drawImage(bg.getBackground(),(int) bg.getX2(), 0, null);
		g.drawImage(bg.getBackground(),(int) bg.getX2()+bg.getBackground().getWidth(), 0, null);
		g.drawImage(bg.getBackground(),(int) bg.getX2()-bg.getBackground().getWidth(), 0, null);
		g.drawImage(bg.getBridge(), (int)bg.getX(), 0, null);
		g.drawImage(bg.getBridge(), (int)bg.getX()+bg.getBridge().getWidth(), 0, null);
		g.drawImage(bg.getBridge(), (int)bg.getX()-bg.getBridge().getWidth(), 0, null);
		
		g.drawImage(dummy.getLook(), dummy.getBounding().x, dummy.getBounding().y, null);
		
		
		if(!player.getDirektion()&&player.isPunching()){
			g.drawImage(player.getLook(), player.getBounding().x-25, player.getBounding().y, null);		//wegen dem Links schlag bild fehler
		}else{
			g.drawImage(player.getLook(), player.getBounding().x, player.getBounding().y, null);
		}
		
	}
	
}
