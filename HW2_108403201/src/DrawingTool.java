
//
//�[���\��: �W�[draw3DRect�T���x�Υ\��Pfill3DRect
//			�[�JJMenuBar�BJMenu�M�ⶵJMenuItem 
//			�bJMenuBar�]�m����\��
//			�W�[�ܴ��I���C�⪺JButton
//			�W�[���h�C��ϵ����C��i�H���h��JButton
//			�bJMenu���ɮפ��W�[�}���ɮץ\�઺JMenuItem,�å�FileNameExtensionFilter�L�o�ɮ�����
//			�bJMenu���ɮפ��W�[�x�s�ɮץ\�઺JMenuItem


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
		private final String[] tool = {"����","���u","����","�x��","�ꨤ�x��","�T���x��"};
		private final JPanel toolPanel; //ø�Ϥu���ܼƫŧi
		
		private final String small;
		private final String medium;
		private final String big;
		private final JLabel sizeJLabel;
		private  JRadioButton bigJRadioButton;
		private  JRadioButton mediumJRadioButton;
		private  JRadioButton smallJRadioButton;
		private ButtonGroup radioGroup;
		private final JPanel sizePanel; //����j�p�ܼƫŧi
		
		private final JPanel fillJPanel;
		private final JCheckBox  fillJCheckBox; //���ܼƫŧi
		
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
		private int x1, y1, x2, y2, drag=0,change; // �ŧi��m
		
		private JMenu File;
		private JMenu Edit, view, image1, color1, instruction,  setting;
		private JMenuItem item0;
		private JMenuItem item1; //�ŧiJMenu
		private UndoManager undoManager = new UndoManager();
		private Color color2, color3;
		private int shapeCount, fileorder;
		public DrawingTool() {
			frame = new JFrame("�p�e�a");
			frame.setLayout(new GridBagLayout());
			statusBar = new JLabel("���Ц�m");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			DrawingJPanel = new drawingJPanel();
			DrawingJPanel.setBackground(Color.WHITE);
			
			
			
			JMenu File = new JMenu("�ɮ�(Alt+F)");
			JMenu Edit = new JMenu("�s��(Alt+E)");
			JMenu view = new JMenu("�˵�(Alt+V)");
			JMenu image1= new JMenu("�v��(Alt+I)");
			JMenu instruction = new JMenu("����(Alt+H)");
			JMenu color1 = new JMenu("��m(Alt+c)");
			JMenu setting = new JMenu("�]�w(Alt+S)");
			File.setMnemonic('F');  //�]�w����Alt+F
			Edit.setMnemonic('E');
			view.setMnemonic('V');
			image1.setMnemonic('I');
			color1.setMnemonic('c');
			setting.setMnemonic('S');
			instruction.setMnemonic('H'); //�]�w����
			item0 = new JMenuItem("�x�s�ɮ�");
			item0.addActionListener(e->{
				JFileChooser fileChooser1 = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG&GIF Images","jpg","gif");
				fileChooser1.setFileFilter(filter);
				int returnVal1=fileChooser1.showSaveDialog(this);
				if (returnVal1==JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(this,"You save the file in:"+fileChooser1.getSelectedFile().getName());
				}
				
			});
			item1 = new JMenuItem("�}������"); //�b�ɮפ��[�JJMenuItem
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
			bar.add(setting);  //�NJMenu�[�JJMenuBar��
			frame.setJMenuBar(bar); // �NJMenuBar�[�Jframe��
			
		
			toolJLabel = new JLabel("ø�Ϥu��");
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
									System.out.println("���"+string1);
								}
								
								if(event.getStateChange()==ItemEvent.SELECTED)
									drawingtool = toolJComboBox.getSelectedIndex();
								if(event.getItem()=="����") {
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
			
			
			sizeJLabel = new JLabel("����j�p");
			bigJRadioButton = new JRadioButton("�j", false);
			mediumJRadioButton = new JRadioButton("��", false);
			smallJRadioButton = new JRadioButton("�p", true);
			
			
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
			
			small = new String("�p");
			medium = new String("��");
			big = new String("�j");
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
			fillJCheckBox = new JCheckBox("��");
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
			colorJButton = new JButton("�����C��");
			cleanJButton = new JButton("�M���e��");			
			eraseJButton = new JButton("�����");
			undoJButton = new JButton("�W�@�B");
			redoJButton = new JButton("�U�@�B");
			Background1JButton = new JButton("���h�C��");
			
			BackgroundJButton = new JButton("�I���C��");
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
				System.out.println("�I��"+event.getActionCommand());
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
					color2=JColorChooser.showDialog(DrawingTool.this,"����C��@",color2);
					color3=JColorChooser.showDialog(DrawingTool.this,"����C��G",color3);
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
				System.out.println("���  "+sizetext+" "+"����");
				
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
						System.out.println("��ܶ�");
					else
						System.out.println("������");
				
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
						statusBar.setText(String.format("���Ц�m:(%d, %d)(�I��)",event.getX(),event.getY()));
				}
				
				public void mousePressed(MouseEvent event) {
					statusBar.setText(String.format("���Ц�m:(%d, %d)(���U)",event.getX(),event.getY()));
					x1=event.getX();
					y1=event.getY();
					
				
					
				}
				
				public void mouseReleased(MouseEvent event) {
					statusBar.setText(String.format("���Ц�m:(%d, %d)(��})",event.getX(),event.getY()));
					x2=event.getX();
					y2=event.getY();
					drag=0;
					repaint();
					shapeCount++;
					
				}
				
				public void mouseEntered(MouseEvent event) {
					statusBar.setText(String.format("���Ц�m:(%d, %d)(�i�J�e��)",event.getX(), event.getY()));
				}
				
				public void mouseExited(MouseEvent event) {
					statusBar.setText(String.format("�������}�e���~"));
				}
				
				public void mouseDragged(MouseEvent event) {
					statusBar.setText(String.format("���Ц�m:(%d, %d)(�즲)", event.getX(), event.getY()));
					
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
					statusBar.setText(String.format("���Ц�m:(%d, %d)(����)", event.getX(), event.getY()));
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

	
	
	

