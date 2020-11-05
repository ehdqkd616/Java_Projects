import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable{

	private static final long serialVersionUID = 3377603511135135933L;
	
	private String name;
	private String email;
	private String gender;
	private Date birth;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public Customer() {
		
	}
	
	public Customer(String name, String email, String gender, Date birth) {
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.birth = birth;
	}
	
	@Override
	public String toString() {
		return "Name=" + name + ", Email=" + email + ", Gender=" + gender + ", Birth=" + (birth.getYear()+1900)+"."+(birth.getMonth()+1)+"."+birth.getDate();
	}
	
	
	
}
