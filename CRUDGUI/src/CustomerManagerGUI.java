import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomerManagerGUI extends Frame {

	static ArrayList<Customer> cusList = new ArrayList<>();
	static Toolkit kit = Toolkit.getDefaultToolkit();
	static Dimension screenSize = kit.getScreenSize();
	
	private CustomerManagerGUI() {
		super("고객 관리 프로그램");
		MenuPanel p = new MenuPanel();
		add(p);
		setVisible(true);
		setLocation(screenSize.width/2-150,screenSize.height/2-150);
		setSize(300, 300);
		setAutoRequestFocus(false);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		readCustomerData();
	}
	
	private class MenuPanel extends Panel implements ActionListener{
		public MenuPanel() {
			super(new GridLayout(2,2));
			Button b1 = new Button("Create");
			Button b2 = new Button("Read");
			Button b3 = new Button("Update");
			Button b4 = new Button("Delete");
			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			b4.addActionListener(this);
			add(b1); add(b2); add(b3); add(b4);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().charAt(0)=='C') {
				new InsertFrame();
			}else if(e.getActionCommand().charAt(0)=='R') {
				new SelectFrame();
			}else if(e.getActionCommand().charAt(0)=='U') {
				new UpdateFrame();
			}else if(e.getActionCommand().charAt(0)=='D') {
				new DeleteFrame();
			}
		}
	}

	public class InsertFrame extends Frame implements ActionListener{

		public InsertFrame() {
			super("Insert Data");
			setSize(200,300);
			setLocation(screenSize.width/2-100,screenSize.height/2-150);
			setVisible(true);
			add(new InsertPanel());
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}

		public class InsertPanel extends Panel implements ActionListener{

			TextField name = new TextField(20);
			TextField email = new TextField(20);
			CheckboxGroup cg = new CheckboxGroup();
			Checkbox male = new Checkbox("Male", cg, false);
			Checkbox female = new Checkbox("Female", cg, false);
			TextField birth = new TextField(20);
			Button b1 = new Button("Insert");

			public InsertPanel() {
				super();
				Label lname = new Label("Name:");
				lname.setAlignment(1);
				Label lemail = new Label("Email:");
				lemail.setAlignment(1);
				Label lgender = new Label("Gender:");
				lgender.setAlignment(1);
				Panel p = new Panel(new GridLayout(1,2));
				Label lbirth = new Label("Birth:(ex:1990-01-01)");
				lbirth.setAlignment(1);
				add(lname); add(name); add(lemail); add(email); 
				add(lgender); p.add(male); p.add(female); add(p); add(lbirth); add(birth);
				b1.addActionListener(this);
				add(b1);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkOverlap(name.getText())) {
					String gender = null;
					if(male.getState()==true) {
						gender = "Male";
					}else {
						gender = "Female";
					}
					insertCustomerData(name.getText(), email.getText(), gender, birth.getText());
					writeCustomerData();
				}else {
					new DialogMessage("Name Overlap");
				}
			}
		}

		public boolean checkOverlap(String name) {
			for(Customer c : cusList) {
				if(name.equals(c.getName())) {
					return false;
				}
			}
			return true;
		}

		public void insertCustomerData(String name, String email, String gender, String date) {
			try {
				Customer c = new Customer();
				c.setName(name);
				c.setEmail(email);
				c.setGender(gender);
				Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				c.setBirth(birth);
				cusList.add(c);
				new DialogMessage("Insert Complete");
				dispose();
			}catch(ParseException e) {
				new DialogMessage(e.getMessage());
			}
		}
	}

	public class SelectFrame extends Frame implements ActionListener{

		public SelectFrame() {
			super("Select");
			setSize(200,100);
			setLocation(screenSize.width/2-100,screenSize.height/2-50);
			setVisible(true);
			Panel p = new Panel();
			Button b1 = new Button("Search");
			b1.addActionListener(this);
			Button b2 = new Button("All Data");
			b2.addActionListener(this);
			p.add(b1); p.add(b2);
			add(p);
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().charAt(0)=='S') {
				dispose();
				new SearchFrame();
			}else {
				dispose();
				new ReadAllFrame();
			}
		}
	}

	public class SearchFrame extends Frame implements ActionListener{

		public SearchFrame() {
			super("Search");
			if(cusList.size()==0){
				new DialogMessage("Have No Data In System");
				dispose();
			}else {
				setSize(500,100);
				setLocation(screenSize.width/2-250,screenSize.height/2-50);
				setVisible(true);
				add(new SearchPanel());
			}
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}
		
		// 검색 패널
		public class SearchPanel extends Panel implements ActionListener{

			TextField name = new TextField(20);
			Button b1 = new Button("Search");

			public SearchPanel() {
				super();

				b1.addActionListener(this);
				add(name); add(b1);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				Customer cus = searchCustomer(name.getText());
				if(cus==null) {
					new DialogMessage("Cannot Find");
				}else {
					add(new Label(cus.toString()));
					validate();
				}
			}

			public Customer searchCustomer(String name) {
				for(Customer c : cusList) {
					if(name.equals(c.getName())) {
						return c;
					}
				}
				return null;
			}
		}
	}

	public class ReadAllFrame extends Frame implements ActionListener{

		public ReadAllFrame() {
			super("All Data");
			if(cusList.size()==0) {
				new DialogMessage("Have No Data In System");
				dispose();
			}else {
				setSize(500,500);
				setLocation(screenSize.width/2-250,screenSize.height/2-250);
				setVisible(true);
				Panel p = new Panel();
				for(Customer c : cusList) {
					Label l = new Label(c.toString());
					p.add(l);
				}
				Button b1 = new Button("Confirm");
				b1.addActionListener(this);
				p.add(b1);
				add(p);
			}
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}

	}
	
	// U를 눌렀을때 작동하는 프레임
	public class UpdateFrame extends Frame implements ActionListener{

		Customer c = new Customer();

		public UpdateFrame() {
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
			setSize(500,100);
			setLocation(screenSize.width/2-250,screenSize.height/2-50);
			SearchFrame s = new SearchFrame();
			s.removeAll();
			UpdateSearchPanel up = new UpdateSearchPanel(s);
			up.add(up.name);
			up.add(up.b1);
			s.add(up);
			s.validate();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}

		public class UpdateSearchPanel extends CustomerManagerGUI.SearchFrame.SearchPanel implements ActionListener{

			public UpdateSearchPanel(CustomerManagerGUI.SearchFrame f) {
				f.super();
				b1.removeActionListener(b1.getActionListeners()[0]);
				b1.addActionListener(this);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				Customer cus = searchCustomer(name.getText());
				if(cus==null) {
					new DialogMessage("Cannot Find");
				}else {
					c = cus;
					InsertFrame i = new InsertFrame();
					UpdateInsertPanel upi = new UpdateInsertPanel(i);
					i.removeAll();
					upi.getData();
					i.add(upi);
					i.validate();
				}
			}
		}

		public class UpdateInsertPanel extends CustomerManagerGUI.InsertFrame.InsertPanel implements ActionListener{

			public UpdateInsertPanel(CustomerManagerGUI.InsertFrame f) {
				f.super();
			}

			public void getData() {
				name.setText(c.getName());
				name.setEditable(false);
				email.setText(c.getEmail());
				if(c.getGender().equals("Male")) {
					male.setState(true);
				}else {
					female.setState(true);
				}
				birth.setText(new SimpleDateFormat("yyyy-MM-dd").format(c.getBirth()));
				b1.setLabel("Update");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				c.setEmail(email.getText());
				String gender = null;
				if(male.getState()==true) {
					gender = "Male";
				}else {
					gender = "Female";
				}
				c.setGender(gender);
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birth.getText());
					c.setBirth(date);
					writeCustomerData();
					new DialogMessage("Update Complete");
				}catch(ParseException p) {
					new DialogMessage(p.getMessage());
				}
			}
		}
	}

	public class DeleteFrame extends Frame implements ActionListener{

		Customer c = new Customer();

		public DeleteFrame() {
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
			setLocation(screenSize.width/2-250,screenSize.height/2-50);
			SearchFrame s = new SearchFrame();
			s.setSize(500,150);
			s.removeAll();
			DeleteSearchPanel up = new DeleteSearchPanel(s);
			up.add(up.name);
			up.add(up.b1);
			s.add(up);
			s.validate();

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}

		public class DeleteSearchPanel extends CustomerManagerGUI.SearchFrame.SearchPanel implements ActionListener{

			public DeleteSearchPanel(CustomerManagerGUI.SearchFrame f) {
				f.super();
				b1.removeActionListener(b1.getActionListeners()[0]);
				b1.addActionListener(this);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				Customer cus = searchCustomer(name.getText());
				if(cus==null) {
					new DialogMessage("Cannot Find");
				}else {
					c = cus;
					removeAll();
					add(new DeletePanel());
					validate();
				}
			}
		}
		
		public class DeletePanel extends Panel implements ActionListener{

			public DeletePanel() {
				setLayout(new GridLayout(3,1));
				Label l = new Label("Delete This Data?");
				l.setAlignment(1);
				Label lc = new Label(c.toString());
				lc.setAlignment(1);
				Button b1 = new Button("Delete");
				b1.addActionListener(this);
				add(l); add(lc); add(b1);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				cusList.remove(c);
				writeCustomerData();
				new DialogMessage("Delete Complete");
				dispose();
			}
		}
	}

	public class DialogMessage extends Frame implements ActionListener{
		public DialogMessage(String message) {
			Dialog d = new Dialog(this, message);
			setVisible(true);
			setSize(200,25);
			setLocation(screenSize.width/2-100,screenSize.height/2-25);
			Button b1 = new Button(message);
			b1.addActionListener(this);
			d.setVisible(true);
			d.setSize(200,100);
			d.setLocation(screenSize.width/2-100,screenSize.height/2-25);
			d.add(b1);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	public static void writeCustomerData() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("CustomerManager.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(cusList);
		}catch (IOException e) {
			System.out.println("is this?");
			System.out.println(e.getMessage());
		}finally {
			if(oos!=null) {
				try {oos.close();}catch(IOException e) {}
			}
		}
	}

	public static void readCustomerData() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("CustomerManager.txt");
			ois = new ObjectInputStream(fis);
			cusList = (ArrayList<Customer>)ois.readObject();
		}catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}finally {
			if(ois!=null) {
				try {ois.close();} catch (IOException e) {};
			}
		}
	}
	
	public static void main(String[] args) {
		CustomerManagerGUI cg = new CustomerManagerGUI();
	}

}
