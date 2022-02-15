
//
//加分功能: 增加draw3DRect三維矩形功能與fill3DRect
//			加入JMenuBar、JMenu和兩項JMenuItem 
//			在JMenuBar設置熱鍵功能
//			增加變換背景顏色的JButton
//			增加漸層顏色使筆刷顏色可以漸層的JButton
//			在JMenu的檔案中增加開啟檔案功能的JMenuItem,並用FileNameExtensionFilter過濾檔案類型
//			在JMenu的檔案中增加儲存檔案功能的JMenuItem


import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;



import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JButton;

import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.MouseEvent;
import java.io.*;
public class DrawingTool extends JPanel{
		
		private final JLabel toolJLabel;
		private final JComboBox<String> toolJComboBox;
		private final String[] tool = {"筆刷","直線","橢圓形","矩形","圓角矩形","三維矩形"};
		private final JPanel toolPanel; //繪圖工具變數宣告
		
		private final String small;
		private final String medium;
		private final String big;
		private final JLabel sizeJLabel;
		private  JRadioButton bigJRadioButton;
		private  JRadioButton mediumJRadioButton;
		private  JRadioButton smallJRadioButton;
		private ButtonGroup radioGroup;
		private final JPanel sizePanel; //筆刷大小變數宣告
		
		private final JPanel fillJPanel;
		private final JCheckBox  fillJCheckBox; //填滿變數宣告
		
		private final JButton colorJButton;
		private final JButton cleanJButton;
		private final JButton eraseJButton;
		private final JButton undoJButton;
		private final JButton redoJButton;
		private final JButton BackgroundJButton;
		private final JButton Background1JButton;
		private final JPanel buttonPanel;
		private  Color backgroundcolor;
		private final JLabel statusBar;

		private JFrame frame;
		private boolean clean = false;
		private final JPanel DrawingJPanel;
		private final JPanel situationJPanel;
		public BufferedImage image;
		private Color color = Color.black;
	
		
		
		int drawingtool=0, size =5, x=0, type=0,Ctype=0;
		private boolean fill = false;
		private int x1, y1, x2, y2, drag=0,change; // 宣告位置
		
