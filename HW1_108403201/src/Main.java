//黃名揚 108403201 資管2A
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.io.Console;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.io.Console;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Main {
		
		
		
		private final JLabel toolJLabel;
		private final JComboBox<String> toolJComboBox;
		private final String[] tool = {"筆刷","直線","橢圓形","矩形","圓角矩形"};
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
		
		private final JLabel fillJLabel;
		private final JPanel fillJPanel;
		private final JCheckBox  fillJCheckBox; //填滿checkbox變數宣告
		
		private final JButton colorJButton;
		private final JButton cleanJButton;
		private final JPanel buttonPanel;  //按鈕宣告
		
		private final JLabel statusBar;  //狀態列宣告

		private JFrame frame; //視窗宣告
		
		private final JPanel DrawingJPanel;
		private final JPanel situationJPanel;
		
		public Main() {
			
			
			frame = new JFrame("小畫家");
			frame.setLayout(new GridBagLayout());
			statusBar = new JLabel("鼠標位置");
			frame.setLocationRelativeTo(null);
			
			
			toolJLabel = new JLabel("繪圖工具");
			toolJComboBox = new JComboBox<String>(tool);
			toolPanel = new JPanel();
			toolPanel.setLayout(new GridLayout(2,1));
			toolPanel.add(toolJLabel);
			toolPanel.add(toolJComboBox);  //建立繪圖工具
			
			toolJComboBox.addItemListener(
					new ItemListener() {
						public void itemStateChanged(ItemEvent event) {
								String string1 = (String) event.getItem();
								if(event.getStateChange() == ItemEvent.SELECTED) {
									System.out.println("選擇"+string1);
								}
								
						}
					}
			);  //輸出所選之繪圖工具
			
			GridBagConstraints location = new GridBagConstraints();
			location.gridx = 0;
			location.gridy =0;
			location.gridwidth =1;
			location.gridheight = 1;
			location.weightx =1;
			location.weighty =1;
			location.fill = GridBagConstraints.NONE;
			location.anchor = GridBagConstraints.WEST; //設定繪圖工具位置  
			frame.add(toolPanel, location); //加入視窗
			
			
			sizeJLabel = new JLabel("筆刷大小");
			bigJRadioButton = new JRadioButton("大", true);
			mediumJRadioButton = new JRadioButton("中", false);
			smallJRadioButton = new JRadioButton("小", false);
			
			
			sizePanel = new JPanel();
			sizePanel.setLayout(new BorderLayout());
			sizePanel.add(sizeJLabel,BorderLayout.NORTH);
			sizePanel.add(smallJRadioButton,BorderLayout.WEST);
			sizePanel.add(mediumJRadioButton,BorderLayout.CENTER);
			sizePanel.add(bigJRadioButton,BorderLayout.EAST);
			radioGroup = new ButtonGroup();
			radioGroup.add(bigJRadioButton);
			radioGroup.add(mediumJRadioButton);
			radioGroup.add(smallJRadioButton); //建立筆刷大小
			
			small = new String("小");
			medium = new String("中");
			big = new String("大");
			smallJRadioButton.addItemListener(new RadioButtonHandler(small));
			mediumJRadioButton.addItemListener(new RadioButtonHandler(medium));
			bigJRadioButton.addItemListener(new RadioButtonHandler(big));
			GridBagConstraints location1 = new GridBagConstraints(); //輸出所選之筆刷大小
			location1.gridx = 1;
			location1.gridy =0;
			location1.gridwidth =1;
			location1.gridheight = 1;
			location1.weightx =1;
			location1.weighty =1;
			location1.fill = GridBagConstraints.NONE;
			location1.anchor = GridBagConstraints.WEST; //設定筆刷大小位置
			frame.add(sizePanel, location1); //加入視窗中
			
			
			fillJPanel = new JPanel();
			fillJLabel = new JLabel("填滿");
			fillJPanel.setLayout(new GridLayout(2,1));
			fillJCheckBox = new JCheckBox();
			fillJPanel.add(fillJLabel);
			fillJPanel.add(fillJCheckBox); //建立填滿checkbox
			
			CheckBoxHandler handler1 = new CheckBoxHandler();
			fillJCheckBox.addItemListener(handler1);
			GridBagConstraints location2 = new GridBagConstraints(); //印出是否填滿
			location2.gridx = 2;
			location2.gridy =0;
			location2.gridwidth =1;
			location2.gridheight = 1;
			location2.weightx =1;
			location2.weighty =1;
			location2.fill = GridBagConstraints.NONE;
			location2.anchor = GridBagConstraints.WEST;
			frame.add(fillJPanel, location2); //將填滿checkbox加入視窗中
			
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(1,2));
			colorJButton = new JButton("筆刷顏色");
			cleanJButton = new JButton("清除畫面");			
			buttonPanel.add(colorJButton);
			buttonPanel.add(cleanJButton); //建立筆刷顏色與清除畫面Button
			GridBagConstraints location3 = new GridBagConstraints(); 
			location3.gridx = 4;
			location3.gridy =0;
			location3.gridwidth =1;
			location3.gridheight = 1;
			location3.weightx =1;
			location3.weighty =1;
			location3.fill = GridBagConstraints.NONE;
			location3.anchor = GridBagConstraints.CENTER;
			frame.add(buttonPanel, location3); //加入視窗當中
			ButtonHandler handler = new ButtonHandler();
			colorJButton.addActionListener(handler);
			cleanJButton.addActionListener(handler); //列印所選
			
			situationJPanel= new JPanel();
			situationJPanel.setLayout(new BorderLayout());
			situationJPanel.setBackground(Color.BLACK);
			situationJPanel.add(statusBar,BorderLayout.WEST);
			GridBagConstraints location4 = new GridBagConstraints();
			location4.gridx = 0;
			location4.gridy =8;
			location4.gridwidth =8;
			location4.gridheight = 5;
			location4.weightx =0;
			location4.weighty =0;
			location4.fill = GridBagConstraints.HORIZONTAL;
			location4.anchor = GridBagConstraints.SOUTHWEST;
			frame.add(situationJPanel, location4);  //將狀態列加入視窗
			
			DrawingJPanel = new JPanel();
			DrawingJPanel.setBackground(Color.WHITE);
			DrawingJPanel.addMouseMotionListener(new MouseHandler());
			GridBagConstraints location5 = new GridBagConstraints();
			location5.gridx=0;
			location5.gridy = 2;
			location5.gridwidth=5;
			location5.gridheight=8;
			location5.weightx=1;
			location5.weighty =1;
			location5.fill =GridBagConstraints.BOTH;
			location5.anchor = GridBagConstraints.WEST;
			frame.add(DrawingJPanel,location5);
				
			
			frame.setSize(495,280);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //視窗屬性
			
		}		
	private class MouseHandler extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent event) {
			int xPos = event.getX();
			int yPos = event.getY();
			String details = String.format("鼠標位置: (%d, %d)",xPos, yPos);
			statusBar.setText(details); //擷取鼠標位置
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
			}
		}	//印出筆刷大小
	
	private class CheckBoxHandler implements ItemListener{
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange()==ItemEvent.SELECTED)
					System.out.println("選擇填滿");
				else
					System.out.println("取消填滿");
			}	
		} //印出是否填滿
		
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {				
			System.out.println("點選"+event.getActionCommand());
		}
	}  //印出點選項目
				
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null,"Wellcome"); //迎賓訊息
		Main drawingapplication = new Main();
	}	
}

