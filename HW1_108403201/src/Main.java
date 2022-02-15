//���W�� 108403201 ���2A
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
		private final String[] tool = {"����","���u","����","�x��","�ꨤ�x��"};
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
		
		private final JLabel fillJLabel;
		private final JPanel fillJPanel;
		private final JCheckBox  fillJCheckBox; //��checkbox�ܼƫŧi
		
		private final JButton colorJButton;
		private final JButton cleanJButton;
		private final JPanel buttonPanel;  //���s�ŧi
		
		private final JLabel statusBar;  //���A�C�ŧi

		private JFrame frame; //�����ŧi
		
		private final JPanel DrawingJPanel;
		private final JPanel situationJPanel;
		
		public Main() {
			
			
			frame = new JFrame("�p�e�a");
			frame.setLayout(new GridBagLayout());
			statusBar = new JLabel("���Ц�m");
			frame.setLocationRelativeTo(null);
			
			
			toolJLabel = new JLabel("ø�Ϥu��");
			toolJComboBox = new JComboBox<String>(tool);
			toolPanel = new JPanel();
			toolPanel.setLayout(new GridLayout(2,1));
			toolPanel.add(toolJLabel);
			toolPanel.add(toolJComboBox);  //�إ�ø�Ϥu��
			
			toolJComboBox.addItemListener(
					new ItemListener() {
						public void itemStateChanged(ItemEvent event) {
								String string1 = (String) event.getItem();
								if(event.getStateChange() == ItemEvent.SELECTED) {
									System.out.println("���"+string1);
								}
								
						}
					}
			);  //��X�ҿ蠟ø�Ϥu��
			
			GridBagConstraints location = new GridBagConstraints();
			location.gridx = 0;
			location.gridy =0;
			location.gridwidth =1;
			location.gridheight = 1;
			location.weightx =1;
			location.weighty =1;
			location.fill = GridBagConstraints.NONE;
			location.anchor = GridBagConstraints.WEST; //�]�wø�Ϥu���m  
			frame.add(toolPanel, location); //�[�J����
			
			
			sizeJLabel = new JLabel("����j�p");
			bigJRadioButton = new JRadioButton("�j", true);
			mediumJRadioButton = new JRadioButton("��", false);
			smallJRadioButton = new JRadioButton("�p", false);
			
			
			sizePanel = new JPanel();
			sizePanel.setLayout(new BorderLayout());
			sizePanel.add(sizeJLabel,BorderLayout.NORTH);
			sizePanel.add(smallJRadioButton,BorderLayout.WEST);
			sizePanel.add(mediumJRadioButton,BorderLayout.CENTER);
			sizePanel.add(bigJRadioButton,BorderLayout.EAST);
			radioGroup = new ButtonGroup();
			radioGroup.add(bigJRadioButton);
			radioGroup.add(mediumJRadioButton);
			radioGroup.add(smallJRadioButton); //�إߵ���j�p
			
			small = new String("�p");
			medium = new String("��");
			big = new String("�j");
			smallJRadioButton.addItemListener(new RadioButtonHandler(small));
			mediumJRadioButton.addItemListener(new RadioButtonHandler(medium));
			bigJRadioButton.addItemListener(new RadioButtonHandler(big));
			GridBagConstraints location1 = new GridBagConstraints(); //��X�ҿ蠟����j�p
			location1.gridx = 1;
			location1.gridy =0;
			location1.gridwidth =1;
			location1.gridheight = 1;
			location1.weightx =1;
			location1.weighty =1;
			location1.fill = GridBagConstraints.NONE;
			location1.anchor = GridBagConstraints.WEST; //�]�w����j�p��m
			frame.add(sizePanel, location1); //�[�J������
			
			
			fillJPanel = new JPanel();
			fillJLabel = new JLabel("��");
			fillJPanel.setLayout(new GridLayout(2,1));
			fillJCheckBox = new JCheckBox();
			fillJPanel.add(fillJLabel);
			fillJPanel.add(fillJCheckBox); //�إ߶�checkbox
			
			CheckBoxHandler handler1 = new CheckBoxHandler();
			fillJCheckBox.addItemListener(handler1);
			GridBagConstraints location2 = new GridBagConstraints(); //�L�X�O�_��
			location2.gridx = 2;
			location2.gridy =0;
			location2.gridwidth =1;
			location2.gridheight = 1;
			location2.weightx =1;
			location2.weighty =1;
			location2.fill = GridBagConstraints.NONE;
			location2.anchor = GridBagConstraints.WEST;
			frame.add(fillJPanel, location2); //�N��checkbox�[�J������
			
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(1,2));
			colorJButton = new JButton("�����C��");
			cleanJButton = new JButton("�M���e��");			
			buttonPanel.add(colorJButton);
			buttonPanel.add(cleanJButton); //�إߵ����C��P�M���e��Button
			GridBagConstraints location3 = new GridBagConstraints(); 
			location3.gridx = 4;
			location3.gridy =0;
			location3.gridwidth =1;
			location3.gridheight = 1;
			location3.weightx =1;
			location3.weighty =1;
			location3.fill = GridBagConstraints.NONE;
			location3.anchor = GridBagConstraints.CENTER;
			frame.add(buttonPanel, location3); //�[�J������
			ButtonHandler handler = new ButtonHandler();
			colorJButton.addActionListener(handler);
			cleanJButton.addActionListener(handler); //�C�L�ҿ�
			
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
			frame.add(situationJPanel, location4);  //�N���A�C�[�J����
			
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
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�����ݩ�
			
		}		
	private class MouseHandler extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent event) {
			int xPos = event.getX();
			int yPos = event.getY();
			String details = String.format("���Ц�m: (%d, %d)",xPos, yPos);
			statusBar.setText(details); //�^�����Ц�m
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
			}
		}	//�L�X����j�p
	
	private class CheckBoxHandler implements ItemListener{
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange()==ItemEvent.SELECTED)
					System.out.println("��ܶ�");
				else
					System.out.println("������");
			}	
		} //�L�X�O�_��
		
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {				
			System.out.println("�I��"+event.getActionCommand());
		}
	}  //�L�X�I�ﶵ��
				
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null,"Wellcome"); //�ﻫ�T��
		Main drawingapplication = new Main();
	}	
}

