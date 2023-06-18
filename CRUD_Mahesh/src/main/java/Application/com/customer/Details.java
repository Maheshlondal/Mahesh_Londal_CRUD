package Application.com.customer;

import jakarta.persistence.Embeddable;

@Embeddable
public class Details {
    private String sex;
    private String dob;
    private String nativeString;
    
    
    
    
	public Details() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Details(String sex, String dob, String nativeString) {
		super();
		this.sex = sex;
		this.dob = dob;
		this.nativeString = nativeString;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getNativeString() {
		return nativeString;
	}
	public void setNativeString(String nativeString) {
		this.nativeString = nativeString;
	}

	@Override
	public String toString() {
		return "Details [sex=" + sex + ", dob=" + dob + ", nativeString=" + nativeString + "]";
	}

    // Getters and setters

  
}
