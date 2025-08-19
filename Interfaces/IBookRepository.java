package Interfaces;

import java.util.*;
import models.Book;

public interface IBookRepository {
    List<Book> getAllBooks();
    List<Book> getBooksByAuthor(String author);
    List<Book> getBooksByRating(double rating);
    List<Book> getBooksByGenre(String genre);
    List<Book> getBooksByYear(int year);
    List<String> getAllAuthors();
    int getTotalBooksByAuthor(String author);
    Book getBookByTitle(String title);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(String title);
    int getTotalBooks();
    double getAverageRating();
    double getAveragePrice();
}