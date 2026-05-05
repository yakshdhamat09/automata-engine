# Automata & Formal Languages Engine ⚙️

A full-stack Java web application designed to visually create, mathematically model, and computationally simulate Deterministic Finite Automata (DFA).

## 🚀 Features
* **Interactive UI:** Create custom states, alphabets, and transitions.
* **Mathematical Enforcement:** Database-level constraints ensure all generated machines strictly adhere to the deterministic rules of a DFA.
* **$O(n)$ Simulation Engine:** A highly optimized algorithmic backend that evaluates strings in linear time.
* **Trace Path Visualization:** Shows the exact path the engine took through the states to reach its accepted or rejected conclusion.

## 🛠️ Technology Stack
* **Backend:** Java 24, Spring Boot 3.3.0
* **Frontend:** HTML5, CSS3, Vanilla JavaScript
* **Database:** MySQL, Hibernate ORM / Spring Data JPA

## ⚙️ How to Run Locally
1. Ensure **MySQL** is running (e.g., via XAMPP) on port `3307` (or update `application.properties` to match your port).
2. Create an empty database named `automata_engine`.
3. Run the Spring Boot application using Maven:
   `.\mvnw spring-boot:run`
4. Open your browser and navigate to `http://localhost:8080/`.

## 📄 Documentation
A full academic report detailing the system architecture, mathematical foundations, and edge-case testing is available in the root directory: `Automata_Engine_Report_Dhamat_Yaksh_M.pdf`.
