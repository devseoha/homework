import java.util.ArrayList;
import java.util.Scanner;

public class CustomerProgram {
    private ArrayList<Customer> customers;
    private Scanner scanner;

    public CustomerProgram() {
        customers = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            System.out.println("=== 아래 메뉴의 번호를 입력해주세요.===");
            System.out.println("1. 고객 추가");
            System.out.println("2. 고객 수정");
            System.out.println("3. 고객 삭제");
            System.out.println("4. 고객 리스트");
            System.out.println("5. 프로그램 종료");
            System.out.print("번호: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    removeCustomer();
                    break;
                case 4:
                    listCustomers();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("번호를 다시 입력새주세요.");
            }
        }
    }

    private void addCustomer() {
        System.out.println("고객 정보를 입력해주세요.");
        System.out.print("고객 번호: ");
        int no = scanner.nextInt();
        scanner.nextLine();
        System.out.print("이름: ");
        String number = scanner.nextLine();
        System.out.print("주소: ");
        String address = scanner.nextLine();
        System.out.print("핸드폰번호: ");
        String phoneNumber = scanner.nextLine();

        Customer customer = new Customer(no, number, address, phoneNumber);
        customers.add(customer);
        System.out.println("고객 정보를 성공적으로 저장했습니다.");
    }

    private void removeCustomer() {
        System.out.print("삭제할 고객번호를 입력해주세요: ");
        int no = scanner.nextInt();

        boolean found = false;
        for (Customer customer : customers) {
            if (customer.getNo() == no) {
                customers.remove(customer);
                found = true;
                System.out.println("고객정보를 성공적으로 삭제했습니다.");
                break;
            }
        }

        if (!found) {
            System.out.println("고객 정보를 찾을 수 없습니다.");
        }
    }

    private void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("고객 정보가 없습니다.");
        } else {
            System.out.println("고객 리스트 ============");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    private void updateCustomer() {
        System.out.print("수정할 고객 번호를 입력해주세요.");
        int no = scanner.nextInt();

        boolean found = false;
        for (Customer customer : customers) {
            if (customer.getNo() == no) {
                scanner.nextLine();
                System.out.print("이름: ");
                String name = scanner.nextLine();
                System.out.print("주소: ");
                String address = scanner.nextLine();
                System.out.print("핸드폰번호: ");
                String phoneNumber = scanner.nextLine();

                customer.setName(name);
                customer.setAddress(address);
                customer.setPhoneNumber(phoneNumber);

                found = true;
                System.out.println("고객 정보를 수정하였습니다.");
                break;
            }
        }

        if (!found) {
            System.out.println("고객 정보를 찾을 수 없습니다.");
        }
    }

    public static void main(String[] args) {
        CustomerProgram program = new CustomerProgram();
        program.start();
    }
}
