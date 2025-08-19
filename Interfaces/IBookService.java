package Interfaces;

import java.util.*;
import models.Book;

public interface IBookService {
    void displayAllBooks();    
    void displayBooksByAuthor(String author);
    void displayBooksByRating(double rating);
    void displayBooksAndPricesByAuthor(String author);
    void displayAllAuthors();
    void displayBookDetails(String title);
    void displayBooksByGenre(String genre);
    void displayBooksByYear(int year);
    void displayBookStatistics();
    void displayTopRatedBooks(int count);
    void displayMostExpensiveBooks(int count);
    void displayBooksInPriceRange(double minPrice, double maxPrice);
    List<Book> searchBooks(String searchTerm);
    void displaySearchResults(String searchTerm);
}