		private JMenu File;
		private JMenu Edit, view, image1, color1, instruction,  setting;
		private JMenuItem item0;
		private JMenuItem item1; //宣告JMenu
		private UndoManager undoManager = new UndoManager();
		private Color color2, color3;
		private int shapeCount, fileorder;
		public DrawingTool() {
			frame = new JFrame("小畫家");
			frame.setLayout(new GridBagLayout());
			statusBar = new JLabel("鼠標位置");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			DrawingJPanel = new drawingJPanel();
			DrawingJPanel.setBackground(Color.WHITE);
			
			
			
			JMenu File = new JMenu("檔案(Alt+F)");
			JMenu Edit = new JMenu("編輯(Alt+E)");
			JMenu view = new JMenu("檢視(Alt+V)");
			JMenu image1= new JMenu("影像(Alt+I)");
			JMenu instruction = new JMenu("說明(Alt+H)");
			JMenu color1 = new JMenu("色彩(Alt+c)");
			JMenu setting = new JMenu("設定(Alt+S)");
			File.setMnemonic('F');  //設定熱鍵Alt+F
			Edit.setMnemonic('E');
			view.setMnemonic('V');
			image1.setMnemonic('I');
			color1.setMnemonic('c');
			setting.setMnemonic('S');
			instruction.setMnemonic('H'); //設定熱鍵
			item0 = new JMenuItem("儲存檔案");
			item0.addActionListener(e->{
				JFileChooser fileChooser1 = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG&GIF Images","jpg","gif");
				fileChooser1.setFileFilter(filter);
				int returnVal1=fileChooser1.showSaveDialog(this);
				if (returnVal1==JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(this,"You save the file in:"+fileChooser1.getSelectedFile().getName());
				}
				
			});
			item1 = new JMenuItem("開啟舊檔"); //在檔案中加入JMenuItem
			item1.addActionListener(e->{
				Path path1 = getFileOrDirectoryPath();
		
			});
			File.add(item0);
			File.add(item1);
			JMenuBar bar = new JMenuBar();
			bar.add(File);
			bar.add(Edit);
			bar.add(view);
			bar.add(image1);
			bar.add(color1);
			bar.add(instruction);
			bar.add(setting);  //將JMenu加入JMenuBar中
			frame.setJMenuBar(bar); // 將JMenuBar加入frame中
			
		
			toolJLabel = new JLabel("繪圖工具");
			toolJComboBox = new JComboBox<String>(tool);
			toolPanel = new JPanel();
			toolPanel.setLayout(new GridLayout(2,1));
			toolPanel.add(toolJLabel);
			toolPanel.add(toolJComboBox);
			
			toolJComboBox.addItemListener(
					new ItemListener() {
						public void itemStateChanged(ItemEvent event) {
								String string1 = (String) event.getItem();
								if(event.getStateChange() == ItemEvent.SELECTED) {
									System.out.println("選擇"+string1);
								}
								
								if(event.getStateChange()==ItemEvent.SELECTED)
									drawingtool = toolJComboBox.getSelectedIndex();
								if(event.getItem()=="筆刷") {
									fillJCheckBox.setEnabled(false);
								}else {
									fillJCheckBox.setEnabled(true);
								}
						}
					}
			);
			GridBagConstraints location = new GridBagConstraints();
			location.gridx = 0;
			location.gridy =0;
			location.gridwidth =1;
			location.gridheight = 1;
			location.weightx =0;
			location.weighty =0;
			location.fill = GridBagConstraints.NONE;
			location.anchor = GridBagConstraints.WEST;
			frame.add(toolPanel, location);
			
			
			sizeJLabel = new JLabel("筆刷大小");
			bigJRadioButton = new JRadioButton("大", false);
			mediumJRadioButton = new JRadioButton("中", false);
			smallJRadioButton = new JRadioButton("小", true);
			
			
			sizePanel = new JPanel();
			sizePanel.setLayout(new BorderLayout());
			sizePanel.add(sizeJLabel,BorderLayout.NORTH);
			sizePanel.add(smallJRadioButton,BorderLayout.WEST);
			sizePanel.add(mediumJRadioButton,BorderLayout.CENTER);
			sizePanel.add(bigJRadioButton,BorderLayout.EAST);
			radioGroup = new ButtonGroup();
			radioGroup.add(bigJRadioButton);
			radioGroup.add(mediumJRadioButton);
			radioGroup.add(smallJRadioButton);
			
			small = new String("小");
			medium = new String("中");
			big = new String("大");
			smallJRadioButton.addItemListener(new RadioButtonHandler(small));
			mediumJRadioButton.addItemListener(new RadioButtonHandler(medium));
			bigJRadioButton.addItemListener(new RadioButtonHandler(big));
			GridBagConstraints location1 = new GridBagConstraints();
			location1.gridx = 1;
			location1.gridy =0;
			location1.gridwidth =1;
			location1.gridheight = 1;
			location1.weightx =0;
			location1.weighty =0;
			location1.fill = GridBagConstraints.NONE;
			location1.anchor = GridBagConstraints.WEST;
			frame.add(sizePanel, location1);
			
			
			fillJPanel = new JPanel();
			fillJPanel.setLayout(new BorderLayout());
			fillJCheckBox = new JCheckBox("填滿");
			fillJPanel.add(fillJCheckBox,BorderLayout.NORTH);
			
			CheckBoxHandler handler1 = new CheckBoxHandler();
			fillJCheckBox.addItemListener(handler1);
			fillJCheckBox.setEnabled(false);
			GridBagConstraints location2 = new GridBagConstraints();
			location2.gridx = 2;
			location2.gridy =0;
			location2.gridwidth =1;
			location2.gridheight = 1;
			location2.weightx =0;
			location2.weighty =0;
			location2.fill = GridBagConstraints.NONE;
			location2.anchor = GridBagConstraints.WEST;
			frame.add(fillJPanel, location2);
			
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(1,2));
			colorJButton = new JButton("筆刷顏色");
			cleanJButton = new JButton("清除畫面");			
			eraseJButton = new JButton("橡皮擦");
			undoJButton = new JButton("上一步");
			redoJButton = new JButton("下一步");
			Background1JButton = new JButton("漸層顏色");
			
