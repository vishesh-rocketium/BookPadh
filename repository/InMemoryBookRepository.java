package repository;

import java.util.*;
import java.util.stream.Collectors;
import Interfaces.IBookRepository;
import Utility.DatasetReader;
import models.Book;

public class InMemoryBookRepository implements IBookRepository {
    private List<Book> books;

    public InMemoryBookRepository() {
        this.books = new ArrayList<>();
    }

    public InMemoryBookRepository(List<Book> books) {
        this.books = new ArrayList<>(books);
    }

    public void loadBooksFromCSV(String filename) {
        this.books = DatasetReader.readDataset(filename);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public List<Book> getBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByRating(double rating) {
        return books.stream()
                .filter(book -> book.getUserRating() == rating)
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByGenre(String genre) {
        return books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByYear(int year) {
        return books.stream()
                .filter(book -> book.getYear() == year)
                .collect(Collectors.toList());
    }

    public List<String> getAllAuthors() {
        return books.stream()
                .map(Book::getAuthor)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public int getTotalBooksByAuthor(String author) {
        return (int) books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .count();
    }

    public Book getBookByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(book.getTitle())) {
                books.set(i, book);
                break;
            }
        }
    }

    public void deleteBook(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    public int getTotalBooks() {
        return books.size();
    }

    public double getAverageRating() {
        return books.stream()
                .mapToDouble(Book::getUserRating)
                .average()
                .orElse(0.0);
    }

    public double getAveragePrice() {
        return books.stream()
                .mapToDouble(Book::getPrice)
                .average()
                .orElse(0.0);
    }
}