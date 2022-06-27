package Java;

import Java.Errors.InvalidInputException;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class DVDLibrary {
    private ArrayList<DVD> library;
    private Scanner input;

    public DVDLibrary(String filepath) {
        input = new Scanner(System.in);
        if (filepath != null) {
            library = LoadFromFile(filepath);
        } else {
            library = new ArrayList<DVD>();
        }
        Run();
        try {
            SaveToFile(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void AddDVD() {
        //Get user input for each DVD attribute
        System.out.println("Please enter values for new DVD:");
        try {
            System.out.print("Title: ");
            String titleIn = Input.getUserString(input);
            System.out.print("Release date (format dd/mm/yyyy): ");
            String dateIn = Input.getUserString(input);
            System.out.print("Age rating: ");
            String ageIn = Input.getUserString(input);
            System.out.print("Director's name: ");
            String dirIn = Input.getUserString(input);
            System.out.print("Studio name: ");
            String studioIn = Input.getUserString(input);
            System.out.print("User note: ");
            String noteIn = Input.getUserString(input);
            //If all input is valid, create DVD object and add to library
            library.add(new DVD(titleIn, dateIn, ageIn, dirIn, studioIn, noteIn));
            //& output creation successful
            System.out.println("DVD creation successful!");
        } catch (RuntimeException e) { //if not, output that creation was invalid
            e.printStackTrace();
            System.out.println("DVD creation unsuccessful! Returning to menu...");
        }
    }

    private void RemoveDVD(String byTitle) { //boolean return to exit function when dvd is removed
        //search through library
        Iterator<DVD> it = library.iterator();
        while (it.hasNext()) {
            //if DVD found with title, remove from arraylist & return true
            if (it.next().getTitle().equals(byTitle)) {
                it.remove();
                System.out.println("The DVD " + byTitle + " has been removed from the library!");
                return;
            }
        }
        //if no DVD found, output no DVD with that name and return false
        System.out.println("No DVD was found in the library with that title!");
    }

    private void EditDVD(DVD in) {
        //Ask user which attribute they want to edit
        System.out.print("Please enter which attribute you would like to edit:\n" +
                "1. Title\n" +
                "2. Release Date\n" +
                "3. Age Rating\n" +
                "4. Director\n" +
                "5. Studio\n" +
                "6. User Note\n");
        int choice;
        try {
            choice = Input.getUserInt(input);
            if (choice < 1 || choice > 6) {
                throw new InvalidInputException("The number inputted does not correspond to an attribute!");
            } else {
                switch (choice) {
                    case 1:
                        in.Edit(DVDAttribute.TITLE, input);
                        break;
                    case 2:
                        in.Edit(DVDAttribute.RELEASE, input);
                        break;
                    case 3:
                        in.Edit(DVDAttribute.AGE, input);
                        break;
                    case 4:
                        in.Edit(DVDAttribute.DIR, input);
                        break;
                    case 5:
                        in.Edit(DVDAttribute.STUDIO, input);
                        break;
                    case 6:
                        in.Edit(DVDAttribute.NOTE, input);
                        break;
                }
                return;
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }



    private void PrintDVD(String byTitle) {
        //Search for DVD with matching title
        Iterator<DVD> it = library.iterator();
        while (it.hasNext()) {
            //if found call DVD.toString
            DVD current = it.next();
            if (current.getTitle().equals(byTitle)) {
                System.out.println("DVD found in library. Details:\n" +
                        current);
                return;
            }
        }
        //if not found, output dvd not found
        System.out.println("DVD with that title was not found in the library!");
    }

    private DVD SearchDVD(String byTitle) throws RuntimeException {
        //find the DVD with title matching
        Iterator<DVD> it = library.iterator();
        DVD out;
        while (it.hasNext()) {
            //if found call DVD.toString
            DVD current = it.next();
            if (current.getTitle().equals(byTitle)) {
                out = current;
                return out;
            }
        }
        return new DVD(); //is there a better way of returning nothing?
            //have to check when searchDVD is called?
    }

    private ArrayList<DVD> LoadFromFile(String filepath) throws RuntimeException {
        ArrayList<DVD> libOut = new ArrayList<DVD>();
        Scanner fileIn;
        try {
            fileIn = new Scanner(new BufferedReader(new FileReader(filepath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return libOut;
        }
        while (fileIn.hasNextLine()) {
            String[] current = fileIn.nextLine().split(",");
            libOut.add(new DVD(current[0], current[1], current[2], current[3], current[4], current[5]));
        }

        fileIn.close();

        return libOut;
    }

    private void SaveToFile(String filepath) throws IOException {
        //Open file to write to
        PrintWriter out = new PrintWriter(new FileWriter(filepath));

        for (DVD current : library) {
            //for each dvd, construct line
            String currentLine = current.getTitle() + ',' +
                    current.getReleaseDate() + ',' +
                    current.getAgeRating() + ',' +
                    current.getDirector() + ',' +
                    current.getStudio() + ',' +
                    current.getNote();
            //and write line to file
            out.println(currentLine);
        }

        out.flush();
        out.close();
        //close writer

    }

    private void PrintMenu() {
        //Print main menu with options for functionality + quitting the program
        System.out.println("Welcome to the DVD library!\n" +
                "Please choose an option:\n" +
                "1. Print all DVDs\n" +
                "2. Find a specific DVD\n" +
                "3. Print the details of a specific DVD\n" +
                "4. Add a new DVD to the library\n" +
                "5. Edit an existing DVD in the library\n" +
                "6. Remove a DVD from the library\n" +
                "7. Quit the application");
    }

    //menu option 1
    private void PrintAllDVDs() {
        //Call DVDLibrary.toString (override)
        System.out.println(this);
    }

    //menu option 2
    private void FindDVD() {
        System.out.println("Please enter the name of the DVD you want to search for: ");
        String searchFor = Input.getUserString(input);
        DVD found = SearchDVD(searchFor);
        if (!found.equalsNull()) {
            System.out.println("DVD found in library. Details:\n" +
                    found);

            int choice = -1;
            do {
                try {
                    System.out.print("Please choose an option:\n" +
                            "1. Edit DVD\n" +
                            "2. Remove DVD\n" +
                            "3. Return to Menu\n");
                    System.out.print("Please enter a number: ");
                    choice = Input.getUserInt(input);
                    if (choice < 1 || choice > 3) {
                        throw new InvalidInputException("The number inputted was invalid!");
                    }
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("\nThe value entered was not a number!");
                    input.next();
                }
            } while (choice < 1 || choice > 3);
            if (choice == 1) {
                EditDVD(found);
            } else if (choice == 2) {
                RemoveDVD(found.getTitle());
            }
        } else {
            System.out.println("DVD not found!\n");
        }
    }

    //menu option 3 (I'm aware some of these method signatures are similar to others)
    //A refactoring for another day
    private void PrintSpecificDVD() {
        System.out.print("Please enter the name of the DVD you want to search for: ");
        String searchFor = Input.getUserString(input);
        PrintDVD(searchFor);
    }

    //menu option 4
    private void AddDVDToLib() {
        AddDVD();
    }

    //menu option 5
    private void EditDVDInLib() {
        System.out.print("Please enter the name of the DVD you want to edit: ");
        String searchFor = Input.getUserString(input);
        DVD found = SearchDVD(searchFor);
        if (!found.equals(new DVD())) {
            EditDVD(found);
        } else {
            System.out.println("The DVD was not found in the library!");
        }
    }

    //menu option 6
    private void RemoveDVDFromLib() {
        System.out.print("Please enter the name of the DVD you want to remove: ");
        String searchFor = Input.getUserString(input);
        RemoveDVD(searchFor);
    }

    private void Run() {
        int pChoice = -1;
        do {
            PrintMenu();
            try {
                pChoice = Input.getUserInt(input);
                if (pChoice < 1 || pChoice > 7) {
                    throw new InvalidInputException("The number inputted was not between 1 and 7!");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (InputMismatchException e) {
                System.out.println("\nThe value entered was not a number!");
                input.next();
                continue;
            }

            switch (pChoice) {
                case 1:
                    PrintAllDVDs();
                    break;
                case 2:
                    FindDVD();
                    break;
                case 3:
                    PrintSpecificDVD();
                    break;
                case 4:
                    AddDVDToLib();
                    break;
                case 5:
                    EditDVDInLib();
                    break;
                case 6:
                    RemoveDVDFromLib();
                    break;
                default:
            }

        } while (pChoice != 7);
    }

    @Override
    public String toString() {
        String out = "Library {\n";
        for (DVD current : library) {
            String currentString = current.toString() + "\n";
            out = out + currentString;
        }
        out = out.concat("}");
        return out;
    }
}
