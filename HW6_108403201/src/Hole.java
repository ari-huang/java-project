import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Hole {
	private int x;
	private ImageIcon holeimageicon;

	private int y;
	Graphics buffergraphics;
	public Hole(int x, int y, Graphics buffergraphics) {
		holeimageicon = new ImageIcon(getClass().getResource("¯}¬}.jpg"));
		holeimageicon.setImage(holeimageicon.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT));
		this.x =x;
		this.y =y;
		this.buffergraphics = buffergraphics;
		for (int i=0;i<5;i++)
		buffergraphics.drawImage(holeimageicon.getImage(),x,y,null);
	
	}
}
