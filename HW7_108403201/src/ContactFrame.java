import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.Border;

public class ContactFrame extends JFrame {
	private final JLabel Contacts;
	private final JTextField FindJTextField;
	private  JList<String> NameJList;
	private final JButton FindJButton;
	private final JButton AddJButton;
	private final String[] Names= {};
	 List<ContactPerson> contactlist;
     ExecutorService executorService = Executors.newCachedThreadPool();

	 DefaultListModel dlm;
	public ContactFrame() {
		super("通訊錄");
		setSize(500,700);
		setDefaultCloseOperation(3);
		DefaultListModel dlm = new DefaultListModel();
		setLayout(new GridBagLayout());
		Contacts = new JLabel("Contacts");
		Contacts.setFont(new Font("Arial",Font.PLAIN,40));
		FindJTextField = new JTextField(20);
		FindJTextField.setPreferredSize(new Dimension(200,30));
		NameJList = new JList<String>(Names);
		NameJList.setFixedCellWidth(100);
		NameJList.setFixedCellHeight(50);
		NameJList.setVisibleRowCount(10);
		NameJList.setCellRenderer(new DefaultListCellRenderer() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawLine(0,getHeight()-1,getWidth(),getHeight()-1);
			}
		});
		
		NameJList.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(NameJList.getSelectedIndex()!=-1) {
					if(e.getClickCount()==2) {
						String[] options = {"Update","Delete"};
						int n=JOptionPane.showOptionDialog(null,contactlist.get(NameJList.getSelectedIndex()).getType()+": " +String.valueOf(contactlist.get(NameJList.getSelectedIndex()).getphoneNumber()),"資訊",1,1,null,options,"Update");
						if(n==0) {
							AddNewFrame addnewframe=new AddNewFrame(NameJList.getSelectedValue(),NameJList);
							addnewframe.execute();
						
						}else if(n==1) {
							try {
								new ContactPersonQueries().DeletePerson(NameJList.getSelectedValue());
								dlm.remove(NameJList.getSelectedIndex());
								NameJList.setModel(dlm);
								
							}catch(SQLException sqlException) {
								
							}
						}
						
					}
				}
			}
		});
		contactlist = new ArrayList<>();
		contactlist = (new ContactPersonQueries().getAllPeople());

		contactlist.forEach(e->{dlm.addElement(e.getName());});
		NameJList.setModel(dlm);
		FindJButton = new JButton("Search");
		FindJButton.addActionListener(e->{
			if(FindJTextField.getText().equals("")) {
				dlm.clear();
				contactlist = new ContactPersonQueries().getAllPeople();			
				contactlist.forEach(v->{dlm.addElement(v.getName());});
				NameJList.setModel(dlm);
			}else {
				dlm.clear();
				contactlist = new ContactPersonQueries().QueryName(FindJTextField.getText());
				contactlist.forEach(v->{dlm.addElement(v.getName());});
				
				NameJList.setModel(dlm);
			}
		});
		AddJButton = new JButton("+");
		AddJButton.setFont(new Font("Arial",Font.PLAIN,40));
		AddJButton.setPreferredSize(new Dimension(60,60));
		AddJButton.setOpaque(false);
		AddJButton.setBorder(null);
		AddJButton.setContentAreaFilled(false);
		AddJButton.setForeground(new Color(119,136,153));
		AddJButton.addActionListener(new ButtonHandler());
		add(Contacts);
		add(AddJButton);
		add(FindJTextField);
		add(FindJButton);
		add(NameJList);
		GridBagConstraints s = new GridBagConstraints();
		s.fill = GridBagConstraints.NONE;
		s.anchor = GridBagConstraints.NORTHWEST;
		s.gridwidth=1;
		s.weightx = 0;
		s.weighty = 0;
		add(Contacts, s);
		s.fill = GridBagConstraints.NONE;
		s.anchor = GridBagConstraints.NORTHEAST;
		s.gridwidth=0;
		s.weightx = 1;
		s.weighty = 0;
		add(AddJButton,s);
		s.fill = GridBagConstraints.HORIZONTAL;
		s.anchor = GridBagConstraints.NORTHWEST;
		s.gridwidth=1;
		s.weightx = 3;
		s.weighty = 1;
		add(FindJTextField,s);
		s.fill = GridBagConstraints.NONE;
		s.anchor = GridBagConstraints.NORTHEAST;
		s.gridwidth=0;
		s.weightx = 1;
		s.weighty = 1;
		add(FindJButton,s);
		s.fill = GridBagConstraints.BOTH;
		s.anchor = GridBagConstraints.CENTER;
		s.gridwidth=0;
		s.weightx = 0;
		s.weighty=10;
		add(new JScrollPane(NameJList),s);
		setVisible(true);
		
	}
	private  class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			AddNewFrame addnewframe= new AddNewFrame("add", NameJList);
			addnewframe.execute();
			
		}
	}
	
		
		
	

	
}
