package GUI;

import java.awt.Frame;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.TextArea;

public class Version extends Frame {
	MyFrame frame2 = new MyFrame();
	
	public Version()
	{
		frame2.setTitle("버젼정보");
		frame2.setSize(200, 166);
		frame2.setLocation(550, 350);
		frame2.setDefaultCloseOperation(frame2.DISPOSE_ON_CLOSE);
		frame2.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("제작자 : 1조");
		lblNewLabel.setBounds(12, 10, 160, 27);
		frame2.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("최재원, 고명수, 박성민");
		label.setBounds(12, 38, 160, 27);
		frame2.getContentPane().add(label);
		
		JLabel label_1 = new JLabel( "프로그램 버젼 : v1.0");
		label_1.setBounds(12, 66, 160, 27);
		frame2.getContentPane().add(label_1);
		
		JLabel lblNewLabel_1 = new JLabel("디자인 협찬 : 임보연");
		lblNewLabel_1.setBounds(12, 103, 145, 15);
		frame2.getContentPane().add(lblNewLabel_1);
		frame2.setVisible(true);
	}
}
