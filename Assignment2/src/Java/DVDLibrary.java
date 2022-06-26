package Java;

import Java.Errors.InvalidInputException;
import Java.Errors.MalformedObjectException;
import Java.Errors.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;

public class DVDLibrary {
    private ArrayList<DVD> library;

    public DVDLibrary(String filepath) {
        if (filepath != null) {
            library = LoadFromFile(filepath);
        } else {
            library = new ArrayList<DVD>();
        }
    }

    private void AddDVD() {
        //Get user input for each DVD attribute
        System.out.println("Please enter values for new DVD:");
        try {
            System.out.print("Title: ");
            String titleIn = Input.getUserString();
            System.out.print("Release date (format dd/mm/yyyy): ");
            String dateIn = Input.getUserString();
            System.out.print("Age rating: ");
            String ageIn = Input.getUserString();
            System.out.print("Director's name: ");
            String dirIn = Input.getUserString();
            System.out.print("Studio name: ");
            String studioIn = Input.getUserString();
            System.out.print("User note: ");
            String noteIn = Input.getUserString();
            //If all input is valid, create DVD object and add to library
            library.add(new DVD(titleIn, dateIn, ageIn, dirIn, studioIn, noteIn));
            //& output creation successful
            System.out.println("DVD creation successful!");
        } catch (RuntimeException e) { //if not, output that creation was invalid
            e.printStackTrace();
            System.out.println("DVD creation unsuccessful! Returning to menu...");
        }
    }

    private boolean RemoveDVD(String byTitle) {
        //search through library
        Iterator<DVD> it = library.iterator();
        while (it.hasNext()) {
            //if DVD found with title, remove from arraylist & return true
            if (it.next().getTitle().equals(byTitle)) {
                it.remove();
                System.out.println("The DVD " + byTitle + " has been removed from the library!");
                return true;
            }
        }
        //if no DVD found, output no DVD with that name and return false
        System.out.println("No DVD was found in the library with that title!");
        return false;
    }

    private boolean RemoveDVD(int index) {
        //remove DVD at index provided
        try {
            library.remove(index);
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The index provided was invalid!");
        }
        return false;
    }

    private boolean EditDVD(DVD in) {
        //Ask user which attribute they want to edit
        System.out.print("Please enter which attribute you would like to edit:\n" +
                "1. Title\n" +
                "2. Release Date\n" +
                "3. Age Rating\n" +
                "4. Director\n" +
                "5. Studio\n" +
                "6. User Note");
        int choice = -1;
        try {
            choice = Input.getUserInt();
            if (choice < 1 || choice > 6) {
                throw new InvalidInputException();
            } else {
                switch (choice) {
                    case 1:
                        in.Edit(DVDAttribute.TITLE);
                        break;
                    case 2:
                        in.Edit(DVDAttribute.RELEASE);
                        break;
                    case 3:
                        in.Edit(DVDAttribute.AGE);
                        break;
                    case 4:
                        in.Edit(DVDAttribute.DIR);
                        break;
                    case 5:
                        in.Edit(DVDAttribute.STUDIO);
                        break;
                    case 6:
                        in.Edit(DVDAttribute.NOTE);
                        break;
                }
                return true;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        //Call DVD.Edit(DVDAttribute)


        return false;
    }

    private void PrintAllDVDs() {
        //Call DVDLibrary.toString (override)
        this.toString();
    }

    private boolean PrintDVD(String byTitle) {
        //Search for DVD with matching title
        Iterator<DVD> it = library.iterator();
        while (it.hasNext()) {
            //if found call DVD.toString
            if (it.next().getTitle().equals(byTitle)) {
                System.out.println("DVD found in library. Details:\n" +
                        it.next().toString());
                return true;
            }
        }
        //if not found, output dvd not found
        System.out.println("DVD with that title was not found in the library!");
        return false;
    }

    private void PrintDVD(DVD in) {
        //call in.toString
        System.out.println("DVD " + in.toString());
    }

    private DVD SearchDVD(String byTitle) throws RuntimeException {
        //find the DVD with title matching
        Iterator<DVD> it = library.iterator();
        DVD out = new DVD();
        while (it.hasNext()) {
            //if found call DVD.toString
            if (it.next().getTitle().equals(byTitle)) {
                out = it.next();
            }
        }
        //return if found
        if (!out.equals(new DVD())) {
            return out;
        } else {
            return new DVD(); //is there a better way of returning nothing?
            //have to check when searchDVD is called?
        }
    }

    private ArrayList<DVD> LoadFromFile(String filepath) {
        //TODO File I/O
        return new ArrayList<DVD>();
    }

    private void SaveToFile(String filepath) {
        //TODO File I/O
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

    @Override
    public String toString() {
        String out = "Library {\n";
        for (DVD current : library) {
            out.concat("{ " + current.toString() + " }\n");
        }
        out.concat("}");
        return out;
    }
}
