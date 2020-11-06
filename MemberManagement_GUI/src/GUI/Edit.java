package GUI;

import java.awt.Frame;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class Edit extends MemberManagements implements ActionListener{
	MyFrame frame3 = new MyFrame();
	JButton eName = new JButton("회원 이름");
	JButton eAge = new JButton("회원 나이");
	JButton eSex = new JButton("회원 성별");
	JButton ePhone = new JButton("전화번호");
	JButton eJob = new JButton("직장명");
	JButton eAddr = new JButton("회원 주소");
	JButton eConfirm = new JButton("확인");
	JButton eCancel = new JButton("취소");
	
	private JTextField tName;
	private JTextField tAge;
	private JTextField tSex;
	private JTextField tPhone;
	private JTextField tJob;
	private JTextField tAddr;
	int index ;
	String name=null, age=null, selectSex=null, phone=null,job=null, addr =null;
	public Edit(int index) {
		frame3.getContentPane().setEnabled(false);
		frame3.setTitle("수정");
		frame3.setSize(409, 295);
		frame3.setLocation(550, 350);
		frame3.setDefaultCloseOperation(frame3.EXIT_ON_CLOSE);
		
		this.index = index - 1;
		
		JLabel lblNewLabel = new JLabel("수정");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel.setBounds(12, 10, 47, 23);
		frame3.getContentPane().add(lblNewLabel);
		
		eName.setBounds(275, 33, 105, 25);
		frame3.getContentPane().add(eName);
		
		eAge.setBounds(275, 63, 105, 25);
		frame3.getContentPane().add(eAge);
		
		eSex.setBounds(275, 93, 105, 25);
		frame3.getContentPane().add(eSex);
		
		ePhone.setBounds(275, 123, 105, 25);
		frame3.getContentPane().add(ePhone);
		
		eJob.setBounds(275, 153, 105, 25);
		frame3.getContentPane().add(eJob);
		
		eAddr.setBounds(275, 183, 105, 25);
		frame3.getContentPane().add(eAddr);
		
		eConfirm.setBounds(61, 222, 105, 25);
		frame3.getContentPane().add(eConfirm);
		
		eCancel.setBounds(218, 222, 105, 25);
		frame3.getContentPane().add(eCancel);
		
		JLabel name = new JLabel("이름 : ");
		name.setBounds(12, 43, 57, 15);
		frame3.getContentPane().add(name);
		
		JLabel age = new JLabel("나이 : ");
		age.setBounds(12, 73, 57, 15);
		frame3.getContentPane().add(age);
		
		JLabel sex = new JLabel("성별 : ");
		sex.setBounds(12, 103, 57, 15);
		frame3.getContentPane().add(sex);
		
		JLabel phone = new JLabel("전화번호 : ");
		phone.setBounds(12, 133, 83, 15);
		frame3.getContentPane().add(phone);
		
		JLabel job = new JLabel("직장명 : ");
		job.setBounds(12, 163, 57, 15);
		frame3.getContentPane().add(job);
		
		JLabel addr = new JLabel("주소 : ");
		addr.setBounds(12, 193, 57, 15);
		frame3.getContentPane().add(addr);
		
		tName = new JTextField();
		tName.setEditable(false);
		tName.setBounds(50, 40, 208, 21);
		//tName.s
		frame3.getContentPane().add(tName);
		tName.setColumns(10);
		
		tAge = new JTextField();
		tAge.setEditable(false);
		tAge.setColumns(10);
		tAge.setBounds(50, 70, 208, 21);
		frame3.getContentPane().add(tAge);
		
		tSex = new JTextField();
		tSex.setEditable(false);
		tSex.setColumns(10);
		tSex.setBounds(50, 100, 208, 21);
		frame3.getContentPane().add(tSex);
		
		tPhone = new JTextField();
		tPhone.setEditable(false);
		tPhone.setColumns(10);
		tPhone.setBounds(73, 130, 185, 21);
		frame3.getContentPane().add(tPhone);
		
		tJob = new JTextField();
		tJob.setEditable(false);
		tJob.setColumns(10);
		tJob.setBounds(60, 160, 198, 21);
		frame3.getContentPane().add(tJob);
		
		tAddr = new JTextField();
		tAddr.setEditable(false);
		tAddr.setColumns(10);
		tAddr.setBounds(50, 190, 208, 21);
		frame3.getContentPane().add(tAddr);
		
		tName.setText(list.get(this.index).getName());
		tAge.setText(list.get(this.index).getAge());
		tSex.setText(list.get(this.index).getSex());
		tPhone.setText(list.get(this.index).getPhone());
		tJob.setText(list.get(this.index).getJob());
		tAddr.setText(list.get(this.index).getAddr());
	
		frame3.setVisible(true);
		eName.addActionListener(this);
		eAge.addActionListener(this);
		eSex.addActionListener(this);
		ePhone.addActionListener(this);
		eJob.addActionListener(this);
		eAddr.addActionListener(this);
		eConfirm.addActionListener(this);
		eCancel.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		int check;
		if(e.getSource() == eName){
			check = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?\n" + 
					"회원 이름 : " + tName.getText() ,"메시지", JOptionPane.INFORMATION_MESSAGE );
			if(check ==0){
				name = JOptionPane.showInputDialog("수정할 이름을 입력하세요", list.get(this.index).getName());
				tName.setText(name);
				if(name == null){
					tName.setText(list.get(this.index).getName());
				}
			}
		}else if(e.getSource() == eAge){
			check = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?\n" + 
					"회원 나이 : " + tAge.getText() ,"메시지", JOptionPane.INFORMATION_MESSAGE );
			if(check ==0){
				age = JOptionPane.showInputDialog("수정할 나이를 입력하세요", list.get(this.index).getAge());
				tAge.setText(age);
				if(age == null){
					tAge.setText(list.get(this.index).getAge());
				}
			}
		}else if(e.getSource() == eSex){
			check = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?\n" + 
					"회원 성별 : " + tSex.getText() ,"메시지", JOptionPane.INFORMATION_MESSAGE );
			if(check ==0){
				String[] str = {"남자", "여자"};
				selectSex = (String)JOptionPane.showInputDialog(this, "수정할 성별을 입력하세요\n" , "메시지",
						JOptionPane.INFORMATION_MESSAGE,	null, str, str[0]);
				tSex.setText(selectSex);
				if(selectSex == null){
					tSex.setText(list.get(this.index).getSex());
				}
			}
		}else if(e.getSource() == ePhone){
			check = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?\n" + 
					"전화번호 : " + tPhone.getText() ,"메시지", JOptionPane.INFORMATION_MESSAGE );
			if(check ==0){
				phone = JOptionPane.showInputDialog("수정할 전화번호를 입력하세요", list.get(this.index).getPhone());
				tPhone.setText(phone);
				if(phone == null){
					tPhone.setText(list.get(this.index).getPhone());
				}
			}
		}else if(e.getSource() == eJob){
			check = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?\n" + 
					"직장명 : " + tJob.getText() ,"메시지", JOptionPane.INFORMATION_MESSAGE );
			if(check ==0){
				job = JOptionPane.showInputDialog("수정할 직장명을 입력하세요", list.get(this.index).getJob());
				tJob.setText(job);
				if(job == null){
					tJob.setText(list.get(this.index).getJob());
				}
			}
		}else if(e.getSource() == eAddr){
			check = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?\n" + 
					"회원 주소 : " + tAddr.getText() ,"메시지", JOptionPane.INFORMATION_MESSAGE );
			if(check ==0){
				addr = JOptionPane.showInputDialog("수정할 주소를 입력하세요", list.get(this.index).getAddr());
				tAddr.setText(addr);
				if(addr == null){
					tAddr.setText(list.get(this.index).getAddr());
				}
			}
		}
		if(e.getSource() == eConfirm){
			if( name != null){
				list.get(this.index).setName(this.name);
			}
			if(age != null){
				list.get(this.index).setAge(this.age);
			}
			if(selectSex != null){
				list.get(this.index).setSex(this.selectSex);
			}
			if(phone != null){
				list.get(this.index).setPhone(this.phone);
			}
			if(job != null){
				list.get(this.index).setJob(this.job);
			}
			if(addr != null){
				list.get(this.index).setAddr(this.addr);
			}
			frame3.dispose();
			Managements m = new Managements();
		}else if(e.getSource() == eCancel){
			frame3.dispose();
			Managements m = new Managements();
		}
		//list.get(this.index).setName(name);
	}
}
