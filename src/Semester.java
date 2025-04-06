import java.util.LinkedList;
import java.util.Scanner;
import java.time.LocalDate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



public class Semester implements Serializable{
    private static final long serialVersionUID = 523701535546027499L; // Define serialVersionUID
    private String term;
    private int year;
    private float overall_score;
    private LinkedList<Course> courses;
    
    // default constructor
    public Semester() {
        this.term = "default";
        this.year = 2025;
        this.overall_score = 100;
        this.courses = new LinkedList<>();
    }

    // Getters and Setters 
    public String getTerm() {
          return term;
    }
    public void setTerm() {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        if (month >= 9 && month <= 12) {
            this.term = "Fall";
        }
        else if (month >= 1 && month <= 5) {
            this.term = "Spring";
        } else {
            System.out.println("We do not support winter or summer classes. Take a break learn on your own. Just chill...");
        }
    }
    public int getYear() {
        return year;
    }
    public void setYear() {
        LocalDate currentDate = LocalDate.now();
        int yr = currentDate.getYear();
        this.year = yr;
    }
    
    public float getOverallScore() {
        return Math.round(overall_score);
    }
    public void setOverallScore(float overall_score) {
        this.overall_score = overall_score;
    }

    /*------------------------------------- Class Methods -------------------------------------*/

    public void addCourse(Course course) {
        courses.add(course);
    }

    // removes field from course if it has the same name specified
    public boolean removeCourse(String name) {
        return courses.removeIf( course->course.getName().equalsIgnoreCase(name));
    }
    
    // creates new field based on user input
    public static Semester createSemester(Scanner scanner) {
        Semester semester = new Semester();
        semester.setTerm();
        semester.setYear();
        return semester;
    }

    public void menu(Scanner scanner) {
        boolean flag = true;

        while(flag) {
            System.out.println("\nSemester Menu:");
            System.out.println("1. Add Course");
            System.out.println("2. Delete Course ");
            System.out.println("3. Edit Course ");
            System.out.println("4. Display Semester ");
            System.out.println("5. Save");
            System.out.println("6. Load");
            System.out.println("7. Exit \n");
            System.out.println("Pick an Option: ");

            // if scanner detects token is not an integer 
            if(!scanner.hasNextInt()) {
                System.out.println("Invalid Option, please enter an integer (i.e 1 ) \n");
                scanner.next();
                continue;
            }
            
            // otherwise it allows a safe reading of integer token
            int option = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");
            switch (option) {
                case 1:
                    System.out.println("Fill out Course Properties");
                    Course new_course = Course.createCourse(scanner);
                    addCourse(new_course);
                    break;
                case 2:
                    boolean found = false;
                    while(!found) {
                        System.out.println("Enter the field's name you want to remove(exit: to exit): ");
                        String input = scanner.nextLine();
                        if(input.equals("exit")) {
                            break;
                        }

                        if(removeCourse(input)) {
                            found = true;
                            break;
                        } else {
                            System.out.println("Course not found. Please ensure correct spelling!");
                        }
                    }
                    break;

                case 3:
                    listCourses();
                    found = false;
                    while(!found) {
                        System.out.println("Enter the Course you want to edit(exit: to exit): ");
                        String input = scanner.nextLine();
                        if(input.equals("exit")) {
                            break;
                        }
                        for (Course course: courses) {
                            if(course.getName().equalsIgnoreCase(input)) {
                                course.menu(scanner);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Course not found. Please ensure correct spelling!");
                        }
                    }

                    break;
                case 4:
                    // only calculates when the use wishes to see it
                    // much faster than original approach of calc when it whenever new is added 
                    calcTotalScore();
                    show();
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    load();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    // save method
                    flag = false;
                    break;
            }
        }
        
    }

    public void listCourses() {
        System.out.println("Courses:");
        for (Course course : courses) {
            System.out.println("Name: " + course.getName());
            System.out.println("Overall Score: " + course.getOverallScore());
            System.out.println("\n");
        }
        System.out.println("\n");
    }

    // displays fields information to user
    public void show() {
        System.out.println(getTerm() +" Semester " + getYear());
        System.out.println("Semester Grade: " + getOverallScore() +"%\n");
        listCourses();
    }

    public void calcTotalScore() {

        if(courses.size() > 0 ) {
            int sum = 0;
            for (Course course: courses) {
                sum += course.getOverallScore();
            }
            setOverallScore(sum / courses.size()); 
        } 

        // TODO: When implemented the drop scores into the calculation there is multiple approaches
        // 1. While Inserting maintain ascending order, then just start after dropPolicy to drop those scores
        // 2. Insert at the end always, then sort in ascneding order, then start after droppolicy
        // 3. insert at end , do not sort, iterate through array summing all vaues abnd saving minium values in array of size dropPolicy then at the end subract by the arrays total and devide by ther sie of scores-dropPoloicy
    }

    public void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("semester.dat"))) {
            out.writeObject(this);
            System.out.println("Yout semester has been saved!");
        } catch (IOException e) {
            System.out.println("Error saving semester: " + e.getMessage());
        }
    }

    public void load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("semester.dat"))) {
            Semester loadedSemester = (Semester) in.readObject();
            this.term = loadedSemester.term;
            this.year = loadedSemester.year;
            this.overall_score = loadedSemester.overall_score;
            this.courses = loadedSemester.courses;
            System.out.println("Yout semester has been loaded!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
    }


  







}
