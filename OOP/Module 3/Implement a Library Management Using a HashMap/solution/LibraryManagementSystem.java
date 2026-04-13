import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;
import java.util.Calendar;
import java.util.Comparator;

public class LibraryManagementSystem {

    static class Book {
        private String title;
        private String author;
        private String genre;
        private int publicationYear;

        public Book(String title, String author, String genre, int publicationYear) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.publicationYear = publicationYear;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getGenre() {
            return genre;
        }

        public int getPublicationYear() {
            return publicationYear;
        }

        @Override
        public String toString() {
            return "Title: " + title +
                    ", Author: " + author +
                    ", Genre: " + genre +
                    ", Publication Year: " + publicationYear;
        }
    }

    private static boolean isValidText(String text) {
        return text != null && !text.trim().isEmpty();
    }

    private static boolean isValidYear(int year) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return year >= 1000 && year <= currentYear;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Book> library = new HashMap<>();

        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add a book");
            System.out.println("2. View all books");
            System.out.println("3. Search for a book");
            System.out.println("4. Remove a book");
            System.out.println("5. View sorted books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {

                System.out.print("Enter ISBN: ");
                String isbn = scanner.nextLine();

                if (library.containsKey(isbn)) {
                    System.out.println("Book already exists!");
                    continue;
                }

                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                if (!isValidText(title)) continue;

                System.out.print("Enter author: ");
                String author = scanner.nextLine();
                if (!isValidText(author)) continue;

                System.out.print("Enter genre: ");
                String genre = scanner.nextLine();
                if (!isValidText(genre)) continue;

                System.out.print("Enter year: ");
                int year;
                try {
                    year = Integer.parseInt(scanner.nextLine());
                    if (!isValidYear(year)) continue;
                } catch (Exception e) {
                    System.out.println("Invalid year");
                    continue;
                }

                library.put(isbn, new Book(title, author, genre, year));
                System.out.println("Book added!");

            } 
            else if (choice.equals("2")) {

                if (library.isEmpty()) {
                    System.out.println("Library is empty");
                } else {
                    for (Map.Entry<String, Book> e : library.entrySet()) {
                        System.out.println("ISBN: " + e.getKey() + ", " + e.getValue());
                    }
                }

            } 
            else if (choice.equals("3")) {

                System.out.print("Search keyword: ");
                String key = scanner.nextLine().toLowerCase();

                boolean found = false;

                for (Map.Entry<String, Book> e : library.entrySet()) {
                    Book b = e.getValue();

                    if (b.getTitle().toLowerCase().contains(key) ||
                        b.getAuthor().toLowerCase().contains(key)) {
                        System.out.println("ISBN: " + e.getKey() + ", " + b);
                        found = true;
                    }
                }

                if (!found) System.out.println("No match found");

            } 
            else if (choice.equals("4")) {

                System.out.print("Enter ISBN to remove: ");
                String isbn = scanner.nextLine();

                if (library.remove(isbn) != null) {
                    System.out.println("Removed successfully");
                } else {
                    System.out.println("Book not found");
                }

            } 
            else if (choice.equals("5")) {

                TreeMap<String, Book> sorted = new TreeMap<>(library);

                System.out.println("Sorted Books:");
                for (Map.Entry<String, Book> e : sorted.entrySet()) {
                    System.out.println("ISBN: " + e.getKey() + ", " + e.getValue());
                }

            } 
            else if (choice.equals("6")) {
                System.out.println("Goodbye!");
                break;
            } 
            else {
                System.out.println("Invalid choice");
            }
        }

        scanner.close();
    }
}
