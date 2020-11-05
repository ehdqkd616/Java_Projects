package second;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerManager implements ICustomerManager {
	
	static final int MAX = 100;
	
	static int count = 0;
	
	static Scanner scan = new Scanner(System.in);
	
	static Customer[] cusList = new Customer[MAX];

	public static void main(String[] args) {
		CustomerManager cus = new CustomerManager();
		cus.start();
	}
	
	public void start() {
		while(true) {
			System.out.printf("현재 데이터 개수 : %d\n", count);
			System.out.println("(I)nsert, (R)ead, (U)pdate, (D)elete, (Q)uit");
			System.out.print("메뉴 선택 : ");
			String menu = scan.next();
			menu = menu.toLowerCase();
			switch(menu.charAt(0)) {
			case 'i' :
			case 'ㅑ' :
				if(count>=MAX) {
					System.out.println("데이터가 가득 찼습니다.");
				}else {
					System.out.println("데이터를 입력해 주세요.");
					insertCustomerData();
				}
				break;
			
			case 'r' :
			case 'ㄱ' :
				if(count>0)
				{
					System.out.println("데이터를 조회합니다.");
					printCustomerData(searchCustomerData());
				}else {
					System.out.println("조회할 데이터가 없습니다.");
				}
				break;
			
			case 'u' :
			case 'ㅕ' :
				if(count>0) {
					System.out.println("데이터를 수정합니다.");
					updateCustomerData(searchCustomerData());
				}else {
					System.out.println("수정할 데이터가 없습니다.");
				}
				break;
			
			case 'd' :
			case 'ㅇ' :
				System.out.println("데이터를 삭제합니다.");
				if(count>0) {
					System.out.println("데이터를 삭제합니다.");
					deleteCustomerData(searchCustomerData());
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
			for(i=0; i<count; i++) {
				if(name.equals(cusList[i].getName())) {
					break;
				}
			}
			if(i==count) {
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
		cusList[count++] = cus;		
	}

	@Override
	public Customer searchCustomerData() {
		while(true) {
			System.out.println("조회, 수정 또는 삭제할 사람의 이름을 입력하세요.");
			System.out.println("이름 : ");
			String name = scan.next();
			System.out.print("검색중");
			System.out.print(".");
			try {
				Thread.sleep(1000);
				System.out.print(".");
				Thread.sleep(1000);
				System.out.print(".");
				Thread.sleep(1000);
				System.out.println();
			}catch(Exception e) {
			
			}
			for(int i=0; i<count; i++) {
				if(name.equals(cusList[i].getName())) {
					return cusList[i];
				}
			}
			System.out.println("찾으시는 이름이 없습니다. 다시 입력해 주세요.");
		}
	}

	@Override
	public void printCustomerData(Customer cus) {
		System.out.println(cus);
	}

	@Override
	public void updateCustomerData(Customer cus) {
		System.out.println("----------UPDATE CUSTOMER INFO----------");
		System.out.printf("이름(%s)\n", cus.getName());
		System.out.printf("성별(%c) : ", cus.getGender());
		cus.setGender(scan.next().toUpperCase().charAt(0));
		System.out.printf("이메일(%s) : ", cus.getEmail());
		cus.setEmail(scan.next());
		System.out.printf("출생년도(ex:1900)(%d) : ", cus.getBirthYear());
		cus.setBirthYear(scan.nextInt());
	}

	@Override
	public void deleteCustomerData(Customer cus) {
		for(int i=0; i<count; i++) {
			if(cus.getName().equals(cusList[i].getName())) {
				for(int j=i; j<count-1; j++) {
					cusList[i] = cusList[i+1];
				}
			}
		}
		count--;	
	}
}
