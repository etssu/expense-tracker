# Expense Tracker

A small Java application designed for tracking and managing your finances.

## Features

*   **Add Expenses:** Quickly log a new expense with an amount and description.
*   **Update Expenses:** Modify the description or amount of an existing entry using its unique ID.
*   **Delete Expenses:** Remove unwanted or accidental entries.
*   **View Expenses:** Display a clean list of all recorded expenses.
*   **Data Persistence:** Built-in integration with Google Gson for efficient serialization to an `expenses.json` file.


## 🛠 Tech Stack

*   **Language:** Java (JDK 25 or higher)
*   **Libraries:** Google Gson (for JSON handling)
*   **Build Tool:** Maven


## 📦 Installation & Setup

1. Clone the repository:
```bash
   git clone https://github.com/etssu/expense-tracker.git
   cd expense-tracker
```
2. Build the project:
   This will download dependencies (Gson) and compile the source code:
   ```
   mvn clean compile
   ```
3. Run the application:
   You can run it using Maven's exec plugin:
   ```
   mvn exec:java -Dexec.mainClass="Main" -Dexec.args="list"
   ```


## Usage Examples
1. Add a new expense
   ```
   mvn exec:java -Dexec.mainClass="Main" -Dexec.args="add --description "Lunch" --amount 20"
   ```
2. Update an expense
   ```
   mvn exec:java -Dexec.mainClass="Main" -Dexec.args="update --amount 30 --id 2"
   ```
3. May Summary
   ```
   mvn exec:java -Dexec.mainClass="Main" -Dexec.args="summary --month 5"
   ```

