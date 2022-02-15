import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JPanel;

public class Turtle extends JApplet implements Runnable{
	private int x;
	private int y;
	private Boolean Continue;
	private int z;
	private int size=120;
	private int height =575;
	private boolean flag= true;
	private boolean flag1=true;
	private int turtlenumber=0;
	private int hp=99;
	private Graphics buffergraphics;
	private static final SecureRandom generator = new SecureRandom();
	private static final SecureRandom generator1 = new SecureRandom();
	private final ImageIcon[] turtleimageicon = { new ImageIcon(getClass().getResource("turtle.png")),
	                                        new ImageIcon(getClass().getResource("turtle2.png"))};
	private Color color;
	public Turtle(int x, int y, Graphics buffergraphics,Boolean Continue) {
		super();
		this.x = x;
		this.y = y;
		this.Continue = Continue;
		this.buffergraphics = buffergraphics;
		
	
	}
	public void draw(Graphics g) {
		color = new Color(135,206,250);
		g.setColor(color);
		z= generator.nextInt(2);
		g.drawImage(turtleimageicon[z].getImage(),x,y,null);
		
	}
	public void run() {
		while (Continue) {
			if(flag) {
				paint(buffergraphics);
			}
			
			if(y+5<height) {
				y+=5;
			}else {
				if(flag1) {
				z=generator1.nextInt(2);
				}
				switch(z) {
					case 0:
						if(x+5>725) {z=1;}
						x+=5;
						break;
					case 1:
						if(x-5<0) {z=0;}
						x-=5;
						break;
				}
				flag1=false;
			}
			this.update(buffergraphics);
			try {
			
				Thread.sleep(25);
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
			flag=false;
		}
		update(buffergraphics);
	}
	public void paint(Graphics g) {
		super.paint(g);
	
		g.setColor(new Color(135,206,250));

		if(flag) {
			g.fillRect(0,0,1000,1000);

			g.drawImage(turtleimageicon[z].getImage(),size,size,120,120,null);
			g.drawRect(x+15,y-10,100,15);
		}else {
			g.fillRect(0,0,1000,1000);

			g.setColor(Color.BLACK);
			g.drawRect(x+15,y-10,100,16);
			g.setColor(Color.RED);
			g.fillRect(x+14,y-9,hp,14);
			g.drawImage(turtleimageicon[z].getImage(),x,y,size,size,null);
			
		}
	
	}
	public void update(Graphics g) {
		g.setColor(new Color(135,206,250));
		g.fillRect(0,0,1000,1000);
		if(Continue==true) {
			paint(g);
		}
	}
	public void Continue(Boolean Continue) {
		if(Continue==false) {
			this.Continue=false;
			this.turtlenumber=0;
		}
	}
	public int getturtlenumber() {
		return turtlenumber;
	}
	public void setturtlenumber(int turtlenumber){
		this.turtlenumber=turtlenumber;
	}	
	public void listen(int x1, int y1) {
		if((x+75)>x1&&x1>x&&y1>y&&y1<y+75) {
			this.Continue=false;
			this.turtlenumber-=1;
		}
	}
	public void shoot(int x2, int y2) {
		if((x+75)>x2&&x2>x&&y2>y&&y2<y+75) {
			hp-=33;
			repaint();
		}
		if(hp==0) {
			turtlenumber-=1;
			this.Continue=false;
			update(buffergraphics);
		}
	}
	public void bebig(int x3, int y3) {
			if(x+75>x3&&x3>x&&y3>y&&y3<y+75) {
				size+=5;
				y-=20;
				height-=5;
				paint(buffergraphics);
			}
	}
	
	
}
