import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class LibraryManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Book> library = new ArrayList<>();

        library.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925));
        library.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        library.add(new Book("1984", "George Orwell", 1949));

        boolean running = true;

        while (running) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add a book");
            System.out.println("2. View all books");
            System.out.println("3. Search for a book by title");
            System.out.println("4. Check out a book");
            System.out.println("5. Return a book");
            System.out.println("6. Sort books");
            System.out.println("7. View available books only");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (choice) {
                case 1:
                    addBook(scanner, library);
                    break;

                case 2:
                    viewAllBooks(library);
                    break;

                case 3:
                    searchBooks(scanner, library);
                    break;

                case 4:
                    checkOutBook(scanner, library);
                    break;

                case 5:
                    returnBook(scanner, library);
                    break;

                case 6:
                    sortBooks(scanner, library);
                    break;

                case 7:
                    viewAvailableBooks(library);
                    break;

                case 8:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    private static void addBook(Scanner scanner, ArrayList<Book> library) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter year: ");

        int year;
        try {
            year = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid year.");
            return;
        }

        library.add(new Book(title, author, year));
        System.out.println("Book added.");
    }

    private static void viewAllBooks(ArrayList<Book> library) {
        if (library.isEmpty()) {
            System.out.println("Library is empty.");
            return;
        }

        for (int i = 0; i < library.size(); i++) {
            System.out.println((i + 1) + ". " + library.get(i));
        }
    }

    private static void searchBooks(Scanner scanner, ArrayList<Book> library) {
        System.out.print("Enter keyword: ");
        String key = scanner.nextLine().toLowerCase();

        boolean found = false;

        for (Book book : library) {
            if (book.getBookTitle().toLowerCase().contains(key) ||
                book.getAuthor().toLowerCase().contains(key)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found.");
        }
    }

    private static void checkOutBook(Scanner scanner, ArrayList<Book> library) {
        for (int i = 0; i < library.size(); i++) {
            System.out.println((i + 1) + ". " + library.get(i));
        }

        System.out.print("Enter book number: ");

        try {
            int num = Integer.parseInt(scanner.nextLine());

            if (num < 1 || num > library.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            Book b = library.get(num - 1);

            if (b.checkOut()) {
                System.out.println("Checked out successfully.");
            } else {
                System.out.println("Already checked out.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private static void returnBook(Scanner scanner, ArrayList<Book> library) {
        System.out.print("Enter book number: ");

        try {
            int num = Integer.parseInt(scanner.nextLine());

            if (num < 1 || num > library.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            Book b = library.get(num - 1);

            if (b.returnBook()) {
                System.out.println("Returned successfully.");
            } else {
                System.out.println("Book was not checked out.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private static void sortBooks(Scanner scanner, ArrayList<Book> library) {
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Year");
        System.out.print("Choice: ");

        try {
            int c = Integer.parseInt(scanner.nextLine());

            if (c == 1) {
                Collections.sort(library, Comparator.comparing(Book::getBookTitle));
            } else if (c == 2) {
                Collections.sort(library, Comparator.comparing(Book::getAuthor));
            } else if (c == 3) {
                Collections.sort(library, Comparator.comparing(Book::getPublicationYear));
            } else {
                System.out.println("Invalid choice.");
                return;
            }

            System.out.println("Sorted books:");
            viewAllBooks(library);

        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    private static void viewAvailableBooks(ArrayList<Book> library) {
        boolean found = false;

        for (Book b : library) {
            if (b.isAvailable()) {
                System.out.println(b);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available books.");
        }
    }
}