			BackgroundJButton = new JButton("背景顏色");
			buttonPanel.add(colorJButton);
			buttonPanel.add(eraseJButton);
			buttonPanel.add(undoJButton);
			buttonPanel.add(redoJButton);
			buttonPanel.add(cleanJButton);
			buttonPanel.add(BackgroundJButton);
			buttonPanel.add(Background1JButton);
			GridBagConstraints location3 = new GridBagConstraints();
			location3.gridx = 3;
			location3.gridy =0;
			location3.gridwidth =1;
			location3.gridheight = 1;
			location3.weightx =0;
			location3.weighty =0;
			location3.fill = GridBagConstraints.NONE;
			location3.anchor = GridBagConstraints.WEST;
			frame.add(buttonPanel, location3);
			ButtonHandler handler = new ButtonHandler();
			eraseJButton.addActionListener(handler);
			colorJButton.addActionListener(handler);
			cleanJButton.addActionListener(handler);
			undoJButton.addActionListener(handler);
			redoJButton.addActionListener(handler);
			Background1JButton.addActionListener(handler);
			colorJButton.addActionListener(e->{
				color = JColorChooser.showDialog(DrawingTool.this,"Choose a color", color);
			});
			BackgroundJButton.addActionListener(e->{
				backgroundcolor = JColorChooser.showDialog(DrawingTool.this,"Choose a color", color);
				DrawingJPanel.setBackground(backgroundcolor);
			});
			
			situationJPanel= new JPanel();
			situationJPanel.setLayout(new BorderLayout());
			situationJPanel.setBackground(Color.BLACK);
			situationJPanel.add(statusBar,BorderLayout.WEST);
			GridBagConstraints location4 = new GridBagConstraints();
			location4.gridx = 0;
			location4.gridy =2;
			location4.gridwidth =2;
			location4.gridheight = 1;
			location4.weightx =1;
			location4.weighty =1;
			location4.fill = GridBagConstraints.HORIZONTAL;
			location4.anchor = GridBagConstraints.SOUTH;
			frame.add(statusBar, location4);
			
			
			
			GridBagConstraints location5 = new GridBagConstraints();
			location5.gridx=0;
			location5.gridy = 2;
			location5.gridwidth=8;
			location5.gridheight=1;
			location5.weightx=1;
			location5.weighty =1;
			location5.fill =GridBagConstraints.BOTH;
			location5.anchor = GridBagConstraints.WEST;
			frame.add(DrawingJPanel,location5);
				
