package Utility;
import java.io.*;
import java.util.*;

import models.Book;

public class DatasetReader {
    
    public static List<Book> readDataset(String filename) {
        List<Book> books = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true; // Skip header
            
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header row
                }
                
                // Parse the CSV line
                Book book = parseBookLine(line);
                if (book != null) {
                    books.add(book);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        return books;
    }
    
    private static Book parseBookLine(String line) {
        try {
            // Handle quoted fields that may contain commas
            String[] fields = parseCSVLine(line);
            
            if (fields.length >= 7) {
                String title = fields[0].trim();
                String author = fields[1].trim();
                double userRating = Double.parseDouble(fields[2].trim());
                int reviews = Integer.parseInt(fields[3].trim());
                double price = Double.parseDouble(fields[4].trim());
                int year = Integer.parseInt(fields[5].trim());
                String genre = fields[6].trim();
                
                return new Book(title, author, userRating, reviews, price, year, genre);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numeric values in line: " + line);
        } catch (Exception e) {
            System.err.println("Error parsing line: " + line);
        }
        
        return null; // Return null for malformed lines
    }
    
    private static String[] parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(currentField.toString());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }
        
        // Add the last field
        fields.add(currentField.toString());
        
        return fields.toArray(new String[0]);
    }
} 