# Portobello Manager - OOP Final Project

## Project Overview
Portobello Manager is a Java application designed to manage a second-hand store inventory.
The system allows users to organize items into a hierarchical structure (categories containing products or sub-categories), calculate the total value of the inventory, and export the full catalog to a text file.

This project was developed as a final assignment to demonstrate proficiency in Object-Oriented Programming, Design Patterns, and core Java technologies.

## Features
* **Hierarchical Organization:** Create products and nest them inside categories and sub-categories.
* **Total Calculation:** Automatically calculate the total price of any category (recursive).
* **Input Validation:** Prevents invalid data (e.g., negative prices) using custom exceptions.
* **Data Export:** Saves the entire catalog structure into a flat file (`export_catalogo.txt`).

## Technical Choices & Design Patterns
The architecture is based on specific Design Patterns to ensure maintainability and scalability:

1.  **Composite Pattern** (`CatalogItem` interface, `Product`, `Category`):
    * *Reason:* Used to treat individual objects (Products) and compositions of objects (Categories) uniformly. This allows infinite nesting of categories.
2.  **Factory Pattern** (`CatalogFactory`):
    * *Reason:* Centralizes object creation logic. It keeps the Main class clean and separates the instantiation logic from the business logic.
3.  **Iterator Pattern** (`StoreIterator`):
    * *Reason:* Implemented to traverse the complex tree structure (categories inside categories) as a flat list. This is essential for printing the full list or exporting it to a file line-by-line.
4.  **Exception Shielding** (`CatalogException`):
    * *Reason:* Custom checked exception used to handle specific domain errors (like negative prices) without crashing the application or showing raw stack traces to the user.

## Technologies Used
* **Java SE 21**: Core language.
* **Maven**: Dependency management and build automation.
* **SLF4J / Logback**: Professional logging framework.
* **JUnit 5**: Unit testing framework.

## Setup and Execution
To run this project, you need JDK 21 and Maven installed.

1.  **Build the project:**
    ```bash
    mvn clean install
    ```

2.  **Run Tests:**
    ```bash
    mvn test
    ```

3.  **Run the Application:**
    You can run the `Main` class directly from your IDE (IntelliJ/Eclipse) or use the command line.
    Upon execution, the program will:
    * Create a sample dataset in memory.
    * Print the structure and total value to the console logs.
    * Generate a file named `export_catalogo.txt` in the project root.

## Limitations & Future Work
* **Data Persistence:** Currently, data is stored in memory and lost when the application closes. Future implementation could include a Database connection.
* **User Interface:** The interaction is currently hardcoded in the Main class for demonstration purposes. A future update could add an interactive CLI menu or a GUI.

## UML Diagram
The following UML diagrams illustrate the structural design and the execution flow of the application.

### Class Diagram
This diagram details the **Composite Pattern** implementation. It shows how `Product` and `Category` implement the common interface `CatalogItem`, and how the `CatalogFactory` manages object creation.
![Class Diagram](class_diagram.jpg)

### Architectural Flow
This flowchart demonstrates the separation between the **Data Preparation Phase** (using Factory and Validation) and the **Output Phase** (using Iterator and Java I/O to generate the report).
![Architecture Diagram](architecture_diagram.jpg)
