import java.awt.Color;
import java.awt.Graphics;

public class Life {
	private int x;
	private int y;
	private int width = 40;
	private int height =10;
	private int life;
	public Life() {
		
	}
	public Life(int x, int y) {
		super();
		this.x=x;
		this.y=y;
		
	}
	public int getX(){
		return x;
		
	}
	public void setX(int x) {
		this.x =x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y=y;
	}
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(0,x,y,15);
		g.setColor(Color.BLUE);
		g.fillRect(0,x,y,14);
		
	}
}
