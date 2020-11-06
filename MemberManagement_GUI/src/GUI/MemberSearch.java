package GUI;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

public class MemberSearch extends MemberManagements implements ActionListener {
	MyFrame frame5 = new MyFrame();
	JButton sCode = new JButton("회원 코드");
	JButton sName = new JButton("회원 이름");
	JButton sPhone = new JButton("전화 번호");
	JButton sAll = new JButton("전체 회원");
	JButton sCancel = new JButton("취소");
	JTextArea textArea = new JTextArea();
	public MemberSearch(){
		frame5.setTitle("회원검색");
		frame5.setSize(477, 293);
		frame5.setLocation(550, 350);
		frame5.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	
		JLabel lblNewLabel = new JLabel("검색");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		lblNewLabel.setBounds(354, 3, 43, 32);
		frame5.getContentPane().add(lblNewLabel);
		
		sCode.setBounds(354, 40, 95, 32);
		frame5.getContentPane().add(sCode);
		
		sName.setBounds(354, 82, 95, 32);
		frame5.getContentPane().add(sName);
		
		sPhone.setBounds(354, 124, 95, 32);
		frame5.getContentPane().add(sPhone);
		
		sAll.setBounds(354, 166, 95, 32);
		frame5.getContentPane().add(sAll);
		
		sCancel.setBounds(354, 208, 95, 32);
		frame5.getContentPane().add(sCancel);
		
		textArea.setEditable(false);
		textArea.setBounds(12, 9, 331, 234);
		frame5.getContentPane().add(textArea);
		
		JList list_1 = new JList();
		list_1.setBounds(242, 48, 1, 1);
		frame5.getContentPane().add(list_1);
		
		frame5.setVisible(true);
		
		sCode.addActionListener(this);
		sName.addActionListener(this);
		sPhone.addActionListener(this);
		sAll.addActionListener(this);
		sCancel.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		int dataCnt=1;
		String[] nameCollection = null;
		String nameCode =null;
		if(e.getSource() == sCode){
			String code = JOptionPane.showInputDialog("검색할 코드을 입력하세요");
			if(code == null){
				return;
			}
			if(list.size() != 0){
				for(int i=0; i<list.size(); i++){
					if(code.equals(list.get(i).getCode())){
						textArea.setText("회원 코드 : "+list.get(i).getCode() + "\n\n회원 이름 : "+list.get(i).getName() 
								+ "\n\n회원 나이 : " + list.get(i).getAge() + "\n\n회원 성별 : " + list.get(i).getSex()
								+ "\n\n회원 전화번호 : " + list.get(i).getPhone() + "\n\n직장명 : " +list.get(i).getJob() +
								"\n\n회원 주소 : " + list.get(i).getAddr());
						break;
					}
					dataCnt++;
				}
				if(dataCnt == list.size()+1){
					JOptionPane.showMessageDialog(this, "일치하는 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}	
			}else{
				JOptionPane.showMessageDialog(this, "등록된 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource() == sName){
			int cnt = 0;
			String name = JOptionPane.showInputDialog("검색할 이름을 입력하세요");
			if(name == null){
				return;
			}
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					if (name.equals(list.get(i).getName())) {
						cnt++;
					}
				}
				if (cnt > 1) {
					nameCollection = new String[cnt];
					int namecnt = 0;
					for (int i = 0; i < list.size(); i++) {
						if (name.equals(list.get(i).getName())) {
							nameCollection[namecnt] = list.get(i).getCode()
									+ ". " + list.get(i).getName() + " "
									+ list.get(i).getPhone();
							namecnt++;
						}
					}
					nameCode = (String) JOptionPane.showInputDialog(this,
							"같은 이름이 존재합니다.\n", "메시지",
							JOptionPane.INFORMATION_MESSAGE, null,
							nameCollection, nameCollection[0]);
					if (nameCode == null) {
						return;
					}
					nameCode = nameCode.substring(0, 4);
					for (int i = 0; i < list.size(); i++) {
						if ((nameCode.equals(list.get(i).getCode()))) {
							textArea.setText("회원 코드 : " + list.get(i).getCode()
									+ "\n\n회원 이름 : " + list.get(i).getName()
									+ "\n\n회원 나이 : " + list.get(i).getAge()
									+ "\n\n회원 성별 : " + list.get(i).getSex()
									+ "\n\n회원 전화번호 : " + list.get(i).getPhone()
									+ "\n\n직장명 : " + list.get(i).getJob()
									+ "\n\n회원 주소 : " + list.get(i).getAddr());
						}
					}
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (name.equals(list.get(i).getName())) {
							textArea.setText("회원 코드 : " + list.get(i).getCode()
									+ "\n\n회원 이름 : " + list.get(i).getName()
									+ "\n\n회원 나이 : " + list.get(i).getAge()
									+ "\n\n회원 성별 : " + list.get(i).getSex()
									+ "\n\n회원 전화번호 : " + list.get(i).getPhone()
									+ "\n\n직장명 : " + list.get(i).getJob()
									+ "\n\n회원 주소 : " + list.get(i).getAddr());
							break;
						}
						dataCnt++;
					}
					if(dataCnt == list.size()+1){
						JOptionPane.showMessageDialog(this, "일치하는 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}else{
				JOptionPane.showMessageDialog(this, "등록된 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource() == sPhone){
			String phone = JOptionPane.showInputDialog("검색할 전화번호를 입력하세요");
			if(phone == null){
				return;
			}
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					if (phone.equals(list.get(i).getPhone())) {
						textArea.setText("회원 코드 : " + list.get(i).getCode()
								+ "\n\n회원 이름 : " + list.get(i).getName()
								+ "\n\n회원 나이 : " + list.get(i).getAge()
								+ "\n\n회원 성별 : " + list.get(i).getSex()
								+ "\n\n회원 전화번호 : " + list.get(i).getPhone()
								+ "\n\n직장명 : " + list.get(i).getJob()
								+ "\n\n회원 주소 : " + list.get(i).getAddr());
						break;
					}
					dataCnt++;
				}
				if(dataCnt == list.size()+1){
					JOptionPane.showMessageDialog(this, "일치하는 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}
			} else{
				JOptionPane.showMessageDialog(this, "등록된 회원이 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource() == sAll){
			AllSearch al = new AllSearch();
		}else if(e.getSource() == sCancel){
			frame5.dispose();
			super.MmStart();
		}
	}
}