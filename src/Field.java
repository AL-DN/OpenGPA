import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Field implements Serializable {
    // class members
    private String name;
    private float weight;
    private int dropPolicy;
    private float overall_score;
    private LinkedList<Float> scores;

    // default constructor
    public Field() {
        this.name = "default";
        this.weight = 0;
        this.dropPolicy = 0;
        this.overall_score = 100;
        this.scores = new LinkedList<>();
    }

    /*------------------------------------- Getters/Setters -------------------------------------*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return Math.round(weight);
    }

    // sets the weight of the respective field
    public void setWeight(Scanner scanner) {
        boolean valid = false;
        float num = 0;
        // persists until user enters valid weight
        while (!valid) {
            // value must be a float (avoids the InputMismatchException)
            if (scanner.hasNextFloat()) {
                num = scanner.nextFloat();
                scanner.nextLine();
                if (num > 0 && num <= 1) {
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

    // sets the number of scores that can be dropped in its respective field
    public void setDropPolicy(Scanner scanner) {
        boolean valid = false;
        int num = 0;
        // persists until user enters valid # of scores to drop
        while (!valid) {
            // ensures score is a float (avoids the InputMismatchException)
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                scanner.nextLine();
                // # of dropped scores must be positive
                if (num >= 0) {
                    this.dropPolicy = num;
                    valid = true; // allows exit of loop once all checks are made
                } else {
                    System.out.println("Invalid input. Please ensure the drop policy is above 0. ");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
    }

    // returns the rounded overall score
    public float getOverallScore() {
        return Math.round(overall_score);
    }

    // saves overall score as float
    public void setOverallScore(float overall_score) {
        this.overall_score = overall_score;
    }

    /*------------------------------------- Class Methods -------------------------------------*/

    public void addScore(Scanner scanner) {
        boolean valid = false;
        float score = 0;

        // persists until user enters valid score
        while (!valid) {
            // ensures score is a float (avoids the InputMismatchException)
            if (scanner.hasNextFloat()) {
                score = scanner.nextFloat();
                scanner.nextLine();
                // ensures score in between 0 and 100
                if (score >= 0 && score <= 100) {
                    scores.add(score);
                    valid = true; // allows exiting of while loop after all checks
                } else {
                    System.out.println("Invalid input. Please ensure score is between 0 and 100. ");
                }
            } else {
                System.out.println("Invalid input. Please enter a float.");
                scanner.next(); // clears invalid input and prepares for next iteration
            }
        }

    }

    // removes first instance of score in scores given to removeScore()
    public void removeScore(float score) {
        scores.remove(Float.valueOf(score));
    }

    // creates new field based on user input
    // must be static to avoid deadlock
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

    // provides UI to interact with the field class
    public void menu(Scanner scanner) {
        boolean flag = true;

        while (flag) {
            System.out.println("\nField Menu:");
            System.out.println("1. Edit Name");
            System.out.println("2. Edit Weight");
            System.out.println("3. Edit Drop Policy");
            System.out.println("4. Add Score to Field");
            System.out.println("5. Delete Score ");
            System.out.println("6. Display Field ");
            System.out.println("7. Exit \n");
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

    // lists and numbers all scores in field instance
    public void listScores() {
        System.out.println("Scores: \t");
        int idx = 0;
        for (Float score : scores) {
            System.out.println("Score " + idx + ": " + score);
            idx++;
        }
    }

    // displays fields information to user
    public void show() {
        System.out.println("Name: " + getName());
        System.out.println("Weight: " + getWeight() * 100 + "%");
        System.out.println("Drop Policy: " + getDropPolicy());
        System.out.println("Overall Score: " + getOverallScore() + "%");
        listScores();
        System.out.println("\n");

    }

    // updates overall score
    public void calcTotalScore() {
        Collections.sort(scores);
        if (scores.size() > 0) {
            float sum = 0;
            // skip dropPolicy amount of lowest scores
            int start = dropPolicy;

            for (int i = start; i < scores.size(); i++) {
                sum += scores.get(i);
            }

            int num_scores_used = scores.size() - dropPolicy;
            // if not all the scores are dropped use adjusted amount as divisor
            if (num_scores_used > 0) {
                setOverallScore(sum / num_scores_used);
            } else {
                setOverallScore(100);
            }
        }

    }

}
