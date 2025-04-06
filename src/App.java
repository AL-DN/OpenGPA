import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Field field = Field.createField(scanner);
        field.menu(scanner);
        scanner.close();
    }
}
