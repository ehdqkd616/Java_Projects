package GUI;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;

public class MemberManagements extends MyFrame{
	static ArrayList<Members> list = new ArrayList<Members>(); 
	MyFrame frame = new MyFrame();
	JFileChooser f = new JFileChooser();
	FileNameExtensionFilter ff;
	Image backImg;
	public MemberManagements() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu1 = new JMenu("파일");
		menuBar.add(menu1);
		
		JMenuItem menu5Item = new JMenuItem("새로하기");
		menu1.add(menu5Item);
		
		JMenuItem menu3Item = new JMenuItem("불러오기");
		menu1.add(menu3Item);
		
		JMenuItem menu4Item = new JMenuItem("저장하기");
		menu1.add(menu4Item);
		
		JMenuItem menu1Item = new JMenuItem("종료");
		menu1.add(menu1Item);
		
		JMenu menu2 = new JMenu("도움말");
		menuBar.add(menu2);
		
		JMenuItem menu2Item = new JMenuItem("버젼정보");
		menu2.add(menu2Item);

		menu1Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					System.exit(0);
			}
		});

		menu2Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Version va = new Version();
			}
		});
		
		ff = new FileNameExtensionFilter("텍스트파일(.txt)", "txt");
		
		menu3Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  f.setCurrentDirectory(new File("./src/"));
				  f.addChoosableFileFilter(ff);
				  int file = f.showOpenDialog(null);
				  if (file != f.APPROVE_OPTION) {
				    return;
				 }
				 String filePath = f.getSelectedFile().getPath(); 
				 if(filePath != null){
					fileLoad(filePath); 
				 }
			}
		});
		
		menu4Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 f.setCurrentDirectory(new File("./src/"));
				 f.setFileSelectionMode(f.FILES_AND_DIRECTORIES);  
				 f.addChoosableFileFilter(ff);
				 int file = f.showSaveDialog(null);
				 if (file == f.APPROVE_OPTION) {
				  String type = ff.getDescription().substring(6, 10);
				  String savepathname = f.getSelectedFile().getAbsolutePath()+type;
				  fileSave(savepathname);
				 }else{
					 return;
				 }
			}
		});
		
		menu5Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 list.clear();
			}
		});

		JLabel lblNewLabel = new JLabel(new ImageIcon("./src/member.jpg"));
		lblNewLabel.setBounds(22, 10, 431, 213);
		frame.getContentPane().add(lblNewLabel);
	}
	
	public void MmStart(){
		frame.setTitle("회원관리 프로그램");
		frame.setSize(500, 360);
		frame.setLocation(500, 300);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JButton bt1 = new JButton("회원등록");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MemberRegister mr = new MemberRegister();
			}
		});
		bt1.setBounds(22, 232, 133, 49);
		frame.getContentPane().add(bt1);
		
		JButton bt2 = new JButton("회원관리");
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Managements mg = new Managements();
			}
		});
		bt2.setBounds(174, 232, 133, 49);
		frame.getContentPane().add(bt2);
		
		JButton bt3 = new JButton("회원검색");
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MemberSearch ms = new MemberSearch();
			}
		});
		bt3.setBounds(324, 232, 133, 49);
		frame.getContentPane().add(bt3);
		
	}
	
	public void fileLoad(String path){
		FileInputStream fi = null;
		InputStreamReader isr = null;
		BufferedReader bfr = null;
		StringTokenizer st = null;
		
		try{
				list.clear();
				fi = new FileInputStream(path);
				isr = new InputStreamReader(fi);
				bfr = new BufferedReader(isr);
				String str = null;
				while((str = bfr.readLine())!= null){
					Members m = new Members();
					st = new StringTokenizer(str,",");
					m.setCode(st.nextToken());
					m.setName(st.nextToken());
					m.setAge(st.nextToken());
					m.setSex(st.nextToken());
					m.setPhone(st.nextToken());
					m.setJob(st.nextToken());
					m.setAddr(st.nextToken());
					list.add(m);
				}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				fi.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void fileSave(String path){
		 FileWriter fw = null;
		 try{
		   fw = new FileWriter(path);
		   for(int i=0; i<list.size(); i++){
			   fw.write(list.get(i).getCode());
			   fw.write(",");
			   fw.write(list.get(i).getName());
			   fw.write(",");
			   fw.write(list.get(i).getAge());
			   fw.write(",");
			   fw.write(list.get(i).getSex());
			   fw.write(",");
			   fw.write(list.get(i).getPhone());
			   fw.write(",");
			   fw.write(list.get(i).getJob());
			   fw.write(",");
			   fw.write(list.get(i).getAddr());
			   fw.write("\r\n");
		   }       
	    }catch (Exception e) {
	    	e.printStackTrace();
	   }finally{
			try{
				fw.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
