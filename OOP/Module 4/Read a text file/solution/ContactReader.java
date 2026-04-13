import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ContactReader {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the contacts file:");
        String fileName = scanner.nextLine();

        int contactCount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;

            System.out.println("\n===== CONTACT LIST =====");

            while ((line = reader.readLine()) != null) {

                if (!line.trim().isEmpty()) {

                    String[] parts = line.split(":");

                    if (parts.length == 2) {

                        String name = parts[0].trim();
                        String phoneNumber = parts[1].trim();

                        System.out.println("Contact: " + name + " | Phone: " + phoneNumber);
                        contactCount++;

                    } else {
                        System.out.println("Warning: Skipping invalid line: " + line);
                    }
                }
            }

            reader.close();

            System.out.println("\nTotal contacts read: " + contactCount);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("Reading error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
