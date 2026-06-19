package com.expense_tracker;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

public class ExpenseStorage {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                @Override
                public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(src.toString());
                }
            })
            .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                @Override
                public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                        throws JsonParseException {
                    return LocalDate.parse(json.getAsString());
                }
            })
            .create();
    private final String FILE_PATH = "expenses.json";

    public void save(Map<Integer, Expense> expenses) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            // convert list to JSON and write to a file
            gson.toJson(expenses, writer);
        } catch (IOException e) {
            System.out.println("An error occurred while saving a file: " + e.getMessage());
        }
    }

    public Map<Integer, Expense> loadExpenses() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new HashMap<>();
        }

        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<Map<Integer, Expense>>(){}.getType();

            Map<Integer, Expense> result = gson.fromJson(reader, type);

            // if file is empty, result will be null -> return empty map
            return result != null ? result : new HashMap<>();

        } catch (IOException e) {
            System.out.println("An error occurred while reading file: " + e.getMessage());
            return new HashMap<>();
        }
    }
}
