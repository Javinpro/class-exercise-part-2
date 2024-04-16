import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PetShop {
    public static String toTitle(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder converted = new StringBuilder();
        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }

    public static void main(String[] args) {
        ArrayList<Dog> dogList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("\n Enter your dog's details " + (i + 1) + ":");
                //input size dengan validasi
                String size;
                while (true) {
                    System.out.print("Size (small/medium/large) : ");
                    size = scanner.nextLine().toLowerCase();
                    if (size.equals("small") || size.equals("medium") || size.equals("large")) {
                        break;
                    } else {
                        System.out.println("Invalid size. Please enter 'small', 'medium', or 'large'.");
                    }
                }

                //input name dengan validasi 
                String name;
                while (true) {
                    System.out.print("Name  (enter your dog's nickname) : ");
                    name = scanner.nextLine();
                    if (name.matches("[a-zA-z]+")){
                        break;
                    } else {
                        System.out.println("Invalid name. Please enter letters only.");
                    }
                }

                System.out.print("Dog's Breed (enter your dog's breed) : ");
                String type = scanner.nextLine();
                System.out.print("Is it done eating? (true/false) : ");
                boolean doneEating = scanner.nextBoolean();
                scanner.nextLine(); // Consume newline

                // depending on the type, create the respective Dog object
                Dog dog;
                switch (type.toLowerCase()) {
                    case "anjing kampung":
                        dog = new AnjingKampung(size, name, type, doneEating);
                        break;
                    case "beagle":
                        dog = new Beagle(size, name, type, doneEating);
                        break;
                    case "bulldog":
                        dog = new Bulldog(size, name, type, doneEating);
                        break;
                    case "cihuahua":
                        dog = new Cihuahua(size, name, type, doneEating);
                        break;
                    case "dachsund":
                        dog = new Dachsund(size, name, type, doneEating);
                        break;
                    case "germansheppherd":
                        dog = new GermanSheppherd(size, name, type, doneEating);
                        break;
                    case "golden":
                        dog = new Golden(size, name, type, doneEating);
                        break;
                    case "pomeranian":
                        dog = new Pomeranian(size, name, type, doneEating);
                        break;
                    case "poodle":
                        dog = new Poodle(size, name, type, doneEating);
                        break;
                    case "shihtzu":
                        dog = new ShihTzu(size, name, type, doneEating);
                        break;
                    default:
                        System.out.println("Invalid dog type. Please input your dog description correctly !");
                        i--;
                        continue; // skip to next iteration of the loop
                }

                dogList.add(dog);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input format. Please enter correct format.");
                scanner.nextLine(); // Clear the input buffer
                i--; // Re-try current iteration
            }
        }

        // Calculate column widths dynamically
        int[] columnWidths = {4, 20, 20, 8, 18, 30}; // Initial column widths
        for (Dog dog : dogList) {
            columnWidths[0] = Math.max(columnWidths[0], String.valueOf(dogList.size()).length() + 2); // No.
            columnWidths[1] = Math.max(columnWidths[1], toTitle(dog.getName()).length() + 2); // Name
            columnWidths[2] = Math.max(columnWidths[2], toTitle(dog.getType_dog()).length() + 2); // Type
            columnWidths[3] = Math.max(columnWidths[3], dog.getSize().length() + 2); // Size
            columnWidths[4] = Math.max(columnWidths[4], (dog.getJadwalMakan() + " times a day").length() + 2); // Schedule
            columnWidths[5] = Math.max(columnWidths[5], "Status".length() + 2); // Status
        }

        // Print the header
        printHeader(columnWidths);

        // Print the details of all dogs
        try {
            FileWriter writer = new FileWriter("dog_list.txt");
            writeHeader(writer, columnWidths);
            for (int i = 0; i < dogList.size(); i++) {
                Dog dog = dogList.get(i);
                writeTableRow(writer, i + 1, toTitle(dog.getName()), toTitle(dog.getType_dog()), dog.getSize(),
                        dog.getJadwalMakan() + " time a day", dog.doneEat(), columnWidths);
            }
            writeSeparator(writer, columnWidths);
            writer.close();
            System.out.println("Table written to file 'dog_list.txt'");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        scanner.close(); // close the scanner
    }

    private static void printHeader(int[] columnWidths) {
        printSeparator(columnWidths);
        System.out.printf("| %" + columnWidths[0] + "s | %" + columnWidths[1] + "s | %" + columnWidths[2] + "s | %" + columnWidths[3] + "s | %" + columnWidths[4] + "s | %" + columnWidths[5] + "s |%n",
                "", "", "", "Welcome to our PetShop", "", "");
        System.out.printf("| %" + columnWidths[0] + "s | %" + columnWidths[1] + "s | %" + columnWidths[2] + "s | %" + columnWidths[3] + "s | %" + columnWidths[4] + "s | %" + columnWidths[5] + "s |%n",
                "No.", "Name", "Breed Dog", "Size", "Eat", "Status");
        printSeparator(columnWidths);
    }

    private static void printTableRow(int number, String name, String type, String size, String schedule, String status, int[] columnWidths) {
        System.out.printf("| %" + columnWidths[0] + "d | %" + columnWidths[1] + "s | %" + columnWidths[2] + "s | %" + columnWidths[3] + "s | %" + columnWidths[4] + "s | %" + columnWidths[5] + "s |%n",
                number, name, type, size, schedule, status);
    }

    private static void printSeparator(int[] columnWidths) {
        System.out.print("+");
        for (int width : columnWidths) {
            System.out.print("-".repeat(width + 2) + "+");
        }
        System.out.println();
    }

    private static void writeHeader(FileWriter writer, int[] columnWidths) throws IOException {
        String welcomeText = "Welcome to our PetShop";
        int welcomeLength = welcomeText.length();
        int totalLength = Arrays.stream(columnWidths).sum() + columnWidths.length * 3 - 1;
        int padding = (totalLength - welcomeLength) / 2;
        writer.write(String.format("+%" + (totalLength - 1) + "s+%n", "").replace(' ', '-'));
        writer.write(String.format("|%" + (totalLength - 1) + "s|%n", "").replace(' ', ' '));
        writer.write(String.format("|%" + padding + "s%s%" + padding + "s|%n", "", welcomeText, ""));
        writer.write(String.format("|%" + (totalLength - 1) + "s|%n", "").replace(' ', ' '));
        writer.write(String.format("+%" + (totalLength - 1) + "s+%n", "").replace(' ', '-'));
        writer.write(String.format("| %" + columnWidths[0] + "s | %" + columnWidths[1] + "s | %" + columnWidths[2] + "s | %" + columnWidths[3] + "s | %" + columnWidths[4] + "s | %" + columnWidths[5] + "s |%n",
                "No.", "Name Dog", "Breed Dog", "Size", "Eat", "Status"));
        writeSeparator(writer, columnWidths);
    }
        
    private static void writeTableRow(FileWriter writer, int number, String name, String type, String size, String schedule, String status, int[] columnWidths) throws IOException {
        writer.write(String.format("| %" + columnWidths[0] + "d | %" + columnWidths[1] + "s | %" + columnWidths[2] + "s | %" + columnWidths[3] + "s | %" + columnWidths[4] + "s | %" + columnWidths[5] + "s |%n",
                number, name, type, size, schedule, status));
    }

    private static void writeSeparator(FileWriter writer, int[] columnWidths) throws IOException {
        writer.write("+");
        for (int width : columnWidths) {
            writer.write("-".repeat(width + 2) + "+");
        }
        writer.write("\n");
    }
}
