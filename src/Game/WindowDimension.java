package Game;

import java.awt.Rectangle;

public class WindowDimension {
	public final int WEIGHT;
	public final int HEIGHT;
	public Rectangle window;
	
	public WindowDimension(int Weight,int Height){
		this.WEIGHT=Weight;
		this.HEIGHT=Height;
		window = new Rectangle(0,0,Weight,Height);
	}
	public int getHeight(){
		return HEIGHT;
		}
	public int getWeight(){
		return WEIGHT;
	}
	public Rectangle getWindow(){
		return window;
	}
}
