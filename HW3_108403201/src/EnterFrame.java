import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EnterFrame {
	public EnterFrame() {
	Icon  enterImage = new ImageIcon(getClass().getResource("登入圖案.jpg")); 
	Object[] options = {"Cancel","NO","YES"};
	int opt=JOptionPane.showOptionDialog(null,"是否為發布者","登入",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, enterImage , options, options[0]);
		 if (opt == 1) {
			 ContactBookJFrame contactbookjframe = new ContactBookJFrame(false);
			 contactbookjframe.setSize(950,700);
			 contactbookjframe.setVisible(true);
			 contactbookjframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else if(opt ==2) {
			 ContactBookJFrame contactbookjframe = new ContactBookJFrame(true);
			 contactbookjframe.setSize(950,700);
			 contactbookjframe.setVisible(true);
			 contactbookjframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}
