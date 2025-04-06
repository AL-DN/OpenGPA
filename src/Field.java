import java.util.LinkedList;
import java.util.Scanner;

public class Field {
    private String name;
    private float weight;
    private int dropPolicy;
    private float overall_score;
    private LinkedList<Float> scores;
    
    // default contructor
    public Field() {
        this.name = "default";
        this.weight = 0;
        this.dropPolicy = 0;
        this.overall_score = 100;
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
        return Math.round(weight);
    }
    public void setWeight(Scanner scanner) {
        boolean valid = false;
        float num = 0;

        while(!valid) {
            if (scanner.hasNextFloat()) {
                num = scanner.nextFloat();
                scanner.nextLine();
                if ( num > 0 && num <= 1) {
                    this.weight = num;
                    valid = true;
                } else {
                    System.out.println("Invalid input. Please ensure the weight is between 0 and 1.");
                }
            } else {
                System.out.println("Invalid input. Please enter a float.");
                scanner.next(); 
            }
        }  
        
    }
    public int getDropPolicy() {
        return dropPolicy;
    }
    public void setDropPolicy(Scanner scanner) {
        boolean valid = false;
        int num = 0;

        while(!valid) {
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                scanner.nextLine();
                if ( num >= 0 ) {
                    this.dropPolicy = num;
                    valid = true;
                } else {
                    System.out.println("Invalid input. Please ensure the drop policy is above 0. ");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); 
            }
        }
    }

    public float getOverallScore() {
        return Math.round(overall_score);
    }
    public void setOverallScore(float overall_score) {
        this.overall_score = overall_score;
    }

    /*------------------------------------- Class Methods -------------------------------------*/

    public void addScore(Scanner scanner) {
        boolean valid = false;
        // needs to be flaot in case of scores with decimals
        float score = 0;

        while(!valid) {
            if (scanner.hasNextFloat()) {
                score = scanner.nextFloat();
                scanner.nextLine();
                if ( score >= 0 && score <=100 ) {
                    scores.add(score);                    
                    valid = true;
                } else {
                    System.out.println("Invalid input. Please ensure score is between 0 and 100. ");
                }
            } else {
                System.out.println("Invalid input. Please enter a float.");
                scanner.next(); 
            }
        }
        
    }

    public void removeScore(float score) {
        scores.remove(Float.valueOf(score));
    }
    
    
    // creates new field based on user input
    public static Field createField(Scanner scanner) {
        Field field = new Field();
        System.out.println("What is the name of the field? (i.e Quiz, Homework,etc): ");
        field.setName(scanner.nextLine());
        System.out.println("What is the weight? (i.e 0.2): ");
        field.setWeight(scanner);
        System.out.println("How many are you allowed to drop?: ");
        field.setDropPolicy(scanner);
        return field;
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

            if(!scanner.hasNextInt()) {
                System.out.println("Invalid Option, please enter an integer (i.e 1 ) \n");
                scanner.next();
                continue;
            }

            System.out.println("Pick an Option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\n");
            switch (option) {
                case 1:
                    System.out.println("Enter new name: ");
                    setName(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Enter new weight: ");
                    setWeight(scanner);
                    break;
                case 3:
                    System.out.println("Enter new drop policy: ");
                    setDropPolicy(scanner);
                    break;
                case 4:
                    System.out.println("Enter score to add: ");
                    addScore(scanner);
                    break;
                case 5:
                    System.out.println("Enter score to remove: ");
                    removeScore(scanner.nextFloat());
                    scanner.nextLine(); 
                    break;
                case 6:
                    // only calculates when the use wishes to see it
                    // much faster than original approach 
                    calcTotalScore();
                    show();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    flag = false;
                    break;
            }
        }
    }

    public void listScores() {
        System.out.println("Scores: \t");
        int idx = 0;
        for (Float score : scores) {
            System.out.println("Score "+idx+": "+score);
            idx++;
        }
    }

    // displays fields information to user
    public void show() {
        System.out.println("Name: " + getName());
        System.out.println("Weight: " + getWeight()*100 + "%");
        System.out.println("Drop Policy: " + getDropPolicy());
        System.out.println("Overall Score: " +getOverallScore()+ "%");
        listScores();
        System.out.println("\n");
        
    }

    public void calcTotalScore() {

        if(scores.size() > 0 ) {
            float sum = 0;
            for (float score: scores) {
                sum += score;
            }
            setOverallScore(sum/scores.size()); 
        } 
        
        //TODO: When implemented the drop scores into the calculation there is multiple approaches
        // 1. While Inserting maintain ascending order, then just start after dropPolicy to drop those scores
        // 2. Insert at the end always, then sort in ascneding order, then start after droppolicy
        // 3. insert at end , do not sort, iterate through array summing all vaues abnd saving minium values in array of size dropPolicy then at the end subract by the arrays total and devide by ther sie of scores-dropPoloicy
    }








}
