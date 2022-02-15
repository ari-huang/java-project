

//黃名揚 108403201 資管2A

/*
 加分功能: 1.增加draw3DRect三維矩形功能與fill3DRect                                           
		2.加入JMenuBar、JMenu和兩項JMenuItem                                                  
		3.在JMenuBar設置熱鍵功能																  *
		4.增加變換背景顏色的JButton																
		5.增加漸層顏色使筆刷顏色可以漸層的JButton
		6.在JMenu的檔案中增加開啟檔案功能的JMenuItem,並用FileNameExtensionFilter過濾檔案類型
		7.在JMenu的檔案中增加儲存檔案功能的JMenuItem
		8.在狀態列加入滑鼠狀態
*/

import javax.swing.JFrame;

import javax.swing.JOptionPane;



public class Main extends JFrame{
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null,"Wellcome");
		DrawingTool drawingapplication = new DrawingTool();
		
		
	}
}
