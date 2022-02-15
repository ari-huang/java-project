import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TimeZone;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.crypto.Data;

public class ContactBookJFrame extends JFrame{
	private JLabel contactbook,weather, editTime, content;
	private JPanel MainJPanel;
	private JPanel SituationJPanel;

	private Color MainColor;
	private Color SituationColor;
	private JButton EditJButton;
	private JButton NewJButton;
	private JButton SaveJButton;
	private JButton AnotherJButton;
	private JButton ImportJButton;
	private JButton CancelJButton;
	private JLabel imageJLabel;
	private JTextArea jTextArea ;
	private JLabel TextJLabel;
	private JLabel WeatherJLabel;
	private JLabel TimeJLabel;
	private boolean lovesituation;
	private final JLabel boldJLabel;
	private final JLabel italicJLabel;
	private final JLabel textsizeJLabel;
	private final JCheckBox boldJCheckBox;
	private final JCheckBox italicJCheckBox;
	private final JPanel checkboxJPanel;
	private final JPanel detailJPanel;
	
	private final JComboBox<String> WeatherJComboBox;
	private final JComboBox<String> textsizeJComboBox;
	private int textsize;
	private Color textcolor;
	private static final String[] number= {"25","26","27","28","29","30","31","32","33","34","35"};
	private static final String[] names = {"晴天","陰天","雨天"};
	private JButton textcolorJButton;
	private int weatherint;
	private final Icon[] icons = {
			new ImageIcon(getClass().getResource("sunny.png")),
			new ImageIcon(getClass().getResource("cloudy.png")),
			new ImageIcon(getClass().getResource("rainy.png"))
	};
	public ContactBookJFrame(Boolean editable){
		super("我的聯絡簿");
		MainColor = new Color(0,100,0);
		SituationColor = new Color(160,82,45);
		setLayout(new BorderLayout());
		MainJPanel = new JPanel();
		MainJPanel.setLayout(new GridLayout(1,5));
		detailJPanel = new JPanel();
		detailJPanel.setLayout(new GridLayout(5,1));
		detailJPanel.setBackground(MainColor);
		SituationJPanel = new JPanel();
		SituationJPanel.setLayout(new GridLayout(1,5,20,20));
		MainJPanel.setBackground(MainColor);
		SituationJPanel.setBackground(SituationColor);
		contactbook = new JLabel("聯絡簿");
		contactbook.setFont(new Font("Dialog", 0, 30));
		contactbook.setForeground(Color.WHITE);
		detailJPanel.add(contactbook);
		add(MainJPanel,BorderLayout.NORTH);
		add(SituationJPanel,BorderLayout.SOUTH);
		EditJButton = new JButton("編輯");
		NewJButton = new JButton("全新貼文");
		SaveJButton = new JButton("儲存");
		AnotherJButton = new JButton("另存新檔");
		ImportJButton = new JButton("匯入內容");
		CancelJButton = new JButton("取消");
		
		ImageIcon image = new ImageIcon(getClass().getResource("unlike.png"));
		image.setImage(image.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		imageJLabel=new JLabel(image);
		SituationJPanel.add(imageJLabel);
		SituationJPanel.add(SaveJButton);
		SituationJPanel.add(AnotherJButton);
		SituationJPanel.add(ImportJButton);
		SituationJPanel.add(CancelJButton);
		SaveJButton.setVisible(false);
		AnotherJButton.setVisible(false);
		ImportJButton.setVisible(false);
		CancelJButton.setVisible(false);
		
		ButtonHandler handler = new ButtonHandler();
		EditJButton.addActionListener(handler);
		NewJButton.addActionListener(handler);
		SaveJButton.addActionListener(handler);
		AnotherJButton.addActionListener(handler);
		ImportJButton.addActionListener(handler);
		CancelJButton.addActionListener(handler);
		
		
		SituationJPanel.add(EditJButton);
		SituationJPanel.add(NewJButton);
		if(editable==false) {
		SituationJPanel.addMouseListener(new MouseAdapter() {
			int number=0;
			public void mouseClicked(MouseEvent e) {
				number++;
				if(number%2 ==1) {
					ImageIcon image = new ImageIcon(getClass().getResource("like.png"));
					image.setImage(image.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
					imageJLabel.setIcon(image);
					lovesituation = true;
					
				}else  {
					ImageIcon image1 = new ImageIcon(getClass().getResource("unlike.png"));
					image1.setImage(image1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
					imageJLabel.setIcon(image1);
					lovesituation = false;
				}
				
			}
		});
		}
		if(editable==false) {
			EditJButton.setVisible(false);
			NewJButton.setVisible(false);
		} 
		boldJCheckBox = new JCheckBox("Bold");
		italicJCheckBox = new JCheckBox("Italic");
		boldJLabel = new JLabel("粗體");
		Border blackline = BorderFactory.createLineBorder(Color.BLACK,5);
		Border titleborder = BorderFactory.createTitledBorder(blackline,"字體型態",0,0,null,Color.RED);
		italicJLabel = new JLabel("斜體");
		textsizeJLabel = new JLabel(" | 字體大小");
		textsizeJLabel.setVisible(false);
		boldJLabel.setVisible(false);
		italicJLabel.setVisible(false);
		checkboxJPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
		checkboxJPanel.setVisible(false);
		checkboxJPanel.add(boldJLabel);
		checkboxJPanel.add(boldJCheckBox);
		checkboxJPanel.add(italicJLabel);
		checkboxJPanel.add(italicJCheckBox);
		checkboxJPanel.add(textsizeJLabel);
		checkboxJPanel.setBackground(Color.WHITE);
		checkboxJPanel.setBorder(titleborder);
		TextJLabel = new JLabel();
		TextJLabel.setBackground(MainColor);
		weatherint = 0;
		Icon sunny = new ImageIcon(getClass().getResource("sunny.png"));
		WeatherJLabel= new JLabel("天氣",icons[weatherint],SwingConstants.LEFT);
		WeatherJLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		WeatherJLabel.setForeground(Color.WHITE);
		WeatherJLabel.setFont(new java.awt.Font("Serif",0,20));
	    TimeJLabel = new JLabel();
		TimeJLabel.setForeground(Color.WHITE);
		TimeJLabel.setFont(new java.awt.Font("Serif",0, 20));
	    detailJPanel.add(WeatherJLabel);
	    WeatherJComboBox = new JComboBox<String>(names);
	    WeatherJComboBox.setPreferredSize(new Dimension(1,25));
	    weatherint =0;
	    WeatherJComboBox.addItemListener(e->{
	    	if(e.getStateChange()==ItemEvent.SELECTED);
	    	 	WeatherJLabel.setIcon(icons[WeatherJComboBox.getSelectedIndex()]);
	    	 	weatherint = WeatherJComboBox.getSelectedIndex();
	    });
	    textsizeJComboBox = new JComboBox<String>(number);
	    checkboxJPanel.add(textsizeJComboBox);
	    textsize=30;
	    textcolorJButton= new JButton("字體顏色");
	    textcolorJButton.addActionListener(e->{
	    	textcolor = JColorChooser.showDialog(ContactBookJFrame.this, "Choose a color", textcolor);
	    	jTextArea.setForeground(textcolor);
	    });
	    checkboxJPanel.add(textcolorJButton);
	    checkboxJPanel.setVisible(false);
	    textsizeJComboBox.setVisible(false);
	    detailJPanel.add(WeatherJComboBox);
	    WeatherJComboBox.setVisible(false);
		detailJPanel.add(TimeJLabel);
		detailJPanel.add(checkboxJPanel);
		Font x = new Font("Serif",0,textsize);
		jTextArea = new JTextArea();
		jTextArea.setFont(x);
		jTextArea.setForeground(Color.YELLOW);
		jTextArea.setBackground(MainColor);
		CheckBoxHandler handler3 = new CheckBoxHandler();
		boldJCheckBox.addItemListener(handler3);
		italicJCheckBox.addItemListener(handler3);
		textsizeJComboBox.addItemListener(handler3);
		jTextArea.setLineWrap(true);
		add(jTextArea,BorderLayout.CENTER);
		MainJPanel.add(detailJPanel);
		
		new ReadSequentialFile();
		
		
		;
	}
	private class CheckBoxHandler implements ItemListener{
		public void itemStateChanged(ItemEvent event) {
			Font font = null;
			if(event.getStateChange()==ItemEvent.SELECTED) {
				textsize=(textsizeJComboBox.getSelectedIndex())+24;
			}
			if(boldJCheckBox.isSelected()&& italicJCheckBox.isSelected())
				font = new Font("Serif", Font.BOLD + Font.ITALIC, textsize);
			else if(boldJCheckBox.isSelected())
				font = new Font("Serif", Font.BOLD, textsize);
			else if(italicJCheckBox.isSelected())
				font = new Font("Serif",Font.ITALIC, textsize);
			else
				font = new Font("Serif", Font.PLAIN, textsize);
			jTextArea.setFont(font);
		}
	}
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource()==EditJButton) {
				imageJLabel.setVisible(false);
				EditJButton.setVisible(false);
				NewJButton.setVisible(false);
				SaveJButton.setVisible(true);
				checkboxJPanel.setVisible(true);
				AnotherJButton.setVisible(true);
				ImportJButton.setVisible(true);
				CancelJButton.setVisible(true);
				WeatherJLabel.setVisible(false);
				WeatherJComboBox.setVisible(true);
				italicJLabel.setVisible(true);
				textsizeJLabel.setVisible(true);
				boldJLabel.setVisible(true);
				boldJCheckBox.setVisible(true);
				italicJCheckBox.setVisible(true);
				textcolorJButton.setVisible(true);
				textsizeJComboBox.setVisible(true);
				jTextArea.setBackground(Color.WHITE);
				jTextArea.setForeground(Color.BLACK);
				jTextArea.setEditable(true);
			}
			if(event.getSource()==NewJButton) {
				imageJLabel.setVisible(false);
				checkboxJPanel.setVisible(true);
				EditJButton.setVisible(false);
				NewJButton.setVisible(false);
				SaveJButton.setVisible(true);
				AnotherJButton.setVisible(true);
				ImportJButton.setVisible(true);
				WeatherJLabel.setVisible(false);
				WeatherJComboBox.setVisible(true);
				italicJLabel.setVisible(true);
				boldJLabel.setVisible(true);
				CancelJButton.setVisible(true);
				textsizeJLabel.setVisible(true);
				textsizeJComboBox.setVisible(true);
				textcolorJButton.setVisible(true);
				jTextArea.setEditable(true);
				jTextArea.setBackground(Color.WHITE);
				jTextArea.setForeground(Color.BLACK);
				jTextArea.setText("");
			}
			if(event.getSource()==CancelJButton) {
				
				imageJLabel.setVisible(true);
				EditJButton.setVisible(true);
				NewJButton.setVisible(true);
				WeatherJLabel.setVisible(true);
				WeatherJComboBox.setVisible(false);
				SaveJButton.setVisible(false);
				AnotherJButton.setVisible(false);
				ImportJButton.setVisible(false);
				checkboxJPanel.setVisible(true);
				CancelJButton.setVisible(false);
				italicJLabel.setVisible(false);
				boldJLabel.setVisible(false);
				italicJCheckBox.setVisible(false);
				boldJCheckBox.setVisible(false);
				checkboxJPanel.setVisible(false);
				textcolorJButton.setVisible(false);
				textsizeJComboBox.setVisible(false);
				textsizeJLabel.setVisible(false);
				jTextArea.setForeground(Color.YELLOW);
				jTextArea.setBackground(MainColor);
				new ReadSequentialFile();
				
			}
			if(event.getSource()==ImportJButton) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT FILE","txt");
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					try {
						jTextArea.setText("");
						Scanner input = new Scanner(fileChooser.getSelectedFile());						
						while(input.hasNext()) {
							jTextArea.append(input.nextLine());
						}
						input.close();
					}catch(FileNotFoundException e) {
						JOptionPane.showMessageDialog(null,"File not found.");
					}
				}
			}
			if(event.getSource()==AnotherJButton) {
				JFileChooser fileChooser1 = new JFileChooser();
				FileNameExtensionFilter filter1= new FileNameExtensionFilter("TXT FILE","txt");
				fileChooser1.setFileFilter(filter1);
				int result1 = fileChooser1.showSaveDialog(null);
				if(result1 == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser1.getSelectedFile();
				}
			}
			if(event.getSource()==SaveJButton) {
				 new CreateSequentialFile();
				 imageJLabel.setVisible(true);
				 EditJButton.setVisible(true);
				 NewJButton.setVisible(true);
				 WeatherJLabel.setVisible(true);
				 WeatherJComboBox.setVisible(false);
				 SaveJButton.setVisible(false);
				 AnotherJButton.setVisible(false);
				 ImportJButton.setVisible(false);
				 CancelJButton.setVisible(false);
				 jTextArea.setEditable(false);
				 jTextArea.setForeground(Color.YELLOW);
				 checkboxJPanel.setVisible(false);
				 textsizeJComboBox.setVisible(false);
				 textcolorJButton.setVisible(false);
				 jTextArea.setBackground(MainColor);
				new ReadSequentialFile();
			}
			
		}
	}
	 
	private class ReadSequentialFile {
		private  ObjectInputStream input;
		public ReadSequentialFile() {
			openFile();
			readRecords();
			closeFile();
		
		}
		public  void openFile() {
			try {
				 input = new ObjectInputStream(Files.newInputStream(Paths.get("src/post")));
				
			}
			catch(IOException ioException) {
				System.err.println("Error opening file.");
				
			}
		}
		
		public  void readRecords() {
			try {
				while(true) {
				PostSerializable record = (PostSerializable) input.readObject();
					jTextArea.setText(record.getContent());
					jTextArea.setEditable(false);
					TimeJLabel.setText((String.valueOf(record.getEditTime())));
					WeatherJLabel.setIcon(icons[record.getWeather()]);
				}
			}
			catch(EOFException endOfFileException) {
				
			}
			catch (ClassNotFoundException classNotFouundException) {
				System.err.println("Invalid object type. Terminating.");
			}
			catch (IOException ioException) {
				System.err.println("Error reading from file. Terminating.");
			}
		}
		public  void closeFile() {
			try {
				if(input != null)
					input.close();
			}
			catch (IOException inException) {
				System.err.println("Error closing File.class Terminating.");
				System.exit(1);
			}
		}
	}
	private class CreateSequentialFile{
		private  ObjectOutputStream output;
		public CreateSequentialFile()  {
			openFile();
			addRecords();
			closeFile();
		}
		public void openFile() {
			try {
				output = new ObjectOutputStream(Files.newOutputStream(Paths.get("src/post")));
			}catch(IOException inException) {
				System.err.println("Error opening file. Terminating");
				System.exit(1);
			}
		}
		public void addRecords()  {
			Scanner input = new Scanner(System.in);
			String content = jTextArea.getText();
			SimpleDateFormat nowdate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date current = new Date();
			
				try {
					PostSerializable record = new PostSerializable(content,lovesituation,current);
					output.writeObject(record);
				}catch(NoSuchElementException elementException) {
					System.err.println("Invalid input. Please try again.");
					input.hasNextLine();
				}catch(IOException ioException) {
					System.err.println("Err writing to file. Terminating");
		
				}
			}
	
			
		
			
		public void closeFile() {
			try {
					output.close();
			}catch(IOException ioException) {
				System.err.println("Error closing file. Terminating");
			}
		}
	
	}
	private class LeftArrowBubble extends JPanel{
		private int strokeThickness =5;
		private int padding = strokeThickness/2;
		private int arrowSize= 6;
		private int radius = 10;
		private Color bubblecolor;
		public LeftArrowBubble() {
			bubblecolor= new Color(255,199,143);
		}
		public void setBubbleColor(Color color) {
			this.bubblecolor = color;
		}
		public Color getBubbleColor() {
			return bubblecolor;
		}
		protected void paintComponent(final Graphics g) {
			final Graphics2D graphics2D = (Graphics2D) g;
			RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        graphics2D.setRenderingHints(qualityHints);
	        
	        graphics2D.setColor(getBubbleColor());
	        graphics2D.setStroke(new BasicStroke(strokeThickness));
	        
	     
	        int x = padding + strokeThickness + arrowSize;
	        int width = getWidth() - arrowSize - (strokeThickness * 2);
	        int height = getHeight() - strokeThickness;
	        graphics2D.fillRect(x, padding, width, height);
	        RoundRectangle2D.Double rect = new RoundRectangle2D.Double(x, padding, width, height, radius, radius);
	        
	        Polygon arrow = new Polygon();
	        arrow.addPoint(14, 6);
	        arrow.addPoint(arrowSize + 2, 10);
	        arrow.addPoint(14, 12);
	        Area area = new Area(rect);
	        area.add(new Area(arrow));
	        graphics2D.draw(area);
		}
	}
	
}


