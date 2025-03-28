# Quiz Application

This is a Java-based Quiz Application using Swing for the GUI. The application fetches trivia questions dynamically from The Trivia API and presents them in a multiple-choice format.

## Requirements

- **JDK 21** (Ensure you have Java 21 installed)
- **JSON Library:** `json-20250107.jar` (Required for parsing API responses)

## Setup Instructions

1. **Install JDK 21**\
   Download and install JDK 21 from [Oracle's official website](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) or use OpenJDK.

2. **Download JSON Library**\
   Download `json-20250107.jar` from [Maven Repository](https://repo1.maven.org/maven2/org/json/json/20250107/) and place it in your project's `lib` folder.

3. **Compile and Run the Project**

   ```sh
   javac -cp ".;lib/json-20250107.jar" quiz/application/*.java
   java -cp ".;lib/json-20250107.jar" quiQuiz Application
   This is a Java-based Quiz Application using Swing for the GUI. The application fetches trivia questions dynamically from The Trivia API and presents them in a multiple-choice format.

   Requirements
   JDK 21 (Ensure you have Java 21 installed)

   JSON Library: json-20250107.jar (Required for parsing API responses)

   Setup Instructions
   Install JDK 21
   Download and install JDK 21 from Oracle's official website or use OpenJDK.

   Download JSON Library
   Download json-20250107.jar from Maven Repository and place it in your project's lib folder.

   Compile and Run the Project

   javac -cp ".;lib/json-20250107.jar" quiz/application/*.java
   java -cp ".;lib/json-20250107.jar" quiz.application.Main
   Features
   Dynamic quiz questions fetched via The Trivia API

   Java Swing-based GUI

   Lifeline feature to remove incorrect options

   Score calculation and result display

   Notes
   Ensure you have an active internet connection to fetch trivia questions.

   Modify the API request parameters (difficulty, category, etc.) inside the Quiz class constructor.

   License
   This project is open-source and available for modification and use in educational or personal projects.

   z.application.Main
   ```

## Features

- Dynamic quiz questions fetched via The Trivia API
- Java Swing-based GUI
- Lifeline feature to remove incorrect options
- Score calculation and result display

## Notes

- Ensure you have an active internet connection to fetch trivia questions.
- Modify the API request parameters (difficulty, category, etc.) inside the `Quiz` class constructor.

## License

This project is open-source and available for modification and use in educational or personal projects.

