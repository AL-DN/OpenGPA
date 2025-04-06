import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Semester sm = Semester.createSemester(scanner);
        sm.menu(scanner);
        scanner.close();
    }
}
