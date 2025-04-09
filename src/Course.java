import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;

public class Course implements Serializable {
    // class members
    private String name;
    private float overall_score;
    private LinkedList<Field> fields;

    // default contructor
    public Course() {
        this.name = "default";
        this.overall_score = 100;
        this.fields = new LinkedList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // returns float overall score as int
    public int getOverallScore() {
        return Math.round(overall_score);
    }

    public void setOverallScore(float overall_score) {
        this.overall_score = overall_score;
    }

    /*------------------------------------- Class Methods -------------------------------------*/

    public void addField(Field field) {
        fields.add(field);
    }

    // removes field from course if it has the same name specified
    public void removeField(String name) {
        fields.removeIf(field -> field.getName().equalsIgnoreCase(name));
    }

    // creates new course based on user input
    // must be static to avoid deadlock
    public static Course createCourse(Scanner scanner) {
        Course course = new Course();
        System.out.println("What is the name of the Course? (i.e Physics II): ");
        course.setName(scanner.nextLine());
        return course;
    }

    // UI for course instances
    public void menu(Scanner scanner) {
        boolean flag = true;

        while (flag) {
            System.out.println("\nCourse Menu:");
            System.out.println("1. Edit Name");
            System.out.println("2. Add Field to Course");
            System.out.println("3. Delete Field ");
            System.out.println("4. Edit Field ");
            System.out.println("5. Display Course ");
            System.out.println("6. Exit \n");
            System.out.println("Pick an Option: ");

            // avoids InputMistMatach on subsequent .nextInt()
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid Option, please enter an integer (i.e 1 ) \n");
                scanner.next();
                continue;
            }

            int option = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");
            // invokes methods to do user specified actions
            switch (option) {
                case 1:
                    System.out.println("Enter new name: ");
                    setName(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Fill out Field Properties");
                    Field new_field = Field.createField(scanner);
                    addField(new_field);
                    break;
                case 3:
                    System.out.println("Enter the field's name you want to remove: ");
                    removeField(scanner.nextLine());
                    break;
                case 4:
                    listFields();
                    boolean found = false;
                    while (!found) {
                        System.out.println("Enter the Field you want to edit(exit: to exit): ");
                        String input = scanner.nextLine();
                        if (input.equals("exit")) {
                            break;
                        }
                        for (Field field : fields) {
                            if (field.getName().equalsIgnoreCase(input)) {
                                field.menu(scanner);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Field not found. Please ensure correct spelling!");
                        }
                    }

                    break;
                case 5:
                    // only calculates when the use wishes to see it
                    // much faster than original approach of calc when it whenever new is added
                    calcTotalScore();
                    show();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    flag = false;
                    break;
            }
        }

    }

    public void listFields() {
        System.out.println("Fields:");
        for (Field field : fields) {
            System.out.println("Name: " + field.getName());
            System.out.println("Weight: " + field.getWeight() * 100 + "%");
            System.out.println("Drop Policy: " + field.getDropPolicy());
            System.out.println("Overall Score: " + field.getOverallScore());
            System.out.println("\n");
        }
        System.out.println("\n");
    }

    // displays fields information to user
    public void show() {
        System.out.println(getName());
        System.out.println("Course Grade: " + getOverallScore() + "%\n");
        listFields();
    }

    // calculates
    public void calcTotalScore() {
        if (fields.size() > 0) {
            int sum = 0;
            int wTotal = 0;
            for (Field field : fields) {
                sum += field.getOverallScore() * field.getWeight();
                wTotal += field.getWeight();
            }

            if (wTotal != 1) {
                System.out.println("Weights do not add up to 1. Please adjust weights or add needed fields!");
            }
            setOverallScore(sum);
        }

    }
}