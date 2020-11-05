package third;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerManager implements ICustomerManager {
	
	static Scanner scan = new Scanner(System.in);
	
	static ArrayList<Customer> cusList = new ArrayList<>();
	
	public static void main(String[] args) {
		CustomerManager cus = new CustomerManager();
		cus.start();
	}
	
	public void start() {
		loadCustomerData();
		while(true) {
			System.out.printf("현재 데이터 개수 : %d\n", cusList.size());
			System.out.println("(I)nsert, (R)ead, read(A)11, (U)pdate, (D)elete, (Q)uit");
			System.out.print("메뉴 선택 : ");
			String menu = scan.next();
			menu = menu.toLowerCase();
			switch(menu.charAt(0)) {
			case 'i' :
			case 'ㅑ' :
					System.out.println("데이터를 입력해 주세요.");
					insertCustomerData();
					saveCustomerData();
					break;
			
			case 'r' :
			case 'ㄱ' :
				if(cusList.size()>0)
				{
					System.out.println("데이터를 조회합니다.");
					printCustomerData(searchCustomerData());
				}else {
					System.out.println("조회할 데이터가 없습니다.");
				}
				break;
				
			case 'a' :
			case 'ㅁ' :	
				if(cusList.size()>0)
				{
					System.out.println("데이터를 조회합니다.");
					printAllCustomerData();
				}else {
					System.out.println("조회할 데이터가 없습니다.");
				}
				
				break;
			
			case 'u' :
			case 'ㅕ' :
				if(cusList.size()>0) {
					System.out.println("데이터를 수정합니다.");
					updateCustomerData(searchCustomerData());
					saveCustomerData();
				}else {
					System.out.println("수정할 데이터가 없습니다.");
				}
				break;
			
			case 'd' :
			case 'ㅇ' :
				System.out.println("데이터를 삭제합니다.");
				if(cusList.size()>0) {
					System.out.println("데이터를 삭제합니다.");
					deleteCustomerData(searchCustomerData());
					saveCustomerData();
				}else {
					System.out.println("삭제할 데이터가 없습니다.");
				}
				break;
			
			case 'q' :
			case 'ㅂ' :
				System.out.println("프로그램을 종료합니다.");
				scan.close();
				System.exit(0);
		 
			default : 
				System.out.println("메뉴를 잘 못 입력하셨습니다.");
			}
		}
	}

	@Override
	public void insertCustomerData() throws InputMismatchException {
		String name = null;
		while(true) {
			System.out.print("이름 : ");
			name = scan.next();
			int i=0;
			for(i=0; i<cusList.size(); i++) {
				if(name.equals(cusList.get(i).getName())) {
					break;
				}
			}
			if(i==cusList.size()) {
				break;
			}
			System.out.println("이름이 중복됩니다. 다시 입력해 주세요.");
		}

		System.out.print("성별(M/F) : ");
		char gender = scan.next().toUpperCase().charAt(0);
		System.out.print("이메일 : ");
		String email = scan.next();
		System.out.println("출생년도(ex:1900) : ");
		int birthYear = 1990;
		try {
		birthYear = scan.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("숫자만 입력하셔야 합니다. 기본값 할당");
			birthYear = 1990;
		}
		Customer cus = new Customer(name, gender, email, birthYear);
		cusList.add(cus);		
	}

	@Override
	public Customer searchCustomerData() {
		while(true) {
			System.out.println("조회, 수정 또는 삭제할 사람의 이름을 입력하세요.");
			System.out.println("메뉴로 돌아가시려면 Q를 눌러주세요.");
			System.out.println("이름 : ");
			String name = scan.next();
			if(name.charAt(0)=='q'||name.charAt(0)=='Q'||name.charAt(0)=='ㅂ') {
				return new Customer();
			}
			for(Customer cus : cusList) {
				if(cus.getName().equals(name)) {
					return cus;
				}
			}
			System.out.println("찾으시는 이름이 없습니다. 다시 입력해 주세요.");
		}
	}

	@Override
	public void printCustomerData(Customer cus) {
		if(cus.getName()==null) {
			System.out.println("메뉴로 돌아갑니다.");
		}else {
			System.out.println(cus);
		}
	}

	@Override
	public void updateCustomerData(Customer cus) {
		if(cus.getName()==null) {
			System.out.println("메뉴로 돌아갑니다.");
		}else {
			System.out.println("----------UPDATE CUSTOMER INFO----------");
			System.out.printf("이름(%s)\n", cus.getName());
			System.out.printf("성별(%c) : ", cus.getGender());
			cus.setGender(scan.next().toUpperCase().charAt(0));
			System.out.printf("이메일(%s) : ", cus.getEmail());
			cus.setEmail(scan.next());
			System.out.printf("출생년도(ex:1900)(%d) : ", cus.getBirthYear());
			cus.setBirthYear(scan.nextInt());
		}
	}

	@Override
	public void deleteCustomerData(Customer cus) {
		if(cus.getName()==null) {
			System.out.println("메뉴로 돌아갑니다.");
		}else {
			cusList.remove(cus);
		}

	}

	@Override
	public void saveCustomerData() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try{
			fos = new FileOutputStream("customer.data");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(cusList);
			System.out.println("저장 완료.");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(oos!=null) {try {oos.close();}catch(IOException e) {}}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadCustomerData() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("customer.data");
			ois = new ObjectInputStream(fis);
			cusList = (ArrayList<Customer>)ois.readObject();
			System.out.println("로드 완료.");
		}catch(FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(ois!=null) {try {ois.close();}catch(IOException e) {}}
		}
	}

	@Override
	public void printAllCustomerData() {
		int i = 0;
		for(Customer cus : cusList) {
			System.out.println(i+". "+cus);
			i++;
		}
		
	}
}
