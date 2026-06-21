# Expense Tracker

A small Java application designed for tracking and managing your finances.
Inspired from the [Expense Tracker](https://roadmap.sh/projects/expense-tracker) project featured from [roadmap.sh](https://roadmap.sh/dashboard).

## Features

*   **Add Expenses:** Quickly log a new expense with an amount and description.
*   **Update Expenses:** Modify the description or amount of an existing entry using its unique ID.
*   **Delete Expenses:** Remove unwanted or accidental entries.
*   **View Expenses:** Display a clean list of all recorded expenses.
*   **Data Persistence:** Built-in integration with Google Gson for efficient serialization to an `expenses.json` file.


## 🛠 Tech Stack

*   **Language:** Java 17 or higher
*   **Libraries:** Google Gson (for JSON handling)
*   **Build Tool:** Maven 3.8.7


## 📦 Installation & Setup

1. Clone the repository:
```bash
   git clone https://github.com/etssu/expense-tracker.git
   cd expense-tracker
```
### 🚀 Run with CLI wrapper (recommended)

For easier usage, a wrapper script is provided.

#### Make it executable (Linux / Git Bash / WSL)
```bash
chmod +x expense-tracker
```
#### Run the application:
```bash
./expense-tracker list
./expense-tracker add --description "Lunch" --amount 20
./expense-tracker update --amount 30 --id 2
./expense-tracker summary --month 5
```

### 🛠 Alternative: Run with Maven

1. Build the project:
   This will download dependencies (Gson) and compile the source code:
   ```
   mvn clean compile
   ```
2. Run the application:
   You can run it using Maven's exec plugin:
   ```
   mvn exec:java -Dexec.mainClass="com.expense_tracker.App" -Dexec.args="list"
   ```

