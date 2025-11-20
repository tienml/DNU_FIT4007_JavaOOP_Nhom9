# DNU manage student scores and rankings

StudentScoreManager is a Java console application used to manage students, courses, grades, GPA, and ranking.
The system tracks academic performance such as final score, GPA, and ranking by class/faculty.
This project runs on Java, so you need an IDE such as IntelliJ IDEA or Visual Studio Code.

Download an IDE here:

IntelliJ IDEA: https://www.jetbrains.com/idea/download

Visual Studio Code: https://code.visualstudio.com/download

## Instructions

Download with [zip](https://github.com/tienml/DNU_FIT4007_JavaOOP_Nhom9/archive/main.zip) or with cli:

```bash
git clone https://github.com/tienml/DNU_FIT4007_JavaOOP_Nhom9.git
```

## Setup project files

Your project uses CSV files as the local database.
Make sure the following directory exists:

```env
/data/
    students.csv
    courses.csv
    grades.csv
    lecturers.csv
    class_groups.csv
```
If they don’t exist, the application will auto-generate them when running the first time.

## Run the Main Program

You can run the application directly via the CLI.

#### Run using Java CLI (Terminal):
```env
java -cp out cli.Main
```
#### Run in IntelliJ IDEA

1. Open the project in IntelliJ IDEA
2. Go to Project Panel → src → cli → Main.java
3. Right-click the Main class → Run 'Main.main()'

#### Run in Visual Studio Code

1. Install extensions:

```env
Java Extension Pack

Debugger for Java
```
2. Open the project folder

3. Open src/cli/Main.java

4. Click Run ▶️ at the top right