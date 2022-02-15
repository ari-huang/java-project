import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.annotation.ElementType;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;




public class GameFrame extends JFrame{
	private Image image1;
	private Image image2;
	private Image image3;
	private Image image4;
	private int x1=0, y1=0;
	private int x2, y2;
	private int x3=0;
	private boolean flag = true;
	private JPanel mapjpanel;
	private int width = 70, height = 75;
	private Graphics buffergraphics;
	private Image bufferimage;
	public static int hp = 745;
	private int count =0;
	private int count1=0;
	private int count2=0;
	Life life = new Life(755,745);
	List<String> listmap = new ArrayList<>();
	List ghostlist = new ArrayList();
	List heartlist = new ArrayList();
    public GameFrame() {
    	super("¹q¬y«æ«æ´Î");
    	setSize(900,900);
    	setDefaultCloseOperation(3);
    	mapjpanel = new jpanel();
    	mapjpanel.setBounds(60,50,750,775);
    	mapjpanel.setBackground(Color.WHITE);
    	setLayout(null);
    	setVisible(true);
    	add(mapjpanel);
    	setBackground(Color.WHITE);
    	bufferimage = createImage(775,750);
    	buffergraphics = bufferimage.getGraphics();
    }
    
    public class jpanel extends JPanel{
    	public jpanel() {
    		setBounds(60,50,750,775);
    		setBackground(Color.WHITE);
    		addMouseMotionListener(new MouseHandler());
    	}
    	public void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		Pattern pattern = Pattern.compile("\\s+");
    		ImageIcon brickwall = new ImageIcon(getClass().getResource("brickwall.png"));
    		brickwall.setImage(brickwall.getImage().getScaledInstance(width,height, Image.SCALE_DEFAULT));
    		ImageIcon diamond = new ImageIcon(getClass().getResource("diamond.png"));
    		diamond.setImage(diamond.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
    		ImageIcon ghost = new ImageIcon(getClass().getResource("ghost.png"));
    		ghost.setImage(ghost.getImage().getScaledInstance(width,height, Image.SCALE_DEFAULT));
    		ImageIcon heart = new ImageIcon(getClass().getResource("heart.png"));
    		heart.setImage(heart.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
    		image1 = brickwall.getImage();
    		image2 = diamond.getImage();
    		image3 = ghost.getImage();
    		image4= heart.getImage();
    		try {
    			if(flag) {
    			
    			listmap=Files.lines(Paths.get("src/map.txt")).flatMap(line->pattern.splitAsStream(line)).collect(Collectors.toList());
    			for(int p =0; p<5; p++) {
    				Random random = new Random();
    				int l = random.nextInt(listmap.size());
    				Stream.of(listmap).forEach(a->a.set(l,"3"));
    		
    				
    			}
    			for(int q=0;q<7;q++) {
    				Random random = new Random();
    				int i = random.nextInt(listmap.size());
    				Stream.of(listmap).forEach(string->string.set(i,"4"));
    			}
    			
    			System.out.println(listmap);
    			
    			
    			listmap.stream().forEach(string->{
    				int i = Integer.valueOf(string);
    				
    				
    				if(i==1) {
    				
    					buffergraphics.drawImage(image1,x1,y1,null);
    					
    				}else if(i==2) {
    					buffergraphics.drawImage(image2,x1,y1,null);
    				}else if(i==3) {
    					buffergraphics.drawImage(image3,x1,y1,null);
    					ghostlist.add(count1);
    				}else if(i==4) {
    					buffergraphics.drawImage(image4,x1,y1,null);
    					heartlist.add(count1);
    				}
    			
    					if(x1<750) {
    						x1+=75;
    					}
    					if(x1==750) {
    						x1=0;
    						y1+=75;
    					}
    				count1++;
    			});
    			}
    		}catch(IOException e) {
    			System.err.println("error");
    		}
    		
    		
    			
    		
    		
    		life.draw(g);
    		g.drawImage(bufferimage,0,0,null);
    	}
    }
    private class MouseHandler implements MouseMotionListener{
    	public void mouseMoved(MouseEvent event) {
    		flag = false;
    		x2=event.getX();
    		y2=event.getY();
    		life.setY(life.getY()-5);
    		repaint();
    		
    		
    		
    		
    	
    	}
    	public void mouseDragged(MouseEvent event) {}
    }
   
}
