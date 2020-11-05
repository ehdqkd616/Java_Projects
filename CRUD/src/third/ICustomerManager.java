package third;

public interface ICustomerManager {

	void insertCustomerData();
	Customer searchCustomerData();
	void printCustomerData(Customer cus);
	void updateCustomerData(Customer cus);
	void deleteCustomerData(Customer cus);
	void saveCustomerData();
	void loadCustomerData();
	void printAllCustomerData();
	
}
