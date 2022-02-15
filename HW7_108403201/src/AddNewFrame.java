import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class AddNewFrame extends SwingWorker<List<ContactPerson>, List<ContactPerson>> {
	private final JLabel NameJLabel;
	private final JLabel TypeJLabel;
	private final JLabel PhoneJLabel;
	private final JTextField NameJTextField;
	private final JTextField PhoneJTextField;
	private final JComboBox<String> TypeJComboBox;
	private final String[] type={"home","company","cell"};
	private final JButton ConfirmJButton;
	private final JButton CancelJButton;
	private final JFrame addnewframe;
	private String model;
	private String name;
	private boolean flag=false;
	List<ContactPerson> returnlist;
	List<ContactPerson> updatelist;
	private final JList NameJList;
	 DefaultListModel dlm;
	 boolean buttonflag= true;
	public AddNewFrame(String model, JList NameJList) {
		addnewframe = new JFrame("新增/修改聯絡人");
		addnewframe.setSize(400,250);
		addnewframe.setLayout(new GridBagLayout());
		updatelist = new ArrayList();
		this.model = model;
		dlm = new DefaultListModel();
		NameJLabel = new JLabel("姓名");
		NameJTextField = new JTextField(20);
		PhoneJLabel = new JLabel("電話");
		PhoneJTextField = new JTextField(20);
		TypeJLabel = new JLabel("類型");
		TypeJComboBox = new JComboBox<String>(type);
		TypeJComboBox.setPreferredSize(new Dimension(225,23));
		ConfirmJButton = new JButton("完成");
		CancelJButton = new JButton("取消");
		this.NameJList = NameJList;
		if(model!="add") {
			updatelist = new ContactPersonQueries().QueryName(model);
			NameJTextField.setText(updatelist.get(0).getName());
			PhoneJTextField.setText(updatelist.get(0).getphoneNumber());
			TypeJComboBox.setSelectedItem(updatelist.get(0).getType());
			name=updatelist.get(0).getName();

		}
		ConfirmJButton.addActionListener(e->{
			try {

				if(model.equals("add")) {
					if(checknumber()) {
						new ContactPersonQueries().InsertNewPerson(String.valueOf(new ContactPersonQueries().getAllPeople().size()+1),NameJTextField.getText(),type[TypeJComboBox.getSelectedIndex()],PhoneJTextField.getText());
						
						buttonflag = false;
						addnewframe.dispose();
					}
				}else{
					if(checknumber()) {
						new ContactPersonQueries().UpdatePerson(NameJTextField.getText(),type[TypeJComboBox.getSelectedIndex()],PhoneJTextField.getText(),updatelist.get(0).getName());
						
						buttonflag = false;
						addnewframe.dispose();

					}
				}
			}catch(SQLException sqlException) {
				
			}
		
		});
		
		CancelJButton.addActionListener(e->{
			addnewframe.dispose();
		});
		addnewframe.add(NameJLabel);
		addnewframe.add(NameJTextField);
		addnewframe.add(PhoneJLabel);
		addnewframe.add(PhoneJTextField);
		addnewframe.add(TypeJLabel);
		addnewframe.add(TypeJComboBox);
		addnewframe.add(ConfirmJButton);
		addnewframe.add(CancelJButton);
		
		GridBagConstraints s = new GridBagConstraints();
		s.fill= GridBagConstraints.NONE;
		s.anchor= GridBagConstraints.SOUTH;
		s.gridwidth = 1;
		s.weightx = 1;
		s.weighty = 1;
		addnewframe.add(NameJLabel, s);
		s.fill= GridBagConstraints.NONE;
		s.anchor= GridBagConstraints.SOUTH;
		s.gridwidth = 0;
		s.weightx = 0;
		s.weighty = 1;
		addnewframe.add(NameJTextField, s);
		s.fill= GridBagConstraints.NONE;
		s.anchor= GridBagConstraints.SOUTH;
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 1;
		addnewframe.add(PhoneJLabel, s);
		s.fill= GridBagConstraints.NONE;
		s.anchor= GridBagConstraints.SOUTH;
		s.gridwidth = 0;
		s.weightx = 1;
		s.weighty = 1;
		addnewframe.add(PhoneJTextField, s);
		s.fill= GridBagConstraints.NONE;
		s.anchor= GridBagConstraints.CENTER;
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 1;
		addnewframe.add(TypeJLabel, s);
		s.fill= GridBagConstraints.NONE;
		s.anchor= GridBagConstraints.CENTER;
		s.gridwidth = 0;
		s.weightx = 1;
		s.weighty = 1;
		addnewframe.add(TypeJComboBox, s);
		s.fill= GridBagConstraints.NONE;
		s.anchor= GridBagConstraints.EAST;
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 1;
		addnewframe.add(ConfirmJButton, s);
		s.fill= GridBagConstraints.NONE;
		s.anchor= GridBagConstraints.CENTER;
		s.gridwidth = 0;
		s.weightx = 1;
		s.weighty = 1;
		addnewframe.add(CancelJButton, s);
		
		addnewframe.setVisible(true);
		
	}	
	private boolean checknumber() {
		boolean flag=true;
		String check = new String();
		int i =0,z=0;
		if(type[TypeJComboBox.getSelectedIndex()]=="cell") {
			try {
				for(z = 0; z< PhoneJTextField.getText().length(); z++) {
						i = Integer.parseInt(PhoneJTextField.getText());
						
						check = Integer.toString(i);
				}
			}catch(Exception ex) {
				flag=false;
				System.out.println("請輸入數字");
			}
			if(z!=10) {
				System.out.println("請輸入十位數");
				flag=false;
			}
			if(PhoneJTextField.getText().charAt(0)!=0) {
				System.out.println("第一個數字須為零");
				flag=false;
			}
			if(PhoneJTextField.getText().charAt(1)!=9) {
				System.out.println("第二個數字須為九");
				flag = false;
			}
		}
		if(type[TypeJComboBox.getSelectedIndex()]=="home") {
			try {
				for(z = 0; z<PhoneJTextField.getText().length(); z++) {
						i = Integer.parseInt(PhoneJTextField.getText());
						check = Integer.toString(i);
				}
			}catch(Exception ex) {
				flag=false;
				System.out.println("請輸入數字");
			}
			if(z>10||z<9) {
				System.out.println("請輸入九或十位數");
				flag=false;
			}
			if(PhoneJTextField.getText().charAt(0)==0) {
				
				System.out.println("第一個數字須為零");
				flag=false;
			}
		}
		if(type[TypeJComboBox.getSelectedIndex()]=="company") {
			try {
				for(z = 0; z<PhoneJTextField.getSelectedText().length(); z++) {
						i = Integer.parseInt(PhoneJTextField.getText());
						check = Integer.toString(i);
				}
			}catch(Exception ex) {
				flag=false;
				System.out.println("請輸入數字");
			}
			if(z>10||z<9) {
				System.out.println("請輸入八或九位數");
				flag=false;
			}
			if(PhoneJTextField.getText().charAt(0)!=0) {
				System.out.println("第一個數字須為零");
				flag = false;
			}
		}
		return flag;
	}
	
	protected List<ContactPerson> doInBackground() throws Exception{
		int i=0;
		while(buttonflag) {
			Thread.sleep(100);
			publish();
		}
		updatelist = new ContactPersonQueries().getAllPeople();
		updatelist.forEach(e->{dlm.addElement(e.getName());});
		return updatelist;
	}
	protected void done() {
		
				NameJList.setModel(dlm);
		
			
	
	}
	
	
	
}
