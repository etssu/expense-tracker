@echo off
call mvn compile -q
java -cp "target/classes;target/dependency/*" org.example.App %*