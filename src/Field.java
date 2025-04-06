import java.util.LinkedList;
import java.util.Scanner;

public class Field {
    private String name;
    private float weight;
    private int dropPolicy;
    private float overall_score;
    public LinkedList<Integer> scores;
    
    // Constructor
    public Field(String name, float weight, int dropPolicy) {
        this.name = name;
        this.weight = weight;
        this.dropPolicy = dropPolicy;
        this.scores = new LinkedList<>();
    }

    // Getters and Setters 
    public String getName() {
          return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public int getDropPolicy() {
        return dropPolicy;
    }
    public void setDropPolicy(int dropPolicy) {
        this.dropPolicy = dropPolicy;
    }
    public float getOverallScore() {
        return overall_score;
    }
    public void setOverallScore(float overall_score) {
        this.overall_score = overall_score;
    }

    /*------------------------------------- Class Methods -------------------------------------*/

    
    // creates new field based on user input
    public static Field createField(Scanner scanner) {
        System.out.println("What is the name of the field? (i.e Quiz, Homework,etc): ");
        String name = scanner.nextLine();
        System.out.println("What is the weight? (i.e 0.2 == 20%): ");
        float weight = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("How many can you drop?: ");
        int dropPolicy = scanner.nextInt();
        scanner.nextLine();
        
        return new Field(name,weight,dropPolicy);
    }

    public void menu(Scanner scanner) {
        boolean flag = true;

        while(flag) {
            System.out.println("\nField Menu:");
            System.out.println("1. Edit Name");
            System.out.println("2. Edit Weight");
            System.out.println("3. Edit Drop Policy");
            System.out.println("4. Add Score to Field");
            System.out.println("5. Delete Score ");
            System.out.println("6. Display Field ");
            System.out.println("7. Exit \n");

            System.out.println("Pick an Option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");
            switch (option) {
                case 1:
                    System.out.println("Enter new name: ");
                    this.setName(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Enter new weight: ");
                    this.setWeight(scanner.nextFloat());
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("Enter new drop policy: ");
                    this.setDropPolicy(scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Enter score to add: ");
                    int score = scanner.nextInt();
                    scanner.nextLine();
                    scores.add(score);
                    break;
                case 5:
                    System.out.println("Enter score to remove: ");
                    int remove_score = scanner.nextInt();
                    scanner.nextLine(); 
                    scores.remove(Integer.valueOf(remove_score));
                    break;
                case 6:
                    // only calculates when the use wishes to see it
                    // much faster than original approach 
                    this.calcTotalScore();
                    this.show();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid Option, please enter an integer (i.e 1 ) \n");
                    break;
            }
        }
        
    }

    public void listScores() {
        System.out.println("Scores: \t");
        for (Integer score : scores) {
            System.out.println(score + ", ");
        }
    }

    // displays fields information to user
    public  void show() {
        System.out.println(this.getName() + " | " + this.getWeight() + " | " + this.getDropPolicy() +  " | " + this.getDropPolicy() +"\n");
        listScores();
        
    }

    public void calcTotalScore() {

        int sum = 0;

        for (int score:scores) {
            sum += score;
        }

        this.setOverallScore(sum / scores.size()); 

        //TODO: When implemented the drop scores into the calculation there is multiple approaches
        // 1. While Inserting maintain ascending order, then just start after dropPolicy to drop those scores
        // 2. Insert at the end always, then sort in ascneding order, then start after droppolicy
        // 3. insert at end , do not sort, iterate through array summing all vaues abnd saving minium values in array of size dropPolicy then at the end subract by the arrays total and devide by ther sie of scores-dropPoloicy
    }








}
