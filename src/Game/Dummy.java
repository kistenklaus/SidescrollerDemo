package Game;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dummy {
	private BufferedImage stand;
	private Rectangle bounding;
	private Background bg;
	
	public Dummy(int x, int y){
		try {
			stand = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/Training_Dummy.png"));
		} catch (IOException e) {e.printStackTrace();}
		bounding = new Rectangle((int)x+(stand.getWidth()/4),y,stand.getWidth()/2,stand.getHeight());
	}
	public void giveBg(Background bg){
		this.bg=bg;
	}
	
	public Rectangle getBounding(){
		return bounding;
	}
	public BufferedImage getLook(){
		return stand;
	}
	public void update(float fD){
		if(bg.getBgMovement()==1){		//->Rechts
			bounding.x-=bg.getSCROLLSPEED()*fD;
		}if(bg.getBgMovement()==2){		//->Links
			bounding.x+=bg.getSCROLLSPEED()*fD;
		}
	}
}
