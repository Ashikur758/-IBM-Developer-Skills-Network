import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileSystemManager {

    private File currentDirectory;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    public FileSystemManager() {
        currentDirectory = new File(System.getProperty("user.dir"));
        scanner = new Scanner(System.in);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public void start() {
        System.out.println("Welcome to the File System Manager!");
        System.out.println("Type 'help' for commands.");

        boolean running = true;

        while (running) {
            System.out.print(currentDirectory.getAbsolutePath() + "> ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                running = false;
            } else {
                running = processCommand(command);
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }

    private boolean processCommand(String command) {
        if (command.isEmpty()) return true;

        String[] parts = command.split("\\s+", 2);
        String cmd = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        switch (cmd) {

            case "help":
                help();
                break;

            case "ls":
                list();
                break;

            case "pwd":
                System.out.println(currentDirectory.getAbsolutePath());
                break;

            case "cd":
                cd(args);
                break;

            case "mkdir":
                mkdir(args);
                break;

            case "touch":
                touch(args);
                break;

            case "rm":
                rm(args);
                break;

            case "rename":
                rename(args);
                break;

            case "find":
                find(args);
                break;

            case "info":
                info(args);
                break;

            default:
                System.out.println("Unknown command");
        }

        return true;
    }

    private void help() {
        System.out.println("Commands:");
        System.out.println("ls, cd, pwd, mkdir, touch, rm, rename, find, info, exit");
    }

    private void list() {
        File[] files = currentDirectory.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("Empty directory");
            return;
        }

        for (File f : files) {
            System.out.println((f.isDirectory() ? "[DIR] " : "[FILE] ") + f.getName());
        }
    }

    private void cd(String dir) {
        if (dir.isEmpty()) return;

        File newDir = new File(currentDirectory, dir);

        if (dir.equals("..")) {
            File parent = currentDirectory.getParentFile();
            if (parent != null) currentDirectory = parent;
        } else if (newDir.exists() && newDir.isDirectory()) {
            currentDirectory = newDir;
        } else {
            System.out.println("Directory not found");
        }
    }

    private void mkdir(String name) {
        if (name.isEmpty()) return;

        File f = new File(currentDirectory, name);

        if (f.mkdir()) {
            System.out.println("Folder created");
        } else {
            System.out.println("Failed");
        }
    }

    private void touch(String name) {
        if (name.isEmpty()) return;

        File f = new File(currentDirectory, name);

        try {
            if (f.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("Already exists");
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private void rm(String name) {
        if (name.isEmpty()) return;

        File f = new File(currentDirectory, name);

        if (f.delete()) {
            System.out.println("Deleted");
        } else {
            System.out.println("Failed");
        }
    }

    private void rename(String args) {
        String[] p = args.split("\\s+", 2);
        if (p.length < 2) return;

        File oldF = new File(currentDirectory, p[0]);
        File newF = new File(currentDirectory, p[1]);

        if (oldF.renameTo(newF)) {
            System.out.println("Renamed");
        } else {
            System.out.println("Failed");
        }
    }

    private void find(String pattern) {
        search(currentDirectory, pattern);
    }

    private void search(File dir, String pattern) {
        File[] files = dir.listFiles();

        if (files == null) return;

        for (File f : files) {
            if (f.getName().contains(pattern)) {
                System.out.println(f.getAbsolutePath());
            }

            if (f.isDirectory()) {
                search(f, pattern);
            }
        }
    }

    private void info(String name) {
        File f = new File(currentDirectory, name);

        if (!f.exists()) {
            System.out.println("Not found");
            return;
        }

        System.out.println("Name: " + f.getName());
        System.out.println("Path: " + f.getAbsolutePath());
        System.out.println("Size: " + f.length());
        System.out.println("Last Modified: " + new Date(f.lastModified()));
        System.out.println("Type: " + (f.isDirectory() ? "Directory" : "File"));
    }

    public static void main(String[] args) {
        new FileSystemManager().start();
    }
}
