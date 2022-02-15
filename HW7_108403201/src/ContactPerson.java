
public class ContactPerson {
	private int MemberID;
	private String name;
	private String type;
	private String phoneNumber;
	
	
	public ContactPerson() {
	}
	public ContactPerson(int MemberID, String name, String type, String phoneNumber) {
		setMemberID(MemberID);
		setName(name);
		setType(type);
		setPhoneNumber(phoneNumber);
		
	}
	public void setMemberID(int MemberID) {
		this.MemberID = MemberID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getMemberID() {
		return MemberID;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getphoneNumber() {
		return phoneNumber;
	}
	
}
