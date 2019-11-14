import java.util.*;

public class Customer {
	
	private String name;
	private int id;
	private String address;
	private String creditNum;
	
	public Customer(String name, int id, String address, String creditNum) {
		this.name = name;
		this.id = id;
		this.address = address;
		this.creditNum = creditNum;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getCreditNum() {
		return creditNum;
	}
	
	public int getID() {
		return id;
	}
	
	
	
	
}
