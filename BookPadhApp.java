import java.util.*;
import services.*;
import Interfaces.*;
import repository.InMemoryBookRepository;

public class BookPadhApp {
    private IBookService bookService;
    private Scanner scanner;

    private IBookRepository repository;

    public BookPadhApp() {
        this.repository = new InMemoryBookRepository();
        this.bookService = new BookService(repository);
        this.scanner = new Scanner(System.in);
    }

    public void initializeData(String csvFilename) {
        if (repository instanceof InMemoryBookRepository) {
            ((InMemoryBookRepository) repository).loadBooksFromCSV(csvFilename);
            System.out.println("Data loaded successfully from " + csvFilename);
        }
    }

    public void run() {
        System.out.println("=== Welcome to BookPadh ===");
        System.out.println("Your personal book management system");

        while (true) {
            displayMenu();
            int choice = getValidChoice();

            switch (choice) {
                case 1:
                    bookService.displayAllBooks();
                    break;
                case 2:
                    bookService.displayAllAuthors();
                    break;
                case 3:
                    searchBooksByAuthor();
                    break;
                case 4:
                    searchBooksByRating();
                    break;
                case 5:
                    searchBooksByGenre();
                    break;
                case 6:
                    searchBooksByYear();
                    break;
                case 7:
                    searchBooksByPriceRange();
                    break;
                case 8:
                    bookService.displayBookStatistics();
                    break;
                case 9:
                    displayTopRatedBooks();
                    break;
                case 10:
                    displayMostExpensiveBooks();
                    break;
                case 11:
                    searchBooks();
                    break;
                case 12:
                    viewBookDetails();
                    break;
                case 0:
                    System.out.println("Thank you for using BookPadh!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void displayMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Display all books");
        System.out.println("2. Display all authors");
        System.out.println("3. Search books by author");
        System.out.println("4. Search books by rating");
        System.out.println("5. Search books by genre");
        System.out.println("6. Search books by year");
        System.out.println("7. Search books by price range");
        System.out.println("8. Display book statistics");
        System.out.println("9. Display top rated books");
        System.out.println("10. Display most expensive books");
        System.out.println("11. Search books (title/author/genre)");
        System.out.println("12. View book details");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getValidChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void searchBooksByAuthor() {
        System.out.print("Enter author name: ");
        scanner.nextLine(); 
        String author = scanner.nextLine();
        bookService.displayBooksByAuthor(author);
    }

    private void searchBooksByRating() {
        System.out.print("Enter rating (0.0-5.0): ");
        double rating = scanner.nextDouble();
        bookService.displayBooksByRating(rating);
    }

    private void searchBooksByGenre() {
        System.out.print("Enter genre: ");
        scanner.nextLine(); 
        String genre = scanner.nextLine();
        bookService.displayBooksByGenre(genre);
    }

    private void searchBooksByYear() {
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        bookService.displayBooksByYear(year);
    }

    private void searchBooksByPriceRange() {
        System.out.print("Enter minimum price: $");
        double minPrice = scanner.nextDouble();
        System.out.print("Enter maximum price: $");
        double maxPrice = scanner.nextDouble();
        bookService.displayBooksInPriceRange(minPrice, maxPrice);
    }

    private void displayTopRatedBooks() {
        System.out.print("Enter number of top books to display: ");
        int count = scanner.nextInt();
        bookService.displayTopRatedBooks(count);
    }

    private void displayMostExpensiveBooks() {
        System.out.print("Enter number of expensive books to display: ");
        int count = scanner.nextInt();
        bookService.displayMostExpensiveBooks(count);
    }

    private void searchBooks() {
        System.out.print("Enter search term: ");
        scanner.nextLine(); 
        String searchTerm = scanner.nextLine();
        bookService.displaySearchResults(searchTerm);
    }

    private void viewBookDetails() {
        System.out.print("Enter book title: ");
        scanner.nextLine(); 
        String title = scanner.nextLine();
        bookService.displayBookDetails(title);
    }
    public static void main(String[] args) {
        BookPadhApp app = new BookPadhApp();

        String csvFile = "bestsellers with categories.csv";
        app.initializeData(csvFile);

        app.run();
    }
}