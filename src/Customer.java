public class Customer {
    private int no;
    private String name;
    private String address;
    private String phoneNumber;

    public Customer(int no, String name, String address, String phoneNumber) {
        this.no = no;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "기본키: " + no + ", 이름: " + name + ", 주소: " + address + ", 핸드폰번호: " + phoneNumber;
    }
}
