package Game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {
	private BufferedImage bg;
	private BufferedImage bg_bridge;
	private float f_posx;
	private float f_posx2=0;
	private final int BORDERBREITE;
	private Rectangle rightBorder;
	private Rectangle leftBorder;
	private Player player;
	private final float SCROLLSPEED=200;
	private byte bgMovement;		//right =1, left=2, none=0
	
	public Background(float x,WindowDimension window,Player player,int bb){
		this.f_posx=x;
		this.player=player;
		this.BORDERBREITE=bb;
		try {
			bg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/background2.png"));
			bg_bridge = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/background_bridge2.png"));
		} catch (IOException e) {e.printStackTrace();}
		
		leftBorder=new Rectangle(-40,0,BORDERBREITE,window.getHeight());		//HARDCODE(-40)
		rightBorder=new Rectangle((int) (window.getWeight()-BORDERBREITE)-100,0,BORDERBREITE,window.getHeight());
	}
	public BufferedImage getBackground(){
		return bg;
	}
	public float getSCROLLSPEED(){
		return SCROLLSPEED;
	}
	public BufferedImage getBridge(){
		return bg_bridge;
	}
	public Rectangle getLeftBorder(){
		return leftBorder;
	}
	public Rectangle getRightBorder(){
		return rightBorder;
	}
	public float getX(){
		return f_posx;
	}
	public float getX2(){
		return f_posx2;
	}
	public byte getBgMovement(){
		return bgMovement;
	}
	public void update(float fD){
		if(rightBorder.intersects(player.getBounding())&&player.getInMotionTimer()!=0){
			f_posx-=SCROLLSPEED*fD;
			f_posx2-=SCROLLSPEED/10*fD;
			if(f_posx<-bg_bridge.getWidth())f_posx+=bg_bridge.getWidth();
			if(f_posx2<-bg.getWidth())f_posx2+=bg.getWidth();
			bgMovement=1;
		}
		if(leftBorder.intersects(player.getBounding())&&player.getInMotionTimer()!=0){
			f_posx+=SCROLLSPEED*fD;
			f_posx2+=SCROLLSPEED/10*fD;
			if(f_posx>=0)f_posx-=bg_bridge.getWidth();  
			if(f_posx2>=0)f_posx2-=bg.getWidth();
			bgMovement=2;
		}
		if((!(leftBorder.intersects(player.getBounding())||rightBorder.intersects(player.getBounding())))||player.getInMotionTimer()==0)bgMovement=0;
		 
	}
}
























