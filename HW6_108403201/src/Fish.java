import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JApplet;

public class Fish extends JApplet implements Runnable{
	private int x;
	private int y;
	private int i=0 ,p=0,q=0,r=0;
	private int size= 100;
	private int width=875;
	private Boolean Continue;
	private Graphics buffergraphics;
	boolean flag = true;
	private BufferedImage image;
	private int fishnumber;
	
	private Graphics gBuffer;
	private Image iBuffer;
	private static SecureRandom generator = new SecureRandom();
	private static SecureRandom generator1 = new SecureRandom();
	private static SecureRandom generator2 = new SecureRandom();
	private static SecureRandom generator3 = new SecureRandom();
	private final ImageIcon[] fishimageicon = { new ImageIcon(getClass().getResource("1.png")),
	                                        new ImageIcon(getClass().getResource("2.png")),
	                                        new ImageIcon(getClass().getResource("3.png")),
	                                        new ImageIcon(getClass().getResource("4.png")),
	                                        new ImageIcon(getClass().getResource("5.png")),
	                                        new ImageIcon(getClass().getResource("6.png"))};
	
	public Fish(int x, int y,Graphics buffergraphics,boolean Continue) {
		super();
		flag=true;
		this.x = x;
		this.y = y;
		this.Continue=Continue;
		this.fishnumber = fishnumber;
		
		fishnumber++;
		this.buffergraphics = buffergraphics;
		i=generator.nextInt(6);
		p=generator1.nextInt(2);
		q=generator2.nextInt(20);
		r=generator.nextInt(5)+1;
		if(p==0) {
			size-=(q+20);
		}else {
			size+=(q+20);
		}
		
		
	}
	
	public void run() {
		
		while(Continue) {
				
				if(flag) {
				paint(buffergraphics);
				}
				switch(i) {
				case 0: case 2: case 4:
				if(x+size>width && i==0) {i=1;}
				if(x+size>width && i==2) {i=3;}
				if(x+size>width && i==4) {i=5;}
				x+=r;
				this.update(buffergraphics);
				break;
					
				case 1: case 3: case 5:
				if(x-5<0 && i==1) {i=0;}
				if(x-5<0 && i==3) {i=2;}
				if(x-5<0 && i==5) {i=4;}
				x-=r;
				this.update(buffergraphics);
				break;
				}
			try {
				Thread.sleep(25);
			}catch(InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
			flag= false;
			
		}
		update(buffergraphics);
	}
	public void update(Graphics g) {
		g.setColor(new Color(135,206,250));
		g.fillRect(0,0,1000,1000);
		if(Continue) {
		paint(buffergraphics);
		}
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(135,206,250));
		
			
		
		
		if(flag) {
		g.fillRect(0,0,1000,1000);

		g.drawImage(fishimageicon[i].getImage(),x,y,size,size,null);
	
		}else {
		g.fillRect(0,0,1000,1000);

		g.drawImage(fishimageicon[i].getImage(),x,y,size,size,null);
		}
		


	}
	public void Continue(Boolean Continue)
	{
		if(Continue==false) {
			this.Continue=false;
			this.fishnumber=0;
		}
	}
	public int getfishnumber() {
		return	fishnumber;
	}
	public void setfishnumber(int fishnumber) {
		this.fishnumber=fishnumber;
	}
	public void listen(int x1, int y1) {
		if((x+size)>x1&&x1>x&&y1>y&&y1<y+size) {
			this.Continue=false;
			this.fishnumber-=1;
		}
	}
	public void bebig(int x3, int y3) {
		if(x+75>x3&&x3>x&&y3>y&&y3<y+75) {
			size+=5;
			width-=5;
			paint(buffergraphics);
		}
	}
}
