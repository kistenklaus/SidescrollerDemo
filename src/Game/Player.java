package Game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;



public class Player {
	private final float PLAYERSPEED = 200;
	private BufferedImage[] stand = new BufferedImage[2];
	private static BufferedImage[] walk_r = new BufferedImage[4];
	private static BufferedImage[] walk_l = new BufferedImage[4];
	private static BufferedImage[] punch_r = new BufferedImage[8];
	private static BufferedImage[] punch_l = new BufferedImage[8];
	private static BufferedImage look;
	private Rectangle bounding;
	private Rectangle HitBox;
	private boolean direktion; 		//-right=true -left=false
	private float f_posx;
	private float f_posy;
	private float inMotionTimer;
	private final float ANISPEED = 0.1f;
	private byte movAniStep = 0;
	private boolean punching;
	private float inPunchTimer;
	private float PUNCHSPEED = 0.3f;
	private byte punchAniStep = 0;
	private Background bg;
	private Dummy dummy;
	private float wpDamage=2;
	private float damage;
	
	
	public Player(int x, int y,Dummy dummy){
		try {
			stand[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_stehen_rechts.png"));
			stand[1] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_stehen_links.png"));
			
			walk_r[0] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_walk_r_1.png"));
			walk_r[1] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_walk_r_2.png"));
			walk_r[2] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_walk_r_3.png"));
			walk_r[3] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_walk_r_4.png"));
			walk_l[0] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_walk_l_1.png"));
			walk_l[1] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_walk_l_2.png"));
			walk_l[2] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_walk_l_3.png"));
			walk_l[3] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_walk_l_4.png"));
			
			punch_r[0] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_r1.png"));
			punch_r[1] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_r2.png"));
			punch_r[2] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_r3.png"));
			punch_r[3] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_r4.png"));
			punch_r[4] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_r5.png"));
			punch_r[5] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_r6.png"));
			punch_r[6] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_r7.png"));
			punch_r[7] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_r8.png"));
			
			punch_l[0] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_l1.png"));
			punch_l[1] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_l2.png"));
			punch_l[2] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_l3.png"));
			punch_l[3] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_l4.png"));
			punch_l[4] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_l5.png"));
			punch_l[5] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_l6.png"));
			punch_l[6] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_l7.png"));
			punch_l[7] =ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/char_punch_l8.png"));
			} catch (IOException e) {e.printStackTrace();
		}
		this.dummy=dummy;
		this.f_posx=x+stand[0].getWidth()/3;
		this.f_posy=y;
		
		//Bounding
		bounding = new Rectangle((int)f_posx,(int)f_posy,(int)stand[0].getWidth()/3,stand[0].getHeight());
		//HitBox
		HitBox = new Rectangle((int)f_posx,(int)f_posy,(int)bounding.getWidth(),(int)bounding.getHeight()/2);
	}
	//SETTER
	public void giveBg(Background bg){
		this.bg=bg;
	}
	
	
	public BufferedImage getLook(){
		if(inMotionTimer==0f){							//Stehend
			if(direktion==true){
				look= stand[0];
			}if(direktion==false){
				look= stand[1];
			}
		}
		if(inMotionTimer>0){							//LaufAni
			if(direktion==true){
				look= walk_r[movAniStep];
			}if(direktion==false){
				look= walk_l[movAniStep];
			}
		}
		if(punching){									//schlag
			if(direktion==true){
				look= punch_r[punchAniStep];
			}else look= punch_l[punchAniStep];
		}
		return look;
	}
	
	//GETTER
	public Rectangle getBounding(){
		return bounding;
	}
	public boolean getDirektion(){
		return direktion;
	}
	public boolean isPunching(){
		return punching;
	}public float getInMotionTimer(){
		return inMotionTimer;
	}
	
	public void update(float fD){ //fD = frameDelay
		if(!punching){
			punchAniStep=0;
			if(Keyboard.isKeyDown(KeyEvent.VK_D)){
				direktion=true;
				if(!(bounding.intersects(bg.getRightBorder()))&&inMotionTimer>0){
					f_posx+=PLAYERSPEED*fD;
				}
				bounding.x=(int)f_posx;
				inMotionTimer+=fD;
			}
			if(Keyboard.isKeyDown(KeyEvent.VK_A)){
				direktion=false;
				if(!(bounding.intersects(bg.getLeftBorder()))){
					f_posx-=PLAYERSPEED*fD;
				}
				bounding.x=(int)f_posx;
				inMotionTimer+=fD;
			}
			if(inMotionTimer>ANISPEED){
				inMotionTimer-=ANISPEED;
				movAniStep++;
				if(movAniStep>=walk_r.length)movAniStep=0;
			}
			//inMotion_false(setter)
			if(!(Keyboard.isKeyDown(KeyEvent.VK_D)||Keyboard.isKeyDown(KeyEvent.VK_A))
					||(Keyboard.isKeyDown(KeyEvent.VK_D)&&Keyboard.isKeyDown(KeyEvent.VK_A)))
				inMotionTimer=0;
			
			
			if(Keyboard.isKeyDown(KeyEvent.VK_SPACE))punching = true;
		}else{
			inPunchTimer+=fD;
			if(punchAniStep==0&&inPunchTimer>PUNCHSPEED/2){
				punchAniStep++;
				punch(punchAniStep);
			}
			else if(inPunchTimer>PUNCHSPEED){
				inPunchTimer-=PUNCHSPEED;
				punchAniStep++;
				switch (punchAniStep){
				case 3:
					//2Hit
					if(!Keyboard.isKeyDown(KeyEvent.VK_SPACE))punching=false;
					else punch(punchAniStep);
					break;
				case 4:
					break;
				case 5:
					//3Hit
					if(!Keyboard.isKeyDown(KeyEvent.VK_SPACE))punching=false;
					break;
				case 7:
					punch(punchAniStep);
					break;
				case 8:
					punchAniStep=0;
					break;
				}
			}
			
		}
	}
	
	public void punch(byte hitType){
								  //HitBox berrechung:
		if(direktion){					//rechts
			HitBox.setLocation((int)(HitBox.x+bounding.getWidth()), HitBox.y);
		}else{							//links
			HitBox.setLocation((int)(HitBox.x-bounding.getWidth()), HitBox.y);
		}
		if(HitBox.intersects(dummy.getBounding())){
			if(hitType==0)damage=wpDamage*1;
			if(hitType==3)damage=wpDamage*1.5f;
			if(hitType==7)damage=wpDamage*2f;
			System.out.println("DMG: "+damage);
			
			
		}
		HitBox.setLocation(bounding.x, HitBox.y);
	}
}



