			frame.setVisible(true);
			frame.setSize(880,400);
			frame.setLocationRelativeTo(null);
			
		}		
		
		private Path getFileOrDirectoryPath() {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG&GIF Images","jpg","gif");
			fileChooser.setFileFilter(filter);
			int returnVal = fileChooser.showOpenDialog(this);
			if(returnVal==JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null,"You chose to open this file:"+fileChooser.getSelectedFile().getName());
			}
			return fileChooser.getSelectedFile().toPath();
		}
		private class ButtonHandler implements ActionListener{
			public void actionPerformed(ActionEvent event) {				
				System.out.println("點選"+event.getActionCommand());
				if(event.getSource()==cleanJButton) {
					image = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
					x1=0;x2=0;y1=0;y2=0;
					DrawingJPanel.setBackground(Color.WHITE);
					clean= true;
					DrawingJPanel.repaint();
				}
				if(event.getSource()==eraseJButton) {
					drawingtool= 6;
				}
				if(event.getSource()==undoJButton) {
					if(shapeCount>0) {
						DrawingJPanel.repaint();
						shapeCount--;
						change++;
					
					}
				}
				
				if(event.getSource()==Background1JButton) {
					color2=JColorChooser.showDialog(DrawingTool.this,"選擇顏色一",color2);
					color3=JColorChooser.showDialog(DrawingTool.this,"選擇顏色二",color3);
					Ctype=1;
					if(color2==null || color3 ==null) {
					Ctype =0;
					}
				}
			}
		}
		
		
		
		private	class RadioButtonHandler implements ItemListener{
			private String sizetext;
			public RadioButtonHandler(String s) {
				sizetext = s;		
			}
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED)
				System.out.println("選擇  "+sizetext+" "+"筆刷");
				
				if(event.getSource() == smallJRadioButton) {
					size = 5;
					
				}
				if(event.getSource() == mediumJRadioButton) {
					size = 10;
					
				}
				if (event.getSource() == bigJRadioButton) {
					size = 20;
					
				}
			}
		}	
	
		private class CheckBoxHandler implements ItemListener{
			public void itemStateChanged(ItemEvent event) {
					if(event.getStateChange()==ItemEvent.SELECTED)
						System.out.println("選擇填滿");
					else
						System.out.println("取消填滿");
				
					if(fillJCheckBox.isSelected()) {
						fill = true;
					}else {
						fill = false;
				}
			}	
		}
		
		
		
		public class drawingJPanel extends JPanel{
			private final ArrayList<Point> points = new ArrayList<>(); 
			
			public drawingJPanel() {
				image = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_ARGB_PRE);
				MouseHandler handler = new MouseHandler();
				addMouseListener(handler);
				addMouseMotionListener(handler);
				
				
			}
			
			private class MouseHandler implements MouseListener, MouseMotionListener{
				
				public void mouseClicked(MouseEvent event) {
						statusBar.setText(String.format("指標位置:(%d, %d)(點擊)",event.getX(),event.getY()));
				}
				
				public void mousePressed(MouseEvent event) {
					statusBar.setText(String.format("指標位置:(%d, %d)(按下)",event.getX(),event.getY()));
					x1=event.getX();
					y1=event.getY();
					
				
					
				}
				
				public void mouseReleased(MouseEvent event) {
					statusBar.setText(String.format("指標位置:(%d, %d)(放開)",event.getX(),event.getY()));
					x2=event.getX();
					y2=event.getY();
					drag=0;
					repaint();
					shapeCount++;
					
				}
				
				public void mouseEntered(MouseEvent event) {
					statusBar.setText(String.format("指標位置:(%d, %d)(進入畫布)",event.getX(), event.getY()));
				}
				
				public void mouseExited(MouseEvent event) {
					statusBar.setText(String.format("指標離開畫布外"));
				}
				
				public void mouseDragged(MouseEvent event) {
					statusBar.setText(String.format("指標位置:(%d, %d)(拖曳)", event.getX(), event.getY()));
					
						if(drawingtool == 0 || drawingtool==6) {
							points.add(event.getPoint());
							repaint();
						}
						if(drawingtool == 2 ) {
							x2 = event.getX();
							y2 = event.getY();
							repaint();
							drag=1;
						}
					
						if(drawingtool == 3) {
							x2 = event.getX();
							y2 = event.getY();
							repaint();
							drag =1;
						}
					
						if(drawingtool == 4) {
							x2= event.getX();
							y2= event.getY();
							repaint();
							drag=1;
						}
						
					
				}
				
				public void mouseMoved(MouseEvent event) {
					statusBar.setText(String.format("指標位置:(%d, %d)(移動)", event.getX(), event.getY()));
				}
				
			}
			
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics gg = image.createGraphics();
			Graphics2D g2d =(Graphics2D) gg;
			g2d.setPaint(color);
			g.setColor(color);

			if(Ctype==0) {
				g2d.setPaint(color);
			}else {
				g2d.setPaint(new GradientPaint(x1,y1,color2,x2,y2,color3,false));
			}
			if(clean) {
				g2d.setColor(Color.WHITE);
				points.clear();
				clean=false;
			}
			
			switch(drawingtool) {
				case 0:
					for (Point point : points)
				         g2d.fillOval(point.x, point.y, size, size);
					break;
				case 1:
					if(fill == true)
						g2d.drawLine(x1, y1, x2, y2);
					else {
						Stroke dasked = new BasicStroke(3, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,new float[] {9},0);
						g2d.setStroke(dasked);
						g2d.drawLine(x1, y1, x2, y2);
						g2d.dispose();
					}
					break;
					
				case 2:
					if(drag ==1) {
						g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
					}
					if(drag == 0) {
						if(fill)
							g2d.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
						else {
							g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
						}
					}
					break;
					
				case 3:
					if(drag == 1) {
						g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
					}
					
					if(drag == 0) {
						if(fill)
							g2d.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
						else {
							g2d.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
						}
								
					}
					break;
				
				case 4:
					if(drag ==1) {
						g.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2),50, 50);
					}
					if(drag ==0) {
						if(fill)
							g2d.fillRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2),50, 50);
						else {
							g2d.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2),50, 50);
						}
					}
					break;
				case 5:
					if(drag==1) {
						g.draw3DRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2),true);
					}
					if(drag==0) {
						if(fill)
							g2d.fill3DRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2),true);
						else {
							g2d.draw3DRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2),true);
						}
					}
					
				case 6:
					g2d.setColor(Color.WHITE);
					for (Point point : points)
						g2d.fillOval(point.x, point.y, size, size);
					break;
				
			}
			if(change==1) {
			}
		
			
			g.drawImage(image,0,0,this);
			
		}
		
	}
		
		
}

	
	
	

