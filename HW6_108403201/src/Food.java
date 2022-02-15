import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JApplet;

public class Food extends JApplet implements Runnable{
	private int x;
	private int y;
	private boolean flag = true;
	private boolean Continue=true;
	private Graphics buffergraphics;
	ArrayList<Turtle> turtlearray= new ArrayList();
	ArrayList<Fish> fisharray = new ArrayList();
	ImageIcon  foodimageicon= new ImageIcon(getClass().getResource("cookie.png"));
	public Food(int x, int y, Graphics buffergraphics,ArrayList<Turtle> turtlearray,ArrayList<Fish> fisharray) {
		super();
		this.x = x;
		this.y = y;
		this.buffergraphics = buffergraphics;
		this.turtlearray=turtlearray;
		this.fisharray= fisharray;
		foodimageicon.setImage(foodimageicon.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT));
	}
	public void run() {
		while(Continue) {
			if(flag) {
				paint(buffergraphics);
			}
			if(y+5<620) {
				y+=5;
			}else {
				Continue=false;
			}
			flag=false;
			this.update(buffergraphics);
			try {
				Thread.sleep(30);
			}catch(InterruptedException e) {
				Thread.currentThread().interrupt();
				
			}
			turtlearray.forEach(e->e.bebig(x,y));
			fisharray.forEach(e->e.bebig(x,y));
		}
		update(buffergraphics);
		
	}
	public void paint(Graphics g ) {
		super.paint(g);
		g.setColor(new Color(135,206,250));
		if(flag) {
			g.fillRect(0,0,1000,1000);
			g.drawImage(foodimageicon.getImage(),x,y,null);
		}else {
			g.drawImage(foodimageicon.getImage(),x,y,null);
		}
	}
	public void update(Graphics g) {
		g.setColor(new Color(135,206,250));
		g.fillRect(0,0,1000,1000);
		if(Continue) {
		paint(g);
		}
		
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}
