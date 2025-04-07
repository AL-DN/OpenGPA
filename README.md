# OpenGPA

**OpenGPA** is the ultimate GPA calculator that lets you track and manage your grades effortlessly. Unlike most calculators, it offers separate fields for quizzes, exams, homework, and more, eliminating the need for tedious manual calculations. Plus, with the ability to save your progress, OpenGPA ensures you never lose track of your GPA throughout the semester. Say goodbye to the hassle and stay organized with OpenGPA!

---

## Table of Contents
1. [Download Stats](#download-stats)
2. [Features](#features)
3. [User Instructions](#user-instructions)
   - [Windows](#windows)
   - [Mac](#mac)
   - [Linux](#linux)
4. [Contributing](#contributing)
5. [Code of Conduct](#code-of-conduct)
6. [Reporting Bugs](#reporting-bugs)
7. [Current Issues](#current-issues)

---

## Download Stats

[![Downloads](https://img.shields.io/github/downloads/AL-DN/OpenGPA/v1.0/total?size=large)](https://github.com/AL-DN/OpenGPA/releases/tag/v1.0)

---

## Features

1. **Create and Save Semester Information**: Users can create and save semester details, allowing them to track multiple semesters.
2. **Populate Courses**: Add courses for each semester and input course-specific information like name and grading policies.
3. **Add Weighted Categories**: Courses can have weighted categories like quizzes, exams, homework, etc., allowing users to track their grades for each component.
4. **Drop Policy**: **OpenGPA** can automatically drop a specified number of the lowest scores for each category when calculating final grades.

---

## User Instructions

To get started with **OpenGPA**, follow these steps:

### Windows

1. **Download and Install Java**: 
   - Visit [Java Downloads](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) and install the appropriate version of Java for Windows.
   - Follow the installation instructions and ensure that `java` is added to your system's PATH environment variable.

2. [Download the latest stable version of OpenGPA](https://github.com/AL-DN/OpenGPA/releases)


3. **Navigate to the Source Code**:
   - In the Terminal, navigate to the project directory:
     ```bash
     cd folder-name/src
     ```
     
4. **Compile App.java**:
   - Once you're inside the src folder, compile App.java:
     ```bash
     javac App.java
     ```
     
5. **Run the Application**:
   - Once you're inside the project folder, run the Main method to start the application:
     ```bash
     java Main
     ```
6. Do not Forget to Save
   - Before exiting the semester menu, save to avoid losing previously added scores, weighted categories, and courses

7. To Re-enter OpenGPA
   - Repeat steps 3-5 and then load previously saved data.

### Mac

1. **Download and Install Java**:
   - Open the Terminal and run the following command to install Java via Homebrew:
     ```bash
     brew install openjdk@11
     ```

2. [Download the latest stable version of OpenGPA](https://github.com/AL-DN/OpenGPA/releases)

3. **Navigate to the Source Code**:
   - In the Terminal, navigate to the project directory:
     ```bash
     cd folder-name/src
     ```
4. **Compile App.java**:
   - Once you're inside the src folder, compile App.java:
     ```bash
     javac App.java
     ```

5. **Run the Application**:
   - Run the program using:
     ```bash
     java Main
     ```
     
6. Do not Forget to Save
   - Before exiting the semester menu, save to avoid losing previously added scores, weighted categories, and courses

7. To Re-enter OpenGPA
   - Repeat steps 3-5 and then load previously saved data.

### Linux

1. **Download and Install Java**:
   - Open the Terminal and install Java using the package manager:
     ```bash
     sudo apt install openjdk-11-jdk
     ```

2. [Download the latest stable version of OpenGPA](https://github.com/AL-DN/OpenGPA/releases)

3. **Navigate to the Source Code**:
   - In the Terminal, navigate to the project directory:
     ```bash
     cd folder-name/src
     ```
     
4. **Compile App.java**:
   - Once you're inside the src folder, compile App.java:
     ```bash
     javac App.java
     ```

5. **Run the Application**:
   - Start the application by running:
     ```bash
     java Main
     ```
     
6. Do not Forget to Save
   - Before exiting the semester menu, save to avoid losing previously added scores, weighted categories, and courses

7. To Re-enter OpenGPA
   - Repeat steps 3-5 and then load previously saved data.

---

## Contributing

Thank you for your interest in contributing to **OpenGPA**! Contributions are always welcome. Please follow the guidelines below to ensure that your contributions can be easily reviewed and merged.

### How to Contribute

1. **Fork the Repository**
    - Start by forking the repository to your own GitHub account. This will allow you to freely make changes without affecting the original codebase.
   
2. **Clone the Forked Repository**
    - After forking the repo, clone it to your local machine:
      ```bash
      git clone https://github.com/your-username/OpenGPA.git
      ```

3. **Create a New Branch**
    - Create a new branch for your work. The branch name should describe the work you’re doing:
      ```bash
      git checkout -b feature-name
      ```

4. **Make Your Changes**
    - Implement your feature, fix, or improvement. Ensure your code follows the project's coding standards and is well-documented.
    - If working on a bug, please make sure it is well-reported and addressed.

5. **Test Your Changes**
    - Run tests locally to ensure your changes do not break existing functionality. We use [pytest](https://pytest.org/) for testing:
      ```bash
      pytest
      ```

6. **Commit Your Changes**
    - Commit your changes with a clear and concise message explaining the purpose of your changes:
      ```bash
      git commit -m "Add feature or fix bug"
      ```

7. **Push Your Changes**
    - Push your changes to your forked repository:
      ```bash
      git push origin feature-name
      ```

8. **Create a Pull Request**
    - Create a pull request (PR) on the original repository. Select your branch and describe the changes you've made.
    - Provide a clear description of the issue you're addressing and the solution you've implemented.

---

## Code of Conduct

This project adheres to the [Contributor Covenant Code of Conduct](https://www.contributor-covenant.org/). By participating in this project, you agree to abide by its terms. Please be respectful and considerate towards others in the community.

---

## Reporting Bugs

If you encounter a bug, please open an issue on the GitHub Issues page. When reporting a bug:
- Provide steps to reproduce the issue.
- Include any relevant logs, error messages, or screenshots.
- Mention the version of Java and any other dependencies you're using.

## Known Issues (Fixable)
1. Does not calculate GPA for semester just a grade for each course 0-100. Would need a credits field and a way to update a letter grade every time grade changes
2. Non aesthetic UI 

---



