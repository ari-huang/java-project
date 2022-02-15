import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class GameFrame extends JFrame  {
	private final JButton AddFishJButton;
	private final JButton AddTurtleJButton;
	private final JButton RemoveChooseJButton;
	private final JButton foodJButton;
	private final JButton shootJButton;
	private final JButton RemoveAllJButton;
	private final JLabel CurrentFunctionJLabel;
	private final JLabel FishNumberJLabel;
	private boolean Continue=true;
	private final JLabel TurtleNumberJLabel;
	private int fishnumber=0;
	private int turtlenumber=0;
	private Image iBuffer;
	private int x2=0, y2=0;
	private boolean flag =true;
	GameJPanel gameJPanel = new GameJPanel();
	private Color color;
	private int x=0, x1=0;
	private int y=0, y1=0,fishnumber1=0,turtlenumber1=0;
	private boolean flag1=true;
	private int functionnumber=1, foodnumber=0;
	ArrayList<Fish> fisharray= new ArrayList();
	ArrayList<Turtle> turtlearray= new ArrayList();
	ArrayList<Food> foodarray = new ArrayList();
	ImageIcon grassimageicon = new ImageIcon(getClass().getResource("海草圖片1.gif"));
	Turtle turtle;
	Fish fish;
	private Image bufferimage,grassimage;
	private Graphics buffergraphics;
	ExecutorService executorService = Executors.newCachedThreadPool();
	public GameFrame()  {
		super("FishBowl");
		GridBagLayout layout = new GridBagLayout();
		AddFishJButton = new JButton("新增魚");
		AddTurtleJButton = new JButton("新增烏龜");
		RemoveChooseJButton = new JButton("移除選取");
		RemoveAllJButton = new JButton("移除全部");
		CurrentFunctionJLabel = new JLabel("目前功能: 新增魚");
		FishNumberJLabel = new JLabel("魚數量:   ");
		TurtleNumberJLabel = new JLabel("烏龜數量:  ");
		setLayout(layout);
		foodJButton = new JButton("飼料");
		shootJButton = new JButton("射擊");
		color = new Color(135,206,250);
		
		TurtleNumberJLabel.setText("烏龜數量:  "+String.valueOf(turtlenumber));
		FishNumberJLabel.setText("魚數量: "+String.valueOf(fishnumber));
		grassimageicon.setImage(grassimageicon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
		GridBagConstraints s = new GridBagConstraints();
		s.fill = GridBagConstraints.BOTH;
		s.anchor= GridBagConstraints.CENTER;
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty=0;
		AddFishJButton.setPreferredSize(new Dimension(365,50));
		add(AddFishJButton,s);
		s.fill = GridBagConstraints.BOTH;
		s.anchor= GridBagConstraints.CENTER;
		s.gridwidth=0;
		s.weightx=1;
		s.weighty = 0;
		RemoveChooseJButton.setPreferredSize(new Dimension(385,25));
		add(RemoveChooseJButton,s);
		s.fill = GridBagConstraints.NONE;
		s.anchor= GridBagConstraints.NORTH;
		s.gridwidth=1;
		s.weightx =0;
		s.weighty=1;
		
		AddTurtleJButton.setPreferredSize(new Dimension(385,50));
		add(AddTurtleJButton,s);
		s.fill = GridBagConstraints.HORIZONTAL;
		s.anchor= GridBagConstraints.NORTH;
		s.gridwidth=0;
		s.weightx=1;
		s.weighty=1;
		RemoveAllJButton.setPreferredSize(new Dimension(385,50));
		add(RemoveAllJButton,s);
		
		s.fill = GridBagConstraints.HORIZONTAL;
		s.anchor= GridBagConstraints.NORTH;
		s.gridwidth=1;
		s.weightx=0;
		s.weighty=2;
		add(foodJButton,s);
		foodJButton.setPreferredSize(new Dimension(385,50));

		s.fill = GridBagConstraints.HORIZONTAL;
		s.anchor= GridBagConstraints.NORTH;
		s.gridwidth=0;
		s.weightx=1;
		s.weighty=2;
		add(shootJButton,s);
		shootJButton.setPreferredSize(new Dimension(385,50));

		s.fill = GridBagConstraints.NONE;
		s.anchor= GridBagConstraints.NORTHWEST;
		s.gridwidth=1;
		
		s.weightx=0;
		s.weighty=3;
		add(CurrentFunctionJLabel,s);
		s.fill = GridBagConstraints.NONE;
		s.anchor= GridBagConstraints.NORTHWEST;
		s.gridwidth=1;
		s.weightx=1;
		s.weighty=3;

		add(FishNumberJLabel,s);
		s.fill = GridBagConstraints.HORIZONTAL;
		s.anchor= GridBagConstraints.NORTHWEST;
		s.gridwidth=0;
		s.weightx=2;
		s.weighty=3;

		add(TurtleNumberJLabel,s);
		s.fill = GridBagConstraints.BOTH;
		s.anchor = GridBagConstraints.CENTER;
		s.gridwidth=0;
		s.weightx=0;
		s.weighty=150;
		
		gameJPanel.setBackground(color);
		gameJPanel.addMouseListener(new MouseHandler());
		ButtonHandler handler = new ButtonHandler();
		AddFishJButton.addActionListener(handler);
		AddTurtleJButton.addActionListener(handler);
		RemoveChooseJButton.addActionListener(handler);
		RemoveAllJButton.addActionListener(handler);
		foodJButton.addActionListener(handler);
		shootJButton.addActionListener(handler);
		add(gameJPanel,s);
		setSize(900,900);
    	setDefaultCloseOperation(3);
    	setVisible(true);
    	bufferimage = createImage(gameJPanel.getWidth(),gameJPanel.getHeight());
    	buffergraphics = bufferimage.getGraphics();
    	iBuffer = createImage(gameJPanel.getWidth(),gameJPanel.getHeight());
    	
    	buffergraphics.setColor(color);
    

	}
	private class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {
			
			x=event.getX();
			y=event.getY();
			
			
			flag=false;
			switch(functionnumber) {
				case 1:
					Continue=true;
					
					fisharray.add(new Fish(x,y,buffergraphics,Continue));
					
					executorService.execute(fisharray.get(fishnumber1));
					fishnumber1++;
					fishnumber++;
					FishNumberJLabel.setText("魚數量: "+String.valueOf(fishnumber) );

					break;
				case 2:
					Continue=true;
					turtlearray.add(new Turtle(x,y,buffergraphics,Continue));
					executorService.execute(turtlearray.get(turtlenumber1));
					turtlenumber1++;
					turtlenumber++;

					TurtleNumberJLabel.setText("烏龜數量:  "+String.valueOf(turtlenumber));
					
					break;
				case 3:
					x1=event.getX();
					y1=event.getY();
					if(fisharray.size()>0) {
						fisharray.forEach(e->e.listen(x1,y1));
						fishnumber-=1;
						fisharray.forEach(e->e.setfishnumber(fishnumber));
						FishNumberJLabel.setText("魚數量: "+ String.valueOf(fishnumber));
					
						
					}
					break;
				case 4:
					
					
					break;
				case 5:
					Continue= true;
					foodarray.add(new Food(x,y,buffergraphics,turtlearray,fisharray));
					executorService.execute(foodarray.get(foodnumber));
					foodnumber++;
					break;
				case 6:
					x2=event.getX();
					y2=event.getY();
					if(turtlearray.size()>0) {
						turtlearray.forEach(e->e.shoot(x2, y2));
					}
					break;
					
			}
			
			

		}

	}
	
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("新增魚")){
				functionnumber=1;
				CurrentFunctionJLabel.setText("目前功能: 新增魚");
			}else if (cmd.equals("新增烏龜")) {
				functionnumber = 2;
				CurrentFunctionJLabel.setText("目前功能: 新增烏龜");

			}else if (cmd.equals("移除選取")) {
				functionnumber = 3;
				CurrentFunctionJLabel.setText("目前功能: 移除選取");

			}else if(cmd.equals("移除全部")){
				functionnumber = 4;
				CurrentFunctionJLabel.setText("目前功能: 移除全部");
				Continue = false;
				fisharray.forEach(event->event.Continue(Continue));
				turtlearray.forEach(c->c.Continue(Continue));
				if(fisharray.size()>0) {
				FishNumberJLabel.setText("魚數量: "+String.valueOf(fisharray.get(0).getfishnumber()));
				fishnumber=fisharray.get(0).getfishnumber();
				fisharray.forEach(u->u.setfishnumber(fishnumber));
				}
				if(turtlearray.size()>0) {
					TurtleNumberJLabel.setText("烏龜數量: "+ String.valueOf(turtlearray.get(0).getturtlenumber()));
					turtlenumber=turtlearray.get(0).getturtlenumber();
					turtlearray.forEach(u->u.setturtlenumber(turtlenumber));
				}
			}
			if(cmd.equals("飼料")) {
				functionnumber=5;
				CurrentFunctionJLabel.setText("目前功能: 飼料");
			}else if(cmd.equals("射擊")) {
				functionnumber=6;
				setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

				CurrentFunctionJLabel.setText("目前功能: 射擊");
			}
		}
	}


	public class GameJPanel extends JPanel implements Runnable{
		
		public GameJPanel() {
			
			setBackground(color);
			Thread thread = new Thread(this);
			thread.start();
		
		}
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			
			
			if(!flag) {
			
				g.drawImage(bufferimage,0,0,null);
				}
				
			
		}
		public void update(Graphics g) {
			
			paint(g);
		}
		public void run() {
			
			while(true) {
				
				repaint();
				
			}
			
			
		}
		
	}



	
}
