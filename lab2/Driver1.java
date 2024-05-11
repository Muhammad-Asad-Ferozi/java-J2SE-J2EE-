class Student {
    private int rollNo;
    private String name;
    private String email;
    private String address;

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(int rollNo, String name, String email, String address) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    // Copy constructor (object cloning)
    public Student(Student other) {
        this.rollNo = other.rollNo;
        this.name = other.name;
        this.email = other.email;
        this.address = other.address;
    }

    // Getter and setter methods
    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Method to input data
    public void inputData(int rollNo, String name, String email, String address) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    // Method to display data
    public void showData() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
    }
}

public class Driver1{
	public static void main(String[] args){
		Student student[] = new Student[5];
		student[0] = new Student(1, "John Doe", "john@example.com", "123 Main St");
        student[1] = new Student(2, "Alice Smith", "alice@example.com", "456 Oak Ave");
        student[2] = new Student(3, "Bob Johnson", "bob@example.com", "789 Elm St");
		
		student[3] = new Student();
		student[3].inputData(36,"asad","maferozzi373@gmail.com","Lahore");
		
		student[4] = new Student(student[3]);
		
		student[1].showData();
		student[4].showData();
	}
}