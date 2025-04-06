import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Course field = Course.createCourse(scanner);
        field.menu(scanner);
        scanner.close();
    }
}
