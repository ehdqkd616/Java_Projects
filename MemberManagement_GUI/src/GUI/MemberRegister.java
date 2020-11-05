package GUI;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class MemberRegister extends MemberManagements implements ActionListener {
	private JTextField name;
	private JTextField age;
	private JTextField phone;
	private JTextField addr;
	private JTextField code;
	private JTextField job;
	JButton bt1 = new JButton("등록");
	JButton bt2 = new JButton("취소");
	MyFrame frame = new MyFrame();
	JRadioButton female;
	JRadioButton male;
	ButtonGroup bg = new ButtonGroup();
	
	public MemberRegister(){
		frame.setTitle("회원등록");
		frame.setSize(500, 330);
		frame.setLocation(500, 300);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	
		JLabel mCode = new JLabel("회원 코드 :");
		mCode.setBounds(27, 16, 90, 30);
		frame.getContentPane().add(mCode);
		
		JLabel mName = new JLabel("회원 이름 :");
		mName.setBounds(207, 16, 90, 30);
		frame.getContentPane().add(mName);
		
		JLabel mAge = new JLabel("회원 나이 :");
		mAge.setBounds(27, 56, 90, 30);
		frame.getContentPane().add(mAge);
		
		JLabel mPhone = new JLabel("회원 전화번호 :");
		mPhone.setBounds(27, 96, 90, 30);
		frame.getContentPane().add(mPhone);
		
		JLabel mAddr = new JLabel("회원 주소 :");
		mAddr.setBounds(27, 182, 90, 30);
		frame.getContentPane().add(mAddr);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(280, 21, 62, 21);
		frame.getContentPane().add(name);
	
		age = new JTextField();
		age.setColumns(10);
		age.setBounds(97, 61, 62, 21);
		frame.getContentPane().add(age);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(129, 101, 144, 21);
		frame.getContentPane().add(phone);
		
		addr = new JTextField();
		addr.setColumns(10);
		addr.setBounds(97, 187, 357, 21);
		frame.getContentPane().add(addr);

		bt1.setBounds(97, 232, 97, 40);
		frame.getContentPane().add(bt1);
		
		bt2.setBounds(280, 232, 97, 40);
		frame.getContentPane().add(bt2);
		
		code = new JTextField();
		code.setColumns(10);
		code.setBounds(97, 21, 62, 21);
		if(list.size() != 0){
			code.setText((Integer.parseInt(list.get(list.size()-1).getCode())+1)+"");
		}else{
			code.setText(1001 + "");
		}
		frame.getContentPane().add(code);
		
		JLabel mSex = new JLabel("회원 성별 :");
		mSex.setBounds(207, 56, 90, 30);
		frame.getContentPane().add(mSex);
		
		male = new JRadioButton("남");
		male.setBounds(283, 60, 45, 23);
		
		female = new JRadioButton("여");
		female.setBounds(331, 60, 45, 23);
		
		bg.add(male);
		bg.add(female);
		
		frame.getContentPane().add(male);
		frame.getContentPane().add(female);
		
		JLabel label = new JLabel("('-'없이 입력해주세요)");
		label.setBounds(280, 104, 138, 15);
		frame.getContentPane().add(label);
		
		JLabel mJob = new JLabel("직장명(학생은 학교명) : ");
		mJob.setBounds(27, 147, 144, 15);
		frame.getContentPane().add(mJob);
		
		job = new JTextField();
		job.setColumns(10);
		job.setBounds(164, 144, 144, 21);
		frame.getContentPane().add(job);
		
		frame.setVisible(true);
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		String memSex = null;
		boolean  StringCheck = true;
		if(e.getSource() == bt1){
			String memCode = code.getText();
			String memName = name.getText();
			String memAge = age.getText();
			if(male.isSelected()){
				memSex = "남자";
			}else if(female.isSelected()){
				memSex = "여자";
			}
			String memPhone = phone.getText();
			String memJob = job.getText();
			String memAddr = addr.getText();
			if(memCode.equals("")){
				JOptionPane.showMessageDialog(this, "회원 코드을 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}else if(memName.equals("")){
				JOptionPane.showMessageDialog(this, "회원 이름을 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}else if(memAge.equals("")){
				JOptionPane.showMessageDialog(this, "회원 나이를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}else if(memSex.equals("")){
				JOptionPane.showMessageDialog(this, "회원 성별를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}else if(memPhone.equals("")){
				JOptionPane.showMessageDialog(this, "회원 전화번호를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}else if(memJob.equals("")){
				JOptionPane.showMessageDialog(this, "회원 직장명을 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}else if(memAddr.equals("")){
				JOptionPane.showMessageDialog(this, "회원 주소를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}else{
				for(int i=0; i<list.size(); i++){
					if(memCode.equals(list.get(i).getCode())){
						JOptionPane.showMessageDialog(this, "동일한 회원코드가 있습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				if(!integerOrNot(memCode)){
					JOptionPane.showMessageDialog(this, "회원 코드는 문자를 입력할 수 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}else if(!integerOrNot(memAge)){
					JOptionPane.showMessageDialog(this, "회원 나이는 문자를 입력할 수 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}else if(!integerOrNot(memPhone)){
					JOptionPane.showMessageDialog(this, "전화번호는 문자를 입력할 수 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}else if(!(memPhone.substring(0,2).equals("01") && (memPhone.length() ==10 || memPhone.length() ==11))){
					JOptionPane.showMessageDialog(this, "잘못된 전화번호를 입력하였습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}else{
					int check = JOptionPane.showConfirmDialog(this, "입력한 내용이 맞습니까?\n" + 
							"회원 코드 : "+memCode + "\n회원 이름 : "+memName + "\n회원 나이 : " + memAge + 
							"\n회원 성별 : " + memSex + "\n회원 전화번호 : " + memPhone + "\n직장 : " +memJob +
							"\n회원 주소 : " + memAddr,
							"메시지", JOptionPane.INFORMATION_MESSAGE );
					if(check == 0){
						Members m = new Members();
						m.setCode(memCode);
						m.setName(memName);
						m.setAge(memAge);
						m.setSex(memSex);
						m.setPhone(memPhone);
						m.setJob(memJob);
						m.setAddr(memAddr);
						list.add(m);
						JOptionPane.showMessageDialog(this, "회원이 등록되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
						int check2 = JOptionPane.showConfirmDialog(this, "계속 입력하시겠습니까?");
						if(check2 == 0){
							code.setText(Integer.parseInt(list.get(list.size()-1).getCode()) +1 + "");
							name.setText(null);
							age.setText(null);
							bg.clearSelection();
							phone.setText(null);
							addr.setText(null);
							job.setText(null);
						}else if(check2 == 1){
							frame.setVisible(false);
							super.MmStart();
						}
					}
				}
			}
		}else if(e.getSource() == bt2){
			frame.dispose();
			super.MmStart();
		}
	}
	
	public boolean integerOrNot(String strData){ // 입력값이 숫자인지 문자인지 판별 : 
		char[] charData = strData.toCharArray();
		boolean check=true;
		while(check){
			for(int i=0; i<charData.length; i++){		
				if(!Character.isDigit(charData[i])){
						check = !check;
						break;
				}
			}
			break;	
		}return check;
	}
}

