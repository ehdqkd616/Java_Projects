import java.util.Scanner;

public class CustomerManager {
	
	static final int MAX = 100;
	
	static int count = 0;
	
	static int index = -1;
	
	static Scanner scan = new Scanner(System.in);
	
	static String[] nameList = new String[MAX];
	static char[] genderList = new char[MAX];
	static String[] emailList = new String[MAX];
	static int[] birthYearList = new int[MAX];
	
	public static void main(String[] args) {
		while(true) {
			System.out.printf("현재 데이터 개수 : %d, 현재 커서 위치 : %d\n", count, index);
			System.out.println("(I)nsert, (P)revious, (N)ext, (R)ead, (U)pdate, (D)elete, (Q)uit");
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
					insertCustomer();
				}
				break;
			
			case 'p' :
			case 'ㅔ' :
				if(index<=0) {
					System.out.println("커서를 더 이상 이동할 수 없습니다.");
				} else {
					index--;
					System.out.println("커서를 이동했습니다.");
				}
				break;
			
			case 'n' :
			case 'ㅜ' :
				if(index>=count-1) {
					System.out.println("커서를 더 이상 이동할 수 없습니다.");
				}else {
					index++;
				}
				break;
			
			case 'r' :
			case 'ㄱ' :
				if(index>-1 && index<count)
				{
					System.out.println("데이터를 조회합니다.");
					readCustomer(index);
				}else {
					System.out.println("커서가 정상범위가 아닙니다.");
				}
				break;
			
			case 'u' :
			case 'ㅕ' :
				if(index>-1 && index<count) {
					System.out.println(index + "번째 데이터를 수정합니다.");
					updateCustomer(index);
				}else {
					System.out.println("커서가 정상범위가 아닙니다.");
				}
				break;
			
			case 'd' :
			case 'ㅇ' :
				System.out.println("데이터를 삭제합니다.");
				if(index>-1 && index<count) {
					System.out.println(index + "번째 데이터를 삭제합니다.");
					deleteCustomer(index);
				}else {
					System.out.println("커서가 정상범위가 아닙니다.");
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
	
	public static void insertCustomer() {
		System.out.print("이름 : ");
		String name = scan.next();
		System.out.print("성별(M/F) : ");
		char gender = scan.next().toUpperCase().charAt(0);
		System.out.print("이메일 : ");
		String email = scan.next();
		System.out.println("출생년도(ex:1900) : ");
		int birthYear = scan.nextInt();
		nameList[count] = name;
		genderList[count] = gender;
		emailList[count] = email;
		birthYearList[count] = birthYear;
		count++;
	}
	
	public static void readCustomer(int index) {
		System.out.printf("이름 : %s\n", nameList[index]);
		System.out.printf("성별 : %c\n", genderList[index]);
		System.out.printf("이메일 : %s\n", emailList[index]);
		System.out.printf("출생년도 : %d\n", birthYearList[index]);
	}
	
	public static void updateCustomer(int index) {
		System.out.println("----------UPDATE CUSTOMER INFO----------");
		System.out.print("이름(" + nameList[index] + ") :");
		nameList[index] = scan.next();
		
		System.out.print("성별(" + genderList[index] + ") :");
		genderList[index] = scan.next().toUpperCase().charAt(index);
		
		System.out.print("이메일(" + emailList[index] + ") :");
		emailList[index] = scan.next();
		
		System.out.print("출생년도(" + birthYearList[index] + ") :");
		birthYearList[index] = scan.nextInt();
	}
	
	public static void deleteCustomer(int index) {
		for(int i=index; i<count-1; i++) {
			nameList[i] = nameList[i+1];
			genderList[i] = genderList[i+1];
			emailList[i] = emailList[i+1];
			birthYearList[i] = birthYearList[i+1];
		}
		count--;
	}
}
