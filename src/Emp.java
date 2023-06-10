import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Emp {
    private String name;
    private String phone;
    private String department;
    private String position;
    private double salary;

    public Emp(String name, String phone, String department, String position, double salary) {
        this.name = name;
        this.phone = phone;
        this.department = department;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static void addEmployee(List<Emp> empList) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("이름: ");
        String name = scanner.nextLine();
        System.out.print("연락처: ");
        String phone = scanner.nextLine();
        System.out.print("부서: ");
        String department = scanner.nextLine();
        System.out.print("직급: ");
        String position = scanner.nextLine();
        System.out.print("급여: ");
        double salary = scanner.nextDouble();

        Emp emp = new Emp(name, phone, department, position, salary);
        empList.add(emp);

        System.out.println("성공적으로 추가했습니다.");
    }

    public static void deleteEmployee(List<Emp> empList) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("삭제할 사원의 이름을 입력해주세요");
        String name = scanner.nextLine();

        boolean removed = false;
        for (Emp emp : empList) {
            if (emp.getName().equalsIgnoreCase(name)) {
                empList.remove(emp);
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println("삭제를 성공했습니다. ");
        } else {
            System.out.println("사원번호를 확인할 수 없습니다.");
        }
    }

    public static void displayEmployees(List<Emp> empList) {
        if (empList.isEmpty()) {
            System.out.println("출력할 사원이 없습니다.");
        } else {
            System.out.println("사원 리스트=============");
            for (Emp emp : empList) {
                System.out.println("이름: " + emp.getName());
                System.out.println("연락처: " + emp.getPhone());
                System.out.println("부서: " + emp.getDepartment());
                System.out.println("직급: " + emp.getPosition());
                System.out.println("급여: " + emp.getSalary());
                System.out.println("----------------------");
            }
        }
    }

    public static void updateEmployee(List<Emp> empList) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("수정할 사원의 이름을 입력해주세요: ");
        String name = scanner.nextLine();

        boolean updated = false;
        for (Emp emp : empList) {
            if (emp.getName().equalsIgnoreCase(name)) {
                System.out.print("연락처: ");
                String phone = scanner.nextLine();
                System.out.print("부서: ");
                String department = scanner.nextLine();
                System.out.print("직급: ");
                String position = scanner.nextLine();
                System.out.print("급여: ");
                double salary = scanner.nextDouble();

                emp.setPhone(phone);
                emp.setDepartment(department);
                emp.setPosition(position);
                emp.setSalary(salary);

                updated = true;
                break;
            }
        }

        if (updated) {
            System.out.println("수정하였습니다.");
        } else {
            System.out.println("수정할 사원을 찾을 수 없습니다.");
        }
    }

    public static void saveToFile(List<Emp> empList, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Emp emp : empList) {
                writer.write("이름: " + emp.getName());
                writer.newLine();
                writer.write("연락처: " + emp.getPhone());
                writer.newLine();
                writer.write("부서: " + emp.getDepartment());
                writer.newLine();
                writer.write("직급: " + emp.getPosition());
                writer.newLine();
                writer.write("급여: " + emp.getSalary());
                writer.newLine();
                writer.write("----------------------");
                writer.newLine();
            }
            System.out.println("파일을 저장했습니다==>" + filename);
        } catch (IOException e) {
            System.out.println("다시 확인해주세요.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Emp> empList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("========================= 아래의 메뉴 중 하나를 선택해주세요 =========================");
            System.out.println("1. 사원 추가");
            System.out.println("2. 사원 수정");
            System.out.println("3. 사원 삭제");
            System.out.println("4. 사원 리스트");
            System.out.println("5. 사원 리스트 텍스트파일로 저장");
            System.out.println("6. 종료");
            System.out.print("번호를 입력해주세요 : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee(empList);
                    break;
                case 2:
                    updateEmployee(empList);
                    break;
                case 3:
                    deleteEmployee(empList);
                    break;
                case 4:
                    displayEmployees(empList);
                    break;
                case 5:
                    saveToFile(empList, "회원리스트.txt");
                    break;
                case 6:
                    System.out.println("종료합니다.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("번호를 다시 입력해주세요.");
            }
        }
    }
}