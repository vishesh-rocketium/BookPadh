package services;

import java.util.*;
import java.util.stream.Collectors;
import Interfaces.IBookService;
import Interfaces.IBookRepository;
import models.Book;

public class BookService implements IBookService {
    private IBookRepository bookRepository;

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void displayAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        System.out.println("=== All Books ===");
        for (Book book : books) {
            System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
        }
        System.out.println("Total books: " + books.size());
    }

    public void displayBooksByAuthor(String author) {
        List<Book> books = bookRepository.getBooksByAuthor(author);
        if (books.isEmpty()) {
            System.out.println("No books found for author: " + author);
            return;
        }

        System.out.println("=== Books by " + author + " ===");
        for (Book book : books) {
            System.out.println("- " + book.getTitle());
        }
        System.out.println("Total books: " + books.size());
    }

    public void displayBooksByRating(double rating) {
        List<Book> books = bookRepository.getBooksByRating(rating);
        if (books.isEmpty()) {
            System.out.println("No books found with rating: " + rating);
            return;
        }

        System.out.println("=== Books with Rating " + rating + " ===");
        for (Book book : books) {
            System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
        }
        System.out.println("Total books: " + books.size());
    }

    public void displayBooksAndPricesByAuthor(String author) {
        List<Book> books = bookRepository.getBooksByAuthor(author);
        if (books.isEmpty()) {
            System.out.println("No books found for author: " + author);
            return;
        }

        System.out.println("=== Books and Prices by " + author + " ===");
        for (Book book : books) {
            System.out.println("- " + book.getTitle() + ": $" + book.getPrice());
        }
        System.out.println("Total books: " + books.size());
    }

    public void displayAllAuthors() {
        List<String> authors = bookRepository.getAllAuthors();
        if (authors.isEmpty()) {
            System.out.println("No authors found.");
            return;
        }

        System.out.println("=== All Authors ===");
        for (String author : authors) {
            System.out.println("- " + author);
        }
        System.out.println("Total unique authors: " + authors.size());
    }

    public void displayBookDetails(String title) {
        Book book = bookRepository.getBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found: " + title);
            return;
        }

        System.out.println("=== Book Details ===");
        book.printDetails();
    }

    public void displayBooksByGenre(String genre) {
        List<Book> books = bookRepository.getBooksByGenre(genre);
        if (books.isEmpty()) {
            System.out.println("No books found for genre: " + genre);
            return;
        }

        System.out.println("=== Books in Genre: " + genre + " ===");
        for (Book book : books) {
            System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
        }
        System.out.println("Total books: " + books.size());
    }

    public void displayBooksByYear(int year) {
        List<Book> books = bookRepository.getBooksByYear(year);
        if (books.isEmpty()) {
            System.out.println("No books found for year: " + year);
            return;
        }

        System.out.println("=== Books Published in " + year + " ===");
        for (Book book : books) {
            System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
        }
        System.out.println("Total books: " + books.size());
    }

    public void displayBookStatistics() {
        System.out.println("=== Book Statistics ===");
        System.out.println("Total books: " + bookRepository.getTotalBooks());
        System.out.println("Average rating: " + String.format("%.2f", bookRepository.getAverageRating()));
        System.out.println("Average price: $" + String.format("%.2f", bookRepository.getAveragePrice()));
        System.out.println("Total authors: " + bookRepository.getAllAuthors().size());
    }

    public void displayTopRatedBooks(int count) {
        List<Book> books = bookRepository.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        List<Book> topRated = books.stream()
                .sorted((b1, b2) -> Double.compare(b2.getUserRating(), b1.getUserRating()))
                .limit(count)
                .collect(Collectors.toList());

        System.out.println("=== Top " + count + " Rated Books ===");
        for (Book book : topRated) {
            System.out.println(
                    "- " + book.getTitle() + " by " + book.getAuthor() + " (Rating: " + book.getUserRating() + ")");
        }
    }

    public void displayMostExpensiveBooks(int count) {
        List<Book> books = bookRepository.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        List<Book> mostExpensive = books.stream()
                .sorted((b1, b2) -> Double.compare(b2.getPrice(), b1.getPrice()))
                .limit(count)
                .collect(Collectors.toList());

        System.out.println("=== Most Expensive " + count + " Books ===");
        for (Book book : mostExpensive) {
            System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + " ($" + book.getPrice() + ")");
        }
    }

    public void displayBooksInPriceRange(double minPrice, double maxPrice) {
        List<Book> books = bookRepository.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        List<Book> inRange = books.stream()
                .filter(book -> book.getPrice() >= minPrice && book.getPrice() <= maxPrice)
                .sorted((b1, b2) -> Double.compare(b1.getPrice(), b2.getPrice()))
                .collect(Collectors.toList());

        if (inRange.isEmpty()) {
            System.out.println("No books found in price range $" + minPrice + " - $" + maxPrice);
            return;
        }

        System.out.println("=== Books in Price Range $" + minPrice + " - $" + maxPrice + " ===");
        for (Book book : inRange) {
            System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + " ($" + book.getPrice() + ")");
        }
        System.out.println("Total books: " + inRange.size());
    }

    public List<Book> searchBooks(String searchTerm) {
        List<Book> books = bookRepository.getAllBooks();
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        book.getGenre().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }


    public void displaySearchResults(String searchTerm) {
        List<Book> results = searchBooks(searchTerm);
        if (results.isEmpty()) {
            System.out.println("No books found matching: " + searchTerm);
            return;
        }

        System.out.println("=== Search Results for '" + searchTerm + "' ===");
        for (Book book : results) {
            System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + " (" + book.getGenre() + ")");
        }
        System.out.println("Total results: " + results.size());
    }
}